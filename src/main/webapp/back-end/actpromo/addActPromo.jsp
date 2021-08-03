<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actpromo.model.*"%>


<%
	ActPromoVO actPromoVO = (ActPromoVO)request.getAttribute("actPromoVO");
%>
<%-- <%= actPromoVO== null %>--${ActPromoVO.actPromotionId}--
 --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動優惠方案新增 - addActPromo.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>活動優惠方案新增 - addActPromo.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath()%>/back-end/actpromo/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/actpromo/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列，

>>>從controller中，每個error msg linkedlist來儲存每次validation&exception流程中 call list.add(errMsg)
 所存放的一系列errors including:

 	1. input validation errors by "errorMsgs.add("員工姓名: 請勿空白");".
 	2. other possible errors in "catch" block by "errorMsgs.add(e.getMessage());".

  	ref: https://www.geeksforgeeks.org/throwable-getmessage-method-in-java-with-examples/

  will call RequestDispatcher(JSP url) to "forward request" with error(s) set in attribute "errorMsgs"
  back to the original-request-sent  JSP, and show as below.


 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" name="form1">
<table>

	<tr>
		<td>活動優惠名稱:</td>
		<td><input type="TEXT" name="actPromotionName" size="11"
			 value="<%= (actPromoVO==null)? "暑期優惠" : actPromoVO.getActPromotionName()%>" /></td>
	</tr>
	<tr>
		<td>活動優惠折數:</td>
		<td><input type="TEXT" name="actPromotionDiscount" size="45"
			 value="<%= (actPromoVO==null)? "0.78" : actPromoVO.getActPromotionDiscount()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>