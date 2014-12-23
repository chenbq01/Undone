<%@page import="cn.com.uwoa.global.security.SecurityHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/normal/css/humanity/jquery-ui-1.10.3.custom.css" />
<script src="${pageContext.request.contextPath}/resources/normal/js/jquery-1.9.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/normal/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="http://jquery-json.googlecode.com/files/jquery.json-2.2.min.js"></script>
<script>
	$(function() {
		$("#side").accordion({
			heightStyle : "content"
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
	height: 100%;
	_height: 100%;
	_margin-left: 250px;
	background: #FFFFFF;
}
</style>
</head>
<body>
<%@ include file="./../../base/alertDialog.jsp"%>
	<div id="hd">
		<table border="0" cellspacing="0" cellpadding="0" width=100%
			height="150px">
			<tr>
				<td><img
					src="${pageContext.request.contextPath}/resources/normal/images/banner_logo.jpg"></img></td>
				<td width="100%" align="right" valign="top" background="${pageContext.request.contextPath}/resources/normal/images/banner_bg.jpg">
					<span style="color:#ffffff;cursor:pointer" onclick="passwordDialog('asdfdsaf');">
						[修改密码]
					</span>
					&nbsp;&nbsp;
					<span style="color:#ffffff;cursor:pointer" onclick="window.location='/uwoa/j_spring_security_logout'">
						[退出]
					</span>
					&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<div id="bd">
		<div id="side">
			<h3>餐厅设置</h3>
			<div>
				<ul>
					<%if(SecurityHelper.HasAuthority("/restaurant/table")){ %>
					<li><a href="${pageContext.request.contextPath}/restaurant/table" target="mainFrame">餐桌管理</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/restaurant/restaurantInfo")){ %>
					<li><a href="${pageContext.request.contextPath}/restaurant/restaurantInfo" target="mainFrame">餐厅介绍</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/restaurant/systemInfo")){ %>
					<li><a href="${pageContext.request.contextPath}/restaurant/systemInfo" target="mainFrame">会员优惠设置</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/system/restaurant")){ %>
					<li><a href="${pageContext.request.contextPath}/restaurant/memberDiscount" target="mainFrame">会员优惠菜品分类</a></li>
					
					<!-- <li><a href="${pageContext.request.contextPath}/system/restaurant" target="mainFrame">连锁店管理</a></li>-->
					<%} %>
				</ul>
			</div>
			<h3>菜品管理</h3>
			<div>
				<ul>
					<%if(SecurityHelper.HasAuthority("/food/foodType")){ %>
					<li><a href="${pageContext.request.contextPath}/food/foodType" target="mainFrame">菜品分类管理</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/food/food")){ %>
					<li><a href="${pageContext.request.contextPath}/food/food" target="mainFrame">菜品维护</a></li>
					<%} %>
					<li><a href="${pageContext.request.contextPath}/food/foodSpecial" target="mainFrame">特色菜</a></li>
					<li><a href="${pageContext.request.contextPath}/food/foodNew" target="mainFrame">新品推荐</a></li>
					<%if(SecurityHelper.HasAuthority("/food/buffet")){ %>
					<!--<li><a href="${pageContext.request.contextPath}/food/buffet" target="mainFrame">自助餐管理</a></li>  -->
					<%} %>
					<%if(SecurityHelper.HasAuthority("/food/specialOffer")){ %>
					<li><a href="${pageContext.request.contextPath}/food/specialOffer" target="mainFrame">限时特价</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/food/commentManage")){ %>
					<li><a href="${pageContext.request.contextPath}/food/commentManage" target="mainFrame">菜品点评管理</a></li>
					<%} %>
				</ul>
			</div>
			<%if(SecurityHelper.HasAuthority("/order/order")){ %>
			<h3>订单管理</h3>
			<div>
				<ul>
				
					<li><a href="${pageContext.request.contextPath}/order/order" target="mainFrame">订单列表</a></li>
				</ul>
			</div>
			<%} %>
			<%if(SecurityHelper.HasAuthority("/system/restaurant")){ %>
			<!-- <h3>平台管理</h3>
			<div>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/system/restaurant"
						target="mainFrame">餐厅管理</a></li>
				</ul>
			</div> -->
			<%} %>
			<!--
			<h3>系统管理</h3>
			<div>
				<ul>
					<%if(SecurityHelper.HasAuthority("/system/dictionary")){ %>
					<li><a href="${pageContext.request.contextPath}/system/dictionary" target="mainFrame">数据字典</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/system/au/resource")){ %>
					<li><a href="${pageContext.request.contextPath}/system/au/resource" target="mainFrame">资源管理</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/system/au/role")){ %>
					<li><a href="${pageContext.request.contextPath}/system/au/role" target="mainFrame">角色管理</a></li>
					<%} %>
					<%if(SecurityHelper.HasAuthority("/system/au/user")){ %>
					
					<li><a href="${pageContext.request.contextPath}/system/au/user" target="mainFrame">用户管理</a></li>
					
					<%} %>
					
				</ul>
			</div>  -->
			<h3>统计查询</h3>
			<div>
				<ul>
					<li><a href="${pageContext.request.contextPath}/report/report1" target="mainFrame">历史订单查询</a></li>
					<li><a href="${pageContext.request.contextPath}/report/report2" target="mainFrame">菜品销量统计(数量)</a></li>
					<li><a href="${pageContext.request.contextPath}/report/report3" target="mainFrame">菜品销量统计(金额)</a></li>
					<li><a href="${pageContext.request.contextPath}/report/report4" target="mainFrame">营销查询</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
				<iframe name="mainFrame" width="100%" height="100%" frameborder="0"></iframe>
		</div>
	</div>
	
	
	<div id="password_div" title="修改密码">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 原密码</td>
					<td class="input"><input type="password" name="password_old" id="password_old" validate="notNull" inputName="原密码" /></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 新密码</td>
					<td class="input"><input type="password" name="password_new" id="password_new" validate="notNull" inputName="新密码" /></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 再输一次</td>
					<td class="input"><input type="password" name="password_new2" id="password_new2" validate="notNull" inputName="再输一次" /></td>
				</tr>
			</table>
		</form>
		<p class="validateTips"></p>
	</div>
	<script>
		function passwordDialog(msg) {
			$("#password_new").val("");
			$("#password_new2").val("");
			$("#password_old").val("");
			$("#password_div").dialog({
				modal : true,
				buttons : {
					"确定" : function() {
						var bValid = true;
						var o = {};
						if (bValid) {
							o["id"]="<%=SecurityHelper.getLoginUserId() %>";
							o["password_old"]=$("#password_old").val();
							o["password_new"]=$("#password_new").val();
							o["password_new2"]=$("#password_new2").val();
							if(o["password_old"]==""){
								alertDialog("请输入原密码");
							}
							else if(o["password_new"]==""){
								alertDialog("请输入新密码");
							}
							else if(o["password_new"]!=o["password_new2"]){
								alertDialog("两次密码不一致");
							}
							else{
								var jsonuserinfo = $.toJSON(o);
								
								jQuery.ajax({
									async : false,
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/system/au/user/password',
									data : jsonuserinfo,
									dataType : 'json',
									success : function(data) {
										if (data && data.success == "true") {
											$("#password_div").dialog("close");
										}
										else{
											alertDialog("修改密码失败");
										}
									},
									error : function(data) {
										alert("error")
									}
								});
							}
						}
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	</script>
</body>
</html>