<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shoporder.model.*"%>

<%	// ShopServlet.java(Concroller), 存入req的shopVO物件
  ShopOrderVO shopOrderVO = (ShopOrderVO) request.getAttribute("shopOrderVO"); 
%>

<html>
<head>
<meta charset="BIG5">
<title>一筆訂單資料 - ListOneShopOrder</title>

<style>
  
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

 <h3>訂單資料 - ListOneShopOrder.jsp</h3>
	
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單成立時間</th>
		<th>訂單總金額</th>
		<th>付款方式</th>
		<th>運送方式</th>
		<th>運送狀態</th>
		<th>修改</th>
	</tr>
	
	<tr>
		<td><%=shopOrderVO.getItemOrderId()%></td>
		<td><%=shopOrderVO.getMemId()%></td>
		<td><%=shopOrderVO.getItemOrderDate()%></td>
		<td><%=shopOrderVO.getItemAmounts()%></td>
		<td><%=shopOrderVO.getPaymentMethod()==0?"信用卡":shopOrderVO.getPaymentMethod()==1?"現金":"匯款"%></td>
		<td><%=shopOrderVO.getShippingMethod()==0?"宅配":shopOrderVO.getShippingMethod()==1?"超商":"郵寄"%></td>
		<td><%=shopOrderVO.getShippingStatus()==0?"成立":shopOrderVO.getShippingStatus()==1?"出貨":shopOrderVO.getShippingStatus()==2?"完成":"取消"%></td>
		
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do" style="margin-bottom: 0px;">
		<input type="submit" value="修改">
		<input type="hidden" name="itemOrderId"  value="${shopOrderVO.itemOrderId}">
		<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
	</tr>
	
</table>

<h4><a href="backEndPage.jsp">回首頁</a></h4>


</body>
</html>