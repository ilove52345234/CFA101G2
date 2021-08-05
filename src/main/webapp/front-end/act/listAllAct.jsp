<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.actphoto.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActService actSvc = new ActService();
	List<ActVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="actPhotoSvc" scope="page" class="com.actphoto.model.ActPhotoService" />

<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>

<style>
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
				<h3>島旅-活動區</h3>
				<h4>
					<a href="select_page.jsp"><img
						src="<%=request.getContextPath()%>/images/Home.png"  width="100"
						height="100" border="0"></a>
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
			<th>活動圖片</th>
			<th>活動名稱</th>
			<th>活動開始日期</th>
			<th>活動結束日期</th>
			<th>活動狀態</th>
			<th>價格</th>
			<th>活動報名人數</th>
			<th>活動開始報名日期</th>
			<th>活動報名結束日期</th>
			<th>報名</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
 				<td><img src="<%=request.getContextPath()%>/actphoto/GetFirstActPhotoServlet?actCategoryId=${ActTypeVO.actCategoryId}"></td>
				<td width="800px">${actVO.actDescription}</td>
				<td width="300px"><fmt:formatDate value="${actVO.actStart}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td width="300px"><fmt:formatDate value="${actVO.actEnd}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${actVO.actStatus}</td>
				<td>${actVO.actFee}</td>
				<td>${actVO.applicants}</td>
				<td><fmt:formatDate value="${actVO.partStart}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td><fmt:formatDate value="${actVO.partEnd}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>
			 	 <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/act/act.do" style="margin-bottom: 0px;" >
			     <input type="submit" value="報名">
			     <input type="hidden" name="actId"  value="${actVO.actId}">
			     <input type="hidden" name="action"	value="getOne_For_actId"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>