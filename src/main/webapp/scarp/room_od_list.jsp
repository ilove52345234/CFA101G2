<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<!-- 網頁使用的語言 -->
<html lang="en">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的瀏覽器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport視口：網頁可以根據設置的寬度自動進行適配，在瀏覽器的內部虛擬一個容器，容器的寬度與設備的寬度相同。
    width: 默認寬度與設備的寬度相同
    initial-scale: 初始的縮放比，為1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3個meta標籤*必須*放在最前面，任何其他內容都*必須*跟隨其後！ -->
    <title>後台管理系統</title>

    <!-- 1. 導入CSS的全局樣式 -->
    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 導入bootstrap的js文件 -->
    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>


</head>
<body>
<div class="container">
    <h3 style="text-align: center">房間列表</h3>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>

    <%-- 成功 --%>
    <c:if test="${not empty Msgs}">

    </c:if>


    <%
        request.removeAttribute("Msgs");
    %>

    <div style="float: left;">

        <form class="form-inline" action="<%=request.getContextPath()%>/room/roomServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">房間號碼</label>
                <input type="text" name="ROOM_ID" value="${condition.ROOM_ID}" class="form-control"
                       id="exampleInputName2">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">住客姓名</label>
                <input type="text" name="ROOM_INFORMATION" value="${condition.ROOM_INFORMATION}" class="form-control"
                       id="exampleInputEmail2">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">房間使用情況</label>
                <select name="ROOM_CHECK_STATUS" class="form-control" id="exampleInputEmail3">


<%--                    <c:if test="${  condition.ROOM_CHECK_STATUS eq ''}">--%>
<%--                        <option value= "" selected>查全部</option>--%>
<%--                        <option value=0 >未使用</option>--%>
<%--                        <option value=1>待入住</option>--%>
<%--                        <option value=2>已入住</option>--%>
<%--                        <option value=3>待退房</option>--%>
<%--                    </c:if>--%>

                    <c:if test="${ condition.ROOM_CHECK_STATUS == 0 }">
                        <option value= "" >查全部</option>
                        <option value=0 selected>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3>待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 1}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1 selected>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3>待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 2}">
                        <option value="" >查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2 selected>已入住</option>
                        <option value=3>待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 3}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3 selected>待退房</option>
                    </c:if>


                </select>
            </div>
            <button type="submit" class="btn btn-default">查詢</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <%--${pageContext.request.contextPath}同樣可用--%>

        <a class="btn btn-primary" href="<%=request.getContextPath()%>/back-end/add.jsp">新增管理員</a>

        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected"> 刪除選中</a>

    </div>


    <%--  用表單將整個table包起來,用來提交所有的勾選--%>
    <%--    <%=request.getContextPath()%>/emp/delSelectedServlet?currentPage=${pb.currentPage}&rows=5--%>
    <form id="empForm" action="" method="post">

        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>列表編號</th>
                <th>房間號碼</th>
                <th>房型類別</th>
                <th>房間使用情況</th>
                <th>房間狀態</th>
                <th>住客姓名與資訊</th>
                <%--                <th>住客資訊</th>--%>
                <th>操作</th>
            </tr>
            <%--        按選中送出時提交value出去--%>
            <c:forEach items="${pb.list}" var="rms" varStatus="s">
                <tr>
                        <%--                    <td><input type="checkbox" name="delEmpId" value="${emps.empId}"></td>--%>
                    <td>${(pb.rows  * pb.currentPage)-pb.rows+s.count }</td>
                    <td>${rms.roomId}</td>
                    <td>
                        <c:forEach items="${rtVOS}" var="rtVO">
                            <c:if test="${rtVO.roomCategoryId eq rms.roomCategoryId}">${rtVO.roomName}</c:if>
                        </c:forEach></td>
                    <td><c:if test="${rms.roomCheckStatus == 0 }">
                        未使用
                    </c:if>
                        <c:if test="${rms.roomCheckStatus == 1 }">
                            待入住
                        </c:if>
                        <c:if test="${rms.roomCheckStatus == 2 }">
                            已入住
                        </c:if>
                        <c:if test="${rms.roomCheckStatus == 3 }">
                            待退房
                        </c:if>

                    </td>
                    <td>${rms.roomSaleStatus==0?'可用':'不可用'}</td>
                        <%--                    <td>${emps.empStatus==1?'啟用中':'未啟用'}</td>--%>
                    <td>
                        <c:if test="${rms.roomSaleStatus == 0 }">
                            未入住
                        </c:if>
                        <c:if test="${rms.roomSaleStatus == 1 }">
                            <A HREF="javascript:presses${s.count}()">${rms.roomInformation}</a>
                        </c:if>

                    </td>

                    <td><a class="btn btn-default btn-sm"
                        <%--                        <%=request.getContextPath()%>/emp/GetOneEmpServlet?empId=${emps.empId}&currentPage=${pb.currentPage}&rows=5--%>
                           href="">修改狀態</a>&nbsp;

                        <c:if test="${rms.roomSaleStatus == 0 }">
                            <a class="btn btn-default btn-sm"
                               href="">入住</a>
                        </c:if>

                        <c:if test="${rms.roomSaleStatus == 1 }">
                            <a class="btn btn-default btn-sm"
                               href="">退房</a>
                        </c:if>


                    </td>
                </tr>
                <%--                javascript:deleteEmp(${emps.empId});--%>
                <%--                <script>--%>
                <%--                    function presses${s.count}() {--%>
                <%--                        document.open("<%=request.getContextPath()%>/emp/EmpFuncServlet?empId=${emps.empId}&empName=${emps.empName}", "", "height=250,width=850,left=65,top=157,resizable=yes,scrollbars=yes");--%>
                <%--                    }--%>
                <%--                </script>--%>

            </c:forEach>


        </table>


    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1 }">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != 1 }">
                <li>
                    </c:if>

                    <a href="<%=request.getContextPath()%>/room/roomServlet?currentPage=${pb.currentPage != 1? pb.currentPage - 1 :pb.currentPage}&rows=5&ROOM_ID=${condition.ROOM_ID}&ROOM_INFORMATION=${condition.ROOM_INFORMATION}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i }">
                        <li class="active"><a
                                href="<%=request.getContextPath()%>/room/roomServlet?currentPage=${i}&rows=5&ROOM_ID=${condition.ROOM_ID}&ROOM_INFORMATION=${condition.ROOM_INFORMATION}&">${i}</a>
                        </li>
                    </c:if>

                    <c:if test="${pb.currentPage != i }">
                        <li>
                            <a href="<%=request.getContextPath()%>/room/roomServlet?currentPage=${i}&rows=5&ROOM_ID=${condition.ROOM_ID}&ROOM_INFORMATION=${condition.ROOM_INFORMATION}">${i}</a>
                        </li>
                    </c:if>

                </c:forEach>


                <c:if test="${pb.currentPage == pb.totalPage }">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != pb.totalPage }">
                <li>
                    </c:if>

                    <a href="<%=request.getContextPath()%>/room/roomServlet?currentPage=${pb.currentPage != pb.totalPage? pb.currentPage + 1 :pb.currentPage}&rows=5&ROOM_ID=${condition.ROOM_ID}&ROOM_INFORMATION=${condition.ROOM_INFORMATION}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    總共有${pb.totalCount}間房間，共${pb.totalPage}頁
                </span>

            </ul>
        </nav>
    </div>
