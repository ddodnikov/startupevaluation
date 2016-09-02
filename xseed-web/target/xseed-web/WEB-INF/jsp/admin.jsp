<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		
		<h3><spring:message code="login.message"/></h3>
	
		<form method="post" action="login">
			<spring:message code="login.email"/> : <br> 
			<input type="text" name="username" /> <br>
			<spring:message code="login.password"/> : <br> 
			<input type="password" name="password" /> <br>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" name="login" value="<spring:message code="login.login"/>" />
		</form>
		
		<a href="register" class="button"><spring:message code="index.register"/></a>
		
		<a href="reset-password" class="button"><spring:message code="login.resetpassword"/></a>
			
	    <c:if test="${param.status=='error'}">
	     	<label style="color:red"><spring:message code="login.statuserror"/>!!</label>
	    </c:if>
	    
	    <c:if test="${param.status=='logout'}">
	     	<label style="color:green"><spring:message code="login.statuslogout"/>!!</label>
	    </c:if>
	
	</body>
</html>