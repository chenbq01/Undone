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
				<!--div id="sample">
<h4>First Textarea</h4>
<textarea name="area1" cols="40"></textarea>
<br />

<h4>Second Textarea</h4>
<textarea name="area2" style="width: 100%;">
	Some Initial Content was in this textarea
</textarea>
<br />

<h4>Third Textarea</h4>
<textarea name="area3" style="width: 300px; height: 100px;">
	HTML <b>content</b> <i>default</i> in textarea
</textarea>
</div-->
				<div class="row">
					<div class="span8">
						<ol class="faqList">
						</ol>
					</div>
					<div class="span4">
						<a href="javascript:addFAQ();"
							class="btn btn-large btn-primary btn-block btn-big-block">添加服务支持</a>
						<a href="<c:url value="/category/manage"/>"
							class="btn btn-large btn-block btn-big-block">返回使用指南</a>
					</div>
					<!-- /.span4 -->
				</div>
				<!-- /.row -->
				<div id="hiddenform"
					style="display: none; overflow: scroll; width: 400px; height: 400px;">
					<form class="form-horizontal" method="post">

						<br> <br>问题<br> <input type="text"
							class="input-large" name="question" id="question"><br>
						<br> 解答<br>

						<!--textarea name="answer" id="answer" rows="4"></textarea-->
						<div id="myNicPanel" style="width: 100%;"></div>
						<div id="divanswer"
							style="background-color: #ccc; border: 1px solid #000; width: 100%;"></div>
						<div>
							<br> <br>排序标识<br> <input type="text"
								class="input-large" name="orderno" id="orderno"><input
								type="hidden" name="faqid" id="faqid"><input
								type="hidden" name="answer" id="answer"><input
								type="hidden" name="categoryid" id="categoryid"
								value="${categoryid}"> <input type="hidden"
								name="categoryname" id="categoryname" value="${categoryname}"><br>
							<br> <input type="button" class="input-large"
								name="btn_save" value="保存"
								onclick="javascript:$('#answer').val($('#divanswer').html());form.submit();" />
							<input type="button" class="input-large" name="btn_cancel"
								value="取消"
								onclick="javascript:form.reset();$('#hiddenform').hide();" />
						</div>
					</form>

				</div>
			</div>
			<!-- /.container -->
		</div>
		<!-- /#content -->

	</div>
	<!-- /#wrapper -->

	<%@include file="/WEB-INF/views/common/html_body_footer.jsp"%>
	<script src="<c:url value="/resources/js/nicEdit.js"/>"></script>
	<script type="text/javascript">
		bkLib.onDomLoaded(function() {
			var myNicEditor = new nicEditor({
				iconsPath : "<c:url value="/resources/js/nicEditorIcons.gif"/>"
			});
			myNicEditor.setPanel('myNicPanel');
			myNicEditor.addInstance('divanswer');
		});
	</script>
	<script>
		$(function() {
			var msg = '${msg}';
			if (msg != '') {
				_alert(msg);
			}

			loadContent('${categoryid}');
		});

		function loadContent(categoryid) {

			$
					.get(
							"<c:url value="/faq/list"/>?categoryid="
									+ categoryid + "&_json=true",
							function(data) {
								var html = "";
								$
										.each(
												data,
												function(i, item) {
													html += "<li>";
													html += "<h4>"
															+ item.question
															+ "</h4>";
													html += "<p>" + item.answer
															+ "</p>	";
													html += "<p><a href=\"javascript:deleteFAQ('"
															+ item.id
															+ "');\">删除</a>&nbsp;<a href=\"javascript:modifyFAQ('"
															+ item.id
															+ "');\">修改</a></p>";
													html += "</li>";
												});
								$('.faqList').html(html);
							});

		}

		function addFAQ() {
			$("#faqid").val("");
			$("#question").val("");
			$("#divanswer").html("");
			$("#orderno").val("");
			$("form").attr("action", "<c:url value="/faq/add" />");
			$("#hiddenform").show();
			$("#hiddenform").css({
				'opacity' : 1,
				'position' : 'absolute',
				'top' : 0,
				'left' : 0,
				'background-color' : '#AAAAAA',
				'width' : '100%',
				'z-index' : 5000
			});
			/*$.blockUI({
				message : $("#hiddenform"),
				css : {
					border : 'none'
				}
			});*/
		}

		function deleteFAQ(faqid) {
			_confirm("是否确定删除", function(result) {
				if (result == false) {
					return;
				}
				$.post("<c:url value="/faq/remove"/>?_json=true", {
					"id" : faqid
				}, function(data) {
					if (data.flag == "true") {
						_info("服务支持内容已删除！", function() {
							loadContent('${categoryid}');
						});
					}
				});
			});
		}
		function modifyFAQ(faqid) {
			$.ajax({
				url : "<c:url value="/faq/getinfo"/>?_json=true",
				async : false,
				type : "post",
				dataType : "json",
				data : "faqid=" + faqid,

				success : function(data) {
					if (data != null) {
						$("form").attr("action",
								"<c:url value="/faq/modify" />");
						$("#faqid").val(faqid);
						$("#question").val(data.question);
						$("#divanswer").html(data.answer);
						$("#orderno").val(data.orderno);
					}
				}
			});
			$("#hiddenform").show();
			$("#hiddenform").css({
				'opacity' : 1,
				'position' : 'absolute',
				'top' : 0,
				'left' : 0,
				'background-color' : '#AAAAAA',
				'width' : '100%',
				'z-index' : 5000
			});
			/*$.blockUI({
				message : $("#hiddenform"),
				css : {
					border : 'none'
				}
			});*/
			/* $.post("<c:url value="/faq/getinfo"/>?_json=true", {
				"faqid" : faqid
			}, function(data) {
				
			}); */

		}
	</script>

</body>
</html>