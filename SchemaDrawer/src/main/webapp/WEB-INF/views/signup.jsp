<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script src="<c:url value="/resources/javascript/signup.js" />"></script>
	<title>Sign up</title>
</head>
<body>
<h1>Please fill the following information  </h1>
    <form:form id="profileCreation" action = "/schema/profile/create" commandName="profile" method = "POST" >
	  <table>
		<tr><td>User name: </td><td><input id="username"type = "text" name = "username" /><div class="error"></div></td></tr>
		<tr><td>Password: </td><td><input id="password" type = "password" name = "password" /><div class="error"></div></td></tr>
		<tr><td>Re-enter Password: </td><td><input id="rpassword" type = "password" name = "password"/><div class="error"></div></td></tr>
		<tr><td>Email Address: </td><td><input id="emailAddress" type = "text" name = "emailAddress"/><div class="error"></div></td></tr>
		<tr><td>First Name: </td><td><input id="firstName" type = "text" name = "firstName"/><div class="error"></div></td></tr>
		<tr><td>Last Name: </td><td><input id="lastName" type="text" name="lastName"/><div class="error"></div></td></tr>
		<tr><td>DOB: </td><td><input id="dob" type="date" name="dob"/><div class="error"></div></td></tr>
		<tr>
		  <td>Select Gender: </td>
		  <td>
		    <select id="gender" name="gender">
		  	  <option value="Unspecified">Unspecified</option>
		      <option value="male">male</option>
		      <option value="female">female</option>
		    </select>
		    <div class="error"></div>
		  </td>
		</tr>
		<tr><td colspan = "2" align = "right"><input type = "submit" value = "Submit" /></td></tr>
	  </table>
	 </form:form>
	 <font color="red">
	   <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	 </font>  
</body>
</html>
