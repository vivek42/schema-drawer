<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<jsp:include page="header.jsp">
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
				<form:form id="uploadRequestForm" action="/ui/admin/upload" commandName="uploadRequest" method="post" enctype="multipart/form-data">
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
<br/>
<div class="container well-lg">
	<h4>Upload History</h4>
	<table class="table table-hover">
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
					<td><form:form action="/ui/download/file" commandName="uploadRow" method="post">
						<form:input type="hidden" value="${upload.serialNumber}" path="serialNumber"/>
						<form:input type="hidden" value="${upload.fileName }" path="fileName"/>
						<form:input type="hidden" value="${upload.uploadTime }" path="uploadTime"/>
						<input type="submit" class="btn btn-default" value="Download" />
<!-- 						<input type="submit" class="btn btn-default" value="Download" data-toggle="modal" data-target="#notAvailable"/> -->
					    </form:form>
					</td>
					<td>
					  <form:form action="/ui/download/diagram" commandName="uploadRow" method="post">
					  <form:input type="hidden" value="${upload.serialNumber}" path="serialNumber"/>
					  <form:input type="hidden" value="${upload.fileName }" path="fileName"/>
					  <form:input type="hidden" value="${upload.uploadTime }" path="uploadTime"/>
					  <input type="submit" class="btn btn-default" value="Generate Diagram" />
					  </form:form>
<!-- 					  <input type="submit" class="btn btn-default" value="Generate Diagram" data-toggle="modal" data-target="#notAvailable"/> -->
					</td>
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
