<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/mobile/css/jquery.mobile.theme-1.3.1.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/mobile/css/jquery.mobile.structure-1.3.1.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/mobile/css/global.css"/>" />
<script src="<c:url value="/resources/mobile/js/jquery.js"/>"></script>
<script
	src="<c:url value="/resources/mobile/js/jquery.mobile-1.3.1.js"/>"></script>
</head>
<body>
	<div data-role="page" data-fullscreen="true">
		<div data-role="header" data-position="fixed">
			<h1>${sessionScope._profile['rest_name']}</h1>
		</div>
		<div data-role="content">
			<%@include file="/WEB-INF/views/mobile/business/common/mainimage.jsp"%>
			<div class="ui-content">
				<div class="ui-info">
					<strong>餐厅简介：</strong>
				</div>
				<div class="ui-info">${sessionScope._profile['rest_intr']}</div>
				<div class="ui-info">
					<strong>餐厅地址：</strong>
				</div>
				<div class="ui-info">${sessionScope._profile['rest_address']}</div>
				<div class="ui-info">
					<strong>联系电话：</strong>
				</div>
				<div class="ui-info">${sessionScope._profile['rest_phone']}</div>
			</div>
			<ul data-role="listview" style="margin-top: -10px;" data-inset="true"
				data-theme="a">
				<li><a href="#"> <img width="60px" height="60px"
						src="<c:url value="/resources/mobile/images/icon/dc.png"/>">
						<h2>点餐</h2>
						<p>浏览菜单自助点餐</p></a></li>
				<li><a href="#" data-rel="popup" data-transition="pop"> <img
						width="60px" height="60px"
						src="<c:url value="/resources/mobile/images/icon/jc.png"/>">
						<h2>加餐</h2>
						<p>录入已提交订单的订单号加菜</p></a></li>
				<li><a href="#" data-rel="popup" data-transition="pop"> <img
						width="60px" height="60px"
						src="<c:url value="/resources/mobile/images/icon/yqd.png"/>">
						<h2>一起点</h2>
						<p>告知同伴订单号一起参与点餐</p></a></li>
				<c:if test="${sessionScope._profile['open_vip'] == '1'}">
					<li><a href="#" data-rel="popup" data-transition="pop"> <img
							width="60px" height="60px"
							src="<c:url value="/resources/mobile/images/icon/acdc.png"/>">
							<h2>爱吃的菜</h2>
							<p>验证手机号看看自己在这里最爱吃的菜</p></a></li>
				</c:if>
				<li><a href="#" data-rel="popup" data-transition="pop"> <img
						width="60px" height="60px"
						src="<c:url value="/resources/mobile/images/icon/dp.png"/>">
						<h2>推荐点评</h2>
						<p>用餐结束时可以留下自己的反馈</p></a></li>
			</ul>
		</div>
		<%@include
			file="/WEB-INF/views/mobile/business/common/systeminfofooter.jsp"%>
	</div>
</body>