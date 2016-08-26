<%-- Pagina define o template da aplicacao, no Tiles. --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		
		<spring:url value="/resources/css/bootstrap.min.css" var="bootstrap_url" />
		<spring:url value="/resources/css/springmvc_gae.css" var="css_url" />
		<spring:url value="/resources/js/jquery-1.7.2.min.js" var="jquery_url" />
		<spring:url value="/resources/js/jquery.validate.min.js" var="jquery_validate_url" />
		
		<link rel="stylesheet" type="text/css" media="screen" href="${bootstrap_url}"><!-- required for FF3 and Opera --></link>
		<link rel="stylesheet" type="text/css" media="screen" href="${css_url}"><!-- required for FF3 and Opera --></link>
		<script type="text/javascript" src="${jquery_url}"></script>
		<script type="text/javascript" src="${jquery_validate_url}"></script>
		
		<spring:message code="page.title.app" var="page_title_app" htmlEscape="false" />
		<title>${page_title_app}</title>
	</head>

  	<body>
  		<div class="header">
    		<div class="container">
    			<tiles:insertAttribute name="header" ignore="true" />
    		</div>
    	</div>
  		
  		<div class="container">
	   		<div id="wrapper" class="row-fluid show-grid">
			    <div class="sidebar">
			    	<div class="well span2">
			    		<tiles:insertAttribute name="menu" ignore="true" />
			    	</div>
			    </div>
			    <div class="span8">
		    		<tiles:insertAttribute name="body"/>
		    		
		    		<footer>
			    		<tiles:insertAttribute name="footer" ignore="true"/>
			    	</footer>
			    </div>
			</div>
		</div>
	</body>
</html>
