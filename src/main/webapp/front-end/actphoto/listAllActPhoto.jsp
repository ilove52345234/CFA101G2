<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actphoto.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActPhotoService actPhotoSvc = new ActPhotoService();
	List<ActPhotoVO> list = actPhotoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有活動圖片 - listAllActPhoto.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>島旅-活動圖片</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/Home.jpg"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
                        	
	<table>
		<tr>
			<th>圖片編號</th>
			<th>活動編號</th>
			<th>活動圖片</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="actPhotoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actPhotoVO.actPhotoId}</td>
			<td>${actPhotoVO.actCategoryId}</td>
			<c:set var="actPhotoFile" value="${actPhotoVO.actPhotoFile}"></c:set> 
				<%byte b[] = (byte[]) pageContext.getAttribute("actPhotoFile");
 				String encode = null;
 				if (b != null) {
 					encode = Base64.encode(b);%>
 			<td><img src="data:image/jpg;base64,<%=encode%>" width="200" height="200" border="0"></td>
					<%}else{%><td></td><%}%>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>