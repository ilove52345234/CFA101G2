<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actphoto.model.*"%>
<%
  	ActPhotoVO actPhotoVO = (ActPhotoVO) request.getAttribute("actPhotoVO");
%>
<html>
<head>
<title>活動圖片資料 - listOneActPhoto.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動圖片資料 - ListOneActPhoto.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/Home.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動圖片</th>
		<th>活動圖片編號</th>
		<th>活動類別編號</th>
	</tr>
	<tr>
		<c:set var="actPhotoFile" value="${actPhotoVO.actPhotoFile}"></c:set> 
				<%byte b[] = (byte[]) pageContext.getAttribute("actPhotoFile");
 				String encode = null;
 				if (b != null) {
 					encode = Base64.encode(b);%>
 				<td><img src="data:image/jpg;base64,<%=encode%>" width="200" height="200" border="0"></td>
					<%}else{%><td></td><%}%>
		<td>${actPhotoVO.actId}</td>
		<td>${actPhotoVO.actCategoryId}</td>
	</tr>
</table>

</body>
</html>