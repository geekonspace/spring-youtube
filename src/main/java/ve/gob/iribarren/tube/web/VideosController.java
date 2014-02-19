package ve.gob.iribarren.tube.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ve.gob.iribarren.tube.repository.CategoryRepository;

@RequestMapping("/videos/**")
@Controller
public class VideosController {

	@Autowired
    CategoryRepository categoryRepository;
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(ModelMap uiModel) {
    	uiModel.addAttribute("categories", categoryRepository.findAll());    	
        return "videos/index";
    }
}
