<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div id="topbar">

		<div class="container">

			<a href="javascript:;" id="menu-trigger" class="dropdown-toggle"
				data-toggle="dropdown" data-target="#"> <i class="icon-cog"></i>
			</a>

			<div id="top-nav">
			
				<ul class="pull-right">
					<li><i class="icon-user"></i> ${sessionScope._USER_INFO['username']}</li>
					<li><a href="<c:url value="/user/logout"/>">退出</a></li>
				</ul>

			</div>
			<!-- /#top-nav -->

		</div>
		<!-- /.container -->

	</div>
	<!-- /#topbar -->