<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<body>
	<div id="search_table" class="ui-widget">
		<form method="post" id="search_form" name="search_form">
			<table id="search" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th colspan="4">快速查询</th>
					</tr>
				</thead>
				<tbody>
					<td class="label">姓名：</td>
					<td class="input"><input type="text" name="name"/></td>
					<td class="label"></td>
					<td class="input"><button id="submit">查询</button></td>
				</tbody>
			</table>
		</form>
	</div>
	<div id="toolbar_table">
		<button id="but_add">新增</button>
		<button id="but_edit">修改</button>
		<button id="but_delete">删除</button>
		<button id="but_view">查看</button>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content">
			<thead>
				<tr class="ui-widget-header ">
					<th width="20"></th>
					<th width="25%">姓名</th>
					<th width="20%">性别</th>
					<th width="25%">生日</th>
					<th width="25%">年龄</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

	<div id="edit_div" title="">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label">姓名：</td>
					<td class="input"><input type="text" name="name" id="name" class="text" /></td>
					<td class="label">性别：</td>
					<td class="input"><input type="text" name="sex" id="sex" class="text" /></td>
				</tr>
				<tr>
					<td class="label">生日：</td>
					<td class="input"><input type="text" name="birthday" id="birthday"class="text" /></td>
					<td class="label">年龄：</td>
					<td class="input"><input type="text" name="age" id="age"class="text" /></td>
				</tr>
			</table>
			<input type="text" name="id" id="id"/>
		</form>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="">
		<table>
			<tr>
				<td class="label">姓名：</td>
				<td class="input"><input type="text" name="name" id="name" class="text" readonly /></td>
				<td class="label">性别：</td>
				<td class="input"><input type="text" name="sex" id="sex" class="text" readonly /></td>
			</tr>
			<tr>
				<td class="label">生日：</td>
				<td class="input"><input type="text" name="birthday" id="birthday"class="text" readonly /></td>
				<td class="label">年龄：</td>
				<td class="input"><input type="text" name="age" id="age"class="text" readonly /></td>
			</tr>
		</table>
	</div>
	
	<script>
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};

		$(document).ready(function() {
			search(1);
			$("#submit").click(function() {
				search(1);
			});
		});
		
		function search(page) {
			var o = $('#search_form').serializeObject();
			o["page"]=page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/demo/student/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								var newData = "";
								$.each(
									data.data,
									function(i, item) {
										newData += "<tr>";
										newData += "<td id=\"c\"><input name=\"checkbox\" type=\"checkbox\" value=\""+item.id+"\"></td>";
										newData += "<td id=\"l\">"
												+ item.name
												+ "</td>";
										newData += "<td id=\"r\">"
												+ item.sex
												+ "</td>";
										newData += "<td id=\"r\">"
											+ item.birthday
											+ "</td>";
										newData += "<td id=\"r\">"
											+ item.age
											+ "</td>";
										newData += "</tr>";
									});
								$('#data_tbody').html(newData);
								
								$('#page_info').html(data.pageHtml);
							}
						},
						error : function(data) {
							alert("error")
						}
					});
		}
	</script>

	<script>
		$(function() {
			var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
					[]).add(name).add(email).add(password), tips = $(".validateTips");
			function updateTips(t) {
				tips.text(t).addClass("ui-state-highlight");
				setTimeout(function() {
					tips.removeClass("ui-state-highlight", 1500);
				}, 500);
			}
			function checkLength(o, n, min, max) {
				if (o.val().length > max || o.val().length < min) {
					o.addClass("ui-state-error");
					updateTips("Length of " + n + " must be between " + min
							+ " and " + max + ".");
					return false;
				} else {
					return true;
				}
			}
			function checkRegexp(o, regexp, n) {
				if (!(regexp.test(o.val()))) {
					o.addClass("ui-state-error");
					updateTips(n);
					return false;
				} else {
					return true;
				}
			}
			$("#edit_div").dialog(
					{
						autoOpen : false,
						height : 300,
						width : 500,
						modal : true,
						buttons : {
							"保存" : function() {
								var bValid = true;
								allFields.removeClass("ui-state-error");
								//bValid = bValid && checkLength(name, "username", 3, 16);
								//bValid = bValid && checkLength(email, "email", 6, 80);
								//bValid = bValid && checkLength(password, "password", 5, 16);
								//bValid = bValid && checkRegexp(name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter.");

								// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/

								//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com");
								//bValid = bValid && checkRegexp(password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");
								if (bValid) {
									var jsonuserinfo = $.toJSON($('#edit_form')
											.serializeObject());
									jQuery.ajax({
										type : 'POST',
										contentType : 'application/json',
										url : '/uwoa/demo/student/edit',
										data : jsonuserinfo,
										dataType : 'json',
										success : function(data) {
											search(1);
										},
										error : function(data) {
											alert("error")
										}
									});
									$(this).dialog("close");
								}

							},

							"取消" : function() {

								$(this).dialog("close");

							}

						},

						close : function() {

							allFields.val("").removeClass("ui-state-error");

						}

					});

			$("#detail_div").dialog(
					{
						autoOpen : false,
						height : 300,
						width : 500,
						modal : true,
						buttons : {

							"关闭" : function() {

								$(this).dialog("close");

							}

						},

						"取消" : function() {

							allFields.val("").removeClass("ui-state-error");

						}

					});
			
			function getChecked(name) {
				var o = {};
				var values = "";
				count = 0;
				$("input[name='" + name + "']").each(function() {
					if (this.checked) {
						if (values == "") {
							values = this.value || '';
						} else {
							values += "," + this.value || '';
						}
						count += 1;
					}
				});
				o["ids"] = values;
				o["count"] = count;
				return o;
			}

			//新增按钮
			$("#but_add").button().click(function() {
				$("#edit_div").dialog("open");
				$("input").val("");
			});

			//修改按钮
			$("#but_edit").button().click(
					function() {
						var o = getChecked("checkbox");
						if (o.count != 1) {
							alertDialog("请选择1条记录进行编辑！");
							return false;
						}
						var jsonuserinfo = $.toJSON({"ids":o.ids});
						jQuery.ajax({
							type : 'POST',
							contentType : 'application/json',
							url : '/uwoa/demo/student/query',
							data : jsonuserinfo,
							dataType : 'json',
							success : function(data) {
								if (data && data.success == "true") {
									$("#edit_div").dialog("open");
									$.each(data.data, function(i, item) {
										$("#edit_form input[name='id']").val(item.id);
										$("#edit_form input[name='name']").val(item.name);
										$("#edit_form input[name='sex']").val(item.sex);
										$("#edit_form input[name='birthday']").val(item.birthday);
										$("#edit_form input[name='age']").val(item.age);
									});

								}
							},
							error : function(data) {
								alert("error")
							}
						});
					});

			$("#but_delete").button().click(function() {
				var o = getChecked("checkbox");
				if (o.count < 1) {
					alertDialog("请选择至少1条记录进行删除！");
					return false;
				}
				var jsonuserinfo = $.toJSON({"ids":o.ids});

				confirmDialog("删除操作不可恢复，您确认要删除吗？",function(ok){
					if(ok==true){
						jQuery.ajax({
							type : 'POST',
							contentType : 'application/json',
							url : '/uwoa/demo/student/delete',
							data : jsonuserinfo,
							dataType : 'json',
							success : function(data) {
								if (data && data.success == "true") {
									search(1);
								}
							},
							error : function(data) {
								alert("error")
							}
						});
					}
				});
			});

			$("#but_view").button().click(function() {
				var o = getChecked("checkbox");
				if (o.count != 1) {
					alertDialog("请选择1条记录进行编辑！");
					return false;
				}
				var jsonuserinfo = $.toJSON({"ids":o.ids});
				jQuery.ajax({
					type : 'POST',
					contentType : 'application/json',
					url : '/uwoa/demo/student/query',
					data : jsonuserinfo,
					dataType : 'json',
					success : function(data) {
						if (data && data.success == "true") {
							$("#detail_div").dialog("open");
							$.each(data.data, function(i, item) {
								$("#detail_div input[name='name']").val(item.name);
								$("#detail_div input[name='sex']").val(item.sex);
								$("#detail_div input[name='birthday']").val(item.birthday);
								$("#detail_div input[name='age']").val(item.age);
							});
						}
					},
					error : function(data) {
						alert("error")
					}
				});
			});

			$("#submit").button().click(function() {
				$("#search_form").submit();
			});

		});
	</script>
</body>