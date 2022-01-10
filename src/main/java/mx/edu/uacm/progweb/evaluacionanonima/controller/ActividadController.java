package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 
 * @author HP
 *
 */
@Controller
@RequestMapping("/actividad")
@Slf4j
public class ActividadController {
	
  @Autowired
  private ActividadService actividadService;
	
  /**
   * 	
   * @param model
   * @param actividad
   * @return
   */
  @PostMapping("/guardar")
  public String guardarActividad(Model model, Actividad actividad) {
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a ActividadController.guardarActividad");
      log.debug("Actividad {}", actividad);
    }
    Actividad actividadGuardada = null;

    try {
      actividadGuardada = actividadService.agregarActividad(actividad);
      if (actividadGuardada != null && actividadGuardada.getId() != null)
        model.addAttribute("mensajeExitoso", "Acitivdad guardada exitosamente!");
    } catch (AplicacionExcepcion e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());
    }

    return "admin-catalogos::#modalMensaje";
  }
  
  /**
   * busca las actvidades para el catalogo del administrador
   * @param model
   * @return
   */
  @GetMapping("/buscar")
  public String buscar(Model model) {
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscar actividades <");
    List<Actividad> actividades = actividadService.obtenerActividades();
    model.addAttribute("actividades", actividades);
    return "admin-catalogos";
  }
  
  /**
   * elimina una activdad
   * @param model
   * @param actividad
   * @return
 * @throws Exception 
   */
  @GetMapping("/eliminaractividad")
  public String eliminarActividad(Model model, Actividad actividad) throws AplicacionExcepcion {
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a Actividadcontroller.eliminarActividad");
      log.debug("actividad {}",  actividad);
    }
    try {
      Actividad actividad2 = actividadService.buscarActividad(actividad.getId());
      if (actividad2 != null) {
        actividadService.borrarActividad(actividad);
        model.addAttribute("mensajeExitoso", "Exito: Actividad Borrada");
      } 
    } catch (AplicacionExcepcion  e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());
    } catch (Exception e) {
      model.addAttribute("mensajeError", "Error: Actividad no encontrada");
    }
    return "admin-catalogos::#modalMensaje";
  }
  
  /**
   * busca las actividades para las respuestas a la actividad
   * @param model
   * @return
   */
  @GetMapping("/buscars")
  public String buscars(Model model) {
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscar actividades <");
    List<Actividad> actividades = actividadService.obtenerActividades();
    model.addAttribute("actividades", actividades);
    return "home-respuesta";
  }
  
  @GetMapping("/buscara")
  public void buscarActividad(Model model, Actividad actividad) throws AplicacionExcepcion {
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscarActividad <");
    
    try {
        Actividad actividad2 = actividadService.buscarActividad(actividad.getId());
        if (actividad2 != null) {
          
          model.addAttribute("actividad", actividad);
        } 
      } catch (AplicacionExcepcion  e) {
        log.error(e.getMessage());
        model.addAttribute("mensajeError", e.getMessage());
      } catch (Exception e) {
        model.addAttribute("mensajeError", "Error: Actividad no encontrada");
      }
    
  }
  
  /**
   * edita la actividad 
   * @param id
   * @param attributes
   * @param model
   * @return
 * @throws Exception 
   */
  @GetMapping("/editarActividad/{id}")
  public String editaActividad(@PathVariable("id") Long id, Model model) throws Exception {
	  if (log.isDebugEnabled()) {
	      log.debug("> Entrando a Actividadcontroller.editarActividadBusca");
	    
	    }
    Actividad actividadEncontrada = actividadService.buscarActividad(id);
	model.addAttribute("actividadEncontrada", actividadEncontrada); 	
	return "edita-actividad";	
  }
  
  @PostMapping("/editarA")
  public String edita(@Validated @ModelAttribute("actividad")  Actividad actividad, Model model ) {
	  if (log.isDebugEnabled()) {
	      log.debug("> Entrando a Actividadcontroller.editarA");
	      log.debug("actividad {}",  actividad);
	    }
	  try {
		  
		  actividadService.modificarActividad(actividad);
		  model.addAttribute("actividad", actividad);
		  model.addAttribute("mensajeExitoso", "Actividad modificada exitosamente!");
		  return "edita-actividad::#modalMensaje";
		 
	  }catch (Exception e) {
		  log.error(e.getMessage()); 
	      model.addAttribute("mensajeError", e.getMessage());
		 
	  }
	  return "redirect:/admin-cat";
  }
  
	
}
