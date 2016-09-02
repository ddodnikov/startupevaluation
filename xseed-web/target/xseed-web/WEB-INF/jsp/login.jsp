<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		 
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/index.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="index_header.jsp"></jsp:include>	
		<div class="row content">											
			<div class="col-md-12 login-form">
				<div class="container">
					<div class="col-md-2"></div>
					<div class="col-md-7">
						<h2><spring:message code="login.title"/></h2>
						<form method="POST" action="<c:url value="/login"></c:url>" class="form-horizontal" role="form">
						
							<!-- User name -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="email"><spring:message code="login.email"/>:</label>
							    <div class="col-sm-8">
							    	<input name="username" type="email" class="form-control" id="email" placeholder="Enter email">
							    </div>
						    </div>
						    
						    <!-- User password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-3" for="pwd"><spring:message code="login.password"/>:</label>
						    	<div class="col-sm-8">
						    		<input name="password" type="password" class="form-control" id="pwd" placeholder="Enter password">
						    	</div>
						    </div>
						    
						    <!-- Submit -->
						    <div class="form-group">
						    	<div class="col-sm-offset-3 col-sm-8">
						    		<button type="submit" class="btn btn-default" style="width:100%"><spring:message code="login.submit"/></button>
						    	</div>
						    </div>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
						<!-- Register & Reset password -->
						<div class="row">
							<div class="col-sm-3"></div>
							<div class="col-sm-4 link leftlink">
								<a href="<c:url value="/register"></c:url>"><spring:message code="index.register"/></a>
							</div>
							<div class="col-sm-4 link rightlink">
								<a href="<c:url value="/reset-password"></c:url>"><spring:message code="index.forgotten-password"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
	    <c:if test="${param.status=='error'}">
	     	<label class="red-font"><spring:message code="login.statuserror"/>!!</label>
	    </c:if>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>