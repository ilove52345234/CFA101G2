<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>預訂客房類型</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="../images/x-icon" href="../images/ball.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/avt_item.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script src="<%=request.getContextPath()%>/front-end/js/avt-item.js"></script>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0"></script>
</head>

<body>



	<!--導覽列==========================================================================-->
	<!-- <header>
  
  </header>

  <br> -->
	<!--幻燈片===================================================================-->
	<div class="container-fluid">
		<div class="row">
			<div id="carousel-id" class="carousel slide" data-ride="carousel">
				<!-- 幻燈片小圓點區 -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-id" data-slide-to="0" class=""></li>
					<li data-target="#carousel-id" data-slide-to="1" class="active"></li>
				</ol>
				<!-- 幻燈片主圖區 -->
				<div class="carousel-inner">
					<div class="item">
						<img src="../images/3room2.png" align="right" alt=""
							class="img_item"> <img src="../images/3room.png"
							align="right" alt="" class="img_gray">
						<div class="top-left">
							<br> <br>

						</div>
						<div class="top-right"></div>
					</div>
					<div class="item active">
						<img src="../images/twingroom3.jpg" align="right" alt=""
							class="img_gray"> <img src="../images/twingroom2.png"
							align="right" alt="" class="img_item">
						<div class="top-left"></div>
						<div class="top-right">
							房型內容<br> <br> <br>
						</div>
					</div>
				</div>

				<!-- 換頁控制區 -->
				<a class="left carousel-control" href="#carousel-id"
					data-slide="prev"><span
					class="glyphicon glyphicon-chevron-left"></span></a> <a
					class="right carousel-control" href="#carousel-id"
					data-slide="next"><span
					class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</div>
	</div>

	<!--內容區===================================================================-->
	<div class="container content">
		<!--功能及麵包屑==========================================-->
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="#">房間專區</a></li>
				<li class="active">房間總覽</li>
			</ul>
		</div>

		<%--    <jsp:useBean id="rtSvc" scope="page" class="com.rmtype.model.RtService" /> --%>


		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="avt_title">
					<h2>
						<b>房間內容</b>
					</h2>
					<h5 class="avt_type">房間類型 :&nbsp;${rtVO.roomName}&nbsp;&nbsp;</h5>
				</div>
			</div>
		</div>


		<div class="avt_detail">
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<img
						src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}&action=show_pics"
						alt="Jane" style="width: 100%">
				</div>
				<div class="col-md-6 col-sm-12">
					<br>
					<p>
						&nbsp;<label>房間名稱 :</label>&nbsp;${rtVO.roomName}
					</p>
					<p>
						&nbsp;<label>房間費用 :</label>&nbsp;${rtVO.roomPrice}
					</p>
					<p style="color: red">
						&nbsp;<label>房間剩餘總數 :</label>&nbsp;${rtVO.roomTypeAmount}&nbsp;間
					</p>
					<br>
					<p>

						<%-- 錯誤表列 --%>
						<%
							List<String> errorMsgs = (List<String>) request.getAttribute("errorMsgs");
							if (errorMsgs.isEmpty() == false) {
						%>
						<font style="color: red">輸入錯誤:</font>
					<ul>
						<%
							for (int i = 0; i < errorMsgs.size(); i++) {
						%>
						<li style="color: red"><%=errorMsgs.get(i)%></li>
						<%
							}
						%>
					</ul>

					<%
						}
					%>

					</p>
					<p align="right">
					<div class="text-right more">
						<a href="#modal-id-super-activity-type-update" data-toggle="modal"
							class="btn btn-danger btn-lg" role="button" id="signUp">我要訂房</a>
						<!-- modal修改類型視窗 =======================================-->
						<div class="modal fade" id="modal-id-super-activity-type-update">

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/rmorder/RmorderServlet"
								style="margin-bottom: 0px;">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title">訂房</h4>
										</div>
										<div class="type_update" style="padding: 20px">
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3">&nbsp;房型名稱:</label>&nbsp;
													${rtVO.roomName}
												</div>
											</div>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3"> <font
														color='red'>*</font>&nbsp;預定房間數:
													</label>
													<div class="col-sm-9">
														<input type="text" name="roomNumber" id="roomNumber"
															class="form-control" value="1" />
													</div>
												</div>
											</div>
											<br> <br>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3"> <font
														color='red'>*</font>&nbsp;住房人數:
													</label>
													<div class="col-sm-9">
														<input type="text" name="memNumber" id="memNumber"
															class="form-control" value="1" />
													</div>
												</div>
											</div>
											<br> <br>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3" for="date"> <font
														color='red'>*</font>&nbsp;住房日期：
													</label>
													<div class="col-sm-9 room_date"  Style="display: flex;">
														<input type="date" id="roomDateStr" class="form-control short"
															name="checkInDate"> &nbsp;&nbsp;至&nbsp;&nbsp; <input
															type="date" id="roomDateEnd" class="form-control short"
															name="checkOutDate" />
													</div>
												</div>
											</div>
											<br>
											<div class="form-group">
												<div Style="text-align: left">
													&nbsp;&nbsp;&nbsp; <label><font color='red'>*</font>&nbsp;系統將帶入您的個人資料</label>&nbsp;
													<input type="checkbox" class="type_preview_check"
														name="getMemberData">
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<input type="hidden" name="roomCategoryId"
												value="${rtVO.roomCategoryId}"> <input type="hidden"
												name="action" value="getRoomOrder"> <input
												type="submit" class="btn btn-danger" value="確認">
										</div>
									</div>
								</div>
							</FORM>

						</div>
						<!-- modal修改類型視窗 =======================================-->
					</div>
					<br>
					<div class="text-right more">
						<a href="#modal-id-room_cart" data-toggle="modal"
							class="btn btn-info btn-lg" role="button" id="signUp">加入購物車</a>
						<!-- modal修改類型視窗 =======================================-->
						<div class="modal fade" id="modal-id-room_cart">

								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title">加入購物車</h4>
										</div>
										<div class="type_update" style="padding: 20px">
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3">&nbsp;房型名稱:</label>&nbsp;
													${rtVO.roomName}
												</div>
											</div>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3"> <font
														color='red'>*</font>&nbsp;預定房間數:
													</label>
													<div class="col-sm-9">
														<input type="text" name="roomNumber" id="room_cart_roomNumber"
															class="form-control" value="1" />
													</div>
												</div>
											</div>
											<br> <br>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3"> <font
														color='red'>*</font>&nbsp;住房人數:
													</label>
													<div class="col-sm-9">
														<input type="text" name="memNumber" id="room_cart_memNumber"
															class="form-control" value="1" />
													</div>
												</div>
											</div>
											<br> <br>
											<div class="form-group">
												<div Style="text-align: left">
													<label class="control-label col-sm-3" for="date"> <font
														color='red'>*</font>&nbsp;住房日期：
													</label>
													<div class="col-sm-9" Style="display: flex;">
														<input type="date" id="room_cart_date_str" class="form-control short"
															name="room_cart_checkInDate"> &nbsp;&nbsp;至&nbsp;&nbsp; <input
															type="date" id="room_cart_date_end" class="form-control short"
															name="room_cart_checkOutDate" />
													</div>
												</div>
											</div>
											<br>
											<div class="form-group">
												<div Style="text-align: left">
													&nbsp;&nbsp;&nbsp; <label><font color='red'>*</font>&nbsp;系統將帶入您的個人資料</label>&nbsp;
													<input type="checkbox" class="type_preview_check"
														name="room_cart_getMemberData" id="room_cart_checkMemberData">
												</div>
											</div>
										</div>
										<div class="modal-footer">
										     <input type="hidden" id="room_cart_roomCategoryId" value="${rtVO.roomCategoryId}">
											 <button type="button" class="btn btn-nfo" id="roomCartSubmit" >加入</button>
										</div>
									</div>
								</div>
						

						</div>
						<!-- modal修改類型視窗 =======================================-->
					</div>
					</p>
				</div>
			</div>
		</div>


		<div class="avt_detail">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/rmtype/RmtypeServlet"
				style="margin-bottom: 0px;">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<h3>房間介紹:</h3>
					</div>
					<div class="col-md-12 col-sm-12">
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${rtVO.roomTypeContent}</p>
					</div>
				</div>
			</FORM>

			<div class="row">
				<div class="col-md-12 col-sm-12">
					<h3>備註:</h3>
				</div>
				<div class="col-md-12 col-sm-12">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ‧假日、平日 附贈早餐
						‧茶包、盥洗包、頂級羽絨被、數位有線電視、電暖爐、乾濕分離衛浴、無線網路</p>
				</div>
			</div>
		</div>
		<div class="avt_detail">
			<div class="row">
				<div class="col-md-2 col-xs-2">
					<h3>房間圖片:</h3>
					<br> <img
						src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}&action=show_pics"
						alt="Jane" style="width: 100%" alt="" width="100%">
				</div>
				<div class="col-md-8 col-xs-10">

					<c:forEach var="rtpVO" items="${rtpVOs}" varStatus="s">
						<div class="mySlides">
							<div class="numbertext">${s.index+1}/${fn:length(rtpVOs)}</div>
							<img
								src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomPhotoId=${rtpVO.roomPhotoId}&action=show_many_pics"
								style="width: 97%">
						</div>
					</c:forEach>

					<a class="prev" onclick="plusSlides(-1)">❮</a> <a class="next"
						onclick="plusSlides(1)">❯</a> <br>
				</div>
				<div class="col-md-2 col-xs-12">
					<div class="ad">

						<c:forEach var="rtpVO" items="${rtpVOs}" varStatus="s">
							<div class="column">
								<img class="demo cursor"
									src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomPhotoId=${rtpVO.roomPhotoId}&action=show_many_pics"
									style="width: 100%" onclick="currentSlide(${s.index+1})"
									alt="The Woods">
							</div>
						</c:forEach>


					</div>
				</div>
			</div>
		</div>


	</div>

	<div class="row">
		<p align="center">
			<br> <a
				href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType"
				class="btn btn-primary " role="button">回到訂房列表</a>
		</p>
	</div>






	<!--底部平台介紹================================================================-->
	<footer> </footer>



	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    // ------------------ cart ---------------------
    $("#roomCartSubmit").click(function() {
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/rmorder/RmCartServlet",
			data : {
					  action : "add",
					  roomNumber : $("#room_cart_roomNumber").val(),
					  memNumber : $("#room_cart_memNumber").val(),
					  checkInDate : $("#room_cart_date_str").val(),
					  checkOutDate : $("#room_cart_date_end").val(),
					  getMemberData : $("#room_cart_checkMemberData").val(),
					  roomCategoryId : $("#room_cart_roomCategoryId").val()
			},
			success: function(req) {
				if(req==''){
					alert('已經加入購物車');
				}else{
					alert(req);
				}
				
				/* alert('已經加入購物車'); */
			},
			error : function(e) { 
				alert("異常"); 
		    } 
		})
	});
	//-----------------------------------------

    var today = new Date().toISOString().split('T')[0];
    document.getElementById("roomDateStr").setAttribute('min', today);
    document.getElementById("roomDateEnd").setAttribute('min', today);

    document.getElementById("room_cart_date_str").setAttribute('min', today);
    document.getElementById("room_cart_date_end").setAttribute('min', today);
    
	//-----------------------------------------

    var add_responses = document.getElementsByClassName("message_member_response");
    var buton_responses = document.getElementsByClassName("response");

    for (i = 0; i < buton_responses.length; i++) {
      buton_responses[i].addEventListener("click", myFunction);
    }

    function myFunction(e) {
      if (add_responses[e.target.id].style.display == 'none') {
        add_responses[e.target.id].style.display = 'block';
      } else {
        add_responses[e.target.id].style.display = 'none';
      }

    }

    // ----------------------------------------
    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
      showSlides(slideIndex += n);
    }

    function currentSlide(n) {
      showSlides(slideIndex = n);
    }

    function showSlides(n) {
      var i;
      var slides = document.getElementsByClassName("mySlides");
      var dots = document.getElementsByClassName("demo");
      var captionText = document.getElementById("caption");
      if (n > slides.length) { slideIndex = 1 }
      if (n < 1) { slideIndex = slides.length }
      for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
      }
      for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
      }
      slides[slideIndex - 1].style.display = "block";
      dots[slideIndex - 1].className += " active";
      captionText.innerHTML = dots[slideIndex - 1].alt;
    }

  </script>
</body>
</html>