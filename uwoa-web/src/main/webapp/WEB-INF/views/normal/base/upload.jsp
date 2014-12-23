<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form action="<%=request.getContextPath()%>/demo/upload/uploadFile" method="post" enctype="multipart/form-data">
         <input type="hidden" name="_method" value="post">    
         文件1：<input type="file" name="file">
         <input type="submit" value=" 提 交 "><br>
     </form>
</script>