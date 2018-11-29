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
	
	<form action="mostraCatalogofiltrado" method="post">
		<table>
			<tr>
				<th>
					<label>Cidade de saida</label>
				</th>
				<th>
					<label>Cidade de chegada</label>
				</th>
			</tr>
			<tr>
				<th>
					<select name="cidadesaida">
						<c:forEach items="${cidades}" var="cidade">
							<option value="${cidade}">${cidade}</option>
						</c:forEach>
					</select>			
				</th>
				<th>
					<select name="cidadechegada">
						<c:forEach items="${cidades}" var="cidade">
							<option value="${cidade}">${cidade}</option>
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