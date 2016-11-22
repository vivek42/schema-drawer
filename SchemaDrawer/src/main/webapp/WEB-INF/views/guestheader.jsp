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
				Welcome, Guest
			</h4>
		</div>
		<div class="col-sm-3" align="center">
<!-- 			<div class="btn btn-default"> -->
<!-- 				<a href="/login">Go to login page</a> -->
<!-- 			</div> -->
		</div>
	</div>
</body>
</html>