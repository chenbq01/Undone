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

	<script src="<c:url value="/resources/js/plugins/faq/faq.js"/>"></script>

	<script>
		$(function() {
			loadCategoryMenu();
		});

		function loadCategoryMenu() {
			var html = "";
			$
					.get(
							"<c:url value="/category/list"/>?_json=true",
							function(data) {
								$
										.each(
												data,
												function(i, item) {
													html += "<li id='"+item.id+"'><a href='#tab"+item.id+"' data-toggle='tab'> <i class='icon-ok'></i> "
															+ item.categoryname
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
				html += "<ol class=\"faqList\">";
				html += "<li>";
				html += "<h4>" + id + "How can I get my own domain name?</h4>";
				html += "<p>The Internet Corporation for Assigned Names and Numbers (ICANN) maintains a list of accredited registrars . Any of the companies on this list can register a domain name for you..</p>	";
				html += "</li>";
				html += "</ol>";
				html += "</div>";
				$("#div_content").append(html);
				//loadFAQList(id);
				$('.faqList').goFaq();
			}
		}

		function addFAQ(categoryid) {

		}

		function deleteFAQ(faqid) {

		}

		function loadInitContent() {
			//$("#ul_menu > li[id='6'] > a").trigger("click");
			$("#ul_menu > li:first > a").trigger("click");
		}
	</script>

</body>
</html>