</div>


<script>
    <%--function deleteEmp(empId) {--%>
    <%--    //給安全提示--%>
    <%--    if (confirm("確定要刪除嗎?")) {--%>
    <%--        //路徑--%>
    <%--        location.href = "<%=request.getContextPath()%>/emp/DelEmpServlet?empId=" + empId + "&requestURL=<%=request.getServletPath()%>&currentPage=${pb.currentPage}&rows=5";--%>
    <%--    }--%>
    <%--}--%>


    <%--window.onload = function () {--%>
    <%--    //給刪除選中按鈕添加點擊事件--%>
    <%--    document.getElementById("delSelected").onclick = function () {--%>
    <%--        //判斷是否有選中至少一個--%>
    <%--        let flag = false;--%>

    <%--        if (confirm("確定要刪除所有選中嗎?")) {--%>

    <%--            let elementsByName = document.getElementsByName("delEmpId");--%>

    <%--            for (let i = 0; i < elementsByName.length; i++) {--%>
    <%--                //如果有一個是選中的話--%>
    <%--                if (elementsByName[i].checked) {--%>
    <%--                    flag = true;--%>
    <%--                    break;--%>
    <%--                }--%>
    <%--            }--%>


    <%--            if (flag) {--%>
    <%--                //表單提交的一個方法--%>
    <%--                document.getElementById("empForm").submit();--%>
    <%--            } else {--%>
    <%--                alert("你什麼都沒選啊");--%>
    <%--            }--%>
    <%--        }--%>

    <%--    }--%>

    <%--    //獲取第一個checkbox--%>
    <%--    document.getElementById("firstCB").onclick = function () {--%>
    <%--        //獲取下方的所有的checkbox--%>
    <%--        let elementsByName = document.getElementsByName("delEmpId");--%>

    <%--        for (let i = 0; i < elementsByName.length; i++) {--%>
    <%--            //設置這些checkbox的狀態=firstCB的狀態--%>
    <%--            elementsByName[i].checked = this.checked;--%>
    <%--        }--%>
    <%--    }--%>

    <%--}--%>


</script>
</body>
</html>