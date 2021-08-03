<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.actpromo.model.*"%>


<%
	ActPromoVO actPromoVO = (ActPromoVO)request.getAttribute("actPromoVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>活動優惠資料 - listOneActPromo.jsp</title>

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
	width: 600px;
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

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4>
 -->
 <table id="table-1">
 	<tr><td>
		 <h3>活動優惠資料 - listOneActPromo.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/actpromo/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/actpromo/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動優惠編號</th>
		<th>活動優惠名稱</th>
		<th>活動優惠折數</th>
	</tr>
	<tr>
		<td><%=actPromoVO.getActPromotionId()%></td>
		<td><%=actPromoVO.getActPromotionName()%></td>
		<td><%=actPromoVO.getActPromotionDiscount()%></td>
		
	</tr>
</table>

</body>
</html>