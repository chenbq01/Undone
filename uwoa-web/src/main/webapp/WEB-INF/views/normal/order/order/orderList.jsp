<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.restaurant.table.TableHelper"%>
<%@ page import="cn.com.uwoa.business.food.food.FoodHelper" %>
<%@ page import="cn.com.uwoa.business.food.buffet.BuffetHelper" %>
<body>
	<style>
	    div#checkout_div table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
	 	div#checkout_div table td.label { width:20%; text-align: right;}
	 	div#checkout_div table td.input { width:30%; text-align: left;}
	 	div#checkout_div table input { width:150px;}
	 	div#checkout_div table select { width:154px;}
	 	div#checkout_div table textarea { width:486px;height:60px;}
	 	
	 	div#table_div .table_but {
			width:120px;
			height:120px;
			float:lift;
			margin-left:5px;
			margin-right:5px;
			margin-bottom:5px;
			margin-top:5px;
	 	}
	 	div#table_div table {
	 		width:80px;
	 		height:90px;
	 	}
	</style>
	<div id="table_div">

	</div>
	<iframe id="printFrame" style="width:0;height:0"></iframe>

	<div id="edit_div" title="编辑">
		<form id="edit_form" name="edit_form">
			<table id="main_table">
				<tr>
					<td class="label">订单号：</td>
					<td class="input"><input type="text" name="order_no" id="order_no" readonly/></td>
					<td class="label"><font color="#FF0000">*</font> 桌号：</td>
					<td class="input"><select name="table_id" id="table_id" validate="notNull" inputName="桌号"><%=TableHelper.optionHtml("SELECT") %></select></td>
				</tr>
				<tr>
					<td class="label">自助餐：</td>
					<td class="input"><select name="buffet_id" id="buffet_id" inputName="自助餐" onchange="computeAll()"><%=BuffetHelper.optionHtml("NULL") %></select></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">人数：</td>
					<td class="input"><input type="text" name="person_count" id="person_count" inputName="人数" /></td>
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
					<td class="label"><font color="#FF0000">*</font> 订单金额：</td>
					<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额" readonly /></td>
					<td class="label"><font color="#FF0000">*</font> 优惠金额：</td>
					<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额" readonly /></td>
				</tr>
				<tr>
					<td class="label">发票抬头：</td>
					<td class="input" colspan="3"><input type="text" name="invoice_title" id="invoice_title" style="width:488px"/></td>
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
						<th width="10%">原价</th>
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
				<td class="input"><input type="text" name="order_amount" id="order_amount" validate="notNull" inputName="订单金额" readonly /></td>
				<td class="label">优惠金额：</td>
				<td class="input"><input type="text" name="favor_amount" id="favor_amount" validate="notNull" inputName="优惠金额" readonly /></td>
			</tr>
			<tr>
				<td class="label">发票抬头：</td>
				<td class="input" colspan="3"><input type="text" name="invoice_title" id="invoice_title" style="width:488px"/></td>
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
						<th width="10%">原价</th>
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
						<th width="10%">原价</th>
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
	var huiyuanzhekou = 1;
	var huiyuanjiangjia = 0;
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
			var timer = setInterval( function(){
				search(1);
			} , 60000);
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
						url : '/uwoa/order/order/queryTable',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								var newData = "";
								$("#table_div").html("");
								$
										.each(
												data.data,
												function(i, item) {
													var html="";
													if(item.order_no){
														if(item.status=="01"){
															html="<div id=\"but_edit_"+i+"\" class=\"table_but\" style=\"background:#ffa4a4 url() 50% 50% repeat-x\">"
														}
														else{
															html="<div id=\"but_edit_"+i+"\" class=\"table_but\" style=\"background:#a4ffa4 url() 50% 50% repeat-x\">"
														}
 														html+=item.table_name
														if(item.waie_num){
															html+="("+item.waie_num+")";
														}
														html+="<br><br>订单："+item.order_no
														html+="<br>金额："+item.favor_amount
														html+="</div>";
														$("#table_div").append(html);
														addButEdit("#but_edit_"+i,item.order_no);
													}
													else{
														html="<div id=\"but_add_"+i+"\" class=\"table_but\">"
														html+=item.table_name
														html+="</div>";
														$("#table_div").append(html);
														addButAdd("#but_add_"+i,item.id);
													}
													
												});
							}
						},
						error : function(data) {
							alert("error")
						}
					});
		}

		//新增按钮
		function addButAdd(name,table_id){
			$(name).button().click(function() {
				$("#edit_div").dialog("open");
				$("#edit_div input").val("");
				$("#edit_div select").val("");
				$("#edit_div textarea").val("");
				$("#edit_form input[name='rest_id']").val("<%=SecurityHelper.getRestId() %>");
				$('#sub_tbody').html("");
				$("#edit_div input[name=status]").val("02");
				$("#edit_div select[name=table_id]").val(table_id);
				$("#edit_div #is_huiyuan").val("0");
				$("#edit_div #is_dianping").val("0");
				$("#edit_div #is_tuijian").val("0");
				huiyuanzhekou=1;
				huiyuanjiangjia=0;
			});
		}

		//修改按钮
		function addButEdit(name,order_no){
			$(name).button().click(
					function() {
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
										$("#edit_form textarea[name='memo']").val(item.memo);
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
												var memo="备注：";
												if(subItemGroup[0].memo){
													memo=memo+subItemGroup[0].memo+" "
												}
												$('#edit_div #sub_tbody').append("<tr class=\"ui-widget-header\"><th colspan=\"8\">加单"+k+"&nbsp;&nbsp;<span onclick=\"save();printBatch('"+subItemGroup[0].batch_id+"')\"><a href=\"#\">[打印]</a></span></th></tr><tr><td colspan=\"8\">"+memo+"</td></tr>");
											}
											$.each(subItemGroup, function(j, subItem) {
												l=l+1;
												var newData = "";
												newData += "<tr id=\"row_"+l+"\">";
												newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+l+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
												newData += "<td id=\"l\" name=\"d8\">"+subItem.original_price+"</td>";
												newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" validate=\"notNull\" inputName=\"子表-价格\" style=\"width:100%\" readonly value=\""+subItem.food_price+"\"/></td>";
												newData += "<td id=\"l\" name=\"d3\">"+subItem.unit_name+"</td>";
												newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"food_count\" id=\"food_count\" validate=\"notNull\" inputName=\"子表-数量\" style=\"width:100%\" onchange=\"compute("+l+")\" value=\""+subItem.food_count+"\"/></td>";
												newData += "<td id=\"l\" name=\"d5\"><input type=\"text\" name=\"food_amount\" id=\"food_amount\" validate=\"notNull\" inputName=\"子表-金额\" style=\"width:100%\" onchange=\"sumSub()\" readonly value=\""+subItem.food_amount+"\"/></td>";
												newData += "<td id=\"l\" name=\"d6\"><input type=\"text\" name=\"favor_amount\" id=\"favor_amount\" validate=\"notNull\" inputName=\"子表-优惠后金额\" style=\"width:100%\" onchange=\"sumSub()\" value=\""+subItem.favor_amount+"\"/></td>";
												newData += "<td id=\"c\" name=\"d7\"><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+subItem.id+"\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
												newData += "</tr>";
												$('#edit_div #sub_tbody').append(newData);
												$('#edit_div #sub_tbody #row_'+l+" select[name=food_id]").val(subItem.food_id);
											});
										});
										$('#edit_div #maxRow').val(l);
										$("#edit_div #sub_tbody select[name=food_id]").combobox();
										var html="<tr class=\"ui-widget-header\"><th colspan=\"8\">加单</th></tr><tr class=\"ui-widget-header\"><th>菜名</th><th>原价</th><th>价格</th><th>单位</th><th>数量</th><th>金额</th><th>优惠后金额</th><th></tr></tr>"
										$('#edit_div #sub_tbody').append(html);
										
										sumSub();
									});
								}
							},
							error : function(data) {
								alert("error")
							}
						});
					});
		}

		//删除
		function deleteOrder(id){
			var jsonuserinfo = $.toJSON({
				"ids" : id
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
							$("#edit_div").dialog("close");
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
		}

		//查看
		$("#but_view").button().click(
				function() {
					$("#detail_div input").val("");
					$("#detail_div select").val("");
					$("#detail_div textarea").val("");
					$("#detail_div input").attr("disabled",true);
					$("#detail_div select").attr("disabled",true);
					$("#detail_div textarea").attr("disabled",true);
					
					$('#detail_div #sub_tbody').html("");
					
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
										newData += "<td id=\"l\" name=\"d8\">"+subItem.original_price+"</td>";
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
							save();
							$(this).dialog("close");
						},
						"拆桌" : function() {
							save();
							var table_id=$("#edit_div select[name=table_id]").val();
							$("#edit_div").dialog("open");
							$("#edit_div input").val("");
							$("#edit_div select").val("");
							$("#edit_div textarea").val("");
							$("#edit_form input[name='rest_id']").val("<%=SecurityHelper.getRestId() %>");
							$('#sub_tbody').html("");
							$("#edit_div input[name=status]").val("02");
							$("#edit_div select[name=table_id]").val(table_id);
						},
						"打印" : function() {
							save();
							var id=$("#edit_div input[name='id']").val();
							$("#printFrame").attr("src","/uwoa/order/order/print/"+id);
						},
						"结账" : function() {
							save();
							$(this).dialog("close");
							checkout($("#edit_div input[name=order_no]").val());
						},
						"删除" : function() {
							var id=$("#edit_div input[name='id']").val();
							deleteOrder(id);
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
			newData += "<td id=\"l\" name=\"d8\"></td>";
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
			if(option.getAttribute("is_favor")=="1"){
				$("#edit_div #data_table #row_"+rowid+" td[name=d8]").html(option.getAttribute("price"));
				$("#edit_div #data_table #row_"+rowid+" td[name=d2] input[name=food_price]").val(option.getAttribute("favor_price"));
			}
			else{
				$("#edit_div #data_table #row_"+rowid+" td[name=d8]").html(option.getAttribute("price"));
				$("#edit_div #data_table #row_"+rowid+" td[name=d2] input[name=food_price]").val(option.getAttribute("price"));
			}
			$("#edit_div #data_table #row_"+rowid+" td[name=d3]").html(option.getAttribute("unit"));
			compute(rowid);
		}
		function compute(rowid){
			computeRow(rowid);
			sumSub();
		}
		
		function computeRow(rowid){
			var food_count=parseFloat($("#edit_div #data_table #row_"+rowid+" input[name=food_count]").val());
			var food_price=parseFloat($("#edit_div #data_table #row_"+rowid+" input[name=food_price]").val());
			if(!food_count){
				food_count=0;
			}
			if(!food_price){
				food_price=0;
			}
			$("#edit_div #data_table #row_"+rowid+" input[name=food_amount]").val((food_count*food_price).toFixed(2));
			var option = $("#edit_div #data_table #row_"+rowid+" td[name=d1] select option:selected");
			if(option.attr("is_favor")=="0" && option.attr("is_discount")=="1"){
				$("#edit_div #data_table #row_"+rowid+" input[name=favor_amount]").val((food_count*food_price*huiyuanzhekou).toFixed(2));
			}
			else{
				$("#edit_div #data_table #row_"+rowid+" input[name=favor_amount]").val((food_count*food_price).toFixed(2));
			}
			var buffet_id=$("#edit_div select[name=buffet_id]").val();
			if(buffet_id!=""){
				if(option.attr("buffet").indexOf(buffet_id)>-1){
					$("#edit_div #data_table #row_"+rowid+" input[name=food_amount]").val(0);
					$("#edit_div #data_table #row_"+rowid+" input[name=favor_amount]").val(0);
				}
			}
		}
		
		function computeAll(){
			$.each($("#edit_div #data_table input[name=favor_amount]"),function(i,item){
				computeRow(i+1);
			});
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
			$("#checkout_div input[name=pay_change]").val((pay_amount-favor_amount).toFixed(2));
		}
		
		function sumSub(){
			var rowCount = parseInt($('#maxRow').val());
			var sumFoodAmount = 0;
			var sumFavorAmount = 0;
			var buffetPrice=0;
			if($("#edit_div select[name=buffet_id] option:selected").attr("price")){
				buffetPrice=parseFloat($("#edit_div select[name=buffet_id] option:selected").attr("price"));
			}
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
			sumFoodAmount=sumFoodAmount+buffetPrice;
			sumFavorAmount=sumFavorAmount-huiyuanjiangjia+buffetPrice;
			if(sumFavorAmount<0){
				sumFavorAmount=0;
			}
			$("#edit_form input[name=order_amount]").val(sumFoodAmount.toFixed(2));
			$("#edit_form input[name=favor_amount]").val(sumFavorAmount.toFixed(2));
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
		function checkout(order_no) {
			$("#checkout_div input").val("");
			$("#checkout_div select").val("");
			$("#checkout_div textarea").val("");
			$('#checkout_div #sub_tbody').html("");
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
						$("#checkout_div").dialog("open");
						$.each(data.data, function(i, item) {
							$("#checkout_form input[name='id']").val(item.id);
							$("#checkout_form input[name='order_no']").val(item.order_no);
							$("#checkout_form select[name='table_id']").val(item.table_id);
							$("#checkout_form input[name='person_count']").val(item.person_count);
							$("#checkout_form input[name='order_amount']").val(item.order_amount);
							$("#checkout_form input[name='favor_amount']").val(item.favor_amount);
							$("#checkout_form input[name='invoice_title']").val(item.invoice_title);
							$("#checkout_form textarea[name='memo']").val(item.memo);
							$("#checkout_div input[name=status]").val("03");
							if(item.member_id){
								$("#checkout_div #is_huiyuan").val(1);
							}
							else{
								$("#checkout_div #is_huiyuan").val(0);
							}
							if(item.food_comment_id){
								$("#checkout_div #is_dianping").val(1);
							}
							else{
								$("#checkout_div #is_dianping").val(0);
							}
							if(item.food_recommend_id){
								$("#checkout_div #is_tuijian").val(1);
							}
							else{
								$("#checkout_div #is_tuijian").val(0);
							}
							var l=0;
							$.each(item.sub.data, function(j, subItemGroup) {
								$.each(subItemGroup, function(j, subItem) {
									var newData = "";
									l++;
									newData += "<tr id=\"row_"+l+"\">";
									newData += "<td id=\"l\">"+subItem.food_name+"</td>";
									newData += "<td id=\"l\">"+subItem.original_price+"</td>";
									newData += "<td id=\"l\">"+subItem.food_price+"</td>";
									newData += "<td id=\"l\">"+subItem.unit_name+"</td>";
									newData += "<td id=\"l\">"+subItem.food_count+"</td>";
									newData += "<td id=\"l\">"+subItem.food_amount+"</td>";
									newData += "<td id=\"l\">"+subItem.favor_amount+"</td>";
									newData += "</tr>";
									$('#checkout_div #sub_tbody').append(newData);
									$('#checkout #sub_tbody #row_'+l+" select[name=food_id]").val(subItem.food_id);
								});
							});
						});
		
					}
				},
				error : function(data) {
					alert("error")
				}
			});
		}
		
		//结账确定
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
									url : '/uwoa/order/order/checkout',
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
		function printBatch(batchId){
			$("#printFrame").attr("src","/uwoa/order/order/printBatch/"+batchId);
		}
		function save(){
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
					async:false,
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
			}
		}
	</script>
</body>