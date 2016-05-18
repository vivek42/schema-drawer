<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<jsp:include page="header.jsp">
	<jsp:param value="currentPage" name="profile"/>
</jsp:include>
<head>
	<title>Profile</title>
	<style>
		#backColor {
			background-color: #DDD;
		}
	</style>
</head>
<body>
	<div class="modal show" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 align="center" class="modal-title"> Personal Details </h2>
				</div>
				<div class="modal-body">
					<table class="table table-hover">
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
							<td>${profile.dob }</td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td>${profile.gender }</td>
						</tr>
						<tr>
							<td colspan="2" align="right"></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-sm-6" align="right">
							<input class="btn btn-default" type="submit" value="Edit" data-toggle="modal" data-target="#notAvailable" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<font color="red"> <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</font>
	
	<div id="notAvailable" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Feature not available</h4>
	      </div>
	      <div class="modal-body">
	        <p>This feature is not available in current version of the application.<br/>
	        It will be released in upcoming versions</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</body>
</html>
