<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shoporder.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增訂單明細 - addShopOrder.jsp</title>
</head>
<body>

<h3>新增訂單明細:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="shoporder.do" name="form1">

<table>

	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="itemOrderId" size="45" value="${param.itemOrderId}"/></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memId" size="45" value="${param.memId}"/></td>
	</tr>
	<tr>
		<td>訂單成立時間:</td>
		<td><input type="TEXT" name="itemOrderDate" size="45" value="${param.itemOrderDate}"/></td>
	</tr>
	<tr>
		<td>訂單總金額:</td>
		<td><input type="TEXT" name="itemAmounts" size="45" value="${param.itemAmounts}"/></td>
	</tr>
	<tr>
		<td>付款方式:</td>
		<td><input type="TEXT" name="paymentMethod" size="45" value="${param.paymentMethod}"/></td>
	</tr>
	<tr>
		<td>運送方式:</td>
		<td><input type="TEXT" name="shippingMethod" size="45" value="${param.shippingMethod}"/></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="shippingStatus" size="45" value="${param.shippingStatus}"/></td>
	</tr>

</table>

<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
<h4><a href="/back-end/shop/backEndPage.jsp">回首頁</a></h4>


</body>
</html>