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
				<div class="row">

					<div class="tabbable">

						<div class="span3">

							<ul id="ul_menu" class="nav nav-tabs nav-stacked">
							</ul>

						</div>
						<!-- /.span3 -->


						<div class="span9">

							<div id="div_content" class="tab-content"></div>
							<!-- /.tab-content -->

						</div>
						<!-- /.span9 -->

					</div>
					<!-- /.tabbable -->

				</div>
				<!-- /.row -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /#content -->

	</div>
	<!-- /#wrapper -->

	<%@include file="/WEB-INF/views/common/html_body_footer.jsp"%>

	<script>
		$(function() {
			loadRegionMenu();
		});

		function loadRegionMenu() {
			var html = "";
			$
					.get(
							"<c:url value="/region/list"/>?_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													html += "<li id='"+item.id+"'><a href='#tab"+item.id+"' data-toggle='tab'> <i class='icon-ok'></i> "
															+ item.regionname
															+ " <i class='icon-chevron-right'></i></a></li>";
												});
								$("#ul_menu").html(html);
								$("#ul_menu > li").click(function() {
									loadContent(this.id);
								});
								loadInitContent();
							});
		}

		function loadContent(id) {
			if ($("''#tab" + id + "'").size() == 0) {
				//加载内容
				var html = "<div class='tab-pane' id='tab"+id+"'>";
				html += "<a href='javascript:addBox(" + id
						+ ");' class='btn btn-large btn-primary'>添加机顶盒</a>";
				html += "<p>";
				html += "<table id='tbl_"+id+"' class='table table-bordered table-striped table-highlight'>";
				html += "<thead><tr><th>#</th><th>机顶盒</th><th>操作</th></tr></thead>";
				html += "<tbody></tbody>";
				html += "</table>";
				html += "</div>";
				$("#div_content").append(html);
				loadBoxList(id);
			}
		}

		function addBox(regionid) {
			_prompt("请输入要添加的机顶盒：", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/box/add"/>?_json=true", {
					"regionid" : regionid,
					"boxname" : result
				}, function(data) {
					if (data.flag == "true") {
						loadBoxList(regionid);
					}
				});
			});
		}

		function deleteBox(boxid,regionid) {
			_confirm("是否确定删除", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/box/remove"/>?_json=true", {
					"boxid" : boxid
				}, function(data) {
					if (data.flag == "true") {
						_info("机顶盒已删除！", function() {
							//$("#tr" + boxid).remove();
							loadBoxList(regionid);
						});
					}
				});
			});
		}

		function manageImage(boxid, boxname, regionid) {
			var uri_boxname = encodeURI(encodeURI(boxname));
			//alert(uri_boxname);
			window.location.href = "<c:url value="/box/imagelist"/>?boxid="
					+ boxid + "&boxname=" + uri_boxname + "&regionid="
					+ regionid;
		}

		function loadBoxList(regionid) {
			$("#tbl_" + regionid + " > tbody").html("");
			$
					.get(
							"<c:url value="/box/list"/>?regionid=" + regionid
									+ "&_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													var html = "<tr id='tr"+item.id+"'><td>"
															+ (i + 1)
															+ "</td><td>"
															+ item.boxname
															+ "</td><td>";
													if (item.deleteflag == 0) {
														html += "<a href=\"javascript:deleteBox('"
																+ item.id
																+ "','"
																+ regionid
																+ "');\">删除</a>&nbsp;<a href=\"javascript:manageImage('"
																+ item.id
																+ "','"
																+ item.boxname
																+ "','"
																+ regionid
																+ "');\">设置图片</a>";
													}
													html += "</td></tr>";
													$(
															"#tbl_"
																	+ regionid
																	+ " > tbody")
															.append(html);
												});
								//alert($("table > tbody").html());
							});
		}

		function loadInitContent() {
			var regionid = "${regionid}";
			//$("#ul_menu > li[id='6'] > a").trigger("click");
			if (regionid == "") {
				$("#ul_menu > li:first > a").trigger("click");
			} else {
				$("#ul_menu > li[id='" + regionid + "'] > a").trigger("click");
			}
		}
	</script>

</body>
</html>