/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

import ve.gob.iribarren.tube.model.YoutubeCanal;
import ve.gob.iribarren.tube.repository.YoutubeCanalRepository;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

/**
 *
 * @author Williams Rivas
 * Created 18/02/2014 14:09:49
 *
 */
@RequestMapping("/youtubecanals")
@Controller
@RooWebScaffold(path = "youtubecanals", formBackingObject = YoutubeCanal.class)
public class YoutubeCanalController {

    @Autowired
    YoutubeCanalRepository youtubeCanalRepository;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid YoutubeCanal youtubeCanal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, youtubeCanal);
            return "youtubecanals/create";
        }
        uiModel.asMap().clear();
        youtubeCanalRepository.save(youtubeCanal);
        return "redirect:/youtubecanals/" + encodeUrlPathSegment(youtubeCanal.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
    	List<YoutubeCanal> canals = youtubeCanalRepository.findAll();
    	if(canals != null && canals.size() > 0){ //solo debe ser uno
    		YoutubeCanal canal = canals.get(0);
            populateEditForm(uiModel, canal);
            return "youtubecanals/update";
    	}
        populateEditForm(uiModel, new YoutubeCanal());
        return "youtubecanals/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("youtubecanal", youtubeCanalRepository.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "youtubecanals/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("youtubecanals", youtubeCanalRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) youtubeCanalRepository.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("youtubecanals", youtubeCanalRepository.findAll());
        }
        return "youtubecanals/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid YoutubeCanal youtubeCanal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, youtubeCanal);
            return "youtubecanals/update";
        }
        uiModel.asMap().clear();
        youtubeCanalRepository.save(youtubeCanal);
        return "redirect:/youtubecanals/" + encodeUrlPathSegment(youtubeCanal.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, youtubeCanalRepository.findOne(id));
        return "youtubecanals/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        YoutubeCanal youtubeCanal = youtubeCanalRepository.findOne(id);
        youtubeCanalRepository.delete(youtubeCanal);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/youtubecanals";
    }

    void populateEditForm(Model uiModel, YoutubeCanal youtubeCanal) {
        uiModel.addAttribute("youtubeCanal", youtubeCanal);
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }
}
