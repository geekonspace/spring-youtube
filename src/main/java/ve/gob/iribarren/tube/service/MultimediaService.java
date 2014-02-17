package ve.gob.iribarren.tube.service;
import java.util.List;
import ve.gob.iribarren.tube.model.Multimedia;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:26:29
 *
 */
public interface MultimediaService {

	public abstract long countAllMultimedias();


	public abstract void deleteMultimedia(Multimedia multimedia);


	public abstract Multimedia findMultimedia(Long id);


	public abstract List<Multimedia> findAllMultimedias();


	public abstract List<Multimedia> findMultimediaEntries(int firstResult, int maxResults);


	public abstract void saveMultimedia(Multimedia multimedia);


	public abstract Multimedia updateMultimedia(Multimedia multimedia);

}
