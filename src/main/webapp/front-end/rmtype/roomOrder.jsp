<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmorder.model.MemRoomOrderVO" %>

<!-- 要注意 jstl/core 和import MemRoomOrderVO資料才會吃到 -->

<!DOCTYPE html>

<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>訂房網站</title>
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--     <link rel="short Icon" type="images/x-icon"
        href="/xxx/xxx/front-end/images/logo.png"> -->
    
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--[if lt IE 9]>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
    </head>
    
    
    <body>
    
        <!--導覽列===============================================================================-->
        
    
    
        <!--導覽列=============================================================================================-->
        <header>
    
        </header>
    
    
    
    
    
    
        <!--第一列===================================================================================-->
        <br>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <ol class="breadcrumb">
                        <li><a
                            href="/xxx/xxx/front-end/index.do?action=navbar&title=index">首頁</a></li>
                        <li><a
                            href="/xxx/xxx/front-end/class/member/member.jsp">會員中心</a></li>
                    </ol>
                </div>
            </div>
        </div>
    
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-3">
    
    
    
    <head> 
    <link rel="stylesheet" type="text/css" 
    href="/xxx/xxx/front-end/class/member/css/member.css"> 
    </head>
    
    
                    <table class="table table-hover table-bordered"
                        style='word-break: keep-all'>
                        <thead></thead>
                        <tbody>
                            <ul>
    
                                <tr>
                                    <td class="profile-img"><img class="profile-img-card"
                                        src='/xxx/xxx/front-end/class/member/images/profile-img-card.png' width=80% /></td>
                                </tr>
    
                                <tr	onclick="window.document.location='/xxx/xxx/front-end/class/member/member.jsp';">
                                    <td class="list member"><span
                                        class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;會員訂單</td>
                                </tr>
    
                                <tr	onclick="window.document.location='/xxx/xxx/front-end/class/member/member_information.jsp';">
                                    <td class="list informatiom"><span
                                        class="glyphicon glyphicon-user list"></span>&nbsp;&nbsp;會員資料</td align="center">
                                </tr>
    
                                <tr onclick="window.document.location='<%=request.getContextPath()%>/rmtype/RmtypeServlet?action=getAllRoomType';">
                                    <td class="list Room"><span
                                        class="glyphicon glyphicon-heart list"></span>&nbsp;&nbsp;房間列表</td>
                                </tr>
                        </tbody>
                        </ul>
                    </table>
                    
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>
    
                        <h4 class="text-center">
                            <b>廣告</b>
                        </h4>
                        <!-- Wrapper for slides -->
          <div style="width:99% ;height:99%;">
            <div id="carousel-id" class="carousel slide" data-ride="carousel">
                <!-- 幻燈片小圓點區 -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
                    
                    <li data-target="#carousel-id" data-slide-to="1"></li>
                    
                    <li data-target="#carousel-id" data-slide-to="2"></li>
                    
                </ol>
                <!-- 幻燈片主圖區 -->
                <div class="carousel-inner">
                  
                    <div class="item active" >
                        <a href="/xxx/xxx/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=STR_0001">
                        <img src="/xxx/tools/Mem_Red_Img?adv_no=ADV_0005" ></a>
                        <div class="container" aling="right">
                            <div class="carousel-caption"><b>
                                <h3>xxx</h3>
                                <p>2020-12-27至<br>2020-12-31</p>                           
                            </b></div>
                        </div>
                    </div>
                    
                    <div class="item " >
                        <a href="/xxx/xxx/front-end/class/search/search.do?action=enter_store_select&select=introduce&str_no=STR_0001">
                        <img src="/xxx/tools/Mem_Red_Img?adv_no=ADV_0006" ></a>
                        <div class="container" aling="right">
                            <div class="carousel-caption"><b>
                                <h3>xxx</h3>
                                <p>2020-12-27至<br>2021-01-02</p>                           
                            </b></div>
                        </div>
                    </div>
                    
                </div>
                <!-- 上下頁控制區 -->
                <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
                <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
              </div>
           </div>
                    </div>
    
                </div>
                <div class="col-xs-12 col-sm-9">
                    <!-- <div class="dropdown">
                        <button class="btn dropdown-toggle btn-danger" type="button"
                            data-toggle="dropdown">
                            test <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">最近一個月訂單</a></li>
                            <li><a href="#">最近三個月訂單</a></li>
                            <li><a href="#">最近半年訂單</a></li>
                            <li><a href="#">全部訂單訂單</a></li>
                        </ul>
                    </div> -->
                    <!-- <select class="form-control" id="sel1">
                  <option value="最近一個月">最近一個月訂單</option>
                  <option value="最近三個月">最近三個月訂單</option>
                  <option value="最近半年">最近半年訂單</option>
                  <option value="最近一年">最近一年訂單</option>
                  <option value="全部訂單">全部訂單訂單</option>
                </select> -->
                    <ul class="nav nav-tabs" style="margin-top: 20px">
                        <li class="active"><a data-toggle="tab" href="#home">已預定訂單</a></li>
                        <li><a data-toggle="tab" href="#menu1">已退房訂單</a></li>
                        <li><a data-toggle="tab" href="#menu2">已取消訂單</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="home" class="tab-pane fade in active">
                            <table class="table table-hover order">
                                <thead>
                                    <tr>
                                        <th>住房訂單編號</th>
                                        <th>會員編號</th>
                                        <th>房型</th>
                                        <th>預定日期</th>
                                        <th>入住日期</th>
                                        <th>房間金額</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
    								<c:forEach var="memRoomOrderVO" items="${memRoomOrderVOs}">
                                    <tr
                                        onclick="window.document.location='<%=request.getContextPath()%>/rmorder/RmorderServlet?orderId=${memRoomOrderVO.roomOrderId}&action=getRoomOrderList';">
                                        																	       <!--      orderId是從req.getParameter("orderId"); 來的-->
                                        <td>${memRoomOrderVO.roomOrderId}</td>
                                        <td>${memRoomOrderVO.memId}</td>
                                        <td>${memRoomOrderVO.roomName}</td>
                                        <td>${memRoomOrderVO.orderDate}</td>
                                        <th>${memRoomOrderVO.checkInDate}</th>
                                        <th>${memRoomOrderVO.totalPrice}</th>
                                    </tr>
                                     </c:forEach>
                                     
                      
                                    
                                </tbody>
                            </table>
                        </div>
                        <div id="menu1" class="tab-pane fade">
                            <table class="table table-hover order">
                                <thead>
                                    <tr>
                                       <th>住房訂單編號</th>
                                        <th>會員編號</th>
                                        <th>房型</th>
                                        <th>預定日期</th>
                                        <th>入住日期</th>
                                        <th>訂單總金額</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                    <tr>
                                        <td colspan="4" class="text-center">目前尚無訂單</td>
                                    </tr>
    
                                    
                                </tbody>
                            </table>
                        </div>
                        <div id="menu2" class="tab-pane fade">
                            <table class="table table-hover order">
                                <thead>
                                    <tr>
                                        <th>住房訂單編號</th>
                                        <th>會員編號</th>
                                        <th>房型</th>
                                        <th>預定日期</th>
                                        <th>入住日期</th>
                                        <th>訂單總金額</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                    <tr>
                                        <td colspan="4" class="text-center">目前尚無訂單</td>
                                    </tr>
    
                                    
                                </tbody>
                            </table>
                        </div>
                    
                                    
                                </tbody>
                            </table>
                        </div>
    
                    </div>
                </div>
            </div>
        </div>
    
    
        <!--底部平台介紹==============================================================================-->
        
    
    
    
        <!--底部平台介紹=============================================================================================-->
    
    
        <footer>
          
        </footer>
    
    
    
    
        <script src="https://code.jquery.com/jquery.js"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script language="JavaScript"
            src="/xxx/xxx/front-end/class/member/js/member.js"></script>
    </body>
    </html>