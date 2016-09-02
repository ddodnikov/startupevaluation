<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="forgotten-password.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>		
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/index.css"></c:url>" rel="stylesheet" type="text/css"/>
		
	</head>
	<body>
		<jsp:include page="index_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12 reset-form">
				<div class="container">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h2><spring:message code="forgotten-password.title"/></h2>
						<form method="POST" action="<c:url value="/reset-password"></c:url>" class="form-horizontal" role="form">
						
							<!-- Email -->
						    <div class="form-group">
							    <label class="control-label col-sm-2" for="email"><spring:message code="forgotten-password.email"/>:</label>
							    <div class="col-sm-8">
							    	<input name="email" type="email" class="form-control" id="email" placeholder="Enter email">
							    </div>
						    </div>
						   
						   <!-- Submit -->
						    <div class="form-group">
						    	<div class="col-sm-offset-2 col-sm-8">
						    		<button type="submit" class="btn btn-default"><spring:message code="forgotten-password.submit"/></button>
						    	</div>
						    </div>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
						
						<c:if test="${success!=null}">
							<p class="blue-font"><c:out value="${success}"></c:out></p>
						</c:if>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<c:if test="${email}">
			<p>${email}</p>
		</c:if>
	</body>
</html>