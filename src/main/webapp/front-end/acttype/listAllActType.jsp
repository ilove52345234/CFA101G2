<%@page import="com.acttype.model.ActTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.acttype.model.*"%>
<%@ page import="com.act.model.*"%>

    


<%
	ActTypeService actTypeService = new ActTypeService();
	List<ActTypeVO> list = actTypeService.getAll();
	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" /> 

<html>
<head>
<meta charset="UTF-8">
<title>所有活動類別 - listAllActType.jsp</title>

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
		 <h3>所有活動類別 - listAllActType.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/front-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
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
		<th>活動類別編號</th>
		<th>活動名稱</th>
		<th>活動宣傳內文</th>
		<th>活動人數上限</th>
		<th>活動人數下限</th>
		<th>活動費用</th>
		
	</tr>
	<%@ include file="page1.file" %>    <%--Use jsp. include to reuse the same block.--%>
	<c:forEach var="actTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
		   <td><c:forEach var="actVO" items="${actSvc.all}">
                    <c:if test="${actTypeVO.actCategoryId==actVO.actCategoryId}">
	                    ${actVO.actFee}
                    </c:if>
            </c:forEach></td>
			<td>${actTypeVO.actCategoryName}</td>
			<td>${actTypeVO.actCategoryDesc}</td>
			<td>${actTypeVO.actMaxPart}</td>
			<td>${actTypeVO.actMinPart}</td>
			<td>${actTypeVO.actFee}</td> 
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>