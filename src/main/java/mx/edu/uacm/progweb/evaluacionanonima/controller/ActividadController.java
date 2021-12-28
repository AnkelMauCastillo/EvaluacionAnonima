package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;

@Controller
@RequestMapping("/actividad")
@Slf4j
public class ActividadController {
	
	@Autowired
	private ActividadService actividadService;
	
	
	@PostMapping("/guardar")
	public String guardarActividad(Model model,Actividad actividad) {
		if(log.isDebugEnabled()) {
			log.debug("> Entrando a ActividadController.guardarActividad");
			log.debug("Actividad {}", actividad);
		}
		
		Actividad actividadGuardada = null;
		
		try {
			actividadGuardada = actividadService.agregarActividad(actividad);
			if(actividadGuardada != null && actividadGuardada.getId() != null)
				model.addAttribute("mensajeExitoso", "Registro guardado exitosamente");
		} catch (AplicacionExcepcion e) {
			log.error(e.getMessage());
			model.addAttribute("mensajeError", e.getMessage());
		}
		
		return "admin-catalogos::#modalMensaje";
	}
	
	@GetMapping("/buscar")
	public String buscar(Model model) {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a buscar actividades <");
		
		List<Actividad> actividades = actividadService.obtenerActividades();
		
		model.addAttribute("actividades", actividades);
		
		
		return "admin-catalogos";
	}
	

	
	@GetMapping("/eliminaractividad")
	public String eliminarActividad(Model model, Actividad actividad) throws AplicacionExcepcion {
		if (log.isDebugEnabled()) {
			log.debug("> Entrando a Actvidadcontroller.eliminarActividad");
			log.debug("Actividad {}",actividad );
		}
	    try {
	    	
	    	actividadService.BorrarActividad(actividad);
	    	model.addAttribute("mensajeExitoso", "Registro Borrado");
	    } catch (AplicacionExcepcion  e) {
	    	log.error(e.getMessage());
	    	model.addAttribute("mensajeError" , e.getMessage());
	    	
	    }
		
		
		return "redirect:/admin-catalogos";
	}

}
