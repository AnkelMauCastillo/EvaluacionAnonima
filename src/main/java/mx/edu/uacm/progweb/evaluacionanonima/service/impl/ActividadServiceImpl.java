package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.repository.ActividadRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@Slf4j
public class ActividadServiceImpl implements ActividadService {
	
  @Autowired
  private ActividadRepository actividadRepository;
  private Actividad actividad;
  
  @Override
  public List<Actividad> obtenerActividades() {
    return (List<Actividad>) actividadRepository.findAll();
  }
  
  /**
   * guarda una actividad a la base de datos 
   */
  public Actividad agregarActividad(Actividad actividad) throws AplicacionExcepcion {

    if(log.isDebugEnabled())
      log.debug("> Entrando a agregarActividad <");
    Actividad actividadGuardada = null;
    try {
      actividadGuardada = actividadRepository.save(actividad);
    } catch (DataAccessException e) {
      log.error(e.getMessage());
      throw new AplicacionExcepcion("Hubo un error al guardar el registro");
    }
    return actividadGuardada;
  }

  @Override
  public Page<Actividad> obtenerActividadesPaginadas(Pageable pageable) {
    return actividadRepository.findAll(pageable);
  }

  @Override
  public void borrarActividad(Actividad actividad) throws AplicacionExcepcion {
   
	if (log.isDebugEnabled())
      log.debug("> entrando a BorrarActividad ");
    try { 
      actividadRepository.delete(actividad);
    } catch (DataAccessException e) {
      log.error(e.getMessage());
      throw new AplicacionExcepcion("Error al eliminar el registro");
    }
  }

  
  /**
  *  busqueda por id 
  * @param id 
  * @throws Exception 
   */
  @Override
  public Actividad buscarActividad(Long id) throws AplicacionExcepcion  {
    
	try {	
      actividad = actividadRepository.findById(id).get(); 
    } catch (DataAccessException e) {
      throw new AplicacionExcepcion("Error");
    }
    return actividad;
  }
  
  /**
   *modifica la activdad 
   */
  @Override
  public Actividad modificarActividad(Actividad actividad) throws Exception {
	  
    Actividad actividadGuardar = buscarActividad(actividad.getId());
    modificar(actividad,actividadGuardar);
    return actividadRepository.save(actividadGuardar);
	
  }
  
  public void modificar(Actividad actividad1 , Actividad actividad2) {
	  actividad2.setDescripcion(actividad2.getDescripcion());
	  actividad2.setObjetivos(actividad1.getObjetivos());
	  actividad2.setPuntosEvaluar(actividad1.getPuntosEvaluar());
	  actividad2.setPuntaje(actividad2.getPuntaje());
  }
  
}
