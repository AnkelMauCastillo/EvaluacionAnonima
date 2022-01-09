package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;


/**
 * 
 * @author Andres Mendoza 
 *
 */
@Entity
@Data
public class Usuario  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellidoPat;
  private String apellidoMat;
  private String matricula;
  private String correo;
  private String contrasenia;
  private String rol;
 
  @ManyToOne
  @JoinColumn(name = "curso_id", foreignKey = @ForeignKey(name = "CURSO_ID_FK"))
  private Curso curso;

  public Usuario() {

  }

  public String getUsername() {
    return nombre;
  }
  
}
