<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div id="upload_div" title="上传">
		<iframe id="uploadFrame" style="width:100%;height:100%;border:0px"></iframe>
	</div>
<script>
		function uploadDialog() {
			$("#uploadFrame").attr("src","/uwoa/uploadPage")
			$("#upload_div").dialog("open");
		}
	//编辑确定
	$("#upload_div").dialog(
			{
				autoOpen : false,
				height : 200,
				width : 400,
				modal : true,
				buttons : {
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			});
</script>