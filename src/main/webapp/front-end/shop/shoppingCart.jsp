<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  @SuppressWarnings("unchecked")
  Vector<ShopVO> buylist = (Vector<ShopVO>) 
  session.getAttribute("shoppingcart");

  MemVO memVO = (MemVO)session.getAttribute("memVO");
  
  if (buylist != null){
   ShopService shopSvc = new ShopService();
   for(ShopVO buyVO:buylist){
    ShopVO shopVO = shopSvc.getOneShop(buyVO.getItemId());
    buyVO.setItemStatus(shopVO.getItemStatus());
    buyVO.setItemFee(shopVO.getItemFee());
    buyVO.setItemQuantity(shopVO.getItemQuantity());
    pageContext.setAttribute("buylist", buylist);
   }
  }
%>

<%
  if (buylist != null && (buylist.size() > 0)) {
%>
<jsp:useBean id="sps" scope="page" class="com.shoppic.model.ShopPicService" />
<!DOCTYPE html>
<html>
<head>

<script
  src="https://code.jquery.com/jquery-1.12.4.js"
  integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
  crossorigin="anonymous">
  </script>
  
<meta charset="UTF-8">
<title>購物車內容</title>
</head>
<style>
body {
 height: auto;
 background: #E0FFFF;
}
.orderQuantity {
  width: 40px;
  height: 35px;
  text-align: center;
  border: 0;
  border-top: 1px solid #aaa;
  border-bottom: 1px solid #aaa;
}


input.plus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}

input.minus {
  width: 25px;
  height: 35px;
  border: 1px solid #aaa;
  background: #f8f8f8;
}
</style>
<body>

 <div class="container" style="margin-top: 30px" >

  <font size="+3">購物車清單</font> <a href="<%=request.getContextPath()%>/front-end/shop/shopHomePage.jsp">
  <p>
  <font size="+1">回商城主頁</font></a>
  <table id="table-1" style="border: 2px; border-color: black;text-align: center;">
   <thead>
    <tr class="row">
     <td class="col-lg-5">商品</td>
     <td class="col-lg-1">名稱</td>
     <td class="col-lg-1">詳細資訊</td>
     <td class="col-lg-1">選購數量</td>
     <td class="col-lg-1">單價</td>
     <td class="col-lg-1">小計</td>
     <td class="col-lg-1">庫存</td>
     <td class="col-lg-1">操作</td>
    </tr>
   <thead>
   
   <%
    for (int index = 0; index < buylist.size(); index++) {
      ShopVO order = buylist.get(index);
      int item = order.getItemId();
   %>
   
  <tr class="row item">
   
   <td class="itemId" style="display:none"><%=order.getItemId() %></td>
    <td><img src="data:image/jpeg;base64,<%=sps.getOneShopPic(item)%>"></td>
    <td><div align="center"><%=order.getItemName()%></div></td>
    <td><div align="center"><%=order.getItemDescribtion()%></div></td>
 
   <td class="td_orderQuantity">
     
     
    <!--減數量--><button class="btn downOne" type="submit" value="down_one" >-<i class="minus"></i></button>
  
  
  
     <input style="display:none;" class="back_itemQuantity" type=text
     name="back_itemQuantity" value="${order.orderQuantity}"/> 
   <!--輸入數量--><input class="orderQuantity" type=text name='orderQuantity' 
     value="<%=order.getOrderQuantity()%>"/> 
    
     
     
     <!--加數量--><c:if test="${order.itemQuantity == order.orderQuantity}">
      <button class="btn disabled addOne" type="submit" value="add_one" >+<i class="plus" ></i></button>
     </c:if> 
     
     <c:if test="${order.itemQuantity < order.orderQuantity}">
      <button class="btn disabled addOne" type="submit" value="add_one" >+<i class="plus" ></i></button>
      <br>
      <span>庫存不足</span>
     </c:if> 
     
     <c:if test="${order.itemQuantity > order.orderQuantity}">
      <button class="btn addOne" type="submit" value="add_one" >+<i class="plus" ></i></button>
     </c:if>
   </td>
    
    <td class="Fee" style="display:none"><%=order.getItemFee()%></td>
    <td class="show_Fee"></td>
    <td class="subtotal"></td>
    <td><%=order.getItemQuantity()%></td>
    
    <td class="DELETE_item">
     <form name="deleteForm" action="ShoppingCart.do" method="POST" <%=request.getServletPath()%>>
      <input type="hidden" name="action" value="DELETE"> 
      <input type="hidden" name="del" value="<%=index%>"> 
      <input type="submit" value="刪 除" class="button">
     </form>
     
    </td>
    
   </tr>
   
   <%
    }
   %>
   
    <tfoot>
    <tr class="row" id="total"></tr>
    </tfoot>
   </table>
   
      <div class="row">
  <p>
  <form name="checkoutForm" action="<%=request.getContextPath()%>/ShoppingCart.do" method="POST">
   <input type="hidden" name="action" value="CHECKOUT"> 
   <input type="submit" value="結帳" class="button">
  </form>
  
  <%
   }
  %>

 </div>
