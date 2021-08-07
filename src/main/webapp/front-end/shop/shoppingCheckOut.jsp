<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shoporder.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
 @SuppressWarnings("unchecked")
 Vector<ShopVO> buylist = (Vector<ShopVO>) session.getAttribute("shoppingcart");

 if (buylist != null) {
  ShopService shopSvc = new ShopService();
  for (ShopVO buyVO : buylist) {
   ShopVO shopVO = shopSvc.getOneShop(buyVO.getItemId());
   buyVO.setItemStatus(shopVO.getItemStatus());
   buyVO.setItemFee(shopVO.getItemFee());
   buyVO.setItemQuantity(shopVO.getItemQuantity());
   pageContext.setAttribute("buylist", buylist);
  }
 }
%>

<%
 if (buylist != null && (buylist.size() >= 0)) {
%>

<jsp:useBean id="shopSvc" scope="page"
 class="com.shop.model.ShopService" />
<jsp:useBean id="sps" scope="page"
 class="com.shoppic.model.ShopPicService" />
<jsp:useBean id="shopOrderSvc" scope="page"
 class="com.shoporder.model.ShopOrderService" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>島旅商城結帳</title>

<%--<script src="../assets/jquery-1.12.4.min.js"></script>--%>

 <script src="//code.jquery.com/jquery-1.9.1.js"></script>




 <style>
body {
 height: auto;
 background: #E0FFFF;
}
</style>
</head>
<body>
<form method="Post" action="<%=request.getContextPath()%>/ShoppingCart.do">

 <font size="+3" style="text-align: center;">結帳(請檢查金額)</font>
 <p>
  <a
   href="<%=request.getContextPath()%>/front-end/shop/shopHomePage.jsp"><font
   size="+1">回商城主頁</font></a>
 <p>
  <a
   href="<%=request.getContextPath()%>/front-end/shop/shoppingCart.jsp">回上頁</a>
 <table border="1" bgcolor="#87CEFA"
  style="margin-left: 160px; text-align: center;">
  <thead>
   <tr>
    <td width="200">商品</td>
    <th width="200">商品名稱</th>
    <th width="200">詳細資訊</th>
    <th width="100">數量</th>
    <th width="100">單價</th>
    <td class="100">小計</td>

   </tr>
  </thead>
  <tbody>
   <%
     for (int i = 0; i < buylist.size(); i++) {
       ShopVO order = buylist.get(i);
       int item = order.getItemId();
   %>

   <tr class="row item">
    
    
    <td class="itemId" style="display: none"><%=order.getItemId()%>
    <input type="hidden" name="count" value="<%=buylist.size()%>">
    <input  type="hidden" name="item<%=i+1 %>" value="<%=order.getItemId()%>"/>
    </td>
    <td><img src="data:image/jpeg;base64,<%=sps.getOneShopPic(item)%>"></td>
    
    <td><div align="center"><%=order.getItemName()%></div></td>
    
    <td><div align="center"><%=order.getItemDescribtion()%></div></td>

    <td class="Fee" style="display: none"><%=order.getItemFee()%>
    <input type="hidden" name="itemFee<%=i+1 %>" value="<%=order.getItemFee()%>">
    </td>
     
    <td class="OrderQuantity">
    <input type="hidden" name="orderQuantity<%=i+1 %>" value="<%=order.getOrderQuantity()%>">
    <%=order.getOrderQuantity()%></td>
    <td class="Fee" style="display: none"></td>
    <td class="show_Fee"></td>
    <td class="subtotal"></td>
   </tr>

   <%
     }
   %>
  </tbody>
  <tfoot>
   <tr class="row" id="total">
    
   </tr>
  </tfoot>
 </table>

 <%
  }
 %>


 <div class="container mt-3"
  style="width: 100%; display: flex; flex-wrap: wrap; margin-left: 240px;">
  <div id="shipType">
   <div class="row title">
    <span><b>運送方式</b></span>
   </div>

   <div id="shipType">
    <input id="shiptype0" type="radio" name="shipType" value="0"
     required> <label for="shiptype0"> <span>宅配</span>
    </label>
   </div>
   <div id="shipType">
    <input id="shiptype1" type="radio" name="shipType" value="1"
     required> <label for="shiptype1"> <span>超商取貨</span>
    </label>
   </div>
  </div>
  <span id="ship_info" class="info"></span>


  <div class="container mt-3" id="payType">

   <div class="row title">
    <span><b>付款方式</b></span>
   </div>

   <div class="row title ms-4">
    <input id="paytype0" type="radio" name="payType" class="radio"
     value="0" required> <label for="paytype0"> <span>信用卡支付</span>
    </label>
   </div>
   <div class="row title ms-4">
    <input id="paytype1" type="radio" name="payType" class="radio"
     value="1"> <label for="paytype1"> <span>匯款</span>
    </label>
   </div>
   <input id="paytype2" type="radio" name="payType" class="radio"
    value="2"> <span>超商繳款</span>
  </div>

  <div class="container mt-3">
   <div class="title">
    <span style="padding-left: 0px"><b>訂購人資料</b></span>
   </div>
   <div class="title">

    <ul style="list-style: none;"
     class="formstyle--horizontal formstyle">
     <li><label class="li_title">*姓名</label> 
     <input id="name" type="text" name="name" placeholder="請輸入姓名"
      value="${memVO.memName}" maxlength="10" required="required"
      class="floatLabel text"> <span id="name_info" class="info"></span>
     </li>
     
     <li><label class="li_title">*手機</label> 
     <input id="mobile" type="tel" name="mobile" placeholder="請輸入手機號碼"
      value="${memVO.memPhone}" maxlength="10" required="required"
      class="floatLabel text"> 
      <span id="mobile_info"class="info">
      </span></li>
      
     <li id="cvs_required"><label class="li_title">*門市</label> <input
      id="cvs" type="text" name="cvs" placeholder="請輸入取件門市"
      maxlength="20" required="required" class="floatLabel text">
      <span id="cvs_info" class="info"></span></li>
      
     <li id="addr_required"><label class="li_title">*地址</label> <input
      id="addr" type="text" name="addr" placeholder="請輸入地址"
      value="${memVO.memAddress}" maxlength="20" required="required"
      class="w-50 text"> <span id="addr_info" class="info"></span>
    </ul>
   </div>
   <ul style="list-style: none;" class="bottom-btn">
    <li>
     <button id="btnCheck" class="btn check" type="submit" name="action"
      value="confirm_check">送出訂單</button>
    </li>
   </ul>
  </div>
 </div>
 </form>
