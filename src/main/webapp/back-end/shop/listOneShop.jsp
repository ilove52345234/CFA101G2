<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%
	// ShopServlet.java(Concroller), 存入req的shopVO物件
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
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
		style > table {
			width: 600px;
			background-color: white;
			margin-top: 5px;
			margin-bottom: 5px;
		}

		table, th, td {
			border: 1px solid #CCCCFF;
		}

		th, td {
			padding: 5px;
			text-align: center;
		}
	</style>
</head>

<body>
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
	<tr>
		<td><%=shopVO.getItemId()%></td>
		<td><%=shopVO.getItemCategoryId()%></td>
		<td><%=shopVO.getItemName()%></td>
		<td><%=shopVO.getItemFee()%></td>
		<td><%=shopVO.getItemQuantity()%></td>
		<td><%=shopVO.getItemDescribtion()%></td>
		<td><%=shopVO.getItemStatus() == 1 ? "下架" : "上架"%></td>
		<td><%=shopVO.getCommentNumber()%></td>
		<td><%=shopVO.getCommentTotalScore()%></td>
		<th><img id="preview1" style="width: 150px; height: 150px;"
				 src="<%=request.getContextPath() + "/" + shopVO.getShopPicSrc()%>">
		</th>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do" style="margin-bottom: 0px;">
				<input type="submit" class="btn btn-default" value="修改">
				<input type="hidden" name="itemId" value="<%=shopVO.getItemId()%>">
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
	</tr>
</table>
</body>
</html>