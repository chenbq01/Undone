<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
	<div id="search_table" class="ui-widget">
		<form method="post" id="search_form" name="search_form">
			<table id="search" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th colspan="5">快速查询</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="label">菜名：</td>
						<td class="input"><input type="text" name="key" /></td>
						<td class="label">推荐度：</td>
						<td class="input"><select type="text" name="recommend" style="width:145px"></select></td>
						<td class="button"><button id="but_submit">查询</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div id="toolbar_table">
		<button id="but_add">新增</button>
		<button id="but_edit">修改</button>
		<button id="but_delete">删除</button>
		<button id="but_view">查看</button>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content">
			<thead>
				<tr class="ui-widget-header ">
					<th width="20"></th>
					<th width="43%">关键字</th>
					<th width="43%">名称</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

	<div id="edit_div" title="">
		<form id="edit_form" name="edit_form">
			<table>
				<tr>
					<td class="label">关键字：</td>
					<td class="input"><input type="text" name="key" id="key" class="text" /></td>
					<td class="label">名称：</td>
					<td class="input"><input type="text" name="name" id="name" class="text" /></td>
				</tr>
			</table>
			<input type="hidden" name="id" id="id" />
		</form>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="">
		<table>
			<tr>
				<td class="label">关键字：</td>
				<td class="input"><input type="text" name="key" id="key" class="text" readonly /></td>
				<td class="label">名称：</td>
				<td class="input"><input type="text" name="name" id="name" class="text" readonly /></td>
			</tr>
		</table>
		<iframe width="100%" height="250"></iframe>
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

		//查询
		function search(page) {
			var o = $('#search_form').serializeObject();
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/bd/food/query',
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
			$("input").val("");
		});

		//修改按钮
		$("#but_edit").button().click(function() {
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
				url : '/uwoa/bd/food/query',
				data : jsonuserinfo,
				dataType : 'json',
				success : function(data) {
					if (data && data.success == "true") {
						$("#edit_div").dialog("open");
						$.each(data.data, function(i, item) {
							$("#edit_form input[name='id']").val(item.id);
							$("#edit_form input[name='key']").val(item.key);
							$("#edit_form input[name='name']").val(item.name);
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
						url : '/uwoa/bd/food/delete',
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
		$("#but_view").button().click(function() {
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
				url : '/uwoa/bd/food/query',
				data : jsonuserinfo,
				dataType : 'json',
				success : function(data) {
					if (data && data.success == "true") {
						$("#detail_div").dialog("open");
						$.each(data.data, function(i, item) {
							$("#detail_div input[name='key']").val(item.key);
							$("#detail_div input[name='name']").val(item.name);
						});
					}
				},
				error : function(data) {
					alert("error")
				}
			});
		});

		//查询
		$("#but_submit").button().click(function() {
			search(1);
		});

		//编辑确定
		$("#edit_div").dialog(
				{
					autoOpen : false,
					height : 300,
					width : 500,
					modal : true,
					buttons : {
						"保存" : function() {
							var bValid = true;
							if (bValid) {
								var jsonuserinfo = $.toJSON($('#edit_form')
										.serializeObject());
								jQuery.ajax({
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/bd/food/edit',
									data : jsonuserinfo,
									dataType : 'json',
									success : function(data) {
										search(1);
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
			height : 400,
			width : 600,
			modal : true,
			buttons : {
				"关闭" : function() {
					$(this).dialog("close");
				}
			},
			"取消" : function() {
			}
		});
	</script>
</body>