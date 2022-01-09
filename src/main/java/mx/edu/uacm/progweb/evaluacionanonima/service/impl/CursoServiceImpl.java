package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Curso;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.repository.CursoRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;



/**
 * 
 * @author Andres Mendoza 
 *
 */
@Service
@Slf4j
public class CursoServiceImpl implements CursoService {
	
	
  @Autowired
  private CursoRepository cursoRepository;
  private Curso curso;
  
  @Override
  public List<Curso> obtenerCursos() {
    return (List<Curso>) cursoRepository.findAll();
  }

  /**
   * guarda el curso en la base de datos 	
   */
  public Curso guardarCurso(Curso curso) throws AplicacionExcepcion {
    if (log.isDebugEnabled()) {
      log.debug("> Entrando a agregarActividad");
    }
    Curso cursoGuardado = null;
    try {
      cursoGuardado = cursoRepository.save(curso);
    } catch (DataAccessException e) {
      log.error(e.getMessage());
      throw new AplicacionExcepcion("Hubo un error al guardar el registro");
    }
    return cursoGuardado;
  }

  @Override
  public void eliminarCurso(Curso curso) throws AplicacionExcepcion {
    if (log.isDebugEnabled()) {
      log.debug("> entrando a eliminarCurso ");
    }
    try { 
      cursoRepository.delete(curso);
    } catch (DataAccessException e) {
      log.error(e.getMessage());
      throw new AplicacionExcepcion("Error al eliminar el registro");
    }
  }
  
  @Override
  public Curso buscarCurso(Long id) throws AplicacionExcepcion  {
    
    try {
      curso = cursoRepository.findById(id).get(); 
    } catch (DataAccessException e) {
      throw new AplicacionExcepcion("Error");
    }
    return curso;
 
  }

}
