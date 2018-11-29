package Pacote.model;

public class Rota {
	private String Id = "";
	private String SaidaID;
	private String ChegadaID;
	private String CidadeSaida;
	private String CidadeChegada;
	private String UFSaida;
	private String UFChegada;
	
	public String getId() {
		return Id;
	}
	
	public String getSaidaid() {
		return SaidaID;
	}
	
	public void setSaidaid(String CidadeUFSaidaID) {
		SaidaID = CidadeUFSaidaID;
	}
	
	public String getChegadaid() {
		return ChegadaID;
	}
	
	public void setChegadaid(String CidadeUFChegadaID) {
		ChegadaID = CidadeUFChegadaID;
	}
	
	public void setId(String Id) {
		this.Id = Id;
	}
	
	public String getCidadeSaida() {
		return CidadeSaida;
	}
	
	public void setCidadesaida(String CidadeSaida) {
		this.CidadeSaida = CidadeSaida;
	}
	
	public String getCidadeChegada() {
		return CidadeChegada;
	}
	
	public void setCidadechegada(String CidadeChegada) {
		this.CidadeChegada = CidadeChegada;
	}
	
	public String getUFSaida() {
		return UFSaida;
	}
	
	public void setUfsaida(String UFSaida) {
		this.UFSaida = UFSaida;
	}
	
	public String getUFChegada() {
		return UFChegada;
	}
	
	public void setUfchegada(String UFChegada) {
		this.UFChegada = UFChegada;
	}
}
