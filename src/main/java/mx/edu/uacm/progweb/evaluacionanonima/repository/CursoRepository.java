package mx.edu.uacm.progweb.evaluacionanonima.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
	
	Page<Curso> findAll(Pageable pageable);

	void deleteById(Curso curso);

	

}
