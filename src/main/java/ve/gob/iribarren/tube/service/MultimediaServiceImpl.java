package ve.gob.iribarren.tube.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ve.gob.iribarren.tube.model.Multimedia;
import ve.gob.iribarren.tube.repository.MultimediaRepository;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:26:35
 *
 */
@Service
@Transactional
public class MultimediaServiceImpl implements MultimediaService {

	@Autowired
    MultimediaRepository multimediaRepository;

	public long countAllMultimedias() {
        return multimediaRepository.count();
    }

	public void deleteMultimedia(Multimedia multimedia) {
        multimediaRepository.delete(multimedia);
    }

	public Multimedia findMultimedia(Long id) {
        return multimediaRepository.findOne(id);
    }

	public List<Multimedia> findAllMultimedias() {
        return multimediaRepository.findAll();
    }

	public List<Multimedia> findMultimediaEntries(int firstResult, int maxResults) {
        return multimediaRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveMultimedia(Multimedia multimedia) {
        multimediaRepository.save(multimedia);
    }

	public Multimedia updateMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }
}
