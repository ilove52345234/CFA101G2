<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
	if(memVO == null){
		memVO = new MemVO();
	}
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

<title>個人資料查看</title>
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
			<form class="login100-form validate-form" method="post" action="/CFA101G2/front-end/mem/mem.do" name="form1">
				<span class="login100-form-title p-b-37"> 個人資料 </span>
<div class='box'>
			<div class='a'>帳號:
				<div class="wrap-input100 validate-input m-b-20" data-validate="帳號請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemAccount()%></span>
				</div>
			</div>
			<div class='b'>密碼:
				<div class="wrap-input100 validate-input m-b-25" data-validate="密碼請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemPassword()%></span>
				</div>
			</div>
			<div class='c'>姓名:
				<div class="wrap-input100 validate-input m-b-20" data-validate="姓名請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemName()%></span>
				</div>
			</div>
</div>
<div class='box'>
			<div class='a'>地址:
				<div class="wrap-input100 validate-input m-b-20" data-validate="地址請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemAddress()%></span>
				</div>
			</div>
			<div class='b'>電話:
				<div class="wrap-input100 validate-input m-b-20" data-validate="電話請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemPhone()%></span>
				</div>
			</div>
			<div class='c'>身分證字號:
				<div class="wrap-input100 validate-input m-b-20" data-validate="身分證字號請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemUid()%></span>
				</div>
			</div>
</div>

<div class='box'>
			<div class='a'>信箱:
				<div class="wrap-input100 validate-input m-b-20" data-validate="信箱請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemEmail()%></span>
				</div>
			</div>
			<div class='b'>性別:
				<div class="wrap-input100 validate-input m-b-20" data-validate="性別請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemSex()%></span>
				</div>
			</div>
			<div class='c'>生日:
				<div class="wrap-input100 validate-input m-b-20" data-validate="生日請勿空白">
					<span class="input100" style="text-decoration:underline;"><%=memVO.getMemDob()%></span>
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

				
			</form>

				
<div class="container-login100-form-btn">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do" style="margin-bottom: 0px;">
	   <input type="hidden" name="action" value="getOne_For_Update2">
    	<button class="login100-form-btn">修改</button>
    </FORM>
</div>
<br>
<div class="container-login100-form-btn">
				<form method="post" action="<%=request.getContextPath()%>/carte/carte.do" name="form2">
					   <input type="hidden" name="action" value="showCarte">
       					<button class="login100-form-btn">查看個人名片</button>
				</form>
</div>

				
				
				<div class="text-right">
					<a href="" class="txt2 hov1">查看個人訂單</a><br>
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