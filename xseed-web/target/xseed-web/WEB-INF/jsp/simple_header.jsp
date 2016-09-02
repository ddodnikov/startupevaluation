<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container text-center" >
	<div class="row">
		<nav class="navbar navbar-default navbar-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
				  		<li class="dropdown">
						<c:if test="${sessionScope.currentUser.role == 'USER'}">
							<a href="#" id="link" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.currentStartup.name}<span class="caret"></span></a>
						</c:if>
						<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
							<a href="#" id="link" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="admin.menu"/><span class="caret"></span></a>
						</c:if>
					
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/home"></c:url>"><spring:message code="home.menu-home"/></a></li>
						  	<li role="separator" class="divider"></li>
						 	<li><a href="<c:url value="/editprofile"></c:url>"><spring:message code="home.menu-edit-profile"/></a></li>
						  	<li role="separator" class="divider"></li>
					  
							<!-- USER MENU -->
							<c:if test="${sessionScope.currentUser.role == 'USER'}">
								<li><a href="<c:url value="/editstartup"></c:url>"><spring:message code="home.menu-startup"/></a></li>
								<li role="separator" class="divider"></li>
							 	<li><a href="<c:url value="/surveys"></c:url>"><spring:message code="home.menu-survey"/></a></li>
								<li role="separator" class="divider"></li>
								<li><a href="<c:url value="/snapshots"></c:url>"><spring:message code="home.menu-snapshot"/></a></li>
								<li role="separator" class="divider"></li>
								<!-- Redirect to reports list -->
								<%-- <li><a href="<c:url value="/reports"></c:url>"><spring:message code="home.menu-reports"/></a></li>
								<li role="separator" class="divider"></li> --%>
								<!-- Redirect to progress report page -->
								<li><a href="<c:url value="/progressreport"></c:url>"><spring:message code="home.menu-reports"/></a></li>
								<li role="separator" class="divider"></li>
							</c:if>
							  
							<!-- ADMIN MENU -->
							<c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
							  	<li><a href="<c:url value="/showallusers"></c:url>"><spring:message code="home.menu-users"/></a></li>
								<li role="separator" class="divider"></li>
							 	<li><a href="<c:url value="/showallstartups"></c:url>"><spring:message code="home.menu-startups"/></a></li>
								<li role="separator" class="divider"></li>
								<li><a href="<c:url value="/admins"></c:url>"><spring:message code="home.menu-admins"/></a></li>
								<li role="separator" class="divider"></li>
								<li><a href="<c:url value="/algoquestions"></c:url>"><spring:message code="home.menu-algo"/></a></li>
								<li role="separator" class="divider"></li>
								<li><a href="<c:url value="/calculations"></c:url>"><spring:message code="home.menu-calculations"/></a></li>
								<li role="separator" class="divider"></li>
								<%-- <li><a href="<c:url value="/reports"></c:url>"><spring:message code="home.menu-reports"/></a></li>
								<li role="separator" class="divider"></li> --%>
							</c:if>
							<li><a href="<c:url value="/logout"></c:url>"><spring:message code="home.menu-logout"/></a></li>
						</ul> 
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav>
	</div>
	<div class="row header header-title" >
		<div class="col-md-12">
			<p><spring:message code="xseed.title"/></p>
		</div>
	</div>
	<c:if test="${breadcrumbs!=null}">
		<div class="row text-left" style="padding-top:10px;font-size:17px">
			<c:forEach items="${breadcrumbs}" var="link">
				<a href="<c:url value="${link.url}"></c:url>">${link.label}</a> /
			</c:forEach>
		</div>
	</c:if>
	
	<jsp:include page="errors.jsp"></jsp:include>