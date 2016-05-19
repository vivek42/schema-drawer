<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<jsp:include page="header.jsp">
	<jsp:param value="profile" name="currentPage"/>
</jsp:include>
<head>
	<title>Profile</title>
	<style>
		#backColor {
			background-color: #DDD;
		}
		.container{
			background-color: #F5F5F5;
		}
		#edit{
			margin-bottom: 15px;
			margin-top:-20px;
		}
	</style>
</head>
<body id="backColor">
	<div class="container">
		<h4> Personal Details</h4>
		<table class="table table-hover">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td>User name:</td>
					<td>${profile.username }</td>
				</tr>
				<tr>
					<td>Email Address:</td>
					<td>${profile.emailAddress }</td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td>${profile.firstName }</td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td>${profile.lastName }</td>
				</tr>
				<tr>
					<td>DOB:</td>
					<td>${profile.dobShow }</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>${profile.gender }</td>
				</tr>
				<tr>
					<td colspan="2" align="right"></td>
				</tr>
			</tbody>
		</table>
		<div class="row">
			<div class="col-sm-12" align="right">
				<input id="edit" class="btn btn-default" type="submit" value="Edit" data-toggle="modal" data-target="#updateProfileModal" />
			</div>
		</div>
	</div>
	<font color="red"> <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
	
	<div id="updateProfileModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 align="center" class="modal-title">Update Personal Details</h2>
			</div>
			<form:form id="profileUpdateForm" action="/schema/profile/edit"
				commandName="profile" method="POST">
				<div class="modal-body">
					<table class="table table-hover">
						<tr>
							<td>User name:</td>
							<td>${profile.username }</td>
						</tr>
						<tr>
							<td>Email Address:</td>
							<td><input id="emailAddress" type="text"
								name="emailAddress" value="${profile.emailAddress }"/>
								<div class="error"></div></td>
						</tr>
						<tr>
							<td>First Name:</td>
							<td><input id="firstName" type="text" name="firstName" value="${profile.firstName }"/>
								<div class="error"></div></td>
						</tr>
						<tr>
							<td>Last Name:</td>
							<td><input id="lastName" type="text" name="lastName" value="${profile.lastName }"/>
								<div class="error"></div></td>
						</tr>
						<tr>
							<td>DOB:</td>
							<td>${profile.dobShow }</td>
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
						<div class="col-sm-12" align="right">
							<input class="btn btn-default" type="submit" value="Submit" />
						</div>
					</div>
				</div>
				<form:input type="hidden" value="${profile.username}" path="username"/>
			</form:form>
		</div>
	  </div>
	</div>
	
</body>
</html>
