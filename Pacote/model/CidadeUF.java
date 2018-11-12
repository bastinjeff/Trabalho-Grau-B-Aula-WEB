package Pacote.model;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
public class CidadeUF {
	private String Cidade;
	private String UF;
	
	public String getCidade() {
		return Cidade;
	}
	
	public void setCidade(String Cidade) {
		this.Cidade = Cidade;
	}
	
	public String getUF() {
		return UF;
	}
	
	public void setUF(String UF) {
		this.UF = UF;
	}
}
