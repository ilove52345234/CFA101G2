<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Back End - ShopOrder Home</title>

</head>
<body>

<h3>ShopOrder Select Page</h3>

<h3>資料查詢</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllShopOrder.jsp'>列出所有商品資料</a><br><br></li>
   
  <li>
    <FORM METHOD="post" ACTION="shoporder.do" >
        <b>輸入訂單編號 (如1):</b>
        <input type="text" name="itemOrderId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="shopOrderSvc" scope="page" class="com.shoporder.model.ShopOrderService" />
   
  <li>
     <FORM METHOD="post" ACTION="shoporder.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="itemOrderId">
         <c:forEach var="shopOrderVO" items="${shopOrderSvc.all}"> 
          <option value="${shopOrderVO.itemOrderId}">${shopOrderVO.itemOrderId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>

<h3>訂單管理</h3>

<ul>
  <li><a href='addShopOrder.jsp'>新增訂單</a></li>
</ul>

</body>
</html>