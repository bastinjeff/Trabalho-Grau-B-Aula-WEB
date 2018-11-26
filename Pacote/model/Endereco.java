package Pacote.model;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class Endereco {
	private String id;
	private String Rua;
	private String Numero;
	private String Bairro;
	private String CEP;
	private CidadeUF cidadeuf = new CidadeUF();
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getRua() {
		return Rua;
	}
	
	public void setRua(String Rua) {
		this.Rua = Rua;
	}
	
	public String getNumero() {
		return Numero;
	}
	
	public void setNumero(String Numero) {
		this.Numero = Numero;
	}
	
	public String getBairro() {
		return Bairro;
	}
	
	public void setBairro(String Bairro) {
		this.Bairro = Bairro;
	}
	
	public String getCep() {
		return CEP;		
	}
	
	public void setCep(String CEP) {
		this.CEP = CEP;
	}
	
	public CidadeUF getCidadeuf() {
		return cidadeuf;
	}
	
	public void setCidadeUF(String Cidade, String UF) {
		cidadeuf.setCidade(Cidade);
		cidadeuf.setUf(UF);
	}
}
