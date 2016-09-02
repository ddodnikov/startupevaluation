<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="algoquestions.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/question.css"></c:url>" rel="stylesheet" type="text/css"/>
		
		<style>	
			div.col-md-12 .row{
				padding:20px 25px;	
			}
			@media only screen and (max-device-width: 480px) {		
				.content, .header, .navbar{
					width:480px;
				}
			}
			input[type=submit]{
				float:right;
				margin-right:0%;
				background:none;
				border:none;
				background-image:url('./img/makecalc.png');
				background-size:300px 40px;
				background-repeat: no-repeat;
				width:300px;
				height:40px;
				display:inline-block;
			 	vertical-align:middle;
				font-size:20px;
				font-color:black;
			}
			.selectAlgo :hover{
				cursor : default;
			}
		</style>
		
		<script>
			function selectAlgo(id){
				var input = document.getElementById("input" + id);
				if(input.checked == true){
					input.checked = false;
				} else {
					input.checked = true;
				}
			};
		</script>
	</head>
	<body>
		<jsp:include page="simple_header.jsp"></jsp:include>
		<div class="row content">											
			<div class="col-md-12">
				<div class="row">
					<h2>Algorithm questions</h2>
					<c:url value="/algoquestions" var="link"></c:url>
					<form action="${link}" accept-charset="UTF-8" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<table class="table table-default">
							<thead>
								<tr>
									<th><span>Question</span></th>
									<th><span>Algorithm Y/N</span></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${algoQuestions}" var="algoQuestion" varStatus="loop">
									<tr id="${loop.index + 1}" onclick="selectAlgo(this.id)" class="selectAlgo">
										<td width="90%"><span><c:out value="${loop.index + 1}. ${algoQuestion.text}"></c:out></span></td>
										<c:if test="${algoQuestion.algo == 'TRUE'}">
											<td width="10%">
												<label>
													<input id="input${loop.index + 1}" type="checkbox" name="${algoQuestion.id}" checked>
													<span></span>
												</label>
											</td>
										</c:if>
										<c:if test="${algoQuestion.algo == 'FALSE'}">
											<td width="10%">
												<label>
													<input id="input${loop.index + 1}" type="checkbox" name="${algoQuestion.id}">
													<span></span>
												</label>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<input type="submit" id="submit" value="<spring:message code="algoquestions.save" />"/>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>