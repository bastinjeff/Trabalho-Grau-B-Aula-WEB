package Pacote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import Pacote.dao.EmpresaDAO;
import Pacote.model.Empresa;

@Controller
public class EmpresaController {
	
	@RequestMapping("mostraEmpresa")
	public String MostrarEmpresa(Model modelo) throws ClassNotFoundException {
		EmpresaDAO dao = new EmpresaDAO();
		Empresa emp = dao.PegarEmpresa();
		modelo.addAttribute("empresa",emp);
		return "empresa/mostraempresa";
	}
	
	@RequestMapping("alteraEmpresa")
	public String AlterarEmpresa(Empresa empresa) throws ClassNotFoundException {
		EmpresaDAO dao = new EmpresaDAO();
		dao.Alterar(empresa);
		return "principal/principal";
	}
}
