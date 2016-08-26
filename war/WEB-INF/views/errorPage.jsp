<%-- Pagina acionada quando um erro na aplicacao (controller) acontece. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="msg.erro.processamento" var="msg_erro_processamento" htmlEscape="false" />
	<spring:message code="msg.erro.descricao" var="msg_erro_descricao" htmlEscape="false" />
	<spring:message code="msg.erro.detalhes" var="msg_erro_detalhes" htmlEscape="false" />
	
	<h2>${msg_erro_processamento}</h2>
    <p>${msg_erro_descricao}</p>
    
    <c:if test="${not empty exception}">
	<div>
		<h4>${msg_erro_detalhes}</h4>
		
		<div class="alert-message error">
			<p><c:out value="${exception.localizedMessage}" /></p>
		</div>
		
		<div id="exception">
		<c:forEach items="${exception.stackTrace}" var="trace">
			<c:out value="${trace}" />
			<br />
		</c:forEach>
		</div>
	</div>
    </c:if>
</div>