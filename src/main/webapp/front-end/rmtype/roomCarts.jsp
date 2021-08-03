<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmorder.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Object roomCarts = session.getAttribute("roomCarts");// �qsession���o
    List<BookRoomRequestVO> roomCartList = null;
    if (roomCarts != null) {
    	roomCartList=(List<BookRoomRequestVO>) roomCarts;
    }
    pageContext.setAttribute("roomCartsList",roomCartList);
%>


<html>
<head>
<title>�ж��ʪ���</title>

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
  <h1>�ж��ʪ���</h1>      
  <p>�H�U���ʪ��M��</p>
</div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%-- �ж���� --%>
<div class="container">
<c:if test="${empty roomCartsList}">
	<font style="color:red">�ثe�S���ж��[�J�ʪ���</font>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType" class="btn btn-danger">��^�q�ЦC��</a>
</c:if>
<c:if test="${not empty roomCartsList}">
	<table class="table">
	<tr class="info">
		<th>�Ы��s��</th>
		<th>�ж��W��</th>
		<th>�J����</th>
		<th>�h�Ф��</th>
		<th>�H��</th>
		<th>�ж���</th>
		<th>�R��</th>
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
			  <button type="button" class="btn btn-info" >�ק�(�ثe�g��)</button>
			</td> -->	
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmorder/RmCartServlet?action=delete">
			  <input type="hidden" name="roomCategoryId" value="${roomCart.roomCategoryId}"> 
			  <input type="hidden" name="checkInDate" value="${roomCart.checkInDate}"> 
			  <input type="hidden" name="checkOutDate" value="${roomCart.checkOutDate}"> 
			  <input type="submit" class="btn btn-info" value="�R��">
			 </form>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<br>
  <div style="text-align:center">
<a href="<%=request.getContextPath()%>/rmorder/RmorderFromCartServlet" class="btn btn-success">�ڭn�q��</a>
<a href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType" class="btn btn-danger">��^�q�ЦC��</a>
   <div>
</c:if>

</body>
</html>