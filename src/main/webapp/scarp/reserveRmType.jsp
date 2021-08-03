<%@page import="com.rmtype.model.RtVO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
  <title>預訂客房類型</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="short Icon" type="../images/x-icon" href="../images/ball.png">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/avt_item.css">
  <!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

  <script src="../js/avt-item.js"></script>
  <div id="fb-root"></div>
  <script async defer crossorigin="anonymous"
    src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0"></script>
</head>

<body>



  <!--導覽列==========================================================================-->
  <!-- <header>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="navbar-brand" href="#" id="navbar-title"><img src="../assets/images/ball.png" width="30px"
              id="imgTop" class="pull-left"><b>訂房網站</b></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a class="dropdown-toggle nav_title" data-toggle="dropdown" href="#"
                style="background-color:#0000AA;border-color: #0000AA;color:white;">活動專區
                <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">活動總覽</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle nav_title" data-toggle="dropdown" href="#">社員專區
                <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="../member/mem.html">社員資料修改</a></li>
                <li><a href="../member/mem_avt.html">我的活動</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle nav_title" data-toggle="dropdown" href="#">活動管理
                <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="../manager/activity/new.html">我的活動管理</a></li>
                <li><a href="../manager/check.html">活動審核</a></li>
                <li><a href="../manager/member.html">社員管理</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle nav_title" data-toggle="dropdown" href="#">活動最高管理
                <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="../super_manager/sys_management/function.html">系統管理</a></li>
                <li><a href="../super_manager/admin_manager/qualification.html">管理員管理</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 社員登入<b></b></a></li>
          </ul>
        </div>
      </div>
    </nav>
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
            <img src="../images/3room2.png" align="right" alt="" class="img_item">
            <img src="../images/3room.png" align="right" alt="" class="img_gray">
            <div class="top-left">
              <br>
              <br>

            </div>
            <div class="top-right">

            </div>
          </div>
          <div class="item active">
            <img src="../images/twingroom3.jpg" align="right" alt="" class="img_gray">
            <img src="../images/twingroom2.png" align="right" alt="" class="img_item">
            <div class="top-left">

            </div>
            <div class="top-right">
              房型內容<br>
              <br>
              <br>
            </div>
          </div>
        </div>

        <!-- 換頁控制區 -->
        <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span
            class="glyphicon glyphicon-chevron-left"></span></a>
        <a class="right carousel-control" href="#carousel-id" data-slide="next"><span
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
          <h2><b>房間內容</b></h2>
          <h5 class="avt_type">房間類型 :&nbsp;${rtVO.roomName}&nbsp;&nbsp;</h5>
        </div>
      </div>
    </div>
    

    <div class="avt_detail">
      <div class="row">
        <div class="col-md-6 col-sm-12">
          <img src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}" alt="Jane" style="width: 100%">
        </div>
        <div class="col-md-6 col-sm-12">
          <br>
          <p>&nbsp;<label>房間名稱 :</label>&nbsp;${rtVO.roomName}</p>
          <p>&nbsp;<label>房間費用 :</label>&nbsp;${rtVO.roomPrice}</p>
          <p style="color: red">&nbsp;<label>房間剩餘總數 :</label>&nbsp;${rtVO.roomTypeAmount}&nbsp;間</p>
          <br>
          <p>

