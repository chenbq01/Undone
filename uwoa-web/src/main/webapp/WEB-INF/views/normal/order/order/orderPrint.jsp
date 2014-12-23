<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<body>
<%
	Map orderInfo = (Map)request.getAttribute("orderInfo");
	if(orderInfo!=null){
		List orderData = (List)orderInfo.get("data");
		for(int i=0;i<orderData.size();i++){
			Map orderMain = (Map)orderData.get(i);
			List orderSub = (List)((Map)orderMain.get("sub")).get("data");
			List orderBatch = (List)orderMain.get("batch");
%>
	<div id="myPrintArea">
		<table border="0" width="178" cellspacing="0" cellpadding="0" style="font-size:12px">
			<tr>
				<td style="text-align:center" colspan="4">欢&nbsp;迎&nbsp;光&nbsp;临</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">
					日　期：<%=((new SimpleDateFormat("yyyy-MM-hh HH:mm:ss")).format(new Date())) %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					订单号：<%=orderMain.get("order_no") %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					桌　号：<%=orderMain.get("table_name") %>
				</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td style="text-align:center">菜品</td>
				<td style="text-align:center" width="40">数量</td>
				<td style="text-align:center" width="40">单价</td>
				<td style="text-align:center" width="50">金额</td>
			</tr>
<%
			for(int k=0;k<orderSub.size();k++){
				List orderSubGroup = (List)orderSub.get(k);
				for(int j=0;j<orderSubGroup.size();j++){
					Map row = (Map)orderSubGroup.get(j);
%>
			<tr>
				<td><%=row.get("food_name") %></td>
				<td style="text-align:right"><%=row.get("food_count") %></td>
				<td style="text-align:right"><%=row.get("food_price") %></td>
				<td style="text-align:right"><%=row.get("food_amount") %></td>
			</tr>
<%
				}
			}
%>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">
					备　注：<%=orderMain.get("memo") %>
				</td>
			</tr>
<%
			for(int k=0;k<orderBatch.size();k++){
					Map row = (Map)orderBatch.get(k);
%>
			<tr>
				<td colspan="4">
					备　注：<%=row.get("memo") %>
				</td>
			</tr>
<%
			}
%>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="3">合计：</td>
				<td style="text-align:right"><%=orderMain.get("order_amount") %></td>
			</tr>
		</table>
		<br>
		<br>
		<br>
		<table border="0" width="178" cellspacing="0" cellpadding="0" style="font-size:12px">
			<tr>
				<td style="text-align:center" colspan="4">欢&nbsp;迎&nbsp;光&nbsp;临</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">
					日　期：<%=((new SimpleDateFormat("yyyy-MM-hh HH:mm:ss")).format(new Date())) %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					订单号：<%=orderMain.get("order_no") %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					桌　号：<%=orderMain.get("table_name") %>
				</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td style="text-align:center">菜品</td>
				<td style="text-align:center" width="40">数量</td>
				<td style="text-align:center" width="40">单价</td>
				<td style="text-align:center" width="50">金额</td>
			</tr>
<%
			for(int k=0;k<orderSub.size();k++){
				List orderSubGroup = (List)orderSub.get(k);
				for(int j=0;j<orderSubGroup.size();j++){
					Map row = (Map)orderSubGroup.get(j);
%>
			<tr>
				<td><%=row.get("food_name") %></td>
				<td style="text-align:right"><%=row.get("food_count") %></td>
				<td style="text-align:right"><%=row.get("food_price") %></td>
				<td style="text-align:right"><%=row.get("food_amount") %></td>
			</tr>
<%
				}
			}
%>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			
			<tr>
				<td colspan="4">
					备　注：<%=orderMain.get("memo") %>
				</td>
			</tr>
<%
			for(int k=0;k<orderBatch.size();k++){
					Map row = (Map)orderBatch.get(k);
%>
			<tr>
				<td colspan="4">
					备　注：<%=row.get("memo") %>
				</td>
			</tr>
<%
			}
%>
<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="3">合计：</td>
				<td style="text-align:right"><%=orderMain.get("order_amount") %></td>
			</tr>
			<tr>
				<td colspan="3">折后金额：</td>
				<td style="text-align:right"><%=orderMain.get("favor_amount") %></td>
			</tr>
		</table>
		<br>
		<br>
		<br>
		<table border="0" width="178" cellspacing="0" cellpadding="0" style="font-size:12px">
			<tr>
				<td style="text-align:center" colspan="4">欢&nbsp;迎&nbsp;光&nbsp;临</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">
					日　期：<%=((new SimpleDateFormat("yyyy-MM-hh HH:mm:ss")).format(new Date())) %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					订单号：<%=orderMain.get("order_no") %>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					桌　号：<%=orderMain.get("table_name") %>
				</td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td style="text-align:center">菜品</td>
				<td style="text-align:center" width="40">数量</td>
				<td style="text-align:center" width="40">单价</td>
				<td style="text-align:center" width="50">金额</td>
			</tr>
<%
			for(int k=0;k<orderSub.size();k++){
				List orderSubGroup = (List)orderSub.get(k);
				for(int j=0;j<orderSubGroup.size();j++){
					Map row = (Map)orderSubGroup.get(j);
%>
			<tr>
				<td><%=row.get("food_name") %></td>
				<td style="text-align:right"><%=row.get("food_count") %></td>
				<td style="text-align:right"><%=row.get("food_price") %></td>
				<td style="text-align:right"><%=row.get("food_amount") %></td>
			</tr>
<%
				}
			}
%>
			<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="4">
					备　注：<%=orderMain.get("memo") %>
				</td>
			</tr>
<%
			for(int k=0;k<orderBatch.size();k++){
					Map row = (Map)orderBatch.get(k);
%>
			<tr>
				<td colspan="4">
					备　注：<%=row.get("memo") %>
				</td>
			</tr>
<%
			}
%>
<tr>
				<td style="text-align:center" colspan="4"><hr/></td>
			</tr>
			<tr>
				<td colspan="3">合计：</td>
				<td style="text-align:right"><%=orderMain.get("order_amount") %></td>
			</tr>
			<tr>
				<td colspan="3">折后金额：</td>
				<td style="text-align:right"><%=orderMain.get("favor_amount") %></td>
			</tr>
		</table>
	</div>
	<script>
		$(document).ready(function() {
			$("#myPrintArea").printArea();
		});
	</script>
<%
		}
	}
	else{
%>
	<script>
		$(document).ready(function() {
			alert("没有找到该订单，可能已经删除，请刷新重试。");
		});
	</script>
<%
	}
%>
</body>