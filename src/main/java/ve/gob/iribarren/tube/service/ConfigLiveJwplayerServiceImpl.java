package ve.gob.iribarren.tube.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve.gob.iribarren.tube.model.ConfigLiveJwplayer;
import ve.gob.iribarren.tube.repository.ConfigLiveJwplayerRepository;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:19:01
 *
 */
@Service
@Transactional
public class ConfigLiveJwplayerServiceImpl implements ConfigLiveJwplayerService {

	@Autowired
    ConfigLiveJwplayerRepository configLiveJwplayerRepository;

	public long countAllConfigLiveJwplayers() {
        return configLiveJwplayerRepository.count();
    }

	public void deleteConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer) {
        configLiveJwplayerRepository.delete(configLiveJwplayer);
    }

	public ConfigLiveJwplayer findConfigLiveJwplayer(Long id) {
        return configLiveJwplayerRepository.findOne(id);
    }

	public List<ConfigLiveJwplayer> findAllConfigLiveJwplayers() {
        return configLiveJwplayerRepository.findAll();
    }

	public List<ConfigLiveJwplayer> findConfigLiveJwplayerEntries(int firstResult, int maxResults) {
        return configLiveJwplayerRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer) {
        configLiveJwplayerRepository.save(configLiveJwplayer);
    }

	public ConfigLiveJwplayer updateConfigLiveJwplayer(ConfigLiveJwplayer configLiveJwplayer) {
        return configLiveJwplayerRepository.save(configLiveJwplayer);
    }
}
