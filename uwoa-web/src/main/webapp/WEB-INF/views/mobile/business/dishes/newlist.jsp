<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page" data-fullscreen="true">
		<%@include
			file="/WEB-INF/views/mobile/business/common/ordernoheader.jsp"%>
		<div data-role="content">
			<%@include
				file="/WEB-INF/views/mobile/business/common/mainplaceholder.jsp"%>
			<c:if test="${newlist.size() == 0}">
				<p style="text-align: center">暂无新品推介</p>
			</c:if>
			<ul data-role="listview" data-split-icon="plus" data-split-theme="d"
				data-inset="true">
				<c:forEach var="news" items="${newlist}">
					<li><a href="#"
						onclick="javascript:getDishesCommentData('${news['id']}');"> <img
							width="80px" height="80px" src="${news['pic']}"><span
							id="${news['id']}" class="ui-li-count">${news['ordernum']
								== null?"0":(news['ordernum']==0?"?":news['ordernum'])}</span>
							<h2>${news['food_name']}</h2>
							<p>${news['price']}元/${news['unit']}&nbsp;&nbsp;推荐次数${news['vote_num']}</p>
							<p>${news['food_intro']}</p>
					</a> <!--a href="<c:url value="/dishes/${news['id']}/prompt"/>"
						data-rel="dialog">我想吃</a--> <a
						href="javascript:quickbuy('<c:url value="/mobileorder/temporary/quickbuy"/>', '${sessionScope._orderno}', '${news['id']}', '${news['food_name']}', '${news['unit']}', '${news['price']}');">我想吃</a></li>
				</c:forEach>
			</ul>
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
		<%@include file="/WEB-INF/views/mobile/business/common/navfooter.jsp"%>
	</div>
</body>