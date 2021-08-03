<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>G2 Act: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>G2 Act: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for G2 Act: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}"> <%-- errorMsgs from controller's every exception --%>
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/act/listAllAct.jsp'>List</a> all Act.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" >
        <b>輸入活動編號 (如 8001):</b>
        <input type="text" name="actId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
  <jsp:useBean id="e" scope="page" class="com.acttype.model.ActTypeService" />
  
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" >
       <b>選擇活動編號:</b>
       <select size="1" name="actId">
         <c:forEach var="actVO" items="${actSvc.all}" > 
          <option value="${actVO.actId}">${actVO.actId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<%--   <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" >
       <b>選擇活動類別名稱:</b>
       <select size="1" name="actId">
         <c:forEach var="actVO" items="${actSvc.all}" > 
          <option value="${actVO.actId}">${actTypeSvc.getOneById(actVO.actCategoryId).actCategoryName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li> --%>
</ul>


<h3>活動管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/act/addAct.jsp'>Add</a> a new Act.</li>
</ul>

</body>
</html>