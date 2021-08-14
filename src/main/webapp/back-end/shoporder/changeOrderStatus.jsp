<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shoporder.model.*"%>
<%
	//ShopServlet.java (Concroller) 存入req的shopVO物件 (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)
	ShopOrderVO shopOrderVO = (ShopOrderVO) request.getAttribute("shopOrderVO");
	System.out.println("shopOrderVO: " + shopOrderVO);
%>

<html>
<head>
	<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- 引入 backEndPage -->
	<jsp:include page="../header.jsp" flush="true" />
	<!-- 匯入jQuery-->
	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<script>
		function updateItemStatus(itemStatus) {
			if (itemStatus == 0) {
				$('#itemStatus').val(0);
			} else {
				$('#itemStatus').val(1);
			}
			$('#update_form').submit();
		}
	</script>

	<style>
		.font {
			text-align: center;
			font-size: 20px;
			margin: auto;
			font-style: "微軟正黑體", cursive;
			line-height: 40px;
		}

		.center {
			margin-left: auto;
			margin-right: auto;
		}
	</style>
</head>

<body>
<div class="font">
	<FORM METHOD="post" id="update_form" ACTION="shoporder.do" name="form1">
		<div style="text-align:center;"><h3><strong>訂單狀態修改</strong></h3></div>
		<br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table class="center">
			<tr>
				<td>訂單編號：</td>
				<td><%=shopOrderVO.getItemOrderId()%></td>
			</tr>
			<tr>
				<td>會員編號：</td>
				<td><%=shopOrderVO.getMemId()%></td>
			</tr>
			<tr>
				<td>收件人姓名：</td>
				<td><%=shopOrderVO.getOrderName()%></td>
			</tr>
			<tr>
				<td>收件人電話：</td>
				<td><%=shopOrderVO.getOrderMobile()%></td>
			</tr>
			<tr>
				<td>收件人地址：</td>
				<td><%=shopOrderVO.getOrderAddress()%></td>
			</tr>
			<tr>
				<td>訂單成立時間：</td>
				<td><%=shopOrderVO.getItemOrderDate()%></td>
			</tr>
			<tr>
				<td>訂單總金額：</td>
				<td><%=shopOrderVO.getItemAmounts()%></td>
			</tr>
			<tr>
				<td>付款方式：</td>
				<td><%=shopOrderVO.getPaymentMethod() == 0 ? "信用卡" : shopOrderVO.getPaymentMethod() == 1 ? "現金" : "匯款"%></td>
			</tr>
			<tr>
				<td>運送方式：</td>
				<td><%=shopOrderVO.getShippingMethod() == 0 ? "宅配" : shopOrderVO.getShippingMethod() == 1 ? "超商" : "郵寄"%></td>
			</tr>
			<tr>
				<td>訂單狀態：</td>
				<td>
					<label class="radio-inline"> <% System.out.println(shopOrderVO.getShippingStatus());%>
						<input type="radio" name="shippingStatus" value="0"
							<%=shopOrderVO.getShippingStatus().toString().equals("0") ? "checked" : "1234"%>>訂單已成立
					</label>
					<label class="radio-inline">
						<input type="radio" name="shippingStatus" value="1"
							<%=shopOrderVO.getShippingStatus() == 1 ? "checked" : "123"%>>訂單已出貨
					</label> <label class="radio-inline">
					<input type="radio" name="shippingStatus" value="3"
						<%=shopOrderVO.getShippingStatus() == 3 ? "checked" : "123"%>>訂單已取消
				</label></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update">
		<input type="hidden" name="itemOrderId" value="<%=shopOrderVO.getItemOrderId()%>">
		<input type="hidden" name="memId" value="<%=shopOrderVO.getMemId()%>">
		<input type="hidden" name="orderName" value="<%=shopOrderVO.getOrderName()%>">
		<input type="hidden" name="orderMobile" value="<%=shopOrderVO.getOrderMobile()%>">
		<input type="hidden" name="orderAddress" value="<%=shopOrderVO.getOrderAddress()%>">
		<input type="hidden" name="itemOrderDate" value="<%=shopOrderVO.getItemOrderDate()%>">
		<input type="hidden" name="itemAmounts" value="<%=shopOrderVO.getItemAmounts()%>">
		<input type="hidden" name="paymentMethod" value="<%=shopOrderVO.getPaymentMethod()%>"> 				<input type="hidden" name="shippingMethod" value="<%=shopOrderVO.getShippingMethod()%>">
		<input type="submit" class="btn btn-default" value="送出修改">
	</FORM>
</div>
</body>
</html>