package Pacote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import Pacote.jdbc.ConnectionFactory;
import Pacote.model.Empresa;
import Pacote.dao.EnderecoDAO;
public class EmpresaDAO {
		Connection connection;
		
		public EmpresaDAO() throws ClassNotFoundException {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public Empresa PegarEmpresa(String Empresa_ID) {
			String Query = "SELECT * FROM Empresa WHERE Id=?";
			Empresa empresa = new Empresa();
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, Empresa_ID);
				ResultSet Resultado = stmt.executeQuery();
				while(Resultado.next()) {
					empresa.setId(TestNULL(Resultado,"Empresa.ID"));
					empresa.setNomefantasia(TestNULL(Resultado,"Empresa.Nome_Fantasia"));
					empresa.setRazaosocial(TestNULL(Resultado,"Empresa.Razao_Social"));
					empresa.setEmail(TestNULL(Resultado,"Empresa.Email"));
					empresa.setTelefone(TestNULL(Resultado,"Empresa.Telefone"));
					empresa.setCnpj(TestNULL(Resultado,"Empresa.CNPJ"));
					empresa.setidend(TestNULL(Resultado,"Empresa.Endereco_ID"));
				}
				return empresa;
			}catch(SQLException e) {
				System.out.println(e);
				return empresa;
			}
		}
		
		public void Alterar(Empresa empresa) {
			String Query = "UPDATE Empresa SET Nome_Fantasia =? , Razao_Social =? , CNPJ =? , Email =? , Telefone =? WHERE Id=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, empresa.getNomefantasia());
				stmt.setString(2, empresa.getRazaosocial());
				stmt.setString(3, empresa.getCnpj());
				stmt.setString(4, empresa.getEmail());
				stmt.setString(5, empresa.getTelefone());
				stmt.setString(6, empresa.getId());
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		public void CriarEmpresa(Empresa empresa, String CredencialID) throws ClassNotFoundException {
			String Query = "INSERT INTO Empresa(Credenciais_ID,Nome_Fantasia,Razao_Social,CNPJ,Endereco_ID,Email,Telefone)"
					+ " VALUES(?,?,?,?,?,?,?)";
			try {
				PreparedStatement stmt = connection.prepareStatement(Query);
				stmt.setString(1, CredencialID);
				stmt.setString(2, empresa.getNomefantasia());
				stmt.setString(3, empresa.getRazaosocial());
				stmt.setString(4, empresa.getCnpj());
				String EndID = new EnderecoDAO().Criar(empresa.getEndereco());
				stmt.setString(5, EndID);
				stmt.setString(6, empresa.getEmail());
				stmt.setString(7, empresa.getTelefone());
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
