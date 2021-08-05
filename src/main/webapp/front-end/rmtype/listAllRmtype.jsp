<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.rmtype.model.*"%>
<%@ page import="com.rmtypepic.model.*"%>       使用大吳範例<%？？？%>的值才需要使用
另外 目前這個網頁只用這個顯示功能 所以沒設action --%>
<!DOCTYPE html>
<%
/*  	RtService rtSvc = new RtService();
	List<RtVO> list = rtSvc.getAll();
	pageContext.setAttribute("list", list); */
	
/* 		 List<RtVO> list = (List<RtVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list);  */ 
 	
/*  	List<RtVO> list = (List<RtVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list);  
	  */
	
/* 	
  RtpService rtpSvc = new RtpService();
	List<RtpVO> listpic = rtpSvc.getAll();
	pageContext.setAttribute("listpic", listpic);  
	錯誤寫法  使用雙重迴圈 */
	
	 
%>
<!--
    第9行是直接創造RtService的物件 並且要import"com.rmtype.model.*"才不會有錯誤
    第10行抓的是RtService內的 getAll
    然後第11行則從第10行獲取List放到pageContext 透過${list}獲取RtVO內所有的值
    
    
  上面的三行抓的資料是listallrmtype的頁面 -->
  
 <!--  開啟路徑 使用get方法 http://localhost:8081/CFA101G2/rmtype/RmtypeServlet?action=getAllRoomType -->

<html>

<head>
<jsp:include page="/front-end/header.jsp"></jsp:include>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>訂房網站</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="short Icon" type="../images/x-icon" href="../images/ball.png">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/rm.css">

<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<script src="/CFA101G2/front-end/js/index.js"></script>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0"></script>
</head>

<body>

	<!--導覽列==========================================================================-->


	<br>
	<!--幻燈片===================================================================-->
	<div class="container-fluid">
		<div class="row">
			<div id="carousel-id" class="carousel slide" data-ride="carousel">
				<!-- 幻燈片小圓點區 0和1代表小圓點 active代表起始點-->
				<ol class="carousel-indicators">
					<li data-target="#carousel-id" data-slide-to="0" class=""></li>
					<li data-target="#carousel-id" data-slide-to="1" class="active"></li>
				</ol>
				<!-- 幻燈片主圖區 align圖片依靠方向-->
				<div class="carousel-inner">
					<div class="item">
						<img src="<%=request.getContextPath()%>/front-end/images/1.jpeg" align="right" alt="" class="img_item"> 
						<img src="<%=request.getContextPath()%>/front-end/images/1.jpeg" align="right" alt="" class="img_gray">
						<div class="top-left">
							<a href="avt-item.html" class="link_activity"> 優惠方案<br>
								110/8/17(二) 19:00<br> 單人房優惠
							</a>
						</div>
						<div class="top-right"></div>
					</div>
					<div class="item active">
						<img src="<%=request.getContextPath()%>/front-end/images/10.jpeg" align="right" alt="" class="img_gray">
						<img src="<%=request.getContextPath()%>/front-end/images/10.jpeg" align="right" alt="" class="img_item">
						<div class="top-left">
						
						</div>
						<div class="top-right">
							<a href="avt-item.html" class="link_activity"> 優惠方案<br>
								110/8/12(四) 10:00<br> 雙人房優惠<br>
							</a>
						</div>
					</div>
				</div>

				<!-- 換頁控制區 -->
				<a class="left carousel-control" href="#carousel-id"
					data-slide="prev">
			   <span class="glyphicon glyphicon-chevron-left"></span></a> 
				<a class="right carousel-control" href="#carousel-id"
					data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</div>
	</div>

	<!--內容區===================================================================-->
	<div class="container content">
		<!--功能及麵包屑==========================================-->
		<div class="row">
			<!--       <div class="col-md-12 col-sm-12 show_list">
            <ul class="nav nav-pills">
              <li><a href="#" class="show" style="background-color:#eee"><b>資料修改</b></a></li>
              <li><a href="#" class="show"><b>我的</b></a></li>
              <li><a href="#" class="show"><b>常見問題</b></a></li>
            </ul>
      </div> -->
			<!--  <div class="col-md-12 col-sm-12"> -->
			<div class="row">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmtype/RmtypeServlet" class="form-horizontal">
						<div class="search">
