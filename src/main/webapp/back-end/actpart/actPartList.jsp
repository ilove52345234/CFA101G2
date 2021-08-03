<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ page import="com.act.model.*"%>
<%@ page import="com.actpart.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
<jsp:useBean id="actPartSvc" scope="page" class="com.actpart.model.ActPartService" />



<%
// HTTP 500 nullpointerexception
//request 取不到 actVO 
//1. @Servlet Session.setAttribute("actVO",actVO)  simple and easy;
//2. queryString?actId=Xxx;


	ActVO actVO = (ActVO) session.getAttribute("actVO");
			/* 在ActPartServlet 生成actVO後 session.setAttribute("actVO",actVO);
			   於 artPartList.jsp 以 session.getAttribute("actVO");
			   確保分頁其他頁面都可以access到 actVO.getActId() 取得actId來查找 參加該活動的actPart資訊*/
			   
	List<ActPartVO> list = actPartSvc.getAllPartsByActId(actVO.getActId());
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<meta charset="UTF-8">
<title>參加活動會員資料 - artPartList.jsp</title>

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


 <table id="table-1">
 	<tr><td>
		 <h3>參加活動會員資料 - artPartList.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/act/actBackendIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/act/images/home.png" width="30" height="30" border="0">回活動報名管理頁面</a></h4>
	</td></tr>
</table>


	<div>活動編號 : ${actVO.actId}</div>
<table>
	<tr>
		<th>會員編號</th>  
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>電子信箱</th>
		<th>活動報名日期</th>
		<th>確認繳費</th>
	<!-- 	<th>星號評分</th>
		<th>活動評論日期</th>
		<th>活動評論內文</th> -->
	</tr>
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	
	
	<%@ include file="page1.file" %>    <%--Use jsp. include to reuse the same block.--%>
	<c:forEach var="actPartVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
		<td>${actPartVO.memId}</td> 
		<td>${memSvc.getOneMem(actPartVO.getMemId()).memName}</td>
		<td>${memSvc.getOneMem(actPartVO.getMemId()).memPhone}</td>
		<td>${memSvc.getOneMem(actPartVO.getMemId()).memEmail}</td>
		<td>${actPartVO.actApplyDate}</td> 
		<td>${actPartVO.feeConfirm}</td> 
		<%-- <td>${actPartVO.actStar}</td>		
		<td>${actPartVO.actCommentDate}</td>
		<td>${actPartVO.actCommentText}</td>	 --%>
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

	
	
</body>
</html>