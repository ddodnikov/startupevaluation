<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="showallstartups.title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>		
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		
		<style>	
			div.col-md-12 .row{
				padding:20px 25px;	
			}
			@media only screen and (max-device-width: 645px) {
				.content, .header, .navbar{
					width:645px;
				}
			}
		</style>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12 no-top-margin">
				<div class="row">
					<h2 class="left"><spring:message code="showallstartups.form-title" /></h2>
					<table class="table table-default">
						<thead>
							<tr>
								<th><spring:message code="showallstartups.name" /></th>
								<th><spring:message code="showallstartups.email" /></th>
								<th><spring:message code="showallstartups.website" /></th>
								<th><spring:message code="showallstartups.vat" /></th>
								<th><spring:message code="showallstartups.phone" /></th>
								<th><spring:message code="showallstartups.country" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${startups}" var="startup">	
								<tr>
									<td>${startup.name}</td>
									<td class="blue-font">${startup.email}</td>
									<td class="blue-font">${startup.website}</td>
									<td>${startup.vat}</td>
									<td>${startup.phone}</td>
									<td>${startup.country.name}</td>	
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>	
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>