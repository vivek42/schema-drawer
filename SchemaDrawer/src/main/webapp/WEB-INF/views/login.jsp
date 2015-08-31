<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<h1>Enter your credentials : </h1>
    <form action = "j_spring_security_check" method = "POST" >
	  <table>
		<tr>
		  <td>Username: </td>
		  <td><input type = "text" name = "j_username" /></td>	  
		</tr>
		<tr>
		  <td>Password: </td>
		  <td><input type = "password" name = "j_password" /></td>
		</tr>
		<tr>
		  <td colspan = "2" align = "right"><input type = "submit" value = "login" /></td>
		</tr>
	  </table>
	 </form>
	 <font>
	   <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	 </font>
</body>
</html>