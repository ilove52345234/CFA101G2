<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>


<%
	ActVO actVO = (ActVO)request.getAttribute("actVO");
%>
 <%-- <%= actTypeVO== null %> --%>
 <!-- --${actVO.actId}-- -->
 	


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動資料新增 - addAct.jsp</title>

<style>
	/* bootstrap settings */
	div.md-3.row{
		margin-bottom: 20px;
	}
</style>


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


	<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
	<jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />
   
<table id="table-1">
	<tr><td>
		 <h3>活動資料新增 - addAct.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath()%>/back-end/act/actBackendIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/act/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- below is bootstrap -->

<div class="container">
	<form class="row g-3" METHOD="post" ACTION="<%= request.getContextPath()%>/act/ActServlet" name="form1">
		<div class="mb-3 row">
		  <label  for="actCategoryId" class="form-label">活動類別名稱</label><br>
	
				<select id="actCategoryId" class="form-select" size="1" name="actCategoryId">
					<c:forEach var="actTypeVO" items="${actTypeSvc.all}">
						<option value="${actTypeVO.actCategoryId}" ${(actVO.actCategoryId==actTypeVO.actCategoryId)? 'selected':'' } >${actTypeVO.actCategoryName}
					</c:forEach>
				</select>
		</div><br>
		<div class="mb-3 row">
			<label for="actPromotionId" class="form-label">活動優惠方案</label><br>
	
			<select id="actPromotionId" class="form-select" size="1" name="actPromotionId">
				<c:forEach var="actPromoVO" items="${actPromoSvc.all}">
					<option value="${actPromoVO.actPromotionId}" ${(actVO.actPromotionId==actPromoVO.actPromotionId)? 'selected':'' } >${actPromoVO.actPromotionName}
				</c:forEach>
			</select>
		</div><br>
	
		<div class="mb-3 row">
		  <label for="actDescription" class="form-label">本期活動說明:	</label><br>
		  <textarea id="actDescription" name="actDescription" class="form-control " id="exampleFormControlTextarea1" rows="3" maxlength="50"></textarea>
		</div><br>
		<div class="mb-3 row">
			<label for="actStart" class="form-label">活動開始日期:	</label><br>
			<input id="actStart" type="TEXT" name="actStart" id="actStart">
		</div><br>
		<div class="mb-3 row">
			<label for="actEnd" class="form-label">活動結束日期:	</label><br>
			<input id="actEnd" type="TEXT" name="actEnd" id="actEnd">
		</div><br>
		<div class="mb-3 row">
			<label for="actStatus" class="form-label">活動狀態:	</label><br>
			<select id="actStatus" size="1" name="actStatus" >
				<option value="尚未報名">尚未報名</option>
				<option value="報名中">報名中</option>
				<option value="額滿截止">額滿截止</option>
				<option value="整團取消">整團取消</option>
				<option value="成團">成團</option>
			 </select>
		</div><br>
		<div class="mb-3 row">
			<label for="actFee" class="form-label">本期報名費用:	</label><br>
			<input id="actFee" type="TEXT" name="actFee" size="11"
					 value="<%= (actVO==null)? "2200" : actVO.getActFee()%>" />
		</div><br>
		<div class="mb-3 row">
			<label for="partStart" class="form-label">開始報名日期:	</label><br>
			<input id="partStart" type="TEXT" name="partStart" id="partStart">
	
		</div><br>
		<div class="mb-3 row">
			<label for="partEnd" class="form-label">報名截止日期:	</label><br>
			<input id="partEnd" type="TEXT" name="partEnd" id="partEnd">
	
		</div><br>
		<div class="mb-3 row">
			<label for="actMaxPart" class="form-label">活動人數上限:	</label><br>
			<input id="actMaxPart" type="TEXT" name="actMaxPart" size="11"
					 value="<%= (actVO==null)? "30" : actVO.getActMaxPart()%>" />
		</div><br>
		<div class="mb-3 row">
			<label for="actMinPart" class="form-label">活動人數下限:	</label><br>
			<input id="actMinPart" type="TEXT" name="actMinPart" size="11"
					 value="<%= (actVO==null)? "5" : actVO.getActMinPart()%>" />
		</div><br>
		<br>
		<input type="hidden" name="action" value="insert">
		<button type="submit">送出新增</button>
	</form>
</div>





<%-- <h3>資料新增:</h3>

錯誤表列，

