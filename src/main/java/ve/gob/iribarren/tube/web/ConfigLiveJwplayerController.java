/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayer;
import ve.gob.iribarren.tube.repository.ConfigLiveJwplayerRepository;

/**
 * 
 * @author Williams Rivas
 * Created 18/02/2014 14:09:09
 *
 */
@RequestMapping("/configlivejwplayers")
@Controller
public class ConfigLiveJwplayerController {

	@Autowired
    ConfigLiveJwplayerRepository configLiveJwplayerRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid ConfigLiveJwplayer configLiveJwplayer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, configLiveJwplayer);
            return "configlivejwplayers/create";
        }
        uiModel.asMap().clear();
        configLiveJwplayerRepository.save(configLiveJwplayer);
        return "redirect:/configlivejwplayers/" + encodeUrlPathSegment(configLiveJwplayer.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new ConfigLiveJwplayer());
        return "configlivejwplayers/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("configlivejwplayer", configLiveJwplayerRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "configlivejwplayers/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("configlivejwplayers", configLiveJwplayerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) configLiveJwplayerRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("configlivejwplayers", configLiveJwplayerRepository.findAll());
        }
        return "configlivejwplayers/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid ConfigLiveJwplayer configLiveJwplayer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, configLiveJwplayer);
            return "configlivejwplayers/update";
        }
        uiModel.asMap().clear();
        configLiveJwplayerRepository.save(configLiveJwplayer);
        return "redirect:/configlivejwplayers/" + encodeUrlPathSegment(configLiveJwplayer.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, configLiveJwplayerRepository.findOne(id));
        return "configlivejwplayers/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ConfigLiveJwplayer configLiveJwplayer = configLiveJwplayerRepository.findOne(id);
        configLiveJwplayerRepository.delete(configLiveJwplayer);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/configlivejwplayers";
    }

	void populateEditForm(Model uiModel, ConfigLiveJwplayer configLiveJwplayer) {
        uiModel.addAttribute("configLiveJwplayer", configLiveJwplayer);
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
