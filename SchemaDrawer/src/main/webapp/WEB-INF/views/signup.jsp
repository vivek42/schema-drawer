<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script src="<c:url value="/resources/javascript/signup.js" />"></script>
<title>Sign up</title>
<style>
.modal {
	background: #79776E;
	opacity: 0.8;
}
.error {
	color: red;
}
</style>
</head>
<body>
	<div class="modal show" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 align="center" class="modal-title">Please fill the
						following information</h2>
				</div>
				<form:form id="profileCreation" action="/profile/create"
					commandName="profile" method="POST">
					<div class="modal-body">
						<table class="table table-hover">
							<tr>
								<td>User name:</td>
								<td><input id="username" type="text" name="username" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input id="password" type="password" name="password" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>Re-enter Password:</td>
								<td><input id="rpassword" type="password" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>Email Address:</td>
								<td><input id="emailAddress" type="text"
									name="emailAddress" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td><input id="firstName" type="text" name="firstName" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input id="lastName" type="text" name="lastName" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>DOB:</td>
								<td><input id="dob" type="date" name="dob" />
									<div class="error"></div></td>
							</tr>
							<tr>
								<td>Select Gender:</td>
								<td><select id="gender" name="gender">
										<option value="Unspecified">Unspecified</option>
										<option value="male">male</option>
										<option value="female">female</option>
								</select>
									<div class="error"></div></td>
							</tr>
							<tr>
								<td colspan="2" align="right"></td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm-6" align="left">
							  	<a href="/login" class="btn btn-default" role="button">Back to login</a>
							</div>
							<div class="col-sm-6" align="right">
								<input class="btn btn-default" type="submit" value="Submit" />
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<font color="red"> <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
</body>
</html>
