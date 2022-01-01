package mx.edu.uacm.progweb.evaluacionanonima.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.progweb.evaluacionanonima.error.AplicacionExcepcion;
import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
	
  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private HttpSession httpSession;
  private String registrar;
  private String paginaIni;
  private ServletContext servletContext;
	
  public UsuarioController(ServletContext servletContext) {
		this.servletContext = servletContext;
  }
	
  @PostMapping("/login")
  public String iniciarSesion(@RequestParam("correo") String correo,
		                  @RequestParam("contrasenia") String password,
			               Model model) {
	if(log.isDebugEnabled())
	   log.debug("> Entrando al metodo iniciarSesion <");
		
	Usuario usuario = usuarioService.obtenerUsuarioPorCorreoYContrasenia(correo, password);
		
	if(usuario != null) {
	   httpSession.setAttribute("usuarioLogueado", usuario);
	 if (usuario.getRol().equals("USER")) {
		   
		   paginaIni ="redirect:/home2";
	 } else {
		paginaIni = "redirect:/home";
	 }
    } else {
	    servletContext.setAttribute("errorMensaje", "Usuario/Contrasenia no validos");
	    paginaIni = "redirect:/login";
	   
    }   	
	
		return paginaIni;
		
  }

  @GetMapping("/logout")
  public String logout() {
    httpSession.removeAttribute("usuarioLogueado");
		return "redirect:/";
  }
	

  @GetMapping("/initlogin")
  public String iniciarLogin() {
    servletContext.removeAttribute("errorMensaje");
    return "redirect:/login";
  }
  
  @GetMapping("/registro")
  public String registrarUsuario(Model model, Usuario usuario) {
    if(log.isDebugEnabled()) {
      log.debug(">Entrando a usuarioController.registrarusuario");
      log.debug("Usuario {}", usuario)	;
    }
    
    if (usuario.getNombre() != null && usuario.getCorreo() != null) {
    	
      try {
		Usuario usuarioGuardado;
		usuarioGuardado = usuarioService.registrarUsuario(usuario);
		
		if (usuarioGuardado != null && usuarioGuardado.getId() != null)
			model.addAttribute("mensajeExitoso","Registro guardado exitosamente");
      } catch (AplicacionExcepcion e) {
	    log.error(e.getMessage());
	    model.addAttribute("mensajeError", e.getMessage());
	  }
      
      registrar = "registro::#modalMensaje";
     } else {
			 registrar = "redirect:/registro";
     }
		return registrar;
	  }

}
