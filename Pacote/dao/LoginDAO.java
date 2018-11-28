package Pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Pacote.jdbc.ConnectionFactory;
import Pacote.model.Credenciais;

public class LoginDAO {
	
	Connection connection;
	
	public LoginDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public String ExisteConta(Credenciais credenciais, String Tabela){
		String ID = "", sql = "";
		if(Tabela.equals("Usuario")) {
			sql = "select Usuario.Id from Credenciais JOIN Usuario ON Credenciais.id = Credenciais_ID where login= ? and senha = ?";
		}else sql = "select Empresa.Id from Credenciais JOIN Empresa ON Credenciais.id = Credenciais_ID where login= ? and senha = ?";
    	System.out.println("val = " + credenciais.getLogin());
    	System.out.println("val = " + credenciais.getSenha());
			try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);			
			stmt.setString(1, credenciais.getLogin());
			stmt.setString(2, credenciais.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			//stmt.execute();
			
			if(rs.next())
			{
				ID = rs.getString("Id");
				stmt.close();
				return ID;
			}
			else
			{
				stmt.close();
				return ID;
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public boolean VerificarEmail(String Email) {
		String Query = "Select Login FROM Credenciais";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query);
			ResultSet Resultado = stmt.executeQuery();
			while(Resultado.next()) {
				if(Resultado.getString("Login").equals(Email)) {
					stmt.close();
					Resultado.close();
					return true;
				}
			}
			stmt.close();
			Resultado.close();
			return false;
		}catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public String CriarCredencial(Credenciais credencial) {
		String Query = "INSERT INTO Credenciais(Login,Senha) VALUES(?,?)";
		String ID="";
		try {
			PreparedStatement stmt = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, credencial.getLogin());
			stmt.setString(2, credencial.getSenha());
			stmt.executeUpdate();
			ResultSet Resultado = stmt.getGeneratedKeys();
			while(Resultado.next()) {
				ID = String.valueOf(Resultado.getInt(1));
			}
			stmt.close();
			return ID;
		}catch(SQLException e) {
			System.out.println(e);
			return ID;
		}
	}
	
}
