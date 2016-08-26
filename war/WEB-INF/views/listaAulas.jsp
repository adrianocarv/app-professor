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
		<h4>${turma.disciplina} - ${turma.semestre} </h4>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Aluno</th>
				<c:forEach items="${aulas}" var="au">
			      <th> <a href="/aula/edita/${au.id}?form"> ${au.dataFormatada} </a> </th>
			    </c:forEach>
			</tr>
		</thead>
		<c:forEach items="${tabelaPresenca}" var="p">
		<tr>
			<td>${p.key.nome}</td>
			<c:forEach items="${p.value}" var="pres">
			  <td>${pres}</td>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
</div>