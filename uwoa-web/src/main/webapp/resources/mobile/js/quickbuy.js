function quickbuy(url, orderno, dishid, dishname, unit, price) {
	jQuery.ajax({
		type : 'GET',
		// contentType : 'application/json',
		url : url,
		data : {
			"orderno" : orderno,
			"dishid" : dishid,
			"dishname" : encodeURI(dishname),
			"unit" : encodeURI(unit),
			"price" : price
		},
		dataType : 'text',
		success : function(data) {
			var c = $("#" + dishid).html();
			if (c != "?") {
				if (data == "1") {
					$("#" + dishid).html(parseInt(c) + 1);
				}
				if (data == "0") {
					$("#" + dishid).html("?");
				}
			}
		},
		error : function(data) {
			alert("添加失败！");
		}
	});
}

function getDishesCommentData(obj) {
	jQuery
			.ajax({
				type : 'GET',
				//contentType : 'application/json',
				url : '/uwoa/dishes/commentdata',
				data : {
					"dishesid" : obj
				},
				dataType : 'json',
				success : function(data) {
					var dishinfo = data.dishinfo;
					$("#food_name").html(dishinfo.food_name);
					/*
					$("#food_img").attr({
						src : dishinfo.pic,
						alt : dishinfo.food_name
					});
					*/
					if(dishinfo.pic){
						$("#food_img").attr("src",dishinfo.pic);
					}else{
						$("#food_img").attr("src","/uwoa/resources/mobile/images/nopic.png");
					}
					$("#food_img").attr("alt",dishinfo.food_name);
					$("#food_price").html(
							"<strong>价格：" + dishinfo.price + "元/"
									+ dishinfo.unit
									+ "&nbsp;&nbsp;推荐次数："
									+ dishinfo.vote_num + "</strong>");
					$("#food_intro").html(dishinfo.food_intro);
					
					var commentlist_html = "<li data-role=\"list-divider\" role=\"heading\" class=\"ui-li ui-li-divider ui-bar-a ui-li-has-count ui-first-child\">食客的评论 <span class=\"ui-li-count ui-btn-up-a ui-btn-corner-all\">"+data.commentlist.length+"</span></li>";
					if (data.commentlist.length == 0) {
						commentlist_html += "<li class=\"ui-li ui-li-static ui-btn-up-d ui-last-child\"><p style=\"text-align: center\" class=\"ui-li-desc\">暂无评论</p></li>";
						
					} else {
						$
								.each(
										data.commentlist,
										function(i, item) {
											commentlist_html += "<li class=\"ui-li ui-li-static ui-btn-up-d";
											if(i==(data.commentlist.length-1)){
												commentlist_html += " ui-last-child";
											}
											commentlist_html += "\"><p class=\"ui-li-aside ui-li-desc\"><strong>";
											commentlist_html += item.comm_time;
											commentlist_html += "</strong></p><p class=\"ui-li-desc\"><strong>";
											commentlist_html += item.comm_content;
											commentlist_html += "</strong></p></li>";
										});
					}
					$("#commentlist").html(commentlist_html);
					$("#demo").popup("open");

				},
				error : function(data) {
					alert("error");
				}
			});
}