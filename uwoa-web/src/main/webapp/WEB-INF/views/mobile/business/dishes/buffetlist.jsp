<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>${sessionScope._profile['rest_name']}</h1>
		</div>
		<%@include file="/WEB-INF/views/mobile/business/common/mainplaceholder.jsp"%>
		<ul data-role="listview" style="margin-top: -10px;" data-inset="true"
			data-theme="a">
			<li data-role="list-divider" data-theme="a">请选择自助餐种类</li>
			<c:forEach var="buffet" items="${buffetlist}">
				<li data-icon="check"
					<c:if
					test="${buffet['id'] == buffetid}">class="ui-btn-active"
					</c:if>><a
					href="javascript:changeit('<c:url value="/dishes/confirmbuffet"/>', '${buffet['id']}');">
						<img width="60px" height="60px" src="${buffet['pic']}">
						<h2>${buffet['buffet_name']} ${buffet['price']}</h2>
						<p>${buffet['memo']}</p>
				</a></li>
			</c:forEach>
		</ul>
		<script type="text/javascript">
			function changeit(url, buffetid) {
				jQuery.ajax({
					type : 'GET',
					//contentType : 'application/json',
					url : url,
					data : {
						"buffetid" : buffetid,
						"orderno" : '${sessionScope._orderno}'
					},
					dataType : 'text',
					success : function(data) {
						alert("操作成功！");
						$(".ui-dialog").dialog("close");
					},
					error : function(data) {
						alert("操作失败！");
					}
				});
			}
		</script>
	</div>
</body>