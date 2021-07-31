<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%

  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
%>

<html>
<head>
<title>會員資料</title>



</head>
<style>
		body {
			background-image: url('images/backGround.png');
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: 100% 100%;
			/* opacity: 0.5; */
			}
			
		div {
			width: 500px;
			height: 450px;
			color: darkblue;
			letter-spacing: 2px;
			line-height: 26px;
			background-color: lightcyan;
			position: absolute;
			left: 50%;
			top: 50%;
			margin-top: -225px;
			margin-left: -250px;
			border-radius: 10px;
		}
		[type="submit"] {
			border-radius: 10px;
			background-color: pink;
			font-family: DFKai-sb;
		}
		table {
			width: 500px;
			margin-top: 5px;
			margin-bottom: 5px;
			text-align: center;
		}
		th, td {
			text-align: center;
			border: 1px solid #CCCCFF;
		}
</style>
<body bgcolor='white'>

<div>

<table>
<h3 style="text-align:center;">會員資料</h3>
	<tr>
		<td>會員編號:</td>
		<td><%=memVO.getMemId()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><%=memVO.getMemAccount()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><%=memVO.getMemName()%></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><%=memVO.getMemPassword()%></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><%=memVO.getMemAddress()%></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><%=memVO.getMemPhone()%></td>
	</tr>
	<tr>
		<td>身分證字號:</td>
		<td><%=memVO.getMemUid()%></td>
	</tr>
	<tr>
		<td>電子信箱:</td>
		<td><%=memVO.getMemEmail()%></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><%=memVO.getMemSex()%></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><%=memVO.getMemDob()%></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><%=memVO.getMemStatus() == 1 ? "啟用" : "未啟用" %></td>
	</tr>
	<tr>
		<td>更新時間:</td>
		<td><fmt:formatDate value="<%=memVO.getMemUpdate()%>" pattern="yyyy-MM-dd hh:mm:ss" /></td>
	</tr>


</table>
<h4 style="text-align:center;"><a href="select_page.jsp">回首頁</a></h4>
</div>

</body>
</html>