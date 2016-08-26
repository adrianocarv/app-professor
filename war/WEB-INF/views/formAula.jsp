<%-- 
     Fragmento com o formulario de preenchimento com os dados da aula.
     Utilizado pela pagina de inclusao e edicao de aula.
     O formulario de aulas utiliza o plugin Validation do JQuery, para validar os inputs.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.aula" var="label_aula" htmlEscape="false" />
<spring:message code="label.aula.data" var="label_aula_data" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="aula" class="form-horizontal" id="frmAula">
	<input type="hidden" name="id" value="${aula.id}" />
	<fieldset>
   		<legend><h3>${label_mercadoria} <small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">${label_aula_data}</label>
    		<div class="controls">
    			<form:input path="data" class="input-large" />
    			<form:errors path="data" cssClass="alert alert-error" />
    		</div>
   		</div>
   	   <table>
   	   <c:forEach items="${alunos}" var="a">
		<tr>
			<td>${a.key.nome}</td>
			<td> <input type="checkbox" name="presChecks" value="${a.key.id}" ${a.value?"checked":""}/> </td>
		</tr>
	   </c:forEach>
	   </table>
   	</fieldset>
</form:form>


<div class="control-group form-horizontal">
	<div class="controls">
		<button id="salvar" class="btn btn-success">${button_salvar}</button>
		<a href="/"><button class="btn">${button_cancelar}</button></a>
		<c:if test="${not empty param.enableRemove}">
			<button id="excluir" class="btn btn-danger">${button_excluir}</button>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function () {
 	$("#frmAula").validate({
 		 	rules: {
 	 		 	data: { required: true }
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmAula").submit(); });
});
</script>