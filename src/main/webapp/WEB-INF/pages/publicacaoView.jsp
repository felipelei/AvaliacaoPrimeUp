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
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery.mask.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	</head>
	
	<script type="text/javascript">
		$(document).ready(function() {
			// mask
			$("#ano").mask('9999');
			
			$("#formPublicacao").validate({
				rules: {
					conferencia: {required: true},
					ano: {min: 1, minlength: 4},
					titulo: {required: true}
				},
				messages: {
					conferencia: {required: "Preencha a conferência."},
					ano: {min: "Ano inválido.", minlength: "Ano inválido."},
					titulo: {required: "Preencha o título."}
				}
			});
			
			// insert handler
			if($("#acao").val() == 'insert') 
			{
				$("#formPublicacao :input").prop("disabled", false);
				
				$("#btnConfirmar, #btnCancelar").prop('disabled', false);
			    $("#btnEditar").prop('disabled', true);
			    
			    $("#formPublicacao").prop('action', "${pageContext.request.contextPath}/publicacao/save");
			}
			
		    $("#btnEditar").click(function(){
		    	$("#formPublicacao :input").prop("disabled", false);
		        $("#btnConfirmar, #btnCancelar").prop('disabled', false);
		        $(this).prop('disabled', true);
		        
		        $("#acao").val('edit');
		    });
		    
		    $('#btnConfirmar').click(function(e) {
		        e.preventDefault();
		        
		        if($("#formPublicacao").valid())
		        {
		        	$("#formPublicacao :input").prop("disabled", false);
			        $('#formPublicacao').submit();	
		        }
		    });
		});
	</script>
	
<body>
	<div class="container">
		<h2>Detalhes da Publicação</h2>
		
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<c:if test="${not empty msgAcao}">
			<div class="alert alert-info">${msgAcao}</div>
		</c:if>
		
		<form:form id="formPublicacao"  method="POST" action="${pageContext.request.contextPath}/publicacao/${publicacao.id}/update" modelAttribute="publicacao">
			<input type="hidden" id="acao" name="acao" value="${acao}"/>
			
			<table class="table table-sm table-striped table-bordered">
				<tr>
					<td><form:label path="titulo">Título:</form:label></td>
					<td colspan="3"><form:input required="required" disabled="true" size="70" path="titulo"></form:input></td>
				</tr>	
				<tr>
					<td><form:label path="conferencia">Conferência:</form:label></td>
					<td><form:input required="required" disabled="true" path="conferencia"/></td>
					
					<td><form:label path="ano">Ano:</form:label></td>
					<td><form:input required="required" disabled="true" path="ano"/></td>
				</tr>
				<tr>
					<td><form:label path="projeto">Projeto Vinculado:</form:label></td>
					<td colspan="3">
						<form:select disabled="true" path="projeto.id">
							<form:option value="" label="Nenhum"/>
							<c:if test="${not empty publicacao.projeto.id}">
								<form:option value="${publicacao.projeto.id}" label="${publicacao.projeto.titulo}"/>
							</c:if>
							<form:options items="${projetoEmAndamento}" itemValue="id" itemLabel="titulo"/>
						</form:select>
					</td>
				</tr>
			</table>
			
			<input id="btnEditar" class="btn btn-primary" name="editar" type="button" value="Editar" />
			<input id="btnConfirmar" disabled="disabled" class="btn btn-primary" type="submit" name="confirmar" value="Confirmar" />
			<input id="btnCancelar" disabled="disabled" class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath}/publicacao/${publicacao.id}'" name="cancelar" value="Cancelar" />
			
		</form:form>
		
		
		<table class="table table-sm table-striped table-bordered">		
			<tr>
				<td colspan="3" class="bg-primary">
					<input ${empty publicacao.id ? 'disabled="disabled"' : ''} type="button" id="btnAddColaborador" value="Adicionar Colaborador" data-toggle="modal" data-target="#dialog-colaborador" class="btn btn-success"/>
					<label>Colaboradores Participantes</label>
				</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty publicacao.colaboradorList}">
					<c:forEach var="colaborador" items="${publicacao.colaboradorList}">
						<tr>
							<td colspan="3">${colaborador}</td>
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<tr><td>Não há colaboradores participantes desta publicacação.</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		
		<!-- dialog colaborador -->
		<div class="modal fade" id="dialog-colaborador" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Adicionando colaborador</h4>
		        </div>
		        
		        <form id="addColaborador" method="POST" action="${pageContext.request.contextPath}/publicacao/${publicacao.id}/addColaborador">
			        <div class="modal-body">
			          <label>Colaborador:</label>
			        
				      <select required="required" name="colaboradorId">
			         		<option value="">-Selecione-</option>
			         		<c:forEach var="item" items="${colaboradorNaoVinculadoComPublicacao}">
				                <option value="${item.id}">${item}</option>
				              </c:forEach>
			          </select>
			        </div>
			        <div class="modal-footer">
			          <button type="submit" class="btn btn-default">Confirmar</button>
			          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			        </div>
		        </form>
		      </div>
		    </div>
		</div>
		
		
		<a href="${pageContext.request.contextPath}/publicacao" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
