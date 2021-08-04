<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.carte.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
  CarteVO carteVO = (CarteVO) request.getAttribute("carteVO");
	if(carteVO == null){
		carteVO = new CarteVO();
	}

// -------------------Blob to Base64-------------------
	Blob image = carteVO.getUserPic();
	InputStream inputStream = null;
	ByteArrayOutputStream outputStream = null;
	inputStream = image.getBinaryStream();
	outputStream = new ByteArrayOutputStream();
	byte[] buffer = new byte[inputStream.available()];
	int bytesRead = -1;

	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, bytesRead);
	}

	byte[] imageBytes = outputStream.toByteArray();
	String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	outputStream.flush();
	outputStream.close();
	inputStream.close();
// -------------------Blob to Base64-------------------
%>
<html>
<head>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<title>修改個人名片</title>
<div class="main">
<FORM METHOD="post" ACTION="carte.do" name="form1" enctype="multipart/form-data">
<table>
  <tr>
    <th>上傳大頭貼</th>
    <td>
    	<input type="file"  accept="image/*" name="userPic" id="file"  onchange="loadFile(event)" style="display: none;">
    	<label for="file" style="cursor: pointer;">上傳大頭貼</label>
    	<img id="output" width="100" />
    </td>
    <td><img src="data:image/jpg;base64,<%=base64Image %>" width="100px" /></td>
  </tr>
  <tr>
    <th>暱稱</th>
    <td><input type="text" name="userName" size="45" value="<%=carteVO.getUserName()%>"></td>
  </tr>
<!--   <tr> -->
<!--     <th>隱藏個人名片</th> -->
<!--     <td><button name="button" type="submit" value="carteStatus">隱藏名片</button></td> -->
<!--   </tr> -->
  <tr>
    <th>最後更新時間</th>
    <td>
    	<fmt:formatDate value="<%=carteVO.getUserUpdate()%>" pattern="yyyy-MM-dd hh:mm:ss"/>
    </td>
  </tr>

</table>
	<input type="hidden" name="action" value="updateCarte">
	<input type="submit"  value="確認修改">
</form>
</div>
<style>
table{
  width: 100%;
  border-collapse: collapse;
}

table tr{
  border-bottom: solid 2px white;
}

table tr:last-child{
  border-bottom: none;
}

table th{
  position: relative;
  width: 30%;
  background-color: #7d7d7d;
  color: white;
  text-align: center;
  padding: 10px 0;
}

table th:after{
  display: block;
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  top:calc(50% - 10px);
  right:-10px;
  border-left: 10px solid #7d7d7d;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
}

table td{
  text-align: left;
  width: 70%;
  text-align: center;
  background-color: #eee;
  padding: 10px 0;
}

.main {
  margin: 20px auto;
  item-align: center;
  width: 80%;
}

</style>
</head>
<body>

<!-- --------------------------Display select image------------------------------------ -->
<script>
var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
};
</script>
<!-- --------------------------Display select image------------------------------------ -->

</body>
</html>