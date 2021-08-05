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
    <title>房間系統</title>
    <%--    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>--%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
            type="text/javascript"></script>
    <!-- 引入 layui.css -->
    <link rel="stylesheet" href="//unpkg.com/layui@2.6.8/dist/css/layui.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"></script>

    <!-- 引入 layui.js -->
    <script src="//unpkg.com/layui@2.6.8/dist/layui.js"></script>

    <!-- 1. 導入CSS的全局樣式 -->
<%--    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <%--    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>--%>
    <!-- 3. 導入bootstrap的js文件 -->
<%--    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>--%>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/js/include.js"></script>
    <style type="text/css">
        td, th , .th {
            text-align: center; !important;
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

        .swal2-container {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            padding: 10px;
            background-color: transparent;
            z-index: 19891016;
        }

    </style>


</head>
<body>
<div id="header"></div>


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
                    <%--                    <c:if test="${  empty condition.ROOM_CHECK_STATUS }">--%>
                    <%--                        <option value="" selected>查全部</option>--%>
                    <%--                        <option value=0>未使用</option>--%>
                    <%--                        <option value=1>待入住</option>--%>
                    <%--                        <option value=2>已入住</option>--%>
                    <%--                        <option value=3>已退房</option>--%>
                    <%--                    </c:if>--%>

                    <c:if test="${ condition.ROOM_CHECK_STATUS == 0 || empty condition.ROOM_CHECK_STATUS}">
                        <option value="" selected>查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3>已退房</option>
                        <option value=4 >待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 1}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1 selected>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3>已退房</option>
                        <option value=4 >待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 2}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2 selected>已入住</option>
                        <option value=3>已退房</option>
                        <option value=4 >待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 3}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3 selected>已退房</option>
                        <option value=4 >待退房</option>
                    </c:if>
                    <c:if test="${condition.ROOM_CHECK_STATUS == 4}">
                        <option value="">查全部</option>
                        <option value=0>未使用</option>
                        <option value=1>待入住</option>
                        <option value=2>已入住</option>
                        <option value=3 >已退房</option>
                        <option value=4 selected>待退房</option>
                    </c:if>

                </select>
            </div>
            <button type="submit" class="btn btn-default">查詢</button>

        </form>

    </div>


    <div style="float: right;margin: 5px;">

        <%--${pageContext.request.contextPath}同樣可用--%>


        <a class="btn btn-primary" href="javascript:calendar();" id="delSelected">數量查詢</a>
        <a class="btn btn-primary" href="javascript:addRoom();" id="delSelected">增加房間</a>

    </div>
    <div style="float: right;margin: 5px;">

        <%--${pageContext.request.contextPath}同樣可用--%>


        <select name="RoomCategoryId" class="form-control" id="RoomCategoryId">
            <c:forEach items="${rtVOS}" var="rms" varStatus="s">

                <option value=${rms.roomCategoryId}>${rms.roomName}</option>
            </c:forEach>
        </select>

    </div>


    <%--  用表單將整個table包起來,用來提交所有的勾選--%>
    <%--    <%=request.getContextPath()%>/emp/delSelectedServlet?currentPage=${pb.currentPage}&rows=5--%>
    <form id="empForm" action="" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="info">
                <th class="th">列表編號</th>
                <th class="th">房間號碼</th>
                <th class="th">房型類別</th>
                <th class="th">房間使用情況</th>
                <th class="th">房間狀態</th>
                <th class="th">住客姓名與資訊</th>
                <th class="th">操作</th>
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

                    <c:if test="${rms.roomCheckStatus == 0 }">
                        <td> 未使用</td>
                    </c:if>
                    <c:if test="${rms.roomCheckStatus == 1 }">
                        <td> 待入住</td>
                    </c:if>
                    <c:if test="${rms.roomCheckStatus == 2 }">
                        <td> 已入住</td>
                    </c:if>
                    <c:if test="${rms.roomCheckStatus == 3 }">
                        <td> 已退房</td>
                    </c:if>
                    <c:if test="${rms.roomCheckStatus == 4 }">
                        <td style="color: red"> 待退房</td>
                    </c:if>


                    <td>
                        <c:if test="${rms.roomSaleStatus==0}">
                            <span class="label label-success">可用</span>
                        </c:if>
                        <c:if test="${rms.roomSaleStatus==1}">
                            <span class="label label-danger">不可用</span>
                        </c:if>

                    </td>
                        <%--                    <td>${emps.empStatus==1?'啟用中':'未啟用'}</td>--%>
                    <td>
                        <c:if test="${rms.roomInformation == null }">
                            未入住
                        </c:if>
                        <c:if test="${rms.roomInformation != null }">
                            <A HREF="javascript:openif('${rms.roomId}','${rms.roomInformation}')">${rms.roomInformation}</a>
                        </c:if>

                    </td>

                    <td>
                            <%--                        <a class="btn btn-default btn-sm"--%>
                            <%--                        <%=request.getContextPath()%>/emp/GetOneEmpServlet?empId=${emps.empId}&currentPage=${pb.currentPage}&rows=5--%>
                            <%--                           href="">修改狀態</a>&nbsp;--%>

                        <c:if test="${rms.roomCheckStatus == 1 ||rms.roomCheckStatus == 0 }">
                            <%--                            <a class="btn btn-default btn-sm"--%>
                            <%--                               href="" οnclick="aa()">入住</a>--%>
                            <a class="btn btn-success btn-sm"
                               href="javascript:presses(${rms.roomId});">入住</a>

                        </c:if>

                        <c:if test="${rms.roomCheckStatus == 2 ||rms.roomCheckStatus == 4 }">
                            <a class="btn btn-info btn-sm"
                               href="javascript:checkOut('${rms.roomId}','${rms.roomInformation}')">退房</a>
                        </c:if>

                        <c:if test="${rms.roomCheckStatus == 3 }">
                            <a class="btn btn-primary btn-sm"
                               href="javascript:done(${rms.roomId})">確認完成</a>
                        </c:if>


                    </td>
                </tr>


            </c:forEach>

        </table>


    </form>
    <%--    分頁--%>
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

    function presses(roomId) {
        <%--document.open("<%=request.getContextPath()%>/back-end/room/addRoom.jsp?roomId=" + roomId, "", "height=250,width=850,left=65,top=157,resizable=yes,scrollbars=yes");--%>


        var url = "<%=request.getContextPath()%>/back-end/room/addRoom.jsp?roomId=" + roomId;

        // if (top.layui.index) {
        //     top.layui.index.openTabsPage(url, title)
        // } else {
        //     window.open(url)
        // }


        layer.open({
            title: '住客登記',
            type: 2,
            content: url,
            shade: 0.2,
            shadeClose: true,
            maxmin: true,
            area: ['500px', '400px'],


        });

    }

    function calendar() {
        <%--document.open("<%=request.getContextPath()%>/back-end/room/addRoom.jsp?roomId=" + roomId, "", "height=250,width=850,left=65,top=157,resizable=yes,scrollbars=yes");--%>
        var RoomCategoryId = $('#RoomCategoryId').val();

        var url = "<%=request.getContextPath()%>/back-end/room/FullCalendar.html?RoomCategoryId=" + RoomCategoryId;

        layer.open({
            title: '房型編號:' + RoomCategoryId,
            type: 2,
            content: url,
            shade: 0.2,
            shadeClose: true,
            maxmin: true,
            area: ['800px', '600px'],


        });

    }

    function addRoom() {
        <%--document.open("<%=request.getContextPath()%>/back-end/room/addRoom.jsp?roomId=" + roomId, "", "height=250,width=850,left=65,top=157,resizable=yes,scrollbars=yes");--%>
        var RoomCategoryId = $('#RoomCategoryId').val();

        var url = "<%=request.getContextPath()%>/room/roomAddServlet?RoomCategoryId=" + RoomCategoryId;


        Swal.fire({
            title: '房型編號:'+RoomCategoryId,
            input: 'text',
            inputPlaceholder: '請輸入房間數量',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: '確定新增',
            showLoaderOnConfirm: true,
            preConfirm: (login) => {
                // alert(url+"&amount="+login)
                return fetch(url+"&amount="+login)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(response.statusText)
                        }
                        return response.json()
                    })
                    .catch(error => {
                        Swal.showValidationMessage(
                            `新增失敗: ${error}`
                        )
                    })
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            alert(result)
            if (result.flag) {
                Swal.fire({
                    title: '新增成功'
                    // imageUrl: result.value.avatar_url
                })
            }else {
                Swal.showValidationMessage(
                    `新增失敗: ${result.errorMsg}`
                )
            }
        })

        // layer.open({
        //     title: '房型編號:' + RoomCategoryId,
        //     type: 2,
        //     content: url,
        //     shade: 0.2,
        //     shadeClose: true,
        //     maxmin: true,
        //     area: ['800px', '600px'],
        //
        //
        // });

    }

    function openif(roomId, roomInformation) {

        var url = "<%=request.getContextPath()%>/back-end/room/information.jsp?roomId=" + roomId + "&name=" + roomInformation;

        console.log(url)
        layer.open({
            title: '住客資訊',
            type: 2,
            content: url,
            shade: 0.2,
            shadeClose: true,
            maxmin: true,
            area: ['500px', '400px']

        });

    }


    function checkOut(roomId, roomInformation) {

        var url = "<%=request.getContextPath()%>/back-end/room/information.jsp?roomId=" + roomId + "&name=" + roomInformation;


        layer.open({
            title: ['請確認退房資訊與房卡', 'color: red'],
            type: 2,
            content: url,
            shade: 0.2,
            shadeClose: true,
            maxmin: true,
            area: ['500px', '400px'],
            btn: ['Check out'],
            yes: function (index, layero) {
                // var group_name = $(layero).find("iframe")[0].contentWindow.group_name();
                //
                // alert(group_name)

                $.ajax({
                    type: "post",

                    url: "/CFA101G2/room/checkOutServlet?roomId=" + roomId,

                    data: {},//序列化表单数据

                    success: function (data) {
                        if (data.flag) {
                            swal("完成!", "2秒後回到列表", "success",);
                            setTimeout(function () {
                                window.parent.location.reload(); //刷新父頁面
                                var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                                parent.layer.close(index); // 關閉圖層
                            }, 2000);
                        } else {
                            swal("失敗", "原因" + data.errorMsg, "error");
                            setTimeout(function () {
                                window.parent.location.reload(); //刷新父頁面
                                var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                                parent.layer.close(index); // 關閉圖層
                            }, 2000);
                        }
                    }
                });
            }, btnAlign: 'c'
        });


    }

    function done(roomId) {

        var url = "<%=request.getContextPath()%>/back-end/room/information.jsp?roomId=" + roomId;


        swal({
            title: '確認房間清潔完成',
            input: 'checkbox',
            inputPlaceholder: '房況確認完畢'
        }).then(function (result) {
            if (result === 1) {
                $.ajax({
                    type: "post",

                    url: "/CFA101G2/room/roomDoneServlet?roomId=" + roomId,

                    data: {},

                    success: function (data) {
                        if (data.flag) {
                            swal("完成!", "2秒後回到列表", "success",);
                            setTimeout(function () {
                                window.parent.location.reload(); //刷新父頁面
                                var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                                parent.layer.close(index); // 關閉圖層
                            }, 2000);
                        } else {
                            swal("失敗", "原因" + data.errorMsg, "error");
                            setTimeout(function () {
                                window.parent.location.reload(); //刷新父頁面
                                var index = parent.layer.getFrameIndex(window.name); //獲取窗口索引
                                parent.layer.close(index); // 關閉圖層
                            }, 2000);
                        }
                    }
                });

            } else if (result === 0) {
                swal({
                    type: 'error',
                    text: "請確認房況"
                });
            }
        });


    }

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