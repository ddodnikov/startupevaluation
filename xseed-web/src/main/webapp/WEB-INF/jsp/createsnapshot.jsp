<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="createsnapshot.title"/></title>
		
		<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
		<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/css/home.css"></c:url>" rel="stylesheet" type="text/css"/>
		
	</head>
	<body>
		<jsp:include page="home_header.jsp"></jsp:include>
		<br>
		<div class="row content">											
			<div class="col-md-12">
				<c:if test="${sessionScope.currentUser.role == 'USER'}">	
					<c:choose>
						<c:when test="${success!=null}">
							<p class="blue-font"><spring:message code="createsnapshot.success"/></p>
						</c:when>
						<c:otherwise>
							<p class="red-font"><c:out value="${submitError}"></c:out></p>
						</c:otherwise>
					</c:choose>		
				</c:if>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>