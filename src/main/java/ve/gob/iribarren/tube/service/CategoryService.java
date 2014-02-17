package ve.gob.iribarren.tube.service;
import java.util.List;
import ve.gob.iribarren.tube.model.Category;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:07:06
 *
 */
public interface CategoryService {

	public abstract long countAllCategorys();


	public abstract void deleteCategory(Category category);


	public abstract Category findCategory(Long id);


	public abstract List<Category> findAllCategorys();


	public abstract List<Category> findCategoryEntries(int firstResult, int maxResults);


	public abstract void saveCategory(Category category);


	public abstract Category updateCategory(Category category);

}
