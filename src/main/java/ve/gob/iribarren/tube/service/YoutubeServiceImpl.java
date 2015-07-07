/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ve.gob.iribarren.tube.core.Util;
import ve.gob.iribarren.tube.exceptions.HttpGetException;
import ve.gob.iribarren.tube.exceptions.SearchYoutubeException;
import ve.gob.iribarren.tube.model.PageResultYoutube;
import ve.gob.iribarren.tube.model.YoutubeCanal;
import ve.gob.iribarren.tube.repository.YoutubeCanalRepository;

/**
 * 
 * @author Williams Rivas Created 20/02/2014 12:59:49
 * 
 */
@Service
public class YoutubeServiceImpl implements YoutubeService {

	public static final String PARAM_MAX_RESULTS = "maxResults";

	private static int DEFAULT_VALUE_MAX_RESULTS = 16;

	private static String DEFAULT_VALUE_CHANNEL_ID = "UCIwcrFgqc3-g_Cl9L77PWYw";

	public static final String PARAM_PART = "part";

	public static final String DEFAULT_VALUE_PART = "snippet";

	public static final String DEFAULT_VALUE_PARTS_VIDEOS = "contentDetails%2Cstatistics";

	public static final String PARAM_PAGE_TOKEN = "pageToken";

	public static final String PARAM_CHANNEL_ID = "channelId";

	public static final String PARAM_VIDEO_IDS = "id";

	public static final String PARAM_KEY = "key";

	private static final String DEFAULT_VALUE_KEY = "XXX";

	public static final String URL_SEARCH_YOUTUBE = "https://www.googleapis.com/youtube/v3/search?type=video&";

	public static final String URL_VIDEOS_LIST = "https://www.googleapis.com/youtube/v3/videos?";

	public static final String PARAM_VALUE_SEPARATOR = "=";

	public static final String PARAM_VALUE_CONCAT = "&";

	public static final String PARAM_VALUE_COMMA = "%2C";

	@Autowired
	protected YoutubeCanalRepository youtubeCanalRepository;

	@Override
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults, String pageToken) throws SearchYoutubeException {
		setDefaultValues();
		String query = buildQueryUrl(part, channelId, maxResults, pageToken,
				null);
		String dataJson = Util.httpGet(query);

		PageResultYoutube page = new PageResultYoutube(dataJson);

		String videoIds = page.getVideoIdsSeparetedByComma();

		String contentDetailsJson = "";
		try {
			contentDetailsJson = Util.httpGet(buildQueryUrl(
					DEFAULT_VALUE_PARTS_VIDEOS, null, DEFAULT_VALUE_MAX_RESULTS,
					null, videoIds));			
		} catch (HttpGetException e) {
			throw new SearchYoutubeException(e.getMessage());
		}

		page.setContentDetailsVideos(contentDetailsJson);

		return page;
	}

	@Override
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults) throws SearchYoutubeException {
		return searchYoutubeVideos(part, channelId, maxResults, null);
	}

	@Override
	public PageResultYoutube searchYoutubeVideos()
			throws SearchYoutubeException {
		return searchYoutubeVideos(DEFAULT_VALUE_PART,
				DEFAULT_VALUE_CHANNEL_ID, DEFAULT_VALUE_MAX_RESULTS);
	}

	private String buildQueryUrl(String part, String channelId, int maxResults,
			String pageToken, String videoIds) {
		StringBuilder url;
		if (channelId != null && !channelId.equals("")) {
			url = new StringBuilder(URL_SEARCH_YOUTUBE);
			// parametro part
			url.append(PARAM_PART).append(PARAM_VALUE_SEPARATOR);
			if (part != null && !part.equals("")) {
				if (!part.equals(DEFAULT_VALUE_PART)) {
					url.append(DEFAULT_VALUE_PART);
				} else {
					url.append(part);
				}
			} else {
				url.append(DEFAULT_VALUE_PART);
			}

			// parametro channel id
			url.append(PARAM_VALUE_CONCAT).append(PARAM_CHANNEL_ID)
					.append(PARAM_VALUE_SEPARATOR);
			if (channelId != null && !channelId.equals("")) {
				url.append(channelId);
			} else {
				url.append(DEFAULT_VALUE_CHANNEL_ID);
			}
			// pagina
			if (pageToken != null && !pageToken.equals("")) {
				url.append(PARAM_VALUE_CONCAT).append(PARAM_PAGE_TOKEN)
						.append(PARAM_VALUE_SEPARATOR).append(pageToken);
			}
		} else { // busqueda no por canal sino por ids de videos
					// https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id=uWKM4F2RAtI%2CJ2NIttHwZBA%2C6UliPT1LIc4%2CSaLWwDyHMvo&maxResults=3&key=AIzaSyBB8x12DXzrzXKhkum5f_Nv3Yl7-0GSwCg
			url = new StringBuilder(URL_VIDEOS_LIST);

			// parametro part
			url.append(PARAM_PART).append(PARAM_VALUE_SEPARATOR);
			if (part != null && !part.equals("")) {
				if (!part.equals(DEFAULT_VALUE_PARTS_VIDEOS)) {
					url.append(DEFAULT_VALUE_PARTS_VIDEOS);
				} else {
					url.append(part);
				}
			} else {
				url.append(DEFAULT_VALUE_PARTS_VIDEOS);
			}
			// videos ids separados por comma
			if (videoIds != null && !videoIds.equals("")) {
				url.append(PARAM_VALUE_CONCAT).append(PARAM_VIDEO_IDS)
						.append(PARAM_VALUE_SEPARATOR);
				url.append(videoIds);
			}
		}

		// max results
		url.append(PARAM_VALUE_CONCAT).append(PARAM_MAX_RESULTS)
				.append(PARAM_VALUE_SEPARATOR);
		if (maxResults <= 0) {
			url.append(DEFAULT_VALUE_MAX_RESULTS);
		} else {
			url.append(maxResults);
		}

		// key developer
		url.append(PARAM_VALUE_CONCAT).append(PARAM_KEY)
				.append(PARAM_VALUE_SEPARATOR).append(DEFAULT_VALUE_KEY);

		return url.toString();
	}

	@Override
	public PageResultYoutube searchYoutubeVideos(String pageToken)
			throws SearchYoutubeException {
		return searchYoutubeVideos(DEFAULT_VALUE_PART,
				DEFAULT_VALUE_CHANNEL_ID, DEFAULT_VALUE_MAX_RESULTS, pageToken);
	}

	private void setDefaultValues() {
		List<YoutubeCanal> canals = youtubeCanalRepository.findAll();
		if (canals != null && canals.size() > 0) {
			DEFAULT_VALUE_CHANNEL_ID = canals.get(0).getIdChannel();
			DEFAULT_VALUE_MAX_RESULTS = canals.get(0).getMaxResults();
		}
	}
}
