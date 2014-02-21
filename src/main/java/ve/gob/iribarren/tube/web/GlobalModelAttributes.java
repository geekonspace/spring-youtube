/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import ve.gob.iribarren.tube.model.Category;
import ve.gob.iribarren.tube.repository.CategoryRepository;

/**
 * 
 * @author Williams Rivas
 * Created 19/02/2014 13:14:57
 *
 */
public class GlobalModelAttributes {
	@Autowired
    CategoryRepository categoryRepository;
	
	
	@ModelAttribute("categories")
	protected List<Category> findAllCategories(){
    	return categoryRepository.findAll();
	}
}
