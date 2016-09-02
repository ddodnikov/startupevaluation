<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty errors}">
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<p class="red-font">${error.message}</p>			
		</c:forEach>
	</div>
</c:if>