<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<c:url value="/logout" var="logoutUrl" />
	<p>
		<a href="${logoutUrl}">Log Out</a>
	</p>
	<p>Please find account functions below...</p>
	<ul>
		<c:url value="/account/changePassword" var="changePasswordUrl" />
		<li><a href="${changePasswordUrl}">Change Password</a></li>
	</ul>
	<p>Upload</p>
	<form enctype="multipart/form-data"
		action="<c:url value="/file/upload" />" method="post">
		<input type="file" name="file" /> <br> <input type="button"
			name="btn_upload" value="上传" onclick="javascript:form.submit();" />
		<input type="button" name="btn_cancel" value="取消"
			onclick="javascript:form.reset();" />
	</form>
	<form action="<c:url value="/file/delete" />" method="post">
		<p>Please input File ID</p>
		<input type="text" name="fileid" /> <br> <input type="button"
			name="btn_delete" value="删除" onclick="javascript:form.submit();" />
		<input type="button" name="btn_cancel" value="取消"
			onclick="javascript:form.reset();" />
	</form>
</body>
</html>
