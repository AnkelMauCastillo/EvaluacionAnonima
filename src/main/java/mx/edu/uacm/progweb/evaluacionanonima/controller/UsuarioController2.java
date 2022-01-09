package mx.edu.uacm.progweb.evaluacionanonima.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import mx.edu.uacm.progweb.evaluacionanonima.dominio.Usuario;
import mx.edu.uacm.progweb.evaluacionanonima.service.UsernNotFoundException;

import mx.edu.uacm.progweb.evaluacionanonima.service.UsuarioService2;
@Controller
public class UsuarioController2 {
	
	@Autowired
	private UsuarioService2 service;
	
	
	
	@GetMapping("/usuarios")
	public String listAll(Model model) {
		List<Usuario> listaDeUsuarios = service.listAll();
		System.out.println(listaDeUsuarios);
		model.addAttribute("listaDeUsuarios", listaDeUsuarios);
		return "usuarios";
	}
	
	@GetMapping("/editar/{id}")
	public String editUser(@PathVariable(name= "id") Long id, RedirectAttributes attributes, Model model) {
		
		try {
			
			Usuario usuario = service.get(id); 
			model.addAttribute("usuario", usuario);
			model.addAttribute("tituloUsuario", "Editar Usuario (ID: " + id + ")");
			return "formulario_admin";
		} catch (UsernNotFoundException ex) {
			// TODO: handle exception
			attributes.addFlashAttribute("mensaje", ex.getMessage());	
			
			return "redirect:/usuarios";
			
		}	
		
		
		
	}
	
	@GetMapping("/usuarios/nuevo")
	public String newUser(Model model) {
		List<Usuario> listaDeRoles =  service.listAll();
		Usuario usuario = new Usuario();		
		model.addAttribute("usuario", usuario); 
		model.addAttribute("listaDeRoles", listaDeRoles);
		
		return "registro";
	}
	
	@PostMapping("/usuarios/save")
	public String saveUser(Usuario usuario, RedirectAttributes redirectAttributes) {
		System.out.println(usuario);
		service.save(usuario);
		
		redirectAttributes.addFlashAttribute("mensaje", "El usuario se ha guardado satisfactoriamente");	
		return "redirect:/usuarios";
	}

}
