<%-- Pagina de inclusao de aula. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<spring:message code="label.incluir" var="label_incluir" htmlEscape="false" />

	<c:import url="/WEB-INF/views/formAula.jsp">
		<c:param name="method" value="POST" />
		<c:param name="sublabel" value="${label_incluir}" />
	</c:import>
</div>