<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.restaurant.table.TableHelper"%>
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
						<td class="label">是否会员</td>
						<td class="input"><select id="is_huiyuan" name="is_huiyuan"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "ALL") %></select></td>
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
					<th>订单号</th>
					<th>是否会员</th>
					<th>订单金额</th>
					<th>优惠后金额</th>
					<th>实付金额</th>
					<th>就餐人数</th>
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
					<td class="label">订单号：</td>
					<td class="input"><input type="text" name="order_no" id="order_no" disabled="disabled"/></td>
					<td class="label">桌号：</td>
					<td class="input"><select name="table_id" id="table_id" validate="notNull" inputName="桌号" disabled="disabled"><%=TableHelper.optionHtml("SELECT") %></select></td>
				</tr>
				<tr>
					<td class="label">人数：</td>
					<td class="input"><input type="text" name="person_count" id="person_count" inputName="人数" disabled="disabled" /></td>
					<td class="label">是否会员：</td>
					<td class="input"><select id="is_huiyuan" disabled><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL") %></select></td>
				</tr>
				<tr>
					<td class="label">是否点评：</td>
					<td class="input"><select id="is_dianping" disabled><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL") %></select></td>
					<td class="label">是否推荐：</td>
					<td class="input"><select id="is_tuijian" disabled><%=DictionaryHelper.optionHtml("GLOBAL_TF", "NULL") %></select></td>					
				</tr>
				<tr>
					<td class="label">订单金额：</td>
					<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额"  disabled="disabled" /></td>
					<td class="label">优惠金额：</td>
					<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额"  disabled="disabled" /></td>
				</tr>
				<tr>
					<td class="label">发票抬头：</td>
					<td class="input" colspan="3"><input type="text" name="invoice_title" id="invoice_title" style="width:488px" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo" disabled="disabled"></textarea></td>
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
						<th width="5%"></th>
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
						url : '/uwoa/report/report1/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								var newData = "";
								$.each(data.dataHJ,function(i, item) {
									newData += "<tr>";
									newData += "<td id=\"c\"></td>";
									newData += "<td id=\"l\">合计：订单总数"
										+ item.countOrder
										+ "</td>";
									newData += "<td id=\"c\"></td>";
									newData += "<td id=\"c\">"
										+ item.order_amount
										+ "</td>";
									newData += "<td id=\"c\">"
											+ item.favor_amount
											+ "</td>";
									newData += "<td id=\"c\">"
											+ item.spay_amount
											+ "</td>";
									newData += "<td id=\"l\">"
											+ item.person_count
											+ "</td>";
									newData += "</tr>";
								});
								$.each(data.data,function(i, item) {
													newData += "<tr>";
													newData += "<td id=\"c\">"
															+ (data.pageInfo.pageRow
																	* (data.pageInfo.page - 1)
																	+ i + 1)
															+ "</td>";
													newData += "<td id=\"l\"><a onclick=\"view('"+item.order_no+"')\" href=\"#\">"
															+ item.order_no
															+ "</a></td>";
													if(item.member_id){
														newData += "<td id=\"c\">是</td>";
													}
													else{
														newData += "<td id=\"c\">否</td>";
													}
													newData += "<td id=\"c\">"
															+ item.order_amount
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.favor_amount
															+ "</td>";
													newData += "<td id=\"c\">"
															+ item.spay_amount
															+ "</td>";
													newData += "<td id=\"l\">"
															+ item.person_count
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
		
		//修改按钮
		function view(order_no) {
			$("#edit_div input").val("");
			$("#edit_div select").val("");
			$("#edit_div textarea").val("");
			$('#edit_div #sub_tbody').html("");
			
			var jsonuserinfo = $.toJSON({
				"order_no" : order_no
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
							$("#edit_form input[name='invoice_title']").val(item.invoice_title);
							$("#edit_div input[name=status]").val("02");
							if(item.member_id){
								$("#edit_div #is_huiyuan").val(1);
							}
							else{
								$("#edit_div #is_huiyuan").val(0);
							}
							if(item.food_comment_id){
								$("#edit_div #is_dianping").val(1);
							}
							else{
								$("#edit_div #is_dianping").val(0);
							}
							if(item.food_recommend_id){
								$("#edit_div #is_tuijian").val(1);
							}
							else{
								$("#edit_div #is_tuijian").val(0);
							}

							if(item.open_vip=="1" && item.member_id){
								if(item.discout_type=="1"){
									huiyuanzhekou=parseFloat(item.discount_num);
									huiyuanjiangjia=0;
								}
								else{
									huiyuanzhekou=1
									huiyuanjiangjia=parseFloat(item.discount_num);
								}
								
							}
							var l=0;
							$.each(item.sub.data, function(k, subItemGroup) {
								if(k!=0){
									var memo="";
									if(subItemGroup[0].memo){
										memo="<br>"+subItemGroup[0].memo
									}
									$('#edit_div #sub_tbody').append("<tr class=\"ui-widget-header\"><th colspan=\"7\">加单"+k+"&nbsp;&nbsp;<span onclick=\"printBatch('"+subItemGroup[0].batch_id+"')\"><a href=\"#\">[打印]</a></span>"+memo+"</th></tr>");
								}
								$.each(subItemGroup, function(j, subItem) {
									l=l+1;
									var newData = "";
									newData += "<tr id=\"row_"+l+"\">";
									newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+l+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\" disabled=\"disabled\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
									newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" validate=\"notNull\" inputName=\"子表-价格\" style=\"width:100%\"  disabled=\"disabled\" value=\""+subItem.food_price+"\"/></td>";
									newData += "<td id=\"l\" name=\"d3\">"+subItem.unit_name+"</td>";
									newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"food_count\" id=\"food_count\" validate=\"notNull\" inputName=\"子表-数量\" style=\"width:100%\" onchange=\"compute("+l+")\" value=\""+subItem.food_count+"\" disabled=\"disabled\"/></td>";
									newData += "<td id=\"l\" name=\"d5\"><input type=\"text\" name=\"food_amount\" id=\"food_amount\" validate=\"notNull\" inputName=\"子表-金额\" style=\"width:100%\" onchange=\"sumSub()\" readonly value=\""+subItem.food_amount+"\" disabled=\"disabled\"/></td>";
									newData += "<td id=\"l\" name=\"d6\"><input type=\"text\" name=\"favor_amount\" id=\"favor_amount\" validate=\"notNull\" inputName=\"子表-优惠后金额\" style=\"width:100%\" onchange=\"sumSub()\" value=\""+subItem.favor_amount+"\" disabled=\"disabled\"/></td>";
									newData += "</tr>";
									$('#edit_div #sub_tbody').append(newData);
									$('#edit_div #sub_tbody #row_'+l+" select[name=food_id]").val(subItem.food_id);
								});
							});
							$('#edit_div #maxRow').val(l);
							$("#edit_div #sub_tbody select[name=food_id]").combobox();
							
							sumSub();
						});
					}
				},
				error : function(data) {
					alert("error")
				}
			});
		}
		//编辑确定
		$("#edit_div").dialog(
				{
					autoOpen : false,
					height : 350,
					width : 700,
					modal : true,
					buttons : {
						"取消" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
					}
				});
	</script>
</body>