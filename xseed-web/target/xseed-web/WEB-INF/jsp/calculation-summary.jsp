<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
		<title><spring:message code="calculation-summary.title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href='css/navigation.css' rel="stylesheet" type="text/css"/>
		<link href='css/main.css' rel="stylesheet" type="text/css"/>
		<link href='css/inner-page.css' rel="stylesheet" type="text/css"/>
		
		<style>
			div.col-md-12 .row{
				padding:20px 25px;	
			}
		</style>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="row">
						<h3><spring:message code="calculation-summary.title" /></h3>
						<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
							<c:if test="${noResultsFound == null}">
								<table class="table table-default">
									<thead>
										<tr>
											<th><spring:message code="calculation-summary.name" /></th>
											<th><spring:message code="calculation-summary.date" /></th>
											<th><spring:message code="calculation-summary.user" /></th>
											<th><spring:message code="calculation-summary.number" /></th>
											<th><spring:message code="calculation-summary.questions" /></th>
											<th>T</th>
											<th>Tknown</th>
											<th>Tknown-straight</th>
											<th>Tknown-interaction</th>
											<th>Tunknown</th>
											<th><spring:message code="calculation-summary.details" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><span>${calculation.name}</span></td>
											<td><span>${calculation.date}</span></td>
											<td><span>${sessionScope.currentUser.role} - ${sessionScope.currentUser.name}</span></td>
											<td><span>${calculation.numberOfStartups}</span></td>
											<td><span>$${calculation.kpi.text}</span></td>
											<td><span>${calculation.t}%</span></td>
											<td><span>${calculation.tk}%</span></td>
											<td><span>${calculation.tks}%</span></td>
											<td><span>${calculation.tki}%</span></td>
											<td><span>${calculation.tu}%</span></td>
											<td class="font-blue">
												<a href="calculation-details?calculationid=${calculation.id}">Details</a>
											</td>
										</tr>
									</tbody>
								</table>
							</c:if>
							<c:if test="${noResultsFound!=null}">
								<p class="red-font">${noResultsFound}</p>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>