<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.food.food.FoodHelper" %>
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
						<td class="label">订单开始日期：</td>
						<td class="input"><input type="text" name="beginDate" id="beginDate"/></td>
						<td class="label">订单结束日期：</td>
						<td class="input"><input type="text" name="endDate" id="endDate"/></td>
						<td class="label">菜品名称</td>
						<td class="input">
						<select name="food_id" id="food_id" validate="notNull" inputName="菜品名称" style="width:100%"><%=FoodHelper.optionSubHtml("ALL") %></select>
						</td>
						<td class="button"><div id="but_search" class="div_but">查询</div></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<br />
	<div id="toolbar_table">
		<div id="but_send" class="div_but" style="width:80px">发送短信</div>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content2">
			<thead>
				<tr class="ui-widget-header ">
					<th width="5%"><input type="checkbox" onclick="$('div#data_table input[name=checkbox]').prop('checked',this.checked)"/></th>
					<th>序</th>
					<th>会员手机号码</th>
					<th>点该菜品次数</th>
					<th>推荐该菜品次数</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

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
			$("#beginDate").datepicker();
			$("#endDate").datepicker();
			$("#food_id").combobox();
			//search(1);
		});
		
		//查询
		function search(page) {
			if($("#food_id").val()==""){
				alertDialog("请输入菜品名称");
				return false;
			}
			var o = $('#search_form').serializeObject();
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/report/report4/query',
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
													newData += "<td id=\"c\"><input name=\"checkbox\" type=\"checkbox\" value=\""+item.mobile_num+"\"></td>";
													newData += "<td id=\"c\">"
															+ (data.pageInfo.pageRow
																	* (data.pageInfo.page - 1)
																	+ i + 1)
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.mobile_num
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.countdc
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.counttj
															+ "</td>";
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

		//查询
		$("#but_search").button().click(function() {
			search(1);
		});
		function comboboxCallback(option){
		}
		
		//新增按钮
		$("#but_send").button().click(function() {
			var o = getChecked("checkbox");
			if (o.count < 1) {
				alertDialog("请选择至少1条记录进行发送短信！");
				return false;
			}
			var msg = prompt("请输入短信内容：","");
			var jsonuserinfo = $.toJSON({
				"ids" : o.ids,
				msg : msg
			});

			confirmDialog("发送短信操作不可恢复，您确认要发送短信？", function(ok) {
				if (ok == true) {
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/report/report4/sendMsg',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								alertDialog("短信发送成功！");
							}
							else{
								alertDialog("短信发送失败！");
							}
						},
						error : function(data) {
							alert("error")
						}
					});
				}
			});
		});
		
	</script>
</body>