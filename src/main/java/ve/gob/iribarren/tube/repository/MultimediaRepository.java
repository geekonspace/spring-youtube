package ve.gob.iribarren.tube.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ve.gob.iribarren.tube.model.Multimedia;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:16:59
 *
 */
@Repository
public interface MultimediaRepository extends JpaSpecificationExecutor<Multimedia>, JpaRepository<Multimedia, Long> {
}
