<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>首頁</title>

  <!-- 1. 導入CSS的全局樣式 -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery導入，建議使用1.9以上的版本 -->
  <script src="js/jquery-2.1.0.min.js"></script>
  <!-- 3. 導入bootstrap的js文件 -->
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>
</head>
<body>


<div >${user.name},歡迎您</div>
<div align="center">
  <a
          href="<%=request.getContextPath()%>/emp/EmpListServlet" style="text-decoration:none;font-size:33px">管理系統
  </a>
</div>






</body>
</html>