<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>${dishinfo['food_name']}</h1>
		</div>
		<div data-role="content">
			<img class="img-fullwidth" src="${dishinfo['pic']}"
				alt="${dishinfo['food_name']}" />
			<div data-theme="d" data-overlay-theme="b" class="ui-content"
				style="max-width: 340px; padding-bottom: 2em;">
				<p>
					<strong>${dishinfo['price']}元/${dishinfo['unit']}&nbsp;&nbsp;推荐次数${dishinfo['vote_num']}</strong>
				</p>
				<p></p>
				<a id="_confirm" href="#" data-role="button" data-theme="a"
					data-icon="check" data-inline="true" data-mini="true"
					data-corners="false" onclick="javascript:_confirm();">确定</a> <a
					href="index.html" data-role="button" data-rel="back"
					data-inline="true" data-mini="true" data-corners="false">取消</a>
			</div>
		</div>
		<script type="text/javascript">
			function _confirm() {
				jQuery
						.ajax({
							type : 'GET',
							//contentType : 'application/json',
							url : '<c:url value="/mobileorder/temporary/${sessionScope._orderno}/add"/>',
							data : {
								"dishid" : "${dishinfo['id']}",
								"dishname" : encodeURI("${dishinfo['food_name']}"),
								"num" : "0",
								"unit" : "${dishinfo['unit']}",
								"price" : "${dishinfo['price']}"
							},
							dataType : 'text',
							success : function(data) {
								alert("添加成功！");
								$(".ui-dialog").dialog("close");
							},
							error : function(data) {
								alert("添加失败！");
							}
						});
			}
		</script>
	</div>
</body>