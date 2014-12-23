<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="dialog-confirm" title="　"></div>
<script>
	function confirmDialog(msg, callback) {
		$("#dialog-confirm").html(msg);
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 140,
			modal : true,
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
					callback(true);
				},
				"取消" : function() {
					$(this).dialog("close");
					callback(false);
				}
			}
		});
	}
</script>