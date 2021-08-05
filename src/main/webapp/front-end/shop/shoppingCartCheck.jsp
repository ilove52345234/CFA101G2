<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.shop.model.*" %>    
<%@ page import="com.mem.model.*" %>    
<%@ page import="com.shoporder.model.*" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %> 


<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>請記得付錢</title>
</head>
<style>
body {
 background: #E0FFFF;
 position: absolute;
}
div{
 text:center;
 
}
</style>
<body>
<div style="text-align:center;margin:250px 300px 250px 500px;" >
<div class="alert alert-info" role="alert"  style="position: realative; ">
  <a href="<%=request.getContextPath()%>/front-end/shop/shopOrdered_page.jsp" class="alert-link">${status}:${itemOrderId}<br>點擊查看</a>
</div>

<div class="alert alert-success" role="alert">
  <a href="<%=request.getContextPath()%>/front-end/shop/shopHomePage.jsp" class="alert-link">回商城</a>
</div>

</div>
</body>
</html>