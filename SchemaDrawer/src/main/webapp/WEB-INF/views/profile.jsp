<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<jsp:include page="header.jsp"/>
<head>
	<title>Edit Profile</title>
</head>
<body>
<h1>
	Edit Profile 
</h1>
<h2>Change Password</h2>
	<form:form id="changePasswordForm" action="/schema/profile/changePassword" commandName="user" method="post">
			<table>
				<tr>
					<td>Enter new password :</td>
					<td><form:input path="password" />
					</td>
					<td><form:errors path="password" cssClass="error" />
				</tr>
				<tr>
					<td colspan="3"><input type="submit" />
					</td>
				</tr>
			</table>
	</form:form> 
</body>
</html>