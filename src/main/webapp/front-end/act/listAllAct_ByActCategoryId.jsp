<%@page import="com.actphoto.model.ActPhotoService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.act.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="listAllAct_ByActCategoryId" scope="request"
	type="java.util.Set<ActVO>" />
<jsp:useBean id="actTypeSvc" scope="page"
	class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actPhotoSvc" scope="page"
	class="com.actphoto.model.ActPhotoService" />
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<html>
<head>
<title>活動查看</title>
<meta charset="utf-8">
 
</head>
<%-- <jsp:include page="/front-end/header.jsp"></jsp:include> --%>
<body bgcolor='white'>

			<div class="intro">
				<h2 class="text-center">島旅</h2>
			</div>
<!-- 			<h4> -->
<!-- 				<a -->
<%-- 					href="<%=request.getContextPath()%>/front-end/act/select_page.jsp"><img --%>
<%-- 					src="<%=request.getContextPath()%>/front-end/images/Home.png" width="30" --%>
<!-- 					height="30" border="0">回首頁</a> -->
<!-- 			</h4> -->
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
			<th>活動編號</th>
			<th>活動內容</th>
			<th>活動開始日期</th>
			<th>活動結束日期</th>
			<th>活動狀態</th>
			<th>價格</th>
			<th>活動報名人數</th>
			<th>活動開始報名日期</th>
			<th>活動報名結束日期</th>
			<th>活動報名上限</th>
			<th>活動報名下限</th>
			<th>報名</th>
		</tr>
		<c:forEach var="actVO" items="${listAllAct_ByActCategoryId}">
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/actphoto/GetFirstActPhotoServlet?actCategoryId=${actVO.actCategoryId}"
					width="200" height="200" border="0"></td>
				<td>${actVO.actId}</td>
				<td>${actVO.actDescription}</td>
				<td><fmt:formatDate value="${actVO.actStart}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${actVO.actEnd}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${actVO.actStatus}</td>
				<td>${actVO.actFee}</td>
				<td>${actVO.applicants}</td>
				<td><fmt:formatDate value="${actVO.partStart}"
						pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${actVO.partEnd}"
						pattern="yyyy-MM-dd" /></td>
				<td>${actVO.actMaxPart}</td>
				<td>${actVO.actMinPart}</td>
				<td>
			 	 <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/act/ActServlet2" style="margin-bottom: 0px;" >
			     <input type="submit" value="報名">
			     <input type="hidden" name="actId"  value="${actVO.actId}">
			     <input type="hidden" name="action"	value="getOne_For_actId"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>