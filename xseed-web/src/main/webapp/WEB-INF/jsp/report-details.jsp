<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Details</title>
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
<style>

	div.col-md-12 .row{
		padding:20px 25px;	
	}
	
	table tr td.question-category{
		background-color:#e4f5e4;
		padding-top:10px;
	}
	
	table tr td.question-category span{
		display:block;
	}
	
	.factor{
		width: 100%;
		display: block;
        overflow-x: auto;
	}
	
	.snapshot-answer{
		width:130px;
	}
	
</style>

</head>
<body>
<jsp:include page="simple_header.jsp"></jsp:include>
	<div class="row content">											
		<div class="col-md-12">
			
			<div class="row">
				<h3>Progress report</h3>
				<c:if test="${empty errors}">
					<table class="table factor">
						<thead>
							<tr>
								<th style="border:0px;"></th>
								<th style="border:0px;"></th>
								<th colspan="2">Target</th>
								<th>Delta</th>
								<c:if test="${months >= 1}">
									<th>Current month</th>
								</c:if>
								<c:if test="${months >=2 }">
									<th>Previous month</th>
								</c:if>
								<c:if test="${months >=3 }">
									<c:forEach  var="i" begin="1" end="${months-2}">
										<th>Previous month-${i}</th>
									</c:forEach>
								</c:if>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="phaseEntry" items="${data}">
							<c:set var="rowspanPhase" value="true"></c:set>
							
							<c:forEach var="groupEntry" items="${phaseEntry.value}">
								<c:set var="rowspanGroup" value="true"></c:set>
								
								<c:forEach var="row" items="${groupEntry.value}">
									<tr>
										<c:if test="${rowspanPhase}">
											<td rowspan="${phaseRows[phaseEntry.key]}"class="question-category">${phaseEntry.key}</td>
											<c:set var="rowspanPhase" value="false"></c:set>
										</c:if>
										
										<c:if test="${rowspanGroup}">
											<td rowspan="${fn:length(groupEntry.value)}"class="question-category">${groupEntry.key}</td>
											<c:set var="rowspanGroup" value="false"></c:set>
										</c:if>
										<td>${row.questionTitle}</td>
										<td>${row.targetValue}</td>
										<td>
											<c:if test="${row.delta}">
												<img src="<c:url value="/img/ICO_Yes.png"></c:url>"/>
											</c:if>
											<c:if test="${!row.delta}">
												<img src="<c:url value="/img/ICO_NO.png"></c:url>"/>
											</c:if>
										</td>
										<c:forEach var="snapshot" items="${row.answers}">
										<td class="snapshot-answer">
											<span style="margin-right:20px;">${snapshot.answer}</span>
											<span>${snapshot.value}</span>
										</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:forEach>					
						</tbody>
				
					</table>
				</c:if>				
			</div>
			</div>
		</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>