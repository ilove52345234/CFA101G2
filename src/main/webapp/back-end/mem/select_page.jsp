<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<title>後台管理</title>
<head>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="../js/include.js"></script>
<style>
		body {
			background-image: url('images/backGround.png');
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: 100% 100%;
			/* opacity: 0.5; */
			}
			
		#mem {
			width: 500px;
			height: 450px;
			color: darkblue;
			letter-spacing: 2px;
			line-height: 26px;
			background-color: lightcyan;
			position: absolute;
			left: 50%;
			top: 50%;
			margin-top: -225px;
			margin-left: -250px;
			border-radius: 10px;
		}
		[type="submit"] {
			border-radius: 10px;
			background-color: pink;
			font-family: DFKai-sb;
		}
</style>

<body>


<div id="header"></div>
<div id="mem" STYLE="margin-bottom: 500px">
<h3 style="text-align:center;">全部會員資料查詢:</h3>
<ul>
  <li><a href='listAllMem.jsp'>List</a> all Members.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/CFA101G2/front-end/mem/mem.do" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="memId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="memId">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memId}">${memVO.memId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/CFA101G2/front-end/mem/mem.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="memId">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memId}">${memVO.memName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

</div>


</body>
</html>