<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.acttype.model.*"%>

<%	// ShopServlet.java(Concroller), 存入req的shopVO物件
  ActTypeVO acttypeVO = (ActTypeVO) request.getAttribute("acttypeVO"); 
%>

<html>
<head>


<meta charset="BIG5">
<title>搜尋商品 - searchCommodity</title>
</head>
<body>

	<FORM METHOD="post" ACTION="acttype.do" >
        <input type="text" placeholder="搜尋活動" name="itemName">
        <input type="hidden" name="action" value="getSearch">
<!--         <input type="submit" value="搜尋商品"> -->
	
<!--         <input class="form-control" type="search" placeholder="Search" aria-label="Search" name="itemName"> -->
      	<button class="btn btn-outline-success" type="submit">Search</button>
      	
</FORM>
</body>
</html>