/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ve.gob.iribarren.tube.repository.CategoryRepository;

/**
 * 
 * @author Williams Rivas
 * Created 18/02/2014 14:10:10
 *
 */
@RequestMapping("/inicio/**")
@Controller
public class InicioController {

	@Autowired
    CategoryRepository categoryRepository;
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(Model uiModel) {
    	uiModel.addAttribute("categories", categoryRepository.findAll());
        return "inicio/index";
    }
}
