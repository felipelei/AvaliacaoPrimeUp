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
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery.maskMoney.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/moment/js/moment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/js/custom-validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	</head>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			// validation
			$("#formProjeto").validate({
				rules: {
					titulo: {required: true},
					dataInicio: {required: true, validarDataBR: true},
					dataTermino: {
								required: true, 
								validarDataBR: true, 
								dataMaiorIgual: function() {
									return $("#dataInicio").val();	
								}
					},
					agenciaFinanciadora: {required: true},
					valorFinanciado: {required: true},
					objetivo: {required: true},
					descricao: {required: true}
				},
				messages: {
					titulo: {required: "Preencha o título."},
					dataInicio: {required: "Preencha a data de início."},
					dataTermino: {required: "Preencha a data de término."},
					objetivo: {required: "Preencha o objetivo."},
					descricao: {required: "Preencha a descrição."},
					agenciaFinanciadora: {required: "Preencha a agência financiadora."},
					valorFinanciado : {required: "Preencha o valor financiado."},
				}
			});
			
			// masks 
			$("#dataInicio, #dataTermino").mask('99/99/9999');
			$("#valorFinanciado").maskMoney({prefix: 'R$ ', allowNegative: false, thousands:'.', decimal:','});
			
			// insert handler
			if($("#acao").val() == 'insert') 
			{
				$("#formProjeto :input").prop("disabled", false);
				
				$("#btnConfirmar, #btnCancelar").prop('disabled', false);
			    $("#btnEditar").prop('disabled', true);
			    
			    $("#formProjeto").prop('action', "${pageContext.request.contextPath}/projeto/save");
			}
			
			// edit event
		    $("#btnEditar").click(function(){
		        $("#status, #btnConfirmar, #btnCancelar").prop('disabled', false);
		        $(this).prop('disabled', true);
		        
		        $("#acao").val('edit');
		    });
		    
			// submit handler
		    $('#btnConfirmar').click(function(e) {
		        e.preventDefault();
		        
		        if($("#formProjeto").valid()) {
		        	$("#formProjeto :input").prop("disabled", false);
		        	$("#valorFinanciado").val($("#valorFinanciado").maskMoney('unmasked')[0]);
			        $('#formProjeto').submit();	
		        }
		    });
		});
	</script>
	
