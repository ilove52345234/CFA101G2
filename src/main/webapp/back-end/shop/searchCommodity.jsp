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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
<!-- 引入 backEndPage2 導覽頁 -->
<jsp:include page="backEndPage2.jsp" flush="true" />
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

<style>
#listShopId {
 vertical-align: middle !important;
 text-align: center;
}
</style>

<meta charset="Big5">
<title>搜尋商品 - searchCommodity</title>
</head>
<body>

	<div id="container" style="margin-top: 100px;">
		<div class="from-group row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<input style="margin-bottom: 50px;" class="form-control" id="myInput" type="text" placeholder="請輸入您要搜尋的商品關鍵字">
			</div>
			<div class="col-sm-4"></div>
		</div>
		<table id="shopTable"  class="table table-hover">
			<thead>
				<tr style="vertical-align: middle !important;text-align: center;">
	<!-- 			style="vertical-align: middle !important;text-align: center;" 文字垂直水平置中 -->
					<td>商品編號</td>
					<td>商品類別編號</td>
					<td>商品名稱</td>
					<td>商品價格</td>
					<td>商品數量</td>
					<td>商品敘述</td>
					<td>商品狀態</td>
					<td>評價總人數</td>
					<td>評價總分</td>
					<td>修改
				</tr>
			</thead>
	
			<c:forEach var="shopVO" items="${listShop}">
				<tbody id="myTable">
					<tr id="listShopId">
						<td >${shopVO.itemId}</td>
						<td>${shopVO.itemCategoryId}</td> 
<!-- 						==1?"1、酒類":shopVO.itemCategoryId==2?"2、點心類":shopVO.itemCategoryId==3?"3、飲料類":shopVO.itemCategoryId==4?"4、紀念品類": -->
<!-- 							  shopVO.itemCategoryId==5?"5、服飾類":shopVO.itemCategoryId==6?"6、戶外類":"7、美妝類" -->
						<td>${shopVO.itemName}</td>
						<td>${shopVO.itemFee}</td>
						<td>${shopVO.itemQuantity}</td>
						<td>${shopVO.itemDescribtion}</td>
						<td>${shopVO.itemStatus==1?"下架":"上架"}</td>
						<td>${shopVO.commentNumber}</td>
						<td>${shopVO.commentTotalScore}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-light" value="修改"> 
								<input type="hidden" name="itemId" value="${shopVO.itemId}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</body>
</html>