<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<jsp:include page="header.jsp"/>
<head>
	<title>Profile</title>
	<style>
		#backColor {
			background-color: #DDD;
		}
	</style>
</head>
<body id="backColor">
<c:if test="${message !=null}">
	<p>${message}</p><br/>
	<c:remove var="message"/>
</c:if>
	<div class="modal show" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 align="center" class="modal-title">Please fill the
						following information</h2>
				</div>
				<form:form id="profileCreation" action="/schema/profile/create"
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
							  	<a href="/schema/login" class="btn btn-default" role="button">Back to login</a>
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



<div class="container">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Upload Box</h4></div>
				<div class="panel-body">
				<form:form id="uploadRequestForm" action="/schema/admin/upload" commandName="uploadRequest" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col-sm-6"><form:input class="btn btn-info" type="file" value="select File" path="uploadContentFile"/></div>
						<div class="col-sm-6" align="center"><input class="btn btn-default" type="submit" value="Upload"/></div>
					</div>
					<div class="row">
						<form:errors path="uploadContentFile" cssClass="error" />
					</div>
				</form:form>
			  </div>
			</div>
		</div>
		<div class="col-sm-6"></div>
	</div>
</div>
<div class="container">
	<h4>Upload History</h4>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Serial No.</th>
				<th>Filename</th>
				<th>Date Uploaded</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="upload" items="${uploadHistory}">
				<tr>
					<td><c:out value="${upload.serialNumber}"></c:out></td>
					<td><c:out value="${upload.fileName}"></c:out></td>
					<td><fmt:formatDate type="both" value="${upload.uploadTime}" /></td>
					<td><input type="submit" class="btn btn-default" value="Download" data-toggle="modal" data-target="#notAvailable"/></td>
					<td><input type="submit" class="btn btn-default" value="Generate Diagram" data-toggle="modal" data-target="#notAvailable"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
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
