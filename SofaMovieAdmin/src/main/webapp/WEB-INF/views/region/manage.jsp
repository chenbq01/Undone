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
				<a href="javascript:addRegion();" class="btn btn-large btn-primary">添加区域</a>
				<p>
				<table class="table table-bordered table-striped table-highlight">
					<thead>
						<tr>
							<th>#</th>
							<th>区域</th>
							<th>导视频道</th>
							<th>点播频道</th>
							<th>支持电话</th>
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
			loadRegionList();
		});

		function addRegion() {
			_prompt("请输入要添加的区域：", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/region/add"/>?_json=true", {
					"regionname" : result
				}, function(data) {
					if (data.flag == "true") {
						loadRegionList();
					}
				});
			});
		}

		function deleteRegion(regionid) {
			_confirm("是否确定删除", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/region/remove"/>?_json=true", {
					"regionid" : regionid
				}, function(data) {
					if (data.flag == "true") {
						_info("区域及相关信息已删除！", function() {
							//$("#" + regionid).remove();
							loadRegionList();
						});
					}
				});
			});
		}

		function setChannel(regionid) {
			$.post("<c:url value="/region/getinfo"/>?_json=true", {
				"regionid" : regionid
			}, function(data) {
				$.msgbox("请输入频道号（多个频道之间用英文逗号[,]分隔）", {
					type : "prompt",
					inputs : [ {
						type : "text",
						label : "请输入导视频道：",
						value : data.guidechannels
					}, {
						type : "text",
						label : "请输入点播频道：",
						value : data.demandchannels
					} ],
					buttons : [ {
						type : "submit",
						value : "保存"
					}, {
						type : "cancel",
						value : "取消"
					} ]
				}, function(guidechannels, demandchannels) {
					if (guidechannels == undefined
							|| demandchannels == undefined) {
						return;
					}
					$.post("<c:url value="/region/setchannel"/>?_json=true", {
						"regionid" : regionid,
						"guidechannels" : guidechannels,
						"demandchannels" : demandchannels
					}, function(data) {
						if (data.flag == "true") {
							loadRegionList();
							_info("设置成功！");
						}
					});
				});
			});
		}

		function setSupportPhone(regionid) {
			$
					.post(
							"<c:url value="/region/getinfo"/>?_json=true",
							{
								"regionid" : regionid
							},
							function(data) {
								$
										.msgbox(
												"请输入支持电话",
												{
													type : "prompt",
													inputs : [ {
														type : "text",
														label : "请输入支持电话号码：",
														value : data.supportphone
													} ],
													buttons : [ {
														type : "submit",
														value : "保存"
													}, {
														type : "cancel",
														value : "取消"
													} ]
												},
												function(supportphone) {
													if (supportphone == false) {
														return;
													}
													$
															.post(
																	"<c:url value="/region/setsupportphone"/>?_json=true",
																	{
																		"regionid" : regionid,
																		"supportphone" : supportphone
																	},
																	function(
																			data) {
																		if (data.flag == "true") {
																			loadRegionList();
																			_info("设置成功！");
																		}
																	});
												});
							});
		}

		function loadRegionList() {
			$("table > tbody").html("");
			$
					.get(
							"<c:url value="/region/list"/>?_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													var html = "<tr id='"+item.id+"'><td>"
															+ (i + 1)
															+ "</td><td>"
															+ item.regionname
															+ "</td><td>"
															+ (item.guidechannels == null ? ""
																	: item.guidechannels)
															+ "</td><td>"
															+ (item.demandchannels == null ? ""
																	: item.demandchannels)
															+ "</td><td>"
															+ (item.supportphone == null ? ""
																	: item.supportphone)
															+ "</td><td>";
													if (item.deleteflag == 0) {
														html += "<a href=\"javascript:deleteRegion('"
																+ item.id
																+ "');\">删除</a>&nbsp;<a href=\"javascript:setChannel('"
																+ item.id
																+ "');\">设置频道</a>&nbsp;<a href=\"javascript:setSupportPhone('"
																+ item.id
																+ "');\">设置支持电话</a>";
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