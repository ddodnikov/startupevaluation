<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reports</title>

<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet' type='text/css'/>
<link href="<c:url value="/css/navigation.css"></c:url>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/main.css"></c:url>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/css/inner-page.css"></c:url>" rel="stylesheet" type="text/css"/>

<style>
	div.col-md-12 .row{
		padding:20px 25px;	
	}
</style>

</head>
<body>
<jsp:include page="simple_header.jsp"></jsp:include>
	<div class="row content">											
		<div class="col-md-12">
			<div class="row">
				<h2>Reports</h2>
				<a href="<c:url value="/progressreport"></c:url>">
					<span>Progress report</span>
				</a>
				<%-- <table class="table table-default">
					<thead>
						<tr>
							<th>Startup</th>
							<th>Date</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Test1</td>
							<td>31.03.2016</td>
							<td>
								<a href="<c:url value="/report/1"></c:url>">
									<span>Progress report</span>
								</a>
							</td>
						</tr>
					</tbody>
				</table> --%>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>