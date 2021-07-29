<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
	//ShopServlet.java (Concroller) 存入req的shopVO物件 (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<html>
<head>
<meta charset="BIG5">
<title>商品資料修改 - updateShop.jsp</title>
<jsp:include page="backEndPage2.jsp" flush="true" />
<style type="text/css">
.imagePreview {
	width: 100%;
	height: 180px;
	background-position: center center;
	background:
		url(http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg);
	background-color: #fff;
	background-size: cover;
	background-repeat: no-repeat;
	display: inline-block;
	box-shadow: 0px -3px 6px 2px rgba(0, 0, 0, 0.2);
}

.btn-primary {
	display: block;
	border-radius: 0px;
	box-shadow: 0px 4px 6px 2px rgba(0, 0, 0, 0.2);
	margin-top: -5px;
}

.imgUp {
	margin-bottom: 15px;
}

.del {
	position: absolute;
	top: 0px;
	right: 15px;
	width: 30px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	background-color: rgba(255, 255, 255, 0.6);
	cursor: pointer;
}

.imgAdd {
	width: 30px;
	height: 30px;
	border-radius: 50%;
	background-color: #4bd7ef;
	color: #fff;
	box-shadow: 0px 0px 2px 1px rgba(0, 0, 0, 0.2);
	text-align: center;
	line-height: 30px;
	margin-top: 0px;
	cursor: pointer;
	font-size: 15px;
}
</style>

</head>
<body>
	<div id="container" style="margin-top: 60px;">
		<h3>商品資料修改:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post" id="update_form" ACTION="shop.do" name="form1">
			<table>
				<tr>
					<td>商品編號：</td>
					<td><%=shopVO.getItemId()%></td>
				</tr>
				<tr>
					<td>商品類別編號：</td>
					<td><%=shopVO.getItemCategoryId()==1?"1、酒類":shopVO.getItemCategoryId()==2?"2、點心類":shopVO.getItemCategoryId()==3?"3、飲料類":shopVO.getItemCategoryId()==4?"4、紀念品類":
				shopVO.getItemCategoryId()==5?"5、服飾類":shopVO.getItemCategoryId()==6?"6、戶外類":"7、美妝類"%></td>
				</tr>
				<tr>
					<td>商品名稱：</td>
					<td><input type="TEXT" name="itemName" size="45"
						value="<%=shopVO.getItemName()%>" /></td>
				</tr>
				<tr>
					<td>商品價格：</td>
					<td><input type="TEXT" name="itemFee" size="45"
						value="<%=shopVO.getItemFee()%>" /></td>
				</tr>
				<tr>
					<td>商品數量：</td>
					<td><input type="TEXT" name="itemQuantity" size="45"
						value="<%=shopVO.getItemQuantity()%>" /></td>
				</tr>
				<tr>
					<td>商品敘述：</td>
					<td><input type="TEXT" name="itemDescribtion" size="45"
						value="<%=shopVO.getItemDescribtion()%>" /></td>
				</tr>
				<tr>
					<td>商品狀態：</td>
					<td><%=shopVO.getItemStatus() == 1 ? "下架" : "上架"%></td>
					<%-- <td><input type="TEXT" id="itemStatus" name="itemStatus" size="45" value="<%=shopVO.getItemStatus()%>" /></td> --%>
				</tr>
				<tr>
					<td>評價總人數：</td>
					<td><%=shopVO.getCommentNumber()%></td>
				</tr>
				<tr>
					<td>評價總分：</td>
					<td><%=shopVO.getCommentTotalScore()%></td>
				</tr>

			</table>
			<div class="row mb-3">
				<p class="fs-6">商品圖片：</p>
				<div class="col-sm-5">
					<input class="form-control" id="imgId" name="itemPhoto" type="file"
						accept="image/gif, image/jpeg, image/png"
						value="${param.itemPhoto}">
						<br> 
						<img style="width: 200px; height: 200p; display: none;" id="preview_imgId" src=""/> 
						<img id="oldPreview" style="width: 200px; height: 200px" src="<%=request.getContextPath() + "/" + shopVO.getShopPicSrc()%>">
				</div>
			</div>
			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="itemId" value="<%=shopVO.getItemId()%>">
			<input type="hidden" name="itemCategoryId" value="<%=shopVO.getItemCategoryId()%>"> 
			<input type="hidden" name="commentNumber" value="<%=shopVO.getCommentNumber()%>"> 
			<input type="hidden" name="commentTotalScore" value="<%=shopVO.getCommentTotalScore()%>">
			<input type="hidden" name="itemStatus" id="itemStatus"> 
			<input type="hidden" name="pic_base64" id="pic_base64">
			<button type="button" class="btn btn-light" onclick="updateItemStatus(0)">儲存並上架</button>
			<button type="button" class="btn btn-light" onclick="updateItemStatus(1)">儲存並下架</button>
		</FORM>
		<h4><a href="backEndPage.jsp">回首頁</a></h4>
	</div>
</body>

<script>
	function encodeImgtoBase64(element) {
		var img = element.files[0];
		var reader = new FileReader();
		reader.onloadend = function() {
			document.getElementById('preview_imgId').style.display="";
			document.getElementById('oldPreview').style.display="none";
			$('#pic_base64').val(reader.result);
			$("#preview_imgId").attr('src', reader.result);
		}
		reader.readAsDataURL(img);
	}
	var max_size = 65536;
	$('input[id=imgId]').each(function() {
		$(this).change(function(evt) {
			var finput = $(this);
			var files = evt.target.files; // 獲得檔案物件   
			var output = [];
			for (var i = 0, f; f = files[i]; i++) { //檢查檔案大小   
				if (f.size > max_size) {
					alert("上傳的圖片不能超過64KB!");
					$(this).val('');
				} else {
					encodeImgtoBase64(this); // 沒超過64KB才顯示照片
				}
			}
		});
	});
	<!-- 儲存上下架button -->
	function updateItemStatus(itemStatus) {

		if (itemStatus == 0) {
			$('#itemStatus').val(0);
		} else {
			$('#itemStatus').val(1);
		}
		$('#update_form').submit();
	}
</script>
</html>