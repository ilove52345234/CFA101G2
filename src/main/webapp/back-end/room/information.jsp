<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.extrabill.model.ExtraBillService" %>
<%@ page import="com.extrabill.model.ExtraBillVO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: ilove52345234
  Date: 2021/7/19
  Time: 12:45 上午
  To change this template use File | Settings | File Templates.
--%>

<%
    String roomId = request.getParameter("roomId");
    ExtraBillService extraBillService = new ExtraBillService();
    ExtraBillVO extraBillVO = extraBillService.GetOne(Integer.parseInt(roomId));
    pageContext.setAttribute("extraBillVO", extraBillVO);
%>

<html>
<head>
    <title>Title</title>
    <script src="../js/getParameter.js"></script>
    <!-- 1. 導入CSS的全局樣式 -->
    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <%--    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>--%>
    <!-- 3. 導入bootstrap的js文件 -->
    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>


</head>
<body>
<Style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    table tr {
        border-bottom: solid 2px white;
    }

    table tr:last-child {
        border-bottom: none;
    }

    table th {
        position: relative;
        width: 30%;
        background-color: #7d7d7d;
        color: white;
        text-align: center;
        padding: 10px 0;
    }

    table th:after {
        display: block;
        content: "";
        width: 0px;
        height: 0px;
        position: absolute;
        top: calc(50% - 10px);
        right: -10px;
        border-left: 10px solid #7d7d7d;
        border-top: 10px solid transparent;
        border-bottom: 10px solid transparent;
    }

    table td {
        text-align: left;
        width: 70%;
        text-align: center;
        background-color: #eee;
        padding: 10px 0;
    }

    .main {
        margin: 20px auto;
        item-align: center;
        width: 80%;
    }
</Style>

<div class="main">
    <table>
        <tr>
            <th>房間號碼</th>
            <td id="roomId">${extraBillVO.roomId}</td>
        </tr>
        <tr>
            <th>登記姓名</th>
            <td id="name"></td>
        </tr>
        <tr>
            <th>登記電話</th>
            <td>${extraBillVO.informationPhone}</td>
        </tr>
        <tr>
            <th>入住時間</th>
            <td><fmt:formatDate value="${extraBillVO.checkInDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <th>預計退房日</th>
            <td><fmt:formatDate value="${extraBillVO.expectedCheckOutDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>退房時間</th>
            <td>
                <c:if test="${extraBillVO.checkOutDate==null}">
                    尚未退房
                </c:if>
                <c:if test="${extraBillVO.checkOutDate!=null}">
                    <fmt:formatDate value="${extraBillVO.checkOutDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </c:if>
            </td>
        </tr>
    </table>


</div>


<script>
    window.onload = function () {
        let id = document.getElementById('name');
        let parameter = getParameter("name");
        let decode = decodeURI(parameter);
        id.innerText = decode;
    }

    // var group_name = function (){
    //     let parameter = getParameter("name");
    //     let decode = decodeURI(parameter);
    //     var data = {
    //         group_name : decode
    //     };
    //     return data;
    // }

</script>
</body>
</html>
