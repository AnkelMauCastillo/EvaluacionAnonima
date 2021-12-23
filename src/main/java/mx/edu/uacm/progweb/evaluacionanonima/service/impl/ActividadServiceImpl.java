package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.repository.ActividadRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;

@Service
@Slf4j
public class ActividadServiceImpl implements ActividadService {
	
	//inyeccion de dependencias
	@Autowired
	private ActividadRepository actividadRepository;

	@Override
	public List<Actividad> obtenerActividades() {
		
		return (List<Actividad>) actividadRepository.findAll();
	}

	
	public Actividad agregarActividad(Actividad actividad) throws AplicacionExcepcion {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a agregarActividad <");
		
		Actividad ActividadGuardada = null;
		
		try {
			
			ActividadGuardada = actividadRepository.save(actividad);
		
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			//Hardcode
			//TODO implementar localizacion mediante message resources
			throw new AplicacionExcepcion("Hubo un error al guardar el registro");
		}
		
		
		return ActividadGuardada;
	}


	@Override
	public Page<Actividad> obtenerActividadesPaginadas(Pageable pageable) {
		
		return actividadRepository.findAll(pageable);
	}


	

}
