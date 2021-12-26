package mx.edu.uacm.progweb.evaluacionanonima.service.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.service.ActividadService;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class ActividadServiceImplTest {

	@Autowired
	private ActividadService actividadService;
	
	@Test
	public void debeObtenerActividades() {
		
		log.debug("Entrando a debeObtenerActividades");
		
		List<Actividad> lista = actividadService.obtenerActividades();
		
		for (Actividad actividad : lista) {
			System.out.println(actividad.toString());
		}
		
		
	}
}
