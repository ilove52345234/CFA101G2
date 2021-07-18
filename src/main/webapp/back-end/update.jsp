<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.func.model.FuncVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.func.model.FuncService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    FuncService FuncSvc = new FuncService();
    List<FuncVO> list = FuncSvc.getAll();
    pageContext.setAttribute("list", list);
%>


<!-- HTML5文檔-->
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
    <title>修改管理員</title>
    <!-- 1. 導入CSS的全局樣式 -->
    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 導入bootstrap的js文件 -->
    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>修改管理員頁面</h3></center>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>

            </c:forEach>
        </ul>
    </c:if>

    <form action="${pageContext.request.contextPath}/emp/updateEmpServlet" method="post">
        <%--    隱藏傳送id--%>
        <input type="hidden" name="empId" value="${emp.empId}">

        <div class="form-group">
            <label for="empName">姓名：</label>
            <input type="text" class="form-control" id="empName" name="empName" value="${emp.empName}"
                   placeholder="請輸入姓名">
        </div>

        <div class="form-group">
            <label for="empAccount">帳號：</label>
            <input type="text" class="form-control" id="empAccount" name="empAccount" value="${emp.empAccount}"
                   placeholder="請輸入帳號">
        </div>

        <div class="form-group">
            <label for="empPassword">密碼：</label>
            <input type="password" class="form-control" id="empPassword" name="empPassword"
                   value="${emp.empPassword}" placeholder="請輸入密碼">
        </div>

        <div class="form-group">
            <label for="empAccount">新增日期：</label>
            <input type="text" class="form-control" id="empAddDate" name="empAddDate" value="${emp.empAddDate}"
                   placeholder="請輸入帳號">
        </div>

        <div class="form-group">
            <label for="empStatus">狀態：</label>
            <select name="empStatus" class="form-control" id="empStatus">

                <c:if test="${emp.empStatus == 1}">
                    <option value=1 selected>啟用</option>
                    <option value=0>未啟用</option>
                </c:if>

                <c:if test="${emp.empStatus == 0 || empty emp.empStatus}">
                    <option value=1>啟用</option>
                    <option value=0 selected>未啟用</option>
                </c:if>

            </select>
        </div>
        <%--        <div class="form-group">--%>
        <label for="empStatus">設定權限：</label>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>權限名稱</th>
                <th>權限說明</th>
            </tr>
            <c:forEach items="${list}" var="list" varStatus="s">
                <tr>
                    <td>
                        <input type="checkbox" value="${list.funcId}" name="funs" id="funs${s.count}"
                            <c:forEach items="${func}" var="func">
                                <c:if test="${func.funcId eq list.funcId}"> checked</c:if>
                            </c:forEach>/>

                        <label for="funs${s.count}">${list.funcName} </label></td>
                    <td>${list.funcDesc}</td>

                </tr>
            </c:forEach>
        </table>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回" onclick="javascript:history.go(-1);"/>
        </div>

    </form>
</div>
</body>
</html>