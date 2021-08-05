<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rmorder.model.BookRmoVO"%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>預約成功</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/rmOrder.css"></script>
</head>
<body>

<div class="container">
  <h2>預約成功信息</h2>
  <p>在預定確認:</p>            
  <table class="table table-bordered">
    <thead>
      <tr>
        <th colspan="4"><li><font color="white">預約者信息</font></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td class="td1">預約者姓名</td>
        <td class="td1">電子郵件地址</td>
        <td class="td1">電話</td>
      </tr>
      <tr>
        <td class="td3">${bookRmoVO.memName}</td>
        <td class="td3">${bookRmoVO.memEmail}</td>
        <td>${bookRmoVO.memPhone}</td>
      </tr>
    </tbody>
  </table>
</div>
<br>
<div class="container">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th colspan="4"><li><font color="white">住宿信息</font></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="td2">房型名稱</td>
          <td class="td4">${bookRmoVO.roomName}</td>
        </tr>
        <tr>
          <td class="td2">旅館電話號碼</td>
          <td class="td4">ＸＸＸＸＸＸＸＸＸＸ</td>
        </tr>
        <tr>
            <td class="td2">入住登記日</td>
            <td class="td4">${bookRmoVO.checkInDate}</td>
        </tr>
        <tr>
            <td class="td2">住宿天數</td>
            <td class="td4">${bookRmoVO.days}</td>
          </tr>
          <tr>
            <td class="td2">退房預定時間</td>
            <td class="td4">${bookRmoVO.checkOutDate}</td>
          </tr>
        

      </tbody>
    </table>
  </div>
  <br>
  <div class="container">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th colspan="5"><li><font color="white">住宿信息詳細資訊</font></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="td1">一晚</td>
          <td class="td2">房間類型</td>
          <td class="td2">住宿人數</td>
          <td class="td2">房間數</td>
          <td class="td2">價格</td>
        </tr>
        <tr>
          <td class="td2">${bookRmoVO.checkInDate}</td>
          <td class="td4">${bookRmoVO.roomName}</td>
          <td class="td4">${bookRmoVO.memNumber}</td>
          <td class="td4">${bookRmoVO.roomCount}</td>
          <td class="td4">${bookRmoVO.roomTotalPrice}元</td>
        </tr>
        <tr>
            <td class="td2">選擇的服務</td>
            <td colspan="4">活動 其他消費 ...etc</td>
        </tr>
        <tr>
            <td class="td2">住宿合計人數</td>
            <td colspan="4" style="text-align: center;">${bookRmoVO.memNumber}名</td>
        </tr>
        <tr>
            <td class="td2" height="50px">總金額<br>*標示為優惠價時 不可和其他優惠活動搭配</td>
            <td colspan="4" style="text-align: center;">${bookRmoVO.roomTotalPrice}元</td>
        </tr>

      </tbody>
    </table>
  </div>
<br>

<div class="container">
<%--  <div id="booking"><font color="red">如果內容確認無誤請按確定,若有變更情況請點擊修改按鈕</font></div>--%>
<br>
<div style="text-align: center;">
<%--<input type="submit" class="press-confirm" name="按鈕名稱" value="確定">&nbsp;--%>
  <input type="button" onclick="history.back()"  class="press-confirm" name="按鈕名稱" value="返回">
</div>
<br>
</div>
  
</body>
</html>
