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
	<jsp:include page="../header.jsp" flush="true" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
<!-- 引入 backEndPage2 導覽頁 -->
<%--<jsp:include page="backEndPage2.jsp" flush="true" />--%>

	<link rel="icon" href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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">
	<!-- 收藏夾顯示圖示 -->
	<link rel="icon" href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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" type="images/logo_2.ico">
	<!-- 匯入bootstrap5 css -->
<%--	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
	<!-- 匯入bootstrap5 java script -->
<%--	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>
	<!-- 匯入bootstrap3 jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



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

	<div id="container" style="margin-top: 20px;">
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