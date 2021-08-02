<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shoporder.model.*"%>
<%@ page import="java.util.*"%>
<%
	ShopOrderService svc = new ShopOrderService();
	List<ShopOrderVO> listShopOrder = svc.getAll();
	pageContext.setAttribute("listShopOrder", listShopOrder);
%>

<html>
<head>
	<jsp:include page="../header.jsp" flush="true" />

	<title>島旅 Island Brigade - 日期查詢訂單-searchOrder.jsp</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap-datepicker3.min.css" />
	<link rel="stylesheet" href="../css/bootstrap-datepicker3.standalone.css" />
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap-datepicker3.standalone.css.map" />

	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment().locale('zh-tw'),
				defaultDate : new Date()
			});
			$('#datetimepicker2').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment().locale('zh-tw'),
				defaultDate : new Date()
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
	<title>搜尋訂單 - searchOeder.jsp</title>
</head>
<body>
<div class="container" style="margin-top: 70px;">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do"
		  style="margin-bottom: 0px;">
		<div class="row">
			<div class='col-sm-3'>
				<div class='input-group date' id='datetimepicker1'>
					<input type='text' name="startDate"
						   value="${shopOrderVO.startDate}" class="form-control" />
					<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
				</div>
			</div>
			<div class='col-sm-3'>
				<div class='input-group date' id='datetimepicker2'>
					<input type='text' name="endDate"
						   value="${shopOrderVO.endDate}" class="form-control" />
					<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
				</div>
			</div>
			<div class='col-sm-3'>
				<input type="submit" value="查詢">
				<input type="hidden" name="action" value="searchOrder">
			</div>
		</div>
	</FORM>
</div>
<br>
<div class="container jumbotron">
	<table id="shopTable" class="table table-hover">
		<thead>
		<tr style="vertical-align: middle !important; text-align: center;">
			<td>訂單編號</td>
			<td>會員編號</td>
			<td>訂單成立時間</td>
			<td>訂單總金額</td>
			<td>付款方式</td>
			<td>運送方式</td>
			<td>訂單狀態</td>
			<td>查看明細</td>
			<td>修改</td>
		</tr>
		</thead>
		<c:forEach var="shopOrderVO" items="${listShopOrder}">
			<tbody id="myTable">
			<tr id="listShopId">
				<td>${shopOrderVO.itemOrderId}</td>
				<td>${shopOrderVO.memId}</td>
				<td>${shopOrderVO.itemOrderDate}</td>
				<td>${shopOrderVO.itemAmounts}</td>
				<td>${shopOrderVO.paymentMethod==0?"信用卡":shopOrderVO.paymentMethod==1?"現金":"匯款"}</td>
				<td>${shopOrderVO.shippingMethod==0?"宅配":shopOrderVO.shippingMethod==1?"超商":"郵寄"}</td>
				<td>${shopOrderVO.shippingStatus==0?"訂單成立":shopOrderVO.shippingStatus==1?"訂單已出貨":"訂單已取消"}</td>
				<td>
					<FORM METHOD="post" ACTION="/CFA101G2/back-end/shoporderdetail/shoporderdetail.do" style="margin-bottom: 0px;">
						<input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
						<input type="hidden" name="action" value="getShop_Join_Detail">
						<input type="submit" class="btn btn-default" value="查看明細">
					</FORM>
					<!-- 						<form> -->
					<!-- 						   <div class="btn-group"> -->
						<%-- 						    <a href="<%=request.getContextPath()%>/back-end/shoporderdetail/listAllShopOrderDetail.jsp?itemOrderId=${shopOrderVO.itemOrderId}" class="btn btn-secondary">查看明細</a> --%>
					<!-- 						   </div> -->
					<!-- 						  </form> -->
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do" style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-default" value="修改">
						<input type="hidden" name="itemOrderId" value="${shopOrderVO.itemOrderId}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
			</tbody>
		</c:forEach>
	</table>
</div>

<%-- <%@include file="/back-end/shop/backEndPage.jsp"%> --%>

</body>
</html>