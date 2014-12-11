<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="zh">
<!--<![endif]-->
<%@include file="/WEB-INF/views/common/html_head.jsp"%>

<body>

	<%@include file="/WEB-INF/views/common/html_body_topbar.jsp"%>

	<div id="wrapper">

		<%@include file="/WEB-INF/views/common/html_body_header.jsp"%>

		<div id="content">

			<div class="container">
				<a href="javascript:addUser();" class="btn btn-large btn-primary">添加用户</a>
				<p>
				<table class="table table-bordered table-striped table-highlight">
					<thead>
						<tr>
							<th>#</th>
							<th>用户名</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>

			</div>
			<!-- /.container -->

		</div>
		<!-- /#content -->

	</div>
	<!-- /#wrapper -->

	<%@include file="/WEB-INF/views/common/html_body_footer.jsp"%>

	<script>
		$(function() {
			loadUserList();
		});

		function enableUserAccount(userid) {
			_confirm(
					"是否确定启用用户账户",
					function(result) {
						if (result == false) {
							return;
						}
						$
								.post(
										"<c:url value="/user/enable"/>?_json=true",
										{
											"userid" : userid
										},
										function(data) {
											if (data.flag == "true") {
												_info(
														"用户已启用！",
														function() {
															$(
																	"#"
																			+ userid
																			+ " > td")
																	.eq(2)
																	.html(
																			"<a href='javascript:disableUserAccount("
																					+ userid
																					+ ");'''>停用</a>&nbsp;<a href='javascript:resetPassword("
																					+ userid
																					+ ");'''>重置密码</a>");
														});
											}
										});
					});
		}

		function disableUserAccount(userid) {
			_confirm("是否确定停用用户账户", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/user/disable"/>?_json=true", {
					"userid" : userid
				}, function(data) {
					if (data.flag == "true") {
						_info("用户账户已停用！", function() {
							$("#" + userid + " > td").eq(2).html(
									"<a href='javascript:enableUserAccount("
											+ userid + ");'''>启用</a>");
						});
					}
				});
			});
		}

		function resetPassword(userid) {
			_confirm("是否确定重置用户密码", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/user/resetpassword"/>?_json=true", {
					"userid" : userid
				}, function(data) {
					if (data.flag == "true") {
						_info("用户密码已重置！");
					}
				});
			});
		}

		function addUser() {
			_prompt("请输入要添加的用户名：", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/user/add"/>?_json=true", {
					"username" : result
				}, function(data) {
					if (data.flag == "true") {
						loadUserList();
					}
				});
			});
		}

		function loadUserList() {
			$("table > tbody").html("");
			$
					.get(
							"<c:url value="/user/list"/>?_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													var html = "<tr id='"+item.id+"'><td>"
															+ (i + 1)
															+ "</td><td>"
															+ item.username
															+ "</td><td>";
													if (item.deleteflag == 1) {
														html += "<a href='javascript:enableUserAccount("
																+ item.id
																+ ");'''>启用</a>";
													}
													if (item.deleteflag == 0) {
														html += "<a href='javascript:disableUserAccount("
																+ item.id
																+ ");'''>停用</a>&nbsp;<a href='javascript:resetPassword("
																+ item.id
																+ ");'''>重置密码</a>";
													}
													html += "</td></tr>";
													$("table > tbody").append(
															html);
												});
								//alert($("table > tbody").html());
							});
		}
	</script>

</body>
</html>