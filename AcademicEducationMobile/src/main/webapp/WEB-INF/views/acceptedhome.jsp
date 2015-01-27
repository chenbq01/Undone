<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link rel="shortcut icon"
	href="<c:url value="/resources/mobile/image/favicon.ico"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/mobile/css/themes/default/jquery.mobile-1.4.5.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/mobile/css/jqm-demos.css"/>">

<script src="<c:url value="/resources/mobile/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/mobile/js/home.js"/>"></script>
<script
	src="<c:url value="/resources/mobile/js/jquery.mobile-1.4.5.js"/>"></script>
</head>

<body>
	<div data-role="page" class="jqm-demos jqm-home">
		<div data-role="header" class="jqm-header">
			<h2>
				<img src="<c:url value="/resources/mobile/image/logo.png"/>"
					alt="Logo">
			</h2>
			<!--p>
				Version <span class="jqm-version"></span>
			</p-->
			<a href="#"
				class="jqm-navmenu-link ui-btn ui-btn-icon-notext ui-corner-all ui-icon-bars ui-nodisc-icon ui-alt-icon ui-btn-left">Menu</a>
			<a href="#popupLogin" data-rel="popup" data-position-to="window"
				class="ui-btn ui-icon-user ui-btn-icon-notext ui-corner-all"
				data-transition="pop">Account</a>
			<div data-role="popup" id="popupLogin" data-theme="a"
				class="ui-corner-all">
				<form>
					<div style="padding: 10px 20px;">
						<h3>请登录</h3>
						<label for="un" class="ui-hidden-accessible">用户名:</label>
						            <input type="text" name="user" id="un" value=""
							placeholder="用户名" data-theme="a">             <label
							for="pw" class="ui-hidden-accessible">密码:</label>             <input
							type="password" name="pass" id="pw" value="" placeholder="密码"
							data-theme="a">             
						<button type="submit"
							class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">登录</button>
					</div>		    
				</form>
			</div>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content jqm-content">
		</div>
		<!-- /content -->
		<div data-role="panel" class="jqm-navmenu-panel" data-position="left"
			data-display="overlay" data-theme="a">
			<ul class="jqm-list ui-alt-icon ui-nodisc-icon">
				<!-- <li data-filtertext="" data-icon="home"><a href="#">首页</a></li>
				<li data-filtertext=""><a href="#" data-ajax="false">菜单</a></li> -->
				<li data-role="list-divider">导航</li>
				<li data-role="collapsible" data-enhanced="true"
					data-collapsed-icon="carat-d" data-expanded-icon="carat-u"
					data-iconpos="right" data-inset="false"
					class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
					<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
						<a href="#"
							class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
							首页<span class="ui-collapsible-heading-status">首页</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="<c:url value="/notice"/>" data-ajax="false">网院公告</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">我的日程</a></li>
						</ul>
					</div>
				</li>
				<li data-role="collapsible" data-enhanced="true"
					data-collapsed-icon="carat-d" data-expanded-icon="carat-u"
					data-iconpos="right" data-inset="false"
					class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
					<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
						<a href="#"
							class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
							学习交流<span class="ui-collapsible-heading-status">学习交流</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">学课程</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">做作业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">做自测</a></li>
						</ul>
					</div>
				</li>
				<li data-role="collapsible" data-enhanced="true"
					data-collapsed-icon="carat-d" data-expanded-icon="carat-u"
					data-iconpos="right" data-inset="false"
					class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
					<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
						<a href="#"
							class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
							考试预约<span class="ui-collapsible-heading-status">考试预约</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">预约考试</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">预约查询</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">统考报名</a></li>
						</ul>
					</div>
				</li>
				<li data-role="collapsible" data-enhanced="true"
					data-collapsed-icon="carat-d" data-expanded-icon="carat-u"
					data-iconpos="right" data-inset="false"
					class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
					<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
						<a href="#"
							class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
							成绩查询<span class="ui-collapsible-heading-status">成绩查询</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">作业成绩</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">自测成绩</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">历史成绩</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">最新成绩</a></li>
						</ul>
					</div>
				</li>
				<li data-filtertext="在线辅导"><a href="#" data-ajax="false">在线辅导</a></li>
			</ul>
		</div>
		<!-- /panel -->
	</div>
</body>

</html>
