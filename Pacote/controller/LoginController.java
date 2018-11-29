package Pacote.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import Pacote.dao.LoginDAO;
import Pacote.model.Credenciais;
import Pacote.model.Usuario;
import Pacote.dao.CatalogoDAO;
import Pacote.model.Erro;
import Pacote.model.Empresa;
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
    
    @RequestMapping(value="efetuaLogin",params = "usuario", method=RequestMethod.POST )
    public String efetualoginUsuario(Credenciais credenciais, HttpSession sessao) throws ClassNotFoundException {
    	System.out.println(("Usuario"));
    	if(efetuaLogin(credenciais,sessao,"Usuario")) {
            return "redirect:listaPrincipal";
    	}else return "redirect:loginForm";
    }
    
    @RequestMapping(value="efetuaLogin",params = "empresa", method=RequestMethod.POST )
    public String efetualoginEmpresa(Credenciais credenciais, HttpSession sessao) throws ClassNotFoundException {
    	System.out.println("Empresa");
    	if(efetuaLogin(credenciais,sessao,"Empresa")) {
            return "redirect:listaPrincipal"; 
    	}else return "redirect:loginForm";
    }
    
    
    //@RequestMapping("efetuaLogin")
    public boolean efetuaLogin(Credenciais credenciais, HttpSession session, String UsuarioOuEmpresa) throws ClassNotFoundException {
        Erro = false;
        String ID = "";
		if(UsuarioOuEmpresa.equals("Empresa")) {
	        ID = new LoginDAO().ExisteConta(credenciais,"Empresa");
		}else ID = new LoginDAO().ExisteConta(credenciais,"Usuario");
    	if(!ID.equals("")) {
    		if(UsuarioOuEmpresa.equals("Empresa")) {
    			Empresa empresa = new Empresa();
    			empresa.setId(ID);
    			session.setAttribute("empresaLogada", empresa);
    		}else {
            	Usuario usuario = new Usuario();
            	usuario.setid(ID);
            	session.setAttribute("usuarioLogado", usuario);
    		}
            return true;
        }
        Erro = true;
        return false;
    }
    
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
    }
    
}