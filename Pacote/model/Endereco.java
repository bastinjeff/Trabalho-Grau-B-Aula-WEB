package Pacote.model;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class Endereco {
	private String Rua;
	private String Numero;
	private String Bairro;
	private String CEP;
	private CidadeUF cidadeuf;
	
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
	
	public String getCEP() {
		return CEP;		
	}
	
	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
	
	public CidadeUF getCidadeUF() {
		return cidadeuf;
	}
	
	public void setCidadeUF(String Cidade, String UF) {
		cidadeuf = new CidadeUF();
		cidadeuf.setCidade(Cidade);
		cidadeuf.setUF(UF);
	}
}
