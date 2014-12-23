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
						<td class="label">关键字：</td>
						<td class="input"><input type="text" name="key" /></td>
						<td class="label">名称：</td>
						<td class="input"><input type="text" name="name" /></td>
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
					<th width="45%">关键字</th>
					<th width="45%">名称</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

	<div id="edit_div" title="编辑">
		<form id="edit_form" name="edit_form">
			<table id="main_table">
				<tr>
					<td class="label"><font color="#FF0000">*</font> 关键字：</td>
					<td class="input"><input type="text" name="key" id="key" validate="notNull" inputName="关键字" /></td>
					<td class="label"><font color="#FF0000">*</font> 名称：</td>
					<td class="input"><input type="text" name="name" id="name" validate="notNull" inputName="名称" /></td>
				</tr>
			</table>
			<input type="hidden" name="id" id="id" />
		</form>
		<input type="hidden" name="maxRow" id="maxRow" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="45%"><font color="#FF0000">*</font> 名称</th>
						<th width="45%"><font color="#FF0000">*</font> 值</th>
						<th width="5%"></th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
		<div id="toolbar_table">
			<div id="but_addRow" style="width:80px;height:25px;">
				<table><tr>
					<td><img src="${pageContext.request.contextPath}/resources/normal/images/but_add.gif"></img></td>
					<td> 增行</td>
				</tr></table>
			</div>
		</div>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="查看明细">
		<table>
			<tr>
				<td class="label">关键字：</td>
				<td class="input"><input type="text" name="key" id="key" /></td>
				<td class="label">名称：</td>
				<td class="input"><input type="text" name="name" id="name" /></td>
			</tr>
		</table>
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="45%">名称</th>
						<th width="45%">值</th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
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
						url : '/uwoa/system/dictionary/query',
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
															+ item.key
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.name
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
			$('#sub_tbody').html("");
		});

		//修改按钮
		$("#but_edit").button().click(
				function() {
					$("#edit_div input").val("");
					$("#edit_div select").val("");
					$("#edit_div textarea").val("");
					$('#edit_div #sub_tbody').html("");
					
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
						url : '/uwoa/system/dictionary/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(item.id);
									$("#edit_form input[name='key']").val(item.key);
									$("#edit_form input[name='name']").val(item.name);
									var newData = "";
									$.each(item.sub.data, function(j, subItem) {
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += "<td id=\"l\"><input type=\"text\" name=\"name\" id=\"name\" validate=\"notNull\" inputName=\"子表-名称\" style=\"width:100%\" value=\""+subItem.name+"\"/></td>";
										newData += "<td id=\"l\"><input type=\"text\" name=\"value\" id=\"value\" validate=\"notNull\" inputName=\"子表-值\" style=\"width:100%\" value=\""+subItem.value+"\"/></td>";
										newData += "<td id=\"c\"><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+subItem.id+"\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
										newData += "</tr>";
									});
									$('#edit_div #maxRow').val(item.sub.data.length);
									$('#edit_div #sub_tbody').html(newData);
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
						url : '/uwoa/system/dictionary/delete',
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
						url : '/uwoa/system/dictionary/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='key']").val(item.key);
									$("#detail_div input[name='name']").val(item.name);
									var newData = "";
									$.each(item.sub.data, function(j, subItem) {
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += "<td id=\"l\">"+subItem.name+"</td>";
										newData += "<td id=\"l\">"+subItem.value+"</td>";
										newData += "</tr>";
									});
									$('#detail_div #sub_tbody').html(newData);
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
							var o=$('#edit_form').serializeObject();
							var vo=checkValidate(o,"edit_div");
							//子表
							var rowCount = parseInt($('#maxRow').val());
							var subo={};
							var subvo={};
							for(var i=1;i<=rowCount;i++){
								if($("#edit_div #sub_tbody #row_"+i).val()!=undefined){
									var row={};
									var vrow={};
									row["name"]=$("#edit_div #sub_tbody #row_"+i+" input[name=name]").val();
									row["value"]=$("#edit_div #sub_tbody #row_"+i+" input[name=value]").val();
									row["id"]=$("#edit_div #sub_tbody #row_"+i+" input[name=id]").val();
									var vrow=checkValidate(row,"edit_div #sub_tbody #row_"+i);
									subo[i]=row;
									subvo[i]=vrow;
								}
							}
							o["sub"]=subo;
							vo["sub"]=subvo;
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
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/system/dictionary/edit',
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
		
		//新增按钮
		$("#but_addRow").button().click(function() {
			if($('#edit_div #maxRow').val()==""){
				$('#edit_div #maxRow').val(0);
			}
			$('#edit_div #maxRow').val(parseInt($('#edit_div #maxRow').val())+1);
			
			var newData="";
			newData += "<tr id=\"row_"+$('#maxRow').val()+"\">";
			newData += "<td id=\"l\"><input type=\"text\" name=\"name\" id=\"name\" validate=\"notNull\" inputName=\"子表-名称\" style=\"width:100%\"/></td>";
			newData += "<td id=\"l\"><input type=\"text\" name=\"value\" id=\"value\" validate=\"notNull\" inputName=\"子表-值\" style=\"width:100%\" /></td>";
			newData += "<td id=\"c\"><input type=\"hidden\" name=\"id\" id=\"id\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
			newData += "</tr>";
			$('#edit_div #sub_tbody').append(newData);
		});
		
		//新增按钮
		function deleteRow(obj) {
			$(obj).parent().parent().remove();
		}
	</script>
</body>