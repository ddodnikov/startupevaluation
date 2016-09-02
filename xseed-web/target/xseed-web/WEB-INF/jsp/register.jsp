<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="register.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/index.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>	
		<jsp:include page="index_header.jsp"></jsp:include>		
		<div class="row content">											
			<div class="col-md-12 register-form">
				<div class="container">
					<div class="col-md-2"></div>
					<div class="col-md-7">
						<h2><spring:message code="register.title"/></h2>
						<c:url value="/register" var="link"></c:url>
						<form:form method="POST" action="${link}"  modelAttribute="userdto" class="form-horizontal" role="form">
						    <!-- Email -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="email"><spring:message code="register.email"/>:</label>
							    <div class="col-sm-8">
							    	<form:input path="email" value="${email}" class="form-control" id="email" placeholder="Enter email"></form:input>
							    </div>
						    </div>
						    <!-- Name -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="email"><spring:message code="register.name"/>:</label>
							    <div class="col-sm-8">
							    	<form:input path="name" value="${name}" class="form-control" id="name" placeholder="Enter name"></form:input>
							    </div>
						    </div>
						    <!-- Password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-3" for="pwd"><spring:message code="register.password"/>:</label>
						    	<div class="col-sm-8">
						    		<form:password path="password" name="password" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    
						    <!-- Confirm password  -->
						     <div class="form-group">
						    	<label class="control-label col-sm-3" for="pwd"><spring:message code="register.password-confirmation"/>:</label>
						    	<div class="col-sm-8">
						    		<form:password path="confirmationPassword" name="confirmationPassword" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    
						     <!-- Startup -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="startup-name"><spring:message code="register.startup-name"/>:</label>
							    <div class="col-sm-8">
							    	<form:input path="startup.name" value="${startup.name}" class="form-control" id="startup-name" placeholder="Enter startup name"></form:input>
							    </div>
						    </div>
						    
						    <!-- Submit -->
						    <div class="form-group">
						    	<div class="col-sm-offset-3 col-sm-8">
						    		<button type="submit" class="btn btn-default" style="width:100%"><spring:message code="register.submit"/></button>
						    	</div>
						    </div>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form:form>

						<!-- FORM no dto-->
						<%-- <form method="POST" action="${link}" class="form-horizontal" role="form">
						    <!-- Email -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="email"><spring:message code="register.email"/>:</label>
							    <div class="col-sm-8">
							    <input name="email" value="${email}" class="form-control" id="email" placeholder="Enter email"/>
							    <form:input path="email" value="${email}" class="form-control" id="email" placeholder="Enter email"></form:input>
							    </div>
						    </div>
						    <!-- Name -->
						    <div class="form-group">
							    <label class="control-label col-sm-4" for="email"><spring:message code="register.name"/>:</label>
							    <div class="col-sm-8">
							    	<input name="name" value="${name}" class="form-control" id="name" placeholder="Enter name">
							    	<form:input path="name" value="${name}" class="form-control" id="name" placeholder="Enter name"></form:input>
							    </div>
						    </div>
						    <!-- Password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-4" for="pwd"><spring:message code="register.password"/>:</label>
						    	<div class="col-sm-8">
						    		<input type="password" name="password" class="form-control" id="pwd" placeholder="Enter password"/>
						    		<form:password path="password" name="password" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    
						    <!-- Confirm password  -->
						     <div class="form-group">
						    	<label class="control-label col-sm-4" for="pwd"><spring:message code="register.password-confirmation"/>:</label>
						    	<div class="col-sm-8">
						    		<input type="password" name="confirmationPassword" class="form-control" id="pwd" placeholder="Enter password"/>
						    	</div>
						    </div>
						    
						     <!-- Startup -->
						    <div class="form-group">
							    <label class="control-label col-sm-4" for="startup-name"><spring:message code="register.startup-name"/>:</label>
							    <div class="col-sm-8">
							    	<input name="startupName" value="${startupName}" class="form-control" id="startup-name" placeholder="Enter startup name"/>							    	<form:input path="startup.name" value="${startup.name}" class="form-control" id="startup-name" placeholder="Enter startup name"></form:input>							    	
							    </div>
						    </div>
						    
						    <!-- Submit -->
						    <div class="form-group">
						    	<div class="col-sm-offset-4 col-sm-8">
						    		<button type="submit" class="btn btn-default"><spring:message code="register.submit"/></button>
						    	</div>
						    </div>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>--%>
						
						
						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-4 link leftlink">
								<a href="<c:url value="/login"></c:url>"><spring:message code="index.login"/></a>
							</div>
							<div class="col-md-4 link rightlink">
								<a href="<c:url value="/reset-password"></c:url>"><spring:message code="index.forgotten-password"/></a>
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>