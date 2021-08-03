<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acttype.model.*"%>
<%-- <%@ page import="com.actphoto.model.*"%>
 --%>

<%
	ActTypeVO actTypeVO = (ActTypeVO)request.getAttribute("actTypeVO");
/* 	ActPhotoVO actPhotoVO = (ActPhotoVO) request.getAttribute("actPhotoVO");
 */%>

 

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>


<title>活動類別資料新增 - addActType.jsp</title>

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
		 <h3>活動類別資料新增 - actType.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>
<div>
<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/acttype/ActTypeServlet" name="form1" id="insert_form" enctype="multipart/form-data">
<h3>資料新增:</h3>


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<table>
<%-- 	<tr>
		<td>活動類別編號:</td>
		<td><input type="TEXT" name="actCategoryId" size="11" 
			 value="<%= (actTypeVO==null)? "1" : actTypeVO.getActCategoryId()%>" /></td>
	</tr> --%>
	<tr>
		<td>活動類別名稱:</td>
		<td><input type="TEXT" name="actCategoryName" size="11"
			 value="<%= (actTypeVO==null)? "快艇衝浪" : actTypeVO.getActCategoryName()%>" /></td>
	</tr>
	<tr>
		<td>活動宣傳內文:</td>
		<td><input type="TEXT" name="actCategoryDesc" size="45"
			 value="<%= (actTypeVO==null)? "Wakesurfing by the resort" : actTypeVO.getActCategoryDesc()%>" /></td>
	</tr>
	<tr>
		<td>活動人數上限:</td>
		<td><input type="TEXT" name="actMaxPart" size="11"
			 value="<%= (actTypeVO==null)? "30" : actTypeVO.getActMaxPart()%>" /></td>
	</tr>
	<tr>
		<td>活動人數下限:</td>
		<td><input type="TEXT" name="actMinPart" size="11"
			 value="<%= (actTypeVO==null)? "2" : actTypeVO.getActMinPart()%>" /></td>
	</tr>
	<tr>
		<td>活動費用:</td>
		<td><input type="TEXT" name="actFee" size="11" 
			 value="<%= (actTypeVO==null)? "2500" : actTypeVO.getActFee()%>" /></td>
	</tr>
	

	
</table>
	<tr>
    <th>上傳活動類別照片</th>
    <td>
    	<input type="file"  accept="image/*" name="actPhotoFile1" id="file"  onchange="loadFile(event)" style="display: none;">
    	<label for="file" style="cursor: pointer;">上傳活動類別照片</label>
    	<img id="output" width="100" />
    </td>
  </tr>
	<input type="hidden" name="action" value="insert">
	<input type="submit"  value="確認">
				
</FORM>
	</div>


<!-- --------------------------Display select image------------------------------------ -->
<script>
var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
};
</script>

</body>

</html>