package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.repository.ActividadRepository;
import mx.edu.uacm.progweb.evaluacionanonima.repository.RespuestaRepository;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;

/**
 * prueba unitaria de respuesta
 * @author Andres Mendoza 
 *
 */
@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class RespuestaTest {
  @Autowired
  private RespuestaRepository respuestaRepo;
  @Autowired
  private ActividadService actividadRepo;
  private Actividad actividad; 
  
  @Test
  public void debeGuardarRespuesta() throws Exception {
    
    log.debug(" entrando a debeGuardarRespuesta ");
    actividad = actividadRepo.buscarActividad((long) 3);
    log.debug("entrando a respuesta");
    Respuesta respuesta = new Respuesta();
    respuesta.setGithub("https://github.com/AndresMcAs/EvaluacionAnonima");
    respuesta.setDrive("https://drive.google.com/drive/u/0/folders/1fVl_47Q4lGFm-UnjU4aYLMJeICHTpkKQ");
    respuesta.setActividad(actividad);
    Respuesta respuestaGuardada = respuestaRepo.save(respuesta);
    assertNotNull(respuestaGuardada);
  }
  

}
