<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
    MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
	if(memVO == null){
		memVO = new MemVO();
	}

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>後台會員資料修改</title>`

<style>
		body {
			background-image: url('/CFA101G2/back-end/mem/images/backGround.png');
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: 100% 100%;
			/* opacity: 0.5; */
			}
			
		#mem {
			width: 500px;
			height: auto;
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
		[type="TEXT"] {
			border:1px; 
			border-bottom-style: solid;
			border-top-style: none;
			border-left-style:none;
			border-right-style:none;
			font-family: DFKai-sb;
			font-size: 16px;
			background-color: lightcyan;
		}
		td {
			font-weight:bold;
			font-family:DFKai-sb;
		}
</style>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="/CFA101G2/back-end/js/include.js"></script>

</head>
<body bgcolor='white'>

<div id="header"></div>


<div id="mem">
<h3 style="text-align:center;">後台會員資料修改</h3>
<h4 style="text-align:center;"><a href="/CFA101G2/back-end/mem/select_page.jsp">回首頁</a></h4>
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
</c:if>


<FORM METHOD="post" ACTION="/CFA101G2/front-end/mem/mem.do" name="form1">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemId()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="memAccount" size="45" value="<%=memVO.getMemAccount()%>" /></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="memName" size="45" value="<%=memVO.getMemName()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="memPassword" size="45" value="<%=memVO.getMemPassword()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="memAddress" size="45"	value="<%=memVO.getMemAddress()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="memPhone" size="45"	value="<%=memVO.getMemPhone()%>" /></td>
	</tr>
	<tr>
		<td>身分證字號:</td>
		<td><input type="TEXT" name="memUid" size="45"	value="<%=memVO.getMemUid()%>" /></td>
	</tr>
	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="memEmail" size="45"	value="<%=memVO.getMemEmail()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="memSex" size="45"	value="<%=memVO.getMemSex()%>" /></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="memDob" type="TEXT" size="45" value="<%=memVO.getMemDob()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="memStatus" size="45"	value="<%=memVO.getMemStatus() %>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memId" value="<%=memVO.getMemId()%>">
<input type="submit" value="送出修改"></FORM>

</div>
</body>

<script>

</script>


</html>