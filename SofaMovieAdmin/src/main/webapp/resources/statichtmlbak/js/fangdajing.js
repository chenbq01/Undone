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
function magnifier(){
	this.initialize.apply(this, arguments);
}

magnifier.prototype = {
	initialize : function(){
		var _this = this;
		if($('box')){
			this.oBox = $('box');
			this.oLeft = $$$(this.oBox, 'left')[0];
			this.oMove = $$$(this.oBox, 'move')[0];
			this.oMask = $$$(this.oBox, 'mask')[0];
			this.oLimg = $$(this.oLeft, 'img')[0];
			this.oRight = $$$(this.oBox, 'right')[0];
			this.oRimg = $$(this.oRight, 'img')[0];
			this.oMask.onmouseover = function(){
				_this.mouseover();
			};
			this.oMask.onmousemove = function(e){
				_this.mousemove(e);
			};
			this.oMask.onmouseout = function(){
				_this.mouseout();
			};
		}
	},
	mouseover : function(){
		this.oMove.style.display = 'block';
		this.oRight.style.display = 'block';
	},
	mousemove : function(e){
		this.oEvent = e || event;
		this.left = (this.oEvent.clientX - this.oBox.offsetLeft) - this.oMove.offsetWidth / 2;
		this.top = (this.oEvent.clientY - this.oBox.offsetTop) - this.oMove.offsetHeight / 2;
		this.margin();
		this.oMove.style.left = this.left + 'px';
		this.oMove.style.top = this.top + 'px';
		this.bigImg();
		
	},
	mouseout : function(){
		this.oMove.style.display = 'none';
		this.oRight.style.display = 'none';
	},
	margin : function(){
		this.marightW = this.oMask.offsetWidth - this.oMove.offsetWidth;
		this.marightH = this.oMask.offsetHeight - this.oMove.offsetHeight;
		if(this.left < 3){
			this.left = 3;
		}else if(this.left > this.marightW - 3){
			this.left = this.marightW - 3;
		}
		if(this.top < 3){
			this.top = 3;
		}else if(this.top > this.marightH - 3){
			this.top = this.marightH - 3;
		}
	},
	bigImg : function(){
		this.bigW = this.oMask.offsetWidth - this.oMove.offsetWidth;
		this.bigH = this.oMask.offsetHeight - this.oMove.offsetHeight;
		this.bigX = this.left / (this.bigW);
		this.bigY = this.top / (this.bigH);
		this.oRimg.style.left = -this.bigX * (this.bigW) +'px'
		this.oRimg.style.top = -this.bigY * (this.bigH) +'px'
	}
};