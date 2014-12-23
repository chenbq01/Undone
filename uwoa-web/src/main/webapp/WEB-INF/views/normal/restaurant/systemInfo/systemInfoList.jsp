<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<body>	
	<div id="edit_div" title="系统配置">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label">折扣类型：</td>
					<td class="input"><select name="discout_type" id="discout_type"><%=DictionaryHelper.optionHtml("REST_DISCOUTTYPE", "") %></select></td>
					<td class="label">折扣值：</td>
					<td class="input"><input type="text" name="discount_num" id="discount_num" /></td>
				</tr>
				<tr style="display:none">
					<td class="label">是否包含酒水：</td>
					<td class="input"><select name="include_wine" id="include_wine"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "") %></select></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">会员提示信息：</td>
					<td class="input" colspan="3"><textarea name="member_info" id="member_info"></textarea></td>
				</tr>
				<tr>
					<td class="label">订单提供发票：</td>
					<td class="input"><select name="give_invoice" id="give_invoice"><%=DictionaryHelper.optionHtml("GLOBAL_TF", "") %></select></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label">推荐票来源：</td>
					<td class="input"><select name="vote_source" id="vote_source"><%=DictionaryHelper.optionHtml("REST_VOTESPIRCE", "") %></select></td>
					<td class="label">推荐票数：</td>
					<td class="input"><input type="text" name="vote_count" id="vote_count" /></td>
				</tr>
				<tr>
					<td class="label">推荐、点评提示信息：</td>
					<td class="input" colspan="3"><textarea id="reviews_notice" name="reviews_notice"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="id" id="id" />
		</form>
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
			showEdit();
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
		
		function showEdit() {
			$("#edit_form input").val("");
			$("#edit_form select").val("");
			$("#edit_form textarea").val("");
			var o ={ids:"<%=SecurityHelper.getRestId() %>"};
			var jsonuserinfo = $.toJSON(o);
			jQuery.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : '/uwoa/restaurant/systemInfo/query',
				data : jsonuserinfo,
				dataType : 'json',
				success : function(data) {
					if (data && data.success == "true") {
						$("#edit_div").dialog("open");
						$.each(data.data, function(i, item) {
							
							$("#edit_form input[name='id']").val(
									item.id);
							$("#edit_form select[name='discout_type']")
									.val(item.discout_type);
							$("#edit_form input[name='discount_num']")
									.val(item.discount_num);
							$("#edit_form select[name='include_wine']")
									.val(item.include_wine);
							$("#edit_form textarea[name='member_info']")
									.val(item.member_info);
							$("#edit_form select[name='give_invoice']").val(
									item.give_invoice);
							$("#edit_form select[name='vote_source']").val(
									item.vote_source);
							$("#edit_form input[name='vote_count']").val(
									item.vote_count);
							$("#edit_form textarea[name='reviews_notice']").val(
									item.reviews_notice);
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
						"保存" : function() {
							if(document.getElementById('member_info').value.length>50){
								alert("会员提示信息最多输入50个字符！");
								return false;
							}
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
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/restaurant/systemInfo/edit',
									data : jsonuserinfo,
									dataType : 'json',
									success : function(data) {
										if (data && data.success == "error") {
											alertDialog(data.errorInfo);
										}
										else{
											$("#edit_div").dialog("close");
											showEdit();
											alert("保存成功");
										}
									},
									error : function(data) {
										alert("error")
									}
								});
							}
						},
						"重置" : function() {
							window.location="/uwoa/restaurant/systemInfo";
						}
					},
					open : function(){
						$("button").each(function(i){
						       if (this.title=="close"){
						        this.style.display="none";
						       }
						});
					},
					close : function() {
					}
				});
	</script>
</body>