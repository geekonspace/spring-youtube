package ve.gob.iribarren.tube.service;
import java.util.List;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayer;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:18:54
 *
 */
public interface ConfigLiveJwplayerService {

	public abstract long countAllConfigLiveJwplayers();


	public abstract void deleteConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer);


	public abstract ConfigLiveJwplayer findConfigLiveJwplayer(Long id);


	public abstract List<ConfigLiveJwplayer> findAllConfigLiveJwplayers();


	public abstract List<ConfigLiveJwplayer> findConfigLiveJwplayerEntries(int firstResult, int maxResults);


	public abstract void saveConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer);


	public abstract ConfigLiveJwplayer updateConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer);

}
