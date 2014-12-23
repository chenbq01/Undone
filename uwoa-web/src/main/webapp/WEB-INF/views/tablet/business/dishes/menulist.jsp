<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<%@include
			file="/WEB-INF/views/tablet/business/common/ordernoheader.jsp"%>
		<!--div data-role="content"-->
		<%--@include
				file="/WEB-INF/views/tablet/business/common/mainplaceholder.jsp"--%>
		<table>
			<tr valign="top">
				<td width="25%"><%@include
						file="/WEB-INF/views/tablet/business/common/navsider.jsp"%>
				</td>
				<td><c:if test="${menulist.size() == 0}">
						<p style="text-align: center">暂无菜品</p>
					</c:if>
					<div data-role="collapsible-set" data-theme="a"
						data-content-theme="d">
						<c:set value="#typename#" var="typename" scope="page"></c:set>
						<c:forEach var="menu" items="${menulist}">
							<c:if test="${typename != menu['type_name']}">
								<c:if test="${typename != '#typename#'}">
									</ul>
					</div> </c:if> <c:set value="${menu['type_name']}" var="typename" scope="page"></c:set>
					<div data-role="collapsible">
						<h2>${menu['type_name']}</h2>
						<ul data-role="listview" data-split-icon="plus"
							data-split-theme="d" data-inset="false" data-filter="true"
							data-filter-placeholder="" data-filter-theme="c">
							</c:if>
							<li><a href="#"
								onclick="javascript:getDishesCommentData('${menu['id']}');">
									<img width="80px" height="80px" src="${menu['pic']}"><span
									id="${menu['id']}" class="ui-li-count">${menu['ordernum']
										== null?"0":(menu['ordernum']==0?"?":menu['ordernum'])}</span>
									<h2>${menu['food_name']}</h2>
									<p>${menu['price']}元/${menu['unit']}&nbsp;&nbsp;推荐次数${menu['vote_num']}</p>
									<p>${menu['food_intro']}</p>
							</a> <!--a href="<c:url value="/dishes/${menu['id']}/prompt"/>"
						data-rel="dialog">我想吃</a--> <a
								href="javascript:quickbuy('<c:url value="/mobileorder/temporary/quickbuy"/>', '${sessionScope._orderno}', '${menu['id']}', '${menu['food_name']}', '${menu['unit']}', '${menu['price']}');">我想吃</a></li>
							</c:forEach>
							<c:if test="${menulist.size() > 0}">
						</ul>
					</div> </c:if></td>
			</tr>
		</table>
		<!--/div-->
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
		<%--@include file="/WEB-INF/views/tablet/business/common/navfooter.jsp"--%>
		<%@include
			file="/WEB-INF/views/tablet/business/common/systeminfofooter.jsp"%>
	</div>
</body>
