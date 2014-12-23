<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div data-role="header" data-position="fixed">
	<a href="<c:url value="/mobile/${sessionScope._profile['rest_code']}?shadow"/>"
		data-corners="false" data-icon="back">返回</a>
	<h1>订单号：${fn:substring(sessionScope._orderno,fn:length(sessionScope._orderno)-6,fn:length(sessionScope._orderno))}</h1>
	<a href="<c:url value="/mobileorder/temporary/${sessionScope._orderno}"/>"
		class="ui-btn-right" data-role="button" data-icon="bars" data-ajax="false"
		data-corners="false">下单</a>
</div>