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
						<td class="label">订单开始日期：</td>
						<td class="input"><input type="text" name="beginDate" id="beginDate"/></td>
						<td class="label">订单结束日期：</td>
						<td class="input"><input type="text" name="endDate" id="endDate"/></td>
						<td class="label">菜品分类：</td>
						<td class="input"><select name="type_id"><%=FoodTypeHelper.optionHtml("ALL") %></select></td>
						<td class="button"><div id="but_search" class="div_but">查询</div></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<br />
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content2">
			<thead>
				<tr class="ui-widget-header ">
					<th>序</th>
					<th>菜品名称</th>
					<th>菜品分类</th>
					<th>销售量</th>
					<th>被推荐次数</th>
					<th>推荐排名</th>
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

		$(document).ready(function() {
			$("#beginDate").datepicker();
			$("#endDate").datepicker();
			//search(1);
		});
		
		//查询
		function search(page) {
			var o = $('#search_form').serializeObject();
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/report/report2/query',
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
													newData += "<td id=\"c\">"
															+ (i + 1)
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.food_name
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.type_name
															+ "</td>";
													newData += "<td id=\"r\">"
															+ item.food_count
															+ "</td>";
													newData += "<td id=\"r\">"
															+ item.countreco
															+ "</td>";
													newData += "<td id=\"r\">"
															+ item.orderbyreco
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

		//查询
		$("#but_search").button().click(function() {
			search(1);
		});
	</script>
</body>