package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;

@Controller
@RequestMapping("/actividad")
@Slf4j
public class ActividadController {
	
	@Autowired
	private ActividadService acitividadService;
	
	
	@PostMapping("/guardar")
	public String guardarActividad(Model model,Actividad actividad) {
		if(log.isDebugEnabled()) {
			log.debug("> Entrando a ActividadController.guardarActividad");
			log.debug("Actividad {}", actividad);
		}
		
		Actividad actividadGuardada = null;
		
		try {
			actividadGuardada = acitividadService.agregarActividad(actividad);
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
			log.debug("> Entrando a buscar <");
		
		List<Actividad> actividades = acitividadService.obtenerActividades();
		
		model.addAttribute("actividades", actividades);
		
		
		return "admin-catalogos";
	}
	
	@GetMapping("/buscarPaginado")
	public String buscarPaginado(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		if(log.isDebugEnabled()) {
			log.debug("> Entrando a ActividadController.buscarPaginado");
			
		}
		
		//numero pagina
		final int paginaActual = page.orElse(1);
		//tamanio de registros 
		final int tamanioPagina = size.orElse(10);
		
		Page<Actividad> actividadesPaginadas = acitividadService.obtenerActividadesPaginadas(PageRequest.of(paginaActual-1, tamanioPagina));
		
		
		model.addAttribute("actividades", actividadesPaginadas);
		
		int totalPaginas = actividadesPaginadas.getTotalPages();
		
		if(totalPaginas > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPaginas).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		return "admin-catalogos-paginacion::#resultado";
		
	}

}
