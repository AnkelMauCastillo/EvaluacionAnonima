package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.EvaluacionAnonimawebApplication;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {EvaluacionAnonimawebApplication.class})
@Slf4j
public class UsuarioTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;   
	
	
	@Test
	public void debeGuardarUsuario() {
		log.debug("Entrando a debeGuardarUsuario");
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Karla");
		usuario.setApellidoPat("Jimenez");
		usuario.setApellidoMat("Lara");
		usuario.setMatricula("1121");
		usuario.setCorreo("karla@uacm.edu.mx");
        usuario.setContrasenia( passwordEncoder.encode("123") );
		
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		
		assertNotNull(usuarioGuardado);
		assertNotNull(usuarioGuardado.getId());
	}

}
