package Pacote.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import Pacote.dao.LoginDAO;
import Pacote.model.Credenciais;
import Pacote.model.Usuario;
import Pacote.dao.CatalogoDAO;
import Pacote.model.Erro;
@Controller
public class LoginController{

	private boolean Erro = false;
	
    @RequestMapping("loginForm")
    public String loginForm(Model modelo) {
    	if(Erro) {
            Erro E = new Erro();
            E.setErrormessage("Login e senha nao conferem");
            modelo.addAttribute("erro",E);
    	}
        return "login/formulario-login";
    }
    
    @RequestMapping("efetuaLogin")
    public String efetuaLogin(Credenciais credenciais, HttpSession session) throws ClassNotFoundException {
        Erro = false;
    	if(new LoginDAO().existeUsuario(credenciais)) {
        	Usuario usuario = new Usuario();
        	session.setAttribute("usuarioLogado", usuario);
            return "redirect:listaPrincipal";
        }
        Erro = true;
        return "redirect:loginForm";
    }
    
}