<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.acttype.model.*"%>
<%@ page import="com.actphoto.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ActTypeVO actTypeVO = (ActTypeVO)request.getAttribute("actTypeVO");

	ActPhotoService actPhotoSvc = new ActPhotoService();

	ActPhotoVO actPhotoVO = actPhotoSvc.getOneByTypeId(actTypeVO.getActCategoryId());
%> 




<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動類別資料修改 - update_acttype_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>活動類別資料修改 - update_acttype_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet" name="form1">
<table>
	<tr>
		<th>活動類別編號:<font color=red><b>*</b></font></th>
		<td><%=actTypeVO.getActCategoryId()%></td>
	</tr>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="actCategoryName" size="11" value="<%=actTypeVO.getActCategoryName()%>" /></td>
	</tr>
	<tr>
		<td>活動宣傳內文:</td>
		<td><input type="TEXT" name="actCategoryDesc" size="45" value="<%=actTypeVO.getActCategoryDesc()%>" /></td>
	</tr>
	
	<tr>
		<td>活動人數上限:</td>
		<td><input type="TEXT" name="actMaxPart" size="11"	value="<%=actTypeVO.getActMaxPart()%>" /></td>
	</tr>
	<tr>
		<td>活動人數下限:</td>
		<td><input type="TEXT" name="actMinPart" size="11" value="<%=actTypeVO.getActMinPart()%>" /></td>
	</tr>
	<tr>
		<td>活動費用:</td>
		<td><input type="TEXT" name="actFee" size="11" value="<%=actTypeVO.getActFee()%>" /></td>
	</tr>
	</table>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="actCategoryId" value="<%=actTypeVO.getActCategoryId()%>">
	<input type="submit" value="送出修改">
</table>
</FORM>


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actphoto/ActPhotoServlet" name="form2" enctype="multipart/form-data">
 	<table>
    <th>上傳活動照片</th>
    <td>
    	<input type="file" name="actPhoto" id="imgs"  accept="image/*"   onchange="loadFile(this.files)" style="display: none;">
    	<label for="file" style="cursor: pointer;">上傳活動照片</label>
    	<img id="output" width="100px" />
    	</td>
    	<td><img src="<%=request.getContextPath()%>/actphoto/GetFirstActPhotoServlet?actCategoryId=${ActTypeVO.actCategoryId}" width="100px" />
		</td>
  		</tr>
  	</table>
  	<input type="submit" value="送出照片修改"> 
	<input type="hidden" name="actCategoryId" value="<%=actTypeVO.getActCategoryId()%>">
	<input type="hidden" name="action" value="updateActPhoto">
</FORM>

<!-- --------------------------Display select image------------------------------------ -->


<script>
var loadFile = function(obj) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(obj.target.files[0]);
};
</script>
<!-- --------------------------Display select image------------------------------------ -->

</body>
</html>