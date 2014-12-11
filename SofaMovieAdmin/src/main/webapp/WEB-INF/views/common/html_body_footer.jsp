<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="footer">

	<div class="container">

		<div class="row">

			<div class="span6">中辉华尚（北京）文化传播有限公司　版权所有</div>
			<!-- /span6 -->

			<div id="builtby" class="span6">
				<a href="#">Built by Sakya</a>
			</div>
			<!-- /.span6 -->

		</div>
		<!-- /row -->

	</div>
	<!-- /container -->

</div>
<!-- /#footer -->
<script src="<c:url value="/resources/js/libs/jquery-1.7.2.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/libs/jquery-ui-1.8.21.custom.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/libs/jquery.ui.touch-punch.min.js"/>"></script>

<script
	src="<c:url value="/resources/js/libs/bootstrap/bootstrap.min.js"/>"></script>

<script src="<c:url value="/resources/js/Theme.js"/>"></script>
<script src="<c:url value="/resources/js/Charts.js"/>"></script>

<script
	src="<c:url value="/resources/js/plugins/excanvas/excanvas.min.js"/>"></script>
<script src="<c:url value="/resources/js/plugins/flot/jquery.flot.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/flot/jquery.flot.pie.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/flot/jquery.flot.orderBars.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/flot/jquery.flot.tooltip.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/flot/jquery.flot.resize.js"/>"></script>

<script src="<c:url value="/resources/js/demos/charts/line.js"/>"></script>
<script src="<c:url value="/resources/js/demos/charts/donut.js"/>"></script>

<script
	src="<c:url value="/resources/js/plugins/msgbox/jquery.msgbox.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/msgbox/lightbox/jquery.lightbox.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/msgbox/lightbox/jquery.lightbox.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/lightbox/jquery.lightbox.js"/>"></script>
<script
	src="<c:url value="/resources/js/plugins/hoverIntent/jquery.hoverIntent.minified.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.blockUI.js"/>"></script>

<script>
	function _alert(msg, callback) {
		$.msgbox(msg, {
			buttons : [ {
				type : "submit",
				value : "确定"
			} ]
		}, callback);
	}

	function _info(msg, callback) {
		$.msgbox(msg, {
			type : "info",
			buttons : [ {
				type : "submit",
				value : "确定"
			} ]
		}, callback);
	}

	function _error(msg, callback) {
		$.msgbox(msg, {
			type : "error",
			buttons : [ {
				type : "submit",
				value : "确定"
			} ]
		}, callback);
	}

	function _confirm(msg, callback) {
		$.msgbox(msg, {
			type : "confirm",
			buttons : [ {
				type : "submit",
				value : "是"
			}, {
				type : "cancel",
				value : "否"
			} ]
		}, callback);
	}

	function _prompt(msg, callback) {
		$.msgbox(msg, {
			type : "prompt",
			buttons : [ {
				type : "submit",
				value : "确定"
			}, {
				type : "cancel",
				value : "取消"
			} ]
		}, callback);
	}

	$(document).ajaxStart(function() {
		$.blockUI({
			message : '请稍后...'
		});
	});

	$(document).ajaxComplete(function() {
		$.unblockUI();
	});

	$(function() {

		Theme.init();

		$("#main-nav > li > a:first-child").click(function() {
			$("#main-nav > li").removeClass("active");
			$(this).parent().addClass("active");
			;
		});

	});
</script>