<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="editstartup.title"/></title>
		
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
				
					<!-- Tabs -->
					<div id="tabs" style="display: inline-block;">
						<ul class="nav nav-pills" style="align:center">
						  <li class="active"><a href="editstartup" class="tablinks"><spring:message code="editstartup.edit-link"/></a></li>
						  <li><a href="add-user-to-startup" class="tablinks"><spring:message code="editstartup.add-link"/></a></li>
						  <li><a href="showusers" class="tablinks"><spring:message code="editstartup.show-link"/></a></li>
						</ul>
					</div>
					
					<!-- Form -->
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-7">
							<h2><spring:message code="editstartup.form-title"/></h2>
							<c:url value="/editstartup" var="link"></c:url>
							<form:form method="POST" action="${link}"  modelAttribute="startupdto" class="form-horizontal" role="form">
							    
							    <!-- Name -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="name"><spring:message code="editstartup.name"/>:</label>
								    <div class="col-sm-8">
								    	<form:input path="name" value="${name}" class="form-control" id="name" placeholder="Enter name"></form:input>
								    </div>
							    </div>
							    
							    
							    <!-- Phone -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="phone"><spring:message code="editstartup.phone"/>:</label>
								    <div class="col-sm-8">
								    	<form:input path="phone" value="${phone}" class="form-control" id="phone" placeholder="Enter phone"></form:input>
								    </div>
							    </div>
							    
							    <!-- Email -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="email"><spring:message code="editstartup.email"/>:</label>
								    <div class="col-sm-8">
								    	<form:input path="email" value="${email}" class="form-control" id="email" placeholder="Enter email"></form:input>
								    </div>
							    </div>
							    
							    <!-- Website -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="website"><spring:message code="editstartup.website"/>:</label>
								    <div class="col-sm-8">
								    	<form:input path="website" value="${website}" class="form-control" id="website" placeholder="Enter website"></form:input>
								    </div>
							    </div>
							    
							    <!-- VAT -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="vat"><spring:message code="editstartup.vat"/>:</label>
								    <div class="col-sm-8">
								    	<form:input path="vat" value="${vat}" class="form-control" id="vat" placeholder="Enter vat"></form:input>
								    </div>
							    </div>
							    <!-- Country -->
							    <div class="form-group">
								    <label class="control-label col-sm-3" for="country"><spring:message code="editstartup.country"/>:</label>
								    <div class="col-sm-8">
								    <form:select class="form-control" path="country.name" id="country">
								 	 	<form:option value="0" label="Select" />
										<form:options  items="${countries}" itemValue="name" itemLabel="name"></form:options>
									</form:select>
									</div>
								</div>
							    
							    <div class="form-group">
							    	<div class="col-sm-offset-3 col-sm-8">
							    		<button type="submit" class="btn btn-default" style="width:100%"><spring:message code="editstartup.submit"/></button>
							    	</div>
							    </div>
							    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</form:form>	
						</div>
						<div class="col-md-3"></div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>