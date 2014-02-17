package ve.gob.iribarren.tube.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve.gob.iribarren.tube.model.YoutubeCanal;
import ve.gob.iribarren.tube.repository.YoutubeCanalRepository;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:12:41
 *
 */
@Service
@Transactional
public class YoutubeCanalServiceImpl implements YoutubeCanalService {

	@Autowired
    YoutubeCanalRepository youtubeCanalRepository;

	public long countAllYoutubeCanals() {
        return youtubeCanalRepository.count();
    }

	public void deleteYoutubeCanal(YoutubeCanal youtubeCanal) {
        youtubeCanalRepository.delete(youtubeCanal);
    }

	public YoutubeCanal findYoutubeCanal(Long id) {
        return youtubeCanalRepository.findOne(id);
    }

	public List<YoutubeCanal> findAllYoutubeCanals() {
        return youtubeCanalRepository.findAll();
    }

	public List<YoutubeCanal> findYoutubeCanalEntries(int firstResult, int maxResults) {
        return youtubeCanalRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveYoutubeCanal(YoutubeCanal youtubeCanal) {
        youtubeCanalRepository.save(youtubeCanal);
    }

	public YoutubeCanal updateYoutubeCanal(YoutubeCanal youtubeCanal) {
        return youtubeCanalRepository.save(youtubeCanal);
    }
}
