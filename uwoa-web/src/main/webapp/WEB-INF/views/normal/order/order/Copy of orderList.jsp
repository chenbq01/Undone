<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.restaurant.table.TableHelper"%>
<%@ page import="cn.com.uwoa.business.food.food.FoodHelper" %>
<body>
	<style>
	    div#checkout_div table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
	 	div#checkout_div table td.label { width:20%; text-align: right;}
	 	div#checkout_div table td.input { width:30%; text-align: left;}
	 	div#checkout_div table input { width:150px;}
	 	div#checkout_div table select { width:154px;}
	 	div#checkout_div table textarea { width:486px;height:60px;}
	</style>
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
						<td class="label">桌号：</td>
						<td class="input"><select name="table_id"><%=TableHelper.optionHtml("ALL") %></select></td>
						<td class="label">订单号：</td>
						<td class="input"><input type="text" name="order_no" /></td>
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
		<div id="but_checkout" class="div_but">结账</div>
		<div id="but_print" class="div_but">打印</div>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content2">
			<thead>
				<tr class="ui-widget-header ">
					<th width="5%"><input type="checkbox" onclick="$('div#data_table input[name=checkbox]').prop('checked',this.checked)"/></th>
					<th width="5%">序</th>
					<th width="25%">订单号</th>
					<th width="20%">桌号</th>
					<th width="15%">金额</th>
					<th width="15%">优惠后金额</th>
					<th width="15%">人数</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>
	<iframe id="printFrame" style="width:0;height:0"></iframe>

	<div id="edit_div" title="编辑">
		<form id="edit_form" name="edit_form">
			<table id="main_table">
				<tr>
					<td class="label"><font color="#FF0000">*</font> 订单号：</td>
					<td class="input"><input type="text" name="order_no" id="order_no" validate="notNull" inputName="订单号" /></td>
					<td class="label"><font color="#FF0000">*</font> 桌号：</td>
					<td class="input"><select name="table_id" id="table_id" validate="notNull" inputName="桌号"><%=TableHelper.optionHtml("SELECT") %></select></td>
				</tr>
				<tr>
					<td class="label">人数：</td>
					<td class="input"><input type="text" name="person_count" id="person_count" inputName="人数" /></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 订单金额：</td>
					<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额" readonly /></td>
					<td class="label"><font color="#FF0000">*</font> 优惠金额：</td>
					<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额" readonly /></td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="status" id="status"/>
			<input type="hidden" name="rest_id" id="rest_id"/>
			<input type="hidden" name="id" id="id" />
		</form>
		<input type="hidden" name="maxRow" id="maxRow" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="20%"><font color="#FF0000">*</font> 菜名</th>
						<th width="10%">价格</th>
						<th width="10%">单位</th>
						<th width="10%"><font color="#FF0000">*</font> 数量</th>
						<th width="15%"><font color="#FF0000">*</font> 金额</th>
						<th width="15%"><font color="#FF0000">*</font> 优惠后金额</th>
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
				<td class="label">订单号：</td>
				<td class="input"><input type="text" name="order_no" id="order_no" validate="notNull" inputName="订单号" /></td>
				<td class="label">桌号：</td>
				<td class="input"><select name="table_id" id="table_id" validate="notNull" inputName="桌号"><%=TableHelper.optionHtml("SELECT") %></select></td>
			</tr>
			<tr>
				<td class="label">人数：</td>
				<td class="input"><input type="text" name="person_count" id="person_count" inputName="人数" /></td>
				<td class="label"></td>
				<td class="input"></td>
			</tr>
			<tr>
				<td class="label">订单金额：</td>
				<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额" readonly /></td>
				<td class="label">优惠金额：</td>
				<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额" readonly /></td>
			</tr>
			<tr>
				<td class="label">备注：</td>
				<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="20%">菜名</th>
						<th width="10%">价格</th>
						<th width="10%">单位</th>
						<th width="10%">数量</th>
						<th width="15%">金额</th>
						<th width="15%">优惠后金额</th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
	</div>

	<div id="checkout_div" title="结账">
		<form id="checkout_form" name="choutout_form">
			<table id="main_table">
				<tr>
					<td class="label">订单号：</td>
					<td class="input"><input type="text" name="order_no" id="order_no" validate="notNull" inputName="订单号" disabled/></td>
					<td class="label">桌号：</td>
					<td class="input"><select name="table_id" id="table_id" validate="notNull" inputName="桌号" disabled><%=TableHelper.optionHtml("SELECT") %></select></td>
				</tr>
				<tr>
					<td class="label">人数：</td>
					<td class="input"><input type="text" name="person_count" id="person_count" inputName="人数" disabled/></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">订单金额：</td>
					<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额" disabled /></td>
					<td class="label">优惠金额：</td>
					<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额" disabled /></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 付款方式：</td>
					<td class="input"><select name="pay_type" id="pay_type" validate="notNull" inputName="付款方式"><%=DictionaryHelper.optionHtml("ORDER_PAYTYPE", "SELECT") %></select></td>
					<td class="label"><font color="#FF0000">*</font> 实付金额：</td>
					<td class="input"><input type="text" name="pay_amount" id="pay_amount" validate="notNull" inputName="优惠金额" onchange="computePay()"/></td>
				</tr>
				<tr>
					<td class="label"></td>
					<td class="input"></td>
					<td class="label"><font color="#FF0000">*</font> 找零：</td>
					<td class="input"><input type="text" name="pay_change" id="pay_change" validate="notNull" inputName="找零" readonly /></td>
				</tr>
				<tr>
					<td class="label">发票抬头：</td>
					<td class="input" colspan="3"><input type="text" name="invoice_title" id="invoice_title" style="width:488px"/></td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo" disabled></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="status" id="status"/>
			<input type="hidden" name="rest_id" id="rest_id"/>
			<input type="hidden" name="id" id="id" />
		</form>
		<input type="hidden" name="maxRow" id="maxRow" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="20%">菜名</th>
						<th width="10%">价格</th>
						<th width="10%">单位</th>
						<th width="10%">数量</th>
						<th width="15%">金额</th>
						<th width="15%">优惠后金额</th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
		<p class="validateTips"></p>
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
			o["status"]="02";
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/order/order/query',
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
													newData += "<td id=\"l\">" + item.order_no + "</td>";
													newData += "<td id=\"l\">" + item.table_name + "</td>";
													newData += "<td id=\"r\">" + item.order_amount + "</td>";
													newData += "<td id=\"r\">" + item.favor_amount + "</td>";
													newData += "<td id=\"r\">" + item.person_count + "</td>";
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
			$("#edit_form input[name='rest_id']").val("<%=SecurityHelper.getRestId() %>");
			$('#sub_tbody').html("");
			$("#edit_div input[name=status]").val("02");
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
						url : '/uwoa/order/order/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(item.id);
									$("#edit_form input[name='order_no']").val(item.order_no);
									$("#edit_form select[name='table_id']").val(item.table_id);
									$("#edit_form input[name='person_count']").val(item.person_count);
									$("#edit_form input[name='order_amount']").val(item.order_amount);
									$("#edit_form input[name='favor_amount']").val(item.favor_amount);
									$("#edit_div input[name=status]").val(item.status);
									$.each(item.sub.data, function(j, subItem) {
										var newData = "";
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+(j+1)+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
										newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" validate=\"notNull\" inputName=\"子表-价格\" style=\"width:100%\" readonly value=\""+subItem.food_price+"\"/></td>";
										newData += "<td id=\"l\" name=\"d3\">"+subItem.unit_name+"</td>";
										newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"food_count\" id=\"food_count\" validate=\"notNull\" inputName=\"子表-数量\" style=\"width:100%\" onchange=\"compute("+(j+1)+")\" value=\""+subItem.food_count+"\"/></td>";
										newData += "<td id=\"l\" name=\"d5\"><input type=\"text\" name=\"food_amount\" id=\"food_amount\" validate=\"notNull\" inputName=\"子表-金额\" style=\"width:100%\" onchange=\"sumSub()\" readonly value=\""+subItem.food_amount+"\"/></td>";
										newData += "<td id=\"l\" name=\"d6\"><input type=\"text\" name=\"favor_amount\" id=\"favor_amount\" validate=\"notNull\" inputName=\"子表-优惠后金额\" style=\"width:100%\" onchange=\"sumSub()\" value=\""+subItem.favor_amount+"\"/></td>";
										newData += "<td id=\"c\" name=\"d7\"><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+subItem.id+"\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
										newData += "</tr>";
										$('#edit_div #sub_tbody').append(newData);
										$('#edit_div #sub_tbody #row_'+(j+1)+" select[name=food_id]").val(subItem.food_id);
									});
									$('#edit_div #maxRow').val(item.sub.data.length);
									$("#edit_div #sub_tbody select[name=food_id]").combobox();
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
						url : '/uwoa/order/order/delete',
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
						url : '/uwoa/order/order/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='id']").val(item.id);
									$("#detail_div input[name='order_no']").val(item.order_no);
									$("#detail_div select[name='table_id']").val(item.table_id);
									$("#detail_div input[name='person_count']").val(item.person_count);
									$("#detail_div input[name='order_amount']").val(item.order_amount);
									$("#detail_div input[name='favor_amount']").val(item.favor_amount);
									$.each(item.sub.data, function(j, subItem) {
										var newData = "";
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += "<td id=\"l\">"+subItem.food_name+"</td>";
										newData += "<td id=\"l\">"+subItem.food_price+"</td>";
										newData += "<td id=\"l\">"+subItem.unit_name+"</td>";
										newData += "<td id=\"l\">"+subItem.food_count+"</td>";
										newData += "<td id=\"l\">"+subItem.food_amount+"</td>";
										newData += "<td id=\"l\">"+subItem.favor_amount+"</td>";
										newData += "</tr>";
										$('#detail_div #sub_tbody').append(newData);
										$('#detail_div #sub_tbody #row_'+(j+1)+" select[name=food_id]").val(subItem.food_id);
									});
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
									row["food_id"]=$("#edit_div #sub_tbody #row_"+i+" select[name=food_id]").val();
									row["food_count"]=$("#edit_div #sub_tbody #row_"+i+" input[name=food_count]").val();
									row["food_price"]=$("#edit_div #sub_tbody #row_"+i+" input[name=food_price]").val();
									row["food_amount"]=$("#edit_div #sub_tbody #row_"+i+" input[name=food_amount]").val();
									row["favor_amount"]=$("#edit_div #sub_tbody #row_"+i+" input[name=favor_amount]").val();
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
									url : '/uwoa/order/order/edit',
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
						"打印" : function() {
							var id=$("#edit_div input[name='id']").val();
							$("#printFrame").attr("src","/uwoa/order/order/print/"+id);
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
				"打印" : function() {
					var id=$("#detail_div input[name='id']").val();
					$("#printFrame").attr("src","/uwoa/order/order/print/"+id);
				},
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
			newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+$('#maxRow').val()+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
			newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" validate=\"notNull\" inputName=\"子表-价格\" style=\"width:100%\" readonly/></td>";
			newData += "<td id=\"l\" name=\"d3\"></td>";
			newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"food_count\" id=\"food_count\" validate=\"notNull\" inputName=\"子表-数量\" style=\"width:100%\" onchange=\"compute("+$('#maxRow').val()+")\"/></td>";
			newData += "<td id=\"l\" name=\"d5\"><input type=\"text\" name=\"food_amount\" id=\"food_amount\" validate=\"notNull\" inputName=\"子表-金额\" style=\"width:100%\" onchange=\"sumSub()\" readonly/></td>";
			newData += "<td id=\"l\" name=\"d6\"><input type=\"text\" name=\"favor_amount\" id=\"favor_amount\" validate=\"notNull\" inputName=\"子表-优惠后金额\" style=\"width:100%\" onchange=\"sumSub()\" /></td>";
			newData += "<td id=\"c\" name=\"d7\"><input type=\"hidden\" name=\"id\" id=\"id\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
			newData += "</tr>";
			$('#edit_div #sub_tbody').append(newData);
			$( "#food_id_"+$('#maxRow').val() ).combobox();
		});
		
		//新增按钮
		function deleteRow(obj) {
			$(obj).parent().parent().remove();
			sumSub();
		}
		function comboboxCallback(option){
			var rowid=option.getAttribute("rowid");
			$("#edit_div #data_table #row_"+rowid+" td[name=d2] input").val(option.getAttribute("price"));
			$("#edit_div #data_table #row_"+rowid+" td[name=d3]").html(option.getAttribute("unit"));
			compute(rowid);
		}
		function compute(rowid){
			var food_count=parseFloat($("#edit_div #data_table #row_"+rowid+" input[name=food_count]").val());
			var food_price=parseFloat($("#edit_div #data_table #row_"+rowid+" input[name=food_price]").val());
			if(!food_count){
				food_count=0;
			}
			if(!food_price){
				food_price=0;
			}
			$("#edit_div #data_table #row_"+rowid+" input[name=food_amount]").val(food_count*food_price);
			$("#edit_div #data_table #row_"+rowid+" input[name=favor_amount]").val(food_count*food_price);
			sumSub();
		}
		
		function computePay(){
			var pay_amount=parseFloat($("#checkout_div input[name=pay_amount]").val());
			var favor_amount=parseFloat($("#checkout_div input[name=favor_amount]").val());
			if(!pay_amount){
				pay_amount=0;
			}
			if(!favor_amount){
				favor_amount=0;
			}
			$("#checkout_div input[name=pay_change]").val(pay_amount-favor_amount);
		}
		
		function sumSub(){
			var rowCount = parseInt($('#maxRow').val());
			var sumFoodAmount = 0;
			var sumFavorAmount = 0;
			for(i=1;i<=rowCount;i++){
				var foodAmount=parseFloat($("#edit_div #data_table #row_"+i+" input[name=food_amount]").val());
				var favorAmount=parseFloat($("#edit_div #data_table #row_"+i+" input[name=favor_amount]").val());
				if(!foodAmount){
					foodAmount=0;
				}
				if(!favorAmount){
					favorAmount=0;
				}
				sumFoodAmount=sumFoodAmount+foodAmount;
				sumFavorAmount=sumFavorAmount+favorAmount;
			}
			$("#edit_form input[name=order_amount]").val(sumFoodAmount);
			$("#edit_form input[name=favor_amount]").val(sumFavorAmount);
		}
		
		//打印
		$("#but_print").button().click(function() {
			var o = getChecked("checkbox");
			if (o.count < 1) {
				alertDialog("请选择1条记录进行打印！");
				return false;
			}

			$("#printFrame").attr("src","/uwoa/order/order/print/"+o.ids);
		});
		
		//结账按钮
		$("#but_checkout").button().click(
				function() {
					$("#checkout_div input").val("");
					$("#checkout_div select").val("");
					$("#checkout_div textarea").val("");
					$('#checkout_div #sub_tbody').html("");
					
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
						url : '/uwoa/order/order/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#checkout_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#checkout_form input[name='id']").val(item.id);
									$("#checkout_form input[name='order_no']").val(item.order_no);
									$("#checkout_form select[name='table_id']").val(item.table_id);
									$("#checkout_form input[name='person_count']").val(item.person_count);
									$("#checkout_form input[name='order_amount']").val(item.order_amount);
									$("#checkout_form input[name='favor_amount']").val(item.favor_amount);
									$("#checkout_div input[name=status]").val("03");
									$.each(item.sub.data, function(j, subItem) {
										var newData = "";
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += "<td id=\"l\">"+subItem.food_name+"</td>";
										newData += "<td id=\"l\">"+subItem.food_price+"</td>";
										newData += "<td id=\"l\">"+subItem.unit_name+"</td>";
										newData += "<td id=\"l\">"+subItem.food_count+"</td>";
										newData += "<td id=\"l\">"+subItem.food_amount+"</td>";
										newData += "<td id=\"l\">"+subItem.favor_amount+"</td>";
										newData += "</tr>";
										$('#checkout_div #sub_tbody').append(newData);
										$('#checkout #sub_tbody #row_'+(j+1)+" select[name=food_id]").val(subItem.food_id);
									});
								});

							}
						},
						error : function(data) {
							alert("error")
						}
					});
				});
		
		//编辑确定
		$("#checkout_div").dialog(
				{
					autoOpen : false,
					height : 350,
					width : 700,
					modal : true,
					buttons : {
						"保存" : function() {
							var o=$('#checkout_form').serializeObject();
							var vo=checkValidate(o,"checkout_div");
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
									url : '/uwoa/order/order/edit',
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
	</script>
</body>