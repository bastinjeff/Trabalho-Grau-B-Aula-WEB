package Pacote.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.dao.CatalogoDAO;
import Pacote.model.Catalogo;
import Pacote.model.Rota;
import Pacote.dao.EnderecoDAO;
@Controller
public class CatalogoController {
	
		@RequestMapping("mostraCatalogofiltrado")
		public String MostrarCatalogoFiltrado(Model modelo, Rota rota) throws ClassNotFoundException {
			CatalogoDAO catalogo = new CatalogoDAO();
			System.out.println(rota.getCidadeChegada());
			System.out.println(rota.getCidadeSaida());
			rota.setCidadechegada(ArrumarCidade(rota.getCidadeChegada()));
			rota.setCidadesaida(ArrumarCidade(rota.getCidadeSaida()));
	    	Catalogo cat = catalogo.ResgatarCatalogo(rota.getCidadeChegada(),rota.getCidadeSaida());
	    	modelo.addAttribute("viagens",cat.getViagens());
			return "catalogo/mostracatalogo";
		}
		
		private String ArrumarCidade(String Cidade) {
			return Cidade.substring(0,Cidade.indexOf('/'));
		}
		
		@RequestMapping("mostraCatalogo")
		public String MostrarCatalogo(Model modelo) throws ClassNotFoundException {
			modelo.addAttribute("cidades",new EnderecoDAO().ListarCidades());
			return "catalogo/mostrafiltros";
		}
}
