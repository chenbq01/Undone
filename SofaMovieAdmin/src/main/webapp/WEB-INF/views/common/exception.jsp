<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title></title>
<body>
	<%
		Exception ex = (Exception) request.getAttribute("ex");
	%>

	Exception:
	<%=ex.toString()%>
</body>