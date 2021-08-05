<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.acttype.model.*"%>
<%@ page import="com.actphoto.model.*"%>

<%
	ActTypeVO actTypeVO = (ActTypeVO)request.getAttribute("actTypeVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>活動類別資料 - listOneActType.jsp</title>

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
		 <h3>活動類別資料 - listOneActType.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動類別編號</th>
		<th>活動名稱</th>
		<th>活動宣傳內文</th>
		<th>活動人數上限</th>
		<th>活動人數下限</th>
		<th>活動費用</th>
		<th>評價總人數</th>
		<th>評價總分</th>
	</tr>
	<tr>
		<td><%=actTypeVO.getActCategoryId()%></td>
		<td><%=actTypeVO.getActCategoryName()%></td>
		<td><%=actTypeVO.getActCategoryDesc()%></td>
		<td><%=actTypeVO.getActMaxPart()%></td>
		<td><%=actTypeVO.getActMinPart()%></td>
		<td><%=actTypeVO.getActFee()%></td>
		<td><%=actTypeVO.getActNumber()%></td>
		<td><%=actTypeVO.getActTotalScore()%></td>
	</tr>
</table>

</body>
</html>