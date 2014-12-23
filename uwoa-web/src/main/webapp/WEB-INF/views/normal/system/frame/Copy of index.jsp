<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/normal/css/ui-lightness/jquery-ui-1.10.3.custom.css" />
<script
	src="${pageContext.request.contextPath}/resources/normal/js/jquery-1.9.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/normal/js/jquery-ui-1.10.3.custom.js"></script>
  <script>
  $(function() {
    $( "#side" ).accordion({
      heightStyle: "content"
    });
  });
  </script>
<style type="text/css">
html,body {
	overflow: hidden;
	height: 100%;
	margin: 0;
	padding: 0;
	font: 14px/1.8 Georgia, Arial, Simsun;
}

html {
	_padding: 110px 0;
}

iframe {
	width: 100%;
	height: 100%;
}

#hd {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 150px;
	background: #ffffff;
}

#bd {
	position: absolute;
	top: 150px;
	right: 0;
	bottom: 0;
	left: 0;
	overflow: hidden;
	width: 100%;
	_height: 100%;
}

#side {
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	overflow: auto;
	width: 250px;
	_height: 100%;
	background: #FFFFFF;
}

#main {
	position: absolute;
	_position: static;
	top: 0;
	right: 0;
	bottom: 0;
	left: 250px;
	overflow: auto;
	_overflow: hidden;
	height: 100%;
	_height: 100%;
	_margin-left: 250px;
	background: #FFFFFF;
}

#content {
	_overflow: auto;
	_width: 100%;
	_height: 100%;
}

/* 与布局无关，一些说明性内容样式 */
.tit-layout {
	margin: 30px 0 0;
	font-size: 18px;
	text-align: center;
}

.copyright {
	font-weight: bold;
	text-align: center;
}

#feature {
	width: 200%;
	line-height: 4;
}

#feature .hd {
	padding: 20px 15px;
}

#feature .hd h2 {
	margin: 0;
	font-size: 16px;
}

#feature .bd ol {
	margin-top: 0;
}

#feature .bd h3 {
	margin: 0;
	padding: 0 15px;
	font-size: 14px;
}

#feature .ft {
	padding: 10px 15px 30px;
}
</style>
</head>
<body>
	<div id="hd">
		<table border="0" cellspacing="0" cellpadding="0" width=100%
			height="150px">
			<tr>
				<td><img
					src="${pageContext.request.contextPath}/resources/normal/images/banner_logo.jpg"></img></td>
				<td width="100%"
					background="${pageContext.request.contextPath}/resources/normal/images/banner_bg.jpg"></td>
			</tr>
		</table>
	</div>
	<div id="bd">
		<div id="side">
			<h3>餐厅设置</h3>
			<div>
				<ul>
					<li><a href="${pageContext.request.contextPath}/food/foodType"
						target="mainFrame">菜品分类管理</a></li>
					<li><a href="${pageContext.request.contextPath}/food/food"
						target="mainFrame">菜品维护</a></li>
					<li><a
						href="${pageContext.request.contextPath}/food/characteristic"
						target="mainFrame">特色菜</a></li>
					<li><a href="${pageContext.request.contextPath}/food/new"
						target="mainFrame">新品推荐</a></li>
					<li><a href="${pageContext.request.contextPath}/food/special"
						target="mainFrame">限时特价</a></li>
					<li><a href="${pageContext.request.contextPath}/food/comment"
						target="mainFrame">菜品点评管理</a></li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
					<li>aaa</li>
				</ul>
			</div>
			<h3>菜品管理</h3>
			<div>
				<p>2dafdsafasd
			</div>
			<h3>系统管理</h3>
			<div>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/system/dictionary"
						target="mainFrame">数据字典</a></li>
				</ul>
			</div>
			<h3>样例</h3>
			<div>
				<ul>
					<li><a href="${pageContext.request.contextPath}/demo/student"
						target="mainFrame">学生管理</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<div id="content">
				<iframe name="mainFrame"></iframe>
			</div>
		</div>
	</div>
</body>
</html>