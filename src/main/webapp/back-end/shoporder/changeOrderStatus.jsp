<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shoporder.model.*"%>

<%	//ShopServlet.java (Concroller) 存入req的shopVO物件 (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)
  ShopOrderVO shopOrderVO = (ShopOrderVO) request.getAttribute("shopOrderVO"); 
System.out.println("shopOrderVO: "+shopOrderVO);
%>

<html>
<head>
<meta charset="BIG5">
<title>修改訂單明細 - updateShopOrder</title>

<!-- 匯入jQuery-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function updateItemStatus(itemStatus){
	if(itemStatus == 0){
	$('#itemStatus').val(0);
	}else{
	$('#itemStatus').val(1);
	}
	$('#update_form').submit();
}
</script>

</head>
<body>

<h3>訂單狀態修改 - changeOrderStatus.jsp</h3>
		 
<h3>商品資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" id="update_form" ACTION="shoporder.do" name="form1">
<table>
	<tr>
		<td>訂單編號：</td><td><%=shopOrderVO.getItemOrderId()%></td>
	</tr>
	<tr>
		<td>會員編號：</td><td><%=shopOrderVO.getMemId()%></td>
	</tr>
	<tr>
		<td>訂單成立時間：</td><td><%=shopOrderVO.getItemOrderDate()%></td>
	</tr>
	<tr>
		<td>訂單總金額：</td><td><%=shopOrderVO.getItemAmounts()%></td>
	</tr>
	<tr>
		<td>付款方式：</td>
		<td><input type="TEXT" name="paymentMethod" size="45" value="<%=shopOrderVO.getPaymentMethod()%>" />

		</td>
	</tr>
	<tr>
		<td>運送方式：</td>
		<td><input type="TEXT" name="shippingMethod" size="45" value="<%=shopOrderVO.getShippingMethod()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態：</td>
		<td><input type="TEXT" id="itemStatus" name="shippingStatus" size="45" value="<%=shopOrderVO.getShippingStatus()%>"/></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemOrderId" value="<%=shopOrderVO.getItemOrderId()%>">
<input type="hidden" name="memId" value="<%=shopOrderVO.getMemId()%>">
<input type="hidden" name="itemOrderDate" value="<%=shopOrderVO.getItemOrderDate()%>">
<input type="hidden" name="itemAmounts" value="<%=shopOrderVO.getItemAmounts()%>">
<input type="submit" value="送出修改"></FORM>
<h4><a href="/back-end/shop/backEndPage.jsp">回首頁</a></h4>

</body>
</html>