package Pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import Pacote.jdbc.ConnectionFactory;
import Pacote.model.Usuario;


public class UsuarioDAO {
		Connection connection;
		
		public UsuarioDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
	
		public Usuario PegarUsuario() {
			Usuario usuario = new Usuario();
				try {
					PreparedStatement stmt = this.connection.prepareStatement(QueryPegar("Where Id = 1"));
					ResultSet Resultado = stmt.executeQuery();
					while(Resultado.next()) {
						usuario.setNome(TestNULL(Resultado,"Usuario.nome"));
						usuario.setEmail(TestNULL(Resultado,"Usuario.Email"));
						usuario.setCpf(TestNULL(Resultado,"Usuario.CPF"));
						usuario.setid("1");
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
