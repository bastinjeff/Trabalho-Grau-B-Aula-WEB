package Pacote.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import Pacote.model.Viagem;
import Pacote.model.Catalogo;
import Pacote.model.Rota;
import Pacote.jdbc.ConnectionFactory;

public class CatalogoDAO {
		Connection connection;
		
		public CatalogoDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public Catalogo ResgatarCatalogo(String CidadeChegada, String CidadePartida){
			Catalogo Cat = new Catalogo();
			try {
				PreparedStatement stmt = this.connection.prepareStatement(ViagemQuery(CidadeChegada,CidadePartida));
				Cat = RealizarBusca(stmt,Cat);
				stmt.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return Cat;
		}
		
		public Catalogo ResgatarCatalogoEmpresa(String Empresa_ID) {
			Catalogo Cat = new Catalogo();
			try {
				PreparedStatement stmt = this.connection.prepareStatement(EmpresaViagem(Empresa_ID));
				Cat = RealizarBusca(stmt,Cat);
				stmt.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return Cat;
		}
		
		public Catalogo ResgatarCatalogoPassagens(String Usuario_ID) {
			Catalogo Cat = new Catalogo();
			try {
				PreparedStatement stmt = this.connection.prepareStatement(QueryPassagens(Usuario_ID));
				Cat = RealizarBusca(stmt,Cat);
				stmt.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return Cat;
		}

		private Catalogo RealizarBusca(PreparedStatement stmt, Catalogo Cat) throws SQLException {
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				Viagem viagem  = new Viagem();
				viagem.setNomeempresa(TestNULL(Resultado,"Empresa.Nome_Fantasia"));
				viagem.setHora(TestNULL(Resultado,"Viagem.Hora"));
				viagem.setPreco((TestNULL(Resultado,"Viagem.Preco")));
				viagem.setModalidade(TestNULL(Resultado,"Modalidade.TipoOnibus"));
				viagem.setId(Resultado.getString("Viagem.ID"));
				List<String> DiasSemana = new ArrayList<String>();
				String[] CidadeChegadaPartida = new String[2];
				String[] UFChegadaPartida = new String[2];
		    	//System.out.println("TestePreWhile");
		    	do {
		    		if(!DiasSemana.contains(Resultado.getString("DiaSemana.Dia"))) {
		    			DiasSemana.add(Resultado.getString("DiaSemana.Dia"));
		    		}
		    		
		    		if(Resultado.getString("Rotas.Cidade_UF_Partida_ID").equals(Resultado.getString("CidadeUF.Id"))) {
		    			CidadeChegadaPartida[0] = Resultado.getString("Cidade.NomeCidade");
		    			UFChegadaPartida[0] = Resultado.getString("UF.NomeUF");
		    		}else {
		    			CidadeChegadaPartida[1] = Resultado.getString("Cidade.NomeCidade");
		    			UFChegadaPartida[1] = Resultado.getString("UF.NomeUF");
		    		}
		    	
		    	}while(Resultado.next() && Resultado.getString("Viagem.ID").equals(viagem.getId()));
		    	Resultado.previous();
		    	viagem.setRota(CidadeChegadaPartida[0], CidadeChegadaPartida[1],
		    			UFChegadaPartida[0], UFChegadaPartida[1]);
	    		viagem.setDiassemana(DiasSemana);		    		
				Cat.AddViagem(viagem);	
			}
			Resultado.close();
			return Cat;
		}
		
		public void NovaViagem(Viagem viagem) throws ClassNotFoundException {
			viagem.getRota().setId(PegarIDRota(viagem));
			viagem.setId(InserirNovaViagem(viagem));
			InserirDiasSemana(viagem);
		}
		
		private void InserirDiasSemana(Viagem viagem) {
			List<String> IDDias = PegarIdsDiasSemana(viagem.getListadiassemana());
			String Query = "INSERT INTO Frequencia(DiaSemana_ID,Viagem_ID) VALUES(?,?)";
			try {				
				PreparedStatement stmt;
				for(String IDs: IDDias) {
					stmt = connection.prepareStatement((Query));
					stmt.setString(1, IDs);
					stmt.setString(2, viagem.getId());
					stmt.executeUpdate();
					stmt.close();
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		private List<String> PegarIdsDiasSemana(List<String> DiasSemana){
			boolean primeiro = true;
			List<String> IDDias = new ArrayList<String>();
			String Query = "Select * FROM DiaSemana WHERE ";
			for(String dias: DiasSemana){
				if(!primeiro) {
					Query += " OR ";
				}else primeiro = false;
				Query += "Dia = '" + dias + "'";
			}
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				ResultSet Resultado = stmt.executeQuery();
				while(Resultado.next()) {
					IDDias.add(Resultado.getString("ID"));
				}
				Resultado.close();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);				
			}
			return IDDias;
		}
		
		private String PegarIDRota(Viagem viagem) throws ClassNotFoundException {
			viagem.setRota(ChecarRota(viagem.getRota()));
			if(viagem.getRota().getId().equals("")) {
				viagem.getRota().setChegadaid(IDCidadeUF(viagem.getRota().getCidadeChegada(),viagem.getRota().getUFChegada()));
				System.out.println("ChegadaID: " + viagem.getRota().getChegadaid());
				viagem.getRota().setSaidaid(IDCidadeUF(viagem.getRota().getCidadeSaida(),viagem.getRota().getUFSaida()));
				System.out.println("SaidaID: " + viagem.getRota().getSaidaid());
				viagem.getRota().setId(CriarRota(viagem.getRota()));
			} 
			return viagem.getRota().getId();
		}
		
		private String IDCidadeUF(String Cidade, String UF) throws ClassNotFoundException {
			EnderecoDAO dao = new EnderecoDAO();
			return dao.PegarIDCidadeUF(Cidade, UF);
		}
		
		private String CriarRota(Rota rota) {
			String Query = "INSERT INTO Rotas(Cidade_UF_Partida_ID,Cidade_UF_Chegada_ID) VALUES (?,?)";
			String ID = "";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, rota.getSaidaid());
				stmt.setString(2, rota.getChegadaid());
				stmt.executeUpdate();
				ResultSet Resultado = stmt.getGeneratedKeys();
				while(Resultado.next()) {
					ID = String.valueOf(Resultado.getInt(1));
				}
				Resultado.close();
				stmt.close();
				return ID;
				
			}catch(SQLException e) {
				System.out.println(e);
				return ID;
			}
		}
		
		private Rota ChecarRota(Rota rota) {
			try {
				PreparedStatement stmt = connection.prepareStatement(QueryChecarRota(rota.getCidadeChegada(),rota.getCidadeSaida()));
				ResultSet Resultado = stmt.executeQuery();
				if(Resultado.next()) {
					rota.setId(Resultado.getString("Rotas.ID"));
					rota.setChegadaid(Resultado.getString("Cidade_UF_Chegada_ID"));
					rota.setSaidaid(Resultado.getString("Cidade_UF_Partida_ID"));
					Resultado.close();
					stmt.close();
					return rota;
				}else {
					Resultado.close();
					stmt.close();
					return rota;
				}
			}catch(SQLException e) {
				System.out.println(e);
				return rota;
			}
		}
		
		private String InserirNovaViagem(Viagem viagem) {
			String IDModalidade = PegarIDModalidade(viagem.getModalidade());
			String ID = "";
			try {
				PreparedStatement stmt = connection.prepareStatement(QueryInsertViagem(), Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, viagem.getEmpresa().getId());
				stmt.setString(2, viagem.getRota().getId());
				stmt.setString(3, viagem.getHora());
				stmt.setString(4, viagem.getPreco());
				stmt.setString(5, IDModalidade);
				stmt.executeUpdate();
				ResultSet Resultado = stmt.getGeneratedKeys();
				while(Resultado.next()) {
					ID = String.valueOf(Resultado.getInt(1));
				}
				Resultado.close();
				stmt.close();
				return ID;
			}catch(SQLException e) {
				System.out.println(e);
				return ID;
			}
		}
		
		private String PegarIDModalidade(String Modalidade) {
			String ID = "";
			String Query = "SELECT ID FROM Modalidade WHERE TipoOnibus = ?";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, Modalidade);
				ResultSet Resultado = stmt.executeQuery();
				while(Resultado.next()) {
					ID = Resultado.getString("ID");
				}
				Resultado.close();
				stmt.close();		
				return ID;
			}catch(SQLException e) {
				System.out.println(e);
				return ID;
			}
		}
		
		public void DeletarViagem(Viagem viagem) {
			String Query = "DELETE FROM Viagem WHERE Id=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, viagem.getId());
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		
		private String TestNULL(ResultSet Result, String Coluna) throws SQLException {
			if(Result.wasNull()) {
				return "";
			}else return Result.getString(Coluna);			
		}
		
		private String ViagemQuery(String CidadeChegada, String CidadeSaida) {
			String Query = "Select *";
			Query += " From Viagem";
			Query += " LEFT JOIN Empresa ON Empresa_ID = Empresa.ID";
			Query += " LEFT JOIN Modalidade ON Modalidade_ID = Modalidade.ID";
			Query += " LEFT JOIN Frequencia ON Viagem_ID = Viagem.ID";
			Query += " LEFT JOIN DiaSemana ON  DiaSemana_ID = DiaSemana.ID";
			Query += " LEFT JOIN Rotas ON Rota_ID = Rotas.ID";
			Query += " LEFT JOIN CidadeUF ON Cidade_UF_Partida_ID = CidadeUF.ID OR Cidade_UF_Chegada_ID = CidadeUF.ID";
			Query += " LEFT JOIN Cidade ON Cidade_ID = Cidade.ID";
			Query += " LEFT JOIN UF ON UF_ID = UF.ID";
			Query += " WHERE Cidade_UF_Partida_ID = ( SELECT ID FROM Cidade WHERE NomeCidade  = '"+CidadeSaida+"')";
			Query += " AND Cidade_UF_Chegada_ID = (SELECT ID FROM Cidade WHERE NomeCidade = '"+CidadeChegada+"')";
			System.out.println(Query);
			return Query;
		}
		
		private String EmpresaViagem(String Empresa_ID) {
			String Query = "Select *";
			Query += " From Viagem";
			Query += " LEFT JOIN Empresa ON Empresa_ID = Empresa.ID";
			Query += " LEFT JOIN Modalidade ON Modalidade_ID = Modalidade.ID";
			Query += " LEFT JOIN Frequencia ON Viagem_ID = Viagem.ID";
			Query += " LEFT JOIN DiaSemana ON  DiaSemana_ID = DiaSemana.ID";
			Query += " LEFT JOIN Rotas ON Rota_ID = Rotas.ID";
			Query += " LEFT JOIN CidadeUF ON Cidade_UF_Partida_ID = CidadeUF.ID OR Cidade_UF_Chegada_ID = CidadeUF.ID";
			Query += " LEFT JOIN Cidade ON Cidade_ID = Cidade.ID";
			Query += " LEFT JOIN UF ON UF_ID = UF.ID";
			Query += " WHERE Empresa.Id = "+Empresa_ID+"";
			System.out.println(Query);
			return Query;
		}
		
		private String QueryInsertViagem() {
			String Query = "INSERT INTO Viagem(Empresa_ID,Rota_ID,Hora,Preco,Modalidade_ID)";
			Query += " VALUES(?,?,?,?,?)";
			return Query;
		}
		
		private String QueryChecarRota(String CidadeChegada, String CidadeSaida) {
			String Query = "SELECT Rotas.ID, Cidade_UF_Partida_ID, Cidade_UF_Chegada_ID";
			Query += " FROM Rotas";
			Query += " LEFT JOIN CidadeUF ON Cidade_UF_Partida_ID = CidadeUF.ID OR Cidade_UF_Chegada_ID = CidadeUF.ID";
			Query += " LEFT JOIN Cidade ON Cidade_ID = Cidade.ID";
			Query += " LEFT JOIN UF ON UF_ID = UF.ID";
			Query += " WHERE Cidade_UF_Partida_ID = ( SELECT ID FROM Cidade WHERE NomeCidade  = '"+CidadeSaida+"')";
			Query += " AND Cidade_UF_Chegada_ID = (SELECT ID FROM Cidade WHERE NomeCidade = '"+CidadeChegada+"')";
			return Query;
		}
		
		private String QueryPassagens(String Usuario_ID) {
			String Query = "Select *";
			Query += " From Viagem";
			Query += " LEFT JOIN Empresa ON Empresa_ID = Empresa.ID";
			Query += " LEFT JOIN Modalidade ON Modalidade_ID = Modalidade.ID";
			Query += " LEFT JOIN Frequencia ON Frequencia.Viagem_ID = Viagem.ID";
			Query += " LEFT JOIN DiaSemana ON  DiaSemana_ID = DiaSemana.ID";
			Query += " LEFT JOIN Rotas ON Rota_ID = Rotas.ID";
			Query += " LEFT JOIN CidadeUF ON Cidade_UF_Partida_ID = CidadeUF.ID OR Cidade_UF_Chegada_ID = CidadeUF.ID";
			Query += " LEFT JOIN Cidade ON Cidade_ID = Cidade.ID";
			Query += " LEFT JOIN UF ON UF_ID = UF.ID";
			Query += " LEFT JOIN Passagens ON Passagens.Viagem_ID = Viagem.ID";
			Query += " WHERE Usuario_ID = "+Usuario_ID+"";
			System.out.println(Query);
			return Query;
		}
		
}
