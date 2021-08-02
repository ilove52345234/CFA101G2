<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shoporderdetail.model.*"%>

<%
    ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
    List<ShopOrderDetailVO> list = shopOrderDetailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有訂單明細資料 - listAllShopOrderDetail</title>

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

<h3>所有訂單資料 - listAllShopOrderDetail.jsp</h3>
	
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
		<th>商品編號</th>
		<th>商品數量</th>
		<th>商品金額</th>
		
		<th>修改</th>
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="shopOrderDetailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${shopOrderDetailVO.itemOrderId}</td>
			<td>${shopOrderDetailVO.itemId}</td>
			<td>${shopOrderDetailVO.itemQuantity}</td>
			<td>${shopOrderDetailVO.itemAmounts}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporderdetail/shoporderdetail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemOrderId"  value="${shopOrderDetailVO.itemOrderId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporderdetail/shoporderdetail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="itemOrderId"  value="${shopOrderDetailVO.itemOrderId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>
 <h4><a href="/CFA101G2/back-end/shoporder/searchOrder.jsp">回首頁</a></h4>

</body>
</html>