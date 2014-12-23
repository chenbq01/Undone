<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page" data-fullscreen="true">
		
		<div data-role="content">
			<a onclick="showpup();" href="#">测试弹出窗口</a>
			
			<ul data-role="listview" style="margin-top: -10px;" data-inset="true"
				data-theme="a">
				<li><a href="<c:url value="/dishes/specialtylist"/>"> <img
						width="60px" height="60px"
						src="<c:url value="/resources/tablet/images/icon/dc.png"/>">
						<h2>点餐</h2>
						<p>浏览菜单自助点餐</p>
				</a></li>
				<li><a href="#popupOrgOrderNo" data-rel="popup"
					data-transition="pop"> <img width="60px" height="60px"
						src="<c:url value="/resources/tablet/images/icon/jc.png"/>">
						<h2>加餐</h2>
						<p>输入已提交订单的订单号加菜</p></a>
					<div data-role="popup" id="popupMenu_diancan" data-theme="a">
						
							<div style="padding: 10px 20px;">
								<h3>请填写原订单号</h3>
								<input name="add_orderno" value="" placeholder="订单号"
									data-theme="a" type="text"> <a id="a_adddish" href="#"
									data-role="button" data-inline="true" data-theme="b"
									data-icon="check" data-corners="false"
									onclick="javascript:AddDish();">确定</a><a href="#"
									data-role="button" data-inline="true" data-rel="back"
									data-theme="a" data-corners="false">取消</a>
							</div>
						
					</div></li>
				<li><a href="#popupOrderNo" data-rel="popup"
					data-transition="pop"> <img width="60px" height="60px"
						src="<c:url value="/resources/tablet/images/icon/yqd.png"/>">
						<h2>一起点</h2>
						<p>告知同伴订单号一起参与点餐</p></a>
					<div data-role="popup" id="popupMenu" data-theme="a">
						<div data-role="popup" id="popupOrderNo" class="ui-corner-all"
							data-theme="a">
							<div style="padding: 10px 20px;">
								<h3>请填写原订单号</h3>
								<input name="together_orderno" value="" placeholder="订单号"
									data-theme="a" type="text"> <a id="a_together" href="#"
									data-role="button" data-inline="true" data-theme="b"
									data-icon="check" data-corners="false"
									onclick="javascript:Together();">确定</a><a href="#"
									data-role="button" data-inline="true" data-rel="back"
									data-theme="a" data-corners="false">取消</a>
							</div>
						</div>
					</div></li>
				
				<li><a href="#popupOgrOrderNo4DP" data-rel="popup"
					data-transition="pop"> <img width="60px" height="60px"
						src="<c:url value="/resources/tablet/images/icon/dp.png"/>">
						<h2>推荐点评</h2>
						<p>用餐结束时可以留下自己的反馈</p></a>
					<div data-role="popup" id="popupMenu" data-theme="a">
						<div data-role="popup" id="popupOgrOrderNo4DP"
							class="ui-corner-all" data-theme="a">
							<div style="padding: 10px 20px;">
								<h3>请填写原订单号</h3>
								<input name="comment_orderno" value="" placeholder="订单号"
									data-theme="a" type="text"> <a id="a_comment" href="#"
									data-role="button" data-inline="true" data-theme="b"
									data-icon="check" data-corners="false"
									onclick="javascript:Comment();">确定</a><a href="#"
									data-role="button" data-inline="true" data-rel="back"
									data-theme="a" data-corners="false">取消</a>
							</div>
						</div>
					</div></li>
			</ul>
		</div>
		<%@include
			file="/WEB-INF/views/tablet/business/common/systeminfofooter.jsp"%>
		<script type="text/javascript">
			function showpup()
			{
				$( "#popupMenu_diancan" ).popup( "open" )
			}
			$( "#popupMenu_diancan" ).on(
					{    
						popupbeforeposition: function() 
						{        
							var h = $( window ).height();         
							$( "#popupMenu_diancan" ).css( "height", h );    
						}
					});
			function AddDish() {
				var orderno = $("input[name='add_orderno']");
				var value = orderno.val();
				if ($.trim(value).length == 0) {
					orderno.focus();
					orderno.attr("placeholder", "请先输入订单号");
					return;
				} else {
					jQuery
							.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : '<c:url value="/mobileorder/valid/org/orderno"/>',
								data : {
									"orderno" : value
								},
								dataType : 'text',
								success : function(data) {
									if (data.length == 0) {
										alert("订单号输入有误！");
									} else {
										$.mobile
												.navigate("<c:url value="/dishes/specialtylist"/>");
									}
								},
								error : function(data) {
									alert("无法验证订单号！");
								}
							});
				}
			}
			
			function Together() {
				var orderno = $("input[name='together_orderno']");
				var value = orderno.val();
				if ($.trim(value).length == 0) {
					orderno.focus();
					orderno.attr("placeholder", "请先输入订单号");
					return;
				} else {
					jQuery
							.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : '<c:url value="/mobileorder/valid/temporary/orderno"/>',
								data : {
									"orderno" : value
								},
								dataType : 'text',
								success : function(data) {
									if (data.length == 0) {
										alert("订单号输入有误！");
									} else {
										alert("同伴一起点餐时，以最早提交的订单为准！");
										$.mobile
												.navigate("<c:url value="/dishes/specialtylist"/>");
									}
								},
								error : function(data) {
									alert("无法验证订单号！");
								}
							});
				}
			}

			function Comment() {
				var orderno = $("input[name='comment_orderno']");
				var value = orderno.val();
				if ($.trim(value).length == 0) {
					orderno.focus();
					orderno.attr("placeholder", "请先输入订单号");
					return;
				} else {
					jQuery
							.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : '<c:url value="/mobileorder/valid/comment/orderno"/>',
								data : {
									"orderno" : value
								},
								dataType : 'text',
								success : function(data) {
									if (data.length == 0) {
										alert("订单号输入有误！");
									} else {
										$.mobile
												.navigate("<c:url value="/mobileorder/disheslist"/>"
														+ "/"
														+ data);
									}
								},
								error : function(data) {
									alert("无法验证订单号！");
								}
							});
				}
			}
		</script>
	</div>
	

</body>