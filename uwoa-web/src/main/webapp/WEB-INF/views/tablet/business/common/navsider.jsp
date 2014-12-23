<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul data-role="listview" data-inset="true" data-theme="a">
	<li><a href="<c:url value="/dishes/specialtylist"/>" data-ajax="false"
		${requestScope['ui-btn-active']=='1'?'class="ui-btn-active"':''}><img
			width="60px" height="60px"
			src="<c:url value="/resources/tablet/images/icon/ts.png"/>">
			<h2>特色</h2>
			<p>去看看店家最具特色和不需此行的美味</p></a></li>
	<li><a href="<c:url value="/dishes/newlist"/>" data-ajax="false"
		${requestScope['ui-btn-active']=='2'?'class="ui-btn-active"':''}><img
			width="60px" height="60px"
			src="<c:url value="/resources/tablet/images/icon/xp.png"/>">
			<h2>新品</h2>
			<p>这里有店家最近新推介的菜品</p></a></li>
	<li><a href="<c:url value="/dishes/promotionlist"/>" data-ajax="false"
		${requestScope['ui-btn-active']=='3'?'class="ui-btn-active"':''}><img
			width="60px" height="60px"
			src="<c:url value="/resources/tablet/images/icon/tj.png"/>">
			<h2>特价</h2>
			<p>看看哪些是今天的特价菜</p></a></li>
	<li><a href="<c:url value="/dishes/recommendlist"/>" data-ajax="false"
		${requestScope['ui-btn-active']=='4'?'class="ui-btn-active"':''}><img
			width="60px" height="60px"
			src="<c:url value="/resources/tablet/images/icon/tuijian.png"/>">
			<h2>推荐</h2>
			<p>何不考虑一下大家公认的菜肴</p></a></li>
	<li><a href="<c:url value="/dishes/menulist"/>" data-ajax="false"
		${requestScope['ui-btn-active']=='5'?'class="ui-btn-active"':''}><img
			width="60px" height="60px"
			src="<c:url value="/resources/tablet/images/icon/cp.png"/>">
			<h2>菜谱</h2>
			<p>这里是店家所有的菜品</p></a></li>
</ul>