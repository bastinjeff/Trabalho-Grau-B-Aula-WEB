package Pacote.model;
import java.util.List;

public class Viagem {
	private Empresa empresa;
	private Rota rota;
	private String hora;
	private String preco;
	private List<String> diassemana;
	private String modalidade;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String NomeEmpresa) {
		empresa = new Empresa();
		empresa.setNomefantasia(NomeEmpresa);		
	}
	
	public String getNomeempresa() {
		return empresa.getNomefantasia();
	}
	
	public Rota getRota() {
		return rota;
	}
	
	public void setRota(String CidadeSaida, String CidadeChegada, String UFChegada, String UFSaida) {
		rota = new Rota();
		rota.setCidadeChegada(CidadeChegada);
		rota.setCidadeSaida(CidadeSaida);
		rota.setUFChegada(UFChegada);
		rota.setUFSaida(UFSaida);
	}
	
	public String getCidadechegada() {
		return rota.getCidadeChegada();
	}
	
	public String getCidadesaida() {
		return rota.getCidadeSaida();
	}
	
	public String getUfchegada() {
		return rota.getUFChegada();
	}
	
	public String getUfsaida() {
		return rota.getUFSaida();
	}

	public String getHora() {
		return hora;
	}
	
	public void setHora(String Hora) {
		this.hora = Hora;
	}
	
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String Preco) {
		this.preco = Preco;
	}
	
	public String getDiassemana(){
		String DiasSemana = "";
		boolean Primeiro = true;
		for(String Dias: diassemana ) {
			if(!Primeiro) {
				DiasSemana+=",";
			}else Primeiro = false;
			DiasSemana+=Dias;
		}
		return DiasSemana;
	}
	
	public void setDiassemana(List<String> DiasSemana){
		this.diassemana = DiasSemana;
	}
	
	public String getModalidade() {
		return modalidade;
	}
	
	public void setModalidade(String Modalidade) {
		this.modalidade = Modalidade;
	}
}
