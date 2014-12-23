<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/tablet/css/jquery.mobile.theme-1.3.1.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/tablet/css/jquery.mobile.structure-1.3.1.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/tablet/css/idangerous.swiper.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/tablet/css/global.css"/>" />
<script src="<c:url value="/resources/tablet/js/jquery.js"/>"></script>
<script
	src="<c:url value="/resources/tablet/js/jquery.mobile-1.3.1.js"/>"></script>
<script
	src="<c:url value="/resources/tablet/js/idangerous.swiper-2.1.min.js"/>"></script>
<script
	src="<c:url value="/resources/tablet/js/quickbuy.js"/>"></script>
<script type="text/javascript">
	$.mobile.defaultPageTransition = "none";
	$.mobile.defaultDialogTransition = "flip";
</script>
<sitemesh:write property='head' />
</head>
<body>
	<sitemesh:write property='body' />
</body>
</html>