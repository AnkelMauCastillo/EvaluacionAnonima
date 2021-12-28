package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Curso;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.CursoService;

@Controller
@RequestMapping("/curso")
@Slf4j
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	
	@GetMapping("/guardarcurso")
	public String guardarCurso(Model model, Curso curso) {
		if(log.isDebugEnabled()) {
			log.debug("> Entrando a CursoController.guardarCurso");
			log.debug("Curso {}", curso);
		}
		
		Curso cursoGuardado = null;
		
		try {
			cursoGuardado = cursoService.guardarCurso(curso);
			if(cursoGuardado != null && cursoGuardado.getId() != null)
				model.addAttribute("mensajeExitoso", "Registro guardado exitosamente");
		} catch (AplicacionExcepcion e) {
			log.error(e.getMessage());
			model.addAttribute("mensajeError", e.getMessage());
		}
		
		return "admin-catalogos2::#modalMensaje";
	}
	
	@GetMapping("/buscar")
	public String buscar(Model model) {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a buscarcursos <");
		
		List<Curso> cursos = cursoService.obtenerCursos();
		
		model.addAttribute("cursos", cursos);
		
		
		return "admin-catalogos2";
	}
	
	
	@GetMapping("/eliminarcurso")
	public String eliminarCurso(Model model, Curso curso) throws AplicacionExcepcion {
		if (log.isDebugEnabled()) {
			log.debug("> Entrando a Cursocontroller.eliminarCurso");
			log.debug("Curso {}",curso );
		}
	    try {
	    	
	    	cursoService.eliminarCurso(curso);
	    	model.addAttribute("mensajeExitoso", "Registro Borrado");
	    } catch (AplicacionExcepcion  e) {
	    	log.error(e.getMessage());
	    	model.addAttribute("mensajeError" , e.getMessage());
	    	
	    }
		
		
		return "redirect:/admin-catalogos2";
	}

}
