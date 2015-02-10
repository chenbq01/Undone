<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>
		Hello
		<security:authentication property="principal.username" />
		!
	</h1>

	<security:authorize access="hasRole('ROLE_ADMIN')"><P>The time on the server is ${serverTime}.</P></security:authorize>
	<c:url value="/logout" var="logoutUrl" />
	<P>
		<a href="${logoutUrl}">Log Out</a>
	</P>
</body>
</html>
