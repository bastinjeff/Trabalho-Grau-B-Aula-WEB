package Pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Pacote.jdbc.ConnectionFactory;
import Pacote.model.Credenciais;

public class LoginDAO {
	
	Connection connection;
	
	public LoginDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public boolean existeUsuario(Credenciais credenciais){
		
		String sql = "select * from Credenciais where login= ? and senha = ?";
    	System.out.println("val = " + credenciais.getLogin());
    	System.out.println("val = " + credenciais.getSenha());
			try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);			
			stmt.setString(1, credenciais.getLogin());
			stmt.setString(2, credenciais.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			stmt.execute();
			
			if(rs.next())
			{
				stmt.close();
				return true;
			}
			else
			{
				stmt.close();
				return false;
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
}
