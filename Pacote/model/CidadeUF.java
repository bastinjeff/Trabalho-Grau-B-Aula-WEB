package Pacote.model;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class CidadeUF {
	private String id;
	private String Cidade;
	private String UF;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCidade() {
		return Cidade;
	}
	
	public void setCidade(String Cidade) {
		this.Cidade = Cidade;
	}
	
	public String getUf() {
		return UF;
	}
	
	public void setUf(String UF) {
		this.UF = UF;
	}
}
