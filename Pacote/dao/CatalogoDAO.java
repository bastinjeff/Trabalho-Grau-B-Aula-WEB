package Pacote.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import Pacote.model.Viagem;
import Pacote.model.Catalogo;


import Pacote.jdbc.ConnectionFactory;

public class CatalogoDAO {
		Connection connection;
		
		public CatalogoDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public Catalogo ResgatarCatalogo(){
			Catalogo Cat = new Catalogo();
			try {
				PreparedStatement stmt = this.connection.prepareStatement(ViagemQuery());
				ResultSet Resultado = stmt.executeQuery();
				while(Resultado.next()) {
					Viagem viagem  = new Viagem();
					viagem.setEmpresa(TestNULL(Resultado,"Empresa.Nome_Fantasia"));
					viagem.setHora(TestNULL(Resultado,"Viagem.Hora"));
					viagem.setPreco((TestNULL(Resultado,"Viagem.Preco")));
					viagem.setModalidade(TestNULL(Resultado,"Modalidade.TipoOnibus"));
					String ViagemID = Resultado.getString("Viagem.ID");
					List<String> DiasSemana = new ArrayList<String>();
					List<String> CidadeChegadaPartida = new ArrayList<String>();
					List<String> UFChegadaPartida = new ArrayList<String>();
			    	//System.out.println("TestePreWhile");
			    	do {
			    		if(!DiasSemana.contains(Resultado.getString("DiaSemana.Dia"))) {
			    			DiasSemana.add(Resultado.getString("DiaSemana.Dia"));
			    		}
			    		if(!CidadeChegadaPartida.contains(TestNULL(Resultado,"Cidade.NomeCidade"))) {
			    			CidadeChegadaPartida.add(TestNULL(Resultado,"Cidade.NomeCidade"));
			    		}
			    		if(!UFChegadaPartida.contains(TestNULL(Resultado,"UF.NomeUF"))) {
			    			UFChegadaPartida.add((TestNULL(Resultado,"UF.NomeUF")));
			    		}
			    	}while(Resultado.next() && Resultado.getString("Viagem.ID").equals(ViagemID));
			    	Resultado.previous();
			    	List<String> CidadesUFs = OrganizarCidUF(CidadeChegadaPartida,UFChegadaPartida);
			    	viagem.setRota(CidadesUFs.get(0), CidadesUFs.get(1),
			    			CidadesUFs.get(2), CidadesUFs.get(3));
		    		viagem.setDiassemana(DiasSemana);		    		
					Cat.AddViagem(viagem);					
				}
				Resultado.close();
				stmt.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return Cat;
		}
		
		private List<String> OrganizarCidUF(List<String> Cidades, List<String> Ufs){
			List<String> CidUF = new ArrayList<String>();
			if(Cidades.size()==2) {
				CidUF.add(Cidades.get(0));
				CidUF.add(Cidades.get(1));
			}else {
				CidUF.add(Cidades.get(0));
				CidUF.add(Cidades.get(0));
			}
			
			if(Ufs.size()==2) {
				CidUF.add(Ufs.get(0));
				CidUF.add(Ufs.get(1));
			}else {
				CidUF.add(Ufs.get(0));
				CidUF.add(Ufs.get(0));
			}
			return CidUF;
		}
		
		private String TestNULL(ResultSet Result, String Coluna) throws SQLException {
			if(Result.wasNull()) {
				return "";
			}else return Result.getString(Coluna);			
		}
		
		private String ViagemQuery() {
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
			return Query;
		}
}
