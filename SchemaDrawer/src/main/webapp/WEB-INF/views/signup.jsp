<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Sign up</title>
</head>
<body>
<h1>Please fill the following information  </h1>
    <form:form id="profileCreation" action = "/schema/profile/create" commandName="profile" method = "POST" >
	  <table>
		<tr><td>User name: </td><td><input type = "text" name = "username" /></td></tr>
		<tr><td>Password: </td><td><input type = "password" name = "password" /></td></tr>
		<tr><td>Re-enter Password: </td><td><input type = "password" name = "password" /></td></tr>
		<tr><td>Email Address: </td><td><input type = "text" name = "emailAddress"/></td></tr>
		<tr><td>First Name: </td><td><input type = "text" name = "firstName"/></td></tr>
		<tr><td>Last Name: </td><td><input type="text" name="lastName"/></td></tr>
		<tr><td>DOB: </td><td><input type="date" name="dob"/></td></tr>
		<tr>
		  <td>Select Gender: </td>
		  <td>
		    <select name="gender">
		  	  <option value="Unspecified">Unspecified</option>
		      <option value="male">male</option>
		      <option value="female">female</option>
		    </select>
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
