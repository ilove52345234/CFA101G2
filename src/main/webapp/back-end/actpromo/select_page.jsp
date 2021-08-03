<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <jsp:include page="../header.jsp" flush="true" />
    <meta charset="UTF-8">
<title>G2 ActPromo: Home</title>

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
   <tr><td><h3>G2 ActPromo: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for G2 ActPromo: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/actpromo/listAllActPromo.jsp'>List</a> all ActPromo.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" >
        <b>輸入活動編號 (如 6001):</b>
        <input type="text" name="actPromotionId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" >
       <b>選擇活動優惠編號:</b>
       <select size="1" name="actPromotionId">
         <c:forEach var="actPromoVO" items="${actPromoSvc.all}" > 
          <option value="${actPromoVO.actPromotionId}">${actPromoVO.actPromotionId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actpromo/ActPromoServlet" >
       <b>選擇活動優惠名稱:</b>
       <select size="1" name="actPromotionId">
         <c:forEach var="actPromoVO" items="${actPromoSvc.all}" > 
          <option value="${actPromoVO.actPromotionId}">${actPromoVO.actPromotionName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>

</ul>


<h3>活動優惠管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/actpromo/addActPromo.jsp'>Add</a> a new ActPromotion.</li>
</ul>

</body>
</html>