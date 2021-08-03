<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>


<%
	ActService actService = new ActService();
	List<ActVO> list = actService.getAll();
	pageContext.setAttribute("list",list);
%>


 
<jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
<jsp:useBean id="actPromoSvc" scope="page" class="com.actpromo.model.ActPromoService" />

<!-- <!DOCTYPE html> -->
<html lang="en">
<head>
    <jsp:include page="../header.jsp" flush="true" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>ActBackEnd management</title>
    
    <style>
    	#addAct {
  			display: inline-block;
  			float: right;
  			
		}
		/* #complexSearch {
			
		} */
    </style>
</head>
<body>

<%--     <jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
 --%>    
      <!-- 模糊查詢 -->
<span>      
     <form class="form-inline" method="post" action="<%=request.getContextPath()%>/act/ActServlet">
            <span class="form-group">            
             <b>選擇活動類別:</b>
		       <select size="1" name="ACT_CATEGORY_ID" >
		         <c:forEach var="actVO" items="${actTypeSvc.all}" > 
		          <option value="${actVO.actCategoryId}">${actVO.actCategoryName}
		         </c:forEach>   
		       </select> 
                   
            </span>       
            <span class="form-group">
            <b>選擇活動狀態:</b>
            <select   size="1" name="ACT_STATUS" >
              <option value="尚未報名">尚未報名</option>
              	  <option value="">---</option>
		          <option value="報名中">報名中</option>
		          <option value="額滿截止">額滿截止</option>
		          <option value="整團取消">整團取消</option>
		          <option value="成團">成團</option>
		       </select>     
           </span>      
            <button id="complexSearch" class="btn btn-outline-info" type="submit" class="btn ">查詢</button>
            <input type="hidden" name="action" value="ComplexSearch">
      </form>
    </span>
      <span>             
      	<button id="addAct" type="button" class="btn btn-outline-primary"  onclick="self.location.href='<%= request.getContextPath()%>/back-end/act/addAct.jsp'">新增活動</button>
      </span>

    <div class="table-responsive col-12" >
        <h4>活動報名管理</h4>
          <table class="table table-hover responsive table-condensed"> 
          <!-- order-table -->
              <thead>
                <tr>
                  <th>活動編號</th>
                  <th>活動類別名稱</th>
<!--              <th>活動優惠名稱</th>-->              
				          <th>本期活動說明</th>
                  <th>活動日期起</th>
                  <th>活動日期迄</th>
                  <th>活動狀態</th>
                  <th>本期報名費用</th>
                  <th>目前報名人數</th>
                  <th>開始報名日期</th>
                  <th>報名截止日期</th>
                  <th>活動人數上限</th>
                  <th>活動人數下限</th>
                  <th>修改活動</th>
                  <th>列出參加者</th>
                  <th>舉辦活動</th>
                  <th>取消活動</th>
                </tr>
              </thead>
              <%@ include file="page1.file" %>    <%--Use jsp. include to reuse the same block.--%>
              <c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
              <tbody>
                <tr>
                <!-- <a href="test_emp.do?empno=${empVO.empno}&action=getOne_From06">${empVO.empno}</a> -->
                        <td>
                        <%-- <a href="<%= request.getContextPath()%>/act/StartActServlet?actId=${actVO.actId}">${actVO.actId}
                        </a> --%>
                        ${actVO.actId}</td>
                        <td>${actTypeSvc.getOneById(actVO.actCategoryId).actCategoryName}</td>
<%--                         <td>${actPromoSvc.getOneById(actVO.actPromotionId).actPromotionName}</td> --%>
                        <td>${actVO.actDescription}</td>
                        <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.actStart}"/></td>
                        <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.actEnd}"/></td>
                        <td id=actStatus>${actVO.actStatus}</td>
                        <td>${actVO.actFee}</td>
                        <td id=applicants>${actVO.applicants}</td>
                        <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.partStart}"/></td>
                        <td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value ="${actVO.partEnd}"/></td>
                        <td id=actMaxPart>${actVO.actMaxPart}</td>
                        <td id=actMinpart>${actVO.actMinPart}</td>
                        <td>
                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/ActServlet" style="margin-bottom: 0px;">
                             <button class="btn btn-outline-primary" type="submit">修改</button>
                             <input type="hidden" name="actId"  value="${actVO.actId}" >
                             <input type="hidden" name="action"	value="getOne_For_Update">
                         </FORM>
                        </td>
                        <td>
                          <form name="actPartList" METHOD="post" ACTION="<%=request.getContextPath()%>/actpart/ActPartServlet">  
                            <button class="btn btn-outline-primary" type="submit">參加人員管理</button>
                            <input id="thisactId" type="hidden" name="actId"  value="${actVO.actId}">
                            <input type="hidden" name="action" value="getAllPartsByActId">
                          </form>
                        </td>
                                 <td>
                          <form name="actPartList" METHOD="post" ACTION="<%=request.getContextPath()%>/act/StartActServlet">  
                            <button class="btn btn-outline-primary" type="submit">舉辦活動</button>
                            <input id="thisactId" type="hidden" name="actId"  value="${actVO.actId}">
                            <input type="hidden" name="action" value="startActFake">
                          </form>
                        </td>
                                 <td>
                          <form name="actPartList" METHOD="post" ACTION="<%=request.getContextPath()%>/act/CancelActServlet">  
                            <button class="btn btn-outline-primary" type="submit">取消活動</button>
                            <input id="thisactId" type="hidden" name="actId"  value="${actVO.actId}">
                            <input type="hidden" name="action" value="cancelActFake">
                          </form>
                        </td>
                      </tr>
                    </c:forEach>
              </tbody>
          </table>
        </div>
        <%@ include file="page2.file" %>
      
     
     

<!-- 以下做0401 bootstrap彈出視窗 include -->      
<%--  <c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="<%= request.getContextPath()%>/back-end/act/listOneAct.jsp"></jsp:include>
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>    --%>

      <script>
      	
        //舉辦活動trigger條件：
        //如果 datetime.now in(partStart, partEnd)  && actVO.applicants > actMinPart ==>成功 else 都失敗 -> then 改變 act.actStatus

        // 5. 無條件取消活動 + 鍾學長jQuery的SweetAlert做取消前的double check 
        
        // 6. 舉辦活動條件： 
        // if 現在時間介於(報名開始, 報名結束)  &&  目前參加總人數 > 最低參加人數門檻 
        // ==>成功舉辦 && 改變活動狀態

        // else 都不管，用quartz或原生的timetask做排程，到報名結束前一直check 人數超過門檻了沒（optional)

        //取消活動條件：any;
        //無條件取消活動 + 鍾學長jQuery的SweetAlert做double check before cancel;

        //參加人員管理：
        //先勾選actlist中的一個row，取得該row的actId後按 參加人員管理的button,跳轉到actPartList（需要帶parameter =actId）過去
        //實作參考  勾選+delete 改成 勾選+跳轉頁面

        //模糊查詢： 用 SQL where 1=1 AND 條件1 like ? AND 條件2 like ? AND 條件3 like ?（有多少查詢 input,就 concate多少個 AND xxx like ? 條件進SQL）
        //實作使用 StringBuilder.append（ "AND key like ?") 來動態產生SQL條件


      </script>
    

</body>
</html>