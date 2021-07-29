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
  <li><a href='listAllShop.jsp'>�C�X�Ҧ��ӫ~���</a><br><br></li>
   
  <li>
    <FORM METHOD="post" ACTION="shop.do" >
        <b>��J�ӫ~�s�� (�p1):</b>
        <input type="text" name="itemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
   
  <li>
     <FORM METHOD="post" ACTION="shop.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="itemId">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.itemId}">${shopVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="shop.do" >
       <b>��ܰӫ~�W��:</b>
       <select size="1" name="itemId">
         <c:forEach var="shopVO" items="${shopSvc.all}" > 
          <option value="${shopVO.itemId}">${shopVO.itemName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>�ӫ~�޲z</h3>

<ul>
  <li><a href='addShop.jsp'>�s�W�ӫ~</a></li>
</ul>

</body>
</html>