>>>從controller中，每個error msg linkedlist來儲存每次validation&exception流程中 call list.add(errMsg)
 所存放的一系列errors including:

 	1. input validation errors by "errorMsgs.add("員工姓名: 請勿空白");".
 	2. other possible errors in "catch" block by "errorMsgs.add(e.getMessage());".

  	ref: https://www.geeksforgeeks.org/throwable-getmessage-method-in-java-with-examples/

  will call RequestDispatcher(JSP url) to "forward request" with error(s) set in attribute "errorMsgs"
  back to the original-request-sent  JSP, and show as below.



<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/act/ActServlet" name="form1">
<table>
	<tr>
		<td>活動類別編號:</td>
		<td><input type="TEXT" name="actCategoryId" size="11" 
			 value="<%= (actTypeVO==null)? "1" : actTypeVO.getActCategoryId()%>" /></td>
	</tr>
	<tr>
		<td>活動類別名稱:<font color=red><b>*</b></font></td>
		<td>
		<select size="1" name="actCategoryId">
			<c:forEach var="actTypeVO" items="${actTypeSvc.all}">
				<option value="${actTypeVO.actCategoryId}" ${(actVO.actCategoryId==actTypeVO.actCategoryId)? 'selected':'' } >${actTypeVO.actCategoryName}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>活動優惠編號:</td>
		<td><select size="1" name="actPromotionId">
			<c:forEach var="actPromoVO" items="${actPromoSvc.all}">
				<option value="${actPromoVO.actPromotionId}" ${(actVO.actPromotionId==actPromoVO.actPromotionId)? 'selected':'' } >${actPromoVO.actPromotionName}
			</c:forEach>
		</select></td>	 
	</tr>
	<tr>
		<td>本期活動說明:</td>
		<td><input type="TEXT" name="actDescription" size="50"
			 value="<%= (actVO==null)? "xxxxxxyyyyyy" : actVO.getActDescription()%>" /></td>
	</tr>
	<tr>
		<td>活動開始日期</td>
		<td><input type="TEXT" name="actStart" id="actStart"></td>
	</tr>
	<tr>
		<td>活動結束日期:</td>
		<td><input type="TEXT" name="actEnd" id="actEnd"></td> 
	</tr>
 	<tr>
		<td>活動狀態:</td>
		<td><input type="TEXT" name="actStatus" size="11" 
			 value="<%= (actVO==null)? "0" : actVO.getActStatus()%>" /></td>
	</tr>
 	<tr>
		<td>本期報名費用:</td>
		<td><input type="TEXT" name="actFee" size="11" 
			 value="<%= (actVO==null)? "2200" : actVO.getActFee()%>" /></td>
	</tr>
 	<tr>
		<td>目前報名人數:</td>
		<td><input type="TEXT" name="applicants" size="11" 
			 value="<%= (actVO==null)? "0" : actVO.getApplicants()%>" /></td>
	</tr>
 	<tr>
		<td>開始報名日期:</td>
		<td><input type="TEXT" name="partStart" id="partStart"></td>
	</tr>
 	<tr>
		<td>報名截止日期:</td>
		<td><input type="TEXT" name="partEnd" id="partEnd"></td>
	</tr>
 	<tr>
		<td>活動人數上限:</td>
		<td><input type="TEXT" name="actMaxPart" size="11" 
			 value="<%= (actVO==null)? "30" : actVO.getActMaxPart()%>" /></td>
	</tr>
 	<tr>
		<td>活動人數下限:</td>
		<td><input type="TEXT" name="actMinPart" size="11" 
			 value="<%= (actVO==null)? "5" : actVO.getActMinPart()%>" /></td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

 --%>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  Timestamp actStart = null;
  try {
	  actStart = actVO.getActStart();
   } catch (Exception e) {
	  actStart = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  Timestamp actEnd = null;
  try {
	  actEnd = actVO.getActEnd();
   } catch (Exception e) {
	  actEnd = new java.sql.Timestamp(System.currentTimeMillis());
   }
  //partStart
  Timestamp partStart = null;
  try {
	  partStart = actVO.getPartStart();
   } catch (Exception e) {
	  partStart = new java.sql.Timestamp(System.currentTimeMillis());
   }
  //partEnd
  Timestamp partEnd = null;
  try {
	  partEnd = actVO.getPartEnd();
   } catch (Exception e) {
	   partEnd = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
        $.datetimepicker.setLocale('zh');
        $('#actStart').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=actStart%>' 
		
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#actEnd').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=actEnd%>'
         });
        $('#partStart').datetimepicker({
  	       theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
  		   value: '<%=partStart%>'
          });
        $('#partEnd').datetimepicker({
  	       theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
  		   value: '<%=partEnd%>'
          });
</script>
</html>