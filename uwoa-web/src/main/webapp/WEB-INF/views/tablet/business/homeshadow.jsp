<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>${sessionScope._profile['rest_name']}</h1>
		</div>
		<!--div data-role="content"-->
		<table>
			<tr valign="top">
				<td width="25%">
					<ul data-role="listview" data-inset="true" data-theme="a">
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
							<div data-role="popup" id="popupMenu" data-theme="a">
								<div data-role="popup" id="popupOrgOrderNo"
									class="ui-corner-all" data-theme="a">
									<div style="padding: 10px 20px;">
										<h3>请填写原订单号</h3>
										<input name="add_orderno1" value="" placeholder="订单号"
											data-theme="a" type="text"> <a href="#"
											data-role="button" data-inline="true" data-theme="b"
											data-icon="check" data-corners="false"
											onclick="javascript:AddDish1();">确定</a><a href="#"
											data-role="button" data-inline="true" data-rel="back"
											data-theme="a" data-corners="false">取消</a>
									</div>
								</div>
							</div></li>
						<li><a href="#popupOrderNo1" data-rel="popup"
							data-transition="pop"> <img width="60px" height="60px"
								src="<c:url value="/resources/tablet/images/icon/yqd.png"/>">
								<h2>一起点</h2>
								<p>告知同伴订单号一起参与点餐</p></a>
							<div data-role="popup" id="popupMenu" data-theme="a">
								<div data-role="popup" id="popupOrderNo1" class="ui-corner-all"
									data-theme="a">
									<div style="padding: 10px 20px;">
										<h3>请填写原订单号</h3>
										<input name="together_orderno1" value="" placeholder="订单号"
											data-theme="a" type="text"> <a href="#"
											data-role="button" data-inline="true" data-theme="b"
											data-icon="check" data-corners="false"
											onclick="javascript:Together1();">确定</a><a href="#"
											data-role="button" data-inline="true" data-rel="back"
											data-theme="a" data-corners="false">取消</a>
									</div>
								</div>
							</div></li>
						<c:if test="${sessionScope._profile['open_vip'] == '1'}">
							<li><a href="#popupMobileNo1" data-rel="popup"
								data-transition="pop"> <img width="60px" height="60px"
									src="<c:url value="/resources/tablet/images/icon/acdc.png"/>">
									<h2>爱吃的菜</h2>
									<p>验证手机号看看自己在这里最爱吃的菜</p></a>
								<div data-role="popup" id="popupMenu" data-theme="a">
									<div data-role="popup" id="popupMobileNo1"
										class="ui-corner-all" data-theme="a">
										<div style="padding: 10px 20px;">
											<h3>请填写并验证手机号</h3>
											<input name="mobileno1" value="" placeholder="手机号"
												data-theme="a" type="text"> <a href="#"
												data-role="button" data-corners="false" data-theme="b"
												onclick="javascript:GetMobileRandomCode1();">获取验证码</a> <input
												name="randomno1" value="" placeholder="手机验证码" data-theme="a"
												type="text"> <a href="#" data-role="button"
												data-inline="true" data-theme="b" data-icon="check"
												data-corners="false" onclick="javascript:ToFavourite1();">确定</a><a
												href="#" data-role="button" data-inline="true"
												data-rel="back" data-theme="a" data-corners="false">取消</a>
										</div>
									</div>
								</div></li>
						</c:if>
						<li><a href="#popupOgrOrderNo4DP1" data-rel="popup"
							data-transition="pop"> <img width="60px" height="60px"
								src="<c:url value="/resources/tablet/images/icon/dp.png"/>">
								<h2>推荐点评</h2>
								<p>用餐结束时可以留下自己的反馈</p></a>
							<div data-role="popup" id="popupMenu" data-theme="a">
								<div data-role="popup" id="popupOgrOrderNo4DP1"
									class="ui-corner-all" data-theme="a">
									<div style="padding: 10px 20px;">
										<h3>请填写原订单号</h3>
										<input name="comment_orderno1" value="" placeholder="订单号"
											data-theme="a" type="text"> <a href="#"
											data-role="button" data-inline="true" data-theme="b"
											data-icon="check" data-corners="false"
											onclick="javascript:Comment1();">确定</a><a href="#"
											data-role="button" data-inline="true" data-rel="back"
											data-theme="a" data-corners="false">取消</a>
									</div>
								</div>
							</div></li>
					</ul>
				</td>
				<td width="75%">
					<table>
						<tr valign="top">
							<td width="50%" nowrap><%@include
									file="/WEB-INF/views/tablet/business/common/mainimage.jsp"%></td>
							<td nowrap>
								<div class="ui-content">
									<div class="ui-info">
										<strong>餐厅简介2：</strong>
									</div>
									<div class="ui-info">${sessionScope._profile['rest_intr']}</div>
									<div class="ui-info">
										<strong>餐厅地址：</strong>
									</div>
									<div class="ui-info">${sessionScope._profile['rest_address']}</div>
									<div class="ui-info">
										<strong>联系电话：</strong>
									</div>
									<div class="ui-info">${sessionScope._profile['rest_phone']}</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--/div-->
		<%@include
			file="/WEB-INF/views/tablet/business/common/systeminfofooter.jsp"%>
		<script type="text/javascript">
			<c:if test="${sessionScope._profile['open_vip'] == '1'}">
			function GetMobileRandomCode1() {
				var mobileno = $("input[name='mobileno1']");
				var value = mobileno.val();
				if ($.trim(value).length == 0) {
					mobileno.focus();
					mobileno.attr("placeholder", "请先输入手机号");
					return;
				}
				var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if (!mobile.test(value)) {
					mobileno.focus();
					mobileno.attr("placeholder", "请输入正确的手机号");
					mobileno.val("");
					return;
				}
				jQuery.ajax({
					type : 'GET',
					//contentType : 'application/json',
					url : '<c:url value="/mobileorder/randomcode"/>',
					data : {
						"mobileno" : value
					},
					dataType : 'text',
					success : function(data) {
						alert("手机验证码已经发送，请查收！");
					},
					error : function(data) {
						alert("无法获取手机验证码！");
					}
				});
			}

			function ToFavourite1() {
				var mobileno = $("input[name='mobileno1']");
				var randomno = $("input[name='randomno1']");
				var value = mobileno.val();
				var randomnovalue = randomno.val();
				if ($.trim(value).length == 0) {
					mobileno.focus();
					mobileno.attr("placeholder", "请先输入手机号");
					return;
				}
				if ($.trim(randomnovalue).length == 0) {
					randomno.focus();
					randomno.attr("placeholder", "请先输入手机验证码");
					return;
				}
				var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
				if (!mobile.test(value)) {
					mobileno.focus();
					mobileno.attr("placeholder", "请输入正确的手机号");
					mobileno.val("");
					return;
				}
				jQuery
						.ajax({
							type : 'GET',
							//contentType : 'application/json',
							url : '<c:url value="/mobileorder/valid/mobilenowithrandomcode"/>',//<c:url value="/dishes/favouritelist"/>
							data : {
								"mobileno" : value,
								"randomno" : randomnovalue
							},
							dataType : 'text',
							success : function(data) {
								mobileno.val("");
								randomno.val("");
								if (data.length == 0) {
									alert("手机号或验证码输入有误！");
								} else {
									var url = "<c:url value="/dishes/favouritelist"/>";
									var goUrl = "";
									if (url.indexOf(";jsessionid") != -1) {
										goUrl = url.substring(0, url
												.indexOf(";jsessionid"))
												+ "/"
												+ value
												+ url
														.substring(url
																.indexOf(";jsessionid"))
									} else {
										goUrl = url + "/" + value;
									}
									$.mobile.navigate(goUrl);
								}
							},
							error : function(data) {
								mobileno.val("");
								randomno.val("");
								alert("无法验证手机号及验证码！");
							}
						});
			}
			</c:if>

			function AddDish1() {
				var orderno = $("input[name='add_orderno1']");
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

			function Together1() {
				var orderno = $("input[name='together_orderno1']");
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

			function Comment1() {
				var orderno = $("input[name='comment_orderno1']");
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
														+ "/" + data);
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