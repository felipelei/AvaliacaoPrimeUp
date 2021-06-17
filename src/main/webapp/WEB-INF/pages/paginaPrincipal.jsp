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
		<h2>Navega��o</h2>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		
		<ul class="list-group">
			<li class="list-group-item">
				<a href="${pageContext.request.contextPath}/projeto">
					Projetos de Pesquisa
				</a>
			</li>
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/colaborador">
					Colaboradores </a></li>

			<li class="list-group-item">
				<label>Produ��es Acad�micas</label>
				<ul>
					<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/orientacao">
					Orienta��o </a></li>
					<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/publicacao">
					Publica��o </a></li>
				</ul>
			</li>			
					
			<li class="list-group-item"><a
				href="${pageContext.request.contextPath}/producaoAcademica/homeRelatorioProducaoAcademica">
					Relat�rio de Produ��o Acad�mica</a></li>
		</ul>
	</div>
</body>

</html>
