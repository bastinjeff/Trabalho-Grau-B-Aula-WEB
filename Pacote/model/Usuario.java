package Pacote.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class Usuario {
	private String Nome;
	private String Email;
	private String CPF;
	private String Telefone;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar DataNascimento; 
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String Nome) {
		this.Nome = Nome;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getTelefone() {
		return Telefone;
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
}
