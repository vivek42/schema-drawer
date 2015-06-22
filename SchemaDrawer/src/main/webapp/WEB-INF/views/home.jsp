<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h2>Upload Box</h2>
	<form:form id="uploadRequestForm" action="/schema/upload" commandName="uploadRequest" method="post">
			<table>
				<tr>
					<td>Enter desired fileName :</td>
					<td><form:input path="fileName" />
					</td>
					<td><form:errors path="fileName" cssClass="error" />
					<td><form:input path="uploadContent" />
					</td>
					<td><form:errors path="uploadContent" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" />
					</td>
				</tr>
			</table>
	</form:form>
</body>
</html>
