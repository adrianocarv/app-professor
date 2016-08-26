<%-- Fragmento com trecho utilizado no cabecalho das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<spring:url var="banner" value="/resources/img/logo_yaw.png" />
	<div class="logo">
		<img src="${banner}" />
	</div>
	
	<spring:message code="app.title" var="app_title" htmlEscape="false" />
	<h1 class="title">${app_title}</h1>
</div>
