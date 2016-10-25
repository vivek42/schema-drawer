<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<jsp:include page="guestheader.jsp">
	<jsp:param value="home" name="currentPage"/>
</jsp:include>
<head>
	<title>Home</title>
	<style>
		#backColor {
			background-color: #DDD;
		}
		.container{
			background-color: #F5F5F5;
		}
	</style>
</head>
<body id="backColor">
<c:if test="${message !=null}">
	<p>${message}</p><br/>
	<c:remove var="message"/>
</c:if>
<div class="container well-sm">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Upload Box (Only works for sqlite db file)</h4></div>
				<div class="panel-body">
				<form:form id="uploadRequestForm" action="/ui/guest" commandName="uploadRequest" method="post" enctype="multipart/form-data" target="_blank">
					<div class="row">
						<div class="col-sm-6"><form:input class="btn btn-info" type="file" value="select File" path="uploadContentFile"/></div>
						<div class="col-sm-6" align="center"><input id="generateSchema" class="btn btn-default" type="submit" value="Generate Schema"/></div>
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
<br/>
</body>
</html>
