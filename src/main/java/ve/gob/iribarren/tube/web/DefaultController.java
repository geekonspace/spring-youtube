/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Williams Rivas
 * Created 18/02/2014 14:07:25
 *
 */
@RequestMapping("/")
@Controller
public class DefaultController {

    @RequestMapping
    public String index() {
        return "redirect:/inicio";
    }
}
