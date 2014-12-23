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
				<textarea cols="40" rows="8" name="remark" placeholder="点评内容"></textarea>
				<p></p>
				<a id="a_submitcomment" href="#" data-role="button" data-theme="a"
					data-icon="check" data-inline="true" data-mini="true"
					data-corners="false" onclick="javascript:submitcomment();">确定</a> <a href="#" data-role="button"
					data-rel="back" data-inline="true" data-mini="true"
					data-corners="false">取消</a>
			</div>
		</div>
		<script type="text/javascript">
			function submitcomment() {
				var remark = $("textarea[name='remark']");
				var value = remark.val();
				if ($.trim(value).length == 0) {
					remark.focus();
					remark.attr("placeholder", "请先输入点评内容");
					return;
				} else {
					jQuery.ajax({
						type : 'GET',
						//contentType : 'application/json',
						url : '<c:url value="/dishes/comment/add"/>',
						data : {
							"dishid" : "${dishinfo['id']}",
							"orderid" : "${orderid}",
							"remark" : encodeURI(value)
						},
						dataType : 'text',
						success : function(data) {
							if (data == '0') {
								alert("评论提交失败！");
							} else {
								alert("评论已经提交！");
								$(".ui-dialog").dialog("close");
							}
						},
						error : function(data) {
							alert("无法提交评论！");
						}
					});
				}
			}
		</script>
	</div>
</body>