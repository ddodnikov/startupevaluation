<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="calculation-details.title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		<style>
			.container{
				padding:0px;
			}		
			div.col-md-12 .row{
				padding:20px 25px;	
			}			
			@media only screen and (max-device-width: 1200px) {
				.content{
						width:1200px;
				}	
				.header{
					width:1200px;
				}
				.navbar{
					width:1200px;
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
						<h3><spring:message code="calculation-details.title" /></h3>
						<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
							<c:if test="${noResultsFound == null}">
								<table class="table table-default">
									<thead>
										<tr>
											<th><spring:message code="calculation-details.name" /></th>
											<th><spring:message code="calculation-details.date" /></th>
											<th><spring:message code="calculation-details.user" /></th>
											<th><spring:message code="calculation-details.number" /></th>
											<th><spring:message code="calculation-details.question" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><span>${calculation.name}</span></td>
											<td><span>${calculation.date}</span></td>
											<td><span>${sessionScope.currentUser.role} - ${sessionScope.currentUser.name}</span></td>
											<td><span>${calculation.numberOfStartups}</span></td>
											<td><span>$${calculation.kpi.text}</span></td>
										</tr>
										<tr>
											<table class="table">
												<tr>
													<td>T:<span>${calculation.t}%</span></td>
													<td>Tknown:<span>${calculation.tk}%</span></td>
													<td>Tknown-straight:<span>${calculation.tks}%</span></td>
													<td>Tknown-interaction:<span>${calculation.tki}%</span></td>
													<td>Tunknown:<span>${calculation.tu}%</span></td>
												</tr>
											</table>
										</tr>
									</tbody>
								</table>
							</c:if>
							<c:if test="${noResultsFound!=null}">
								<p class="red-font">${noResultsFound}</p>
							</c:if>
						</c:if>
					</div>
					<div class="row">
						<h3><spring:message code="calculation-details.calculations" /></h3>
						<table class="table table-default">
							<thead>
								<tr>
									<th><spring:message code="calculation-details.startup" /></th>
									<th><spring:message code="calculation-details.kpi" /></th>
									<th>A = <spring:message code="calculation-details.kpi" /> / &#x2211;<spring:message code="calculation-details.kpi" /></th>
									<th>B = A * &#x2211;n</th>
									<th>C = ln(B)</th>
									<th>D = A * C</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${startupCalculations}" var="startupCalculation">			
									<tr class="tdlinktosurveyz">			
										<td>${startupCalculation.startupName}</td>
										<td>${startupCalculation.kpi}</td>
										<td>${startupCalculation.formula_a}</td>
										<td>${startupCalculation.formula_b}</td>
										<td>${startupCalculation.formula_c}</td>
										<td>${startupCalculation.formula_d}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="row">
						<h3><spring:message code="calculation-details.group" /></h3>
						<table class="table table-default">
							<thead>
								<tr class="tdlinktosurveyz">
									<th><spring:message code="calculation-details.number" /></th>
									<th>A = &#x2211;(<spring:message code="calculation-details.kpi" /> / &#x2211;<spring:message code="calculation-details.kpi" />)</th>
									<th>B = &#x2211;ni / &#x2211;n</th>
									<th>C = B / A</th>
									<th>D = if(C=0;0;ln(C))</th>
									<th>E = A * D</th>
									<th>F = E / &#x2211;n</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groupCalculations}" var="groupCalculation">			
									<tr class="tdlinktosurveyz">			
										<td>${groupCalculation.numberOfStartups}</td>
										<td>${groupCalculation.formula_a}</td>
										<td>${groupCalculation.formula_b}</td>
										<td>${groupCalculation.formula_c}</td>
										<td>${groupCalculation.formula_d}</td>
										<td>${groupCalculation.formula_e}</td>
										<td>${groupCalculation.formula_f}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
						
					<div class="row">
						<h3><spring:message code="calculation-details.question" /></h3>
						<c:forEach items="${questionCalculations}" var="list">
							<div class="row">
								<table class="table table-default">
									<thead>
										<tr>
											<th><spring:message code="calculation-details.name" /></th>
											<th><spring:message code="calculation-details.average" /></th>
											<c:forEach items="${list.get(0).answers}" var="option">
												<th>if ${option.text}</th>
											</c:forEach>
											<c:forEach items="${list.get(0).answers}" var="option2">
												<th>${option2.text}</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="questionCalculation">
											<tr class="tdlinktosurveyz">
												<td>
													${questionCalculation.question.text}
												</td>
												<td>
													${questionCalculation.average}%
												</td>
												<c:forEach items="${questionCalculation.specifics}" var="option3">
													<td>${option3}</td>
												</c:forEach>
												<c:forEach items="${questionCalculation.formulas}" var="option4">
													<td>${option4}%</td>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:forEach>
						<h1>${noResultsFound}</h1>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>	
	</body>
</html>