<%-- 錯誤表列 --%>
<% 
  List<String> errorMsgs = (List<String>) request.getAttribute("errorMsgs");
  if(errorMsgs.isEmpty()==false){
%>
	<font style="color:red">輸入錯誤:</font>
	<ul>
		<% for(int i=0;i<errorMsgs.size();i++){ %>
			<li style="color:red"><%= errorMsgs.get(i) %></li>
		<% } %>
	</ul>

<% } %>
          
          </p>
          <p align="right">
          <div class="text-right more">
            <a href="#modal-id-super-activity-type-update" data-toggle="modal" class="btn btn-danger btn-lg"
              role="button" id="signUp">我要訂房</a>
            <!-- modal修改類型視窗 =======================================-->
            <div class="modal fade" id="modal-id-super-activity-type-update">
            
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmorder/RmorderServlet" style="margin-bottom: 0px;">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">訂房</h4>
                  </div>
                  <div class="type_update" style="padding: 20px">
                    <div class="form-group">
                      <div Style="text-align:left">
                        <label class="control-label col-sm-3">&nbsp;房型名稱:</label>&nbsp;
                        ${rtVO.roomName}
                      </div>
                    </div>
                    <div class="form-group">
                      <div Style="text-align:left">
                        <label class="control-label col-sm-3">
                          <font color='red'>*</font>&nbsp;預定房間數:
                        </label>
                        <div class="col-sm-9">
                          <input type="text" name="roomNumber" id="roomNumber" class="form-control" value="1" />
                        </div>
                      </div>
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                      <div Style="text-align:left">
                        <label class="control-label col-sm-3">
                          <font color='red'>*</font>&nbsp;住房人數:
                        </label>
                        <div class="col-sm-9">
                          <input type="text" name="memNumber" id="memNumber" class="form-control" value="1" />
                        </div>
                      </div>
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                      <div Style="text-align:left">
                        <label class="control-label col-sm-3" for="date">
                          <font color='red'>*</font>&nbsp;住房日期：
                        </label>
                        <div class="col-sm-9" id="room_date">
                          <input type="date" class="form-control short" name="checkInDate"/>
                          &nbsp;&nbsp;至&nbsp;&nbsp;
                          <input type="date" class="form-control short" name="checkOutDate"/>
                        </div>
                      </div>
                    </div>
                    <br>
                    <div class="form-group">
                      <div Style="text-align:left">
                        &nbsp;&nbsp;&nbsp;
                        <label><font color='red'>*</font>&nbsp;系統將帶入您的個人資料</label>&nbsp;
                        <input type="checkbox" class="type_preview_check" name="getMemberData">
                      </div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <input type="hidden" name="roomCategoryId" value="${rtVO.roomCategoryId}">
                    <input type="submit" class="btn btn-info" value="確認">
                  </div>
                </div>
              </div>
              </FORM>
              
            </div>
            <!-- modal修改類型視窗 =======================================-->　
          </div>
          </p>
        </div>
      </div>
    </div>


    <div class="avt_detail">
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rmtype/RmtypeServlet" style="margin-bottom: 0px;">
      <div class="row">
        <div class="col-md-12 col-sm-12">
          <h3>房間介紹:</h3>
        </div>
        <div class="col-md-12 col-sm-12">
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          ${rtVO.roomTypeContent}
          </p>
        </div>
      </div>
      </FORM>

      <div class="row">
        <div class="col-md-12 col-sm-12">
          <h3>備註:</h3>
        </div>
        <div class="col-md-12 col-sm-12">
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ‧假日、平日 附贈早餐
            ‧茶包、盥洗包、頂級羽絨被、數位有線電視、電暖爐、乾濕分離衛浴、無線網路
          </p>
        </div>
      </div>
    </div>
        <div class="avt_detail">
        <div class="row">
          <div class="col-md-2 col-xs-2">
              <h3>房間圖片:</h3>
              <br>
              <img src="<%=request.getContextPath()%>/rmtypepic/RtpServlet?roomCategoryId=${rtVO.roomCategoryId}" alt="Jane" style="width: 100%" alt="" width="100%">
          </div>
          <div class="col-md-8 col-xs-10">
            <div class="mySlides">
              <div class="numbertext">1 / 5</div>
              <img src="../assets/images/hiking.jpeg" style="width:97%">
            </div>
            <div class="mySlides">
              <div class="numbertext">2 / 5</div>
              <img src="../assets/images/menu.png" style="width:97%">
            </div>
            <div class="mySlides">
              <div class="numbertext">3 / 5</div>
              <img src="../assets/images/game1.jpg" style="width:97%">
            </div>
            <div class="mySlides">
              <div class="numbertext">4 / 5</div>
              <img src="../assets/images/menu.png" style="width:97%">
            </div>
            <div class="mySlides">
              <div class="numbertext">5 / 5</div>
              <img src="../assets/images/hiking.jpeg" style="width:97%">
            </div>
            <a class="prev" onclick="plusSlides(-1)">❮</a>
            <a class="next" onclick="plusSlides(1)">❯</a>
            <br>
          </div>
          <div class="col-md-2 col-xs-12">
            <div class="ad">
              <div class="column">
                <img class="demo cursor" src="../assets/images/hiking.jpeg" style="width:100%" onclick="currentSlide(1)"
                  alt="The Woods">
              </div>
              <div class="column">
                <img class="demo cursor" src="../assets/images/menu.png" style="width:100%" onclick="currentSlide(2)"
                  alt="Cinque Terre">
              </div>
              <div class="column">
                <img class="demo cursor" src="../assets/images/game1.jpg" style="width:100%" onclick="currentSlide(3)"
                  alt="Mountains and fjords">
              </div>
              <div class="column">
                <img class="demo cursor" src="../assets/images/menu.png" style="width:100%" onclick="currentSlide(4)"
                  alt="Northern Lights">
              </div>
              <div class="column">
                <img class="demo cursor" src="../assets/images/hiking.jpeg" style="width:100%" onclick="currentSlide(5)"
                  alt="Nature and sunrise">
              </div>
            </div>
          </div>
        </div>
    </div>
    
   

  <div class="avt_detail">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <h3>回饋心得:</h3><br>
      </div>
      <div class="row message_member">
        <div class="col-md-1 col-sm-4">
          <img src="../images/face2.jpeg" class="face_message">
        </div>
        <div class="col-md-11 col-sm-8">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <div class="starts">
              <div id="pingStar">
                <label id="1">☆</label>
                <label id="2">☆</label>
                <label id="3">☆</label>
                <label id="4">☆</label>
                <label id="5">☆</label>
                <span id="dir"></span>
              </div>
              <input type="hidden" value="" id="startP">
            </div>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <textarea class="form-control" placeholder="想說的話..." rows="5"></textarea>
              <br>
            </div>
            <div class="text-right">
              <input type="button" value="送出" class=" btn btn-warning">
              <br>
            </div>
          </div>
        </div>
      </div>
      <div class="row message_member">
        <div class="col-md-1 col-sm-4">
          <img src="../images/face3.jpeg" class="face_message">
        </div>
        <div class="col-md-11 col-sm-8 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>jack667&nbsp;&nbsp;<label style="color:orange;">★★★</label></h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div class="avt_message">
              ergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergninviwenvereergni
            </div>
            <div align="right">
              <a href="#">修改</a>&nbsp;
              <a href="#">刪除</a>&nbsp;
              <label class="response" id="0">回應</label>&nbsp;
              <a href='#' class="btn btn-danger btn-xs">移除</a>
            </div>
          </div>
        </div>
      </div>
      <!-- ---------------留言回應--------------- -->
      <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1 col-xs-2">
          <img src="../images/face2.jpeg" class="face_message_response">
        </div>
        <div class="col-md-10 col-xs-10 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>susan</h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <div class="avt_message_response">
                謝謝你的支持！謝謝你的支持！謝謝你的支持！謝謝你的支持！謝謝你的支持！謝謝你的支持！謝謝你的支持！
              </div>
              <div align="right">
                <a href="#">修改</a>&nbsp;
                <a href="#">刪除</a>&nbsp;
                <a href='#' class="btn btn-danger btn-xs">移除</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1 col-xs-2">
          <img src="../images/face3.jpeg" class="face_message_response">
        </div>
        <div class="col-md-10 col-xs-10 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>frank</h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <div class="avt_message_response">
                哈哈！
              </div>
              <div align="right">
                <a href="#">修改</a>&nbsp;
                <a href="#">刪除</a>&nbsp;
                <a href='#' class="btn btn-danger btn-xs">移除</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row message_member_response" style="display:none">
        <div class="col-md-1">
        </div>
        <div class="col-md-1 col-xs-2">
          <img src="../images/face2.jpeg" class="face_message_response">
        </div>
        <div class="col-md-10 col-xs-10 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>winnie</h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <div class="avt_message_response">
                <textarea rows="1" class="message_response_add"></textarea>
              </div>
              <div align="right">
                <a href="#">新增</a>&nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- ---------------------------------------- -->
      <div class="row message_member">
        <div class="col-md-1 col-sm-4">
          <img src="../images/face3.jpeg" class="face_message">
        </div>
        <div class="col-md-11 col-sm-8 border-bottom">
          <div class="col-md-12 col-sm-12">
            <h4>tom352456&nbsp;&nbsp;<label style="color:orange;">★★★★</label></h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div class="avt_message">
              bubjkmwerkggrtherhubjkmwerkggrtherhubjkmwerkggrtherhubjkmwerkggrtherh
            </div>
            <div align="right">
              <!-- <a href="#">修改</a>&nbsp;
                <a href="#">刪除</a>&nbsp;-->
              <label class="response" id="1">回應</label>&nbsp;
              <a href='#' class="btn btn-danger btn-xs">移除</a>
            </div>
          </div>
        </div>
      </div>
      <div class="row message_member_response" style="display:none">
        <div class="col-md-1">
        </div>
        <div class="col-md-1 col-xs-2">
          <img src="../images/face2.jpeg" class="face_message_response">
        </div>
        <div class="col-md-10 col-xs-10 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>winnie</h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <div class="avt_message_response">
                <textarea rows="1" class="message_response_add"></textarea>
              </div>
              <div align="right">
                <a href="#">新增</a>&nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row message_member">
        <div class="col-md-1 col-sm-4">
          <img src="../images/face2.jpeg" class="face_message">
        </div>
        <div class="col-md-11 col-sm-8 border-bottom">
          <div class="col-md-12 col-sm-12">
            <h4>mary77&nbsp;&nbsp;<label style="color:orange;">★★</label></h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div class="avt_message">
              bubjkmwerkggrtherhubjkmwerkggrtherhubjkmwerkggrtherhubjkmwerkggrtherh
            </div>
            <div align="right">
              <!-- <a href="#">修改</a>&nbsp;
                <a href="#">刪除</a>&nbsp;-->
              <label class="response" id="2">回應</label>&nbsp;
              <a href='#' class="btn btn-danger btn-xs">移除</a>
            </div>
          </div>
        </div>
      </div>
      <div class="row message_member_response" style="display:none">
        <div class="col-md-1">
        </div>
        <div class="col-md-1 col-xs-2">
          <img src="../images/face2.jpeg" class="face_message_response">
        </div>
        <div class="col-md-10 col-xs-10 border-bottom">
          <div class="col-md-12 col-sm-12" style="display: inline-block;">
            <h4>winnie</h4>
          </div>
          <div class="col-md-12 col-sm-12">
            <div>
              <div class="avt_message_response">
                <textarea rows="1" class="message_response_add"></textarea>
              </div>
              <div align="right">
                <a href="#">新增</a>&nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
      <font color='red'>待活動結束才顯示此區塊...</font>
    </div>
  </div>
  </div>

  <div class="row">
    <p align="center">
      <br>
      <a href="<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType" class="btn btn-primary " role="button">回到訂房列表</a>
    </p>
  </div>


  </div>



  <!--底部平台介紹================================================================-->
  <footer>
    <!-- <div class="container">
      <div class="col-sm-2">
        <img src="../assets/images/ball.png" class="img-responsive">
      </div>
      <div class="col-sm-4">
        <h5>關於我們</h5>
        <p>
          歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社
          歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社
          歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社歡迎來到羽球社
        </p>
      </div>
      <div class="col-sm-3">
        <h5>聯絡資訊</h5>
        <p>
          許嘉雯：0912768906<br>
          王美美：0945674335<br>
          許嘉雯：0912768906<br>
          王美美：0945674335<br>
          許嘉雯：0912768906<br>
          王美美：0945674335<br>
          許嘉雯：0912768906<br>
        </p>
      </div>
      <div class="col-sm-3">
        <h5>基分撲蝶會活動網站line</h5>
        <img src="../assets/images/qrCode.png" class="qrCode">
        <br>
      </div>
    </div> -->
  </footer>



  <script src="https://code.jquery.com/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">

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