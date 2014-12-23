<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.food.foodtype.FoodTypeHelper" %>
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
						<td class="label">菜品编号：</td>
						<td class="input"><input type="text" name="food_code" /></td>
						<td class="label">菜品名称：</td>
						<td class="input"><input type="text" name="food_name" /></td>
						<td class="label">菜品分类：</td>
						<td class="input"><select name="type_id"><%=FoodTypeHelper.optionHtml("ALL") %></select></td>
						<td class="button"><div id="but_search" class="div_but">查询</div></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<br />
	<div id="toolbar_table">
		<a href="/uwoa/resources/normal/moban.xls"><div id="but_downloadexcel" class="div_but" style="width:80px">模版下载</div></a>
		<div id="but_import" class="div_but">导入</div>
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
					<th width="10%">菜品编号</th>
					<th width="15%">菜品名称</th>
					<th width="15%">所属分类</th>
					<th width="10%">单位</th>
					<th width="10%">价格</th>
					<th width="10%">助记码</th>
					<th width="10%">新品</th>
					<th width="10%">特色菜</th>
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
					<td class="label"><font color="#FF0000">*</font> 菜品编号：</td>
					<td class="input"><input type="text" name="food_code" id="food_code" validate="notNull" inputName="菜品编号" /></td>
					<td class="label"><font color="#FF0000">*</font> 菜品名称：</td>
					<td class="input"><input type="text" name="food_name" id="food_name" validate="notNull" inputName="菜品名称" /></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 菜品分类：</td>
					<td class="input"><select name="type_id" id="type_id" validate="notNull" inputName="菜品分类"><%=FoodTypeHelper.optionHtml("SELECT") %></select></td>
					<td class="label">助记码：</td>
					<td class="input"><input type="text" name="mnem_code" id="mnem_code" /></td>
				</tr>
				<tr>
					<td class="label">单位：</td>
					<td class="input"><select name="unit" id="unit"><%=DictionaryHelper.optionHtml("FOOD_UNIT", "NULL")%></select></td>
					<td class="label"><font color="#FF0000">*</font> 价格：</td>
					<td class="input"><input type="text" name="price" id="price" validate="notNull" inputName="价格" /></td>
				</tr>
				<tr>
					<td class="label">是否新品：</td>
					<td class="input"><select name="is_new" id="is_new"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "")%></select></td>
					<td class="label">是否特色菜：</td>
					<td class="input"><select name="is_special" id="is_special"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "")%></select></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 是否称重：</td>
					<td class="input"><select name="is_weigh" id="is_weigh" validate="notNull" inputName="是否称重"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "")%></select></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label"> 排序序号：</td>
					<td class="input"><input type="text" name="order_num" id="order_num" /></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">图片预览：</td>
					<td class="input" colspan="3">
						<span style="color:#000000;cursor:pointer" onclick="uploadDialogFrom='2';uploadDialog();">
							[上传图片]
						</span>
						<br>
						<span id="picView"></span>
						<input id="pic" name="pic" type="text" value=""/>
					</td>
				</tr>
				<tr>
					<td class="label">菜品简介：</td>
					<td class="input" colspan="3"><textarea name="food_intro" id="food_intro"></textarea></td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="rest_id" id="rest_id"/>
			<input type="hidden" name="id" id="id" />
		</form>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="查看明细">
		<table>
			<tr>
				<td class="label">菜品编号：</td>
				<td class="input"><input type="text" name="food_code" id="food_code" /></td>
				<td class="label">菜品名称：</td>
				<td class="input"><input type="text" name="food_name" id="food_name" /></td>
			</tr>
			<tr>
				<td class="label">菜品简拼：</td>
				<td class="input"><input type="text" name="food_code" id="food_code" /></td>
				<td class="label">助记码：</td>
				<td class="input"><input type="text" name="mnem_code" id="mnem_code" /></td>
			</tr>
			<tr>
				<td class="label">菜品分类：</td>
				<td class="input"><select name="type_id" id="type_id"><%=FoodTypeHelper.optionHtml("SELECT") %></select></td>
				<td class="label"></td>
				<td class="input"></td>
			</tr>
			<tr>
				<td class="label">单位：</td>
				<td class="input"><select name="unit" id="unit"><%=DictionaryHelper.optionHtml("FOOD_UNIT", "NULL")%></select></td>
				<td class="label">价格：</td>
				<td class="input"><input type="text" name="price" id="price" /></td>
			</tr>
			<tr>
				<td class="label">是否新品：</td>
				<td class="input"><select name="is_new" id="is_new"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL")%></select></td>
				<td class="label">是否特色菜：</td>
				<td class="input"><select name="is_special" id="is_special"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL")%></select></td>
			</tr>
			<tr>
				<td class="label">是否称重：</td>
				<td class="input"><select name="is_weigh" id="is_weigh"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL")%></select></td>
				<td class="label"></td>
				<td class="input"></td>
			</tr>
			<tr>
				<td class="label">是否上级发布：</td>
				<td class="input"><select name="is_parent_public" id="is_parent_public"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL")%></select></td>
				<td class="label">推荐票数：</td>
				<td class="input"><input type="text" name="vote_num" id="vote_num" /></td>
			</tr>
			<tr>
				<td class="label"> 排序序号：</td>
				<td class="input"><input type="text" name="order_num" id="order_num" /></td>
				<td class="label"></td>
				<td class="input"></td>
			</tr>
			<tr>
				<td class="label">图片预览：</td>
				<td class="input" colspan="3">
					<span id="picView"></span>
					<input id="pic" name="pic" type="hidden" value=""/>
				</td>
			</tr>
			<tr>
				<td class="label">菜品简介：</td>
				<td class="input" colspan="3"><textarea name="food_intro" id="food_intro"></textarea></td>
			</tr>
			<tr>
				<td class="label">备注：</td>
				<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
			</tr>
		</table>
	</div>

	<script>
	var uploadDialogFrom = 0;
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
						url : '/uwoa/food/food/query',
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
													newData += "<td id=\"l\">" + item.food_code + "</td>";
													newData += "<td id=\"l\">" + item.food_name + "</td>";
													newData += "<td id=\"l\">" + item.type_name + "</td>";
													newData += "<td id=\"c\">" + item.unit_name + "</td>";
													newData += "<td id=\"r\">" + item.price + "</td>";
													newData += "<td id=\"l\">" + item.mnem_code + "</td>";
													newData += "<td id=\"c\">" + item.is_new_name + "</td>";
													newData += "<td id=\"c\">" + item.is_special_name + "</td>";
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

		$("#but_downloadexcel").button();
		//导入按钮
		$("#but_import").button().click(function() {
			uploadDialogFrom="1";
			uploadDialog();
		});
		
		//新增按钮
		$("#but_add").button().click(function() {
			$("#edit_div").dialog("open");
			$("#edit_div input").val("");
			$("#edit_div select").val("");
			$("#edit_div textarea").val("");
			$("#edit_form input[name='rest_id']").val("<%=SecurityHelper.getRestId() %>");
			$("#edit_form input[name='is_weigh']").val("checked","true");
			$("#edit_form #picView").html("");
			$("#edit_div select[name=is_new]").val("0");
			$("#edit_div select[name=is_special]").val("0");
			$("#edit_div select[name=is_weigh]").val("0");
		});

		//修改按钮
		$("#but_edit").button().click(
				function() {
					$("#edit_form input").val("");
					$("#edit_form select").val("");
					$("#edit_form textarea").val("");
					$("#edit_form #picView").html("");
					
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
						url : '/uwoa/food/food/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(item.id);
									$("#edit_form input[name='rest_id']").val(item.rest_id);
									$("#edit_form input[name='food_code']").val(item.food_code);
									$("#edit_form input[name='food_name']").val(item.food_name);
									$("#edit_form input[name='food_lcode']").val(item.food_lcode);
									$("#edit_form input[name='mnem_code']").val(item.mnem_code);
									$("#edit_form select[name='type_id']").val(item.type_id);
									$("#edit_form select[name='is_new']").val(item.is_new);
									$("#edit_form select[name='is_special']").val(item.is_special);
									$("#edit_form select[name='is_weigh']").val(item.is_weigh);
									$("#edit_form select[name='unit']").val(item.unit);
									$("#edit_form input[name='price']").val(item.price);
									$("#edit_form input[name='pic']").val(item.pic);
									$("#edit_form #picView").html("<img width=\"156\" height=\"122\" src=\""+item.pic+"\" />");
									$("#edit_form select[name='is_parent_public']").val(item.is_parent_public);
									$("#edit_form input[name='vote_num']").val(item.vote_num);
									$("#edit_form input[name='order_num']").val(item.order_num);
									$("#edit_form textarea[name='food_intro']").val(item.food_intro);
									$("#edit_form textarea[name='memo']").val(item.memo);
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
						url : '/uwoa/food/food/delete',
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
						url : '/uwoa/food/food/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='food_code']").val(item.food_code);
									$("#detail_div input[name='food_name']").val(item.food_name);
									$("#detail_div input[name='food_lcode']").val(item.food_lcode);
									$("#detail_div input[name='mnem_code']").val(item.mnem_code);
									$("#detail_div select[name='type_id']").val(item.type_id);
									$("#detail_div select[name='is_new']").val(item.is_new);
									$("#detail_div select[name='is_special']").val(item.is_special);
									$("#detail_div select[name='is_weigh']").val(item.is_weigh);
									$("#detail_div select[name='unit']").val(item.unit);
									$("#detail_div input[name='price']").val(item.price);
									$("#detail_div input[name='pic']").val(item.pic);
									$("#detail_div #picView").html("<img width=\"156\" height=\"122\" src=\""+item.pic+"\" />");
									$("#detail_div select[name='is_parent_public']").val(item.is_parent_public);
									$("#detail_div input[name='vote_num']").val(item.vote_num);
									$("#detail_div input[name='order_num']").val(item.order_num);
									$("#detail_div textarea[name='food_intro']").val(item.food_intro);
									$("#detail_div textarea[name='memo']").val(item.memo);
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
									url : '/uwoa/food/food/edit',
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
		function uploadOk(fileName){
			if(uploadDialogFrom=="1"){
				$("#upload_div").dialog("close");
				var jsonuserinfo = $.toJSON({
					"fileName" : fileName
				});
				jQuery.ajax({
					async : false,
					type : 'POST',
					contentType : 'application/json',
					url : '/uwoa/food/food/importExcel',
					data : jsonuserinfo,
					dataType : 'json',
					success : function(data) {
						if (data && data.success == "error") {
							alertDialog(data.errorinfo)
						}
						else{
							search(1);
						}
					},
					error : function(data) {
						alert("error")
					}
				});
			}
			else if(uploadDialogFrom=="2"){
				$("#upload_div").dialog("close");
				$("#picView").html("<img src=\"/uwoa/resources/normal/upload/<%=SecurityHelper.getRestId() %>/"+fileName+"\" />");
				$("#edit_div #pic").val("/uwoa/resources/normal/upload/<%=SecurityHelper.getRestId() %>/"+fileName);
			}
		}
	</script>
</body>