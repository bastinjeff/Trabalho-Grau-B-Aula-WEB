package Pacote.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.format.annotation.DateTimeFormat;

import Pacote.dao.UsuarioDAO;
import Pacote.model.Usuario;
import Pacote.model.Credenciais;
import Pacote.dao.LoginDAO;
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
	
	@RequestMapping("mostraCriarusuario")
	public String MostrarCriarUsuario() {
		return "usuario/testecadastrousuario";
	}
	
	@RequestMapping("criaUsuario")
	public String CriarUsuario(Usuario usuario, Credenciais credenciais) throws ClassNotFoundException {
		credenciais.setLogin(usuario.getEmail());
		UsuarioDAO dao = new UsuarioDAO();
		LoginDAO logindao = new LoginDAO();
		if(!logindao.VerificarEmail(credenciais.getLogin())) {
			String CredencialID = logindao.CriarCredencial(credenciais);
			dao.CriarUsuario(usuario, CredencialID);
		}
		return "principal/principal";
	}
	
	@RequestMapping("deletaUsuario")
	public String DeletarUsuario(Usuario usuario) {
		return "principal/principal";
	}
	
}
