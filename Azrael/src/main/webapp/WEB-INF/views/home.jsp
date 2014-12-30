<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<a
		href="<c:url value="/callback/?signature=signature&timestamp=timestamp&nonce=nonce&echostr=echostr"/>">模拟微信请求</a>
</body>
</html>
