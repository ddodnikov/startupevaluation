<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="editprofile.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>	
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="col-md-2"></div>
					<div class="col-md-7">
						<h2><spring:message code="editprofile.title"/></h2>
						<c:url value="/editprofile" var="link"></c:url>
						<form:form method="POST" action="${link}" modelAttribute="userdto" class="form-horizontal" role="form">
						    <!-- Email -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="email"><spring:message code="editprofile.email"/>:</label>
							    <div class="col-sm-8">
							    	<label  class="control-label" >${sessionScope.currentUser.email}</label>
							    </div>
						    </div>
						    
						    <!-- Name -->
						    <div class="form-group">
							    <label class="control-label col-sm-3" for="name"><spring:message code="editprofile.name"/>:</label>
							    <div class="col-sm-8">
							    	<form:input path="name" value="${name}" class="form-control" id="name" placeholder="Enter name"></form:input>
							    </div>
						    </div>
							
						    <!-- Password -->
						    <div class="form-group">
						    	<label class="control-label col-sm-3" for="pwd"><spring:message code="editprofile.password"/>:</label>
						    	<div class="col-sm-8">
						    		<form:input path="password" name="password" type="password" class="form-control" id="pwd" placeholder="Enter new password"></form:input>
						    	</div>
						    </div>
						    
						    <!-- Confirm password  -->
						     <div class="form-group">
						    	<label class="control-label col-sm-3" for="pwd"><spring:message code="editprofile.password-confirmation"/>:</label>
						    	<div class="col-sm-8">
						    		<form:password path="confirmationPassword" name="confirm-password" class="form-control" id="pwd" placeholder="Enter password"></form:password>
						    	</div>
						    </div>
						    
						    <!-- Submit  -->
						    <div class="form-group">
						    	<div class="col-sm-offset-3 col-sm-8">
						    		<button type="submit" class="btn btn-default" style="width:100%"><spring:message code="editprofile.submit"/></button>
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