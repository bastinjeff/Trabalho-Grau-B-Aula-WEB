<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema Cadastro</title>
</head>
<body>
	<c:import url="/WEB-INF/views/principal/menu.jsp"/>
	<br />
	Criar Empresa	
	<br />
	<br />
	
	<form action="criaEmpresa" method="post" class="form-horizontal">
					
	<label for="NomeFantasia">Nome Fantasia</label>  
	<input type="text" name="nomefantasia"><br/> 
	
	<label for="RazaoSocial">Razao Social</label>
	<input type="text" name="razaosocial"><br/>
	
	<label for="Email">Email (Login)</label>
	<input type="text" name="email"><br/>
	
	<label for="Senha">Senha</label>
	<input type="text" name="senha"><br/>
	
	<label for="Telefone">Telefone</label>
	<input type="text" name="telefone"><br/>
	
	<label for="CNPJ">CNPJ</label>
	<input type="text" name="cnpj"><br/>
	
	<label for="Rua">Rua</label>
	<input type="text" name="endereco.rua"><br/>
	
	<label for="Numero">Numero</label>
	<input type="text" name="endereco.numero"><br/>
	
	<label for=Bairro">Bairro</label>
	<input type="text" name="endereco.bairro"><br/>
	
	<label for="CEP">CEP</label>
	<input type="text" name="endereco.cep"><br/>
	
	<label for="Cidade">Cidade</label>
	<input type="text" name="endereco.cidadeuf.cidade"><br/>
	
	<label for="UF">UF</label>
	<input type="text" name="endereco.cidadeuf.uf"><br/>
	
	<input type="submit" value="Alterar">
	
	</form>
	
</body>
</html>