<body>
	<div class="container">
		<h2>Detalhes do Projeto</h2>
		
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<c:if test="${not empty msgAcao}">
			<div class="alert alert-info">${msgAcao}</div>
		</c:if>
		
		<form:form id="formProjeto"  method="POST" action="${pageContext.request.contextPath}/projeto/${projeto.id}/update" modelAttribute="projeto">
			<input type="hidden" id="acao" name="acao" value="${acao}"/>
			
			<table class="table table-sm table-striped table-bordered">
				<tr>
					<td><form:label path="titulo">Título:</form:label></td>
					<td><form:input disabled="true" size="70" path="titulo"></form:input></td>
					
					<td><form:label path="status">Status:</form:label></td>
					<td>
						<form:select disabled="true" path="status">
							<form:option value="${projeto.status}"/>
							<c:if test="${not empty projeto.status.proximo and acao ne 'insert' }">
								<form:option value="${projeto.status.proximo}"/>
							</c:if>
						</form:select>
					</td>
				</tr>
				<tr>
					<td><form:label path="dataInicio">Data Início:</form:label></td>
					<fmt:formatDate value="${projeto.dataInicio}" var="dataInicioFmt" pattern="dd/MM/yyyy" />
					<td><form:input maxlength="10" disabled="true" path="dataInicio" value="${dataInicioFmt}"></form:input></td>
					
					<td><form:label path="dataTermino">Data Término:</form:label></td>
					
					<fmt:formatDate value="${projeto.dataTermino}" var="dataTerminoFmt" pattern="dd/MM/yyyy" />
					<td><form:input maxlength="10" disabled="true" path="dataTermino" value="${dataTerminoFmt}"></form:input></td>
				</tr>
				<tr>
					<td><form:label path="agenciaFinanciadora">Agência Financiadora:</form:label></td>
					<td><form:input disabled="true" path="agenciaFinanciadora"/></td>
					
					<td><form:label path="valorFinanciado">Valor Financiado:</form:label></td>
					<td><form:input  disabled="true" path="valorFinanciado"/></td>
				</tr>
				<tr>
					<td><form:label path="objetivo">Objetivo:</form:label></td>
					<td colspan="3"><form:textarea rows="3" cols="100" path="objetivo" disabled="true"/></td>
				</tr>
				<tr>
					<td><form:label path="descricao">Descrição:</form:label></td>
					<td colspan="3"><form:textarea rows="3" cols="100"  path="descricao" disabled="true"/></td>
				</tr>
			</table>
			
			<input ${projeto.status eq 'CONCLUIDO' ? 'disabled="disabled"' : ''}  id="btnEditar" class="btn btn-primary" name="editar" type="button" value="Editar" />
			<input id="btnConfirmar" disabled="disabled" class="btn btn-primary" type="submit" name="confirmar" value="Confirmar" />
			<input id="btnCancelar" disabled="disabled" class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath}/projeto/${projeto.id}'" name="cancelar" value="Cancelar" />
			
		</form:form>
		
		
		<table class="table table-sm table-striped table-bordered">		
			<tr>
				<td colspan="3" class="bg-primary">
					<input ${empty projeto.id or projeto.status ne 'EM_ELABORACAO' ? 'disabled="disabled"' : ''} type="button" id="btnAddColaborador" value="Adicionar Colaborador" data-toggle="modal" data-target="#dialog-colaborador" class="btn btn-success"/>
					<label>Colaboradores Alocados</label>
				</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty projeto.colaboradorList}">
					<c:forEach var="colaborador" items="${projeto.colaboradorList}">
						<tr>
							<td colspan="3">${colaborador}</td>
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<tr><td>Não há colaboradores alocados para este projeto.</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		
		<table class="table table-sm table-striped table-bordered">		
			<tr>
				<td colspan="3" class="bg-primary">
					<input ${empty projeto.id or projeto.status ne 'EM_ANDAMENTO' ? 'disabled="disabled"' : ''} type="button" id="btnAddProducao" value="Adicionar Produção" data-toggle="modal" data-target="#dialog-producao" class="btn btn-success"/>
					<label>Produção Acadêmica - Publicações</label>
				</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty projeto.publicacaoList}">
					<tr>
						<th>Título</th>
						<th>Conferência</th>
						<th>Ano</th>
					</tr>
					
					<c:forEach var="publicacao" items="${projeto.publicacaoList}">
						<tr>
							<td>${publicacao.titulo}</td>
							<td>${publicacao.conferencia}</td>
							<td>${publicacao.ano}</td>
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<tr><td>Não há publicações para este projeto.</td></tr>
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
		        
		        <form id="addColaborador" method="POST" action="${pageContext.request.contextPath}/projeto/${projeto.id}/addColaborador">
			        <div class="modal-body">
			          <label>Colaborador:</label>
			        
				      <select required="required" name="colaboradorId">
			         		<option value="">-Selecione-</option>
			         		<c:forEach var="item" items="${colaboradorNaoVinculadoComProjeto}">
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
		
		<!-- dialog producao-->
		<div class="modal fade" id="dialog-producao" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Adicionando Produção Acadêmica</h4>
		        </div>
		        
		        <form id="addProducao" method="POST" action="${pageContext.request.contextPath}/projeto/${projeto.id}/addProducaoAcademica">
			        <div class="modal-body">
			         	<label>Produção Acadêmica: </label>
			         	
			         	<select required="required" name="publicacaoId">
			         		<option value="">-Selecione-</option>
			         		<c:forEach var="item" items="${publicacaoNaoVinculadaComProjeto}">
				                <option value="${item.id}">${item.titulo}</option>
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
		
		<a href="${pageContext.request.contextPath}/projeto" class="btn btn-primary">Voltar</a>
	</div>
</body>

</html>
