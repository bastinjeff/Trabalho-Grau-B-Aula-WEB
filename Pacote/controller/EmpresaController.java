package Pacote.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.dao.EmpresaDAO;
import Pacote.model.Empresa;
import Pacote.model.Credenciais;
import Pacote.dao.LoginDAO;
@Controller
public class EmpresaController {
	
	@RequestMapping("mostraEmpresa")
	public String MostrarEmpresa(Model modelo, HttpSession sessao) throws ClassNotFoundException {
		EmpresaDAO dao = new EmpresaDAO();
		Empresa empresa = (Empresa) sessao.getAttribute("empresaLogada");
		Empresa emp = dao.PegarEmpresa(empresa.getId());
		modelo.addAttribute("empresa",emp);
		return "empresa/mostraempresa";
	}
	
	@RequestMapping("alteraEmpresa")
	public String AlterarEmpresa(Empresa empresa) throws ClassNotFoundException {
		EmpresaDAO dao = new EmpresaDAO();
		dao.Alterar(empresa);
		return "principal/principal";
	}
	
	@RequestMapping("mostraCriarempresa")
	public String MostraCriarEmpresa() {
		return "empresa/testecadastroempresa";
	}
	
	@RequestMapping("criaEmpresa")
	public String CriarEmpresa(Empresa empresa, Credenciais credenciais) throws ClassNotFoundException {
		credenciais.setLogin(empresa.getEmail());
		if(!new LoginDAO().VerificarEmail(credenciais.getLogin())) {
			String ID = new LoginDAO().CriarCredencial(credenciais);
			new EmpresaDAO().CriarEmpresa(empresa, ID);
		}
		return "principal/principal";
	}
}
