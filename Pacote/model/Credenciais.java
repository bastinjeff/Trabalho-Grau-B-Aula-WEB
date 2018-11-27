package Pacote.model;

import javax.validation.constraints.Size;

public class Credenciais {
	private String logon;
	@Size(min=1 , message="Preencher Campo")
	private String senha;
	@Size(min=1 , message="Preencher Campo")
		
	public String getLogin() {
		return logon;
	}
	
	public void setLogin(String logon) {
		this.logon = logon;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
