package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.repository.ActividadRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class ActividadTest {
	
	
	@Autowired
	private ActividadRepository actividadRepository;
	
	@Test
	@Disabled
	public void debeGuardarActividad() {
		log.debug("> Entrando a debeGuardarActividad <");
		Actividad p = new Actividad();
		p.setDescripcion("");
		p.setObjetivos("");
		p.setPuntaje(Double.valueOf("20"));
		p.setPuntosEvaluar("");
		Actividad actividaGuardada = actividadRepository.save(p);
		assertNotNull(actividaGuardada);
	}
	
	

}
