<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h2>Detalhes do Colaborador</h2>
		
		<table class="table table-sm table-striped table-bordered">
			<tr>
				<td>Nome:</td>
				<td>${colaborador.nome}</td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td>${colaborador.email}</td>
			</tr>
		</table>
		
		<table class="table table-sm table-striped table-bordered">
			<tr>
				<td colspan="4" class="bg-primary text-center">Histórico de Projetos</td>
			</tr>	
						
			<tr>
				<th>Projeto</th>
				<th>Status</th>
				<th>Data Início</th>
				<th>Data Término</th>				
			</tr>
			
			<c:forEach var="projeto" items="${colaborador.projetoList}">
				<tr>
					<td>${projeto.titulo}</td>
					<td>${projeto.status}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataInicio}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataTermino}" /></td>
				</tr>
			</c:forEach>
		</table>		
		
		<table class="table table-sm table-striped table-bordered">		
			<tr>
				<td colspan="3" class="bg-primary text-center">Produção Acadêmica - Publicações</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty colaborador.publicacaoList}">
					<tr>
						<th>Título</th>
						<th>Conferência</th>
						<th>Ano</th>
					</tr>
					
					<c:forEach var="publicacao" items="${colaborador.publicacaoList}">
						<tr>
							<td>${publicacao.titulo}</td>
							<td>${publicacao.conferencia}</td>
							<td>${publicacao.ano}</td>
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<tr><td>Não há publicações para este colaborador.</td></tr>
				</c:otherwise>
			</c:choose>
			
			<tr>
				<td colspan="3" class="bg-primary text-center">Produção Acadêmica - Orientações</td>
			</tr>
			
			<c:choose>
				<c:when test="${not isProfessor and not empty colaborador.orientacao}">
					<tr>
						<th>Título</th>
						<th colspan="2">Professor</th>
					</tr>
					
					<tr>
						<td>${colaborador.orientacao.titulo}</td>
						<td colspan="2">${colaborador.orientacao.professor.nome}</td>
					</tr>
				</c:when>
				<c:when test="${isProfessor and not empty colaborador.orientacaoList}">
					<tr>
						<th>Título</th>
						<th colspan="2">Aluno</th>
					</tr>
					
					<c:forEach var="orientacao" items="${colaborador.orientacaoList}">
						<tr>
							<td>${orientacao.titulo}</td>
							<td colspan="2">${orientacao.aluno.nome}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td>Não há orientações para este colaborador.</td></tr>
				</c:otherwise>
			</c:choose>
			
		</table>
		
		<a href="${pageContext.request.contextPath}/colaborador" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
