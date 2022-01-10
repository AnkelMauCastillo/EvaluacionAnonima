package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Curso;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * controlador para la gestion de cursos 
 * @author Andres Mendoza 
 *
 */
@Controller
@RequestMapping("/curso")
@Slf4j
public class CursoController {
	
  @Autowired
  private CursoService cursoService;
  
  /**
   * metodo para guardar el curso 
   * @param model
   * @param curso
   * @return
   */
  @GetMapping("/guardarcurso")
  public String guardarCurso(Model model, Curso curso) {
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a CursoController.guardarCurso");
      log.debug("Curso {}", curso);
    }
    Curso cursoGuardado = null;

    try {
      cursoGuardado = cursoService.guardarCurso(curso);
      if (cursoGuardado != null && cursoGuardado.getId() != null) {
        model.addAttribute("mensajeExitoso", "Curso guardado exitosamente!");
      }
    } catch (AplicacionExcepcion e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());
    }
    return "admin-catalogos2::#modalMensaje";
  }
  
  /**
   * busca los cursos resgistrados 
   * @param model
   * @return
   */
  @GetMapping("/buscar")
  public String buscar(Model model) {
	if (log.isDebugEnabled())
      log.debug("> Entrando a buscarCursos <");
    List<Curso> cursos = cursoService.obtenerCursos();
    model.addAttribute("cursos", cursos);
    return "admin-catalogos2";
  }
  
  /**
   * elimina algun curso disponible 
   * @param model
   * @param curso
   * @return
   * @throws AplicacionExcepcion
   */
  @GetMapping("/eliminarcurso")
  public String eliminarCurso(Model model, Curso curso) throws AplicacionExcepcion {
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a Cursocontroller.eliminarCurso");
      log.debug("Curso {}", curso);
    }
    try {
      Curso curso2 = cursoService.buscarCurso(curso.getId());	
      if (curso2 != null) { 
        cursoService.eliminarCurso(curso);
        model.addAttribute("mensajeExitoso", "Exito: Curso Borrado!");
      }
    } catch (AplicacionExcepcion  e) {
      log.error(e.getMessage());
      model.addAttribute("mensajeError", e.getMessage());
    } catch (Exception e) {
      model.addAttribute("mensajeError", "Error: Curso no encontrado");
    }
    return "admin-catalogos2::#modalMensaje";
  }
  
  /**
   * busca los cursos para el select del registro de usuarios 
   * @param model
   * @return
   */
  @GetMapping("/buscars")
  public String buscars(Model model) {
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscarcursos-select<");
    List<Curso> cursos = cursoService.obtenerCursos();
    model.addAttribute("cursos", cursos);
    return "registro";
  }
  
  /**
   * busca los cursos para el select del registro de actividades  
   * @param model
   * @return
   */
  @GetMapping("/buscarCA")
  public String buscarcA(Model model) {
    if (log.isDebugEnabled())
      log.debug("> Entrando a buscarcursos-select<");
    List<Curso> cursos = cursoService.obtenerCursos();
    model.addAttribute("cursos", cursos);
    return "admin-catalogos::#modalActividad";
  }
}
