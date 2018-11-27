<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style><%@ include file="../css/estilo.css"%></style>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Onibusca</title>
  <link rel="stylesheet" type="text/css" href="../css/estilo.css">
  <link href="https://fonts.googleapis.com/css?family=Roboto:100,100italic,300,300italic,italic,500,500italic,700,700italic,900,900italic,400|Roboto+Slab:100,300,700,400|Cabin:italic,500,500italic,600,600italic,700,700italic,400|Anton:400|Righteous:400|Black+Han+Sans:400|Fredoka+One:400|Russo+One:400|Titan+One:400|Black+Ops+One:400|Baloo:400|Lilita+One:400|Candal:400|Secular+One:400|Baloo+Bhaijaan:400|Baloo+Tamma:400"
  rel="stylesheet" type="text/css">
</head>

<body class="htmlNoPages">
  <div class="class-header" id="header">
    <img src="assets/logo_site_alpha.png" class="class-logo" id="logo">
    <ul class="class-entrar-cadastrar">
      <li class=""><a href="#">Entrar</a>
        <ul class="">
          <li><a href="#">Cliente</a>
          </li>
          <li><a href="#">Empresa</a>
          </li>
        </ul>
      </li>
      <li class=""><a href="#">Cadastrar</a>
        <ul class="">
          <li><a href="#">Cliente</a>
          </li>
          <li><a href="#">Empresa</a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
  <form class="class-form-consulta" id="form_consulta" action="efetuarConsulta">
    <input class="class-input class-input-origem" type="text" name="origem" placeholder="origem" id="input_origem">
    <input class="class-input class-input-destino" type="text" name="destino" placeholder="destino" id="input_destino">
    <input class="class-input class-input-ida" type="text" name="ida" placeholder="ida" id="input_ida">
    <input class="class-input class-input-volta" type="text" name="volta" placeholder="volta (opcional)" id="input_volta">
    <input type="submit" value="Consultar" class="class-submit-consultar" id="submit_consultar">
  </form>
  <div class="class-footer"></div>
</body>

</html>