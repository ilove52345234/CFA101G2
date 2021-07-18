<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>



<html>
<head>
	<!-- ���w�r�Ŷ� -->
	<meta charset="utf-8">
	<!-- �ϥ�Edge�̷s���s��������V�覡 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- viewport���f�G�����i�H�ھڳ]�m���e�צ۰ʶi��A�t�A�b�s���������������@�Ӯe���A�e�����e�׻P�]�ƪ��e�׬ۦP�C
    width: �q�{�e�׻P�]�ƪ��e�׬ۦP
    initial-scale: ��l���Y���A��1:1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- �W�z3��meta����*����*��b�̫e���A�����L���e��*����*���H���I -->
	<title>��x�޲z�t��</title>

	<!-- 1. �ɤJCSS�������˦� -->
	<link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
	<!-- 2. jQuery�ɤJ�A��ĳ�ϥ�1.9�H�W������ -->
	<script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>
	<!-- 3. �ɤJbootstrap��js��� -->
	<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
	<style type="text/css">
		td, th {
			text-align: center;
		}
	</style>
</head>
<body bgcolor='white'>


<table id="table-1" class="myTable">
	<tr><td>
		 <h3>�޲z���v�� -  ${empName}</h3>
	</td></tr>
</table>



<table border="1" class="table table-bordered table-hover">
	<tr class="success">
		<th>�v���s��</th>
		<th>�v���W��</th>
		<th>�\�໡��</th>
	</tr>

	<c:forEach items="${funcByEmpId}" var="funcByEmpId" varStatus="s">
		<tr>
			<td>${funcByEmpId.funcId}</td>
			<td>${funcByEmpId.funcName}</td>
			<td>${funcByEmpId.funcDesc}</td>
		</tr>

	</c:forEach>


</table>

</body>
</html>