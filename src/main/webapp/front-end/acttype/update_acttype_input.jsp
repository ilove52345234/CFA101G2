<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.acttype.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActTypeVO actTypeVO = (ActTypeVO)request.getAttribute("actTypeVO");
%>
<%-- <%= actTypeVO== null %>--${ActTypeVO.actCategoryId}-- 檢驗此時的request中有沒有empVO，對照controller的failureView部分RequestDispatcher
 --%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動類別資料修改 - update_acttype_input.jsp</title>

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
		 <h3>活動類別資料修改 - update_acttype_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet2" name="form1">
<table>
	<tr>
		<td>活動類別編號:<font color=red><b>*</b></font></td>
		<td><%=actTypeVO.getActCategoryId()%></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actCategoryName" size="11" value="<%=actTypeVO.getActCategoryName()%>" /></td>
	</tr>
	<tr>
		<td>活動宣傳內文:</td>
		<td><input type="TEXT" name="actCategoryDesc" size="45" value="<%=actTypeVO.getActCategoryDesc()%>" /></td>
	</tr>
	
	<tr>
		<td>活動人數上限:</td>
		<td><input type="TEXT" name="actMaxPart" size="11"	value="<%=actTypeVO.getActMaxPart()%>" /></td>
	</tr>
	<tr>
		<td>活動人數下限:</td>
		<td><input type="TEXT" name="actMinPart" size="11" value="<%=actTypeVO.getActMinPart()%>" /></td>
	</tr>
	<tr>
		<td>活動費用:</td>
		<td><input type="TEXT" name="actFee" size="11" value="<%=actTypeVO.getActFee()%>" /></td>
	</tr>
	<tr>
		<td>評價總人數:</td>
		<td><input type="TEXT" name="actNumber" size="11" value="<%=actTypeVO.getActNumber()%>" /></td>
	</tr>
	<tr>
		<td>評價總分:</td>
		<td><input type="TEXT" name="actTotalScore" size="11" value="<%=actTypeVO.getActTotalScore()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actCategoryId" value="<%=actTypeVO.getActCategoryId()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>