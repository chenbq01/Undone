// JavaScript Document.
function $(id){
	return typeof id === "string" ? document.getElementById(id) : id;
}

//获取tagName
function $$(oParent, elem){
	return (oParent || document).getElementsByTagName(elem);
}

//获取className
function $$$(oParent, sClass){
	var aElem = $$(oParent, '*');
	var aClass = [];
	var i = 0;
	for(i=0;i<aElem.length;i++)if(aElem[i].className == sClass)aClass.push(aElem[i]);
	return aClass;
}

//初始化对象
function magnifierTwo(){
	this.initialize.apply(this, arguments);
}

magnifierTwo.prototype = {
	initialize : function(){
		var _this = this;
		if($('boxTwo')){
			this.oboxTwo = $('boxTwo');
			this.oleftTwo = $$$(this.oboxTwo, 'leftTwo')[0];
			this.omoveTwo = $$$(this.oboxTwo, 'moveTwo')[0];
			this.omaskTwo = $$$(this.oboxTwo, 'maskTwo')[0];
			this.oLimg = $$(this.oleftTwo, 'img')[0];
			this.orightTwo = $$$(this.oboxTwo, 'rightTwo')[0];
			this.oRimg = $$(this.orightTwo, 'img')[0];
			this.omaskTwo.onmouseover = function(){
				_this.mouseover();
			};
			this.omaskTwo.onmousemoveTwo = function(e){
				_this.mousemoveTwo(e);
			};
			this.omaskTwo.onmouseout = function(){
				_this.mouseout();
			};
		}
	},
	mouseover : function(){
		this.omoveTwo.style.display = 'block';
		this.orightTwo.style.display = 'block';
	},
	mousemoveTwo : function(e){
		this.oEvent = e || event;
		this.leftTwo = (this.oEvent.clientX - this.oboxTwo.offsetleftTwo) - this.omoveTwo.offsetWidth / 2;
		this.top = (this.oEvent.clientY - this.oboxTwo.offsetTop) - this.omoveTwo.offsetHeight / 2;
		this.margin();
		this.omoveTwo.style.leftTwo = this.leftTwo + 'px';
		this.omoveTwo.style.top = this.top + 'px';
		this.bigImg();
		
	},
	mouseout : function(){
		this.omoveTwo.style.display = 'none';
		this.orightTwo.style.display = 'none';
	},
	margin : function(){
		this.marightTwoW = this.omaskTwo.offsetWidth - this.omoveTwo.offsetWidth;
		this.marightTwoH = this.omaskTwo.offsetHeight - this.omoveTwo.offsetHeight;
		if(this.leftTwo < 3){
			this.leftTwo = 3;
		}else if(this.leftTwo > this.marightTwoW - 3){
			this.leftTwo = this.marightTwoW - 3;
		}
		if(this.top < 3){
			this.top = 3;
		}else if(this.top > this.marightTwoH - 3){
			this.top = this.marightTwoH - 3;
		}
	},
	bigImg : function(){
		this.bigW = this.omaskTwo.offsetWidth - this.omoveTwo.offsetWidth;
		this.bigH = this.omaskTwo.offsetHeight - this.omoveTwo.offsetHeight;
		this.bigX = this.leftTwo / (this.bigW);
		this.bigY = this.top / (this.bigH);
		this.oRimg.style.leftTwo = -this.bigX * (this.bigW) +'px'
		this.oRimg.style.top = -this.bigY * (this.bigH) +'px'
	}
};