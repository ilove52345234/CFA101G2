<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�d�ݭq�����-listOneOrderDetail</title>
</head>

<body>
<table>
	<tr>
		<th>�q��s��</th>
		<th>�|���s��</th>
		<th>�q�榨�߮ɶ�</th>
		<th>�q���`���B</th>
		<th>�I�ڤ覡</th>
		<th>�B�e�覡</th>
		<th>�B�e���A</th>
		<th>�ק�</th>
	</tr>
	
	<tr>
		<td><%=shopOrderDetailVO.getItemOrderId()%></td>
		<td><%=shopOrderVO.getMemId()%></td>
		<td><%=shopOrderVO.getItemOrderDate()%></td>
		<td><%=shopOrderVO.getItemAmounts()%></td>
		<td><%=shopOrderVO.getPaymentMethod()==0?"�H�Υd":shopOrderVO.getPaymentMethod()==1?"�{��":"�״�"%></td>
		<td><%=shopOrderVO.getShippingMethod()==0?"�v�t":shopOrderVO.getShippingMethod()==1?"�W��":"�l�H"%></td>
		<td><%=shopOrderVO.getShippingStatus()==0?"����":shopOrderVO.getShippingStatus()==1?"�X�f":shopOrderVO.getShippingStatus()==2?"����":"����"%></td>
		
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/shoporder/shoporder.do" style="margin-bottom: 0px;">
		<input type="submit" value="�ק�">
		<input type="hidden" name="itemOrderId"  value="${shopOrderVO.itemOrderId}">
		<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
	</tr>
	
</table>

<h4><a href="backEndPage.jsp">�^����</a></h4>
</body>
</html>