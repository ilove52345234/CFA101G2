<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<html lang="en">
<head>
<style>

.box {
  width: 550px;
  display: flex;
  justify-content: space-between;
}
.a, .b, .c {
  width: 180px;
  height: 100px;
}
</style>

<title>個人資料修改</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>


	<div class="container-login100" style="background-image: url('images/backGround.png');">
		<div class="wrap-login100-regist p-l-55 p-r-55 p-t-80 p-b-30">
			<form class="login100-form validate-form" method="post" action="mem.do" name="form1">
				<span class="login100-form-title p-b-37"> 個人資料 </span>
<div class='box'>
			<div class='a'>帳號:
					<input class="input100" type="hidden" name="memAccount" size="25" value="<%=memVO.getMemAccount()%>">
					<span class="input100"><%=memVO.getMemAccount()%></span>
				</div>
			</div>
			<div class='b'>密碼:
				<div class="wrap-input100 validate-input m-b-25" data-validate="密碼請勿空白">
					<input class="input100" type="hidden" name="memPassword" size="25" value="<%=memVO.getMemPassword()%>">
					<span class="input100"><%=memVO.getMemPassword()%></span>
				</div>
			</div>
			<div class='c'>姓名:
				<div class="wrap-input100 validate-input m-b-20" data-validate="姓名請勿空白">
					<input class="input100" type="hidden" name="memName" size="25" value="<%=memVO.getMemName()%>">
					<span class="input100"><%=memVO.getMemName()%></span>
				</div>
			</div>
</div>
<div class='box'>
			<div class='a'>地址:
				<div class="wrap-input100 validate-input m-b-20" data-validate="地址請勿空白">
					<input class="input100" type="hidden" name="memAddress" size="25"	value="<%=memVO.getMemAddress()%>">
					<span class="input100"><%=memVO.getMemAddress()%></span>
				</div>
			</div>
			<div class='b'>電話:
				<div class="wrap-input100 validate-input m-b-20" data-validate="電話請勿空白">
					<input class="input100" type="hidden" name="memPhone" size="25"	value="<%=memVO.getMemPhone()%>">
					<span class="input100"><%=memVO.getMemPhone()%></span>
				</div>
			</div>
			<div class='c'>身分證字號:
				<div class="wrap-input100 validate-input m-b-20" data-validate="身分證字號請勿空白">
					<input class="input100" type="hidden" name="memUid" size="25"	value="<%=memVO.getMemUid()%>">
					<span class="input100"><%=memVO.getMemUid()%></span>
				</div>
			</div>
</div>

<div class='box'>
			<div class='a'>信箱:
				<div class="wrap-input100 validate-input m-b-20" data-validate="信箱請勿空白">
					<input class="input100" type="hidden" name="memEmail" size="25"	value="<%=memVO.getMemEmail()%>">
					<span class="input100"><%=memVO.getMemEmail()%></span>
				</div>
			</div>
			<div class='b'>性別:
				<div class="wrap-input100 validate-input m-b-20" data-validate="性別請勿空白">
					<input class="input100" type="hidden" name="memSex" size="25"	value="<%=memVO.getMemSex()%>">
					<span class="input100"><%=memVO.getMemSex()%></span>
				</div>
			</div>
			<div class='c'>生日:
				<div class="wrap-input100 validate-input m-b-20" data-validate="生日請勿空白">
					<input class="input100" name="memDob" id="f_date1" type="hidden" value="<%=memVO.getMemDob()%>">
					<span class="input100"><%=memVO.getMemDob()%></span>
				</div>
			</div>	
</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

				<div class="container-login100-form-btn">
					<input type="hidden" name="memStatus" value="<%=memVO.getMemStatus()%>" /><!-- test -->
					<input type="hidden" name="memId" value="<%=memVO.getMemId()%>"><!-- test -->
					<input type="hidden" name="memUpdate" /><!-- test -->
					<input type="hidden" name="action" value="getOne_For_Update2"><!-- test -->
					<button class="login100-form-btn">送出修改</button>
				</div>
				
			</form>

				<div class="text-right">
				<form method="post" action="<%=request.getContextPath()%>/carte/carte.do" name="form2">
					   <input type="hidden" name="action" value="showCarte">
       					<input type="submit" value="查看個人名片">
				</form>

					<a href="HomePage.jsp" class="txt2 hov1">回首頁</a>
				</div>
		</div>
	</div>



	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>