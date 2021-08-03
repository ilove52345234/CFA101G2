<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.act.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActVO actVO = (ActVO) request.getAttribute("actVO");
	 System.out.println(actVO.getActId()); 

%>
<%-- <%= actTypeVO== null %>--${ActTypeVO.actCategoryId}-- 檢驗此時的request中有沒有empVO，對照controller的failureView部分RequestDispatcher
 --%>


<html>
<head>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動資料修改 - update_act_input.jsp</title>

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
		 <h3>活動資料修改 - update_act_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/act/actBackendIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/act/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
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

<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />

<table>
	<tr>
		<td>活動編號:<font color=red><b>*</b></font></td>
		<td><%=actVO.getActId()%></td>
		
		<% System.out.println(actVO.getActId()); %>
		
	</tr>
</table>


	<div class="container">
		<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/act/ActServlet" name="form1">
		<table>
			<tr>
				<td>本期活動說明:</td>
				<td><input type="TEXT" name="actDescription" size="50"	value="<%=actVO.getActDescription()%>" /></td>
			</tr>
			<tr>
				<td>活動狀態:</td>
				<td><%=actVO.getActStatus()%></td>
				<td><select size="1" name="actStatus" >
							<option value="尚未報名">尚未報名</option>
						  <option value="報名中">報名中</option>
						  <option value="額滿截止">額滿截止</option>
						  <option value="整團取消">整團取消</option>
						  <option value="成團">成團</option>
					   </select> </td>
					</tr>
		</table>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="actId" value="<%= actVO.getActId()%>">
			<input type="submit" value="送出修改">
		</FORM>
	</div>
	
	
</body>


	


</html>