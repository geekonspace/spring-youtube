package ve.gob.iribarren.tube.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;
import ve.gob.iribarren.tube.model.Category;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:06:51
 *
 */
@Repository
@RooJpaRepository(domainType = Category.class)
public interface CategoryRepository extends JpaSpecificationExecutor<Category>, JpaRepository<Category, Long> {
	
}
