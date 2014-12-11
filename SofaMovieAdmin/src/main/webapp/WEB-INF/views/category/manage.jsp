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
				<a href="javascript:addCategory();"
					class="btn btn-large btn-primary">添加使用指南</a>
				<p>
				<table class="table table-bordered table-striped table-highlight">
					<thead>
						<tr>
							<th>#</th>
							<th>使用指南</th>
							<th>排序标志</th>
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
			loadCategoryList();
		});

		function addCategory() {
			$.msgbox("请输入要添加的使用指南", {
				type : "prompt",
				inputs : [ {
					type : "text",
					label : "请输入使用指南名称：",
					required : true
				}, {
					type : "text",
					label : "请输入使用指南排序号：",
					required : true
				} ],
				buttons : [ {
					type : "submit",
					value : "保存"
				}, {
					type : "cancel",
					value : "取消"
				} ]
			}, function(categoryname, orderno) {
				if (categoryname == undefined || orderno == undefined) {
					return;
				}
				$.post("<c:url value="/category/add"/>?_json=true", {
					"categoryname" : categoryname,
					"orderno" : orderno
				}, function(data) {
					if (data.flag == "true") {
						loadCategoryList();
					}
				});
			});
		}

		function deleteCategory(categoryid) {
			_confirm("是否确定删除", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/category/remove"/>?_json=true", {
					"categoryid" : categoryid
				}, function(data) {
					if (data.flag == "true") {
						_info("使用指南及相关信息已删除！", function() {
							$("#" + categoryid).remove();
						});
					}
				});
			});
		}

		function setCategory(categoryid) {
			$.post("<c:url value="/category/getinfo"/>?_json=true", {
				"categoryid" : categoryid
			}, function(data) {
				$.msgbox("请输入使用指南", {
					type : "prompt",
					inputs : [ {
						type : "text",
						label : "请输入使用指南名称：",
						value : data.categoryname,
						required : true
					}, {
						type : "text",
						label : "请输入使用指南排序号：",
						value : data.orderno,
						required : true
					} ],
					buttons : [ {
						type : "submit",
						value : "保存"
					}, {
						type : "cancel",
						value : "取消"
					} ]
				}, function(categoryname, orderno) {
					if (categoryname == undefined || orderno == undefined) {
						return;
					}
					$.post("<c:url value="/category/setcategory"/>?_json=true",
							{
								"categoryid" : categoryid,
								"categoryname" : categoryname,
								"orderno" : orderno
							}, function(data) {
								if (data.flag == "true") {
									loadCategoryList();
									_info("修改成功！");
								}
							});
				});
			});
		}

		function manageFAQ(categoryid, categoryname) {
			var uri_categoryname = encodeURI(encodeURI(categoryname));
			//alert(uri_boxname);
			window.location.href = "<c:url value="/faq/listfaq"/>?categoryid="
					+ categoryid + "&categoryname=" + uri_categoryname;
		}

		function loadCategoryList() {
			$("table > tbody").html("");
			$
					.get(
							"<c:url value="/category/list"/>?_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													var html = "<tr id='"+item.id+"'><td>"
															+ (i + 1)
															+ "</td><td>"
															+ item.categoryname
															+ "</td><td>"
															+ item.orderno
															+ "</td><td>";
													if (item.deleteflag == 0) {
														html += "<a href=\"javascript:deleteCategory('"
																+ item.id
																+ "');\">删除</a>&nbsp;<a href=\"javascript:setCategory('"
																+ item.id
																+ "');\">修改使用指南</a>&nbsp;<a href=\"javascript:manageFAQ('"
																+ item.id
																+ "','"
																+ item.categoryname
																+ "');\">维护服务支持内容</a>";
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