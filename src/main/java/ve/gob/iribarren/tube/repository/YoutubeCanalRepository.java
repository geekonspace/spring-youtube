package ve.gob.iribarren.tube.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;
import ve.gob.iribarren.tube.model.YoutubeCanal;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:06:59
 *
 */
@Repository
@RooJpaRepository(domainType = YoutubeCanal.class)
public interface YoutubeCanalRepository extends JpaRepository<YoutubeCanal, Long>, JpaSpecificationExecutor<YoutubeCanal> {
}
