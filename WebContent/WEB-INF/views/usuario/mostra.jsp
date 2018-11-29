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
	Alterar usuario: ${usuario.id}	
	<br />
	<br />
	
	<form action="alteraUsuario" method="post" class="form-horizontal">
	
	<input type="hidden" name="id" value="${usuario.id}" />
				
	<label for="Nome">Nome</label>  
	<input type="text" name="nome" value="${usuario.nome}" > 
	<form:errors path="usuario.nome" cssStyle="color:#B71C1C"/><br />
	
	<label for="Email">Email</label>
	<input type="text" name="email" value="${usuario.email}"><br/>
	
	<label for="CPF">CPF</label>
	<input type="text" name="cpf" value="${usuario.cpf}">
	
	<input type="submit" value="Alterar">	
	</form>
	<label>Favoritos</label>
	<table border="1">
		<tr>
			<th>Empresa</th>
			<th>Cidade Saida</th>
			<th>Cidade Chegada</th>
			<th>Hora</th>
			<th>Preço</th>
			<th>Modalidade</th>
			<th>Frequencia</th>
		</tr>
		<c:forEach items="${viagens}" var="viagem">
		<tr>
			<td>${viagem.nomeempresa}</td>
			<td>${viagem.cidadesaida} / ${viagem.ufsaida}</td>
			<td>${viagem.cidadechegada} / ${viagem.ufchegada}</td>
			<td>${viagem.hora}</td>
			<td>R$ ${viagem.preco}</td>
			<td>${viagem.modalidade}</td>
			<td>${viagem.diassemana}</td>
			<td><a href="deletaPassagem?id=${viagem.id}" title="Deletar">Deletar</a></td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>