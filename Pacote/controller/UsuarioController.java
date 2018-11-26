package Pacote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.dao.UsuarioDAO;
import Pacote.model.Usuario;

@Controller
public class UsuarioController {
	
	@RequestMapping("mostraUsuario")
	public String MostrarUsuario(Model modelo) throws ClassNotFoundException {
		UsuarioDAO dao = new UsuarioDAO();
		modelo.addAttribute("usuario",dao.PegarUsuario());
		return "usuario/mostra";
	}
	
	@RequestMapping("alteraUsuario")
	public String AlterarUsuario(Usuario usuario) throws ClassNotFoundException {
		UsuarioDAO dao = new UsuarioDAO();
		dao.Alterar(usuario);
		return "principal/principal";
	}
	
}
