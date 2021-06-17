<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

	<head>
		<meta charset="utf-8">
		
		<title>Avalia��o PrimeUP</title>
		
		<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	</head>
	
<body>
	<div class="container">
		<h2>Relat�rio de Produ��o Acad�mica</h2>
		
		<table class="table table-sm table-striped table-bordered">
		  <thead>
		    <tr>
		      <th rowspan="2" class="text-center" scope="col">N�mero de colaboradores</th>
		      <th rowspan="2" class="text-center" scope="col">N�mero de projetos em elabora��o</th>
		      <th rowspan="2" class="text-center" scope="col">N�mero de projetos em andamento</th>
		      <th rowspan="2" class="text-center" scope="col">N�mero de projetos conclu�dos</th>
		      <th rowspan="2" class="text-center" scope="col">N�mero total de projetos</th>
		      <th colspan="2" class="text-center" scope="col">N�mero de produ��o acad�mica por tipo de produ��o</th>
		    </tr>
		    <tr>
		    	<th class="text-center" scope="col">Publica��o</th>
		    	<th class="text-center" scope="col">Orienta��o</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr class="text-right">
		      <td >${relatorio.totalColaborador}</td>
		      <td >${relatorio.totalProjetoEmElaboracao}</td>
		      <td >${relatorio.totalProjetoEmAndamento}</td>
		      <td >${relatorio.totalProjetoConcluido}</td>
		      <td >${relatorio.totalProjeto}</td>
		      <td >${relatorio.totalPublicacao}</td>
		      <td >${relatorio.totalOrientacao}</td>
		    </tr>
		  </tbody>
		</table>
		
		
		<a href="${pageContext.request.contextPath}/" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
