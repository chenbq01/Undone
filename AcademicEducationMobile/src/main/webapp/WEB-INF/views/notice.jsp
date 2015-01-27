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
				<li data-role="list-divider">网院公告<span class="ui-li-count">3</span></li>
				<li><a href="#">
						<h2>关于近期发现假冒北外网院名义违法招生的免责声明</h2>     
						<p>
							<strong>关键词： com,外网,学员,教育,网络,非法</strong>
						</p>     
						<p>近日，北京外国语大学网络教育学院（以下简称"北外网院"）接到一些学员投诉，反映有部分机构在网络上冒用北外网院名义进行非法招生，此行为已严重损害了北外网院在读学员的切身利益，同时也严重损害了北外网院品牌和良好形象。</p>
						<p class="ui-li-aside">
							<strong>2014-11-28</strong>
						</p>     
				</a></li>
				<li><a href="#">     
						<h2>北京外国语大学2015年春现代远程教育招生简章</h2>     
						<p>
							<strong>关键词： 招生简章,层次及专业,报名办法,录取办法,学制与学分,收费标准</strong>
						</p>     
						<p>北京外国语大学（简称"北外"）是教育部直属、国家首批"211工程"建设的全国重点大学之一，是目前我国高等院校中历史悠久、教授语种最多、办学层次齐全的外国语大学；是我国培养外交、外贸、对外文化交流及外事翻译人才的主要基地。</p>
						        
						<p class="ui-li-aside">
							<strong>2014-10-29</strong>
						</p>     
				</a></li>
				<li><a href="#">     
						<h2>2015年上半年剑桥BEC考试时间及精品课程</h2>     
						<p>
							<strong>关键词： 剑桥BEC,bec考试,剑桥培训,报名,考试</strong>
						</p>     
						<p>剑桥商务英语证书（Cambridge Business English Certificate简称BEC考试）
							是英国剑桥考试委员会所推出的剑桥系列考试中专为学习者提供的国际商务英语资格证书考试，因特别注重考生运用英语在商务环境中进行交际的能力，得到全球商业界广泛认可，在中国各涉外经济部门以及在华投资的跨国公司中更是倍受青睐。</p>
						        
						<p class="ui-li-aside">
							<strong>2015-01-05</strong>
						</p>     
				</a></li>
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
							首页<span class="ui-collapsible-heading-status">首页</span>
						</a>
					</h3>
					<div
						class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed"
						aria-hidden="true">
						<ul>
							<li data-filtertext=""><a href="#" data-ajax="false">网院公告</a></li>
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