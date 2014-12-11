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
				<div class="pricing-plans plans-3">
					<div class="plan-container">
						<div class="plan">
							<div class="plan-header">
								<div class="plan-price">1</div>
								<!-- /plan-price -->
								<div class="plan-title">
									<img src="<c:url value="/resources/img/common/temp.png"/>"
										alt="" />
								</div>
								<!-- /plan-title -->
							</div>
							<!-- /plan-header -->
							<div class="plan-features">
								<ul>
									<li><a href="javascript:;">查看链接</a></li>
								</ul>
							</div>
							<!-- /plan-features -->
							<div class="plan-actions">
								<a href="javascript:showUploadForm(1);" class="btn">重新上传</a>
							</div>
							<!-- /plan-actions -->

						</div>
						<!-- /plan -->
					</div>
					<!-- /plan-container -->
					<div class="plan-container">
						<div class="plan">
							<div class="plan-header">
								<div class="plan-price">2</div>
								<!-- /plan-price -->
								<div class="plan-title">
									<img src="<c:url value="/resources/img/common/temp.png"/>"
										alt="" />
								</div>
								<!-- /plan-title -->
							</div>
							<!-- /plan-header -->
							<div class="plan-features">
								<ul>
									<li><a href="javascript:;">查看链接</a></li>
								</ul>
							</div>
							<!-- /plan-features -->
							<div class="plan-actions">
								<a href="javascript:showUploadForm(2);" class="btn">重新上传</a>
							</div>
							<!-- /plan-actions -->

						</div>
						<!-- /plan -->
					</div>
					<!-- /plan-container -->
					<div class="plan-container">
						<div class="plan">
							<div class="plan-header">
								<div class="plan-price">3</div>
								<!-- /plan-price -->
								<div class="plan-title">
									<img src="<c:url value="/resources/img/common/temp.png"/>"
										alt="" />
								</div>
								<!-- /plan-title -->
							</div>
							<!-- /plan-header -->
							<div class="plan-features">
								<ul>
									<li><a href="javascript:;">查看链接</a></li>
								</ul>
							</div>
							<!-- /plan-features -->
							<div class="plan-actions">
								<a href="javascript:showUploadForm(3);" class="btn">重新上传</a>
							</div>
							<!-- /plan-actions -->

						</div>
						<!-- /plan -->
					</div>
					<!-- /plan-container -->
				</div>
				<p style="text-align: center;">图片要求 宽:300像素 高:320像素</p>
				<div id="uploadform" style="display: none; cursor: default">

					<div class="content clearfix">

						<form class="form-horizontal" enctype="multipart/form-data"
							action="<c:url value="/webimage/uploadfile" />" method="post">
							<input type="hidden" name="imagetype" value="JDT" /><input
								type="hidden" name="sequence" id="sequence" value="" /> <input
								type="file" class="input-large" name="file" /> <input
								type="text" class="input-large" name="linkurl" value="http://"><br>
							<input type="button" class="input-large" name="btn_upload"
								value="上传" onclick="javascript:form.submit();" /> <input
								type="button" class="input-large" name="btn_cancel" value="取消"
								onclick="javascript:form.reset();$.unblockUI();" />
						</form>

					</div>
					<!-- /content -->

				</div>
				<!-- /uploadform -->
			</div>
			<!-- /.container -->

		</div>
		<!-- /#content -->

	</div>
	<!-- /#wrapper -->

	<%@include file="/WEB-INF/views/common/html_body_footer.jsp"%>
	<script>
		$(function() {
			var msg = '${msg}';
			if (msg != '') {
				_alert(msg);
			}
			loadFocusImageList();
		});
		function showUploadForm(sequence) {
			$("#sequence").val(sequence);
			$.blockUI({
				message : $("#uploadform"),
				css : {
					border : 'none'
				}
			});
		}
		function removeImage(sequence) {
			window.location.href = "<c:url value="/webimage/removeImage"/>?sequence="
					+ sequence;
		}
		function loadFocusImageList() {
			//$(".plan-container .plan-title").html("");
			$
					.get(
							"<c:url value="/webimage/focusimagelist"/>?_json=true",
							function(data) {
								if (data.length > 0) {
									var j = 0;
									$(".plan-container")
											.each(
													function(i) {
														if (j < data.length
																&& i == (data[j].sequence - 1)) {
															$(this)
																	.children()
																	.children()
																	.children(
																			".plan-title")
																	.html(
																			"<img src='"+data[j].imageurl+"' alt='' />");

															$(this)
																	.children()
																	.children(
																			".plan-features")
																	.children()
																	.children()
																	.html(
																			"<a target='_blank' href='"+data[j].linkurl+"'>查看链接</a>&nbsp;<a href='javascript:removeImage("
																					+ data[j].sequence
																					+ ");'>删除图片</a>");
															j++;
														} else {
															$(this)
																	.children()
																	.children()
																	.children(
																			".plan-title")
																	.html(
																			"<img src='<c:url value="/resources/img/common/temp.png"/>' alt='' />");

															$(this)
																	.children()
																	.children(
																			".plan-features")
																	.children()
																	.children()
																	.html(
																			"<a target='_blank' href='javascript:;'>查看链接</a>");
														}
													});
								}
							});
		}
	</script>
</body>
</html>