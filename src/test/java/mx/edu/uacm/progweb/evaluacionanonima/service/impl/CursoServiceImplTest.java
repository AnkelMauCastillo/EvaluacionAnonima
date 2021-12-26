package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.repository.CursoRepository;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class CursoServiceImplTest {
	
  @Autowired
  private CursoRepository cursoRepo;
  

  @Test
  public void debeObtenerCursos() {
    log.debug("entrando a debeObtenercursos ()");
    cursoRepo.findAll();
		
  }

}
