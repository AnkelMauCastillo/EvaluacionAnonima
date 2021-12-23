package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String matricula;
	private String correo;
	private String contrasenia;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
}
