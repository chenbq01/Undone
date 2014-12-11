<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.sofamovie.admin.domain.UserInfo"%>

<div id="header">

	<div class="container">

		<a href="<c:url value="/"/>" class="brand">首页</a> <a
			href="javascript:;" class="btn-navbar" data-toggle="collapse"
			data-target=".nav-collapse"> <i class="icon-reorder"></i>
		</a>

		<div class="nav-collapse">
			<ul id="main-nav" class="nav pull-right">
				<%-- <li class="nav-icon${_ACTIVED_MENU=='1'?' active':''}"><a
					href="<c:url value="/"/>"> <i class="icon-home"></i> <span>首页</span>
				</a></li> --%>

				<li class="dropdown${_ACTIVED_MENU=='2'?' active':''}"><a
					href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-th"></i> <span>内容管理</span> <b class="caret"></b>
				</a>

					<ul class="dropdown-menu">
						<li><a href="<c:url value="/webimage/mainimage"/>">氛围图</a></li>
						<li><a href="<c:url value="/webimage/focusimage"/>">焦点图</a></li>
						<li><a href="<c:url value="/category/manage"/>">使用指南</a></li>
					</ul></li>

				<li class="dropdown${_ACTIVED_MENU=='3'?' active':''}"><a
					href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-flag"></i> <span>区域信息维护</span> <b class="caret"></b>
				</a>

					<ul class="dropdown-menu">
						<li><a href="<c:url value="/region/manage"/>">区域&amp;频道</a></li>
						<li><a href="<c:url value="/box/manage"/>">机顶盒</a></li>
						<li><a href="<c:url value="/card/manage"/>">用户卡</a></li>
						<%-- <li><a href="<c:url value="/"/>">频道</a></li> --%>
					</ul></li>

				<li class="dropdown${_ACTIVED_MENU=='4'?' active':''}"><a
					href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-user"></i> <span>账户管理</span> <b class="caret"></b>
				</a>

					<ul class="dropdown-menu">
						<li><a href="<c:url value="/user/modifypassword"/>">修改密码</a></li>
						<%
							if (((UserInfo) session.getAttribute("_USER_INFO")).getIsadmin() == 1) {
						%>
						<li><a href="<c:url value="/user/manage"/>">管理其它账户</a></li>
						<%
							}
						%>
					</ul></li>
			</ul>

		</div>
		<!-- /.nav-collapse -->

	</div>
	<!-- /.container -->

</div>
<!-- /#header -->

<div id="masthead">

	<div class="container">

		<div class="masthead-pad">

			<div class="masthead-text">
				<h2>${_MENU_TITLE}</h2>
			</div>
			<!-- /.masthead-text -->

			<!-- /.masthead-text -->
		</div>

	</div>
	<!-- /.container -->

</div>
<!-- /#masthead -->