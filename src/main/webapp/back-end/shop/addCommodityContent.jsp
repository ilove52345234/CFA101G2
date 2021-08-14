<%@page import="sun.security.util.Length"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shoptype.model.*"%>
<%@ page import="java.util.*"%>
<%
	ShopTypeService svc = new ShopTypeService();
	List<ShopTypeVO> listSve = svc.getAll();
	pageContext.setAttribute("listSve", listSve);

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
		.form-control {
			margin-bottom: 20px;
		}
	</style>
</head>

<body>
<div class="container" style="width: 500px;">
	<FORM METHOD="post" ACTION="shop.do" id="insert_form">
		<div style="text-align:center;"><h3><strong>商品建檔</strong></h3></div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<div class="from-group">
			<label class="control-label" for="pwd">商品類別:</label>
			<select class="form-control" id="sel1" name="itemCategoryId">
				<c:forEach var="shopVO" items="${listSve}">
					<option value="${shopVO.itemCategoryId}">${shopVO.itemCategoryName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="from-group">
			<label class="control-label">商品名稱:</label>
			<input type="TEXT" class="form-control" name="itemName" size="45"
				   value="${param.itemName}" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品價格:</label> <input
				type="TEXT" class="form-control" name="itemFee" size="45"
				value="${param.itemFee}" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品數量:</label> <input
				type="TEXT" class="form-control" name="itemQuantity" size="45"
				value="${param.itemQuantity}" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品敘述:</label> <input
				type="TEXT" class="form-control" name="itemDescribtion" size="45"
				value="${param.itemDescribtion}" />
		</div>

		<div class="from-group">
			<label for="pwd" class="control-label">商品圖片：</label> <input
				class="control-label" id="imgId" name="itemPhoto" type="file"
				accept="image/gif, image/jpeg, image/png"
				value="${param.itemPhoto}">
			<img style="display: none;" id="preview_imgId" src="" />
		</div>

		<br> <input type="hidden" name="action" value="insert">
		<input type="hidden" name="pic_base64" id="pic_base64">
		<input type="hidden" name="itemStatus" id="itemStatus"> <br> <br>
		<div align="center">
			<button type="button" class="btn btn-default" onclick="updateItemStatus(0)">儲存並上架</button>
			<button type="button" class="btn btn-default" onclick="updateItemStatus(1)">儲存並下架</button>
		</div>
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
			$("#preview_imgId").css('display','block');
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