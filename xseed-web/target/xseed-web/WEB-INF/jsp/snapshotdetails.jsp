<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="snapshotdetails.title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		<style>	
			@media only screen and (max-device-width: 400px) {
				.content, .header, .navbar{
					width:400px;
				}
			}
		</style>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12 ">
				<div class="row snapshot-title">
					<h2><spring:message code="snapshotdetails.form-title" /></h2>
					
					<!-- Snapshot name -->
					<div class="col-md-4">
						<span>
							<spring:message code="snapshotdetails.name" />:
						</span> 
						<span>
							<c:out value="${snapshot.name}"></c:out>
						</span>
					</div>
					
					<!-- Snapshot date -->
					<div class="col-md-4">
						<span>
							<spring:message code="snapshotdetails.date" />: 
						</span>
						<span>
							<c:out	value="${snapshot.date}"></c:out>
						</span>
					</div>
					
					<!-- Snapshot user -->
					<div class="col-md-4">
						<span>
							<spring:message code="snapshotdetails.user" />: 
						</span>
						<span>
							<c:out	value="${snapshot.user.name}"></c:out>
						</span>
					</div>
				</div>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th><spring:message code="snapshotdetails.questionNumber" /></th>
								<th><spring:message code="snapshotdetails.question" /></th>
								<th><spring:message code="snapshotdetails.answer" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${snapshot.snapshotlines}" var="line" varStatus="loop">
								<tr id="${loop.index}">
									<td><span> ${loop.index + 1} </span></td>
									<td><c:out value="${line.question.text}"></c:out></td>
									<td>
										<c:if test="${line.textResponse!=null && !line.question.hasOther}">
											<c:out value="${line.textResponse}"></c:out>
										</c:if>
		
										<c:if test="${line.selected_answer!=null}">
											<c:choose>
												<c:when test="${line.question.hasOther}">
													<c:choose>
														<c:when test="${line.textResponse != null}">
															<c:out value="${line.textResponse}"></c:out>
														</c:when>
														<c:otherwise>
															<c:out value="${line.selected_answer.text}"></c:out>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:out value="${line.selected_answer.text}"></c:out>
												</c:otherwise>
											</c:choose>	
										</c:if>
		
										<c:if test="${line.multipleSelectedAnswers!=null}">
											<span> 
												<script>
													var array = new Array();
												
													<c:forEach items="${line.multipleSelectedAnswers}" var="answer" varStatus="innerLoop">
														array[${innerLoop.index}] = '<c:out value="${answer.text}"></c:out>';
													</c:forEach>
													
													<c:if test="${answer.text!='Other'}"> 
														<c:if test="${line.question.hasOther && line.textResponse!=null}">
															array.push('<c:out value="${line.textResponse}"></c:out>');
														</c:if>
													</c:if>
													var index = array.indexOf("Other");
													if(index!=-1)
														array.splice(index, 1);
													array = array.join("/");
													console.log(array);
													console.log(document.getElementById('${loop.index}').childNodes[5])
													document.getElementById('${loop.index}').childNodes[5].innerHTML = array;
												</script>
											</span>
										</c:if>
									</td>
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