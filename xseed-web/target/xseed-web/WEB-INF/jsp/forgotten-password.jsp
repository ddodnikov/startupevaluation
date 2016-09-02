<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<div class="col-md-12">
				<div class="container">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h2><spring:message code="forgotten-password.form-title"/></h2>
						<form method="POST" action="<c:url value="/forgottenpassword"></c:url>" class="form-horizontal" role="form"> 
						    <input type="hidden" name="email" value='<c:out value="${email}"></c:out>'/>
						    <input type="hidden" name="token" value='<c:out value="${token}"></c:out>'/>				    
						    <!-- Password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-4" for="pwd"><spring:message code="forgotten-password.password"/>:</label>
						    	<div class="col-sm-8">
						    		<input id="pwd" type="password" name="password" class="form-control" placeholder="Enter new password"/>
						    	</div>
						    </div>
						    
						    <!-- Confirm password  -->
						     <div class="form-group">
						    	<label class="control-label col-sm-4" for="confirmationPassword"><spring:message code="forgotten-password.password-confirmation"/>:</label>
						    	<div class="col-sm-8">
						    		<input id="confirmationPassword" type="password" name="confirmationPassword" class="form-control" placeholder="Enter new password again"/>
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
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>		
		<jsp:include page="footer.jsp"></jsp:include>			
	</body>
</html>