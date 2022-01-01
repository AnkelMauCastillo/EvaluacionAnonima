package mx.edu.uacm.progweb.evaluacionanonima.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


import mx.edu.uacm.progweb.evaluacionanonima.dominio.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
	
	Page<Respuesta> findAll(Pageable pageable);

	void deleteById(String nombre);

}
