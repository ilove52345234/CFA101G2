<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.shoporder.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.shop.model.*"%>

<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
  ShopOrderService shopOrderSvc = new ShopOrderService();
  List<ShopOrderVO> list = shopOrderSvc.listAllByMemId(memVO.getMemId());
  pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>訂單查詢及付款</title>
  <!-- 網頁標題左側顯示 -->
  <link rel="icon"
        href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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">
  <!-- 收藏夾顯示圖示 -->
  <link rel="icon"
        href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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"
        type="images/logo_2.ico">
  <!-- width=device-width部分設置頁面的寬度以跟隨設備的屏幕寬度（這因設備而異) -->
  <!-- initial-scale=1部分設置瀏覽器首次加載頁面時的初始縮放級別 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 匯入bootstrap css-->
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <!-- 匯入jQuery-->
  <script
          src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <!-- 匯入bootstrap javascript -->
  <script
          src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

  <style type="text/css">
    html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p,
    blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins,
    kbd, q, s, samp, strong, var, b, u, i, ol, ul, li, fieldset, form,
    label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article,
    aside, canvas, details, embed, figure, figcaption, footer, header, nav,
    output, ruby, section, summary, time, mark, audio, video {
      margin:0 ;
      padding: 0;
      border: 0;
      font-size: 100%;
      font: inherit;
      vertical-align: baseline;
      background: #E0FFFF;
    }
  </style>
</head>
<body>
<!-- 首頁開頭 -->
<nav class="nav navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <!-- class="navbar-toggle" 這是關鍵字 有它才能讓我們順利的看到「三」 而且出現在視窗的右方 -->
      <!-- data-toggle="collapse" 這是用來製作動畫效果的 沒有的話 我們點「三」的時候不會有任何反應-->
      <!-- data-target="#myNavbar" 這裡面的myNavbar是一個CSS的id 我們可以取任意的名字 只是一定要跟下面縮放的id對應到 -->
      <button type="button" class="navbar-toggle" data-toggle="collapse"
              data-target="#myNavbar">
        <!-- <span class="icon-bar"></span>這段這是用來產生「一」 當有三個「一」時 合在一起就會變「三」的按鍵了 -->
        <span class="icon-bar"></span> <span class="icon-bar"></span> <span
              class="icon-bar"></span>
      </button>
      <!-- 點擊商標跳轉至首頁 #要放首頁網址 -->
      <a class="navbar-header" href="#"> <img
              src="https://i.imgur.com/2j6Zzxv.png" height="40"
              title="島旅 Island Brigade" alt="島旅 Island Brigade"></a>
    </div>
    <!-- class="collapse navbar-collapse" 這兩個是關鍵字 幫助我們在正常視窗的情況下 可以看到原本的導覽列 -->
    <div class="collapse navbar-collapse" id="myNavbar">
      <!-- 底下放要縮放的內容 -->
      <ul class="nav navbar-nav navbar-left">
        <!-- a href="#" 將要導入的網址放在" "裡面 記得把#拿掉 -->
        <li><a href="#">線上訂房</a></li>
        <li><a href="#">預定活動</a></li>
        <li><a
                href="<%=request.getContextPath()%>/front-end/shop/shopHomePage.jsp">商城</a></li>
        <li><a href="#">社群</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!-- a href="#" 將要導入的網址放在" "裡面 記得把#拿掉 -->
        <!-- class="glyphicon glyphicon-log-in" 這裡是使用bootstrap所提供的icon 直接用class就可以插入這些icon -->
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
          登入</a></li>
        <!-- <li><a href="#">登出</a></li> -->
        <li><a href="#"><span class="glyphicon glyphicon-user"></span>
          會員中心</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- 匯入bootstrap javascript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<div>
  <div class="container">
    <div style="margin-top:40px;">
      <ul style="list-style-type: none" class="navbar-nav">

        <li  style="margin-top:20px; "class="nav-item"><a class="nav-link active" href="<%=request.getContextPath()%>/front-end/frontEndPage.jsp">會員帳號  ${memVO.memAccount}<br>訂單總覽</a></li>

      </ul>
    </div>
  </div>

  <div class="container" style="display:flex;flex-wrap:wrap;margin-left:40px;">

    <%@ include file="page1.file"%>
    <c:forEach var="shopOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
      <c:if test="${shopOrderVO.shippingStatus != 3}">
        <div class="mb-3 p-0" style="margin:20px 20px 20px 70px;border:2px #ADD8E6 solid;border-style:double;padding:30px;">
          <div class="card shadow">
            <div class="m-3" >

              <div class="row storeId" >
                <div class="col-4 div-r">
         <span class="status">
         <c:if test="${shopOrderVO.shippingStatus == 0}">成立</c:if>
         <c:if test="${shopOrderVO.shippingStatus == 1}">出貨中</c:if>
         <c:if test="${shopOrderVO.shippingStatus == 2}">完成</c:if>
         <c:if test="${shopOrderVO.shippingStatus == 3}">取消</c:if>
         <c:if test="${shopOrderVO.shippingStatus == 4}">未付款</c:if>
         </span> <span>&nbsp;|  &nbsp;訂單編號: ${shopOrderVO.itemOrderId}</span>
                </div>
              </div>



              <jsp:useBean id="shopOrderService" scope="page"
                           class="com.shoporder.model.ShopOrderService" />
              <jsp:useBean id="sps" scope="page"
                           class="com.shoppic.model.ShopPicService" />
              <form name="form" action="<%=request.getContextPath()%>/CFA101G2/front-end/shop/shop.do" method="POST">
                <!--圖片 -->					<c:forEach var="shopOrdDetVO" items="${shopOrderSvc.getShopOrderDetailByItemOrderId(shopOrderVO.itemOrderdId)}">
                <div class="row item">
                  <div class="col-2">
                    <img style="border-radius: 20px;" src="data:image/jpeg;base64,${sps.getOneShopPic(shopVO.itemId)} ">
                  </div>
                  <div class="row col-10">
                    <div class="col-12">
                      <span><a href="#">${shopOrderVO.orderName}</a></span>
                    </div>
                    <div class="col-8">
                      <span>${shopOrderDetailVO.orderQuantity}</span>
                    </div>
                    <div class="col-4 div-r">
                      <span class="price">${shopOrderDetailVO.itemAmounts}</span>
                    </div>
                  </div>
                </div>
              </c:forEach>
              </form>
            </div>
            <!-- 總金額 -->	</div>
          <div class="card shadow foot">
            <div class="row">
              <div class="col-6 foot-l">
                <div>
									<span> 運送方式: <c:if
                                            test="${shopOrderVO.shippingMethod == 0}">宅配</c:if> <c:if
                                            test="${shopOrderVO.shippingMethod == 1}">超商</c:if>

									</span>
                </div>
                <div>
									<span> 付款方式: <c:if
                                            test="${shopOrderVO.paymentMethod == 0}">信用卡</c:if> <c:if
                                            test="${shopOrderVO.paymentMethod == 1}">匯款</c:if> <c:if
                                            test="${shopOrderVO.paymentMethod == 2}">超商取貨付款</c:if>
									</span>
                </div>
                <div>
                  <span>收件人: ${shopOrderVO.orderName}</span>
                </div>
                <div>
                  <span>收件人手機: ${shopOrderVO.orderMobile}</span>
                </div>
                <div>
                  <span>收件人地址: ${shopOrderVO.orderAddress}</span>
                </div>
                <div>
									<span>訂單日期: <fmt:formatDate
                                            value="${shopOrderVO.itemOrderDate2}"
                                            pattern="yyyy-MM-dd hh:mm:ss" type="date" dateStyle="long" />
									</span>
                </div>
              </div>

              <span>訂單金額: <span class="amount">${shopOrderVO.itemAmounts}</span>
							</span>
            </div>
            <div style="display:flex;flex-wrap:wrap; margin-top:10px;">
              <c:if test="${shopOrderVO.shippingStatus == 0}">
                <div>
                  <form method="post"
                        action="<%=request.getContextPath()%>/shoporder2.do">
                    <input type="hidden" name="requestURL"
                           value="<%=request.getServletPath()%>"> <input
                          type="hidden" name="whichPage" value="<%=whichPage%>">
                    <input type="hidden" name="itemOrderId"
                           value="${shopOrderVO.itemOrderId}"> <input
                          type="hidden" name="itemAmounts" value="${shopOrderVO.itemAmounts}">
                    <button  class="btn btn-info" type="submit" name="action"
                             value="pay_by_card" >
                      訂單結帳</button>
                  </form>
                </div>
                <br>
              </c:if>
              <c:if test="${shopOrderVO.shippingStatus == 1}"><button  class="btn btn-success" disabled
                                                                       value="pay_by_card" >結帳成功</button></c:if>
              <c:if test="${shopOrderVO.shippingStatus == 0 || shopOrderVO.shippingStatus == 4}">
                <div style="margin-left:20px;">
                  <form method="post"
                        action="<%=request.getContextPath()%>/shoporder2.do">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <input type="hidden" name="whichPage" value="<%=whichPage%>">
                    <input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
                    <button class="btn btn-danger" type="submit" name="action"
                            value="cancel_shoporder">取消訂單</button>
                  </form>
                </div>
              </c:if>
            </div>
          </div>
        </div>
      </c:if>
    </c:forEach>
  </div>
