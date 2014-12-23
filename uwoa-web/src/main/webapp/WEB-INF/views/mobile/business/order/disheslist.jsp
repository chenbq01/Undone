<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div data-role="page" data-fullscreen="true">
		<div data-role="header" data-position="fixed">
			<a
				href="<c:url value="/mobile/${sessionScope._profile['rest_code']}?shadow"/>"
				data-corners="false" data-icon="back">返回</a>
			<h1>订单号：${fn:substring(orderinfo['order_no'],fn:length(orderinfo['order_no'])-6,fn:length(orderinfo['order_no']))}</h1>
		</div>
		<div data-role="content">
			<%@include file="/WEB-INF/views/mobile/business/common/mainplaceholder.jsp"%>
			<ul data-role="listview" data-split-icon="edit" data-split-theme="d"
				data-inset="true">
				<li data-role="list-divider" data-theme="e">可推荐<strong
					id="recommendnum">${orderinfo['vote_count']}</strong>次
				</li>
				<c:forEach var="dish" items="${disheslist}">
					<li><a id="${dish['id']}" name="recommend" href="#"
						onclick="javascript:recommend(this);"> <img width="80px"
							height="80px" src="${dish['pic']}">
							<h2>${dish['food_name']}</h2>
							<p>
								${dish['price']}元/${dish['unit']}&nbsp;&nbsp;<u><strong>推荐</strong></u>
							</p>
							<p>${dish['food_intro']}</p>
					</a> <a
						href="<c:url value="/dishes/${orderinfo['id']}/${dish['id']}/comment"/>"
						data-rel="dialog">点评</a></li>
				</c:forEach>
			</ul>
		</div>
		<%@include
			file="/WEB-INF/views/mobile/business/common/systeminfofooter.jsp"%>
		<script type="text/javascript">
			function recommend(obj) {
				var dishid = obj.id;
				var recommendnum = $("#recommendnum").html();
				if (recommendnum == "0") {
					alert("已超过推荐次数！");
					return;
				} else {
					jQuery.ajax({
						type : 'GET',
						//contentType : 'application/json',
						url : '<c:url value="/dishes/recommend"/>',
						data : {
							"dishid" : dishid,
							"orderid" : "${orderinfo['id']}"
						},
						dataType : 'text',
						success : function(data) {
							if (data == '0') {
								alert("推荐失败！");
							} else {
								alert("推荐成功！");
								$("#recommendnum").html(
										parseInt(recommendnum) - 1);
							}
						},
						error : function(data) {
							alert("未能完成推荐！");
						}
					});
				}
			}
		</script>
	</div>
</body>
