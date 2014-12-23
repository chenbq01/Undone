<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>请填写并验证手机号</h1>
		</div>
		<div data-role="content">
			<input name="mobileno1" value="" placeholder="手机号" data-theme="a"
				type="text"> <a href="#" data-role="button"
				data-corners="false" data-theme="b" data-mini="true"
				onclick="javascript:GetMobileRandomCode1();">获取验证码</a> <input
				name="randomno1" value="" placeholder="手机验证码" data-theme="a"
				type="text"> <a href="#" data-role="button" data-theme="b"
				data-icon="check" data-inline="true" data-mini="true"
				data-corners="false" onclick="javascript:ToValid1();">确定</a> <a
				href="index.html" data-role="button" data-rel="back"
				data-inline="true" data-mini="true" data-corners="false">取消</a>
		</div>
		<script type="text/javascript">
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
			function ToValid1() {
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
									$(".ui-dialog").dialog("close");
								}
							},
							error : function(data) {
								mobileno.val("");
								randomno.val("");
								alert("无法验证手机号及验证码！");
							}
						});
			}
		</script>
	</div>
</body>