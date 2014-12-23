<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div data-role="page" data-fullscreen="true">
		<div data-role="header" data-position="fixed">
			<a href="<c:url value="/dishes/specialtylist"/>" data-corners="false"
				data-icon="back">返回</a>
			<h1>订单号：${fn:substring(sessionScope._orderno,fn:length(sessionScope._orderno)-6,fn:length(sessionScope._orderno))}</h1>
			<a
				href="<c:url value="/mobileorder/temporary/${sessionScope._orderno}"/>"
				class="ui-btn-right" data-role="button" data-icon="bars" data-ajax="false"
				data-corners="false">下单</a>
		</div>
		<div data-role="content">
			<%@include
				file="/WEB-INF/views/mobile/business/common/mainplaceholder.jsp"%>
			<c:if test="${favouritelist.size() == 0}">
				<p style="text-align: center">暂无记录</p>
			</c:if>
			<ol data-role="listview" data-split-icon="plus" data-split-theme="d"
				data-inset="true">
				<c:forEach var="favourite" items="${favouritelist}">
					<li><a href="#"
						onclick="javascript:getDishesCommentData('${favourite['id']}');">
							<img width="80px" height="80px" src="${favourite['pic']}"><span
							id="${favourite['id']}" class="ui-li-count">${favourite['ordernum']
								==
								null?"0":(favourite['ordernum']==0?"?":favourite['ordernum'])}</span>
							<h2>
								<strong>${favourite['food_name']}</strong>
							</h2>
							<p>
								${favourite['price']}元/${favourite['unit']}&nbsp;&nbsp;次数<strong>${favourite['fcount']}</strong>
							</p>
							<p>${favourite['food_intro']}</p>
					</a> <!--a href="<c:url value="/dishes/${favourite['id']}/prompt"/>"
						data-rel="dialog">我想吃</a--> <a
						href="javascript:quickbuy('<c:url value="/mobileorder/temporary/quickbuy"/>', '${sessionScope._orderno}', '${favourite['id']}', '${favourite['food_name']}', '${favourite['unit']}', '${favourite['price']}');">我想吃</a></li>
				</c:forEach>
			</ol>
		</div>
		<div data-role="popup" id="demo" data-theme="a">
			<a href="#" data-rel="back" data-role="button" data-theme="a"
				data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>

			<div id="demo_info" data-theme="a" style="overflow: auto;">
				<div class="ui-content ui-body-c">
					<p style="text-align: center">
						<strong id="food_name"></strong>
					</p>
					<img id="food_img" class="img-fullwidth" src="" alt="" />
					<div class="ui-content">
						<div id="food_price" class="ui-info"></div>
						<div id="food_intro" class="ui-info"></div>
					</div>
					<ul id="commentlist" data-role="listview" data-inset="true"
						data-theme="d" data-divider-theme="a" data-count-theme="a">
					</ul>
				</div>
			</div>
		</div>
		<%@include
			file="/WEB-INF/views/mobile/business/common/systeminfofooter.jsp"%>
	</div>
</body>