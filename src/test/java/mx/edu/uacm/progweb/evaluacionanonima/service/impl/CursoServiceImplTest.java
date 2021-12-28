package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Curso;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.repository.CursoRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.CursoService;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class CursoServiceImplTest {
	
  @Autowired
  private CursoService cursoService;
  @Autowired
  private CursoRepository cursoRepo;

 
  @Test
  public void debeBorrarCurso() throws AplicacionExcepcion {
	  log.debug("Entrando a debeBorrarCurso ()");
	 Curso c = new Curso();
	 c.setNombre("prueba");
	 cursoService.guardarCurso(c);
	 
	  cursoService.eliminarCurso(c);
  }

}
