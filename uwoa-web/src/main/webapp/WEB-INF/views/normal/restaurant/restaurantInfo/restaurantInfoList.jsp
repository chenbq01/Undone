<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<body>	
	<div id="edit_div" title="餐厅介绍">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label">餐厅介绍信息：</td>
					<td class="input" colspan="3"><textarea name="rest_intr" id="rest_intr"></textarea></td>
				</tr>
				<tr>
					<td class="label">餐厅电话：</td>
					<td class="input"><input type="text" name="rest_phone" id="rest_phone" /></td>
					<td class="label">餐厅地址：</td>
					<td class="input"><input type="text" name="rest_address" id="rest_address" /></td>
				</tr>
				<tr>
					<td class="label">图片预览：</td>
					<td class="input" colspan="3">
						<span style="color:#000000;cursor:pointer" onclick="uploadDialog();">
							[上传图片]
						</span>
						<br>
						<span id="picView"></span>
						<input id="rest_pid" name="rest_pid" type="hidden" value=""/>
					</td>
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
				url : '/uwoa/restaurant/restaurantInfo/query',
				data : jsonuserinfo,
				dataType : 'json',
				success : function(data) {
					if (data && data.success == "true") {
						$("#edit_div").dialog("open");
						$.each(data.data, function(i, item) {
							$("#edit_form input[name='id']").val(
									item.id);
							$("#edit_form textarea[name='rest_intr']")
									.val(item.rest_intr);
							$("#edit_form input[name='rest_phone']")
									.val(item.rest_phone);
							$("#edit_form input[name='rest_address']")
									.val(item.rest_address);
							$("#edit_form input[name='rest_pid']").val(item.rest_pid);
							$("#edit_form #picView").html("<img width=\"156\" height=\"122\" src=\""+item.rest_pid+"\" />");
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
									url : '/uwoa/restaurant/restaurantInfo/edit',
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
							window.location="/uwoa/restaurant/restaurantInfo";
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
		function uploadOk(fileName){
			$("#upload_div").dialog("close");
			$("#picView").html("<img src=\"/uwoa/resources/normal/upload/<%=SecurityHelper.getRestId() %>/"+fileName+"\" />");
			$("#edit_div #rest_pid").val("/uwoa/resources/normal/upload/<%=SecurityHelper.getRestId() %>/"+fileName);
		}
	</script>
</body>