package Pacote.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class Usuario {
	private String id;
	private String login;
	private String Nome;
	private String Email;
	private String CPF;
	private String Telefone;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar DataNascimento; 
	
	public String getid() {
		return id;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getNome() {
		return TestNULL(Nome);
	}
	
	public void setNome(String Nome) {
		this.Nome = Nome;
	}
	
	public String getEmail() {
		return TestNULL(Email);
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getCpf() {
		return TestNULL(CPF);
	}
	public void setCpf(String CPF) {
		this.CPF = CPF;
	}
	
	public String getTelefone() {
		return TestNULL(Telefone);
	}
	
	public void setTelefone(String Telefone){
		this.Telefone = Telefone;
	}
	
	public Calendar getDataNasc() {
		return DataNascimento;
	}
	
	public void setDataNasc(Calendar DataNascimento) {
		this.DataNascimento = DataNascimento;
	}
	
	private String TestNULL(String Teste) {
		if(Teste==null || Teste=="") {
			return "";
		}
		else return Teste;
	}
}
