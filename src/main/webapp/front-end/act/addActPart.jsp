<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actpart.model.*"%>
<%@ page import="com.act.model.*"%>

<%
	ActPartVO actPartVO = (ActPartVO) request.getAttribute("actPartVO");
	ActVO actVO = (ActVO) request.getAttribute("actVO");
	pageContext.setAttribute("actVO", actVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>報名活動</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min1.css">
    <link rel="stylesheet" href="../css/Projects-Horizontal1.css">
    <link rel="stylesheet" href="../css/Registration-Form-with-Photo1.css">
    <link rel="stylesheet" href="../css/styles1.css">
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>活動資料報名新增 - addActPart.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/act/listAllActType2.jsp"><img
						src="<%=request.getContextPath()%>/front-end/images/Home.png" width="100"
						height="100" border="0"></a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div>
		<div class="container"></div>
	</div>
	<div class="projects-horizontal">
		<div class="container">
			<div class="intro"></div>
		</div>
	</div>
	<div></div>
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder">
				<img src="<%=request.getContextPath()%>/front-end/images/1.png" width="500PX"
					height="408PX" border="0">
			</div>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/actpart/ActPartServlet"
				name="form1">
				<jsp:useBean id="actSvc" scope="page"
					class="com.act.model.ActService" />
				<h2 class="text-center">
					<strong>報名資訊</strong>
				</h2>
				<div class="form-group">${actVO.actId}</div>
				<div class="form-group"> <input type="TEXT" name="memId" size="45"
						value="<%=(actPartVO == null) ? "1" : actPartVO.getMemId()%>" />
				</div>
				<div class="form-group">
					<input name="actApplyDate" id="f_date1" type="text">
				</div>
				<div class="form-group">
					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="actId" value="<%=actVO.getActId()%>">
					<input type="submit" value="送出新增">
				</div>
			</form>
		</div>
	</div>
	<div></div>
	<script src="../js/jquery.min1.js"></script>
	<script src="../bootstrap/js/bootstrap.min1.js"></script>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp actApplyDate = null;
	try {
		actApplyDate = actPartVO.getActApplyDate();
	} catch (Exception e) {
		actApplyDate = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:00:00',         //format:'Y-m-d H:i:s',
		   value: '<%=actApplyDate%>', // value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		minDate : '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

</html>