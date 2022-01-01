package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Respuesta;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.repository.RespuestaRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.RespuestaService;

@Service
@Slf4j
public class RespuestaServiceImpl implements RespuestaService {
	
	@Autowired
	private RespuestaRepository respuestaRepository;

	@Override
	public List<Respuesta> obtenerRespuestas() {
		
		return (List<Respuesta>) respuestaRepository.findAll();
	}

	
	public Respuesta agregarRespuesta(Respuesta respuesta) throws AplicacionExcepcion {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a agregarActividad <");
		
		Respuesta RespuestaGuardada = null;
		
		try {
			
			RespuestaGuardada = respuestaRepository.save(respuesta);
		
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			
			throw new AplicacionExcepcion("Hubo un error al guardar el registro");
		}
		
		
		return RespuestaGuardada;
	}



	@Override
	public void BorrarRespuesta(Respuesta respuesta) throws AplicacionExcepcion {
		if (log.isDebugEnabled())
			log.debug("> entrando a BorrarRespuesta ");
		
		try { 
			respuestaRepository.delete(respuesta);
			
		}catch (DataAccessException e) {
			log.error(e.getMessage());
			throw new AplicacionExcepcion("Error al eliminar el registro");
		}
		
		
	}


	

}
