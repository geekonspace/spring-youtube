/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import ve.gob.iribarren.tube.exceptions.SearchYoutubeException;
import ve.gob.iribarren.tube.exceptions.YoutubeJsonError;
import ve.gob.iribarren.tube.model.PageResultYoutube;

/**
 * 
 * @author Williams Rivas Created 20/02/2014 12:59:49
 * 
 */
@Service
public class YoutubeServiceImpl implements YoutubeService {

	public static final String PARAM_MAX_RESULTS = "maxResults";

	public static final int DEFAULT_VALUE_MAX_RESULTS = 15;

	public static final String PARAM_PART = "part";

	public static final String DEFAULT_VALUE_PART = "snippet";

	public static final String PARAM_PAGE_TOKEN = "pageToken";

	public static final String PARAM_CHANNEL_ID = "channelId";

	public static final String DEFAULT_VALUE_CHANNEL_ID = "UCIwcrFgqc3-g_Cl9L77PWYw";

	public static final String PARAM_KEY = "key";

	private static final String DEFAULT_VALUE_KEY = "AIzaSyBB8x12DXzrzXKhkum5f_Nv3Yl7-0GSwCg";

	public static final String URL_SEARCH_YOUTUBE = "https://www.googleapis.com/youtube/v3/search?";

	public static final String PARAM_VALUE_SEPARATOR = "=";

	public static final String PARAM_VALUE_CONCAT = "&";

	@Override
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults, String pageToken) throws SearchYoutubeException {
		String query = buildQueryUrl(part, channelId, maxResults, pageToken);
		try {
			String dataJson = invokeRequest(query);
			return new PageResultYoutube(dataJson);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new SearchYoutubeException(e.getMessage());
		}
	}

	@Override
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults) throws SearchYoutubeException {
		return searchYoutubeVideos(part, channelId, maxResults, null);
	}

	@Override
	public PageResultYoutube searchYoutubeVideos()
			throws SearchYoutubeException {
		return searchYoutubeVideos(null, null, 0, null);
	}

	private String buildQueryUrl(String part, String channelId, int maxResults,
			String pageToken) {
		StringBuilder url = new StringBuilder(URL_SEARCH_YOUTUBE);
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
		// max results
		url.append(PARAM_VALUE_CONCAT).append(PARAM_MAX_RESULTS)
				.append(PARAM_VALUE_SEPARATOR);
		if (maxResults <= 0) {
			url.append(DEFAULT_VALUE_MAX_RESULTS);
		} else {
			url.append(maxResults);
		}
		// pagina
		if (pageToken != null && pageToken.equals("")) {
			url.append(PARAM_VALUE_CONCAT).append(PARAM_PAGE_TOKEN)
					.append(PARAM_VALUE_SEPARATOR).append(pageToken);
		}
		// key developer
		url.append(PARAM_VALUE_CONCAT).append(PARAM_KEY)
				.append(PARAM_VALUE_SEPARATOR).append(DEFAULT_VALUE_KEY);
		return url.toString();
	}

	protected String invokeRequest(String stringUrl)
			throws MalformedURLException, SearchYoutubeException {
		URL url = new URL(stringUrl);
		HttpURLConnection urlConnection = null;
		String dataJson = "";
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(5000); // set timeout to 5 seconds
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			dataJson = sb.toString();
		} catch (java.net.SocketTimeoutException e) {
			throw new SearchYoutubeException(e.getMessage());
		} catch (IOException e) {
			if (urlConnection instanceof HttpURLConnection) {
				HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
				InputStream in = null;
				try {
					in = httpConn.getErrorStream();
					StringBuffer buf = new StringBuffer();
					byte[] cbuf = new byte[1024 * 64];
					int r = in.read(cbuf);
					while (r > -1) {
						if (r > 0) {
							buf.append(new String(cbuf, 0, r));
						}
						r = in.read(cbuf);
					}
					YoutubeJsonError jsonError = new YoutubeJsonError(
							buf.toString());
					throw new SearchYoutubeException(jsonError.getMessage());

				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return dataJson;
	}

}
