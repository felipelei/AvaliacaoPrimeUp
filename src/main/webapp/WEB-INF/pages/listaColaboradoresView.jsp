<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

	<head>
		<meta charset="utf-8">
		
		<title>Avaliação PrimeUP</title>
		
		<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	</head>
	
<body>
	<div class="container">
		<h2>Colaboradores</h2>
		
		<ul class="list-group">
			<c:forEach items="${colaboradorList}" var="colaborador">
				<li class="list-group-item"> 
					<a href="${pageContext.request.contextPath}/colaborador/${colaborador.id}">
						${colaborador}
					</a>
					
				</li>	
			</c:forEach>	
		</ul>
		
		<a href="${pageContext.request.contextPath}/" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
