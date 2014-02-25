/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.model;

import java.util.Date;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * Simple POJO donde se almacenara la data relacionada a un video desde youtube.
 * 
 * @author Williams Rivas Created 21/02/2014 08:03:31
 * 
 */
public class VideoYoutube {
	private String videoId;

	private Date publischedAt;

	private String channelId;

	private String title;

	private String description;

	private String thumbnail;

	private String channelTitle;

	private String duration;

	private Long viewCount;

	public VideoYoutube() {
		this.videoId = "";
		this.publischedAt = null;
		this.channelId = "";
		this.title = "";
		this.description = "";
		this.thumbnail = "";
		this.channelTitle = "";
		this.duration = "";
		this.viewCount = null;
	}

	public VideoYoutube(String videoId, Date publischedAt, String channelId,
			String title, String description, String thumbnail,
			String channelTitle, String duration, Long viewCount) {
		super();
		this.videoId = videoId;
		this.publischedAt = publischedAt;
		this.channelId = channelId;
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
		this.channelTitle = channelTitle;
		this.duration = duration;
		this.viewCount = viewCount;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Date getPublischedAt() {
		return publischedAt;
	}

	public void setPublischedAt(Date publischedAt) {
		this.publischedAt = publischedAt;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getDuration() {
		if (duration != null && !duration.equals("")) {
			StringBuilder strPeriod = new StringBuilder();
			PeriodFormatter formatter = ISOPeriodFormat.standard();
			Period period = formatter.parsePeriod(duration);
			if (period.getHours() > 0) {
				strPeriod.append(period.getHours()).append(":");
			}
			if (period.getMinutes() < 10) {
				strPeriod.append("0").append(period.getMinutes()).append(":");
			} else {
				strPeriod.append(period.getMinutes()).append(":");
			}
			if (period.getSeconds() < 10) {
				strPeriod.append("0").append(period.getSeconds());
			} else {
				strPeriod.append(period.getSeconds());
			}
			return strPeriod.toString();
		}
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

}
