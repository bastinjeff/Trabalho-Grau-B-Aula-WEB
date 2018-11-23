package Pacote.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.dao.LoginDAO;
import Pacote.model.Credenciais;
import Pacote.model.Usuario;
import Pacote.dao.CatalogoDAO;
@Controller
public class LoginController{

    @RequestMapping("loginForm")
    public String loginForm() {
        return "login/formulario-login";
    }
    
    @RequestMapping("efetuaLogin")
    public String efetuaLogin(Credenciais credenciais, HttpSession session) throws ClassNotFoundException {
        if(new LoginDAO().existeUsuario(credenciais)) {
        	Usuario usuario = new Usuario();
        	CatalogoDAO Catalogo = new CatalogoDAO();
        	Catalogo.ResgatarCatalogo();
        	session.setAttribute("usuarioLogado", usuario);
            return "redirect:listaPrincipal";
        }
        return "redirect:loginForm";
    }
    
}