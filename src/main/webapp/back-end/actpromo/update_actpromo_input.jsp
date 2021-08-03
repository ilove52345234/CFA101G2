<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.actpromo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActPromoVO actPromoVO = (ActPromoVO)request.getAttribute("actPromoVO");
%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動優惠資料修改 - update_actpromo_input.jsp</title>

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
		 <h3>活動優惠資料修改 - update_actpromo_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/actpromo/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/actpromo/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
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
		<td>活動優惠編號:<font color=red><b>*</b></font></td>
		<td><%=actPromoVO.getActPromotionId()%></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actPromotionName" size="11" value="<%=actPromoVO.getActPromotionName()%>" /></td>
	</tr>
	<tr>
		<td>活動宣傳內文:</td>
		<td><input type="TEXT" name="actPromotionDiscount" size="45" value="<%=actPromoVO.getActPromotionDiscount()%>" /></td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actPromotionId" value="<%=actPromoVO.getActPromotionId()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>