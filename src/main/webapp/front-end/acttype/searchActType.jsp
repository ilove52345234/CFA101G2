<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.acttype.model.*"%>

<%	// ShopServlet.java(Concroller), �s�Jreq��shopVO����
  ActTypeVO acttypeVO = (ActTypeVO) request.getAttribute("acttypeVO"); 
%>

<html>
<head>


<meta charset="BIG5">
<title>�j�M�ӫ~ - searchCommodity</title>
</head>
<body>

	<FORM METHOD="post" ACTION="acttype.do" >
        <input type="text" placeholder="�j�M����" name="itemName">
        <input type="hidden" name="action" value="getSearch">
<!--         <input type="submit" value="�j�M�ӫ~"> -->
	
<!--         <input class="form-control" type="search" placeholder="Search" aria-label="Search" name="itemName"> -->
      	<button class="btn btn-outline-success" type="submit">Search</button>
      	
</FORM>
</body>
</html>