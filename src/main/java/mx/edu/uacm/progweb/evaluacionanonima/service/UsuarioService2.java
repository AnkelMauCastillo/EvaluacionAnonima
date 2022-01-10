package mx.edu.uacm.progweb.evaluacionanonima.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.repository.UsuarioRepository;

@Service
public class UsuarioService2 {
  
	@Autowired
	private UsuarioRepository repository;
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Usuario> listAll (){
		return (List<Usuario>) repository.findAll();
	}
	
	

	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		encodePassword(usuario);
		repository.save(usuario);
	}
	
	private void encodePassword(Usuario usuario) {
		
		String encodePassword = passwordEncoder.encode(usuario.getContrasenia());
		usuario.setContrasenia(encodePassword);
	}
	
	public boolean isEmailUnique(String email) {
		Usuario usuarioEmail = repository.getUsuariorBycorreo(email);
		return usuarioEmail == null;
	}



	public Usuario get(Long id) throws UsernNotFoundException {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException ex) {
			// TODO: handle exception
			throw new UsernNotFoundException("no se pudo encontrar ningún usuario con el ID" + id);
		}
		
	}
	
	public void delete(Long id) throws UsernNotFoundException {
		Long countById = repository.countById(id);
		
		if (countById == null || countById == 0) {
			
			throw new UsernNotFoundException("no se pudo encontrar ningún usuario con el ID" + id);
						
		}
		
		repository.deleteById(id);
	}
	
}
