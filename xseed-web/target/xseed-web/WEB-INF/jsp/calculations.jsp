<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="calculations.title" /></title>
	
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href='css/navigation.css' rel="stylesheet" type="text/css"/>
		<link href='css/main.css' rel="stylesheet" type="text/css"/>
		<link href='css/inner-page.css' rel="stylesheet" type="text/css"/>
		<link href='css/calculations.css' rel="stylesheet" type="text/css"/>
		
		<style type="text/css">
			.h2 {
				float:left;
				margin-left:1%;
			}
		</style>
	</head>
	<body>	
		<jsp:include page="simple_header.jsp"></jsp:include>				
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">
					<div class="row">	
						<h2 class="h2"><spring:message code="calculations.title" /></h2>
						<div class="next">
							<a href="calculate"><spring:message code="calculations.new" /></a>
						</div>
						<br><br>
		
						<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
							<c:forEach items="${calculations}" var="calculation">
								<div class="row" style="margin-top:20px;">
									<table class="table table-default">
										<thead>
											<tr>
												<th><spring:message code="calculations.name" /></th>
												<th><spring:message code="calculations.date" /></th>
												<th><spring:message code="calculations.user" /></th>
												<th><spring:message code="calculations.number" /></th>
												<th><spring:message code="calculations.details" /></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><span>${calculation.name}</span></td>
												<td><span>${calculation.date}</span></td>
												<td><span>${sessionScope.currentUser.role} - ${sessionScope.currentUser.name}</span></td>
												<td><span>${calculation.numberOfStartups}</span></td>
												<td class="blue-font"><span><a href="calculation-details?calculationid=${calculation.id}"><spring:message code="calculations.details" /></a></span></td>
											</tr>
											<tr>
												<table class="table">
													<tr>
														<td>T:${calculation.t}%</td>
														<td>Tknown : ${calculation.tk}%</td>
														<td>Tknown-straight : ${calculation.tks}%</td>
														<td>Tknown-interaction : ${calculation.tki}%</td>
														<td>Tunknown : ${calculation.tu}%</td>
													</tr>
												</table>
											</tr>
										</tbody>
									</table>
								</div>
							</c:forEach>
						</c:if>				
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>	
	</body>
</html>