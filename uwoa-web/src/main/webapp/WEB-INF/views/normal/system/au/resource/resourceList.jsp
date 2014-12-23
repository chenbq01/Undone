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
						<td class="label">资源名称：</td>
						<td class="input"><input type="text" name="name" /></td>
						<td class="label"></td>
						<td class="input"></td>
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
					<th width="15">资源名称</th>
					<th width="15%">资源类型</th>
					<th width="60%">URL</th>
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
					<td class="label"><font color="#FF0000">*</font> 资源名称：</td>
					<td class="input"><input type="text" name="name" id="name" validate="notNull" inputName="资源名称" /></td>
					<td class="label"><font color="#FF0000">*</font> 资源类型：</td>
					<td class="input"><select name="type" id="type" validate="notNull" inputName="资源类型"><%=DictionaryHelper.optionHtml("RESOURCE_TYPE", "SELECT")%></select></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> URL：</td>
					<td class="input" colspan="3"><input type="text" name="url" id="url" validate="notNull" inputName="URL" style="width:488px" /></td>
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
				<td class="label">资源名称：</td>
				<td class="input"><input type="text" name="name" id="name" validate="notNull" inputName="资源名称" /></td>
				<td class="label">资源类型：</td>
				<td class="input"><select name="type" id="type" validate="notNull" inputName="资源类型"><%=DictionaryHelper.optionHtml("RESOURCE_TYPE", "SELECT")%></select></td>
			</tr>
			<tr>
				<td class="label">URL：</td>
				<td class="input" colspan="3"><input type="text" name="url" id="url" validate="notNull" inputName="URL" style="width:488px" /></td>
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
						url : '/uwoa/system/au/resource/query',
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
															+ item.name
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.type_name
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.url
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
		});

		//修改按钮
		$("#but_edit").button().click(
				function() {
					$("#edit_form input").val("");
					$("#edit_form select").val("");
					$("#edit_form textarea").val("");
					
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
						url : '/uwoa/system/au/resource/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(
											item.id);
									$("#edit_form input[name='name']")
											.val(item.name);
									$("#edit_form select[name='type']")
											.val(item.type);
									$("#edit_form input[name='url']")
											.val(item.url);
									$("#edit_form textarea[name='memo']")
											.val(item.memo);
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
						url : '/uwoa/system/au/resource/delete',
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
						url : '/uwoa/system/au/resource/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='name']")
											.val(item.name);
									$("#detail_div select[name='type']")
											.val(item.type);
									$("#detail_div input[name='url']")
											.val(item.url);
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
							
							if (bValid) {
								var jsonuserinfo = $.toJSON(o);
								jQuery.ajax({
									async : false,
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/system/au/resource/edit',
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