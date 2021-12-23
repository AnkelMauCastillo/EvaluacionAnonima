package mx.edu.uacm.progweb.evaluacionanonima.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;

public interface ActividadRepository extends CrudRepository<Actividad, Long> {
	
	Page<Actividad> findAll(Pageable pageable);

}
