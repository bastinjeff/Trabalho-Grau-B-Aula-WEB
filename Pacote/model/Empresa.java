package Pacote.model;

public class Empresa {
	private String id;
	private String nomefantasia;
	private String razaosocial;
	private String cnpj;
	private String Email;
	private String Telefone;
	private Endereco endereco = new Endereco();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;		
	}
	
	public String getNomefantasia() {
		return nomefantasia;
	}
	
	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}
	
	public String getRazaosocial() {
		return razaosocial;
	}
	
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setidend(String id) {
		endereco.setId(id);
	}
	
	public void setRuaend(String Rua) {
		endereco.setRua(Rua);
	}
	
	public void setNumeroend(String Numero) {
		endereco.setNumero(Numero);
	}
	
	public void setBairroend(String Bairro) {
		endereco.setBairro(Bairro);
	}
	
	public void setCepend(String CEP) {
		endereco.setCep(CEP);
	}
	
	public void setCidadeufend(String Cidade, String UF) {
		endereco.setCidadeUF(Cidade, UF);
	}
}
