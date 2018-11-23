package Pacote.model;
import java.util.List;

public class Viagem {
	private Empresa empresa;
	private Rota rota;
	private String Hora;
	private String Preco;
	private List<String> DiasSemana;
	private String Modalidade;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String NomeEmpresa) {
		empresa = new Empresa();
		empresa.setNome_fantasia(NomeEmpresa);		
	}
	
	public Rota getRota() {
		return rota;
	}
	
	public void setRota(String Chegada, String Saida) {
		rota = new Rota();
		rota.setChegada(Chegada);
		rota.setSaida(Saida);
	}
	
	public String getHora() {
		return Hora;
	}
	
	public void setHora(String Hora) {
		this.Hora = Hora;
	}
	
	public String getPreco() {
		return Preco;
	}
	
	public void setPreco(String Preco) {
		this.Preco = Preco;
	}
	
	public List<String> getDiasSemana(){
		return DiasSemana;
	}
	
	public void setDiasSemana(List<String> DiasSemana){
		this.DiasSemana = DiasSemana;
	}
	
	public String getModalidade() {
		return Modalidade;
	}
	
	public void setModalidade(String Modalidade) {
		this.Modalidade = Modalidade;
	}
}