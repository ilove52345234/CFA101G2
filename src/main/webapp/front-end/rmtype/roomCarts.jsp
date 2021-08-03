<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmorder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Object roomCarts = session.getAttribute("roomCarts");// 從session取得
    List<BookRoomRequestVO> roomCartList = null;
    if (roomCarts != null) {
    	roomCartList=(List<BookRoomRequestVO>) roomCarts;
    }
    pageContext.setAttribute("roomCartsList",roomCartList);
%>


<html>
<head>
<title>房間購物車</title>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 2 ;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
/*   table.box{
  width: 1000px;
  height: 250px;
  margin: auto;
  border:2px;
} */
</style>

</head>
<body bgcolor='white'>


<div class="jumbotron">
  <h1>房間購物車</h1>      
  <p>以下為購物清單</p>
</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%-- 房間資料 --%>
<div class="container">
<c:if test="${empty roomCartsList}">
	<font style="color:red">目前沒有房間加入購物車</font>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType" class="btn btn-danger">返回訂房列表</a>
</c:if>
<c:if test="${not empty roomCartsList}">
	<table class="table">
	<tr class="info">
		<th>房型編號</th>
		<th>房間名稱</th>
		<th>入住日期</th>
		<th>退房日期</th>
		<th>人數</th>
		<th>房間數</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="roomCart" items="${roomCartsList}">
		
		<tr class="Active">
			<td>${roomCart.roomCategoryId}</td>
			<td>${roomCart.roomName}</td>
			<td>${roomCart.checkInDate}</td>
			<td>${roomCart.checkOutDate}</td>
			<td>${roomCart.memNumber}</td>
			<td>${roomCart.roomNumber}</td> 
<!-- 		<td>
			  <button type="button" class="btn btn-info" >修改(目前寫死)</button>
			</td> -->	
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmorder/RmCartServlet?action=delete">
			  <input type="hidden" name="roomCategoryId" value="${roomCart.roomCategoryId}"> 
			  <input type="hidden" name="checkInDate" value="${roomCart.checkInDate}"> 
			  <input type="hidden" name="checkOutDate" value="${roomCart.checkOutDate}"> 
			  <input type="submit" class="btn btn-info" value="刪除">
			 </form>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<br>
  <div style="text-align:center">
<a href="<%=request.getContextPath()%>/rmorder/RmorderFromCartServlet" class="btn btn-success">我要訂房</a>
<a href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType" class="btn btn-danger">返回訂房列表</a>
   <div>
</c:if>

</body>
</html>