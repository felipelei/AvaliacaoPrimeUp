<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

	<head>
		<meta charset="utf-8">
		
		<title>Avaliação PrimeUP</title>
		
		<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/resources/jquery/css/cmxform.css" media="screen" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	</head>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#formOrientacao").validate({
				rules: {
					professorId: {required: true},
					alunoId: {required: true},
					titulo: {required: true}
				},
				messages: {
					professorId: {required: "Escolha o orientador."},
					alunoId: {required: "Escolha o aluno."},
					titulo: {required: "Preencha o título."}
				}
			});
			
			// insert handler
			if($("#acao").val() == 'insert') 
			{
				$("#formOrientacao :input").prop("disabled", false);
				
				$("#btnConfirmar, #btnCancelar").prop('disabled', false);
			    $("#btnEditar").prop('disabled', true);
			    
			    $("#formOrientacao").prop('action', "${pageContext.request.contextPath}/orientacao/save");
			}
			
			// edit event
		    $("#btnEditar").click(function(){
		    	$("#titulo, #professor").prop("disabled", false);
		        $("#btnConfirmar, #btnCancelar").prop('disabled', false);
		        $(this).prop('disabled', true);
		        
		        $("#acao").val('edit');
		    });
		    
			// confirm event
		    $('#btnConfirmar').click(function(e) {
		        e.preventDefault();
		        
		        if($("#formOrientacao").valid())
		        {
		        	$("#formOrientacao :input").prop("disabled", false);
			        $('#formOrientacao').submit();	
		        }
		    });
		});
	</script>
	
<body>
	<div class="container">
		<h2>Detalhes da Orientação</h2>
		
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<c:if test="${not empty msgAcao}">
			<div class="alert alert-info">${msgAcao}</div>
		</c:if>
		
		<form id="formOrientacao"  method="POST" action="${pageContext.request.contextPath}/orientacao/${orientacao.id}/update">
			<input type="hidden" id="acao" name="acao" value="${acao}"/>
			
			<table class="table table-sm table-striped table-bordered">
				<tr>
					<td><label >Título:</label></td>
					<td colspan="3"><input name="titulo" id="titulo" value="${orientacao.titulo}" disabled="disabled" size="70" required="required"/></td>
				</tr>	
				<tr>
					<td><label >Aluno:</label></td>
					<td>
						<select name="alunoId" disabled="disabled" required="required">
							<c:if test="${empty orientacao.aluno}">
								<option value="">-Selecione-</option>
								
								<c:forEach items="${alunosSemOrientacao}" var="itemAluno">
							      	<option value="${itemAluno.id}">${itemAluno}</option>
						    	</c:forEach>
							</c:if>
							
							<c:if test="${not empty orientacao.aluno}">
								<option value="${orientacao.aluno.id}" selected="selected">${orientacao.aluno}</option>
							</c:if>
						</select>
					</td>
					
					<td><label>Orientador:</label></td>
					<td>		
						<select name="professorId" id="professor" disabled="disabled" required="required">
							<c:if test="${empty orientacao.professor}">
								<option value="">-Selecione-</option>
							</c:if>
							
							<c:if test="${not empty orientacao.professor}">
								<option value="${orientacao.professor.id}" selected="selected">${orientacao.professor}</option>
							</c:if>
							
						    <c:forEach items="${professores}" var="itemProfessor">
						        <c:if test="${itemProfessor ne orientacao.professor}">
						            <option value="${itemProfessor.id}">${itemProfessor}</option>
						        </c:if>
						    </c:forEach>
						</select>
					</td>
				</tr>
			</table>
			
			<input id="btnEditar" class="btn btn-primary" name="editar" type="button" value="Editar" />
			<input id="btnConfirmar" disabled="disabled" class="btn btn-primary" type="submit" name="confirmar" value="Confirmar" />
			<input id="btnCancelar" disabled="disabled" class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath}/orientacao/${orientacao.id}'" 
					name="cancelar" value="Cancelar" />
			
		</form>
		
		<a href="${pageContext.request.contextPath}/orientacao" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
