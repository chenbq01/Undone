<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title></title>
<body>
	<%
		Exception ex = (Exception) request.getAttribute("ex");
	%>
	<H2>
		Exception:
		<%=ex.toString()%></H2>
</body>