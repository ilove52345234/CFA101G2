<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%
	//ShopServlet.java (Concroller) 存入req的shopVO物件 (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)
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
		body {
			padding: 0;
			margin: 0;
			background-size: cover;
			background-attachment: fixed;
			background-position: center;
		}

		.font {
			text-align: center;
			font-size: 20px;
			margin: auto;
			font-style: "微軟正黑體", cursive;
			line-height: 40px;
		}

		.imagePreview {
			width: 100%;
			height: 180px;
			background-position: center center;
			background:url(http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg);
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

		.center {
			margin-left: auto;
			margin-right: auto;
		}

		.col-sm-5 {
			float: none;
			display: inline-block;
			vertical-align: middle;
		}
		.form-control {
			margin-bottom: 20px;
		}
	</style>
</head>

<body>
<div class="container" style="width: 500px;">
	<FORM METHOD="post" id="update_form" ACTION="shop.do" name="form1">
		<div style="text-align:center;"><h3><strong>商品資料修改</strong></h3></div>
		<br>
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
			<label class="control-label">商品編號：</label>
			<%=shopVO.getItemId()%>
		</div>
		<div class="from-group">
			<label class="control-label">商品類別：</label>
			<%=shopVO.getItemCategoryId()  == 1 ? "酒類" :
					shopVO.getItemCategoryId() == 2 ? "點心類" :
							shopVO.getItemCategoryId() == 3 ? "飲料類" :
									shopVO.getItemCategoryId() == 4 ? "紀念品類" :
											shopVO.getItemCategoryId() == 5 ? "服飾類" :
													shopVO.getItemCategoryId() == 6 ? "戶外類" : "美妝類"%>
		</div>
		<div class="from-group">
			<label class="control-label">商品名稱:</label>
			<input type="TEXT" class="form-control" name="itemName" size="45"
				   value="<%=shopVO.getItemName()%>" />
		</div>
		<div class="from-group">
			<label class="control-label">商品價格:</label>
			<input type="TEXT" class="form-control" name="itemFee" size="45"
				   value="<%=shopVO.getItemFee()%>" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品數量：</label>
			<input type="TEXT" class="form-control" name="itemQuantity" size="45"
				   value="<%=shopVO.getItemQuantity()%>" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品敘述：</label>
			<input type="TEXT" class="form-control" name="itemDescribtion" size="45"
				   value="<%=shopVO.getItemDescribtion()%>" />
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品狀態：</label>
			<%=shopVO.getItemStatus() == 1 ? "下架" : "上架"%>
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">評價總人數：</label>
			<%=shopVO.getCommentNumber()%>
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">評價總分：</label>
			<%=shopVO.getCommentTotalScore()%>
		</div>
		<div class="from-group">
			<label class="control-label" for="pwd">商品圖片：</label>
			<input class="form-control" id="imgId" name="itemPhoto" type="file" accept="image/gif, image/jpeg, image/png" value="${param.itemPhoto}"><br>
			<img style="width: 200px; height: 200p; display: none;" id="preview_imgId" src="" />
			<img id="oldPreview" style="width: 200px; height: 200px;" src="<%=request.getContextPath() + "/" + shopVO.getShopPicSrc()%>">
		</div>
		<br> <input type="hidden" name="action" value="update">
		<input type="hidden" name="itemId" value="<%=shopVO.getItemId()%>">
		<input type="hidden" name="itemCategoryId" value="<%=shopVO.getItemCategoryId()%>">
		<input type="hidden" name="commentNumber" value="<%=shopVO.getCommentNumber()%>">
		<input type="hidden" name="commentTotalScore" value="<%=shopVO.getCommentTotalScore()%>">
		<input type="hidden" name="itemStatus" id="itemStatus">
		<input type="hidden" name="pic_base64" id="pic_base64">
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