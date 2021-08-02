<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>查看所有會員資料 </title>
<style>
  	table{
		border: 1px solid #66c;
		width: 90%;
		margin: 50px auto;
		border-collapse: collapse;
		background-color: #CCCCFF !important;
	}
	table td,table th{
		border: 1px solid black;

	}
	table th{
		color: white;
		background-color: orange;
	}
	body {
		background-image: url('/CFA101G2/back-end/mem/images/backGround.png');
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-size: 100% 100%;
		/* opacity: 0.5; */
	}
	input{
		border-radius: 10px;
		background-color: pink;
		font-family: DFKai-sb;
	}
</style>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="/CFA101G2/back-end/js/include.js"></script>
</head>
<body bgcolor='lightcyan'>

<div id="header"></div>
<table id="table-1" style="margin-top: 0px">
	<tr><td>
		 <h3 style="text-align:center;">查看所有會員資料</h3>
		 <h4 style="text-align:center;"><a href="/CFA101G2/back-end/mem/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員姓名</th>
		<th>密碼</th>
		<th>地址</th>
		<th>電話</th>
		<th>身分證字號</th>
		<th>電子信箱</th>
		<th>性別</th>
		<th>生日</th>
		<th>會員狀態</th>
		<th>更新時間</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memVO.memId}</td>
			<td>${memVO.memAccount}</td>
			<td>${memVO.memName}</td>
			<td>${memVO.memPassword}</td>
			<td>${memVO.memAddress}</td>
			<td>${memVO.memPhone}</td> 
			<td>${memVO.memUid}</td>
			<td>${memVO.memEmail}</td>
			<td>${memVO.memSex}</td>
			<td>${memVO.memDob}</td>
			<td>${memVO.memStatus == 1 ? "啟用" : "未啟用" }</td>
			<td><fmt:formatDate value="${memVO.memUpdate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memId"  value="${memVO.memId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>