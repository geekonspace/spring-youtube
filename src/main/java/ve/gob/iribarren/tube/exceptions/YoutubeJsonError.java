/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Simple POJO donde se guardara la data relacionada a un error generado durante
 * el intento de obtener los videos desde youtube
 * 
 * Ejemplo de un json error:
 * 
 * <pre>
 * {
 * "error": {
 *  "errors": [
 *   {
 *    "domain": "youtube.part",
 *    "reason": "unknownPart",
 *    "message": "contentDetails",
 *    "locationType": "parameter",
 *    "location": "part"
 *   }
 *  ],
 *  "code": 400,
 *  "message": "contentDetails"
 *  }
 * }
 * </pre>
 * 
 * @author Williams Rivas Created 21/02/2014 08:29:28
 * 
 */
public class YoutubeJsonError {

	private String domain;

	private String reason;

	private String locationType;

	private String location;

	private int code;

	private String message;

	public YoutubeJsonError(String jsonError) {
		JSONObject jo = new JSONObject(jsonError);
		if (jo.has("error")) {
			JSONObject joError = jo.getJSONObject("error");
			code = joError.getInt("code");
			message = joError.getString("message");
			JSONArray jaErrors = new JSONArray(joError.get("errors"));
			JSONObject jaError = jaErrors.getJSONObject(0);
			domain = jaError.getString("domain");
			reason = jaError.getString("reason");
			locationType = jaError.getString("locationType");
			location = jaError.getString("location");
		}

	}

	public YoutubeJsonError(String domain, String reason, String locationType,
			String location, int code, String message) {
		super();
		this.domain = domain;
		this.reason = reason;
		this.locationType = locationType;
		this.location = location;
		this.code = code;
		this.message = message;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
