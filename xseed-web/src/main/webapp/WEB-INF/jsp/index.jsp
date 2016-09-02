<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="index.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/index.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>	
		<jsp:include page="index_header.jsp"></jsp:include>		
		<div class="row content">											
			<div class="col-md-12">
				<div class="row">
				
					<div class="col-md-4">
						<img src="<c:url value="/img/ICO_CompanyUsers.png"></c:url>"/>
						<p id="ico-names"><spring:message code="index.startup-title"/></p>
						<p><spring:message code="index.startup-message"/></p>
						<a href="<c:url value="/login"></c:url>"><spring:message code="index.login"/>/<spring:message code="index.register"/></a>
					</div>
					
					<div class="col-md-4">
						<img src="<c:url value="/img/ICO_questionnarie.png"></c:url>"/>
						<p id="ico-names"><spring:message code="index.questionarie-title"/></p>
						<p><spring:message code="index.questionarie-message"/></p>
					</div>
					
					<div class="col-md-4">
						<img src="<c:url value="/img/ICO_Reports.png"></c:url>"/>
						<p id="ico-names"><spring:message code="index.report-title"/></p>
						<p><spring:message code="index.report-message"/></p>
					</div>
				</div>
			</div>
		</div>		
		<jsp:include page="footer.jsp"></jsp:include>	
	</body>
</html>