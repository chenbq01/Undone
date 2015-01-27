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
			<ul data-role="listview" data-inset="true">
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/0.ico"/>">
					<h2>招生简章</h2>
						<p>招生简章</p></a></li>
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/12.ico"/>">
					<h2>报名流程</h2>
						<p>报名流程</p></a></li>
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/18.ico"/>">
					<h2>证书样式</h2>
						<p>证书样式</p></a></li>
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/10.ico"/>">
					<h2>专业介绍</h2>
						<p>专业介绍</p></a></li>
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/7.ico"/>">
					<h2>常见问题</h2>
						<p>常见问题</p></a></li>
				<li><a href="#"><img
						src="<c:url value="/resources/mobile/image/icon/11.ico"/>">
					<h2>课程体验</h2>
						<p>课程体验</p></a></li>
			</ul>
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
							学历教育<span class="ui-collapsible-heading-status">学历教育</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">英语专业（七个专业方向）</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">电子商务专业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">会计学专业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">金融学专业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">国际经济与贸易专业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">工商管理专业</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">信息管理与信息技术专业</a></li>
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
							非学历教育<span class="ui-collapsible-heading-status">非学历教育</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">留学预科</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">剑桥英语</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">企业培训</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">港大面试</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">外教口语</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">商务英语</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">网络课堂</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">青少英语</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">小语种培训</a></li>
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
							学习资源<span class="ui-collapsible-heading-status">学习资源</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">北外名师讲座</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">北外名师公开课</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">北外网络课堂</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">英语学习资讯</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">小语种学习</a></li>
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
							免费测评<span class="ui-collapsible-heading-status">免费测评</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">免费英语词汇量测评</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">免费英语水平测评</a></li>
							<li data-filtertext=""><a href="#" data-ajax="false">北外网络调研问卷（赠网课）</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<!-- /panel -->
	</div>
</body>

</html>
