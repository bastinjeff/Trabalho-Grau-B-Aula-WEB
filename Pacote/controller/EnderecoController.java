package Pacote.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.model.Endereco;
import Pacote.dao.EnderecoDAO;
import Pacote.model.Empresa;
import Pacote.dao.EmpresaDAO;
@Controller
public class EnderecoController {
	@RequestMapping("mostraEndereco")
	public String MostrarEndereco(Model modelo, HttpSession sessao) throws ClassNotFoundException {
		EnderecoDAO dao = new EnderecoDAO();
		Empresa empresa = (Empresa) sessao.getAttribute("empresaLogada");
		empresa = new EmpresaDAO().PegarEmpresa(empresa.getId());
		Endereco end = dao.PegarEndereco(empresa.getEndereco().getId());
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
