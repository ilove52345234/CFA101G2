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
	System.out.println("startDate= "+startDate);
	System.out.println("endDate= "+endDate);
	
%>

<html>
<head>
<title>所有訂單資料 - listAllShopOrder</title>

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

	<h3>所有訂單資料 - listAllShopOrder.jsp</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單成立時間</th>
			<th>訂單總金額</th>
			<th>付款方式</th>
			<th>運送方式</th>
			<th>訂單狀態</th>
			<th>修改</th>

		</tr>
		<%
			System.out.println(shopOrderVOList.size());
		%>
		<c:forEach var="shopOrderVO" items="${shopOrderVOList}">
			<tr>
				<td>${shopOrderVO.itemOrderId}</td>
				<td>${shopOrderVO.memId}</td>
				<td>${shopOrderVO.itemOrderDate}</td>
				<td>${shopOrderVO.itemAmounts}</td>
				<td>${shopOrderVO.paymentMethod==0?"信用卡":shopOrderVO.paymentMethod==1?"現金":"匯款"}</td>
				<td>${shopOrderVO.shippingMethod==0?"宅配":shopOrderVO.shippingMethod==1?"超商":"郵寄"}</td>
				<td>${shopOrderVO.shippingStatus==0?"成立":shopOrderVO.shippingStatus==1?"出貨":shopOrderVO.shippingStatus==2?"完成":"取消"}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
						 <input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
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

	<h4>
		<a href="/CFA101G2/back-end/shoporder/searchOrder.jsp">回首頁</a>
	</h4>

</body>
</html>