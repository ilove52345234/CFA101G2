<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>

<%
	// ShopServlet.java(Concroller), 存入req的shopVO物件
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<html>
<head>
	<jsp:include page="../header.jsp" flush="true" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>一筆商品資料 - ListOneShop</title>
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>
<style>
table {
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

	<h3>商品資料 - ListOneShop.jsp</h3>
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
			<td><%=shopVO.getItemCategoryId()==1?"1、酒類":shopVO.getItemCategoryId()==2?"2、點心類":shopVO.getItemCategoryId()==3?"3、飲料類":shopVO.getItemCategoryId()==4?"4、紀念品類":
				shopVO.getItemCategoryId()==5?"5、服飾類":shopVO.getItemCategoryId()==6?"6、戶外類":"7、美妝類"%></td>
			<td><%=shopVO.getItemName()%></td>
			<td><%=shopVO.getItemFee()%></td>
			<td><%=shopVO.getItemQuantity()%></td>
			<td><%=shopVO.getItemDescribtion()%></td>
			<td><%=shopVO.getItemStatus() == 1 ? "下架" : "上架"%></td>
			<td><%=shopVO.getCommentNumber()%></td>
			<td><%=shopVO.getCommentTotalScore()%></td>
			<th><img id="preview1" style="width: 150px; height: 150px; border-radius: 200px;"
				src="<%=request.getContextPath() + "/" + shopVO.getShopPicSrc()%>">
			</th>

			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do" style="margin-bottom: 0px;">
					<input type="submit" class="btn btn-light" value="修改"> 
					<input type="hidden" name="itemId" value="<%=shopVO.getItemId()%>"> 
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
		</tr>

	</table>
</body>
</html>