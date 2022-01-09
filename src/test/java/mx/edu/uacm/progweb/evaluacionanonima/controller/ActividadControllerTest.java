package mx.edu.uacm.progweb.evaluacionanonima.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@AutoConfigureMockMvc
@Slf4j
public class ActividadControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test 
	public void debeBorrarActividad() throws Exception {
		log.debug("entrando a debeBorrarActividad()");

		mockMvc.perform(get("/actividad/eliminaractividad")
				.param("id", "5")).andExpect(redirectedUrl("/admin-cat"));
	}

}
