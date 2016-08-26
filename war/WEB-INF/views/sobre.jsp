<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.sobre.projeto" var="label_sobre_projeto" htmlEscape="false" />
	<spring:message code="label.sobre" var="label_sobre" htmlEscape="false" />
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_sobre_projeto} <small> ${label_sobre}</small></h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span1"></div>
		
		<div class="span10">
			<c:forEach items="${sobre}" var="s">
				<spring:message code="${s}" var="label_s" htmlEscape="false" />
				<p>${label_s}</p>
			</c:forEach>
		</div>
	</div>

</div>