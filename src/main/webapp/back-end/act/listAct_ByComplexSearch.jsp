<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    <%--declare taglib before use "fmt" tag to format dateFormat.--%>
<%@ page import="com.act.model.*"%>

 
<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />
<jsp:useBean id="listAct_ByComplexSearch" scope="request" type="java.util.List<ActVO>" />    




<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
.//如果要JOIN其他table的column要用 jsp:useBean id = "xxxSvc" scope ="page" class= "com.xxx.model.xxxService"
 --%>
 




<html>
<head>
<meta charset="UTF-8">
<title>複合查詢 - listAct_ByComplexSearch.jsp</title>

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
		 <h3>複合查詢 - listAct_ByComplexSearch.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/act/actBackendIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/act/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
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
		<!-- <th><input type=checkbox id= selectAll></input></th> -->
		<th>活動編號</th>
		<th>活動類別名稱</th>
		<th>活動優惠名稱</th>
		<th>本期活動說明</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動狀態</th>
		<th>本期報名費用</th>
		<th>目前報名人數</th>
		<th>開始報名日期</th>
		<th>報名截止日期</th>
		<th>活動人數上限</th>
		<th>活動人數下限</th>
		
	</tr>
<%-- 	<%@ include file="page1.file" %>    Use jsp. include to reuse the same block. --%>
<%-- begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1% --%>
	<c:forEach var="actVO" items="${listAct_ByComplexSearch}">
		
		<tr align='center' valign='middle'>
 			<td>${actVO.actId}</td>
			<td>${actTypeSvc.getOneById(actVO.actCategoryId).actCategoryName}</td>
			<td>${actPromoSvc.getOneById(actVO.actPromotionId).actPromotionName}</td>
			<td>${actVO.actDescription}</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.actStart}"/></td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.actEnd}"/></td>
			<td>${actVO.actStatus}</td>		
			<td>${actVO.actFee}</td> 
			<td>${actVO.applicants}</td> 
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.partStart}"/></td> 
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.partEnd}"/></td> 		
			<td>${actVO.actMaxPart}</td>
			<td>${actVO.actMinPart}</td>	 
			<%-- <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actId"  value="${actVO.actId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td> --%>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %>
 --%>
</body>
</html>