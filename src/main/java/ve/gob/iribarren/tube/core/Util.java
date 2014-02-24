/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ve.gob.iribarren.tube.exceptions.SearchYoutubeException;
import ve.gob.iribarren.tube.exceptions.YoutubeJsonError;

/**
 * 
 * @author Williams Rivas Created 20/02/2014 13:41:56
 * 
 */
public class Util {
	
	/**
	 * Dado un inputstream, se devuelve su representacion en String.
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream in) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder buffer = new StringBuilder();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	public static String httpGet(String stringUrl) throws SearchYoutubeException{
		URL url;
		try {
			url = new URL(stringUrl);
		} catch (MalformedURLException e2) {
			throw new SearchYoutubeException(e2.getMessage());
		}
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
