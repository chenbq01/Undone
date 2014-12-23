<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.food.food.FoodHelper" %>
<body>	
	<div id="edit_div" title="特色菜">
		<form id="edit_form" name="edit_form">
			<input type="hidden" name="id" id="id" />
		</form>
		<input type="hidden" name="maxRow" id="maxRow" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<%=FoodHelper.checkboxListHtml() %>
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
			$("input[name^='" + name + "']").each(function() {
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
			$("#edit_div input:checkbox").removeAttr("checked");
			
			var o ={ids:"<%=SecurityHelper.getRestId() %>"};
			var jsonuserinfo = $.toJSON(o);
			jQuery.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : '/uwoa/food/foodSpecial/queryWithSub',
				data : jsonuserinfo,
				dataType : 'json',
				success : function(data) {
					if (data && data.success == "true") {
						$("#edit_div").dialog("open");
						$.each(data.data, function(i, item) {
							$('#edit_form input[name=id]').val(item.id);
							$.each(item.sub.data, function(j, subItem) {
								if(subItem.is_special=="1"){
									$('#edit_div input[name=food_id_'+subItem.id+']').prop("checked",true);
								}
							});
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
							var bValid = true;
							var o=$('#edit_form').serializeObject();
							var subo=getChecked("food_id_");
							o["sub"]=subo;
							
							if (bValid) {
								var jsonuserinfo = $.toJSON(o);
								jQuery.ajax({
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/food/foodSpecial/edit',
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