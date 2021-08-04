<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.carte.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
  	CarteVO carteVO = (CarteVO) request.getAttribute("carteVO");

	String memId = request.getParameter("memId");
	
	// -------------------Blob to Base64-------------------
	Blob image = carteVO.getUserPic();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	InputStream inputStream = image.getBinaryStream();
	byte[] buffer = new byte[inputStream.available()];
	int bytesRead = -1;

	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, bytesRead);
	}

	byte[] imageBytes = outputStream.toByteArray();
	String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	outputStream.flush();
	outputStream.close();
	inputStream.close();
	// -------------------Blob to Base64-------------------
%>
<html lang="en">
<head>



	<title>個人名片</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
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
		<div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
			<form class="login100-form validate-form" method="post" action="carte.do" name="form1" enctype="multipart/form-data">
				
				<span class="login100-form-title p-b-37">
					個人名片
				</span>

				
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li class="text-center" style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- 				<div> -->
<%-- 					<img src="data:image/jpg;base64,<%=base64Image %>" width="200px" /> --%>
<!-- 				</div> -->
				
<!-- 				<div class="text-right" > -->
<!-- 					<input type="file"  accept="image/*" name="userPic" id="file"  onchange="loadFile(event)" style="display: none;"> -->
<!--     				<label for="file" style="cursor: pointer;">上傳大頭貼</label> -->
<!--     				<img id="output" width="100" /> -->
<!-- 					<span class="focus-input100"></span> -->
<!-- 				</div> -->
				<div class="text-center" >
					<input type="file" onchange="readURL(this)" accept="image/*" name="userPic" id="file"  onchange="readURL(this)" style="display: none;" targetID="preview_progressbarTW_img">
    				<img id="preview_progressbarTW_img" src="data:image/jpg;base64,<%=base64Image %>" width="85%" />
    				<label for="file" style="cursor: pointer;">上傳大頭貼</label>
					<span class="focus-input100"></span>
				</div>
<!-- 	<div class="text-right" > -->
<%--    <img id="preview_progressbarTW_img" src="data:image/jpg;base64,<%=base64Image %>" width="200px"/> --%>
<!--    <input type="file" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png"/ > -->
<!-- 	</div> -->
				暱稱:
				<div class="wrap-input100 validate-input m-b-25" data-validate="暱稱請勿空白">
					<input class="input100" type="text" name="userName" size="45" value="<%=carteVO.getUserName()%>">
					<span class="focus-input100"></span>
				</div>
				最新更新時間:
				<div >
					<fmt:formatDate value="<%=carteVO.getUserUpdate()%>" pattern="yyyy-MM-dd hh:mm:ss" />
					<span class="focus-input100"></span>
				</div>

				<div class="container-login100-form-btn">
				<input type="hidden" name="action" value="updateCarte"> <!-- test -->
					<button class="login100-form-btn">
						修改
					</button>
				</div>
				
				<div class="text-right">
					<a href="<%=request.getContextPath()%>/front-end/frontEndPage.jsp" class="txt2 hov1">回首頁</a>
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
<!--===============================================================================================-->
<!-- <script> -->
<!--  var loadFile = function(event) { -->
<!-- 	var image = document.getElementById('output'); -->
<!--  	image.src = URL.createObjectURL(event.target.files[0]); -->
<!--  	}; -->
<!-- </script> -->
<script>
function readURL(input){

  if(input.files && input.files[0]){

    var imageTagID = input.getAttribute("targetID");

    var reader = new FileReader();

    reader.onload = function (e) {

       var img = document.getElementById(imageTagID);

       img.setAttribute("src", e.target.result)

    }

    reader.readAsDataURL(input.files[0]);

  }

}
</script>

</body>
</html>