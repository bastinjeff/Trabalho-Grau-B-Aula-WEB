package Pacote.model;
import java.util.List;
public class Catalogo {
	private List<Viagem> Viagens;
	
	public List<Viagem> getViagens(){
		return Viagens;
	}
	
	public void AddViagem(Viagem viagem) {
		Viagens.add(viagem);
	}
	
	public void RemoveViagem(Viagem viagem) {
		Viagens.remove(viagem);
	}
}
