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
	<div class="row fluid">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Upload Box (Only works for sqlite db file)</h4></div>
				<div class="panel-body">
				<form:form id="uploadRequestForm" action="/guest" commandName="uploadRequest" method="post" enctype="multipart/form-data" target="_blank">
					<div class="col-sm-8">
						<div class="row fluid"><form:input class="btn btn-info" type="file" value="select File" path="uploadContentFile"/></div>
<%-- 						<div class="row fluid">
						  <table>
							<thead>
							</thead>
							<tbody>
							<tr>
								<td><h5>Select Format : </h5></td>
								<td>
									<form:select class="form-group" path="format" style="margin-top:15px">
										<c:forEach items="${commandList}" var="comm">
											<form:option value="${comm}" label="${comm }" />
										</c:forEach>
									</form:select>
								</td>
							</tr>
							<tr>
								<td><h5>Select Output type : </h5></td>
								<td>
									<form:select class="form-group" path="output" style="margin-top:15px">
										<c:forEach items="${outputListDropdown}" var="out">
											<form:option value="${out}" label="${out }" />
										</c:forEach>
								    	<form:options id="outputListDropdown" items="${outputList}"/>
			                      	</form:select>
								</td>
							</tr>
							</tbody>
							</table>
	                    </div>    --%>                 
						<div class="row fluid" align="left"><input id="generateSchema" class="btn btn-default" type="submit" value="Generate Schema"/></div>
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
