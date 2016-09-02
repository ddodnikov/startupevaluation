<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="startsurvey.title" /></title>
	
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css' />
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/question.css"></c:url>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/css/startsurvey.css"></c:url>" rel="stylesheet" type="text/css" />
		<!--[if IE ]>
			<link href="<c:url value="/css/ie.css"></c:url>" rel="stylesheet" type="text/css">
		<![endif]-->

		<style>
			@media screen and (max-device-width: 767px) {
				.answer{
					float:none !important;
				}
				.other{
					float:right;
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
					<c:url value="/submit/survey?id=${currentSurvey.id}" var="link"></c:url>
					<form action="${link}" accept-charset="UTF-8" method="post">

						<!-- JSTL QUESTION COUNTER INIT-->
						<c:set var="questionCounter" value="0"></c:set>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="col-md-1"></div>

						<!-- First page -->
						<div id="content-1" class="col-md-10" style="display: block;">

							<!-- Snapshot name -->
							<div class="form-group row">
									<div class="col-sm-1"></div>
									<div class="col-sm-10">
										<h3>
											<spring:message code="startsurvey.name" />
										</h3>
										<input type="text" name="snapshotName" id="snapshotName"
											class="form-control" value="${snapshotName}" />
									</div>
									<div class="col-sm-1"></div>
							</div>
							<!--Page bar-->
							<div class="row ">
								<div class="col-md-1"></div>
								<div class="col-md-12 page-progress">
									<c:forEach var="i" begin="1"
										end="${surveyQuestionsDTOSNumberOfPages+2}" step="1">
										<div id="page-${i}" class="empty-page page-${i}"></div>
									</c:forEach>
								</div>
								<div class="col-md-1"></div>
							</div>

							<!--PREV / NEXT -->
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-10">
									<div class="prev">
										<spring:message code="startsurvey.previous" />
									</div>
									<div class="next">
										<spring:message code="startsurvey.next" />
									</div>
								</div>
								<div class="col-md-1"></div>
							</div>
							
							<br>
							<div class="row">
								<!-- Snapshot name error -->
								<div id="error-message-page1" style="display: none">
									<p class="red-font">
										<spring:message code="startsurvey.name-error" />
									</p>
								</div>
							</div>
						</div>

						<!-- Question pages -->
						<c:forEach items="${surveyQuestionDtosPaged}" var="surveyQuestionDtos" varStatus="loop">
							<div id="content-${loop.index + 2}" class="col-md-10" style="display: none">

								<c:set var="title" value="${false}"></c:set>
								<c:forEach items="${surveyQuestionDtos}" var="surveyQuestion">
									<div class="page${loop.index + 2}_${surveyQuestion.question.type}question row">
										
										<!-- YES / NO -->
										<c:if test="${surveyQuestion.question.type == 'YES_NO'}">
											<c:if test="${!title}">
												<c:set var="title" value="${true}"></c:set>
												<c:set var="questionCounter" value="${questionCounter+1}"></c:set>
												<p class="question-title">
													<c:out value="${questionCounter}"></c:out>
													.
													<spring:message code="startsurvey.checkboxes" />
												</p>
											</c:if>
											<div class="form-group page${loop.index + 2}_${surveyQuestion.question.type}question">
												<div class="col-sm-10 yes-no">
													<label> 
														<input type="checkbox" value="${surveyQuestion.question.id}" name="yesno_${surveyQuestion.question.id}"> 
														<span></span>
														<div>${surveyQuestion.question.text}</div>
													</label>
												</div>
												<div class="col-sm-2"></div>
											</div>
										</c:if>

										<!-- SINGLE CHOICE -->
										<c:if test="${surveyQuestion.question.type == 'SINGLE_CHOICE'}">
											<div class="form-group">
												<!-- JSTL QUESTION COUNTER INCREMENT -->
												<c:set var="questionCounter" value="${questionCounter+1}"></c:set>

												<!-- MONTHLY REVENUE / BURNRATE -->
												<c:if test="${surveyQuestion.question.answers.get(0).identifier == 'MRG1'}">
													<p class="question-title">
														<c:out value="${questionCounter}"></c:out>
														.${surveyQuestion.question.text}
													</p>
													<div class="col-sm-2"></div>
													<div class="col-sm-8">
														<select class="form-control"
															name="radio_${surveyQuestion.question.id}">
															<c:forEach items="${surveyQuestion.question.answers}"
																var="answer">
																<option value="${answer.id}">${answer.text}</option>
															</c:forEach>
														</select>
														<br>
													</div>
													<div class="col-sm-2"></div>
												</c:if>

												<!-- SIX OPTIONS -->
												<c:if test="${surveyQuestion.question.answers.get(0).identifier == '6OG1'}">
													<table class="table table-default six-options">
														<thead>
															<tr>
																<th colspan="6">
																	<p class="question-title">
																		<c:out value="${questionCounter}"></c:out>
																		.${surveyQuestion.question.text}
																	</p>
																</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<c:forEach items="${surveyQuestion.question.answers}" var="answer">
																	<td>
																		<label for="radio_${surveyQuestion.question.id}_${answer.id}">
																			<span>${answer.text}</span>
																		</label>
																	</td>
																</c:forEach>
															</tr>
															<tr style="background-color: grey;">
																<c:forEach items="${surveyQuestion.question.answers}"
																	var="answer">
																	<td>
																	<label> 
																		<input id="radio_${surveyQuestion.question.id}_${answer.id}" type="radio" class="page${loop.index + 2}_singleanswer" name="radio_${surveyQuestion.question.id}" value="${answer.id}"> 
																			<span class="six-options-button"></span>
																			<div style="display:none">${answer.text}</div>
																	</label></td>
																</c:forEach>
															</tr>
														</tbody>
													</table>
												</c:if>

												<!-- OTHERS -->
												<c:if test="${surveyQuestion.question.answers.get(0).identifier != 'MRG1' and surveyQuestion.question.answers.get(0).identifier != '6OG1'}">
													<p class="question-title">
														<c:out value="${questionCounter}"></c:out>
														.${surveyQuestion.question.text}
													</p>
													<c:forEach items="${surveyQuestion.question.answers}" var="answer">
														<div class="col-sm-1" style="margin-top: 5px;"></div>
														<div class="col-sm-5 answer">
															<label> 
																<input type="radio" class="page${loop.index + 2}_singleanswer" name="radio_${surveyQuestion.question.id}" value="${answer.id}">
																	<span></span>
																	<c:if test="${answer.text!='Other'}">
																		<div>${answer.text}</div>
																	</c:if>
																	<c:if test="${surveyQuestion.question.hasOther && answer.text=='Other'}">
																		<div class="otherAnswer" style="margin-left:0px; display:inline">${answer.text}</div>
																		<input class="other" id="radio_${surveyQuestion.question.id}_other" type="text" name="radio_${surveyQuestion.question.id}_other" />
																		<div class="otherlenghterror_page${loop.index + 2}_question${surveyQuestion.question.id}" style="color: red; display: none">
																			<spring:message code="startsurvey.other-lenght" />
																		</div>
																	</c:if>
															</label>
														</div>
													</c:forEach>
												</c:if>
											</div>
										</c:if>

										<!-- MULTIPLE CHOICE -->
										<c:if test="${surveyQuestion.question.type == 'MULTIPLE_CHOICE'}">
											<!-- JSTL QUESTION COUNTER INCREMENT -->
											<c:set var="questionCounter" value="${questionCounter+1}"></c:set>

											<div class="form-group">
												<p class="question-title">
													<c:out value="${questionCounter}"></c:out>
													.${surveyQuestion.question.text}
												</p>
												<c:forEach items="${surveyQuestion.question.answers}" var="answer">
													<div class="col-sm-1"></div>
													<div class="col-sm-5 answer">
														<label> 
															<input class="page${loop.index + 2}_multipleanswer" type="checkbox" name="multiple_${surveyQuestion.question.id}_${answer.id}" value="${answer.id}"> 
																<span>${answer.text}</span>
																<c:if test="${surveyQuestion.question.hasOther && answer.text=='Other'}">
																	<input class="other" id="multiple_${surveyQuestion.question.id}_other" type="text" name="multiple_${surveyQuestion.question.id}_other" />
																	<div class="otherlenghterror_page${loop.index + 2}_question${surveyQuestion.question.id}" style="color: red; display: none">
																		<spring:message code="startsurvey.other-lenght" />
																	</div>
																</c:if>
														</label>
													</div>
												</c:forEach>
											</div>
										</c:if>

										<!-- TEXT -->
										<c:if test="${surveyQuestion.question.type == 'TEXT'}">
											<!-- JSTL QUESTION COUNTER INCREMENT -->
											<c:set var="questionCounter" value="${questionCounter+1}"></c:set>

											<div class="form-group">
												<p class="question-title">
													<c:out value="${questionCounter}"></c:out>
													.${surveyQuestion.question.text}
												</p>
												<div class="col-sm-2"></div>
												<div class="col-sm-8">
													<input class="form-control page${loop.index + 2}_textanswer" name="text_${surveyQuestion.question.id}" type="text" />
												</div>
												<div class="col-sm-2"></div>
											</div>
										</c:if>
									</div>
								</c:forEach>

								<!--Page bar-->
								<div class="row ">
									<div class="col-md-1"></div>
									<div class="col-md-12 page-progress">
										<c:forEach var="i" begin="1"
											end="${surveyQuestionsDTOSNumberOfPages+2}" step="1">
											<div id="page-${i}" class="empty-page page-${i}"></div>
										</c:forEach>
									</div>
									<div class="col-md-1"></div>
								</div>

								<!--PREV / NEXT -->
								<div class="row">
									<div class="col-md-1"></div>
									<div class="col-md-10">
										<div class="prev">
											<spring:message code="startsurvey.previous" />
										</div>
										<div class="next">
											<spring:message code="startsurvey.next" />
										</div>
										<div class="col-md-1"></div>
									</div>
								</div>
								
								<br>
								<div class="row">
									<!-- Question error -->
									<div class="error-message-page2" style="display: none">
										<p class="red-font">
											<spring:message code="startsurvey.questions-error" />
										</p>
									</div>
								</div>
								
							</div>
						</c:forEach>

						<!-- Submit page -->
						<div id="content-${surveyQuestionsDTOSNumberOfPages+2}"
							class="col-md-10" style="display: none;">
							<!--Ready-->
							<div class="form-group">
								<div class="col-sm-2"></div>
								<div class="col-sm-8">
									<h3>
										<spring:message code="startsurvey.ready" />
									</h3>
								</div>
								<div class="col-sm-2"></div>
							</div>

							<!--Page bar-->
							<div class="row ">
								<div class="col-md-1"></div>
								<div class="col-md-12 page-progress">
									<c:forEach var="i" begin="1"
										end="${surveyQuestionsDTOSNumberOfPages+2}" step="1">
										<div id="page-${i}" class="empty-page page-${i}"></div>
									</c:forEach>
								</div>
								<div class="col-md-1"></div>
							</div>

							<!--PREV / SUBMIT -->
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-10">
									<div class="prev">
										<spring:message code="startsurvey.previous" />
									</div>
									<input type="submit" id="submit" class="form-control next"
										value="<spring:message code="startsurvey.finishsurvey" />" />
								</div>
								<div class="col-md-1"></div>
							</div>
						</div>
						<div class="col-md-1"></div>
					</form>
				</div>
			</div>
		</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script type="text/javascript">
			var maxPage = ${surveyQuestionsDTOSNumberOfPages + 2};
		</script>
		<script src="<c:url value="/js/question_validation.js"></c:url>"></script>
		<script src="<c:url value="/js/startsurvey.js"></c:url>"></script>
		<script>
		var isIE;
		isIE = (false || !!document.documentMode);
		if(isIE){
			var fileref=document.createElement("link");
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", "/xseed-web/css/ie.css");
			document.getElementsByTagName("head")[0].appendChild(fileref);
			var otherAnswer = document.getElementsByClassName("otherAnswer");
			for(var i = 0; i<otherAnswer.length; i++){
				var span = otherAnswer[i].parentNode.getElementsByTagName("span")[0];
				span.style.top = "0px";
				console.log(span.style.top);
			}
			
			var yesNo = document.getElementsByClassName("yes-no");
			for(var i = 0; i<yesNo.length; i++){
				var span = yesNo[i].getElementsByTagName("span")[0];
				span.style.position = "relative";
				span.style.top = "12px";
			}
			
			var sixOptions = document.getElementsByClassName("six-options-button");
			for(var i = 0; i<sixOptions.length; i++){
				sixOptions[i].style.position = "relative";
				sixOptions[i].style.top = "0px";
			}
		}
	</script>
		
	</body>
</html>