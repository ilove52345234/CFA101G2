<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>
<%
	ShopService svc = new ShopService();
	List<ShopVO> listShop = svc.getAll();
	pageContext.setAttribute("listShop", listShop);
%>

<html>
<head>
	<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 最佳兼容模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- 引入 backEndPage -->
	<jsp:include page="../header.jsp" flush="true" />

	<script>
		$(document).ready(
				function() {
					$("#myInput").on(
							"keyup",
							function() {
								var value = $(this).val().toLowerCase();
								$("#myTable tr").filter(
										function() {
											$(this).toggle(
													$(this).text().toLowerCase()
															.indexOf(value) > -1)
										});
							});
				});
	</script>

	<style>
		#list {
			text-align-last: center;
		}

		#listShopId {
			vertical-align: middle !important;
			text-align: center;
		}

		.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
		.table>thead>tr>td, .table>thead>tr>th {
			vertical-align: middle;
		}
	</style>
</head>

<body>
<div id="container" style="margin-top: 20px;">
	<div class="from-group row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<input style="margin-bottom: 50px;" class="form-control"
				   id="myInput" type="text" placeholder="請輸入您要搜尋的商品關鍵字">
		</div>
		<div class="col-sm-4"></div>
	</div>
	<table class="table table-hover">
		<thead>
		<tr id="list">
			<th>商品<br>編號</th>
			<th>商品<br>類別</th>
			<th>商品圖片</th>
			<th>商品名稱</th>
			<th>商品<br>價格</th>
			<th>商品<br>數量</th>
			<th>商品敘述</th>
			<th>商品<br>狀態</th>
			<th>評價<br>總人數</th>
			<th>評價<br>總分</th>
			<th>修改</th>
		</tr>
		</thead>
		<% for (int i = 0; i < listShop.size(); i++) { %>
		<tbody id="myTable">
		<tr id="listShopId">
			<td><%=listShop.get(i).getItemId()%></td>
			<td><%=listShop.get(i).getItemCategoryId() == 1 ? "酒類" :
					listShop.get(i).getItemCategoryId() == 2 ? "點心類" :
							listShop.get(i).getItemCategoryId() == 3 ? "飲料類" :
									listShop.get(i).getItemCategoryId() == 4 ? "紀念品類" :
											listShop.get(i).getItemCategoryId() == 5 ? "服飾類" :
													listShop.get(i).getItemCategoryId() == 6 ? "戶外類" : "美妝類" %></td>
			<td><img id="preview1" style="width: 80px; height: 80px;"
					 src="<%=request.getContextPath() + "/" + listShop.get(i).getShopPicSrc()%>"></td>
			<td><%=listShop.get(i).getItemName()%></td>
			<td><%=listShop.get(i).getItemFee()%></td>
			<td><%=listShop.get(i).getItemQuantity()%></td>
			<td><%=listShop.get(i).getItemDescribtion()%></td>
			<td><%=listShop.get(i).getItemStatus() == 1 ? "下架" : "上架"%></td>
			<td><%=listShop.get(i).getCommentNumber()%></td>
			<td><%=listShop.get(i).getCommentTotalScore()%></td>

			<td>
				<FORM METHOD="post"
					  ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
					  style="margin-bottom: 0px;">
					<input type="submit" class="btn btn-default" value="修改">
					<input type="hidden" name="itemId"
						   value="<%=listShop.get(i).getItemId()%>"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
		</tr>
		</tbody>
		<%
			}
		%>
	</table>
</div>
</body>
</html>