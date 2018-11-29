package Pacote.model;
import java.util.List;

public class Viagem {
	private String Id;
	private Empresa empresa = new Empresa();;
	private Rota rota = new Rota();
	private String hora;
	private String preco;
	private List<String> diassemana;
	private String modalidade;
	
	public String getId() {
		return Id;
	}
	
	public void setId(String Id) {
		this.Id = Id;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setIdempresa(String Empresa_ID) {
		empresa.setId(Empresa_ID);
	}
	
	public void setNomeempresa(String NomeEmpresa) {		
		empresa.setNomefantasia(NomeEmpresa);		
	}
	
	public String getNomeempresa() {
		return empresa.getNomefantasia();
	}
	
	public Rota getRota() {
		return rota;
	}
	
	public void setRota(String CidadeSaida, String CidadeChegada, String UFSaida, String UFChegada) {
		rota.setCidadechegada(CidadeChegada);
		rota.setCidadesaida(CidadeSaida);
		rota.setUfchegada(UFChegada);
		rota.setUfsaida(UFSaida);
	}
	
	public void setRota(Rota rota) {
		this.rota = rota;
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
	
	public List<String> getListadiassemana(){
		return diassemana;
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
