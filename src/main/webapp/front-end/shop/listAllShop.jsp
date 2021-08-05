<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>島旅商城 </title>

<style>
body{
	  height:100%;
}
table#table-1 {
	background-color: #6495ED;
    border: 2px solid black;
    text-align: center;
  
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: pink;
    display: inline;
  }
    table {
	width: 1000px;
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
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有商品</h3>
		 <h4><a href="shopHomePage.jsp"><img src="images/島旅商城明細.jpg" width="200" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>
<jsp:useBean id="sps" scope="page" class="com.shoppic.model.ShopPicService" />  	

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
		<th>圖片</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品數量</th>
		<th>購物車</th>	
		<th>追蹤清單</th>	
		
	</tr>
	
	<%@ include file="shoppage1.file" %>
	<c:forEach  var="shopVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
		<td><a href="listOneShop.jsp"${sps.getOneShopPic(shopVO.itemId)}><img src="data:image/jpeg;base64,${sps.getOneShopPic(shopVO.itemId)}"></a>
		<input type="hidden" name="action" value="getItemName_For_Display">
		</td>	

		<td>${shopVO.itemName}</td>
		<td>${shopVO.itemFee}</td>
		<td>${shopVO.itemQuantity}</td>
		<td><input type="button" name="action" value="add"></td>
		<td><input type="button" name="action" value="add"></td>

		</tr>		
	</c:forEach>
</table>
<%@ include file="shoppage2.file" %>
</body>
</html>