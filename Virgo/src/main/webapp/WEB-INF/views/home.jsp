<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
<title><spring:message code="home.title" /></title>
</head>
<body>
	<h1 style="color: <spring:theme code="home.h1.color" />">Sakya</h1>
	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
