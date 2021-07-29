<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料新增  - addShop</title>
</head>
<body>

<h3>資料新增:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="shop.do" name="form1">

<table>

	<tr>
		<td>商品類別編號:</td>
		<td><input type="TEXT" name="itemCategoryId" size="45"
			 value="${param.itemCategoryId}"/></td>
	</tr>
	<tr>
		<td>商品敘述:</td>
		<td><input type="TEXT" name="itemDescribtion" size="45"
			 value="${param.itemDescribtion}"/></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="itemFee" size="45"
			 value="${param.itemFee}"/></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="itemName" size="45"
			 value="${param.itemName}"/></td>
	</tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="itemQuantity" size="45"
			 value="${param.itemQuantity}"/></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="itemStatus" size="45"
			 value="${param.itemStatus}"/></td>
	</tr>
	<tr>
		<td>評價總人數:</td>
		<td><input type="TEXT" name="commentNumber" size="45"
			 value="${param.commentNumber}"/></td>
	</tr>
	<tr>
		<td>評價總分:</td>
		<td><input type="TEXT" name="commentTotalScore" size="45"
			 value="${param.commentTotalScore}"/></td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
<h4><a href="backEndPage.jsp">回首頁</a></h4>

</body>
</html>