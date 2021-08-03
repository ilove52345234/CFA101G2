<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.act.model.*"%>



<%
	ActVO actVO = (ActVO) request.getAttribute("actVO");
	ActService actSvc = new ActService(); 
	ActVO actVO2 = actSvc.getOneById(actVO.getActId());
	pageContext.setAttribute("actVO2", actVO2);
%>



<%-- 取出 對應的DeptVO物件，用empVO.geDeptno()得到的部門id as dID 拿來當deptSvc.getOneDept(dID)--%>
<%-- <%
  DeptService deptSvc = new DeptService();
  DeptVO deptVO = deptSvc.getOneDept(empVO.getDeptno());
%> --%>


<!-- 所以最後面join的部分可以這樣顯示 -->
<%-- <td><%=empVO.getDeptno()%>【<%=deptVO.getDname()%> - <%=deptVO.getLoc()%>】</td> --%>
<!-- 畫面顯示：  部門編號【join來的部門名稱 - join來的部門地點】 -->



<html>
<head>
<meta charset="UTF-8">
<title>活動資料 - listOneAct.jsp</title>

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
	width: 600px;
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


 <table id="table-1">
 	<tr><td>
		 <h3>活動資料 - listOneAct.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/act/actBackendIndex.jsp"><img src="<%= request.getContextPath()%>/back-end/act/images/home.png" width="30" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />


<table>
	<tr>
		<th>活動編號</th>
		<th>活動類別名稱</th>
		<th>活動優惠名稱</th>
		<th>本期活動說明</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動狀態</th>
		<th>本期報名費用</th>
		<th>目前報名人數</th>
		<th>開始報名日期</th>
		<th>報名截止日期</th>
		<th>活動人數上限</th>
		<th>活動人數下限</th>
	</tr>

	
	<tr>
		<td>${actVO2.actId}</td>
		<td>${actTypeSvc.getOneById(actVO2.getActCategoryId()).actCategoryName}</td>
		<td>${actPromoSvc.getOneById(actVO2.getActPromotionId()).actPromotionName}</td>		
		<td>${actVO2.actDescription}</td> 
		<td>${actVO2.actStart}
		</td>
		<td>${actVO2.actEnd}
		</td>
		<td>${actVO2.actStatus}</td>		
		<td>${actVO2.actFee}</td> 
		<td>${actVO2.applicants}</td> 
		<td>${actVO2.partStart}
		</td> 
		<td>${actVO2.partEnd}</td>		
		<td>${actVO2.actMaxPart}</td>
		<td>${actVO2.actMinPart}</td>	
	</tr>
</table>

	
</body>
</html>