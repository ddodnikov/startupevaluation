<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="admin.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/index.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="index_header.jsp"></jsp:include>	
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h2><spring:message code="admin.activate"/></h2>
						<c:url value="/updatenewuser" var="link"></c:url>
						<form:form method="POST" action="${link}"  modelAttribute="userdto" class="form-horizontal" role="form"> 
						    <!-- Email -->
						    <div class="form-group">
							    <label class="control-label col-sm-4" for="email"><spring:message code="admin.email"/>:</label>
							    <div class="col-sm-8">
							    	<label  class="control-label" >${userdto.email}</label>
							    </div>
						    </div>
						    
						    <form:hidden path="email" ></form:hidden>
							<form:hidden path="role" ></form:hidden>
						    
						    <!-- Name -->
						    <div class="form-group">
							    <label class="control-label col-sm-4" for="name"><spring:message code="admin.name"/>:</label>
							    <div class="col-sm-8">
							    	<form:input path="name" value="${name}" class="form-control" id="name" placeholder="Enter name"></form:input>
							    </div>
						    </div>
						    
						    <!-- Password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-4" for="pwd"><spring:message code="admin.password"/>:</label>
						    	<div class="col-sm-8">
						    		<form:password path="password" name="password" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    
						    <!-- Confirm password  -->
						     <div class="form-group">
						    	<label class="control-label col-sm-4" for="pwd"><spring:message code="admin.password-confirmation"/>:</label>
						    	<div class="col-sm-8">
						    		<form:password path="confirmationPassword" name="confirm-password" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    					    
						    <!-- Submit -->
						    <div class="form-group">
						    	<div class="col-sm-offset-2 col-sm-8">
						    		<button type="submit" class="btn btn-default"><spring:message code="admin.submit"/></button>
						    	</div>
						    </div>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form:form>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>	
		<jsp:include page="footer.jsp"></jsp:include>		
	</body>
</html>