<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>showImages</title>
    <!-- 1. 導入CSS的全局樣式 -->
<%--    <link href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
    <script src="<%=request.getContextPath()%>/back-end/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 導入bootstrap的js文件 -->
<%--    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>--%>
<%--    <script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>--%>




    <style type="text/css">
        .float{
            float:left;
            width : 200px;
            height: 200px;
            overflow: hidden;
            border: 1px solid #CCCCCC;
            border-radius: 10px;
            padding: 5px;
            margin: 5px;
        }
        img{
            position: relative;
        }
        .result{
            width: 200px;
            height: 200px;
            text-align: center;
            box-sizing: border-box;
        }


        #file_input{
            display: none;
        }


        .delete{
            width: 200px;
            height:200px;
            position: absolute;
            text-align: center;
            line-height: 200px;
            z-index: 10;
            font-size: 30px;
            background-color: rgba(255,255,255,0.8);
            color: #777;
            opacity: 0;
            transition-duration: 0.7s;
            -webkit-transition-duration: 0.7s;
        }


        .delete:hover{
            cursor: pointer;
            opacity: 1;
        }

    </style>



    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/js/teset1.js"></script>

</head>

<body>








<div class="container">
    <label>請選擇一個圖像文件：</label>
    <button id="select">(重新)選擇圖片</button>
    <button id="add">(追加)圖片</button>
    <input type="file" id="file_input" multiple/> <!--用input標籤並選擇type=file，記得帶上multiple，不然就只能單選圖片了-->
    <button id="submit">提交</button>
</div>

</body>

</html>