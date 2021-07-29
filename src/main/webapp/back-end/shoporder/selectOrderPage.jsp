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

<h3>��Ƭd��</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllShopOrder.jsp'>�C�X�Ҧ��ӫ~���</a><br><br></li>
   
  <li>
    <FORM METHOD="post" ACTION="shoporder.do" >
        <b>��J�q��s�� (�p1):</b>
        <input type="text" name="itemOrderId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="shopOrderSvc" scope="page" class="com.shoporder.model.ShopOrderService" />
   
  <li>
     <FORM METHOD="post" ACTION="shoporder.do" >
       <b>��ܭq��s��:</b>
       <select size="1" name="itemOrderId">
         <c:forEach var="shopOrderVO" items="${shopOrderSvc.all}"> 
          <option value="${shopOrderVO.itemOrderId}">${shopOrderVO.itemOrderId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
</ul>

<h3>�q��޲z</h3>

<ul>
  <li><a href='addShopOrder.jsp'>�s�W�q��</a></li>
</ul>

</body>
</html>