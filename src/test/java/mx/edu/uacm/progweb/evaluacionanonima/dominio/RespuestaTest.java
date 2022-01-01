package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.repository.ActividadRepository;
import mx.edu.uacm.progweb.evaluacionanonima.repository.RespuestaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class RespuestaTest {
	
  @Autowired
  private RespuestaRepository respuestaRepo;
  @Autowired
  private ActividadRepository actividadRepo;
  
  @Test
  public void debeGuardarRespuesta() {
	  log.debug(" entrando a debeGuardarRespuesta ");
	  Actividad p = new Actividad();
		p.setDescripcion("prueba actividad-respuesta");
		p.setObjetivos("res");
		p.setPuntaje(Double.valueOf("20"));
		p.setPuntosEvaluar("r");
		Actividad a = actividadRepo.save(p);
		assertNotNull(a);
		
		log.debug("entrando a respuesta");
	  Respuesta r = new Respuesta();
	  r.setGithub("https://github.com/AndresMcAs/EvaluacionAnonima");
      r.setDrive("https://drive.google.com/drive/u/0/folders/1fVl_47Q4lGFm-UnjU4aYLMJeICHTpkKQ");
      r.setActividad(p);
      Respuesta Guardada = respuestaRepo.save(r);
      assertNotNull(Guardada);
  }
  

}
