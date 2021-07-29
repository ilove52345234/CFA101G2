<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoporder.model.*"%>

<%
	List<ShopOrderVO> shopOrderVOList = (List<ShopOrderVO>) request.getAttribute("shopOrderVOList");
// 	String searcName = (String) request.getAttribute("searcName");
	int pageNumber = (int) request.getAttribute("pageNumber");
	int shopListCount = (int) request.getAttribute("shopListCount");
	int pageSize = (int) request.getAttribute("pageSize");
	pageContext.setAttribute("shopOrderVOList", shopOrderVOList);
	System.out.println(shopOrderVOList);
%>

<html>
<head>
<title>搜尋相關商品 - InquireRelatedProducts</title>

<!-- 匯入bootstrap css-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- 匯入jQuery-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 匯入bootstrap javascript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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

	<h3>搜尋相關商品 - InquireRelatedProducts.jsp</h3>


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
			<th>商品編號</th>
			<th>商品類別編號</th>
			<th>商品敘述</th>
			<th>商品價格</th>
			<th>商品名稱</th>
			<th>商品數量</th>
			<th>商品狀態</th>
			<th>評價總人數</th>
			<th>評價總分</th>
			<th>修改</th>
		</tr>
		<% System.out.println(shopOrderVOList.size());%>
		<c:forEach var="shopOrderVOList" items="${shopOrderVOList}">
			<tr>
				<td>${shopOrderVOList.itemOrderId}</td>
				<td>${shopOrderVOList.memId}</td>
				<td>${shopOrderVOList.itemOrderDate}</td>
				<td>${shopOrderVOList.itemAmounts}</td>
				<td>${shopOrderVOList.paymentMethod==0?"信用卡":shopOrderVO.paymentMethod==1?"現金":"匯款"}</td>
				<td>${shopOrderVOList.shippingMethod}</td>
				<td>${shopOrderVOList.shippingStatus}</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="itemId" value="${shopOrderVOList.itemId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>

	</table>
	<ul class="pagination">
<%-- 	<% for(int i = 0; i <= Math.floor(shopListCount/pageSize); i+=1) { %> --%>
<%-- 		<li class="<%= pageNumber==i+1 ? "active":"" %>"><a href="<%=request.getContextPath()%>/back-end/shop/shop.do?action=getSearch&itemName=<%=searcName%>&pageNumber=<%=i+1 %>&pageSize=<%=pageSize %> "><%=i+1 %></a></li> --%>
<%--     <% } %>	 --%>
	</ul>

	<h4>
		<a href="searchCommodity.jsp">搜尋商品</a>
	</h4>
	<h4>
		<a href="backEndPage.jsp">回首頁</a>
	</h4>

</body>
</html>