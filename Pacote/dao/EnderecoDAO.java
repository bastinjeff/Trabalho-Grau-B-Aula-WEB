package Pacote.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Pacote.jdbc.ConnectionFactory;

import java.util.ArrayList;
import Pacote.model.Endereco;
public class EnderecoDAO {
	Connection connection;
	public EnderecoDAO() throws ClassNotFoundException {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Endereco PegarEndereco() {
		String Query = "SELECT * From Endereco "
				+ "JOIN CidadeUF ON CidadeUF_id = CidadeUF.id "
				+ "JOIN Cidade ON Cidade_ID = Cidade.id "
				+ "JOIN UF ON UF_id = UF.Id "
				+ "Where Endereco.Id =? ";
		Endereco endereco = new Endereco();
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			stmt.setString(1, "2");
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				endereco.setRua(TestNULL(Resultado,"Endereco.Rua"));
				endereco.setBairro(TestNULL(Resultado,"Endereco.Bairro"));
				endereco.setNumero(TestNULL(Resultado,"Endereco.Numero"));
				endereco.setCep(TestNULL(Resultado,"Endereco.CEP"));
				System.out.println(Resultado.getString("Endereco.Id"));
				endereco.setId(TestNULL(Resultado,"Endereco.Id"));
				System.out.println(Resultado.getString("Cidade.NomeCidade"));
				endereco.setCidadeUF(TestNULL(Resultado,"Cidade.NomeCidade"), TestNULL(Resultado,"UF.NomeUF"));
			}
			Resultado.close();
			stmt.close();
			return endereco;
		}catch(SQLException e) {
			System.out.println(e);
			return endereco;
		}
	}
	
	public void Alterar(Endereco endereco) {
		String Query = "UPDATE Endereco SET Rua =? , Numero =? , Bairro =? , CEP =? WHERE id =?";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCep());
			stmt.setString(5, endereco.getId());
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
	
}
