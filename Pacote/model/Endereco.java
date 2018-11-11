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
}
