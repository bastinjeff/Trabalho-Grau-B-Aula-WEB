package Pacote.jdbc;
import java.sql.*;
public class ConnectionFactory {
	
	public Connection getConnection() throws ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
        try {
        	return DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10266061", "sql10266061", "dy5LUbjVyv");
        }catch(SQLException e){
        	System.out.println("Erro de conexao");
			throw new RuntimeException(e+"\nNAO FOI POSSIVEL CONECTAR NO BANCO DE DADOS");
		}
	}
}

