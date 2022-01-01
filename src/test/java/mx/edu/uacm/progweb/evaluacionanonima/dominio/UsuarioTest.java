package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.repository.CursoRepository;
import mx.edu.uacm.progweb.evaluacionanonima.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class UsuarioTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CursoRepository curso;
    @Autowired
    private PasswordEncoder passwordEncoder;   
	
	
	@Test
	
	public void debeGuardarUsuario() {
		log.debug("Entrando a debeGuardarUsuario");
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellidoPat("Mendoza");
		usuario.setApellidoMat("Contreras");
		usuario.setMatricula("16-003-0196");
		usuario.setCorreo("andres@uacm.edu.mx");
        usuario.setContrasenia( passwordEncoder.encode("1234") );
		
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		
		assertNotNull(usuarioGuardado);
		assertNotNull(usuarioGuardado.getId());
	}
    
	@Test
	@Disabled
	public void debeusuarioCurso() {
		
		Curso c = new Curso();
		c.setNombre("progweb");
		
		curso.save(c);
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Osiris");
		usuario.setApellidoPat("flores");
		usuario.setApellidoMat("Bautista");
		usuario.setMatricula("1121");
		usuario.setCorreo("Osiris@estudiante.uacm.edu.mx");
        usuario.setContrasenia( passwordEncoder.encode("123") );
		usuario.setCurso(c);
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		assertNotNull(usuarioGuardado);
		
	}
}
