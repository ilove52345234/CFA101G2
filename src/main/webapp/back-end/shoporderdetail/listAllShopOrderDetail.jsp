<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoporderdetail.model.*"%>
<%
	List<ShopOrderDetailVO> shopOrderDetailVO = (List<ShopOrderDetailVO>) request.getAttribute("shopOrderDetailVO");
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
		.from-group {
			text-align: center;
			font-size: 20;
		}
	</style>
</head>

<body>
<div style="text-align: center;">
	<h3><strong>訂單明細查看</strong></h3>
</div>
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

<% for (int i = 0; i < shopOrderDetailVO.size(); i++) { %>
<div class="from-group">
	<label class="control-label">訂單編號：</label>
	<%=shopOrderDetailVO.get(i).getItemOrderId()%>
</div>
<div class="from-group">
	<label class="control-label">商品名稱：</label>
	<%=shopOrderDetailVO.get(i).getShopVO().getItemName()%>
</div>
<div class="from-group">
	<label class="control-label">商品單價：</label>
	<%=shopOrderDetailVO.get(i).getShopVO().getItemFee()%>
</div>
<div class="from-group">
	<label class="control-label">購買數量：</label>
	<%=shopOrderDetailVO.get(i).getOrderQuantity()%>
</div>
<div class="from-group">
	<label class="control-label">商品總額：</label>
	<%=shopOrderDetailVO.get(i).getItemFinalAmount()%>
</div>
<% } %>
</body>
</html>