<!-- 						<label class="control-label" for="keyWord"> 
							<span class="glyphicon glyphicon-search"></span> 依關鍵字:&nbsp;</label>
							 <input class="search_input" type="text" name="keyWord" id="keyWord" value="" placeholder="輸入關鍵字">
								<input type="hidden" name="action" value="getOneRoomType">
								<input type="submit" class="btn btn-success" value="送出"> &nbsp;  -->
								<label class="control-label" for="keyWord"> 
								<span class="glyphicon glyphicon-search"></span> 依訂房類型:&nbsp;
							    </label> 
							<select name="roomName">
								<option value="">請選擇</option>
								<c:forEach var="rtVO" items="${roomTypeAllList}">
								<option value="${rtVO.roomName}">${rtVO.roomName}</option>
								</c:forEach>
							</select> &nbsp;&nbsp; 					
							<input type="hidden" name="action" value="find_roomname">
						    <input type="submit" class="btn btn-success" value="送出">
							<a href="<%=request.getContextPath()%>/front-end/rmtype/roomCarts.jsp"
							class="btn btn-default">查看購物車</a>
							<br> 
							<br>
						</div>
					</FORM>
			</div>

			
		</div>
		
	</div>

	<!-- 搜尋結果列表==========================================-->
	<!-- 顯示房型第一列圖片 -->
 	<div class="row">
<%--  	<%@ include file="page1.file" %>  --%>
		<c:forEach var="rtVO" items="${list}" > <!--  對應第10行 -->
			<div class="col-md-3 col-sm-12 activity">
				<div class="activity_card">
					<img src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}&action=show_pics" alt="Jane" style="width: 100%">  <!-- 參照p49 和RtpServlet-->
					<div class="container">
						<h3>${rtVO.roomName}</h3>
						<p>一晚價格:${rtVO.roomPrice}元</p>
						<p>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmtype/RmtypeServlet" style="margin-bottom: 0px;">
						<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getOneRoomType" style="margin-bottom: 0px;"> --%>
						                    <!-- 對應controller的路徑 -->  <!--    若想要用同一個servlet可以整個頁面這樣設路徑避免都被濾器影響 -->
							<input type="submit" class="activity_detail" value="詳細內容">
																			<!-- 點進詳細內容進入RmtyprServlet 對應getOneRoomType 並進入2.開始查詢資料-->
							<input type="hidden" name="roomCategoryId" value="${rtVO.roomCategoryId}"> 
							                                    <!-- 把要查詢的房型id放到詳細內容做查詢 -->
							<input type="hidden" name="action" value="getRoomDetail">
							                                      <!--  對應controller第38行  -->
						</FORM>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
<%--  <%@ include file="page2.file" %> --%>
<!--  測試用 -->
<%--  <label>request.getContextPath():<%=request.getContextPath()%></label> --%>
	<!-- ======================== -->
<%-- 	<div class="row">
		 <!-- 錯誤的方法 因為使用兩張table跑兩個迴圈(rtpVO和rtVO)會有資料結構上的問題
		      最好使用一層迴圈就可以完事  資料結構上 最好是以一個表格內的資料colume對應到另外一張表的id
		      才能準確地對到值 
              例如table:ROOM_TYPE_PIC內的ROOM_PHOTO對應到table:ROOM_TYPE內的ROOM_CATEGORY_ID
              才可以準確的對應到該單人房的ID對應到單人房的圖片資料-->
		  <c:forEach var="rtpVO" items="${listpic}"> 
			<div class="col-md-3 col-sm-12 activity">
				<div class="activity_card">
				 <c:forEach var="rtpVO" items="${listpic}"> 
					<img src="<%=request.getContextPath()%>/RtpServlet?roomCategoryId=${rtpVO.roomCategoryId}" alt="Jane" style="width: 100%">
					</c:forEach> 
					<c:forEach var="rtVO" items="${list}"> 
					<div class="container">
						<h3>${rtVO.roomName}</h3>
						<p>一晚價格:${rtVO.roomPrice}元</p>
						<p>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RmtypeServlet" style="margin-bottom: 0px;">
						                    <!-- 對應controller的路徑 -->
							<input type="submit" class="activity_detail" value="詳細內容">
							<input type="hidden" name="roomCategoryId" value="${rtVO.roomCategoryId}"> 
							                                    <!-- 把要查詢的房型id放到詳細內容做查詢 -->
							<input type="hidden" name="action" value="getOneRoomType">
							                                      <!--  對應controller第38行  -->
						</FORM>
						</p>
					</div>
					</c:forEach> 
				</div>
			</div>
	</c:forEach> 
	</div>

================================ --%>





	<!-- 上下頁及選擇頁數 -->
	<!--     <div class="text-center">
      <ul class="pagination ">
        <li><a href="#">&laquo;</a></li>
        <li class="active"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">&raquo;</a></li>
      </ul>
    </div>

  </div> -->


  <script src="https://code.jquery.com/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!--底部平台介紹================================================================-->
	
</body>
</html>
