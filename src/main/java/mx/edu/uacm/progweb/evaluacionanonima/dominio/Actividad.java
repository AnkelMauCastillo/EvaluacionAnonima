package mx.edu.uacm.progweb.evaluacionanonima.dominio;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Actividad {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String descripcion;
  private String objetivos;
  private String puntosEvaluar;
  private double puntaje;  

  @ManyToOne
  @JoinColumn (name  =  "curso_id ", foreignKey = @ForeignKey(name = "curso_ID_FK"))
  private Curso curso;
  
  public Actividad() {
	  
  }

}
