<#escape x as x!"">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>使用指南 - 沙发院线</title>
<meta name="keywords" content="沙发院线,电影,院线,看电影,电影票,影片,大片,国产片,国产电影,国产大片,国产影片,好莱坞,好莱坞电影,好莱坞影片,好莱坞大片,发行,数字电视,有线电视,广电,cable,版权,分账,德州有线,重庆有线,吉林有线,成都有线,歌华有线,东方有线,山西有线,陕西有线,深圳有线,广东有线,山东有线,江苏有线,黑龙江有线,天津有线,福建有线,湖南有线,广西有线,贵州有线,云南有线,武汉有线,德州广电,重庆广电,吉林广电,成都广电,北京广电,上海广电,上海文广,山西广电,陕西广电,深圳广电,广东广电,山东广电,江苏广电,黑龙江广电,天津广电,福建广电,湖南广电,广西广电,贵州广电,云南广电,武汉广电,环球,迪士尼,派拉蒙,福克斯,索尼" />
<meta name="description" content="沙发院线 - 让快乐重返客厅" />
<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico" media="screen" />
<link href="../style/basic.css" rel="stylesheet" type="text/css" />
<link href="../style/common.css" rel="stylesheet" type="text/css" />
<link href="../style/zhinan.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../js/tab.js" type="text/javascript"></script>
<script type="text/javascript">
function show(w,h){
	var isIE = (document.all) ? true : false;
	var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
	var newbox=document.createElement("div");
	newbox.style.width=w+"px";
	newbox.style.height=h+"px";
	newbox.style.border="1px solid #000";
	newbox.style.background="#fff"
	newbox.style.zIndex="9999";
	newbox.id = 'newnewbox';
	document.body.appendChild(newbox);   
	newbox.style.position = !isIE6 ? "fixed" : "absolute";
	newbox.style.top =newbox.style.left = "50%";
	newbox.style.marginTop = - newbox.offsetHeight / 2 + "px";
	newbox.style.marginLeft = - newbox.offsetWidth / 2 + "px";
	if(isIE6){
		newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
		newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
		window.attachEvent("onscroll",function(){
			newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
			newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
		})
	}
};
function showid(idname){
	var isIE = (document.all) ? true : false;
	var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
	var newbox=document.getElementById(idname);
	newbox.style.zIndex="9999";
	newbox.style.display="block"
	newbox.style.position = !isIE6 ? "fixed" : "absolute";
	newbox.style.top =newbox.style.left = "50%";
	newbox.style.marginTop = - newbox.offsetHeight / 2 + "px";
	newbox.style.marginLeft = - newbox.offsetWidth / 2 + "px";
	var layer=document.createElement("div");
	layer.id="layer";
	layer.className = "layer";
	layer.style.width=layer.style.height="100%";
	layer.style.position= !isIE6 ? "fixed" : "absolute";
	layer.style.top=layer.style.left=0;
	layer.style.backgroundColor="#000";
	layer.style.zIndex="9998";
	layer.style.opacity="0.6";
	document.body.appendChild(layer);
	function layer_iestyle(){  
		layer.style.width=Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth) + "px";
		layer.style.height= Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight) + "px";
	}
	function newbox_iestyle(){
		newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
		newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
	}
	if(isIE){layer.style.filter ="alpha(opacity=60)";}
	if(isIE6){
		layer_iestyle()
		newbox_iestyle();
		window.attachEvent("onscroll",function(){                            
			newbox_iestyle();
		})
		window.attachEvent("onresize",layer_iestyle)           
	}   
	layer.onclick=function(){
		newbox.style.display="none";
		this.style.display="none";
	}
};
</script>
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
  <div class="zhinan_l">
    <p class="zhinanTt">使用指南</p>
    <!-- 左侧大导航 开始-->
    <ul class="bank_nav3">
      <li class="hover" onclick="setTab('one',1,3)" id="one1">
        <p><a href="javascript:void(0)" onclick="setTab('one',1,3)"><img src="../images/textXiazai.png" width="48" height="72" alt="" /></a></p>
      </li>
      <li onclick="setTab('one',2,3)" id="one2">
        <p><a href="javascript:void(0)" onclick="setTab('one',2,3)"><img src="../images/textZhuce.png" width="47" height="71" alt="" /></a></p>
      </li>
      <li onclick="setTab('one',3,3)" id="one3">
        <p><a href="javascript:void(0)" onclick="setTab('one',3,3)"><img src="../images/textDianbo.png" width="48" height="72" alt="" /></a></p>
      </li>
    </ul>
    <!-- 左侧大导航 结束-->
  </div>
  <div class="zhinan_r">
    <!--1.下载区域 开始-->
    <div id="con_one_1">
      <p class="wenziDh"><a href="javascript:void(0)" class="thisOver" id="wenziBtn1" onclick="xiazai1()">Android</a> | <a href="javascript:void(0)" id="wenziBtn2" onclick="xiazai2()">iOS</a></p>
      <!--下面为 Android 区-->
      <div class="xiazaiQu" id="xiazaiAndroid">
        <div class="maQu"><img src="../images/android.png" width="176" height="176" alt="" /></div>
        <div class="tishiQu">
          <p><strong>友情提示：</strong><br />
            可使用微信扫一扫或<br />
            其他扫码工具下载本客户端</p>
        </div>
        <div class="xiazaiBtn"> <a href="http://www.sofamovie.cn/mobile/shafayuanxian.apk"><img src="../images/androidXiazai.jpg" width="179" height="41" alt="" title="下载到本机" /></a></div>
        <div class="tishi2"></div>
        <div class="tishi3">Android支持列表：小米 | 三星 | 中兴 | 华为 | 酷派 | 联想 | HTC | LG | SONY等</div>
      </div>
      <!--下面为 Ios 区-->
      <div class="xiazaiQu" id="xiazaiIos" style="display:none;">
        <div class="maQu"><img src="../images/2weima.jpg" width="176" height="176" alt="" /></div>
        <div class="tishiQu">
          <p><strong>友情提示：</strong><br />
            可使用微信扫一扫或<br />
            其他扫码工具下载本客户端</p>
        </div>
        <div class="xiazaiBtn"> <a href="javascript:void(0)"><img src="../images/iosXiazai.jpg" width="179" height="41" alt="" title="App Store下载" /></a></div>
        <div class="tishi2">
          <p>请将您的设备连接至电脑<br />
            并确认已安装iTunes</p>
        </div>
        <div class="tishi3">iOS支持列表：iPhone4/4S/5/5S  |  iPod touch</div>
      </div>
    </div>
    <!--1.下载区域 结束-->
    <!--2.注册区域 开始-->
    <div id="con_one_2" style="display:none;">
      <p class="dianhua" id="haoMa"></p>
      <div class="bootQu"><img src="../images/boot1.jpg" width="204" height="178" /><span></span><img src="../images/boot2.jpg" width="204" height="178" /><span></span><img src="../images/boot3.jpg" width="204" height="178" /></div>
      <div class="zhinanTt2">用户卡在哪里?</div>
      <div class="xuanzeQu">
        <div id="xuanZ1">
          <p class="xuanP">请选择您的城市</p>
          <a href="javascript:void(0)" class="xialaK" onclick="dplaycityList();" id="myCity">请选择您的城市</a>
          <input type="hidden" id="hcity" name="hcity" value=""/>
        </div>
        <div class="fuKuang weizhi1" id="dvcity" style="display:none">
          <ul class="fuKuangUl">
			<#list regions as region>
			<li><a href="javascript:sel('${region.id}','${region.regionname}','${region.supportphone}');">${region.regionname}</a></li>
			</#list>
          </ul>
        </div>
        <div id="xuanZ2">
          <p class="xuanP">请选择您的机顶盒</p>
          <a href="javascript:void(0)" class="xialaK" onclick="dplayjidingheList();"  id="acinema">请选择您的机顶盒</a>
          <input type="hidden" id="hJidinghe" name="hJidinghe" value=""/>
          <ul class="fadajingQu" id="ul_boximages">
          </ul>
        </div>
        <div class="fuKuang weizhi2" id="dvJidinghe" style="display:none">
          <ul class="fuKuangUl" id="ul_box">
          	<#list boxes as box>
			<li name="${box.regionid}"><a href="javascript:selbox('${box.id}','${box.boxname}');">${box.boxname}</a></li>
			</#list>
          </ul>
        </div>
        <div id="xuanZ3">
          <p class="xuanP">请选择您的用户卡</p>
          <a href="javascript:void(0)" class="xialaK" onclick="dplayYhk();" id="ahall" >请选择您的用户卡</a>
          <input type="hidden" id="hYhk" name="hYhk" value=""/>
          <ul class="fadajingQu" id="ul_cardimages">
          </ul>
        </div>
        <div class="fuKuang weizhi3" id="dvYhk" style="display:none">
          <ul class="fuKuangUl" id="ul_card">
          </ul>
        </div>
      </div>
    </div>
    <!--2.注册区域 结束-->
    <!--3.点播区域 开始-->
    <div id="con_one_3" style="display:none;">
      <p class="dianhua" id="haoMa2"></span></p>
      <div class="bootQu"><img src="../images/boot11.jpg" width="204" height="178" /><span></span><img src="../images/boot12.jpg" width="204" height="178" /><span></span><img src="../images/boot13.jpg" width="204" height="178" /></div>
      <div class="zhinanTt2">哪个频道收看点播?<span>*完成手机点播后，您可在“已成功”订单中查看实际播出频道</span></div>
      <div class="xuanzeQu">
        <div id="xuanZ4">
          <p class="xuanP">请选择您的城市</p>
          <a href="javascript:void(0)" class="xialaK" onclick="dplaycityList2();" id="myCity2">请选择您的城市</a>
          <input type="hidden" id="hcity2" name="hcity2" value=""/>
        </div>
        <div class="fuKuang weizhi1" id="dvcity2" style="display:none">
          <ul class="fuKuangUl">
            <#list regions as region>
			<li><a href="javascript:sel2('${region.id}','${region.regionname}','${region.supportphone}','${region.demandchannels}','${region.guidechannels}');">${region.regionname}</a></li>
			</#list>
          </ul>
        </div>
        <div id="xuanZ5">
          <p class="xuanP" onmouseover="showTishi1()" onmouseout="hiddenTishi1()">导视频道号</p>
          <span id="tishi01" onmouseover="showTishi1()" onmouseout="hiddenTishi1()">精彩推荐24小时开放播出</span>
          <ul id="dianboPd_ds" class="dianboPd">
          </ul>
        </div>
        <div id="xuanZ6">
			<p class="xuanP" onmouseover="showTishi2()" onmouseout="hiddenTishi2()">点播频道号</p>
			<span id="tishi02" onmouseover="showTishi2()" onmouseout="hiddenTishi2()">您所点播的影片将在以下频道范围播出,<br />具体频道视您点播的场次而定</span>
			<ul id="dianboPd_db" class="dianboPd">
            </ul>
        </div>
      </div>
    </div>
    <!--3.点播区域 结束-->
  </div>
  <div class="picTanc" id="daTu"><div class="guanbiQu"><a href="javascript:void(0);" title="关闭" onclick="javascript:$('#newbox,.layer,#daTu').hide();"></a></div><div class="datuQu"><img id="daTuImg" src="" /></div></div>
