<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>  
<%@ page import="org.springframework.security.web.WebAttributes" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="utf-8" />
<meta name="author" content="tonglaiz" />
<meta name="copyright" content="tonglaiz 版权所有，未经授权禁止链接、复制或建立镜像。" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<!--[if gt IE 7]>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<![endif]-->
<title>餐饮服务管理系统</title>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/normal/css/global.css" />
<script src="js/jquery.js"></script>

</head>

<script language="javascript" type="text/javascript">
$(function() {
	var resizeTimer = null;
	var bodyheight;
	$("body:eq(0)").addClass('login_body');
	function doResize(){
		var	h_body= parseInt(($(window).height() - $(".login").height())/2) + "px";
		if(bodyheight != h_body){
			bodyheight = h_body;
			$(".login").css("margin-top",h_body);
		}
		if(resizeTimer) clearTimeout(resizeTimer);
		resizeTimer = setTimeout(doResize,0);
	}
	doResize();
 });
</script>
<% String s = "";
if(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!=null)
{
	s = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION).toString();
}%>
<div class="login">
	<h2 title="餐饮服务管理系统">餐饮服务管理系统</h2>
	<div class="cont">
		<form action="j_spring_security_check" method="post" id="loginForm" >
			<p><em>用户名：</em><span class="txt1 inb"><input type="text"  name="username" maxLength="30" /></span></p>
			<p><em>密　码：</em><span class="txt2 inb"><input type="password" name="password" maxLength="20" /></span></p>
			
			<p><em>　　　　</em><input type="submit" value="登 录" class="hand" /></p>
		</form>
		<dl>
			<dt>注意事项：<%=s %></dt>
			<dd>1. 本系统只有经过授权的、合法的管理员才有权使用。</dd>
			<dd>2.如果您还不是管理员，请联系贵单位相关负责人，索取管理员初始帐号和密码。</dd>
			<dd>3.忘记密码时，请联系贵单位相关负责人，请他帮助您重置密码</dd>
		</dl>
	</div>
</div>

</html>