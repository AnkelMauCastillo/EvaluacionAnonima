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
public class UsuarioControllerTest {
	@Autowired
	private MockMvc mo;
	
	@Test
	public void debeLogin() throws Exception{
		log.debug("entrando a logeo");
     mo.perform(post("/usuario/login")
    		    .param("correo", "sergio@uacm.edu.mx")
    		    .param("contrasenia", "1234")
    		    ).andExpect(redirectedUrl("/home"));
         
     	
	}
}