</div>          
          
<!--公共页脚 start-->
<div class="footer">
  <div class="mainC">
    <div class="beiAnqu">中辉华尚（北京）文化传播有限公司　版权所有　<a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备14014536号</a>　<a href="javascript:void(0);">京公网安备11010602004730号</a></div>
    <a href="gywm.html" class="guanYu">关于我们</a><a href="guanzhu.html" class="guanYu">关注沙发影院线</a> </div>
</div>
<!--公共页脚 start-->
<li style="display:none" id="boxes">
			<#list boxes as box>
			<ul name="${box.regionid}"><li><a href="javascript:selbox('${box.id}','${box.boxname}');">${box.boxname}</a></li></ul>
			</#list>
</li>
<li style="display:none" id="cards">
			<#list cards as card>
			<ul name="${card.regionid}"><li><a href="javascript:selcard('${card.id}','${card.cardname}');">${card.cardname}</a></li></ul>
			</#list>
</li>
<li style="display:none" id="boximages">
			<#list boximages as image>
			<ul name="${image.boxid}"><li><a href="javascript:void(0);" onclick="javascript:showDatu(this);"><img src="${image.imageurl}" /><span>点击查看大图</span></a></li></ul>
			</#list>
</li>
<li style="display:none" id="cardimages">
			<#list cardimages as image>
			<ul name="${image.cardid}"><li><a href="javascript:void(0);" onclick="javascript:showDatu(this);"><img src="${image.imageurl}" /><span>点击查看大图</span></a></li></ul>
			</#list>
