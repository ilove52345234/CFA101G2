<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
	<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- 引入 backEndPage -->
	<jsp:include page="../header.jsp" flush="true" />

	<style>
		table {
			margin-left: auto;
			margin-right: auto;
		}

		table, th, td {
			border: 1px solid #D3D3D3;
		}

		th, td {
			padding: 4px;
			text-align: center;
		}
	</style>
</head>

<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>商品編號</th>
		<th>商品類別編號</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品數量</th>
		<th>商品敘述</th>
		<th>商品狀態</th>
		<th>評價總人數</th>
		<th>評價總分</th>
		<th>商品圖片</th>
		<th>修改</th>
	</tr>
	<% for (int i = 0; i < list.size(); i++) { %>
	<tr>
		<td><%=list.get(i).getItemId()%></td>
		<td><%=list.get(i).getItemCategoryId()%></td>
		<td><%=list.get(i).getItemName()%></td>
		<td><%=list.get(i).getItemFee()%></td>
		<td><%=list.get(i).getItemQuantity()%></td>
		<td><%=list.get(i).getItemDescribtion()%></td>
		<td><%=list.get(i).getItemStatus() == 1 ? "下架" : "上架"%></td>
		<td><%=list.get(i).getCommentNumber()%></td>
		<td><%=list.get(i).getCommentTotalScore()%></td>
		<th><img id="preview1" style="width: 100px; height: 100px;" src="<%=request.getContextPath() + "/" + list.get(i).getShopPicSrc()%>"></th>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do" style="margin-bottom: 0px;">
				<input type="submit" class="btn btn-default" value="修改">
				<input type="hidden" name="itemId" value="<%=list.get(i).getItemId()%>">
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
	</tr>
	<% } %>
</table>
</body>
</html>