package ve.gob.iribarren.tube.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve.gob.iribarren.tube.model.Category;
import ve.gob.iribarren.tube.repository.CategoryRepository;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:07:14
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    CategoryRepository categoryRepository;

	public long countAllCategorys() {
        return categoryRepository.count();
    }

	public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

	public Category findCategory(Long id) {
        return categoryRepository.findOne(id);
    }

	public List<Category> findAllCategorys() {
        return categoryRepository.findAll();
    }

	public List<Category> findCategoryEntries(int firstResult, int maxResults) {
        return categoryRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

	public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
