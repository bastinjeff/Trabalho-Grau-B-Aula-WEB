package Pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


import Pacote.jdbc.ConnectionFactory;
import Pacote.model.Usuario;
import Pacote.model.Credenciais;

public class UsuarioDAO {
		Connection connection;
		
		public UsuarioDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
	
		public Usuario PegarUsuario(String Usuario_ID) {
			Usuario usuario = new Usuario();
				try {
					PreparedStatement stmt = this.connection.prepareStatement(QueryPegar("Where Id = " + Usuario_ID));
					ResultSet Resultado = stmt.executeQuery();
					while(Resultado.next()) {
						usuario.setNome(TestNULL(Resultado,"Usuario.nome"));
						usuario.setEmail(TestNULL(Resultado,"Usuario.Email"));
						usuario.setCpf(TestNULL(Resultado,"Usuario.CPF"));
						usuario.setid(Usuario_ID);
					}
					stmt.close();
					Resultado.close();
					return usuario;					
				}catch(SQLException e) {
					return usuario;
				}
			
		}
		
		public void Alterar(Usuario usuario) {
			String Query = "UPDATE Usuario SET Nome =? , Email=? , CPF=? , Telefone=? WHERE Id=?";
			try {				
				PreparedStatement stmt = this.connection.prepareStatement(Query);
				stmt.setString(1,usuario.getNome());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getCpf());
				stmt.setString(4, usuario.getTelefone());
				stmt.setString(5, usuario.getid());
				System.out.println(Query);
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				
			}
		}
		
		public void CriarUsuario(Usuario usuario, String Credencial_ID) {
			String Query = "INSERT INTO Usuario(Nome,Email,CPF,Telefone,Credenciais_ID) "
					+ "VALUES (?,?,?,?,?)";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getCpf());
				stmt.setString(4, usuario.getTelefone());
				stmt.setString(5,Credencial_ID);
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);				
			}
		}
		
		public void DeletaUsuario(Usuario usuario) {
			String Query = "DELETE FROM Usuario WHERE Id=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, usuario.getid());
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		public void AdicionaPassagem(String Viagem_ID, String Usuario_ID) {
			String Query = "INSERT INTO Passagens(Usuario_ID,Viagem_ID) VALUES(?,?)";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, Usuario_ID);
				stmt.setString(2, Viagem_ID);
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		public void DeletaPassagem(String Viagem_ID,String Usuario_ID) {
			String Query = "DELETE FROM Passagens WHERE Viagem_ID=? AND Usuario_ID=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, Viagem_ID);
				stmt.setString(2, Usuario_ID);
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

		
		private String QueryPegar(String where) {
			String StringComando = "SELECT *";
			StringComando+=" FROM Usuario";
			StringComando+=" " + where;
			return StringComando;
		}

}
