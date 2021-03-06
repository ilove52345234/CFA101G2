<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理系統</title>
    <meta name="viewport" content="width=device-width,inital-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="id=edge">
    <link rel="stylesheet" href="/CFA101G2/front-end/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script>
        function refreshCode() {
            //1.獲取圖片物件
            //2.設置src

            document.getElementById("vcode").src="/CFA101G2/emp/empServlet/checkCodeServlet?time="+new Date().getTime();
        }

    </script>


</head>


<body>
<form id="loginForm" class="login">
    <h1>管理員登入</h1>
    <i class="fa fa-user-circle-o"></i>
    <h2>帳號</h2>
    <input type="text" placeholder="請輸入帳號" name="empAccount" id="empAccount">
    <h2>密碼</h2>
    <input type="password" placeholder="請輸入密碼" name="empPassword" id="empPassword">
    <div class="alert alert-warning alert-dismissible" role="alert">

        <strong id="errorMsg">您尚未登入</strong>

        <div class="auto_login">
            <input type="checkbox" name="autoLogin" value="true" class="checkbox">
            <span>自動登入</span>
        </div>
    </div>
    <button id="idForm" type="button" class="button" style="vertical-align:middle"><span>登入 &#8594;</span></button>
    <div class="form-inline">
        <!--        <label for="vcode">驗證碼：</label>-->
        <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="請輸入驗證碼" style="width: 120px;"/>



    </div>
    <div id="v">
        <a href="javascript:refreshCode()"><img src="/CFA101G2/emp/empServlet/checkCodeServlet" title="換一張" id="vcode" name="vcode" class="xxx"/></a>
    </div>
    <!--    <span><img src="/CFA101G2/emp/checkCodeServlet"></span>-->
</form>
<!--<img id="check_img" src="emp/checkCodeServlet" alt="" onclick="changeCheckCode(this)">-->


<script>
    $(function () {
        $.post("/CFA101G2/emp/empServlet/autoLogin",{},function (info) {
            if(info.flag){
                // alert( document.location)
                // location.assign("back-end/list.html");
                 location.assign(${requestURI});

            }else{
                $("#errorMsg").text(data.errorMsg);
            }
        })


        //1.給登入按鈕綁定事件
        $("#idForm").on('click',function () {
            let datas = $("form").serialize();
            // let empAccount = $('empAccount').val();
            // let empPassword = $('empPassword').val();
            //serialize() :序列化表單的方法
            $.post("/CFA101G2/emp/empServlet/empLoginServlet",
                datas,
                function (data) {
                    if (data.flag) {
                        // alert("開始重導向")
                        // location.href = "/CFA101G2/front-end/list.html";
                        location.assign(${requestURI});
                        // alert( document.location)
                        // location.assign(document.location);
                    } else {
                        refreshCode();
                        $("#errorMsg").text(data.errorMsg);
                    }
                });
        });
    });
</script>




</body>
</html>
</html>
