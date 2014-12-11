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

					<div class="span12">

						<form class="form-horizontal" novalidate="novalidate">
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="password">当前密码</label>
									<div class="controls">
										<input type="password" class="input-large" name="password"
											id="password">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="newpassword">新的密码</label>
									<div class="controls">
										<input type="password" class="input-large" name="newpassword"
											id="newpassword">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="confirmpassword">确认密码</label>
									<div class="controls">
										<input type="password" class="input-large"
											name="confirmpassword" id="confirmpassword">
									</div>
								</div>
								<div class="form-actions">
									<!-- <button type="submit" class="btn btn-primary btn-large">
										<i class="icon-star"></i> 确认
									</button> -->
									<input id="btn_confirm" type="button" value="确认"
										class="btn btn-primary btn-large" />
									<!-- <button type="reset" class="btn btn-large">取消</button> -->
									<input id="btn_confirm" type="reset" value="取消"
										class="btn btn-large" />
								</div>
							</fieldset>
						</form>

					</div>
					<!-- /.span12 -->

				</div>
				<!-- /.row -->

			</div>
			<!-- /.container -->

		</div>
		<!-- /#content -->

	</div>
	<!-- /#wrapper -->

	<%@include file="/WEB-INF/views/common/html_body_footer.jsp"%>
	<script
		src="<c:url value="/resources/js/plugins/validate/jquery.validate.js"/>"></script>
	<script>
		$(function() {

			var rules = {
				rules : {
					password : {
						minlength : 6,
						required : true
					},
					newpassword : {
						minlength : 6,
						required : true
					},
					confirmpassword : {
						minlength : 6,
						required : true
					}
				}
			};

			var validationObj = $.extend(rules, Theme.validationRules);

			$('form').validate(validationObj);

			$("#btn_confirm")
					.click(
							function() {
								$
										.post(
												"<c:url value="/user/modifypassword"/>?_json=true",
												$("form").serialize(),
												function(data) {
													if (data.flag == "true") {
														_info("密码修改成功，请重新登录！",function(){
															window.location.href = "<c:url value="/user/logout"/>";
														});
													} else {
														_alert(data.msg);
													}
												});
							});

		});
	</script>

</body>
</html>