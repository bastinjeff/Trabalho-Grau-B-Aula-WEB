package Pacote.model;

import javax.validation.constraints.Size;

public class Credenciais {
	private String Login;
	@Size(min=1 , message="Preencher Campo")
	private String Senha;
	@Size(min=1 , message="Preencher Campo")
	
	public String getLogin() {
		return Login;
	}
	
	public void setLogin(String Login) {
		this.Login = Login;
	}
	
	public String getSenha() {
		return Senha;
	}
	
	public void setSenha(String Senha) {
		this.Senha = Senha;
	}
}
