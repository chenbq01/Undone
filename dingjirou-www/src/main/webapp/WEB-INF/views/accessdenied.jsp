<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
	<h1>Access Denied!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<c:url value="/login" var="loginUrl" />
	<p>
		<a href="${loginUrl}">Log In</a>
	</p>
</body>
</html>
