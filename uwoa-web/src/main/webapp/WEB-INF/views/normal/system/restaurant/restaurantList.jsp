<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<body>
	<div id="div_search" class="ui-widget">
		<form method="post" id="search_form" name="search_form">
			<table id="search" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th colspan="7">快速查询</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="label">餐厅代码：</td>
						<td class="input"><input type="text" name="rest_code" /></td>
						<td class="label">餐厅名称：</td>
						<td class="input"><input type="text" name="rest_name" /></td>
						<td class="label"></td>
						<td class="input"></td>
						<td class="button"><div id="but_search" class="div_but">查询</div></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<br />
	<div id="toolbar_table">
		<div id="but_add" class="div_but">新增</div>
		<div id="but_edit" class="div_but">修改</div>
		<div id="but_delete" class="div_but">删除</div>
		<div id="but_view" class="div_but">查看</div>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content2">
			<thead>
				<tr class="ui-widget-header ">
					<th width="5%"><input type="checkbox" onclick="$('div#data_table input[name=checkbox]').prop('checked',this.checked)"/></th>
					<th width="5%">序</th>
					<th width="10%">餐厅代码</th>
					<th width="15%">餐厅名称</th>
					<th width="10%">是否集团</th>
					<th width="10%">餐厅管理员用户名</th>
					<th width="50%">备注</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

	<div id="edit_div" title="编辑">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 餐厅代码：</td>
					<td class="input"><input type="text" name="rest_code" id="rest_code" validate="notNull" inputName="餐厅代码" /></td>
					<td class="label"><font color="#FF0000">*</font> 餐厅名称：</td>
					<td class="input"><input type="text" name="rest_name" id="rest_name" validate="notNull" inputName="分类名称" /></td>
				</tr>
				<tr id="adminInfo">
					<td class="label"><font color="#FF0000">*</font> 管理员用户名：</td>
					<td class="input"><input type="text" name="admin_name" id="admin_name" validate="notNull" inputName="管理员用户名" /></td>
					<td class="label"><font color="#FF0000">*</font> 管理员密码：</td>
					<td class="input"><input type="text" name="admin_pwd" id="admin_pwd" validate="notNull" inputName="管理员密码" /></td>
				</tr>
				<tr>
					<td class="label">验证短信用户名：</td>
					<td class="input"><input type="text" name="validate_username" id="validate_username" inputName="验证短信用户名" /></td>
					<td class="label">验证短信密码：</td>
					<td class="input"><input type="text" name="validate_password" id="validate_password" inputName="验证短信密码" /></td>
				</tr>
				<tr>
					<td class="label">营销短信用户名：</td>
					<td class="input"><input type="text" name="marketing_username" id="marketing_username" inputName="营销短信用户名" /></td>
					<td class="label">销短信密码：</td>
					<td class="input"><input type="text" name="marketing_password" id="marketing_password" inputName="营销短信密码" /></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 是否启用会员：</td>
					<td class="input"><select name="open_vip" id="open_vip" validate="notNull" inputName="是否启用会员"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL") %></select></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="id" id="id" />
		</form>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="查看明细">
		<table>
			<tr>
				<td class="label">餐厅代码：</td>
				<td class="input"><input type="text" name="rest_code" id="rest_code" validate="notNull" inputName="餐厅代码" /></td>
				<td class="label">餐厅名称：</td>
				<td class="input"><input type="text" name="rest_name" id="rest_name" validate="notNull" inputName="分类名称" /></td>
			</tr>
			<tr>
				<td class="label">验证短信用户名：</td>
				<td class="input"><input type="text" name="validate_username" id="validate_username" validate="notNull" inputName="验证短信用户名" /></td>
				<td class="label">验证短信密码：</td>
				<td class="input"><input type="password" name="validate_password" id="validate_password" validate="notNull" inputName="验证短信密码" /></td>
			</tr>
			<tr>
				<td class="label">营销短信用户名：</td>
				<td class="input"><input type="text" name="marketing_username" id="marketing_username" validate="notNull" inputName="营销短信用户名" /></td>
				<td class="label">营销短信密码：</td>
				<td class="input"><input type="password" name="marketing_password" id="marketing_password" validate="notNull" inputName="营销短信密码" /></td>
			</tr>
			<tr>
				<td class="label">是否启用会员：</td>
				<td class="input"><select name="open_vip" id="open_vip" validate="notNull" inputName="是否启用会员"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL") %></select></td>
				<td class="label"></td>
				<td class="input"></td>
			</tr>
			<tr>
				<td class="label">备注：</td>
				<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
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

		$(document).ready(function() {
			search(1);
		});

		//校验
		function checkValidate(o,divName){
			var vo={};
			for(var p in o){
				var vp={};
				if($("#"+divName+" input[name="+p+"]").attr("validate")){
					vp['name']=p;
					vp['inputName']=$("#"+divName+" input[name="+p+"]").attr("inputName");
					vp['value']=o[p];
					vp['validate']=$("#"+divName+" input[name="+p+"]").attr("validate");
					vo[p]=vp;
				}
				else if($("#"+divName+" select[name="+p+"]").attr("validate")){
					vp['name']=p;
					vp['inputName']=$("#"+divName+" select[name="+p+"]").attr("inputName");
					vp['value']=o[p];
					vp['validate']=$("#"+divName+" select[name="+p+"]").attr("validate");
					vo[p]=vp;
				}
			}
			return vo;
		}
		
		//查询
		function search(page) {
			var o = $('#search_form').serializeObject();
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/system/restaurant/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								var newData = "";
								$
										.each(
												data.data,
												function(i, item) {
													newData += "<tr>";
													newData += "<td id=\"c\"><input name=\"checkbox\" type=\"checkbox\" value=\""+item.id+"\"></td>";
													newData += "<td id=\"c\">"
															+ (data.pageInfo.pageRow
																	* (data.pageInfo.page - 1)
																	+ i + 1)
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.rest_code
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.rest_name
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.is_group_name
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.admin_name
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.memo
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

		//新增按钮
		$("#but_add").button().click(function() {
			$("#edit_div").dialog("open");
			$("#edit_div input").val("");
			$("#edit_div select").val("");
			$("#edit_div textarea").val("");
			$("#edit_form input[name=admin_name]").removeAttr("disabled");
			$("#edit_form input[name=admin_pwd]").removeAttr("disabled");
		});

		//修改按钮
		$("#but_edit").button().click(
				function() {
					$("#edit_form input").val("");
					$("#edit_form select").val("");
					$("#edit_form textarea").val("");
					$("#edit_form input[name=admin_name]").attr("disabled",true);
					$("#edit_form input[name=admin_pwd]").attr("disabled",true);
					
					var o = getChecked("checkbox");
					if (o.count != 1) {
						alertDialog("请选择1条记录进行编辑！");
						return false;
					}
					var jsonuserinfo = $.toJSON({
						"ids" : o.ids
					});
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/system/restaurant/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(
											item.id);
									$("#edit_form input[name='rest_code']")
											.val(item.rest_code);
									$("#edit_form input[name='rest_name']")
											.val(item.rest_name);
									$("#edit_form select[name='is_group']")
											.val(item.is_group);
									$("#edit_form input[name='marketing_username']")
											.val(item.marketing_username);
									$("#edit_form input[name='marketing_password']")
											.val(item.marketing_password);
									$("#edit_form input[name='validate_username']")
											.val(item.validate_username);
									$("#edit_form input[name='validate_password']")
											.val(item.validate_password);
									$("#edit_form select[name='open_vip']")
											.val(item.open_vip);
									$("#edit_form textarea[name='memo']").val(
											item.memo);
								});

							}
						},
						error : function(data) {
							alert("error")
						}
					});
				});

		//删除
		$("#but_delete").button().click(function() {
			var o = getChecked("checkbox");
			if (o.count < 1) {
				alertDialog("请选择至少1条记录进行删除！");
				return false;
			}
			var jsonuserinfo = $.toJSON({
				"ids" : o.ids
			});

			confirmDialog("删除操作不可恢复，您确认要删除吗？", function(ok) {
				if (ok == true) {
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/system/restaurant/delete',
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

		//查看
		$("#but_view").button().click(
				function() {
					$("#detail_div input").val("");
					$("#detail_div select").val("");
					$("#detail_div textarea").val("");
					$("#detail_div input").attr("disabled",true);
					$("#detail_div select").attr("disabled",true);
					$("#detail_div textarea").attr("disabled",true);
					
					var o = getChecked("checkbox");
					if (o.count != 1) {
						alertDialog("请选择1条记录进行查看！");
						return false;
					}
					var jsonuserinfo = $.toJSON({
						"ids" : o.ids
					});
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/system/restaurant/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='rest_code']")
											.val(item.rest_code);
									$("#detail_div input[name='rest_name']")
											.val(item.rest_name);
									$("#detail_div select[name='is_group']")
											.val(item.is_group);
									$("#detail_div input[name='marketing_username']")
											.val(item.marketing_username);
									$("#detail_div input[name='marketing_password']")
											.val(item.marketing_password);
									$("#detail_div input[name='validate_username']")
											.val(item.validate_username);
									$("#detail_div input[name='validate_password']")
											.val(item.validate_password);
									$("#detail_div select[name='open_vip']")
											.val(item.open_vip);
									$("#detail_div textarea[name='memo']")
											.val(item.memo);
								});
							}
						},
						error : function(data) {
							alert("error")
						}
					});
				});

		//查询
		$("#but_search").button().click(function() {
			search(1);
		});

		//编辑确定
		$("#edit_div").dialog(
				{
					autoOpen : false,
					height : 350,
					width : 700,
					modal : true,
					buttons : {
						"保存" : function() {
							var bValid = true;
							var o=$('#edit_form').serializeObject();
							var vo=checkValidate(o,"edit_div");
							var jsoncheckinfo = $.toJSON(vo);
							o["validate"]=vo;
							var bValid=true;
							jQuery.ajax({
								async : false,
								type : 'POST',
								contentType : 'application/json',
								url : '/uwoa/system/validate',
								data : jsoncheckinfo,
								dataType : 'json',
								success : function(data) {
									if (data && data.success == "error") {
										alertDialog(data.errorInfo);
										bValid=false;
									}
								},
								error : function(data) {
									alert("error")
								}
							});
							
							jsoncheckinfo = $.toJSON({
								"id" : $("#id").val(),
								"inputName" : "餐厅代码",
								"rest_code" : $("#rest_code").val()
							});
							jQuery.ajax({
								async : false,
								type : 'POST',
								contentType : 'application/json',
								url : '/uwoa/system/restaurant/repeat',
								data : jsoncheckinfo,
								dataType : 'json',
								success : function(data) {
									if (data && data.success == "error") {
										alertDialog(data.errorInfo);
										bValid=false;
									}
								},
								error : function(data) {
									alert("error")
								}
							});
							
							jsoncheckinfo = $.toJSON({
								"id" : $("#id").val(),
								"inputName" : "管理员用户名",
								"admin_name" : $("#admin_name").val()
							});
							jQuery.ajax({
								async : false,
								type : 'POST',
								contentType : 'application/json',
								url : '/uwoa/system/restaurant/repeat',
								data : jsoncheckinfo,
								dataType : 'json',
								success : function(data) {
									if (data && data.success == "error") {
										alertDialog(data.errorInfo);
										bValid=false;
									}
								},
								error : function(data) {
									alert("error")
								}
							});
							
							if (bValid) {
								var jsonuserinfo = $.toJSON(o);
								jQuery.ajax({
									async : false,
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/system/restaurant/edit',
									data : jsonuserinfo,
									dataType : 'json',
									success : function(data) {
										if (data && data.success == "error") {
											alertDialog(data.errorInfo);
										}
										else{
											search(1);
										}
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
					}
				});

		//查看确定
		$("#detail_div").dialog({
			autoOpen : false,
			height : 350,
			width : 700,
			modal : true,
			buttons : {
				"关闭" : function() {
					$(this).dialog("close");
				}
			},
			"取消" : function() {
			}
		});
	</script>
</body>