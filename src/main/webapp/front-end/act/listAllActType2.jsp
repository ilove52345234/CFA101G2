<%@page import="com.actphoto.model.ActPhotoService"%>
<%@page import="com.acttype.model.ActTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="com.acttype.model.*"%>
<%@ page import="com.actphoto.model.*"%>

<%
	ActPhotoService actPhotoService = new ActPhotoService();

	ActTypeService actTypeService = new ActTypeService();

	List<ActTypeVO> list = actTypeService.getAll();
	pageContext.setAttribute("list", list);
	List<ActPhotoVO> photoList = null;
	for (ActTypeVO atvo : list) {
		photoList = actPhotoService.getAllByActCategoryId(atvo.getActCategoryId());
	}
	pageContext.setAttribute("photoList", photoList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>瀏覽活動類別</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/Article-List.css">
<link rel="stylesheet" href="../css/styles.css">
</head>

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
<jsp:include page="/front-end/header.jsp"></jsp:include>
<body bgcolor='white'>
	<div class="article-list">
		<div class="container">
<!-- 			<div class="intro"> -->
<!-- 				<h2 class="text-center">島旅</h2> -->
<!-- 			</div> -->
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

			<jsp:useBean id="actPhotoSvc" scope="page"
				class="com.actphoto.model.ActPhotoService" />
			<jsp:useBean id="actTypeSvc" scope="page"
				class="com.acttype.model.ActTypeService" />

<%-- 			<%@ include file="page1.file"%> --%>
			<c:forEach var="ActTypeVO" items="${list}">
				<div class="float-left" class="row articles">
					<div class="col-md-4 col-sm-6 item">
						<a href="#"><img
							src="<%=request.getContextPath()%>/actphoto/GetFirstActPhotoServlet?actCategoryId=${ActTypeVO.actCategoryId}"
							width="360" height="240" border="0"></a>
						<h3 class="name">${ActTypeVO.actCategoryName}</h3>
						<p style="text-indent: 2em" Align=left class="description">${ActTypeVO.actCategoryDesc}</p>
						<br>
						<p align=right class="actfee">活動費用：${ActTypeVO.actFee}</p>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet2"
							style="margin-bottom: 0px;">
							<input type="submit" value="查看"> <input type="hidden"
								name="actCategoryId" value="${ActTypeVO.actCategoryId}">
							<input type="hidden" name="action"
								value="listAllAct_ByActCategoryId">
						</FORM>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script src="../js/jquery.min.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>