</div>
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
    console.log(Fee+'有印出來');
    let show_Fee = format_with_substring(Fee);
    $('.item').eq(i).find('.show_Fee').text(show_Fee);
   }
  });

  //方法-計算小計和總金額
  function count() {
   let num = $('table').find('.item').length;
   console.log(num);
   let total = 0;
   for (i = 0; i < num; i++) {
    let Fee = $('.item').eq(i).find('.Fee').text();
   
    let OrderQuantity = $('.item').eq(i).find('.orderQuantity').val();
    let subtotal = Fee * OrderQuantity;
    console.log(subtotal);
    // 小計加千分位符號
    let show_subtotal = format_with_substring(subtotal);
    $('.item').eq(i).find('.subtotal').text(show_subtotal);
    total += subtotal;
   }
   $('#total').html(
     "<td>總計 <span>" + num + "</span> 項商品, <span>$ "
       + format_with_substring(total) + "</td>");
   
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
  $(".orderQuantity").blur(function(){//這個是寫數量格子的
   let prodId = $(this).parent().parent('.item').find('.itemId').text();
   //若更改數量為0，詢問是否移除商品
   if ($(this).val() === "0"){
    if (confirm('確定移除此項商品?')){
     $(this).parent().parent('.item').attr('id','DELETE');
     deleteItem(itemId);
     return;
     
    } else {
     let curr_orderQuantity = $(this).siblings('.curr_orderQuantity').val();
     $(this).val(curr_orderQuantity);
     return;
    }
   }
   $(this).siblings('.addOne').attr('id', 'addOne')
   $(this).attr('id', 'this_orderQuantity');
   console.log('itemId= ' + itemId);
   console.log('#this_orderQuantity= ' + $('#this_orderQuantity').val());
   
   $.ajax({
    url: "/CFA1O1G2/cart/cart.do",
    type: "POST",
    data: {'action': 'change_orderQuantity',
      'itemId': itemId,
      'orderQuantity': $('#this_orderQuantity').val()
      },
    dataType: 'json',
    success: function(data){
     console.log(data);
     let ordQty = data.ordQty;
      let prodQty = data.prodQty;
     
     if (data.status === "failure"){
      alert("商品庫存量不足");
      $('#this_orderQuantity').val(ordQty);
     } 
     if (ordQty <= prodQty){
      $('#this_orderQuantity').siblings('span').remove();
     }
     if (ordQty < prodQty){
      $('#addOne_btn').attr('class', 'addOne');
     }
     $('#this_ordQty').removeAttr('id');
     $('#addOne_btn').removeAttr('id');
     count();
    },
    error:  function(){window.location.reload();}
   });
  });
    //季萱的方法
  
  //數量+1
 $(".addOne").click(function(){
 $(this).attr('id', 'addOne_btn');
 $(this).siblings('.orderQuantity').attr('id','add_orderQuantity');
  console.log('orderQuantity');
  console.log("數量:" + $('#add_orderQuantity').val());
  
  let itemId = $(this).parent().parent('.item').find('.itemId').text();
  
  console.log(itemId);
  $.ajax({
   url: "/CFA101G2/ShoppingCart.do",
   type: "GET",
   data: { 'action': $(this).val(), 
     'itemId': itemId, 
     'orderQuantity': $('#add_orderQuantity').val() },
     
   dataType: 'json',
   success: function(data){
    console.log(data);
    let orderQuantity = data.orderQuantity;
    let itemQuantity = data.itemQuantity;
    console.log('購買數量: ' + orderQuantity);
    console.log('庫存數量: ' + itemQuantity);
    
    //因庫存量不足，購買數量沒有更改
    if (data.status !== "success"){
     //如原購買數量 ＝ 庫存量 
     if ( orderQuantity === itemQuantity){
       alert('商品庫存量不足');
     } else { 
     //原購買數量 > 庫存量 
      let errMsg = "<br><span>請修改訂購數量</span>";
      $('#addOne_btn').after(errMsg);
     }
     $('#addOne_btn').attr('class', 'btn disabled addOne');
    } else {
     $('#add_orderQuantity').attr({"value":  orderQuantity});
     //若購買數量 ＝ 庫存量 ->將增加數量按鈕改為disabled
     if (orderQuantity === itemQuantity){
      $('#addOne_btn').attr('class', 'btn disabled addOne');
     }
    }
    $('#add_orderQuantity').removeAttr('id');
    $('#addOne_btn').removeAttr('id');
    count();
   }
   
  });
 });
 
 //數量-1
 $(".downOne").click(function(){
  $(this).siblings('.addOne').attr('id', 'addOne')
  $(this).siblings('input[name="orderQuantity"]').attr('id','down_orderQuantity');
  console.log("數量:" + $('#down_orderQuantity').val());
  let sub_orderQuantity = $('#down_orderQuantity').val();
  let itemId = $(this).parent().parent('.item').find('.itemId').text();
  //若數量原本是1，詢問是否移除此項品項
  if (sub_orderQuantity === '1') {
   if (confirm('確定移除此項商品?')){
    $(this).parent().parent('.item').attr('id','del_item');
    deleteItem(itemId);
    return;
   } else {
    return;
   }
  }
  
  //若數量不是1，執行數量-1
  $.ajax({
   url: "/CFA101G2/ShoppingCart.do",
   type: "GET",
   data: { 'action': 'down_one', 
     'itemId': itemId, 
     'orderQuantity': $('#down_orderQuantity').val() },
   dataType: "json",
   success: function(data){
    console.log(data);
    let orderQuantity = data.orderQuantity;
    let itemQuantity = data.itemQuantity;
    console.log('購買數量: ' + orderQuantity);
    console.log('庫存數量: ' + itemQuantity);
    //如購買數量-1後，等於庫存量 -> 去掉庫存量不足的錯誤訊息
    if (orderQuantity === itemQuantity){
     $('#addOne').siblings('span').remove();
    }
    //如購買數量-1後，少於庫存量 -> 去掉增加數量按鈕的disabled設定
    if (orderQuantity < itemQuantity){
     $('#addOne').attr('class', 'addOne');
    }
    $('#down_orderQuantity').attr({"value": orderQuantity});
    $('#down_orderQuantity').removeAttr('id');
    $('#addOne').removeAttr('id');
    count();
   },
   
  }); 
 });
 //刪除某品項
 $('.deleteOne').click(function(){
  $(this).parent().parent('.item').attr('id','del_item');
  let itemId = $(this).parent().parent('.item').find('.itemId').text();
  console.log(itemId);
  deleteItem(itemId);
 });
 //方法-刪除某品項
 function deleteItem(itemId){
  $.ajax({
   url: "/CFA101G2/ShoppingCart.do",
   type: "GET",
   data: {'action': 'delete_item',
     'itemId': itemId},
   dataType: "json",
   success: function(){
    $('#del_item').remove();
    if ($('table').find('.item').length === 0){
     window.location.reload();
     return;
    }
    count();
   },
   error: function(){
    window.location.reload();
   }
  });
 }
 </script>
</html>