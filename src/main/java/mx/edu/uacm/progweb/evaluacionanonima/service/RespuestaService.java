package mx.edu.uacm.progweb.evaluacionanonima.service;

import java.util.List;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Respuesta;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;

public interface RespuestaService {
	
	/**
	 * 
	 * @return
	 */
	List<Respuesta> obtenerRespuestas();
	
	/**
	 * 
	 * @param respuesta
	 * @return
	 * @throws AplicacionExcepcion
	 */
	Respuesta agregarRespuesta(Respuesta respuesta) throws AplicacionExcepcion;
	
	
	void BorrarRespuesta(Respuesta respuesta) throws AplicacionExcepcion;

}
