<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="home.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/home.css"></c:url>" rel="stylesheet" type="text/css"/>
	</head>	
	<body>	
		<jsp:include page="home_header.jsp"></jsp:include>		
		<div class="row content">											
			<div class="col-md-12">
			
				<c:if test="${sessionScope.currentUser.role == 'USER'}">
					<div class="row">
						<!-- Show surveys -->
						<div class="col-md-4">
							<a href="<c:url value="/surveys"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_questionnarie.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="user-home.surveys-title"/></p>
							<p><spring:message code="user-home.surveys-message"/></p>
						</div>
						
						<!-- Show reports -->
						<div class="col-md-4">
							<!-- Redirect to reports' list -->
							<%-- <a href="<c:url value="/reports"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_Reports.png"></c:url>"/></a> --%>
							<!-- Redirect to progress report page -->
							<a href="<c:url value="/progressreport"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_Reports.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.report-title"/></p>
							<p><spring:message code="admin-home.report-message"/></p>
						</div>	
						
						<!-- Show snapshots -->
						<div class="col-md-4">
							<a href="<c:url value="/snapshots"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_usersList.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="user-home.snapshots-title"/></p>
							<p><spring:message code="user-home.snapshots-message"/></p>
						</div>	
						
						<!-- Edit startup -->
						<div class="col-md-4">
							<a href="<c:url value="/editstartup"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_company.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="user-home.startup-title"/></p>
							<p><spring:message code="user-home.startup-message"/></p>
						</div>		
		
						<!-- Edit profile -->
						<div class="col-md-4">
							<a href="<c:url value="/editprofile"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_CompanyUsers.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="user-home.editprofile-title"/></p>
							<p><spring:message code="user-home.editprofile-message"/></p>
						</div>
					</div>	
				</c:if>
				
				<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
					<div class="row">
					
						<!-- Show calculations -->
						<div class="col-md-4">
							<a href="<c:url value="/calculations"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_calculations.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.calculations-title"/></p>
							<p><spring:message code="admin-home.calculations-message"/></p>
						</div>
						
						<!-- Show reports -->
						<%-- <div class="col-md-4">
							<a href="<c:url value="/reports"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_Reports.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.report-title"/></p>
							<p><spring:message code="admin-home.report-message"/></p>
						</div> --%>
						
						<!-- Show startups -->
						<div class="col-md-4">
							<a href="<c:url value="/showallstartups"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_company.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.startups-title"/></p>
							<p><spring:message code="admin-home.startups-message"/></p>
						</div>
						
						<!-- Show users -->
						<div class="col-md-4">
							<a href="<c:url value="/showallusers"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_usersList.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.users-title"/></p>
							<p><spring:message code="admin-home.users-message"/></p>
						</div>
						
						<!-- Show admins -->
						<div class="col-md-4">
							<a href="<c:url value="/admins"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_usersList.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.admins-title"/></p>
							<p><spring:message code="admin-home.admins-message"/></p>
						</div>
						
						<!-- Edit profile -->
						<div class="col-md-4">
							<a href="<c:url value="/editprofile"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_CompanyUsers.png"></c:url>"/></a>
							<p id="ico-names"><spring:message code="admin-home.editprofile-title"/></p>
							<p><spring:message code="admin-home.editprofile-message"/></p>
						</div>
						
						<!-- Questions -->
						<div class="col-md-4">
							<a href="<c:url value="/algoquestions"></c:url>"><img class="img-circle" src="<c:url value="/img/ICO_CompanyUsers.png"></c:url>"/></a>
							<p id="ico-names">Questions</p>
							<p>Choose algorithm questions</p>
						</div>
					</div>
				</c:if>
				
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>	
	</body>
</html>