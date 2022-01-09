package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Respuesta;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Andres Mendoza 
 *
 */
@Controller
@RequestMapping("/respuesta")
@Slf4j
public class RespuestaController {
	
  @Autowired
  private RespuestaService respuestaService;
	
  /**
   * guarda la respuesta a la actividad 
   * @param model
   * @param respuesta
   * @return
   */
  @PostMapping("/guardar")
  public String guardarRespuesta(Model model, Respuesta respuesta) {
    
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a RespuestaController.guardarRespuesta");
      log.debug("Respuesta {}", respuesta);
    }
    Respuesta respuestaGuardada = null;
    try {
      respuestaGuardada = respuestaService.agregarRespuesta(respuesta);
      if (respuestaGuardada != null && respuestaGuardada.getId() != null) {
        model.addAttribute("mensajeExitoso", "Respuesta guardada exitosamente");
      }
    } catch (AplicacionExcepcion e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());
    }
    return "home-respuesta::#modalMensaje";
  }
  
  /**
   * busca las resuestas a las actividades 
   * @param model
   * @return
   */
  @GetMapping("/buscar")
  public String buscar(Model model) {
    
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscar respuestas <");
    List<Respuesta> respuestas = respuestaService.obtenerRespuestas();
    model.addAttribute("respuestas", respuestas);
    return "home-resp";
  }
	
  /**
   * 
   * @param model
   * @param respuesta
   * @return
   * @throws AplicacionExcepcion
   */
  @GetMapping("/eliminarespuesta")
  public String eliminarRespuesta(Model model, Respuesta respuesta) throws AplicacionExcepcion {
   
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a Respuestacontroller.eliminarRespuesta");
      log.debug("Respuesta {}", respuesta);
    }
    try {
      respuestaService.BorrarRespuesta(respuesta);
      model.addAttribute("mensajeExitoso", "Respuesta Borrada!");
    } catch (AplicacionExcepcion  e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());

    }
    return "home-respuesta::#modalMensaje";
  }

}
