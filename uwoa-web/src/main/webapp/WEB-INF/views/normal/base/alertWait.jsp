<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dialog-wait" title="　"></div>
<script>
	function alertWait() {
		$("#dialog-wait").html("处理中，请稍候……");
		$("#dialog-wait").dialog({
			modal : true,
			height : 60,
			open : function(){
				$("button").each(function(i){
				       if (this.title=="close"){
				        this.style.display="none";
				       }
				});
			}
		});
	}
	function closeWait(){
		$("#dialog-wait").dialog("close");
	}
</script>