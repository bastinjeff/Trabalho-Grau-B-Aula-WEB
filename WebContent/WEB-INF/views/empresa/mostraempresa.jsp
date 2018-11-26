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
	Alterar Empresa: ${empresa.id}	
	<br />
	<br />
	
	<form action="alteraEmpresa" method="post" class="form-horizontal">
	
	<input type="hidden" name="id" value="${empresa.id}" />
				
	<label for="NomeFantasia">Nome Fantasia</label>  
	<input type="text" name="nomefantasia" value="${empresa.nomefantasia}"><br/> 
	
	<label for="RazaoSocial">Razao Social</label>
	<input type="text" name="razaosocial" value="${empresa.razaosocial}"><br/>
	
	<label for="Email">Email</label>
	<input type="text" name="email" value="${empresa.email}"><br/>
	
	<label for="Telefone">Telefone</label>
	<input type="text" name="telefone" value="${empresa.telefone}"><br/>
	
	<label for="CNPJ">CNPJ</label>
	<input type="text" name="cnpj" value="${empresa.cnpj}"><br/>
	
	<input type="submit" value="Alterar">
	
	</form>
	
</body>
</html>