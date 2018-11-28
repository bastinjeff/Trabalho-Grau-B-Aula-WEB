package Pacote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import Pacote.dao.CatalogoDAO;
import Pacote.model.Catalogo;
@Controller
public class PrincipalController{
	
	@RequestMapping("listaPrincipal")
	public String listaPrincipal(Model modelo) throws ClassNotFoundException {    	
		return "principal/principal";
	}
	
	@RequestMapping("/")
	public String listaPrincipalRaiz() {
		return "principal/principal";
	}
	
	@RequestMapping("mostrahome")
	public String Mostrarhome() {
		return "principal/Home";
	}
	
}