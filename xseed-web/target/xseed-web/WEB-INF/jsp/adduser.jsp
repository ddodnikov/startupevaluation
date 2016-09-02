<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="adduser.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/startup.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		<style>	
			@media only screen and (max-device-width: 200px) {
				.header p:first-child{
					font-size:20px;
				}
			}	
		</style>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="row">
						<!-- User Tabs -->
						<div>
							<div id="tabs" style="display: inline-block;">
								<ul class="nav nav-pills" style="align:center">
									<!-- Edit Startup -->
								  	<li><a href="<c:url value="/editstartup"></c:url>" class="tablinks"><spring:message code="editstartup.edit-link"/></a></li>
								  	
								  	<!-- Add user -->
								  	<li class="active"><a href="<c:url value="/add-user-to-startup"></c:url>" class="tablinks"><spring:message code="editstartup.add-link"/></a></li>
								  	
								  	<!-- Show users -->
								  	<li><a href="<c:url value="/showusers"></c:url>" class="tablinks"><spring:message code="editstartup.show-link"/></a></li>
								</ul>
							</div>
						</div>
						
						<!-- Add user form -->
						<div class="col-md-2"></div>
						<div class="col-md-7">
							<h2><spring:message code="adduser.form-title"/></h2>
							<form method="POST" action="<c:url value="/add-user-to-startup"></c:url>" class="form-horizontal" role="form">
							    
							    <!-- Email -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="email"><spring:message code="adduser.email"/>:</label>
								    <div class="col-sm-8">
								    	<input id="email" type="email" name="email" class="form-control" id="email" placeholder="Enter email"></input>
								    </div>
							    </div>
							 
							 	<!-- Submit -->
							    <div class="form-group">
							    	<div class="col-sm-offset-3 col-sm-8">
							    		<button type="submit" class="btn btn-default" style="width:100%"><spring:message code="adduser.submit"/></button>
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
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>