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
			<c:if test="${promotionlist.size() == 0}">
				<p style="text-align: center">暂无特价菜品</p>
			</c:if>
			<ul data-role="listview" data-split-icon="plus" data-split-theme="d"
				data-inset="true">
				<c:forEach var="promotion" items="${promotionlist}">
					<li><a href="#"
						onclick="javascript:getDishesCommentData('${promotion['id']}');">
							<img width="80px" height="80px" src="${promotion['pic']}"><span
							id="${promotion['id']}" class="ui-li-count">${promotion['ordernum']
								==
								null?"0":(promotion['ordernum']==0?"?":promotion['ordernum'])}</span>
							<h2>${promotion['food_name']}</h2>
							<p>
								<strike>${promotion['price']}元/${promotion['unit']}</strike>&nbsp;&nbsp;<strong>${promotion['tprice']}元/${promotion['unit']}</strong>&nbsp;&nbsp;推荐次数${promotion['vote_num']}
							</p>
							<p>${promotion['food_intro']}</p>
					</a> <!--a href="<c:url value="/dishes/${promotion['id']}/prompt"/>"
						data-rel="dialog">我想吃</a--> <a
						href="javascript:quickbuy('<c:url value="/mobileorder/temporary/quickbuy"/>', '${sessionScope._orderno}', '${promotion['id']}', '${promotion['food_name']}', '${promotion['unit']}', '${promotion['tprice']}');">我想吃</a></li>
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