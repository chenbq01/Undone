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
				<fieldset class="ui-grid-b">
					<div class="ui-block-a">
						<a id="_minus" href="#" data-role="button" data-iconpos="notext"
							data-mini="true" data-icon="minus" data-theme="a"
							style="float: right" onclick="javascript:minus();">减少</a>
					</div>
					<div class="ui-block-b">
						<input id="_num" name="_num" type="text" data-mini="true"
							data-clear-btn="false" value="${num}">
					</div>
					<div class="ui-block-c">
						<a id="_plus" href="#" data-role="button" data-iconpos="notext"
							data-mini="true" data-icon="plus" data-theme="a" onclick="javascript:plus();">增加</a>
					</div>
				</fieldset>
				<p></p>
				<a id="_confirm" href="#" data-role="button" data-theme="a"
					data-icon="check" data-inline="true" data-mini="true"
					data-corners="false" onclick="javascript:_confirm();">确定</a> <a href="index.html" data-role="button"
					data-rel="back" data-inline="true" data-mini="true"
					data-corners="false">取消</a>
			</div>
		</div>
		<script type="text/javascript">
			function minus() {
				var _numInput = $("#_num");
				var _numInt = parseInt(_numInput.val());
				if (_numInt > 0) {
					_numInput.val(_numInt - 1);
				}
			}
			function plus() {
				var _numInput = $("#_num");
				var _numInt = parseInt(_numInput.val());
				_numInput.val(_numInt + 1);
			}
			function _confirm() {
				jQuery
						.ajax({
							type : 'GET',
							//contentType : 'application/json',
							url : '<c:url value="/mobileorder/temporary/${sessionScope._orderno}/modify"/>',
							data : {
								"dishid" : "${dishinfo['id']}",
								"dishname" : encodeURI("${dishinfo['food_name']}"),
								"num" : $("#_num").val(),
								"unit" : "${dishinfo['unit']}",
								"price" : "${dishinfo['price']}"
							},
							dataType : 'text',
							success : function(data) {
								alert("修改成功！");
								$(".ui-dialog").dialog("close");
							},
							error : function(data) {
								alert("修改失败！");
							}
						});
			}
		</script>
	</div>
</body>