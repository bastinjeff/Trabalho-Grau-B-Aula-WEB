<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catalogo</title>
</head>
<body>
<c:import url="/WEB-INF/views/principal/menu.jsp"/>

<form action="criarNovaviagem" method="post">
<input type="hidden" name="empresa.id" value="${empresa.id}">

<table border="1">
		<tr>
			<th>
				<label>Dias da Semana</label><br/>
				<input type="checkbox" name="diassemana" value="Segunda">Segunda<br/>
				<input type="checkbox" name="diassemana" value="Terça">Terça<br/>
				<input type="checkbox" name="diassemana" value="Quarta">Quarta<br/>
				<input type="checkbox" name="diassemana" value="Quinta">Quinta<br/>
				<input type="checkbox" name="diassemana" value="Sexta">Sexta<br/>
				<input type="checkbox" name="diassemana" value="Sabado">Sabado<br/>
				<input type="checkbox" name="diassemana" value="Domingo">Domingo<br/>
			</th>
			<th>
				<label>Modalidade</label><br/>
				<input type="radio" name="modalidade" value="Direto">Direto<br/>
				<input type="radio" name="modalidade" value="Semi-Direto">Semi-Direto<br/>
				<input type="radio" name="modalidade" value="Executivo">Executivo<br/>
				<input type="radio" name="modalidade" value="Leito">Leito<br/>
				<input type="radio" name="modalidade" value="Comum">Comum<br/>
			</th>
		</tr>
		<tr>
			<th>
				<label>Hora</label>
				<input type="time" name="hora">
			</th>
			<th>
				<label>Preço (em R$)</label>
				<input type="text" name="preco">
			</th>
		</tr>
		<tr>
			<th>
				<label>Cidade Saida</label><br/>
				<input type="text" name="rota.cidadesaida">
				<select name="rota.ufsaida">
					<c:forEach items="${UFs}" var="uf">
						<option value="${uf}">${uf}</option>
					</c:forEach>
				</select>
			</th>
			<th>
				<label>Cidade Chegada</label><br/>
				<input type="text" name="rota.cidadechegada">
				<select name="rota.ufchegada">
					<c:forEach items="${UFs}" var="uf">
						<option value="${uf}">${uf}</option>
					</c:forEach>
				</select>	
			</th>
		</tr>
		<tr>
				<th colspan="2">
						<input type="submit" value="Entrar">
				</th>
			</tr>
	</table>
</form>

</body>
</html>