package Pacote.model;

public class Empresa {
	private String Nome_fantasia;
	private String Razao_Social;
	private String CNPJ;
	private String Email;
	private String Telefone;
	
	public String getNome_Fantasia() {
		return Nome_fantasia;
	}
	
	public void setNome_fantasia(String Nome_fantasia) {
		this.Nome_fantasia = Nome_fantasia;
	}
	
	public String getRazao_Social() {
		return Razao_Social;
	}
	
	public void setRazao_Social(String Razao_Social) {
		this.Razao_Social = Razao_Social;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getTelefone() {
		return Telefone;
	}
	
	public void setTelefone(String Telefone) {
		this.Telefone = Telefone;
	}
}
