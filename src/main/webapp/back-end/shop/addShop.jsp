<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��Ʒs�W  - addShop</title>
</head>
<body>

<h3>��Ʒs�W:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="shop.do" name="form1">

<table>

	<tr>
		<td>�ӫ~���O�s��:</td>
		<td><input type="TEXT" name="itemCategoryId" size="45"
			 value="${param.itemCategoryId}"/></td>
	</tr>
	<tr>
		<td>�ӫ~�ԭz:</td>
		<td><input type="TEXT" name="itemDescribtion" size="45"
			 value="${param.itemDescribtion}"/></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="itemFee" size="45"
			 value="${param.itemFee}"/></td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="itemName" size="45"
			 value="${param.itemName}"/></td>
	</tr>
	<tr>
		<td>�ӫ~�ƶq:</td>
		<td><input type="TEXT" name="itemQuantity" size="45"
			 value="${param.itemQuantity}"/></td>
	</tr>
	<tr>
		<td>�ӫ~���A:</td>
		<td><input type="TEXT" name="itemStatus" size="45"
			 value="${param.itemStatus}"/></td>
	</tr>
	<tr>
		<td>�����`�H��:</td>
		<td><input type="TEXT" name="commentNumber" size="45"
			 value="${param.commentNumber}"/></td>
	</tr>
	<tr>
		<td>�����`��:</td>
		<td><input type="TEXT" name="commentTotalScore" size="45"
			 value="${param.commentTotalScore}"/></td>
	</tr>

</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
<h4><a href="backEndPage.jsp">�^����</a></h4>

</body>
</html>