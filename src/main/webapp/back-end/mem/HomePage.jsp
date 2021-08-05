<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>HomePage</title>
</head>
<body>
<p>這是首頁</p>
<p1>已是會員<a href="index.jsp">登入</a></p1><br>
<p3>修改會員資料<a href="select_page.jsp">修改(後台用)</a></p3><br>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>web.xml" style="margin-bottom: 0px;">
	   <input type="hidden" name="action" value="getOne_For_Display2">
       <input type="submit" value="個人資料">
    </FORM>
    <br>
    <form method="post" action="/CFA101G2/front-end/mem/mem.do" name="form1">
<input type="hidden" name="action" value="logout" />
<input type="submit" value="登出" />
<p>新增個人名片<a href="<%=request.getContextPath()%>/carte/addCarte2.jsp">新增</a></p><br>
</body>
</html>