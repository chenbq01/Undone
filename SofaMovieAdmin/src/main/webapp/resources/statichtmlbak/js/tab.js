function showCd(){
	document.getElementById("caiDan2").style.display='block';
}
function hiddenCd(){
	document.getElementById("caiDan2").style.display='none';
}
function setTab(name,cursel,n){
 for(var i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
function xiazai1() {
	document.getElementById("xiazaiAndroid").style.display='block';
	document.getElementById("xiazaiIos").style.display='none';
	document.getElementById("wenziBtn1").className='thisOver';
	document.getElementById("wenziBtn2").className='';
}
function xiazai2() {
	document.getElementById("xiazaiAndroid").style.display='none';
	document.getElementById("xiazaiIos").style.display='block';
	document.getElementById("wenziBtn2").className='thisOver';
	document.getElementById("wenziBtn1").className='';
}
//城市下拉
function dplaycityList(){
	var val = $('#dvcity').html();	
	val = val.replace(/(^\s*)|(\s*$)/g, "");	
	if ($('#dvcity').css('display')=='none' && val != '')
		$('#dvcity').show();
	else
		$('#dvcity').hide();
	$('#dvJidinghe').hide();
	$('#dvYhk').hide();
	document.getElementById("xuanZ1").className='';
	document.getElementById("xuanZ2").className='';
	document.getElementById("xuanZ3").className='';
	document.getElementById("xuanZ4").className='';
	
	if ($('#dvcity').css('display')=='none' && val != '')
	document.getElementById("xuanZ1").className='';
	else
	document.getElementById("xuanZ1").className='thisOver';
};
//机顶盒下拉
function dplayjidingheList(){
	var val = $('#dvJidinghe').html();
	if ($('#dvJidinghe').css('display')=='none' && val != '')
		$('#dvJidinghe').show();
	else
		$('#dvJidinghe').hide();
	$('#dvcity').hide();
	$('#dvYhk').hide();
	document.getElementById("xuanZ1").className='';
	document.getElementById("xuanZ2").className='';
	document.getElementById("xuanZ3").className='';
	document.getElementById("xuanZ4").className='';
	
	if ($('#dvJidinghe').css('display')=='none' && val != '')
	document.getElementById("xuanZ2").className='';
	else
	document.getElementById("xuanZ2").className='thisOver';
};
//用户卡下拉
function dplayYhk(){
	var val = $('#dvYhk').html();
	if ($('#dvYhk').css('display')=='none' && val != ''){
		$('#dvYhk').show();
	}else
		$('#dvYhk').hide();
	$('#dvcity').hide();
	$('#dvJidinghe').hide();
	document.getElementById("xuanZ1").className='';
	document.getElementById("xuanZ2").className='';
	document.getElementById("xuanZ3").className='';
	document.getElementById("xuanZ4").className='';
	
	if ($('#dvYhk').css('display')=='none' && val != '')
	document.getElementById("xuanZ3").className='';
	else
	document.getElementById("xuanZ3").className='thisOver';
};
//城市2下拉
function dplaycityList2(){	

	var val = $('#dvcity2').html();	
	val = val.replace(/(^\s*)|(\s*$)/g, "");	
	if ($('#dvcity2').css('display')=='none' && val != '')
		$('#dvcity2').show();
	else
		$('#dvcity2').hide();
	$('#dvJidinghe').hide();
	$('#dvYhk').hide();
	document.getElementById("xuanZ1").className='';
	document.getElementById("xuanZ2").className='';
	document.getElementById("xuanZ3").className='';
	document.getElementById("xuanZ4").className='';
	
	if ($('#dvcity2').css('display')=='none' && val != '')
	document.getElementById("xuanZ4").className='';
	else
	document.getElementById("xuanZ4").className='thisOver';
};
/*客服支持切换*/
////给Li一个点击事件，给当前点击的li一个class然后去掉同辈li的class
$(function(){
	$(".kefuUl > li").click(function(){
		$(this).siblings().removeClass("thisOver");
		if($(this).attr('class')=='thisOver')
			{
				$(this).removeClass("thisOver");
				
			}
		else $(this).addClass("thisOver");
	})
})
//点播页中提示框
function showTishi1() {
	$('#tishi01').show();
}
function hiddenTishi1() {
	$('#tishi01').hide();
}
function showTishi2() {
	$('#tishi02').show();
}
function hiddenTishi2() {
	$('#tishi02').hide();
}
/*图片弹窗*/
function tanCshow01(){
	$('#daTu01').show();
}
function tanChide01(){
	$('#daTu01').hide();
}
function tanCshow02(){
	$('#daTu02').show();
}
function tanChide02(){
	$('#daTu02').hide();
}
function tanCshow03(){
	$('#daTu03').show();
}
function tanChide03(){
	$('#daTu03').hide();
}
function tanCshow04(){
	$('#daTu01').hide();
	$('#daTu02').hide();
	$('#daTu03').hide();
	$('#daTu04').show();
}
function tanChide04(){
	$('#daTu04').hide();
}
/**/
$(function(){
	$(".fuKuangUl > li > a").click(function(){
		$("#xuanZ1").removeClass("thisOver");
		$("#xuanZ2").removeClass("thisOver");
		$("#xuanZ3").removeClass("thisOver");
		$("#xuanZ4").removeClass("thisOver");
	})
})