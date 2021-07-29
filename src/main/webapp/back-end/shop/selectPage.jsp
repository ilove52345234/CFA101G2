<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Back End - Shop Home</title>

</head>
<body>

<h3>Shop Select Page</h3>

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
  <li><a href='listAllShop.jsp'>列出所有商品資料</a><br><br></li>
   
  <li>
    <FORM METHOD="post" ACTION="shop.do" >
        <b>輸入商品編號 (如1):</b>
        <input type="text" name="itemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
   
  <li>
     <FORM METHOD="post" ACTION="shop.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="itemId">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.itemId}">${shopVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="shop.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="itemId">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.itemId}">${shopVO.itemName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>商品管理</h3>

<ul>
  <li><a href='addShop.jsp'>新增商品</a></li>
</ul>

</body>
</html>