package mx.edu.uacm.progweb.evaluacionanonima.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@AutoConfigureMockMvc
@Slf4j
public class ActividadControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void debeGuardarProducto() throws Exception {
		log.debug("Entrando a debeGuardarProducto");
		
		//binding
		mockMvc.perform(post("/producto/guardar")
				.param("clave", "55")
				.param("nombre", "correa uacm")
				.param("precio", "50.30")
				.param("cantidad", "100")).andExpect(redirectedUrl("/admin-cat"));
	}

}
