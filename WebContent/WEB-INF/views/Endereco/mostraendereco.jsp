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
	Alterar Endereco: ${endereco.id}	
	<br />
	<br />
	
	<form action="alteraEndereco" method="post" class="form-horizontal">
	
	<input type="hidden" name="id" value="${endereco.id}" />
				
	<label for="Rua">Rua</label>  
	<input type="text" name="rua" value="${endereco.rua}"><br/> 
	
	<label for="Numero">Numero</label>
	<input type="text" name="numero" value="${endereco.numero}"><br/>
	
	<label for="Bairro">Bairro</label>
	<input type="text" name="bairro" value="${endereco.bairro}"><br/>
	
	<label for="CEP">CEP</label>
	<input type="text" name="cep" value="${endereco.cep}"><br/>
	
	<label for="Cidade">Cidade</label>
	<input type="text" name="cidadeuf.cidade" value="${endereco.cidadeuf.cidade}"><br/>
	
	<label for="UF">UF</label>
	<input type="text" name="cidadeuf.uf" value="${endereco.cidadeuf.uf}"><br/>		
	
	<input type="submit" value="Alterar">
	
	</form>
	
</body>
</html>