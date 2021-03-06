<%@page import="com.actpromo.model.ActPromoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actpromo.model.*"%>

    


<%
	ActPromoService actPromoService = new ActPromoService();
	List<ActPromoVO> list = actPromoService.getAll();
	pageContext.setAttribute("list",list);
%>



<html>
<head>
<meta charset="UTF-8">
<title>所有活動優惠方案 - listAllActPromo.jsp</title>

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

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4>
 -->
 <table id="table-1">
 
	<tr><td>
		 <h3>所有活動優惠方案 - listAllActPromo.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/actpromo/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/actpromo/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>活動優惠編號</th>
		<th>活動優惠名稱</th>
		<th>活動優惠折數</th>
	</tr>
	<%@ include file="page1.file" %>    <%--Use jsp. include to reuse the same block.--%>
	<c:forEach var="ActPromoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ActPromoVO.actPromotionId}</td>
			<td>${ActPromoVO.actPromotionName}</td>
			<td>${ActPromoVO.actPromotionDiscount}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actPromotionId"  value="${ActPromoVO.actPromotionId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<%-- <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="actPromotionId"  value="${ActPromoVO.actPromotionId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td> --%>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>