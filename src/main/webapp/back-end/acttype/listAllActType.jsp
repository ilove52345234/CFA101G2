<%@page import="com.actphoto.model.ActPhotoService"%>
<%@page import="com.acttype.model.ActTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.acttype.model.*"%>
<%@ page import="com.actphoto.model.*"%>

    


<%
	ActPhotoService actPhotoService = new ActPhotoService();

	ActTypeService actTypeService = new ActTypeService();
	
	List<ActTypeVO> list = actTypeService.getAll();
	pageContext.setAttribute("list",list);
	List<ActPhotoVO> photoList =null;
	for(ActTypeVO atvo: list){
		 photoList = actPhotoService.getAllByActCategoryId(atvo.getActCategoryId());
	}
	pageContext.setAttribute("photoList",photoList);

%>

<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
.//如果要JOIN其他table的column要用 jsp:useBean id = "xxxSvc" scope ="page" class= "com.xxx.model.xxxService"
 --%>
 <!-- In short： 先call  deptSvc，並設定useBean，之後再去table>tr>td中用EL取出 deptSvc.getXXX(xxxVO.deptno).dname
  -->
 
 

<!--用useBean 搭配 EL 的可傳參數getXXX)來達成 JOIN的寫法
的yyySvc.getPropertyA(xxxVO.getPropertyB -->




<!-- <td>
	${deptSvc.getOneDept(empVO.getDeptno).dname}; === ${deptVO.dname}
</td>

如果出現  com.dept.model.DeptVO@14213xxxxxx hashCode  
	==>原因： 只成功取得DeptVO並call了Object.toString()，但還沒call getter 取出需要的property.
解法：  在後面加 .dname 取得 dname這一欄 輸出就正常了

 -->
<html>
<head>
<meta charset="UTF-8">
<title>所有活動類別 - listAllActType.jsp</title>

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
	width: 800px;
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
<body bgcolor='white'>

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4>
 -->
 <table id="table-1">
 
	<tr><td>
		 <h3>所有活動類別 - listAllActType.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/acttype/select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/acttype/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>活動類別編號</th>
		<th>活動名稱</th>
		<th>活動照片</th>
		<th>活動宣傳內文</th>
		<th>活動人數上限</th>
		<th>活動人數下限</th>
		<th>活動費用</th>
		<th>評價總人數</th>
		<th>評價總分</th> 
	</tr>
	<jsp:useBean id="actPhotoSvc" scope="page" class="com.actphoto.model.ActPhotoService" />
	
	<%@ include file="page1.file" %>    <%--Use jsp. include to reuse the same block.--%>
	<c:forEach var="ActTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ActTypeVO.actCategoryId}</td>
			<td>${ActTypeVO.actCategoryName}</td>
			<td><img src="<%=request.getContextPath()%>/actphoto/GetFirstActPhotoServlet?actCategoryId=${ActTypeVO.actCategoryId}" width="200" height="200" border="0"></td>
			<td>${ActTypeVO.actCategoryDesc}</td>
			<td>${ActTypeVO.actMaxPart}</td>
			<td>${ActTypeVO.actMinPart}</td>
			<td>${ActTypeVO.actFee}</td> 
			<td>${ActTypeVO.actNumber}</td>
			<td>${ActTypeVO.actTotalScore}</td>			 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/acttype/ActTypeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" disabled >
			     <input type="hidden" name="actCategoryId" value="${ActTypeVO.actCategoryId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
	<%@ include file="page2.file" %>
	

</body>
</html>