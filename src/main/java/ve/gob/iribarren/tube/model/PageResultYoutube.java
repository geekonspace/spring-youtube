/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Simple POJO donde se mantendra la data del resultado por pagina de los videos
 * consultados
 * 
 * @author Williams Rivas Created 21/02/2014 08:09:23
 * 
 */
public class PageResultYoutube {
	private String nextPageToken;

	private String prevPageToken;

	private int totalResults;

	private int resultsPerPage;

	private Map<String, VideoYoutube> videosResults;

	public PageResultYoutube(String nextPageToken, String prevPageToken,
			int totalResults, int resultsPerPage,
			Map<String, VideoYoutube> videosResults) {
		super();
		this.nextPageToken = nextPageToken;
		this.prevPageToken = prevPageToken;
		this.totalResults = totalResults;
		this.resultsPerPage = resultsPerPage;
		this.videosResults = videosResults;
	}

	public PageResultYoutube(String dataJson) {
		JSONObject jo = new JSONObject(dataJson);
		if (jo.has("nextPageToken")) {
			nextPageToken = jo.getString("nextPageToken");
		}
		if (jo.has("prevPageToken")) {
			prevPageToken = jo.getString("prevPageToken");
		}
		if (jo.has("pageInfo")) {
			JSONObject pageInfo = jo.getJSONObject("pageInfo");
			if (!jo.has("prevPageToken")) { // first page. restar un elemento
				totalResults = pageInfo.getInt("totalResults") - 1;
			} else {
				totalResults = pageInfo.getInt("totalResults");
			}
			resultsPerPage = pageInfo.getInt("resultsPerPage");
		}
		videosResults = new HashMap<String, VideoYoutube>();
		if (jo.has("items")) {
			JSONArray items = jo.getJSONArray("items");
			VideoYoutube video;
			for (int i = 0; i < items.length(); i++) {
				video = new VideoYoutube();
				JSONObject item = items.getJSONObject(i);
				JSONObject id = item.getJSONObject("id");
				if (!id.getString("kind").equals("youtube#video")) {
					continue;
				}
				JSONObject snippet = item.getJSONObject("snippet");
				video.setVideoId(id.getString("videoId"));
				video.setChannelId(snippet.getString("channelId"));
				video.setChannelTitle(snippet.getString("channelTitle"));
				video.setDescription(snippet.getString("description"));
				video.setPublischedAt(new DateTime(snippet
						.getString("publishedAt")).toDate());
				video.setThumbnail(snippet.getJSONObject("thumbnails")
						.getJSONObject("default").getString("url"));
				video.setTitle(snippet.getString("title"));
				videosResults.put(video.getVideoId(), video);
			}

		}

	}

	public String getVideoIdsSeparetedByComma() {
		StringBuilder str = new StringBuilder();
		if (videosResults != null && videosResults.size() > 0) {
			for (Entry<String, VideoYoutube> entry : videosResults.entrySet()) {
				str.append(entry.getKey()).append(",");
			}
		}// XXX fix validation
		String finalStr = str.toString();
		finalStr = finalStr.substring(0, finalStr.toString().length() - 1);
		return finalStr;
	}

	public void addVideo(VideoYoutube video) {
		videosResults.put(video.getVideoId(), video);
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public String getPrevPageToken() {
		return prevPageToken;
	}

	public void setPrevPageToken(String prevPageToken) {
		this.prevPageToken = prevPageToken;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public Map<String, VideoYoutube> getVideosResults() {
		return videosResults;
	}

	public void setVideosResults(Map<String, VideoYoutube> videosResults) {
		this.videosResults = videosResults;
	}

	public void setContentDetailsVideos(String contentDetailsJson) {
		JSONObject jo = new JSONObject(contentDetailsJson);
		if (jo.has("items")) {
			JSONArray items = jo.getJSONArray("items");
			for (int i = 0; i < items.length(); i++) {
				JSONObject item = items.getJSONObject(i);
				VideoYoutube video = videosResults.get(item.get("id"));
				JSONObject cd = item.getJSONObject("contentDetails");
				video.setDuration(cd.getString("duration"));
				JSONObject stats = item.getJSONObject("statistics");
				video.setViewCount(stats.getLong("viewCount"));
				videosResults.put(video.getVideoId(), video);
			}
		}
	}

}
