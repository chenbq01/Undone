<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.uwoa.system.dictionary.DictionaryHelper"%>
<%@ page import="cn.com.uwoa.global.security.SecurityHelper" %>
<%@ page import="cn.com.uwoa.business.food.food.FoodHelper" %>
<%@ page import="cn.com.uwoa.business.food.specialoffer.SpecialOfferHelper" %>
<body>
	<div id="div_search" class="ui-widget">
		<form method="post" id="search_form" name="search_form">
			<table id="search" class="ui-widget ui-widget-content">
				<thead>
					<tr class="ui-widget-header ">
						<th colspan="7">快速查询</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="label">特价活动名称：</td>
						<td class="input"><input type="text" name="acti_name" /></td>
						<td class="label"></td>
						<td class="input"></td>
						<td class="label"></td>
						<td class="input"></td>
						<td class="button"><div id="but_search" class="div_but">查询</div></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<br />
	<div id="toolbar_table">
		<div id="but_add" class="div_but">新增</div>
		<div id="but_edit" class="div_but">修改</div>
		<div id="but_delete" class="div_but">删除</div>
		<div id="but_view" class="div_but">查看</div>
	</div>
	<div id="data_table" class="ui-widget">
		<table class="ui-widget ui-widget-content2">
			<thead>
				<tr class="ui-widget-header ">
					<th width="5%"><input type="checkbox" onclick="$('div#data_table input[name=checkbox]').prop('checked',this.checked)"/></th>
					<th width="5%">序</th>
					<th width="20%">活动名称</th>
					<th width="20%">时间段</th>
					<th width="50%">备注</th>
				</tr>
			</thead>
			<tbody id="data_tbody">
			</tbody>
		</table>
	</div>
	<div id=page_info></div>

	<div id="edit_div" title="编辑">
		<form id="edit_form" name="edit_form">
			<table id="main_table">
				<tr>
					<td class="label"><font color="#FF0000">*</font> 特价活动名称：</td>
					<td class="input"><input type="text" name="acti_name" id="acti_name" validate="notNull" inputName="特价活动名称" /></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 特价开始日期：</td>
					<td class="input"><input type="text" name="begin_date" id="begin_date" validate="notNull" inputName="特价开始日期" readonly/></td>
					<td class="label"><font color="#FF0000">*</font> 特价结束日期：</td>
					<td class="input"><input type="text" name="end_date" id="end_date" validate="notNull" inputName="特价结束日期" readonly/></td>
				</tr>
				<tr>
					<td class="label">特价开始时间：</td>
					<td class="input">
						<select name="begin_time_h" id="begin_time_h" style="width:60px">
							<option value="0">0时</option><option value="1">1时</option><option value="2">2时</option><option value="3">3时</option><option value="4">4时</option><option value="5">5时</option><option value="6">6时</option><option value="7">7时</option><option value="8">8时</option><option value="9">9时</option><option value="10">10时</option><option value="11">11时</option><option value="12">12时</option><option value="13">13时</option><option value="14">14时</option><option value="15">15时</option><option value="16">16时</option><option value="17">17时</option><option value="18">18时</option><option value="19">19时</option><option value="20">20时</option><option value="21">21时</option><option value="22">22时</option><option value="23">23时</option><option value="24">24时</option>
						</select>
						<select name="begin_time_m" id="begin_time_m" style="width:60px">
							<option value="0">00分</option><option value="1">01分</option><option value="2">02分</option><option value="3">03分</option><option value="4">04分</option><option value="5">05分</option><option value="6">06分</option><option value="7">07分</option><option value="8">08分</option><option value="9">09分</option><option value="10">10分</option><option value="11">11分</option><option value="12">12分</option><option value="13">13分</option><option value="14">14分</option><option value="15">15分</option><option value="16">16分</option><option value="17">17分</option><option value="18">18分</option><option value="19">19分</option><option value="20">20分</option><option value="21">21分</option><option value="22">22分</option><option value="23">23分</option><option value="24">24分</option><option value="25">25分</option><option value="26">26分</option><option value="27">27分</option><option value="28">28分</option><option value="29">29分</option><option value="30">30分</option><option value="31">31分</option><option value="32">32分</option><option value="33">33分</option><option value="34">34分</option><option value="35">35分</option><option value="36">36分</option><option value="37">37分</option><option value="38">38分</option><option value="39">39分</option><option value="40">40分</option><option value="41">41分</option><option value="42">42分</option><option value="43">43分</option><option value="44">44分</option><option value="45">45分</option><option value="46">46分</option><option value="47">47分</option><option value="48">48分</option><option value="49">49分</option><option value="50">50分</option><option value="51">51分</option><option value="52">52分</option><option value="53">53分</option><option value="54">54分</option><option value="55">55分</option><option value="56">56分</option><option value="57">57分</option><option value="58">58分</option><option value="59">59分</option>
						</select>
					</td>
					<td class="label">特价开始时间：</td>
					<td class="input">
						<select name="end_time_h" id="end_time_h" style="width:60px">
							<option value="0">0时</option><option value="1">1时</option><option value="2">2时</option><option value="3">3时</option><option value="4">4时</option><option value="5">5时</option><option value="6">6时</option><option value="7">7时</option><option value="8">8时</option><option value="9">9时</option><option value="10">10时</option><option value="11">11时</option><option value="12">12时</option><option value="13">13时</option><option value="14">14时</option><option value="15">15时</option><option value="16">16时</option><option value="17">17时</option><option value="18">18时</option><option value="19">19时</option><option value="20">20时</option><option value="21">21时</option><option value="22">22时</option><option value="23">23时</option><option value="24">24时</option>
						</select>
						<select name="end_time_m" id="end_time_m" style="width:60px">
							<option value="0">00分</option><option value="1">01分</option><option value="2">02分</option><option value="3">03分</option><option value="4">04分</option><option value="5">05分</option><option value="6">06分</option><option value="7">07分</option><option value="8">08分</option><option value="9">09分</option><option value="10">10分</option><option value="11">11分</option><option value="12">12分</option><option value="13">13分</option><option value="14">14分</option><option value="15">15分</option><option value="16">16分</option><option value="17">17分</option><option value="18">18分</option><option value="19">19分</option><option value="20">20分</option><option value="21">21分</option><option value="22">22分</option><option value="23">23分</option><option value="24">24分</option><option value="25">25分</option><option value="26">26分</option><option value="27">27分</option><option value="28">28分</option><option value="29">29分</option><option value="30">30分</option><option value="31">31分</option><option value="32">32分</option><option value="33">33分</option><option value="34">34分</option><option value="35">35分</option><option value="36">36分</option><option value="37">37分</option><option value="38">38分</option><option value="39">39分</option><option value="40">40分</option><option value="41">41分</option><option value="42">42分</option><option value="43">43分</option><option value="44">44分</option><option value="45">45分</option><option value="46">46分</option><option value="47">47分</option><option value="48">48分</option><option value="49">49分</option><option value="50">50分</option><option value="51">51分</option><option value="52">52分</option><option value="53">53分</option><option value="54">54分</option><option value="55">55分</option><option value="56">56分</option><option value="57">57分</option><option value="58">58分</option><option value="59">59分</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label">优惠星期：</td>
					<td class="input" colspan="3">
						<table border="1" style="width:492px">
							<tr>
								<td>
									<input type="checkbox" name="favor_week_1" style="width:20px" />星期一
									<input type="checkbox" name="favor_week_2" style="width:20px" />星期二
									<input type="checkbox" name="favor_week_3" style="width:20px" />星期三
									<input type="checkbox" name="favor_week_4" style="width:20px" />星期四
									<input type="checkbox" name="favor_week_5" style="width:20px" />星期五
									<input type="checkbox" name="favor_week_6" style="width:20px" />星期六
									<input type="checkbox" name="favor_week_7" style="width:20px" />星期日
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="label">优惠日期：</td>
					<td class="input" colspan="3">
						<table border="1" style="width:492px">
							<tr>
								<td>
									<table>
										<tr>
											<td><input type="checkbox" name="favor_day_1" style="width:20px" />1</td>
											<td><input type="checkbox" name="favor_day_2" style="width:20px" />2</td>
											<td><input type="checkbox" name="favor_day_3" style="width:20px" />3</td>
											<td><input type="checkbox" name="favor_day_4" style="width:20px" />4</td>
											<td><input type="checkbox" name="favor_day_5" style="width:20px" />5</td>
											<td><input type="checkbox" name="favor_day_6" style="width:20px" />6</td>
											<td><input type="checkbox" name="favor_day_7" style="width:20px" />7</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_8" style="width:20px" />8</td>
											<td><input type="checkbox" name="favor_day_9" style="width:20px" />9</td>
											<td><input type="checkbox" name="favor_day_10" style="width:20px" />10</td>
											<td><input type="checkbox" name="favor_day_11" style="width:20px" />11</td>
											<td><input type="checkbox" name="favor_day_12" style="width:20px" />12</td>
											<td><input type="checkbox" name="favor_day_13" style="width:20px" />13</td>
											<td><input type="checkbox" name="favor_day_14" style="width:20px" />14</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_15" style="width:20px" />15</td>
											<td><input type="checkbox" name="favor_day_16" style="width:20px" />16</td>
											<td><input type="checkbox" name="favor_day_17" style="width:20px" />17</td>
											<td><input type="checkbox" name="favor_day_18" style="width:20px" />18</td>
											<td><input type="checkbox" name="favor_day_19" style="width:20px" />19</td>
											<td><input type="checkbox" name="favor_day_20" style="width:20px" />20</td>
											<td><input type="checkbox" name="favor_day_21" style="width:20px" />21</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_22" style="width:20px" />22</td>
											<td><input type="checkbox" name="favor_day_23" style="width:20px" />23</td>
											<td><input type="checkbox" name="favor_day_24" style="width:20px" />24</td>
											<td><input type="checkbox" name="favor_day_25" style="width:20px" />25</td>
											<td><input type="checkbox" name="favor_day_26" style="width:20px" />26</td>
											<td><input type="checkbox" name="favor_day_27" style="width:20px" />27</td>
											<td><input type="checkbox" name="favor_day_28" style="width:20px" />28</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_29" style="width:20px" />29</td>
											<td><input type="checkbox" name="favor_day_30" style="width:20px" />30</td>
											<td><input type="checkbox" name="favor_day_31" style="width:20px" />31</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
				</tr>
			</table>
			<input type="hidden" name="rest_id" id="rest_id"/>
			<input type="hidden" name="id" id="id" />
		</form>
		<input type="hidden" name="maxRow" id="maxRow" />
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="25%"><font color="#FF0000">*</font> 菜名</th>
						<th width="25%">价格</th>
						<th width="20%">单位</th>
						<th width="25%"><font color="#FF0000">*</font> 优惠价</th>
						<th width="5%"></th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
		<div id="toolbar_table">
			<div id="but_addRow" style="width:80px;height:25px;">
				<table><tr>
					<td><img src="${pageContext.request.contextPath}/resources/normal/images/but_add.gif"></img></td>
					<td> 增行</td>
				</tr></table>
			</div>
		</div>
		<p class="validateTips"></p>
	</div>

	<div id="detail_div" title="查看明细">
		<table>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 特价活动名称：</td>
					<td class="input"><input type="text" name="acti_name" id="acti_name" validate="notNull" inputName="特价活动名称" /></td>
					<td class="label"></td>
					<td class="input"></td>
				</tr>
				<tr>
					<td class="label"><font color="#FF0000">*</font> 特价开始日期：</td>
					<td class="input"><input type="text" name="begin_date" id="begin_date" validate="notNull" inputName="特价开始日期" readonly/></td>
					<td class="label"><font color="#FF0000">*</font> 特价结束日期：</td>
					<td class="input"><input type="text" name="end_date" id="end_date" validate="notNull" inputName="特价结束日期" readonly/></td>
				</tr>
				<tr>
					<td class="label">特价开始时间：</td>
					<td class="input">
						<select name="begin_time_h" id="begin_time_h" style="width:60px">
							<option value="0">0时</option><option value="1">1时</option><option value="2">2时</option><option value="3">3时</option><option value="4">4时</option><option value="5">5时</option><option value="6">6时</option><option value="7">7时</option><option value="8">8时</option><option value="9">9时</option><option value="10">10时</option><option value="11">11时</option><option value="12">12时</option><option value="13">13时</option><option value="14">14时</option><option value="15">15时</option><option value="16">16时</option><option value="17">17时</option><option value="18">18时</option><option value="19">19时</option><option value="20">20时</option><option value="21">21时</option><option value="22">22时</option><option value="23">23时</option><option value="24">24时</option>
						</select>
						<select name="begin_time_m" id="begin_time_m" style="width:60px">
							<option value="0">00分</option><option value="1">01分</option><option value="2">02分</option><option value="3">03分</option><option value="4">04分</option><option value="5">05分</option><option value="6">06分</option><option value="7">07分</option><option value="8">08分</option><option value="9">09分</option><option value="10">10分</option><option value="11">11分</option><option value="12">12分</option><option value="13">13分</option><option value="14">14分</option><option value="15">15分</option><option value="16">16分</option><option value="17">17分</option><option value="18">18分</option><option value="19">19分</option><option value="20">20分</option><option value="21">21分</option><option value="22">22分</option><option value="23">23分</option><option value="24">24分</option><option value="25">25分</option><option value="26">26分</option><option value="27">27分</option><option value="28">28分</option><option value="29">29分</option><option value="30">30分</option><option value="31">31分</option><option value="32">32分</option><option value="33">33分</option><option value="34">34分</option><option value="35">35分</option><option value="36">36分</option><option value="37">37分</option><option value="38">38分</option><option value="39">39分</option><option value="40">40分</option><option value="41">41分</option><option value="42">42分</option><option value="43">43分</option><option value="44">44分</option><option value="45">45分</option><option value="46">46分</option><option value="47">47分</option><option value="48">48分</option><option value="49">49分</option><option value="50">50分</option><option value="51">51分</option><option value="52">52分</option><option value="53">53分</option><option value="54">54分</option><option value="55">55分</option><option value="56">56分</option><option value="57">57分</option><option value="58">58分</option><option value="59">59分</option>
						</select>
					</td>
					<td class="label">特价开始时间：</td>
					<td class="input">
						<select name="end_time_h" id="end_time_h" style="width:60px">
							<option value="0">0时</option><option value="1">1时</option><option value="2">2时</option><option value="3">3时</option><option value="4">4时</option><option value="5">5时</option><option value="6">6时</option><option value="7">7时</option><option value="8">8时</option><option value="9">9时</option><option value="10">10时</option><option value="11">11时</option><option value="12">12时</option><option value="13">13时</option><option value="14">14时</option><option value="15">15时</option><option value="16">16时</option><option value="17">17时</option><option value="18">18时</option><option value="19">19时</option><option value="20">20时</option><option value="21">21时</option><option value="22">22时</option><option value="23">23时</option><option value="24">24时</option>
						</select>
						<select name="end_time_m" id="end_time_m" style="width:60px">
							<option value="0">00分</option><option value="1">01分</option><option value="2">02分</option><option value="3">03分</option><option value="4">04分</option><option value="5">05分</option><option value="6">06分</option><option value="7">07分</option><option value="8">08分</option><option value="9">09分</option><option value="10">10分</option><option value="11">11分</option><option value="12">12分</option><option value="13">13分</option><option value="14">14分</option><option value="15">15分</option><option value="16">16分</option><option value="17">17分</option><option value="18">18分</option><option value="19">19分</option><option value="20">20分</option><option value="21">21分</option><option value="22">22分</option><option value="23">23分</option><option value="24">24分</option><option value="25">25分</option><option value="26">26分</option><option value="27">27分</option><option value="28">28分</option><option value="29">29分</option><option value="30">30分</option><option value="31">31分</option><option value="32">32分</option><option value="33">33分</option><option value="34">34分</option><option value="35">35分</option><option value="36">36分</option><option value="37">37分</option><option value="38">38分</option><option value="39">39分</option><option value="40">40分</option><option value="41">41分</option><option value="42">42分</option><option value="43">43分</option><option value="44">44分</option><option value="45">45分</option><option value="46">46分</option><option value="47">47分</option><option value="48">48分</option><option value="49">49分</option><option value="50">50分</option><option value="51">51分</option><option value="52">52分</option><option value="53">53分</option><option value="54">54分</option><option value="55">55分</option><option value="56">56分</option><option value="57">57分</option><option value="58">58分</option><option value="59">59分</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label">优惠星期：</td>
					<td class="input" colspan="3">
						<table border="1" style="width:492px">
							<tr>
								<td>
									<input type="checkbox" name="favor_week_1" style="width:20px" />星期一
									<input type="checkbox" name="favor_week_2" style="width:20px" />星期二
									<input type="checkbox" name="favor_week_3" style="width:20px" />星期三
									<input type="checkbox" name="favor_week_4" style="width:20px" />星期四
									<input type="checkbox" name="favor_week_5" style="width:20px" />星期五
									<input type="checkbox" name="favor_week_6" style="width:20px" />星期六
									<input type="checkbox" name="favor_week_7" style="width:20px" />星期日
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="label">优惠日期：</td>
					<td class="input" colspan="3">
						<table border="1" style="width:492px">
							<tr>
								<td>
									<table>
										<tr>
											<td><input type="checkbox" name="favor_day_1" style="width:20px" />1</td>
											<td><input type="checkbox" name="favor_day_2" style="width:20px" />2</td>
											<td><input type="checkbox" name="favor_day_3" style="width:20px" />3</td>
											<td><input type="checkbox" name="favor_day_4" style="width:20px" />4</td>
											<td><input type="checkbox" name="favor_day_5" style="width:20px" />5</td>
											<td><input type="checkbox" name="favor_day_6" style="width:20px" />6</td>
											<td><input type="checkbox" name="favor_day_7" style="width:20px" />7</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_8" style="width:20px" />8</td>
											<td><input type="checkbox" name="favor_day_9" style="width:20px" />9</td>
											<td><input type="checkbox" name="favor_day_10" style="width:20px" />10</td>
											<td><input type="checkbox" name="favor_day_11" style="width:20px" />11</td>
											<td><input type="checkbox" name="favor_day_12" style="width:20px" />12</td>
											<td><input type="checkbox" name="favor_day_13" style="width:20px" />13</td>
											<td><input type="checkbox" name="favor_day_14" style="width:20px" />14</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_15" style="width:20px" />15</td>
											<td><input type="checkbox" name="favor_day_16" style="width:20px" />16</td>
											<td><input type="checkbox" name="favor_day_17" style="width:20px" />17</td>
											<td><input type="checkbox" name="favor_day_18" style="width:20px" />18</td>
											<td><input type="checkbox" name="favor_day_19" style="width:20px" />19</td>
											<td><input type="checkbox" name="favor_day_20" style="width:20px" />20</td>
											<td><input type="checkbox" name="favor_day_21" style="width:20px" />21</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_22" style="width:20px" />22</td>
											<td><input type="checkbox" name="favor_day_23" style="width:20px" />23</td>
											<td><input type="checkbox" name="favor_day_24" style="width:20px" />24</td>
											<td><input type="checkbox" name="favor_day_25" style="width:20px" />25</td>
											<td><input type="checkbox" name="favor_day_26" style="width:20px" />26</td>
											<td><input type="checkbox" name="favor_day_27" style="width:20px" />27</td>
											<td><input type="checkbox" name="favor_day_28" style="width:20px" />28</td>
										</tr>
										<tr>
											<td><input type="checkbox" name="favor_day_29" style="width:20px" />29</td>
											<td><input type="checkbox" name="favor_day_30" style="width:20px" />30</td>
											<td><input type="checkbox" name="favor_day_31" style="width:20px" />31</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="label">备注：</td>
					<td class="input" colspan="3"><textarea name="memo" id="memo"></textarea></td>
				</tr>
		</table>
		<div id="data_table" class="ui-widget">
			<table class="ui-widget ui-widget-content2">
				<thead>
					<tr class="ui-widget-header ">
						<th width="25%"><font color="#FF0000">*</font> 菜名</th>
						<th width="25%">价格</th>
						<th width="20%">单位</th>
						<th width="25%"><font color="#FF0000">*</font> 优惠价</th>
					</tr>
				</thead>
				<tbody id="sub_tbody">
				</tbody>
			</table>
		</div>
	</div>

	<script>
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};

		function getChecked(name) {
			var o = {};
			var values = "";
			count = 0;
			$("input[name='" + name + "']").each(function() {
				if (this.checked) {
					if (values == "") {
						values = this.value || '';
					} else {
						values += "," + this.value || '';
					}
					count += 1;
				}
			});
			o["ids"] = values;
			o["count"] = count;
			return o;
		}

		function getChecked2(name) {
			var o = {};
			$(name).each(function() {
				if (this.checked) {
					o[this.name]=this.name;
				}
			});
			return o;
		}
		
		$(document).ready(function() {
			$("#edit_div #begin_date").datepicker();
			$("#edit_div #end_date").datepicker();
			search(1);
		});
		  $(function() {
			    $( "#combobox" ).combobox();
			    $( "#toggle" ).click(function() {
			      $( "#combobox" ).toggle();
			    });
			  });
		//校验
		function checkValidate(o,divName){
			var vo={};
			for(var p in o){
				var vp={};
				if($("#"+divName+" input[name="+p+"]").attr("validate")){
					vp['name']=p;
					vp['inputName']=$("#"+divName+" input[name="+p+"]").attr("inputName");
					vp['value']=o[p];
					vp['validate']=$("#"+divName+" input[name="+p+"]").attr("validate");
					vo[p]=vp;
				}
				else if($("#"+divName+" select[name="+p+"]").attr("validate")){
					vp['name']=p;
					vp['inputName']=$("#"+divName+" select[name="+p+"]").attr("inputName");
					vp['value']=o[p];
					vp['validate']=$("#"+divName+" select[name="+p+"]").attr("validate");
					vo[p]=vp;
				}
			}
			return vo;
		}
		
		//查询
		function search(page) {
			var o = $('#search_form').serializeObject();
			o["page"] = page;
			var jsonuserinfo = $.toJSON(o);
			jQuery
					.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/food/specialOffer/query',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								var newData = "";
								$
										.each(
												data.data,
												function(i, item) {
													newData += "<tr>";
													newData += "<td id=\"c\"><input name=\"checkbox\" type=\"checkbox\" value=\""+item.id+"\"></td>";
													newData += "<td id=\"c\">"
														+ (data.pageInfo.pageRow
																* (data.pageInfo.page - 1)
																+ i + 1)
														+ "</td>";
													newData += "<td id=\"l\">" + item.acti_name + "</td>";
													newData += "<td id=\"l\">" + item.begin_date + "-" + item.end_date + "</td>";
													newData += "<td id=\"l\">" + item.memo + "</td>";
													newData += "</tr>";
												});
								$('#data_tbody').html(newData);

								$('#page_info').html(data.pageHtml);
							}
						},
						error : function(data) {
							alert("error")
						}
					});
		}

		//新增按钮
		$("#but_add").button().click(function() {
			$("#edit_div").dialog("open");
			$("#edit_div input").val("");
			$("#edit_div select").val("");
			$("#edit_div textarea").val("");
			$("#edit_form input[name='rest_id']").val("<%=SecurityHelper.getRestId() %>");
			$('#sub_tbody').html("");
			$("#edit_div input[name^=favor_]").prop("checked",true);
			$("#edit_div select[name=begin_time_h]").val("0");
			$("#edit_div select[name=begin_time_m]").val("0");
			$("#edit_div select[name=end_time_h]").val("23");
			$("#edit_div select[name=end_time_m]").val("59");
		});

		//修改按钮
		$("#but_edit").button().click(
				function() {
					$("#edit_div input").val("");
					$("#edit_div select").val("");
					$("#edit_div textarea").val("");
					$('#edit_div #sub_tbody').html("");
					$("#edit_div input[name^=favor_]").prop("checked",false);
					
					var o = getChecked("checkbox");
					if (o.count != 1) {
						alertDialog("请选择1条记录进行编辑！");
						return false;
					}
					var jsonuserinfo = $.toJSON({
						"ids" : o.ids
					});
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/food/specialOffer/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#edit_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#edit_form input[name='id']").val(item.id);
									$("#edit_form input[name='acti_name']").val(item.acti_name);
									$("#edit_form input[name='begin_date']").val(item.begin_date);
									$("#edit_form input[name='end_date']").val(item.end_date);
									var begin_time_array = item.begin_time.split(":");
									$("#edit_form select[name='begin_time_h']").val(parseInt(begin_time_array[0]));
									$("#edit_form select[name='begin_time_m']").val(parseInt(begin_time_array[1]));
									var end_time_array = item.end_time.split(":");
									$("#edit_form select[name='end_time_h']").val(parseInt(end_time_array[0]));
									$("#edit_form select[name='end_time_m']").val(parseInt(end_time_array[1]));
									$.each(eval("("+item.favor_week+")"), function(j, favor_week_item) {
										$("#edit_div input[name="+favor_week_item+"]").prop("checked",true);
									});
									$.each(eval("("+item.favor_day+")"), function(j, favor_day_item) {
										$("#edit_div input[name="+favor_day_item+"]").prop("checked",true);
									});
									$("#edit_div textarea[name=memo]").val(item.memo);
									
									var newData = "";
									$.each(item.sub.data, function(j, subItem) {
										var newData = "";
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+(j+1)+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
										newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" validate=\"notNull\" inputName=\"子表-价格\" style=\"width:100%\" readonly value=\""+subItem.food_price+"\"/></td>";
										newData += "<td id=\"l\" name=\"d3\">"+subItem.unit_name+"</td>";
										newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"favor_price\" id=\"favor_price\" validate=\"notNull\" inputName=\"子表-优惠价\" style=\"width:100%\" value=\""+subItem.favor_price+"\"/></td>";
										newData += "<td id=\"c\" name=\"d5\"><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+subItem.id+"\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
										newData += "</tr>";
										$('#edit_div #sub_tbody').append(newData);
										$('#edit_div #sub_tbody #row_'+(j+1)+" select[name=food_id]").val(subItem.food_id);
									});
									$('#edit_div #maxRow').val(item.sub.data.length);
									$("#edit_div #sub_tbody select[name=food_id]").combobox();
								});

							}
						},
						error : function(data) {
							alert("error")
						}
					});
				});

		//删除
		$("#but_delete").button().click(function() {
			var o = getChecked("checkbox");
			if (o.count < 1) {
				alertDialog("请选择至少1条记录进行删除！");
				return false;
			}
			var jsonuserinfo = $.toJSON({
				"ids" : o.ids
			});

			confirmDialog("删除操作不可恢复，您确认要删除吗？", function(ok) {
				if (ok == true) {
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/food/specialOffer/delete',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								search(1);
							}
						},
						error : function(data) {
							alert("error")
						}
					});
				}
			});
		});

		//查看
		$("#but_view").button().click(
				function() {
					$("#detail_div input").val("");
					$("#detail_div select").val("");
					$("#detail_div textarea").val("");
					$("#detail_div input[name^=favor_]").prop("checked",false);
					$('#detail_div #sub_tbody').html("");
					$("#detail_div input").attr("disabled",true);
					$("#detail_div select").attr("disabled",true);
					$("#detail_div textarea").attr("disabled",true);
					
					
					
					var o = getChecked("checkbox");
					if (o.count != 1) {
						alertDialog("请选择1条记录进行查看！");
						return false;
					}
					var jsonuserinfo = $.toJSON({
						"ids" : o.ids
					});
					jQuery.ajax({
						type : 'POST',
						contentType : 'application/json',
						url : '/uwoa/food/specialOffer/queryWithSub',
						data : jsonuserinfo,
						dataType : 'json',
						success : function(data) {
							if (data && data.success == "true") {
								$("#detail_div").dialog("open");
								$.each(data.data, function(i, item) {
									$("#detail_div input[name='id']").val(item.id);
									$("#detail_div input[name='acti_name']").val(item.acti_name);
									$("#detail_div input[name='begin_date']").val(item.begin_date);
									$("#detail_div input[name='end_date']").val(item.end_date);
									var begin_time_array = item.begin_time.split(":");
									$("#detail_div select[name='begin_time_h']").val(parseInt(begin_time_array[0]));
									$("#detail_div select[name='begin_time_m']").val(parseInt(begin_time_array[1]));
									var end_time_array = item.end_time.split(":");
									$("#detail_div select[name='end_time_h']").val(parseInt(end_time_array[0]));
									$("#detail_div select[name='end_time_m']").val(parseInt(end_time_array[1]));
									$.each(eval("("+item.favor_week+")"), function(j, favor_week_item) {
										$("#detail_div input[name="+favor_week_item+"]").prop("checked",true);
									});
									$.each(eval("("+item.favor_day+")"), function(j, favor_day_item) {
										$("#detail_div input[name="+favor_day_item+"]").prop("checked",true);
									});
									$("#detail_div textarea[name=memo]").val(item.memo);

									$.each(item.sub.data, function(j, subItem) {
										var newData = "";
										newData += "<tr id=\"row_"+(j+1)+"\">";
										newData += "<td id=\"l\">"+subItem.food_name+"</td>";
										newData += "<td id=\"l\">"+subItem.food_price+"</td>";
										newData += "<td id=\"l\">"+subItem.unit_name+"</td>";
										newData += "<td id=\"l\">"+subItem.favor_price+"</td>";
										newData += "</tr>";
										$('#detail_div #sub_tbody').append(newData);
										$('#detail_div #sub_tbody #row_'+(j+1)+" select[name=food_id]").val(subItem.food_id);
									});
								});
							}
						},
						error : function(data) {
							alert("error")
						}
					});
				});

		//查询
		$("#but_search").button().click(function() {
			search(1);
		});

		//编辑确定
		$("#edit_div").dialog(
				{
					autoOpen : false,
					height : 350,
					width : 700,
					modal : true,
					buttons : {
						"保存" : function() {
							var o=$('#edit_form').serializeObject();
							var vo=checkValidate(o,"edit_div");
							o["favor_week"]=$.toJSON(getChecked2("#edit_div input[name^=favor_week_]"));
							o["favor_day"]=$.toJSON(getChecked2("#edit_div input[name^=favor_day_]"));
							//子表
							var rowCount = parseInt($('#maxRow').val());
							var subo={};
							var subvo={};
							for(var i=1;i<=rowCount;i++){
								if($("#edit_div #sub_tbody #row_"+i).val()!=undefined){
									var row={};
									var vrow={};
									row["food_id"]=$("#edit_div #sub_tbody #row_"+i+" select[name=food_id]").val();
									row["favor_price"]=$("#edit_div #sub_tbody #row_"+i+" input[name=favor_price]").val();
									row["id"]=$("#edit_div #sub_tbody #row_"+i+" input[name=id]").val();
									var vrow=checkValidate(row,"edit_div #sub_tbody #row_"+i);
									subo[i]=row;
									subvo[i]=vrow;
								}
							}
							o["sub"]=subo;
							vo["sub"]=subvo;
							var jsoncheckinfo = $.toJSON(vo);
							o["validate"]=vo;
							var bValid=true;
							jQuery.ajax({
								async : false,
								type : 'POST',
								contentType : 'application/json',
								url : '/uwoa/system/validate',
								data : jsoncheckinfo,
								dataType : 'json',
								success : function(data) {
									if (data && data.success == "error") {
										alertDialog(data.errorInfo);
										bValid=false;
									}
								},
								error : function(data) {
									alert("error")
								}
							});
							
							if (bValid) {
								var jsonuserinfo = $.toJSON(o);
								jQuery.ajax({
									type : 'POST',
									contentType : 'application/json',
									url : '/uwoa/food/specialOffer/edit',
									data : jsonuserinfo,
									dataType : 'json',
									success : function(data) {
										if (data && data.success == "error") {
											alertDialog(data.errorInfo);
										}
										else{
											search(1);
										}
									},
									error : function(data) {
										alert("error")
									}
								});
								$(this).dialog("close");
							}
							
						},
						"取消" : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
					}
				});

		//查看确定
		$("#detail_div").dialog({
			autoOpen : false,
			height : 350,
			width : 700,
			modal : true,
			buttons : {
				"关闭" : function() {
					$(this).dialog("close");
				}
			},
			"取消" : function() {
			}
		});
		
		//新增按钮
		$("#but_addRow").button().click(function() {
			if($('#edit_div #maxRow').val()==""){
				$('#edit_div #maxRow').val(0);
			}
			$('#edit_div #maxRow').val(parseInt($('#edit_div #maxRow').val())+1);
			
			var newData="";
			newData += "<tr id=\"row_"+$('#maxRow').val()+"\">";
			newData += '<td id=\"l\" name=\"d1\"><select name=\"food_id\" id=\"food_id_'+$('#maxRow').val()+'\" validate=\"notNull\" inputName=\"子表-菜名\" style=\"width:100%\"><%=FoodHelper.optionSubHtml("ALL").replaceAll("\"", "\\\\\"") %></select></td>';
			newData += "<td id=\"l\" name=\"d2\"><input type=\"text\" name=\"food_price\" id=\"food_price\" style=\"width:100%\" readonly/></td>";
			newData += "<td id=\"l\" name=\"d3\"></td>";
			newData += "<td id=\"l\" name=\"d4\"><input type=\"text\" name=\"favor_price\" id=\"favor_price\" validate=\"notNull\" inputName=\"子表-优惠价\" style=\"width:100%\"/></td>";
			newData += "<td id=\"c\"><input type=\"hidden\" name=\"id\" id=\"id\" /><img src=\"${pageContext.request.contextPath}/resources/normal/images/but_delete.gif\" onclick=\"deleteRow(this)\"></img></td>";
			newData += "</tr>";
			$('#edit_div #sub_tbody').append(newData);
			$("#food_id_"+$('#maxRow').val() ).combobox();
		});
		
		//新增按钮
		function deleteRow(obj) {
			$(obj).parent().parent().remove();
		}
		
		function comboboxCallback(option){
			var rowid=option.getAttribute("rowid");
			$("#edit_div #data_table #row_"+rowid+" td[name=d2] input").val(option.getAttribute("price"));
			$("#edit_div #data_table #row_"+rowid+" td[name=d3]").html(option.getAttribute("unit"));
		}
	</script>
</body>