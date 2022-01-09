package mx.edu.uacm.progweb.evaluacionanonima.service;

import java.util.List;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Actividad;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;

public interface UsuarioService {
  
  /**
  * Metodo para obtener al usuario 
  * @param correo
  * @param contrasenia
  * @return Objeto usuario
  */
  Usuario obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia);
	
  Usuario registrarUsuario(Usuario usuario) throws AplicacionExcepcion;

  List<Usuario> obtenerUsuarios();
}
