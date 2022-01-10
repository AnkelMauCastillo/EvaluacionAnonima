package mx.edu.uacm.progweb.evaluacionanonima;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**  
 * Registro de las vistas de los controladores
 * @author Andres Mendoza 
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
  /**
   * registro de las vistas 
   */
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/agregar").setViewName("agregarActividad");
    registry.addViewController("/eliminaractividad").setViewName("borrarActividad");
    registry.addViewController("/eliminarcurso").setViewName("borrarCurso");
    registry.addViewController("/registro").setViewName("registro");
    registry.addViewController("/guardarcurso").setViewName("guardarcurso");
    registry.addViewController("/home2").setViewName("home2");
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/admin-cat").setViewName("admin-catalogos");
    registry.addViewController("/admin-cat2").setViewName("admin-catalogos2");
    registry.addViewController("/home-resp").setViewName("home-respuesta");
    registry.addViewController("/home-revision").setViewName("home-revision");
    registry.addViewController("/edita-actividad").setViewName("edita-actividad");
  }

}
