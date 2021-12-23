package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.repository.CursoRepository;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class CursoTest {
  @Autowired
  private CursoRepository cursoRepository;
  
  @Test
  public void debeGuardarCurso() {
	  
	  log.debug("Entrando a debeGuardarCurso");
	  Curso c = new Curso();
	  c.setNombre("Programacion web");
	  Curso cursoGuardado = cursoRepository.save(c);
	  assertNotNull(cursoGuardado);
  }
}
