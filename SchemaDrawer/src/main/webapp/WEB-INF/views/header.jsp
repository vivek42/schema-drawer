<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Schema Drawer</title>
	<style>
		#headerRow {
			background-color: #79776E;
			color: white;
		}
		.btn{
			margin-top: 10px;
		}
	</style> 
</head>
<body>
  <c:url var="logoutUrl" value="/logout"/>
	<div id="headerRow" class="row">
		<div class="col-sm-1">
		</div>
		<div class="col-sm-8" align="left">
			<h4>
				Hi, <b><c:out value="${pageContext.request.remoteUser}" /></b>
			</h4>
		</div>
		<div class="col-sm-3" align="center">
			<form class="form-inline" action="${logoutUrl}" method="post">
				<input type="submit" class="btn btn-default" value="Log out" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</div>

	<nav id="headerNav" class="navbar navbar-default">
	<div class="container-fluid">
	  <div class="navbar-header">
	    <a class="navbar-brand" href="#">Schema Drawer</a>
	  </div>
	  <ul class="nav navbar-nav">
	  	<c:choose>
	  		<c:when test="${param.currentPage=='home' }">
			    <li class="active"><a href="/ui/home">Home</a></li>
			    <li><a href="/ui/profile/edit">Profile</a></li>
	  		</c:when>
	  		<c:otherwise>
			    <li><a href="/ui/home">Home</a></li>
			    <li class="active"><a href="/ui/profile/edit">Profile</a></li>
	  		</c:otherwise>
	  	</c:choose>
	    <li><a href="#aboutUs" data-toggle="collapse">About Us</a>
	  </ul>
	</div>
	<div id="aboutUs" class="collapse">
		Schema Drawer helps you visualize the database by drawing E-R diagrams using create sql scripts.
	</div>
</nav>
</body>
</html>