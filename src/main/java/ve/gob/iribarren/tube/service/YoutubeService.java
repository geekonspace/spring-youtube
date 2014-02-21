/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.service;

import ve.gob.iribarren.tube.exceptions.SearchYoutubeException;
import ve.gob.iribarren.tube.model.PageResultYoutube;

/**
 * Servicio que utiliza la api de busqueda expuesta por youtube para obtener
 * videos en base a ciertos criterios de busquedas.
 * 
 * <a href="https://developers.google.com/youtube/v3/docs/search/list">https://
 * developers.google.com/youtube/v3/docs/search/list</a>
 * 
 * @author Williams Rivas Created 21/02/2014 09:03:41
 * 
 */
public interface YoutubeService {

	/**
	 * Busca videos de youtube retornando como resultado el objeto
	 * PageResultYoutube el cual contendra informacion sobre la pagina y videos
	 * por pagina. Ejemplo de una url youtube:
	 * </br>
	 * url: https://www.googleapis.com/youtube/v3/search?part=snippet&pageToken=CAoQAA&channelId=UCIwcrFgqc3-g_Cl9L77PWYw&maxResults=10&key=AIzaSyBB8x12DXzrzXKhkum5f_Nv3Yl7-0GSwCg
	 * 
	 * @param part por defecto debe ser "snippet"
	 * @param channelId el id del canal que contendra los videos relacionados
	 * @param maxResults maximo de resultados por pagina
	 * @param pageToken representa el token o id de la pagina, siguiente o pagina anterior
	 * @return
	 * @throws SearchYoutubeException
	 */
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults, String pageToken) throws SearchYoutubeException;

	/**
	 * Busca videos de youtube retornando como resultado el objeto
	 * PageResultYoutube el cual contendra informacion sobre la pagina y videos
	 * por pagina.
	 * 
	 * @param part
	 * @param channelId
	 * @param maxResults
	 * @return
	 * @throws SearchYoutubeException
	 */
	public PageResultYoutube searchYoutubeVideos(String part, String channelId,
			int maxResults) throws SearchYoutubeException;

	/**
	 * Busca videos de youtube retornando como resultado el objeto
	 * PageResultYoutube el cual contendra informacion sobre la pagina y videos
	 * por pagina.
	 * 
	 * @return
	 * @throws SearchYoutubeException
	 */
	public PageResultYoutube searchYoutubeVideos()
			throws SearchYoutubeException;
}
