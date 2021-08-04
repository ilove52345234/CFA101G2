<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
	if(memVO == null){
		memVO = new MemVO();
	}
%>
<!DOCTYPE html>
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

<title>島旅註冊</title>
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

				<span class="login100-form-title p-b-37"> 註冊 </span>
<div class='box'>
			<div class='a'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="帳號請勿空白">
					<input class="input100" type="text" name="memAccount" placeholder="請輸入帳號">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='b'>
				<div class="wrap-input100 validate-input m-b-25" data-validate="密碼請勿空白">
					<input class="input100" type="password" name="memPassword" placeholder="請輸入密碼">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='c'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="姓名請勿空白">
					<input class="input100" type="text" name="memName" placeholder="請輸入姓名">
					<span class="focus-input100"></span>
				</div>
			</div>
</div>
<div class='box'>
			<div class='a'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="地址請勿空白">
					<input class="input100" type="text" name="memAddress" placeholder="請輸入地址">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='b'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="電話請勿空白">
					<input class="input100" type="text" name="memPhone" placeholder="請輸入電話號碼">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='c'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="身分證字號請勿空白">
					<input class="input100" type="text" name="memUid" placeholder="請輸入身分證字號">
					<span class="focus-input100"></span>
				</div>
			</div>
</div>

<div class='box'>
			<div class='a'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="信箱請勿空白">
					<input class="input100" type="text" name="memEmail" placeholder="請輸入信箱">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='b'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="性別請勿空白">
					<input class="input100" type="text" name="memSex" placeholder="請輸入性別">
					<span class="focus-input100"></span>
				</div>
			</div>
			<div class='c'>
				<div class="wrap-input100 validate-input m-b-20" data-validate="生日請勿空白">
					<input class="input100" type="text" name="memDob" id="f_date1" placeholder="生日 西元年-月-日">
					<span class="focus-input100"></span>
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
					<input type="hidden" name="memStatus" value="1" /><!-- test -->
					<input type="hidden" name="memUpdate" /><!-- test -->
					<input type="hidden" name="action" value="insert" /><!-- test -->
					<button class="login100-form-btn">註冊</button>
				</div>
			</form>
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
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
</html>