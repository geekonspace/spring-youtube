package ve.gob.iribarren.tube.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayer;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:15:33
 *
 */
@Repository
public interface ConfigLiveJwplayerRepository extends JpaRepository<ConfigLiveJwplayer, Long>, JpaSpecificationExecutor<ConfigLiveJwplayer> {
}
