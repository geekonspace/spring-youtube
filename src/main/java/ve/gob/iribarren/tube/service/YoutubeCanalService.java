package ve.gob.iribarren.tube.service;
import java.util.List;
import ve.gob.iribarren.tube.model.YoutubeCanal;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:12:36
 *
 */
public interface YoutubeCanalService {

	public abstract long countAllYoutubeCanals();


	public abstract void deleteYoutubeCanal(YoutubeCanal youtubeCanal);


	public abstract YoutubeCanal findYoutubeCanal(Long id);


	public abstract List<YoutubeCanal> findAllYoutubeCanals();


	public abstract List<YoutubeCanal> findYoutubeCanalEntries(int firstResult, int maxResults);


	public abstract void saveYoutubeCanal(YoutubeCanal youtubeCanal);


	public abstract YoutubeCanal updateYoutubeCanal(YoutubeCanal youtubeCanal);

}
