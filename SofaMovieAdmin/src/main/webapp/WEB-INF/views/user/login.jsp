<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/resources/img/favicon.ico"/>" media="screen" />

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,600,800">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/font-awesome.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap-responsive.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/ui-lightness/jquery-ui-1.8.21.custom.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/js/plugins/msgbox/jquery.msgbox.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/application.css"/>">

<script src="<c:url value="/resources/js/libs/modernizr-2.5.3.min.js"/>"></script>

</head>

<body class="login">



	<div class="account-container login stacked">

		<div class="content clearfix">

			<form>
				<h1>沙发院线网站后台管理系统</h1>
				<div class="login-fields">

					<p>登录您的账号</p>
					<div class="field">
						<label for="username">用户名:</label> <input type="text"
							id="username" name="username" value="" placeholder="用户名"
							class="login username-field" />
					</div>
					<!-- /field -->

					<div class="field">
						<label for="password">密码:</label> <input type="password"
							id="password" name="password" value="" placeholder="密码"
							class="login password-field" />
					</div>
					<!-- /password -->

				</div>
				<!-- /login-fields -->
				<div class="login-actions">

					<!-- <button class="button btn btn-primary btn-large">登录</button> -->
					<input id="btn_login" type="button" value="登录"
						class="button btn btn-primary btn-large" />
				</div>
				<!-- .actions -->

			</form>

		</div>
		<!-- /content -->

	</div>
	<!-- /account-container -->

	<!-- /login-extra -->




	<script src="<c:url value="/resources/js/libs/jquery-1.7.2.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/libs/jquery-ui-1.8.21.custom.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/libs/jquery.ui.touch-punch.min.js"/>"></script>

	<script
		src="<c:url value="/resources/js/libs/bootstrap/bootstrap.min.js"/>"></script>

	<script src="<c:url value="/resources/js/signin.js"/>"></script>

	<script
		src="<c:url value="/resources/js/plugins/msgbox/jquery.msgbox.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery.blockUI.js"/>"></script>


	<script>
		$(function() {

			$(document).ajaxStart(function() {
				$.blockUI({
					message : '请稍后...'
				});
			});

			$(document).ajaxComplete(function() {
				$.unblockUI();
			});

			$("#btn_login").click(
					function() {
						$.post("<c:url value="/user/login"/>?_json=true", $(
								"form").serialize(), function(data) {
							if (data.flag == "true") {
								window.location.href = "<c:url value="/"/>";
							} else {
								$.msgbox(data.msg, {
									buttons : [ {
										type : "submit",
										value : "确定"
									} ]
								});
							}
						});
					});

		});
	</script>

</body>
</html>