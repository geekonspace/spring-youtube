/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ve.gob.iribarren.tube.model.PageResultYoutube;
import ve.gob.iribarren.tube.service.YoutubeService;

/**
 * 
 * @author Williams Rivas Created 19/02/2014 09:20:37
 * 
 */

@RequestMapping("/youtube/**")
@Controller
public class YoutubeController extends GlobalModelAttributes {

	@Autowired
	private YoutubeService youtube;

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping
	public String index(
			@RequestParam(value = "page", required = false) String page,
			ModelMap uiModel) {
		PageResultYoutube result;
		if (page != null) {
			result = youtube.searchYoutubeVideos(page);
		} else {
			result = youtube.searchYoutubeVideos();
		}
		uiModel.addAttribute("youtube", result);
		return "youtube/index";
	}
}
