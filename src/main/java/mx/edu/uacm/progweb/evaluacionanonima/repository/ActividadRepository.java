package mx.edu.uacm.progweb.evaluacionanonima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
	
	

	

}