</body>
<script>
 //季萱的方法
 //頁面載入後先計算小計和總金額 & 價格加上千分位符號
 $(function() {
  //計算小計、總金額
  count();
  //價格加千分位符號
  let num = $('table').find('.item').length;
  for (i = 0; i < num; i++) {
   let Fee = $('.item').eq(i).find('.Fee').text();
   Fee=Number(Fee);
   let show_Fee = format_with_substring(Fee);
   $('.item').eq(i).find('.show_Fee').text(show_Fee);
  }
 });

 //方法-計算小計和總金額
 function count() {
  let num = $('table').find('.item').length;
  let total = 0;
  for (i = 0; i < num; i++) {
   console.log(num);
   let Fee = $('.item').eq(i).find('.Fee').text();
   
   let OrderQuantity = $('.item').eq(i).find('.OrderQuantity').text();
   console.log("購買數量:"+OrderQuantity);
   let subtotal = Fee * OrderQuantity;
   console.log(subtotal);
   // 小計加千分位符號
   let show_subtotal = format_with_substring(subtotal);
   $('.item').eq(i).find('.subtotal').text(show_subtotal);
   total += subtotal;
  }
  $('#total').html(
   "<td>總計 <span>" + num + "</span> 項商品, <span>$ "
    + format_with_substring(total) + "</td> "+
   "<input id='amounts' type='hidden' name='total' value='" + total + "'/>");
  
  $('#amounts').val(total);//用val定義總金額
  
  console.log($('#amounts').val()+'來了');
  console.log(total+'來了');
 }
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
  if (f === 0) {
   r = r.substring(1);
  }
  return r + (!!fraction ? "." + fraction : '');
 }
 $('input[name=shipType]').click(function(){
  if ($('input[name=shipType]:checked').val() === "0"){
   $('#cvs').html('');
   $('#cvs_info').html('');
   $('#cvs').removeAttr('required');
   $('#cvs_required').hide();
   $('#addr_required').show();
   $('#addr').attr('required', 'required');
   $('#paytype0').removeAttr('disabled');
   $('#paytype1').removeAttr('disabled');
   $('#paytype2').attr('checked',false);
   $('#paytype2').attr('disabled',true);
   able_to_check();
  } else {
   $('#cvs_required').show();
   $('#cvs').attr('required', 'required');
   $('#addr').html('');
   $('#addr_info').html('');
   $('#addr').removeAttr('required');
   $('#addr_required').hide();
   $('#paytype0').attr('disabled',true);
   $('#paytype1').attr('disabled',true);
   $('#paytype2').attr('checked',true);
   able_to_check();
  }
 });

 $("#mobile").blur(function(){
  //獲取手機號,並去除左右兩邊空格
  let mobile = $.trim($("#mobile").val());
  if (is_mobile(mobile)){
   $("#mobile_info").html("");
  } else {
   $("#mobile_info").html("手機號碼格式不正確(ex:0912345678)");
   $('#btnCheck').attr('disabled', true);
//    return false;
  }
 });

 $("#mobile").keyup(function(){
  //獲取手機號,並去除左右兩邊空格
  let mobile = $.trim($("#mobile").val());
  if (is_mobile(mobile)){
   $("#mobile_info").html("");
   able_to_check();
  }
 });

 
 $("#name").blur(function(){
  let name = $.trim($("#name").val());
  if (is_name(name)){
   $("#name_info").html("");
   $('#btnCheck').attr('disabled', true);
  } else {
   $("#name_info").html("姓名格式不正確");
//    return false;
  }
 });

 $("#name").keyup(function(){
  let name = $.trim($("#name").val());
  if (is_name(name)){
   $("#name_info").html("");
   able_to_check();
  }
 });

 $("#addr").blur(function(){
  let addr = $.trim($("#addr").val());
  if (is_name(addr)){
   $("#addr_info").html("");
   $('#btnCheck').attr('disabled', true);
  } else {
   $("#addr_info").html("地址格式不正確");
//    return false;
  }
 });

 $("#addr").keyup(function(){
  let addr = $.trim($("#addr").val());
  if (is_name(addr)){
   $("#addr_info").html("");
   able_to_check();
  }
 });

 $("#cvs").blur(function(){
  let addr = $.trim($("#cvs").val());
  if (is_name(addr)){
   $("#cvs_info").html("");
  } else {
   $("#cvs_info").html("取貨門市格式不正確");
   $('#btnCheck').attr('disabled', true);
//    return false;
  }
 });

 $("#cvs").keyup(function(){
  let addr = $.trim($("#cvs").val());
  if (is_name(addr)){
   $("#cvs_info").html("");
   able_to_check();
  }
 });
 function is_name(name) {
  if(name == "") {
   return false;
  } else {
   if( ! /[^0-9]{2,10}/.test(name) ) {
    return false;
   }
   return true;
  }
 }

 function is_mobile(mobile) {
  if( mobile == "") {
   return false;
  } else {
   if( ! /^09[0-9]{8}$/.test(mobile) ) {
    return false;
   }
   return true;
  }
 }
 function able_to_check(){
  let shipType = $('input[name=shipType]:checked').val();
  console.log('shipType: ' + shipType);
  let payType = $('input[name=payType]:checked').val();
  console.log('payType: ' + payType);
  let name = $.trim($("#name").val());
  console.log('name: ' + name);
  let mobile = $.trim($("#mobile").val());
  console.log('mobile: ' + mobile);
  let addr = $.trim($("#addr").val());
  console.log('addr: ' + addr);
  let cvs = $.trim($("#cvs").val());
  console.log('cvs: ' + cvs);
  let info = $('.info').text();
  console.log('info: ' + info);
  if (shipType != null && payType != null && name != '' && mobile != '' && info == ''){
   if ((shipType == '0' && addr != '') || (shipType != '0 ' && cvs != '')){
    $('#btnCheck').attr('disabled', false);
    console.log('unset disabled');
    return;
   }
  } 
    $('#btnCheck').attr('disabled', true);
    console.log('still disable because info is null');
 }

 $('input[name=payType]').click(function(){
  able_to_check();
 });
</script>
</html>