</div>

<%@ include file="page2.file"%>

<script>
  window
          .addEventListener(
                  'load',
                  function() {
                    let sum = document.getElementsByClassName('price').length;
                    for (i = 0; i < sum; i++) {
                      let price = document
                              .getElementsByClassName('price')[i].innerHTML;
                      let new_price = '$'
                              + format_with_substring(price);
                      document.getElementsByClassName('price')[i].innerHTML = new_price;
                    }
                    ;
                  });

  window
          .addEventListener(
                  'load',
                  function() {
                    let sum = document.getElementsByClassName('amount').length;
                    for (i = 0; i < sum; i++) {
                      let amount = document
                              .getElementsByClassName('amount')[i].innerHTML;
                      amount = Number(amount);
                      let new_amount = '$'
                              + format_with_substring(amount);
                      document.getElementsByClassName('amount')[i].innerHTML = new_amount;
                    }
                    ;
                  });

  //方法-金額加千分位符號
  function format_with_substring(number) {
    let arr = (number + '').split('.');
    let num = arr[0] + '';
    let fraction = arr[1] || '';
    let f = num.length % 3;
    let r = num.substring(0, f);

    for (let i = 0; i < Math.floor(num.length / 3); i++) {
      r += ',' + num.substring(f + i * 3, f + (i + 1) * 3)
    }
    ;
    if (f === 0) {
      r = r.substring(1);
    }
    ;
    return r + (!!fraction ? "." + fraction : '');
  };
</script>
</body>
</html>