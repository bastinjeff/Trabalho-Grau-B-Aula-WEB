package Pacote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.model.Endereco;
import Pacote.dao.EnderecoDAO;

@Controller
public class EnderecoController {
	@RequestMapping("mostraEndereco")
	public String MostrarEndereco(Model modelo) throws ClassNotFoundException {
		EnderecoDAO dao = new EnderecoDAO();
		Endereco end = dao.PegarEndereco();
		modelo.addAttribute("endereco",end);
		return "Endereco/mostraendereco";
	}
	
	@RequestMapping("alteraEndereco")
	public String AlterarEndereco(Endereco endereco) throws ClassNotFoundException {
		System.out.println(endereco.getCidadeuf().getCidade());
		EnderecoDAO dao = new EnderecoDAO();
		dao.Alterar(endereco);
		return "principal/principal";
	}
}
