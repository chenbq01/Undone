<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
	<div data-role="page" data-fullscreen="true">
		<div data-role="header" data-position="fixed">
			<a
				href="<c:url value="/mobile/${sessionScope._profile['rest_code']}?shadow"/>"
				data-rel="back" data-corners="false" data-icon="back">返回</a>
			<h1>订单号：${fn:substring(sessionScope._orderno,fn:length(sessionScope._orderno)-6,fn:length(sessionScope._orderno))}</h1>
		</div>
		<div data-role="content">
			<%@include
				file="/WEB-INF/views/tablet/business/common/mainplaceholder.jsp"%>
			<form id="listform">
				<c:if
					test="${temporaryorder == null || temporaryorder['orderdetail'].size() == 0}">
					<p style="text-align: center">空</p>
				</c:if>
				<c:if test="${temporaryorder['orderdetail'].size() > 0}">
					<table style="width: 100%;">
						<tr valign="top">
							<td style="width: 50%;">
								<ul data-role="listview" data-inset="true">
									<li data-role="list-divider" data-theme="a">完善基本信息</li>
									<li class="ui-body ui-body-b">
										<table style="width: 100%;">
											<tr>
												<td><select name="position" id="position"
													data-mini="true" data-inline="false">
														<option value="">选择所在位置</option>
														<c:forEach var="table" items="${tables}">
															<option value="${table['id']}">${table['table_name']}（${table['person_count']}人）</option>
														</c:forEach>
												</select></td>
												<td>&nbsp;</td>
												<td><input name="waienum" id="waienum" value=""
													placeholder="等位号" type="text" data-mini="true"
													data-inline="true"></td>
												<td>&nbsp;</td>
												<td><input name="numberofpeople" id="numberofpeople"
													value="" placeholder="人数" type="text" data-mini="true"
													data-inline="true"></td>
											</tr>
										</table>
									</li>
									<li class="ui-body ui-body-b">
										<table style="width: 100%;">
											<tr>
												<td style="width: 48%;" align="right"><select
													name="isinvoice" id="isinvoice" data-mini="true"
													data-inline="true" data-role="slider">
														<option value="0">不开票</option>
														<option value="1" selected>开发票</option>
												</select></td>
												<td style="width: 4%;">&nbsp;</td>
												<td style="width: 48%;"><input name="invoicetitle"
													id="invoicetitle" value="${lastorderinfo['invoice_title']}"
													placeholder="发票抬头" type="text" data-mini="true"
													data-inline="true"></td>
											</tr>
										</table>
									</li>
									<li data-role="fieldcontain">
										<p align="center">
											<textarea cols="40" rows="8" id="remark" name="remark"
												placeholder="备注" data-inline="true">${lastorderinfo['memo']}</textarea>
										</p>
									</li>
									<c:if test="${sessionScope._profile['open_vip'] == '1'}">
										<c:if
											test="${sessionScope._mobileinfo == null || sessionScope._mobileinfo['isvalid'] == 'false'}">
											<li data-role="list-divider" data-theme="e"><span
												name="shuoming">验证手机号立即成为会员</span> <c:if
													test="${sessionScope._profile['member_info'] != null && sessionScope._profile['member_info'] != ''}">
													<p></p>
													${sessionScope._profile['member_info']}
													<p></p>
												</c:if>
												<p>
													<a href="#" onclick="javascript:showMobilePrompt();"><span
														name="gongneng">我要成为会员</span></a>
												</p></li>
										</c:if>
										<c:if test="${sessionScope._mobileinfo['isvalid'] == 'true'}">
											<li data-role="list-divider" data-theme="e"><span
												name="shuoming">您是手机号为[${sessionScope._mobileinfo['mobileno']}]的会员</span>
												<c:if
													test="${sessionScope._profile['member_info'] != null && sessionScope._profile['member_info'] != ''}">
													<p></p>
													${sessionScope._profile['member_info']}
													<p></p>
												</c:if>
												<p>
													<a href="#" onclick="javascript:showMobilePrompt();"><span
														name="gongneng">这不是我的手机号</span></a>
												</p></li>
										</c:if>
									</c:if>
								</ul>
								<table style="width: 100%;">
									<tr>
										<td style="width: 25%;">&nbsp;</td>
										<td style="width: 50%;" align="center"><a id="_submit"
											href="#" data-corners="false" data-role="button"
											data-inline="false" data-mini="true" data-theme="a"
											onclick="javascript:_submit();">提交订单</a></td>
										<td style="width: 25%;">&nbsp;</td>
									</tr>
								</table>
								<div data-role="popup" id="demo" data-theme="a">
									<a href="#" data-rel="back" data-role="button" data-theme="a"
										data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>

									<div id="demo_info" style="overflow: auto;">

										<p style="text-align: center">
											<strong>请填写并验证手机号</strong>
										</p>
										<input name="mobileno1" value="" placeholder="手机号"
											data-theme="a" type="text"> <a href="#"
											data-role="button" data-corners="false" data-theme="b"
											data-mini="true" onclick="javascript:GetMobileRandomCode1();">获取验证码</a>
										<input name="randomno1" value="" placeholder="手机验证码"
											data-theme="a" type="text"> <a href="#"
											data-role="button" data-theme="b" data-icon="check"
											data-inline="true" data-mini="true" data-corners="false"
											onclick="javascript:ToValid1();">确定</a> <a href="index.html"
											data-role="button" data-rel="back" data-inline="true"
											data-mini="true" data-corners="false">取消</a>
									</div>
								</div>
								<div data-role="popup" id="buffetlist" data-theme="a">
									<a href="#" data-rel="back" data-role="button" data-theme="a"
										data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>

									<div id="buffetlist_info" style="overflow: auto;">
										<p style="text-align: center">
											<strong>请选择自助餐</strong>
										</p>
										<ul data-role="listview" style="margin-top: -10px;"
											data-inset="true" data-theme="a">
											<li><a
												href="javascript:changeit('<c:url value="/dishes/confirmbuffet"/>', '', '选择自助餐');">
													<p>不选择自助餐</p>
											</a></li>
											<c:forEach var="buffet" items="${buffetlist}">
												<li><a
													href="javascript:changeit('<c:url value="/dishes/confirmbuffet"/>', '${buffet['id']}', '${buffet['buffet_name']}');">
														<img width="60px" height="60px" src="${buffet['pic']}">
														<h2>${buffet['buffet_name']} ${buffet['price']}</h2>
														<p>${buffet['memo']}</p>
												</a></li>
											</c:forEach>
										</ul>
									</div>
								</div> <input type="hidden" id="withnum_dishid" /> <input
								type="hidden" id="withnum_unit" /> <input type="hidden"
								id="withnum_price" /> <input type="hidden"
								id="withnum_dishname" />
								<div data-role="popup" id="withnum" data-theme="a">
									<a href="#" data-rel="back" data-role="button" data-theme="a"
										data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
									<div id="withnum_info" style="overflow: auto;">
										<p style="text-align: center">
											<strong id="withnum_name"></strong>
										</p>
										<img id="withnum_dishpic" class="img-fullwidth" src="" alt="" />
										<div data-theme="d" data-overlay-theme="b" class="ui-content"
											style="max-width: 340px; padding-bottom: 2em;">
											<p>
												<strong id="withnum_text"></strong>
											</p>
											<fieldset class="ui-grid-b">
												<div class="ui-block-a">
													<a id="_minus" href="#" data-role="button"
														data-iconpos="notext" data-mini="true" data-icon="minus"
														data-theme="a" style="float: right"
														onclick="javascript:minus();">减少</a>
												</div>
												<div class="ui-block-b">
													<input id="_num" name="_num" type="text" data-mini="true"
														data-clear-btn="false" value="">
												</div>
												<div class="ui-block-c">
													<a id="_plus" href="#" data-role="button"
														data-iconpos="notext" data-mini="true" data-icon="plus"
														data-theme="a" onclick="javascript:plus();">增加</a>
												</div>
											</fieldset>
											<p></p>
											<a id="_confirm" href="#" data-role="button" data-theme="a"
												data-icon="check" data-inline="true" data-mini="true"
												data-corners="false"
												onclick="javascript:_confirm('withnum');">确定</a> <a
												href="index.html" data-role="button" data-rel="back"
												data-inline="true" data-mini="true" data-corners="false">取消</a>
										</div>
									</div>
								</div>
								<div data-role="popup" id="withoutnum" data-theme="a">
									<a href="#" data-rel="back" data-role="button" data-theme="a"
										data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>
									<div id="withoutnum_info" style="overflow: auto;">
										<p style="text-align: center">
											<strong id="withoutnum_name"></strong>
										</p>
										<img id="withoutnum_dishpic" class="img-fullwidth" src=""
											alt="" />
										<div data-theme="d" data-overlay-theme="b" class="ui-content"
											style="max-width: 340px; padding-bottom: 2em;">
											<p>
												<strong id="withoutnum_text"></strong>
											</p>
											<p></p>
											<a id="_confirm" href="#" data-role="button" data-theme="a"
												data-icon="check" data-inline="true" data-mini="true"
												data-corners="false"
												onclick="javascript:_confirm('withoutnum');">删除</a> <a
												href="index.html" data-role="button" data-rel="back"
												data-inline="true" data-mini="true" data-corners="false">取消</a>
										</div>
									</div>
								</div>
							</td>
							<td style="width: 50%;">
								<p />
								<table class="ui-responsive table-stroke" style="width: 100%;">
									<thead>
										<tr>
											<th data-priority="1" width="10%">编号</th>
											<th data-priority="persist" width="30%">名称</th>
											<th data-priority="2" width="30%">单价</th>
											<th data-priority="3" width="10%">数量</th>
											<th data-priority="4" width="20%">操作</th>
										</tr>
									</thead>
									<tbody id="orderlist">
										<c:set var="orderamount" value="0" />
										<c:forEach var="dish" items="${temporaryorder['orderdetail']}"
											varStatus="xh">
											<tr>
												<th align="center">${xh.count}</th>
												<td align="center">${dish['dishname']}</td>
												<td align="center">${dish['price']}元/${dish['unit']}</td>
												<td align="center">${dish['num']=="0"?"?":dish['num']}</td>
												<td align="center"><a
													href="javascript:getOrderDishData('${dish['dishid']}');">修改</a></td>
												<c:set var="orderamount"
													value="${orderamount+dish['price']*dish['num']}" />
											</tr>
										</c:forEach>
										<tr>
											<td colspan="5" align="right"><strong>合计：</strong>${orderamount}&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</tbody>
								</table> <c:if test="${buffetlist.size()>0}">
									<table style="width: 100%;">
										<tr>
											<td style="width: 25%;">&nbsp;</td>
											<td style="width: 50%;">&nbsp;</td>
											<td style="width: 25%;"><a id="selbuffet" href="#"
												onclick="javascript:showBuffetList();" data-role="button"
												data-theme="a" data-mini="true">选择自助餐</a></td>
										</tr>
									</table>
								</c:if>
							</td>

						</tr>
					</table>




					<script type="text/javascript">
						function minus() {
							var _numInput = $("#_num");
							var _numInt = parseInt(_numInput.val());
							if (_numInt > 0) {
								_numInput.val(_numInt - 1);
							}
						}
						function plus() {
							var _numInput = $("#_num");
							var _numInt = parseInt(_numInput.val());
							_numInput.val(_numInt + 1);
						}
						function _confirm(id) {
							jQuery
									.ajax({
										type : 'GET',
										//contentType : 'application/json',
										url : '<c:url value="/mobileorder/temporary/${sessionScope._orderno}/modify"/>',
										data : {
											"dishid" : $("#withnum_dishid").val(),
											"dishname" : encodeURI($("#withnum_dishname").val()),
											"num" : $("#_num").val(),
											"unit" : $("#withnum_unit").val(),
											"price" : $("#withnum_price").val()
										},
										dataType : 'json',
										success : function(data) {
											refreshList(data);
											$("#"+id).popup("close");
										},
										error : function(data) {
											alert("修改失败！");
										}
									});
						}
						function getOrderDishData(dishid) {
							jQuery
							.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : '<c:url value="/dishes/orderdishdata"/>',
								data : {
									"dishesid" : dishid
								},
								dataType : 'json',
								success : function(data) {
									var dishinfo = data.dishinfo;
									if(!data.num){//如果是需要称重的菜
										$("#withnum_dishid").val(dishinfo.id);
										$("#withnum_unit").val(dishinfo.unit);
										$("#withnum_price").val(dishinfo.price);
										$("#_num").val("0");
										$("#withnum_dishname").val(dishinfo.food_name);
										$("#withoutnum_name").html(dishinfo.food_name);
										$("#withoutnum_dishpic").attr("src",dishinfo.pic);
										$("#withoutnum_dishpic").attr("alt",dishinfo.food_name);
										$("#withoutnum_text").html(dishinfo.price+"元/"+dishinfo.unit+"&nbsp;&nbsp;推荐次数"+dishinfo.vote_num);
										$("#withoutnum").popup("open");
									} else{
										$("#withnum_dishid").val(dishinfo.id);
										$("#withnum_unit").val(dishinfo.unit);
										$("#withnum_price").val(dishinfo.price);
										$("#withnum_num").val(data.num);
										$("#withnum_dishname").val(dishinfo.food_name);
										$("#withnum_name").html(dishinfo.food_name);
										$("#withnum_dishpic").attr("src",dishinfo.pic);
										$("#withnum_dishpic").attr("alt",dishinfo.food_name);
										$("#_num").val(data.num);
										$("#withnum_text").html(dishinfo.price+"元/"+dishinfo.unit+"&nbsp;&nbsp;推荐次数"+dishinfo.vote_num);
										$("#withnum").popup("open");
									}
								},
								error : function(data) {
									alert("error");
								}
							});
						}
						function changeit(url, buffetid, buffetname) {
							jQuery.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : url,
								data : {
									"buffetid" : buffetid,
									"orderno" : '${sessionScope._orderno}'
								},
								dataType : 'json',
								success : function(data) {
									refreshList(data);
									$("#selbuffet :first-child :first-child").html(buffetname);
									$("#buffetlist").popup("close");
								},
								error : function(data) {
									alert("操作失败！");
								}
							});
						}
						function refreshList(data){
							var content_html = "";
							if (data.length == 0) {
								$("#listform").html("<p style=\"text-align: center\">空</p>");
							} else {
								var orderamount = 0.00;
								$
								.each(
										data,
										function(i, item) {
											content_html += "<tr><th align=\"center\">";
											content_html += (i+1);
											content_html += "</th><td align=\"center\">";
											content_html += item.dishname;
											content_html += "</td><td align=\"center\">";
											content_html += formatNumber(item.price,2,0);
											content_html += "元/";
											content_html += item.unit;
											content_html += "</td><td align=\"center\">";
											content_html += (item.num=="0"?"?":item.num);
											content_html += "</td><td align=\"center\"><a href=\"javascript:getOrderDishData('"+item.dishid+"');\"";
											content_html += " class=\"ui-link\">修改</a></td></tr>";
											orderamount += parseFloat(item.price)*parseFloat(item.num)
										});
								content_html += "<tr><td colspan=\"5\" align=\"right\"><strong>合计：</strong>";
								content_html += formatNumber(orderamount,2,0);;
								content_html += "&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";		
								$("#orderlist").html(content_html);
							}
						}
						$("#isinvoice").change(
								function() {
									if ($("#isinvoice").val() == '1') {
										$("#invoicetitle").removeAttr(
												"readonly");
									}
									if ($("#isinvoice").val() == '0') {
										$("#invoicetitle").val("");
										$("#invoicetitle").attr("readonly",
												"readonly");
									}
								});
						function _submit() {
							var position = $("#position");
							var waienum = $("#waienum");
							var remark = $("#remark");
							var numberofpeople = $("#numberofpeople");
							var isinvoice = $("#isinvoice");
							var invoicetitle = $("#invoicetitle");
							var value_position = $.trim(position.val());
							var value_waienum = $.trim(waienum.val());
							var value_remark = $.trim(remark.val());
							var value_numberofpeople = $.trim(numberofpeople
									.val());
							var value_isinvoice = $.trim(isinvoice.val());
							var value_invoicetitle = $.trim(invoicetitle.val());
							if (value_position.length == 0&&value_waienum.length == 0) {
								alert("请选择所在位置或输入等位号！");
								return;
							}
							if (value_position.length != 0&&value_waienum.length != 0) {
								alert("请选择所在位置或输入等位号！");
								return;
							}
							var zzs = /^\+?[1-9][0-9]*$/;
							if (!zzs.test(value_numberofpeople)) {
								alert("请输入正确的人数！");
								numberofpeople.focus();
								return;
							}
							if (value_isinvoice == '1'
									&& value_invoicetitle.length == 0) {
								alert("请输入发票抬头！");
								invoicetitle.focus();
								return;
							}

							jQuery
									.ajax({
										type : 'GET',
										//contentType : 'application/json',
										url : '<c:url value="/mobileorder/save"/>',
										data : {
											"orderno" : '${sessionScope._orderno}',
											"position" : value_position,
											"waienum" : value_waienum,
											"numberofpeople" : value_numberofpeople,
											"remark" : encodeURI(value_remark),
											"isinvoice" : value_isinvoice,
											"invoicetitle" : encodeURI(value_invoicetitle)
										},
										dataType : 'text',
										success : function(data) {
											if (data == 'true') {
												alert("订单提交成功！\n${sessionScope._profile['reviews_notice']}");
											} else if(data == 'false'){
												alert("订单提交失败！");
											} else {
												alert("有人已经提交订单，如需添加菜品请选择加菜功能！");
											}
											$.mobile
													.navigate("<c:url value="/mobile/${sessionScope._profile['rest_code']}?shadow"/>");
										},
										error : function(data) {
											alert("暂时无法提交订单！");
										}
									});

						}
						function showMobilePrompt() {
							$("#demo").popup("open");
						}
						function showBuffetList() {
							$("#buffetlist").popup("open");
						}
						function GetMobileRandomCode1() {
							var mobileno = $("input[name='mobileno1']");
							var value = mobileno.val();
							if ($.trim(value).length == 0) {
								mobileno.focus();
								mobileno.attr("placeholder", "请先输入手机号");
								return;
							}
							var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
							if (!mobile.test(value)) {
								mobileno.focus();
								mobileno.attr("placeholder", "请输入正确的手机号");
								mobileno.val("");
								return;
							}
							jQuery.ajax({
								type : 'GET',
								//contentType : 'application/json',
								url : '<c:url value="/mobileorder/randomcode"/>',
								data : {
									"mobileno" : value
								},
								dataType : 'text',
								success : function(data) {
									alert("手机验证码已经发送，请查收！");
								},
								error : function(data) {
									alert("无法获取手机验证码！");
								}
							});
						}
						function ToValid1() {
							var mobileno = $("input[name='mobileno1']");
							var randomno = $("input[name='randomno1']");
							var value = mobileno.val();
							var randomnovalue = randomno.val();
							if ($.trim(value).length == 0) {
								mobileno.focus();
								mobileno.attr("placeholder", "请先输入手机号");
								return;
							}
							if ($.trim(randomnovalue).length == 0) {
								randomno.focus();
								randomno.attr("placeholder", "请先输入手机验证码");
								return;
							}
							var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;
							if (!mobile.test(value)) {
								mobileno.focus();
								mobileno.attr("placeholder", "请输入正确的手机号");
								mobileno.val("");
								return;
							}
							jQuery
									.ajax({
										type : 'GET',
										//contentType : 'application/json',
										url : '<c:url value="/mobileorder/valid/mobilenowithrandomcode"/>',//<c:url value="/dishes/favouritelist"/>
										data : {
											"mobileno" : value,
											"randomno" : randomnovalue
										},
										dataType : 'text',
										success : function(data) {
											mobileno.val("");
											randomno.val("");
											if (data.length == 0) {
												alert("手机号或验证码输入有误！");
											} else {
												//$(".ui-dialog").dialog("close");
												$("span[name='shuoming']").html("您是手机号为["+value+"]的会员");
												$("span[name='gongneng']").html("这不是我的手机号");
												$("#demo").popup("close");
											}
										},
										error : function(data) {
											mobileno.val("");
											randomno.val("");
											alert("无法验证手机号及验证码！");
										}
									});
						}
						function formatNumber(num,cent,isThousand){
							num = num.toString().replace(/\$|\,/g,'');
							if(isNaN(num))//检查传入数值为数值类型.
							 num = "0";
							if(isNaN(cent))//确保传入小数位为数值型数值.
							cent = 0;
							cent = parseInt(cent);
							cent = Math.abs(cent);//求出小数位数,确保为正整数.
							if(isNaN(isThousand))//确保传入是否需要千分位为数值类型.
							isThousand = 0;
							isThousand = parseInt(isThousand);
							if(isThousand < 0)
							isThousand = 0;
							if(isThousand >=1) //确保传入的数值只为0或1
							isThousand = 1;
							sign = (num == (num = Math.abs(num)));//获取符号(正/负数)
							//Math.floor:返回小于等于其数值参数的最大整数
							num = Math.floor(num*Math.pow(10,cent)+0.50000000001);//把指定的小数位先转换成整数.多余的小数位四舍五入.
							cents = num%Math.pow(10,cent); //求出小数位数值.
							num = Math.floor(num/Math.pow(10,cent)).toString();//求出整数位数值.
							cents = cents.toString();//把小数位转换成字符串,以便求小数位长度.
							while(cents.length<cent){//补足小数位到指定的位数.
							cents = "0" + cents;
							}
							if(isThousand == 0) //不需要千分位符.
							return (((sign)?'':'-') + num + '.' + cents);
							//对整数部分进行千分位格式化.
							for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
							num = num.substring(0,num.length-(4*i+3))+'’'+
							num.substring(num.length-(4*i+3));
							return (((sign)?'':'-') + num + '.' + cents);
						}
					</script>
				</c:if>
			</form>
		</div>
	</div>
</body>