package Pacote.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import Pacote.model.Viagem;


import Pacote.jdbc.ConnectionFactory;

public class CatalogoDAO {
		Connection connection;
		
		public CatalogoDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public List<Viagem> ResgatarCatalogo(){
			List<Viagem> Viagens = new ArrayList<Viagem>();
			try {
				PreparedStatement stmt = this.connection.prepareStatement(ViagemQuery());
				ResultSet Resultado = stmt.executeQuery();
				while(Resultado.next()) {
					Viagem viagem  = new Viagem();
					viagem.setEmpresa(TestNULL(Resultado,"Empresa.Nome_Fantasia"));
					System.out.println("Teste: " + viagem.getEmpresa().getNome_Fantasia());
				}
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}

			
			return Viagens;
		}
		
		private String TestNULL(ResultSet Result, String Coluna) throws SQLException {
			if(Result.wasNull()) {
				return "";
			}else return Result.getString(Coluna);			
		}
		
		private String ViagemQuery() {
			String Query = "Select *";
			Query += "From Viagem";
			Query += " LEFT JOIN Empresa ON Empresa_ID = Empresa.ID";
			Query += " LEFT JOIN Modalidade ON Modalidade_ID = Modalidade.ID";
			Query += " LEFT JOIN Frequencia ON Viagem_ID = Viagem.ID";
			Query += " LEFT JOIN DiaSemana ON  DiaSemana_ID = DiaSemana.ID";
			Query += " LEFT JOIN Rotas ON Rota_ID = Rotas.ID";
			Query += " LEFT JOIN CidadeUF ON Cidade_UF_Partida_ID = CidadeUF.ID AND Cidade_UF_Chegada_ID = CidadeUF.ID";
			Query += " LEFT JOIN Cidade ON Cidade_ID = Cidade.ID";
			Query += " LEFT JOIN UF ON UF_ID = UF.ID";
			return Query;
		}
}
