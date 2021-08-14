<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoporder.model.*"%>

<%
	List<ShopOrderVO> shopOrderVOList = (List<ShopOrderVO>) request.getAttribute("shopOrderVOList");
	// 	int pageNumber = (int) request.getAttribute("pageNumber");
	// 	int shopListCount = (int) request.getAttribute("shopListCount");
	// 	int pageSize = (int) request.getAttribute("pageSize");
	String startDate = (String) request.getAttribute("startDate");
	String endDate = (String) request.getAttribute("endDate");
	pageContext.setAttribute("shopOrderVOList", shopOrderVOList);
	System.out.println(shopOrderVOList);
	System.out.println("startDate= " + startDate);
	System.out.println("endDate= " + endDate);
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
		#listShopId {
			vertical-align: middle !important;
			text-align: center;
		}

		.table>thead>tr>th {
			vertical-align: bottom;
			border-bottom: 2px solid #ddd;
			text-align: center;
		}
	</style>
</head>

<body>
<div>
	<div style="text-align: center;">
		<h3><strong>搜尋之訂單</strong></h3>
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table id="shopTable" class="table table-hover">
		<thead>
		<tr style="vertical-align: middle !important; text-align: center;">
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>收件人姓名</th>
			<th>收件人電話</th>
			<th>收件人地址</th>
			<th>訂單成立時間</th>
			<th>訂單總金額</th>
			<th>付款方式</th>
			<th>運送方式</th>
			<th>訂單狀態</th>
			<th>查看明細</th>
			<th>修改</th>
		</tr>
		</thead>
		<% System.out.println(shopOrderVOList.size()); %>
		<c:forEach var="shopOrderVO" items="${shopOrderVOList}">
			<tbody id="myTable">
			<tr id="listShopId">
				<td>${shopOrderVO.itemOrderId}</td>
				<td>${shopOrderVO.memId}</td>
				<td>${shopOrderVO.orderName}</td>
				<td>${shopOrderVO.orderMobile}</td>
				<td>${shopOrderVO.orderAddress}</td>
				<td>${shopOrderVO.itemOrderDate}</td>
				<td>${shopOrderVO.itemAmounts}</td>
				<td>${shopOrderVO.paymentMethod==0?"信用卡":shopOrderVO.paymentMethod==1?"現金":"匯款"}</td>
				<td>${shopOrderVO.shippingMethod==0?"宅配":shopOrderVO.shippingMethod==1?"超商":"郵寄"}</td>
				<td>${shopOrderVO.shippingStatus==0?"訂單成立":shopOrderVO.shippingStatus==1?"訂單已出貨":"訂單已取消"}</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporderdetail/shoporderdetail.do" style="margin-bottom: 0px;">
						<input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
						<input type="hidden" name="action" value="getShop_Join_Detail">
						<input type="submit" class="btn btn-default" value="查看明細">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do" style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-default" value="修改">
						<input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
			</tbody>
		</c:forEach>
	</table>
	<!-- 	<ul class="pagination"> -->
	<%-- 		<% --%>
	<!-- // 			for (int i = 0; i <= Math.floor(shopListCount / pageSize); i += 1) { -->
	<%-- 		%> --%>
	<%-- 		<li class="<%=pageNumber == i + 1 ? "active" : ""%>"><a --%>
	<%-- 			href="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do?action=searchOrder&startDate=<%=startDate%>&endDate=<%=endDate%>&pageNumber=<%=i + 1%>&pageSize=<%=pageSize%> "><%=i + 1%></a></li> --%>
	<%-- 		<% --%>
	<!-- // 			} -->
	<%-- 		%> --%>
	<!-- 	</ul> -->
</div>
</body>
</html>