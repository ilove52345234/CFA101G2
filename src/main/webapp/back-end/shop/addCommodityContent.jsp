<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>島旅 Island Brigade - 商品建檔-addCommodityContent.jsp</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<jsp:include page="backEndPage2.jsp" flush="true" />
</head>

<body>
	<div class="mx-auto" style="width: 500px; margin-top: 70px;">
		<FORM METHOD="post" ACTION="shop.do" id="insert_form">

			<h3>商品建檔:</h3>
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
					<td>商品類別編號:</td>
					<td><input type="TEXT" name="itemCategoryId" size="45"
						value="${param.itemCategoryId}" /></td>
				</tr>
				<tr>
					<td>商品名稱:</td>
					<td><input type="TEXT" name="itemName" size="45"
						value="${param.itemName}" /></td>
				</tr>
				<tr>
					<td>商品價格:</td>
					<td><input type="TEXT" name="itemFee" size="45"
						value="${param.itemFee}" /></td>
				</tr>
				<tr>
					<td>商品數量:</td>
					<td><input type="TEXT" name="itemQuantity" size="45"
						value="${param.itemQuantity}" /></td>
				</tr>
				<tr>
					<td>商品敘述:</td>
					<td><input type="TEXT" name="itemDescribtion" size="45"
						value="${param.itemDescribtion}" /></td>
				</tr>

			</table>

			<table>
				<tr>
					<td><label for="formFileLg" class="form-label">商品圖片：</label> <input
						class="form-control" id="imgId" name="itemPhoto" type="file"
						accept="image/gif, image/jpeg, image/png"
						value="${param.itemPhoto}"> <br> <img
						style="width: 300px" id="preview_imgId" src="" /></td>
				</tr>
			</table>

			<br> 
			<input type="hidden" name="action" value="insert">
			<input type="text" name="pic_base64" id="pic_base64">
			<input type="hidden" name="itemStatus" id="itemStatus"> 
			<br><br>
			<button type="button" class="btn btn-light" onclick="updateItemStatus(0)">儲存並上架</button>
			<button type="button" class="btn btn-light" onclick="updateItemStatus(1)">儲存並下架</button>
		</FORM>
	</div>

</body>

<script>
	function encodeImgtoBase64(element) {

		var img = element.files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			$('#pic_base64').val(reader.result);
			$("#preview_imgId").attr('src', reader.result);
		}
		reader.readAsDataURL(img);
	}
	$('input[type=file]').each(function() {
		var max_size = 65536;
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

	function updateItemStatus(itemStatus) {

		if (itemStatus == 0) {
			$('#itemStatus').val(0);
		} else {
			$('#itemStatus').val(1);
		}
		$('#insert_form').submit();
	}
</script>

</html>