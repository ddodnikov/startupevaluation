<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="admins.admins-title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/startup.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="col-md-3"></div>
					<div class="col-md-6">
					
						<!-- Admin Tabs -->
						<div>
							<div id="tabs" style="display: inline-block;">
								<ul class="nav nav-pills" style="align:center">
									<!-- Show admins -->
								  	<li class="active"><a href="<c:url value="/admins"></c:url>" class="tablinks"><spring:message code="admins.admins-message"/></a></li>
								  	
								  	<!-- Add admin -->
								  	<li><a href="<c:url value="/addadmin"></c:url>" class="tablinks"><spring:message code="admins.addadmin-message"/></a></li>
								</ul>
							</div>
						</div>
					
						<h2><spring:message code="admins.admins-title" /></h2>
						
						<!-- Show admins -->
						<div class="row">
							<table class="table table-default">
								<thead>
									<tr>
										<th><spring:message code="admins.admins-name"/></th>
										<th><spring:message code="admins.admins-email"/></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${admins}" var="admin">
									<tr>
										<td><c:out value="${admin.name}"></c:out></td>
										<td><c:out value="${admin.email}"></c:out></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>				
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>			
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>