</li>
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
eval($("#dvcity > ul >li:first >a").attr("href"));
eval($("#dvcity2 > ul >li:first >a").attr("href"));
eval($("#ul_box >li:first >a").attr("href"));
eval($("#ul_card >li:first >a").attr("href"));
});
function showDatu(obj){
	$("#daTuImg").attr("src",$(obj).children("img").attr("src"));
	//$("#daTu").show();
	showid("daTu");
}

function selcard(cardid,cardname){
$('#dvYhk').hide();
$('#ahall').html(cardname);

	$('#ul_cardimages').html("");
	var li_cardimages_html=""
	$.each( $("#cardimages > ul[name='"+cardid+"']"), function(i, n){
	  li_cardimages_html+= n.innerHTML;
	});
	$('#ul_cardimages').html(li_cardimages_html);
}
function selbox(boxid,boxname){
$('#dvJidinghe').hide();
$('#acinema').html(boxname);

	$('#ul_boximages').html("");
	var li_boximages_html=""
	$.each( $("#boximages > ul[name='"+boxid+"']"), function(i, n){
	  li_boximages_html+= n.innerHTML;
	});
	$('#ul_boximages').html(li_boximages_html);
}
function sel(regionid,regionname,phone){
	$('#dvcity').hide();
	$('#myCity').html(regionname);
	$('#haoMa').html("本地电话支持: "+phone);
	$('#acinema').html("请选择您的机顶盒"); 
	$('#ahall').html("请选择您的用户卡");
	
	$('#ul_box').html("");
	var li_box_html=""
	$.each( $("#boxes > ul[name='"+regionid+"']"), function(i, n){
	  li_box_html+= n.innerHTML;
	});
	$('#ul_box').html(li_box_html);
	
	$('#ul_card').html("");
	var li_card_html=""
	$.each( $("#cards > ul[name='"+regionid+"']"), function(i, n){
	  li_card_html+= n.innerHTML;
	});
	$('#ul_card').html(li_card_html);
	
	eval($("#ul_box >li:first >a").attr("href"));
	eval($("#ul_card >li:first >a").attr("href"));
}
function sel2(regionid,regionname,phone,demandchannels,guidechannels){
	$('#dvcity2').hide();
	$('#myCity2').html(regionname);
	$('#haoMa2').html("本地电话支持: "+phone);
	if(guidechannels.length>0){
		var guide = guidechannels.split(',');
		var html="";
		$.each( guide, function(i, n){
			var ar = n.split('-');
			if(ar.length==2){
			html+="<li><strong>"+ar[0]+"</strong><span>--"+ar[1]+"</span></li>";
			} else {
			html+="<li><strong>"+n+"</strong><span>--"+n+"</span></li>";
			}
		});
		$('#dianboPd_ds').html(html);
	}else{
		$('#dianboPd_ds').html("");
	}
	
	if(demandchannels.length>0){
		var demand = demandchannels.split(',');
		var html="";
		$.each( demand, function(i, n){
			var ar = n.split('-');
			if(ar.length==2){
			html+="<li><strong>"+ar[0]+"</strong><span>--"+ar[1]+"</span></li>";
			} else {
			html+="<li><strong>"+n+"</strong><span>--"+n+"</span></li>";
			}
		});
		$('#dianboPd_db').html(html);
	}else{
		$('#dianboPd_db').html("");
	}
	
}
</script>
</body>
</html>
</#escape>