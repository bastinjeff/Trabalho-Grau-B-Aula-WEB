package Pacote.model;
import java.util.ArrayList;
import java.util.List;
public class Catalogo {
	public List<Viagem> Viagens = new ArrayList<Viagem>();
	
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
