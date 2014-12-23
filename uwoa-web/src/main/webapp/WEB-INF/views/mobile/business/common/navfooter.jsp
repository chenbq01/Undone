<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div data-role="footer" data-position="fixed">
	<div data-role="navbar" data-grid="d">
		<ul>
			<li><a href="<c:url value="/dishes/specialtylist"/>"
				${requestScope['ui-btn-active']=='1'?'class="ui-btn-active"':''}>特色</a></li>
			<li><a href="<c:url value="/dishes/newlist"/>"
				${requestScope['ui-btn-active']=='2'?'class="ui-btn-active"':''}>新品</a></li>
			<li><a href="<c:url value="/dishes/promotionlist"/>"
				${requestScope['ui-btn-active']=='3'?'class="ui-btn-active"':''}>特价</a></li>
			<li><a href="<c:url value="/dishes/recommendlist"/>"
				${requestScope['ui-btn-active']=='4'?'class="ui-btn-active"':''}>推荐</a></li>
			<li><a href="<c:url value="/dishes/menulist"/>"
				${requestScope['ui-btn-active']=='5'?'class="ui-btn-active"':''}>菜谱</a></li>
		</ul>
	</div>
</div>