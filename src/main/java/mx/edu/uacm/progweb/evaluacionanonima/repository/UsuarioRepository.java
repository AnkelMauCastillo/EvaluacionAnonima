package mx.edu.uacm.progweb.evaluacionanonima.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByCorreoAndContrasenia(String correo, String contrasenia);

	Usuario findByCorreo(String correo);

	
}
