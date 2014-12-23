<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%=request.getAttribute("fileName") %>
	<body>
<script>
$(window.parent.uploadOk("<%=request.getAttribute("fileName") %>"));
</script>
</body>