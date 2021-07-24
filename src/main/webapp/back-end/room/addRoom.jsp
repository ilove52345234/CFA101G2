<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rmtype.model.*" %>
<!-- 送出請求 -->


<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>住客登記</title>


    <!-- 引入 layui.css -->
    <link rel="stylesheet" href="//unpkg.com/layui@2.6.8/dist/css/layui.css">

    <!-- 引入 layui.js -->
    <script src="//unpkg.com/layui@2.6.8/dist/layui.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>


    <!-- 1. 導入CSS的全局樣式 -->
    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <%--    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>--%>
    <!-- 3. 導入bootstrap的js文件 -->
    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }

        /*body { font-size: 62.5%; }*/
        /*label, input { display:block; }*/
        input.text {
            margin-bottom: 12px;
            width: 95%;
            padding: .4em;
        }

        fieldset {
            padding: 0;
            border: 0;
            margin-top: 25px;
        }

        h1 {
            font-size: 1.2em;
            margin: .6em 0;
        }

        div#users-contain {
            width: 350px;
            margin: 20px 0;
        }

        div#users-contain table {
            margin: 1em 0;
            border-collapse: collapse;
            width: 100%;
        }

        div#users-contain table td, div#users-contain table th {
            border: 1px solid #eee;
            padding: .6em 10px;
            text-align: left;
        }

        .ui-dialog .ui-state-error {
            padding: .3em;
        }

        .validateTips {
            border: 1px solid transparent;
            padding: 0.3em;
        }

        #message {
            position: fixed;
            top: 63px;
            left: 180px;
        }

        table#table-1 {
            background-color: rgba(204, 204, 255, 0.18);
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

        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0px solid rgba(204, 204, 255, 0.08);
        }

        th, td {
            padding: 1px;
        }
    </style>

</head>
<body bgcolor='white'>
<div class="container">
    <table id="table-1">
        <tr>
            <td><h3>房間編號: ${param.roomId}</h3></td>

        </tr>
    </table>

    <h3></h3>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>
    <span id="message" style="color: red" text-align="center"></span>
    <FORM METHOD="post" ACTION="" name="form1">

        <table>

            <tr>
                <td>住客名稱:</td>
                <td><input type="TEXT" name="roomInformation" id="roomInformation" size="45"
                /></td>
            </tr>
            <tr>
                <td>住客電話:</td>
                <td><input type="TEXT" name="informationPhone" id="informationPhone" size="45"
                /></td>
            </tr>


        </table>
        <br>
        <input type="hidden" name="roomId" value=${param.roomId}>
        <%--    <input type="submit" class="btn btn-default btn-sm" value="送出新增"></FORM>--%>
        <a class="btn btn-default btn-sm"
           id="btn1">送出新增</a>
        <%--    href="javascript:save();"--%>
</div>
</body>


<script>
    $("#btn1").on('click', function () {

        $('#message').html("")
        var sno = $('#informationPhone').val();
        var sname = $('#roomInformation').val();
        // alert(sno)

        if (($.trim(sno) == "" || sno == null) || ($.trim(sname) == "" || sname == null)) {

            $('#message').html("請輸入姓名與電話")
        } else {
            swal({
                title: "確定新增？",
                html: "按下確定會新增住客資訊",
                type: "question", // type can be "success", "error", "warning", "info", "question"
                showCancelButton: true,
                showCloseButton: true,
            }).then(
                function (result) {
                    if (result) {
                        save();
                    }
                }, function (dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
                    swal("取消", "住客未被新增", "error");
                    setTimeout(function () {
                        window.parent.location.reload(); //刷新父頁面
                        var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                        parent.layer.close(index); // 關閉圖層
                    }, 1000);

                }).catch(swal.noop);

        }

    });


    function save() {
        let serialize = $("form").serialize();
        $.ajax({
            type: "post",

            url: "/CFA101G2/room/CheckInServlet?"+serialize,

            data: {},//序列化表单数据

            success: function (data) {
                if (data.flag) {
                    swal("完成!", "2秒後回到列表", "success",);
                    setTimeout(function () {
                        window.parent.location.reload(); //刷新父頁面
                        var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                        parent.layer.close(index); // 關閉圖層
                    }, 2000);
                }else {
                    swal("失敗", "原因"+data.errorMsg, "error");
                }
            }
        });
    }
</script>


</html>