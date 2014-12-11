<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索结果 - 沙发院线</title>
<meta name="keywords" content="沙发院线,电影,院线,看电影,电影票,影片,大片,国产片,国产电影,国产大片,国产影片,好莱坞,好莱坞电影,好莱坞影片,好莱坞大片,发行,数字电视,有线电视,广电,cable,版权,分账,德州有线,重庆有线,吉林有线,成都有线,歌华有线,东方有线,山西有线,陕西有线,深圳有线,广东有线,山东有线,江苏有线,黑龙江有线,天津有线,福建有线,湖南有线,广西有线,贵州有线,云南有线,武汉有线,德州广电,重庆广电,吉林广电,成都广电,北京广电,上海广电,上海文广,山西广电,陕西广电,深圳广电,广东广电,山东广电,江苏广电,黑龙江广电,天津广电,福建广电,湖南广电,广西广电,贵州广电,云南广电,武汉广电,环球,迪士尼,派拉蒙,福克斯,索尼" />
<meta name="description" content="沙发院线 - 让快乐重返客厅" />
<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico" media="screen" />
<link href="../style/basic.css" rel="stylesheet" type="text/css" />
<link href="../style/common.css" rel="stylesheet" type="text/css" />
<link href="../style/index.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../js/tab.js" type="text/javascript"></script>
<script src="../js/jquery.highlight-4.js" type="text/javascript"></script>
<style type="text/css">
.highlight { background-color: yellow }
</style>
</head>
<body>
<!--公共头部 start-->
<div class="header">
  <div class="mainC">
    <div class="logo"><a href="index.html"></a></div>
    <div class="daoH">
      <div class="caiDan"><a href="" onmouseover="showCd()" onmouseout="hiddenCd()">菜单</a></div>
      <ul class="caiDan2" id="caiDan2" onmouseover="showCd()" onmouseout="hiddenCd()">
        <li><a href="zhinan.html">使用指南</a></li>
        <li><a href="kefu.html">客服支持</a></li>
        <li><a href="hezuo.html">业务合作</a></li>
      </ul>
    </div>
    <div class="sousuo">
      <input name="" type="text" class="suosuoK" value="请输入您要查找的内容" onclick="value='';focus()" onblur="if(value=='') value='请输入您要查找的内容';" />
      <input name="" type="button" class="sousuoBtn" title="搜索" />
    </div>
  </div>
</div>
<!--公共头部 end-->
<div class="mainNr">
  <p class="sousuoTt">关于<span id="keywords" class="colorBlue"></span>的搜索结果<span id="counter" class="colorBlue"></span></p>
  <ul class="sousuoJg">
    <#if faqs?size gt 0 >
		<#list faqs as faq>
      				<#if faq?size gt 0 >
						<#list faq as item>
        				  	<li>
								<p class="jieguoTt">${item.question}</p>
								<p class="jieguoNr">${item.answer}</p>
    						</li>
						</#list>
					</#if>
		</#list>
	</#if>
  </ul>
</div>
<!--公共页脚 start-->
<div class="footer">
  <div class="mainC">
    <div class="beiAnqu">中辉华尚（北京）文化传播有限公司　版权所有　<a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备14014536号</a>　<a href="javascript:void(0);">京公网安备11010602004730号</a></div>
    <a href="gywm.html" class="guanYu">关于我们</a><a href="guanzhu.html" class="guanYu">关注沙发影院线</a> </div>
</div>
<!--公共页脚 start-->
<script>
$(function(){
	$(".sousuoBtn").click( function () {
		if($.trim($(".suosuoK").val())==""){
			alert("请输入您要查找的内容");
			$(".suosuoK").val("请输入您要查找的内容");
		} 
		var searchword = encodeURIComponent(encodeURIComponent($(".suosuoK").val()));
		window.location.href="sousuo.html?s="+searchword;
	});

	var request =getrequest();
	var keywords = decodeURIComponent(request["s"]);
	var counter = 0;
	$.each( $("ul.sousuoJg > li"), function(i, n){
		if($(n).children("p.jieguoTt").html().indexOf(keywords) >= 0||$(n).children("p.jieguoNr").html().indexOf(keywords) >= 0){
			$(n).children("p").highlight(keywords);
			counter++;
		}else{
			$(n).hide();
		}
	});
	$("#keywords").html(keywords);
	$("#counter").html("("+counter+")");
	
});

function getrequest() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}
</script>
</body>
</html>
