<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="calculate.title" /></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/question.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/calculate.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		<sec:csrfMetaTags />
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12">
				<div class="container">

					<div class="row">
						<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
						<c:url value="/calculate" var="link"></c:url>
							<form action="${link}" accept-charset="UTF-8" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="col-md-1"></div>
								
								<!-- CALCULATION NAME -->
								<div class="col-md-10" id="content-1" style="display: block">
								
									<!-- Name field -->
									<div class="form-group">
										<div class="col-sm-1"></div>
										<div class="col-sm-10">
											<h3><spring:message code="calculate.calculation-name" /></h3>
											<input type="text" name="calculationName" id="calculationName" class="form-control" value="${defaultName}" />
										</div>
										<div class="col-sm-1"></div>
									</div>
									
									<!-- NEXT&PREV -->
									<div class="row">
										<div class="col-md-12">
											<div class="prev"><spring:message code="all.prev-button" /></div>
											<div class="next"><spring:message code="all.next-button" /></div>
										</div>
									</div>
									
									<div class="row">
										<!-- Name error -->
										<div id="nameError">
											<label><spring:message code="calculate.name-error" /></label>
										</div>
									</div>
								</div>
								
								<!-- FILTERS -->
								<div class="col-md-10" id="content-2" style="display: none">
									<c:forEach items="${filterQuestions}" var="filterQuestion" varStatus="loop">
										<div class="row ">
											<div id="filterQuestion_${loop.index}" class="form-group page2_${filterQuestion.type}question">
												<p class="question-title">${filterQuestion.text}</p>
												<c:forEach items="${filterQuestion.answers}" var="answer">
													<div class="col-sm-1"></div>
													<div class="col-sm-5 answer">
														<c:if test="${answer.text != 'Other'}">
															<label>
																<input class="page2_multipleanswer form-control" type="checkbox"  name="multiple_${filterQuestion.id}_${answer.id}" value="${answer.text}">
																<span>${answer.text}</span>
															</label>
														</c:if>
													</div>
												</c:forEach>
											</div>
										</div>
									</c:forEach>
									
									<!-- NEXT&PREV -->
									<div class="row">
										<div class="col-md-12">
											<div class="prev"><spring:message code="all.prev-button" /></div>
											<div class="next"><spring:message code="all.next-button" /></div>
										</div>
									</div>
									
									<div class="row">
										<!-- Result error -->
										<div id="resultError">
											<label><spring:message code="calculate.result-error" /></label>
										</div>
										
										<!-- Filter error -->
										<div id="inputError">
											<label><spring:message code="calculate.input-error" /></label>
										</div>
									</div>
								</div>
								
								<!-- KPI & SUBMIT -->
								<div class="col-md-10" id="content-3" style="display: none">
									<div class="form-group">
									<div class="row ">
										<p class="question-title"><spring:message code="all.calculation-kpi" /></p>	
										</div>
										<!-- KPI -->	
										<div class="row ">
										<c:forEach items="${kpiQuestions}" var="kpiQuestion">
											<div class="col-sm-12 kpi-answer">
												<label>
													<input type="radio" class="page3_kpi form-control" name="kpi" value="${kpiQuestion.id}">
													<span>${kpiQuestion.text}</span>
												</label>
											</div>
										</c:forEach>
										</div>
										
										<!-- PREV & SUBMIT -->
										<c:url value="/checkfilter" var="checkfilterlink"></c:url>
										<div class="row">
											<div class="col-md-12">
												<div class="prev"><spring:message code="all.prev-button" /></div>
												<input type="submit" id="submit" class="form-control next" value="<spring:message code="all.submit-button" />"/>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-1"></div>
							</form>
						</c:if>
					</div>									
				</div>
			</div>
		</div>
		<div id="loader"></div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script>
			var checkfilterl = '${checkfilterlink}';
		</script>
		<script src="<c:url value="/js/calculate.js"></c:url>"></script>
		
	</body>
</html>