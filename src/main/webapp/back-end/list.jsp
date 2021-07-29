<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

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
    <script src="js/include2.js"></script>


</head>
<body>

<div id="header"></div>
<div class="container" style="margin-top: 100px">
    <h3 style="text-align: center">管理員列表</h3>

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

        <form class="form-inline" action="<%=request.getContextPath()%>/emp/findEmpByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">管理員號碼</label>
                <input type="text" name="EMP_ID" value="${condition.EMP_ID}" class="form-control"
                       id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">姓名</label>
                <input type="text" name="EMP_NAME" value="${condition.EMP_NAME}" class="form-control"
                       id="exampleInputName3">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">新增日期</label>
                <input type="text" name="EMP_ADD_DATE" value="${condition.EMP_ADD_DATE}" class="form-control"
                       id="exampleInputEmail2">
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
    <form id="empForm" action="<%=request.getContextPath()%>/emp/delSelectedServlet?currentPage=${pb.currentPage}&rows=5" method="post">

        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCB"></th>
                <th>列表號碼</th>
                <th>管理員號碼</th>
                <th>姓名</th>
                <th>帳號</th>
                <th>密碼</th>
                <th>管理員狀態</th>
                <th>新增日期</th>
                <th>權限</th>
                <th>操作</th>
            </tr>
            <%--        按選中送出時提交value出去--%>
            <c:forEach items="${pb.list}" var="emps" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="delEmpId" value="${emps.empId}"></td>
                    <td>${(pb.rows  * pb.currentPage)-pb.rows+s.count }</td>
                    <td>${emps.empId}</td>
                    <td>${emps.empName}</td>
                    <td>${emps.empAccount}</td>
                    <td>${emps.empPassword}</td>
                    <td>
                            ${emps.empStatus==1?'啟用中':'未啟用'}
                    </td>
                    <td>${emps.empAddDate}</td>
                    <td><A HREF="javascript:presses${s.count}()">查看</a></td>
                    <td><a class="btn btn-default btn-sm"
                           href="<%=request.getContextPath()%>/emp/GetOneEmpServlet?empId=${emps.empId}&currentPage=${pb.currentPage}&rows=5">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="javascript:deleteEmp(${emps.empId});">刪除</a>
                    </td>
                </tr>

                <script>
                    function presses${s.count}() {
                        document.open("<%=request.getContextPath()%>/emp/EmpFuncServlet?empId=${emps.empId}&empName=${emps.empName}", "", "height=250,width=850,left=65,top=157,resizable=yes,scrollbars=yes");
                    }
                </script>

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

                    <a href="<%=request.getContextPath()%>/emp/findEmpByPageServlet?currentPage=${pb.currentPage != 1? pb.currentPage - 1 :pb.currentPage}&rows=5&EMP_ID=${condition.EMP_ID}&EMP_NAME=${condition.EMP_NAME}&EMP_ADD_DATE=${condition.EMP_ADD_DATE}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i }">
                        <li class="active"><a
                                href="<%=request.getContextPath()%>/emp/findEmpByPageServlet?currentPage=${i}&rows=5&EMP_ID=${condition.EMP_ID}&EMP_NAME=${condition.EMP_NAME}&EMP_ADD_DATE=${condition.EMP_ADD_DATE}">${i}</a>
                        </li>
                    </c:if>

                    <c:if test="${pb.currentPage != i }">
                        <li>
                            <a href="<%=request.getContextPath()%>/emp/findEmpByPageServlet?currentPage=${i}&rows=5&EMP_ID=${condition.EMP_ID}&EMP_NAME=${condition.EMP_NAME}&EMP_ADD_DATE=${condition.EMP_ADD_DATE}">${i}</a>
                        </li>
                    </c:if>

                </c:forEach>


                <c:if test="${pb.currentPage == pb.totalPage }">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != pb.totalPage }">
                <li>
                    </c:if>

                    <a href="<%=request.getContextPath()%>/emp/findEmpByPageServlet?currentPage=${pb.currentPage != pb.totalPage? pb.currentPage + 1 :pb.currentPage}&rows=5&EMP_ID=${condition.EMP_ID}&EMP_NAME=${condition.EMP_NAME}&EMP_ADD_DATE=${condition.EMP_ADD_DATE}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    總共有${pb.totalCount}位管理員，共${pb.totalPage}頁
                </span>

            </ul>
        </nav>
    </div>
</div>


<script>
    function deleteEmp(empId) {
        //給安全提示
        if (confirm("確定要刪除嗎?")) {
            //路徑
            location.href = "<%=request.getContextPath()%>/emp/DelEmpServlet?empId=" + empId + "&requestURL=<%=request.getServletPath()%>&currentPage=${pb.currentPage}&rows=5";
        }
    }


    window.onload = function () {
        //給刪除選中按鈕添加點擊事件
        document.getElementById("delSelected").onclick = function () {
            //判斷是否有選中至少一個
            let flag = false;

            if (confirm("確定要刪除所有選中嗎?")) {

                let elementsByName = document.getElementsByName("delEmpId");

                for (let i = 0; i < elementsByName.length; i++) {
                    //如果有一個是選中的話
                    if (elementsByName[i].checked) {
                        flag = true;
                        break;
                    }
                }


                if (flag) {
                    //表單提交的一個方法
                    document.getElementById("empForm").submit();
                } else {
                    alert("你什麼都沒選啊");
                }
            }

        }

        //獲取第一個checkbox
        document.getElementById("firstCB").onclick = function () {
            //獲取下方的所有的checkbox
            let elementsByName = document.getElementsByName("delEmpId");

            for (let i = 0; i < elementsByName.length; i++) {
                //設置這些checkbox的狀態=firstCB的狀態
                elementsByName[i].checked = this.checked;
            }
        }

    }


</script>
</body>
</html>