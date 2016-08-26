<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<spring:message code="label.mercadorias" var="label_mercadorias" htmlEscape="false" />
	<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
	<spring:message code="menu.incluir" var="menu_incluir" htmlEscape="false" />
	<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />
	
	<ul class="nav nav-list" style="padding-top: 15px;">
		<li><em>${label_mercadorias}</em></li>
		
		<li class="${empty active || active == 'lista' ? 'active' : ''}">
			<a href="/">${menu_lista}</a>
		</li>
		<li class="${active == 'incluir' ? 'active' : ''}">
			<a href="/?form">${menu_incluir}</a>
        </li>
		<li class="divider"></li>
		<li class="${active == 'sobre' ? 'active' : ''}">
			<a href="/sobre">${menu_sobre}</a>
		</li>
	</ul>
</div>
