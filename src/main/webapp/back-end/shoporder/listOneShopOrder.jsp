<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shoporder.model.*"%>

<%	// ShopServlet.java(Concroller), 存入req的shopVO物件
	ShopOrderVO shopOrderVO = (ShopOrderVO) request.getAttribute("shopOrderVO");
%>

<html>
<head>
	<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- 引入 backEndPage -->
	<jsp:include page="../header.jsp" flush="true" />
	<title>島旅 Island 島旅 Island Travel - 一筆訂單資料listOneShopOrder.jsp</title>

	<style>


		table {
			width: 600px;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		table, th, td {
			border: 1px solid #CCCCFF;
		}
		th, td {
			padding: 5px;
			text-align: center;
		}
	</style>
</head>

<body>
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單成立時間</th>
		<th>訂單總金額</th>
		<th>付款方式</th>
		<th>運送方式</th>
		<th>運送狀態</th>
		<th>修改</th>
	</tr>
	<tr>
		<td><%=shopOrderVO.getItemOrderId()%></td>
		<td><%=shopOrderVO.getMemId()%></td>
		<td><%=shopOrderVO.getItemOrderDate()%></td>
		<td><%=shopOrderVO.getItemAmounts()%></td>
		<td><%=shopOrderVO.getPaymentMethod()==0?"信用卡":shopOrderVO.getPaymentMethod()==1?"現金":"匯款"%></td>
		<td><%=shopOrderVO.getShippingMethod()==0?"宅配":shopOrderVO.getShippingMethod()==1?"超商":"郵寄"%></td>
		<td><%=shopOrderVO.getShippingStatus()==0?"訂單成立":shopOrderVO.getShippingStatus()==1?"訂單已出貨":"取消"%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do" style="margin-bottom: 0px;">
				<input type="submit" class="btn btn-default" value="修改">
				<input type="hidden" name="itemOrderId"  value="${shopOrderVO.itemOrderId}">
				<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
	</tr>
</table>
</body>
</html>