<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<c:url value="/logout" var="logoutUrl" />
	<P>
		<a href="${logoutUrl}">Log Out</a>
	</P>
</body>
</html>
