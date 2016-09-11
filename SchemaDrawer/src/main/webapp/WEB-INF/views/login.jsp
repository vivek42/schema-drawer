<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="true" %>
<html>
<head>
  	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/javascript/login.js"/>"></script>
	<title>Login</title>
	<style>
		.modal {
			background:#79776E;
			opacity:0.8;
		}
	</style>
</head>
<body>
<!-- Modal -->
<div class="modal show" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h2 align="center" class="modal-title">Welcome to Schema Drawer</h2>
      </div>
      <form action="j_spring_security_check" method="POST">
      	<div class="modal-body">
      		<h4>Please enter credentials below:</h4>
			<table class="table table-hover">
				<tr>
					<td>Username:</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
			</table>
			<p class="text-warning">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
      </div>
      <div class="modal-footer">
		<input class="btn btn-default"type="submit" value="login" />
       	<table>
	   		<tr><td align="left">First time users-</td><td><a href="/ui/profile/create">Register here!</a></td></tr>
	   		<br/>
	   		<tr><td align="right"><a href="/ui/guest">As a guest?</a></td></tr>
	 	</table>
      </div>
	</form>
    </div>
  </div>
</div>
</body>
</html>