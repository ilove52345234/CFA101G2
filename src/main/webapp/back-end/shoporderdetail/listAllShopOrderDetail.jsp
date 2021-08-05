<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoporderdetail.model.*"%>
<%-- <%@ page import="com.shop.model.*"%> --%>
<%
	List<ShopOrderDetailVO> shopOrderDetailVO = (List<ShopOrderDetailVO>)request.getAttribute("shopOrderDetailVO");
	System.out.println(shopOrderDetailVO);
	pageContext.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
%>

<html>
<head>
	<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- 引入 backEndPage -->
	<jsp:include page="../header.jsp" flush="true" />

	<style>
		table {
			width: 1250px;
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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>訂單編號</th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>購買數量</th>
		<th>商品總額</th>
	</tr>
	<% for(int i = 0; i < shopOrderDetailVO.size(); i++) { %>
	<tr>
		<td><%=shopOrderDetailVO.get(i).getItemOrderId()%></td>
		<td><%=shopOrderDetailVO.get(i).getShopVO().getItemName()%></td>
		<td><%=shopOrderDetailVO.get(i).getShopVO().getItemFee()%></td>
		<td><%=shopOrderDetailVO.get(i).getOrderQuantity()%></td>
		<td><%=shopOrderDetailVO.get(i).getItemFinalAmount()%></td>
	</tr>
	<% } %>
</table>
</body>
</html>