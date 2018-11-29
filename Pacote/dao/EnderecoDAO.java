package Pacote.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Pacote.jdbc.ConnectionFactory;

import java.util.ArrayList;
import Pacote.model.Endereco;
public class EnderecoDAO {
	Connection connection;
	public EnderecoDAO() throws ClassNotFoundException {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Endereco PegarEndereco(String Endereco_ID) {
		String Query = "SELECT * From Endereco "
				+ "JOIN CidadeUF ON CidadeUF_id = CidadeUF.id "
				+ "JOIN Cidade ON Cidade_ID = Cidade.id "
				+ "JOIN UF ON UF_id = UF.Id "
				+ "Where Endereco.Id =? ";
		Endereco endereco = new Endereco();
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			stmt.setString(1, Endereco_ID);
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
		String Query = "UPDATE Endereco SET Rua =? , Numero =? , Bairro =? , CEP =? , CidadeUF_ID =? WHERE id =?";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCep());
			String CidadeUFID = PegarIDCidadeUF(endereco.getCidadeuf().getCidade(),endereco.getCidadeuf().getUf());
			System.out.println(CidadeUFID);
			stmt.setString(5, CidadeUFID);
			stmt.setString(6, endereco.getId());
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e) {
			System.out.println(e);			
		}
	}
	
	public String Criar(Endereco endereco) {
		String ID = "";
		String Query = "INSERT INTO Endereco( Rua, Numero, Bairro, CEP, CidadeUF_ID )"
				+ " VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCep());
			String CidadeUFID = PegarIDCidadeUF(endereco.getCidadeuf().getCidade(),endereco.getCidadeuf().getUf());
			stmt.setString(5, CidadeUFID);
			stmt.executeUpdate();
			ResultSet Resultado = stmt.getGeneratedKeys();
			while(Resultado.next()) {
				ID = String.valueOf(Resultado.getString(1));
			}
			Resultado.close();
			stmt.close();
			return ID;
		}catch(SQLException e) {
			System.out.println(e);
			return ID;
		}
	}
	
	public String PegarIDCidadeUF(String Cidade, String UF) {		
		String IDCidade = "", IDUF = "";
		if(!VerificarCidade(Cidade)) {
			IDCidade = CriarCidade(Cidade);
		}else IDCidade = PegarID("NomeCidade","Cidade",Cidade);
		IDUF = PegarID("NomeUF","UF",UF);
		String CidadeUFID = TratarCidadeUF(IDCidade,IDUF);
		return CidadeUFID;
	}
	
	public boolean VerificarCidade(String Cidade) {
		String Query = "SELECT NomeCidade FROM Cidade";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				if(Resultado.getString("NomeCidade").equals(Cidade)) {
					return true;
				}
			}
			return false;
		}catch(SQLException e) {
			System.out.println(e);
			return true;
		}
	}
	
	private String CriarCidade(String Cidade) {
		String Query = "INSERT INTO Cidade(NomeCidade) VALUES(?)";
		String ID = "";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, Cidade);
			stmt.executeUpdate();
			ResultSet Resultado = stmt.getGeneratedKeys();
			while(Resultado.next()) {
				ID = String.valueOf(Resultado.getString(1));
			}
			stmt.close();
			Resultado.close();
			return ID;
		}catch(SQLException e) {
			System.out.println(e);
			return ID;
		}
	}
	
	private String PegarID(String Coluna,String Tabela,String Valor) {
		String Query = "";
		if(Tabela.equals("UF")) {
			Query = "SELECT ID, NomeUF FROM UF";
		}else Query = "SELECT ID, NomeCidade FROM Cidade";
		String ID = "";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				if(Resultado.getString(Coluna).equals(Valor)) {
					ID = Resultado.getString("ID");
				}
			}
			stmt.close();
			Resultado.close();
			return ID;
		}catch(SQLException e) {
			System.out.println("Teste " + e);
			return ID;
		}
	}
	
	private String TratarCidadeUF(String IDCidade, String IDUF) {
		String IDCidadeUF = "";
		String Query = "Select * From CidadeUF";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				if(Resultado.getString("Cidade_ID").equals(IDCidade)&&Resultado.getString("UF_ID").equals(IDUF)) {
					IDCidadeUF = Resultado.getString("ID");
				}				
			}
			stmt.close();
			Resultado.close();
			if(IDCidadeUF.equals("")) {
				IDCidadeUF = CriarCidadeUF(IDCidade,IDUF);
			}
			return IDCidadeUF;
		}catch(SQLException e) {
			System.out.println(e);
			return IDCidadeUF;
		}
	}
	
	private String CriarCidadeUF(String CidadeID, String UFID) {
		String ID = "";
		String Query = "INSERT INTO CidadeUF(Cidade_ID,UF_ID) VALUES(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, CidadeID);
			stmt.setString(2, UFID);
			stmt.executeUpdate();
			ResultSet Resultado = stmt.getGeneratedKeys();
			while(Resultado.next()) {
				ID = String.valueOf(Resultado.getString(1));
			}
			stmt.close();
			Resultado.close();
			return ID;
		}catch(SQLException e) {
			return ID;
		}
	}
	
	public List<String> ListarCidades() {
		String Query = "SELECT NomeCidade FROM Cidade";
		List<String> Cidades = Listar("NomeCidade",Query);
		return Cidades;
	}
		
	public List<String> ListarUFs() {
		String Query = "SELECT NomeUF FROM UF";
		List<String> UFs = Listar("NomeUF",Query);
		return UFs;
	}
	
	public List<String> ListarCidadeUF(){
		String Query = "SELECT * From CidadeUF JOIN Cidade ON Cidade_ID = Cidade.iD JOIN UF ON UF_ID = UF.Id";
		List<String> Lista = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				Lista.add(Resultado.getString("Cidade.NomeCidade") + "/" + Resultado.getString("Uf.NomeUF"));
			}
			Resultado.close();
			stmt.close();		
			return Lista;
		}catch(SQLException e) {
			System.out.println(e);
			return Lista;
		}
	}
	
	private List<String> Listar(String Coluna, String Query) {
		List<String> Lista = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				if(!Lista.contains(Resultado.getString(Coluna))) {
					Lista.add(Resultado.getString(Coluna));
				}
			}
			Resultado.close();
			stmt.close();		
			return Lista;
		}catch(SQLException e) {
			System.out.println(e);
			return Lista;
		}
	}
	
	private String TestNULL(ResultSet Result, String Coluna) throws SQLException {
		if(Result.wasNull()) {
			return "";
		}else return Result.getString(Coluna);			
	}
	
}
