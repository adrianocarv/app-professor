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
	<spring:message code="button.atualizar" var="button_atualizar" htmlEscape="false" />
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_mercadorias} <small> ${label_listagem}</small></h3>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>${label_mercadoria_nome}</th>
				<th>${label_mercadoria_descricao}</th>
				<th>${label_mercadoria_quantidade}</th>
				<th>${label_mercadoria_preco}</th>
			</tr>
		</thead>
		<c:forEach items="${mercadorias}" var="m">
		<tr>
			<td>${m.id}</td>
			<td>
				<spring:url value="/${m.id}" var="edit_url" htmlEscape="true">
					<spring:param name="form"></spring:param>
				</spring:url>
				<a href="${edit_url}" title="${label_editar} ${m.nome}">${m.nome}</a>
			</td>
			<td>${m.descricao}</td>
			<td>${m.quantidade}</td>
			<td><fmt:formatNumber value="${m.preco}" type="currency"/></td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizaMercadoria" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   			<button id="salvar" class="btn btn-success">${button_atualizar}</button>
	   		</div>
	   	</div>
	</form:form>
</div>