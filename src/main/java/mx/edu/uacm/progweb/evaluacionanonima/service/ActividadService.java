package mx.edu.uacm.progweb.evaluacionanonima.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;

public interface ActividadService {
	
	/**
	 * 
	 * @return
	 */
	List<Actividad> obtenerActividades();
	
	/**
	 * 
	 * @param actividad
	 * @return
	 * @throws AplicacionExcepcion
	 */
	Actividad agregarActividad(Actividad actividad) throws AplicacionExcepcion;
	/**
	 * Metodo para obtener actividades de manera paginada
	 * @param pageable
	 * @return
	 */
	Page<Actividad> obtenerActividadesPaginadas(Pageable pageable);
	
	void BorrarActividad(Actividad actividad) throws AplicacionExcepcion;

}
