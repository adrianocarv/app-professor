<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.mercadorias" var="label_mercadorias" htmlEscape="false" />
	<spring:message code="label.listagem" var="label_listagem" htmlEscape="false" />
	<spring:message code="label.mercadoria.nome" var="label_mercadoria_nome" htmlEscape="false" />
	<spring:message code="label.mercadoria.descricao" var="label_mercadoria_descricao" htmlEscape="false" />
	<spring:message code="label.mercadoria.quantidade" var="label_mercadoria_quantidade" htmlEscape="false" />
	<spring:message code="label.mercadoria.preco" var="label_mercadoria_preco" htmlEscape="false" />
	<spring:message code="label.editar" var="label_editar" htmlEscape="false" />
	<spring:message code="button.nova.aula" var="button_nova_aula" htmlEscape="false" />
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Turma</th>
				<th>Semestre</th>
			</tr>
		</thead>
		<c:forEach items="${turmas}" var="turma">
		<tr>
			<td> <a href="/aula/lista/${turma.id}?form"> ${turma.disciplina} </a> </td>
			<td>${turma.semestre}</td>
			<td>
			  <a href="/aula/${turma.id}?form">
			  <button id="salvar" class="btn btn-success">${button_nova_aula}</button>
			  </a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>