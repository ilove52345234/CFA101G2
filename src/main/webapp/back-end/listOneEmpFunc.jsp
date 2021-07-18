<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>



<html>
<head>
	<!-- 指定字符集 -->
	<meta charset="utf-8">
	<!-- 使用Edge最新的瀏覽器的渲染方式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- viewport視口：網頁可以根據設置的寬度自動進行適配，在瀏覽器的內部虛擬一個容器，容器的寬度與設備的寬度相同。
    width: 默認寬度與設備的寬度相同
    initial-scale: 初始的縮放比，為1:1 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3個meta標籤*必須*放在最前面，任何其他內容都*必須*跟隨其後！ -->
	<title>後台管理系統</title>

	<!-- 1. 導入CSS的全局樣式 -->
	<link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
	<!-- 2. jQuery導入，建議使用1.9以上的版本 -->
	<script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>
	<!-- 3. 導入bootstrap的js文件 -->
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
		 <h3>管理員權限 -  ${empName}</h3>
	</td></tr>
</table>



<table border="1" class="table table-bordered table-hover">
	<tr class="success">
		<th>權限編號</th>
		<th>權限名稱</th>
		<th>功能說明</th>
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