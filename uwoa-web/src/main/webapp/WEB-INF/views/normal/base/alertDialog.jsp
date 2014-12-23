<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-message" title="　"></div>
<script>
	function alertDialog(msg) {
		$("#dialog-message").html(msg);
		$("#dialog-message").dialog({
			modal : true,
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
				}
			}
		});
	}
	
	function zhedang(msg) {
		$("#dialog-message").html(msg);
		$("#dialog-message").dialog({
			modal : true,
			
		});
	}
</script>