package Pacote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import Pacote.dao.CatalogoDAO;
import Pacote.model.Catalogo;
import Pacote.model.Catalogo;
import Pacote.dao.CatalogoDAO;
@Controller
public class PrincipalController{
	
	@RequestMapping("listaPrincipal")
	public String listaPrincipal(Model modelo) throws ClassNotFoundException {
    	CatalogoDAO catalogo = new CatalogoDAO();
    	Catalogo cat = catalogo.ResgatarCatalogo();
    	modelo.addAttribute("viagens",cat.getViagens());
		return "principal/principal";
	}
	
	@RequestMapping("/")
	public String listaPrincipalRaiz() {
		return "principal/principal";
	}
	
}