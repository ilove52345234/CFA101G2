<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<!DOCTYPE html>

<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<title>島旅渡假村商城</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/shop/css/style.css">
<!-- Boxing CDN Link -->
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/shop/css/style2.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body {
	height: auto;
	background: #E0FFFF;
}

.topnav {
	background-color: #F5FFFA;
	overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
	float: right;
	color: #483D8B;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
	background-color: #ADD8E6;
	color: white;
}

/* Add a color to the active/current link */
.topnav a.active {
	background-color: #6495ED;
	color: white;
}
</style>
</head>
<body>

<jsp:useBean id="sps" scope="page" class="com.shoppic.model.ShopPicService" />
<div style="display:flex;">
<div class="leftside" style="height:600px;width:250px;">


	

		<div class="sidebar">
			<div class="logo_content">
				<div class="logo">
					<i class='bx bxs-tree'></i>
					<div class="logo_name">商城</div>
				</div>
				<i class='bx bx-menu' id="btn"></i>
			</div>
			<ul class="nav_list">


				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red"></font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<li><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/shop/shop.do">
						<i class='bx bx-search'></i> <select size="1" name="itemName">
							<c:forEach var="shopVO" items="${shopSvc.all}">
								<option value="${shopVO.itemName}">
							</c:forEach>
							<%--找到對應名稱的商品 --%>
						</select> <input type="hidden" name="action"
							value="getItemName_For_Display"> 
						<input type="submit">
						<input type="text" placeholder="請輸入關鍵字" name="itemName">

					</FORM></li>

				<li><a href="#"> <i class='bx bx-grid-alt'></i> <span
						class="links_name">最新消息</span>
				</a> <span class="tooltip">查看</span></li>

				<li><a href="#"> <i class='bx bxs-user'></i> <span
						class="links_name">會員</span>
				</a> <span class="tooltip">會員</span></li>

				<li><a href="#"> <i class='bx bx-chat'></i> <span
						class="links_name">聊天室</span>
				</a> <span class="tooltip">聊天室</span></li>

				<li><a href="#"> <i class='bx bxs-pie-chart-alt'></i> <span
						class="links_name">限時優惠</span>
				</a> <span class="tooltip">手刀購買</span></li>

				<li><a href="#"> <i class='bx bxs-folder-open'></i> <span
						class="links_name">訂單查詢</span>
				</a> <span class="tooltip">付款or看單</span></li>

				<li>
				<a href="<%=request.getContextPath()%>/front-end/shop/shoppingCart.jsp"> <i class='bx bx-cart-alt'></i> <span
						class="links_name">購物車</span>
				</a> <span class="tooltip">購物車</span></li>

				<li><a href="#"> <i class='bx bx-heart'></i> <span
						class="links_name">追蹤清單</span>
				</a> <span class="tooltip">追蹤清單</span></li>

				<li><a href="#"> <i class='bx bx-cog'></i> <span
						class="links_name">設置</span>
				</a> <span class="tooltip">設置</span></li>
			</ul>
			<div class="profile_content">
				<div class="profile">
					<div class="profile_details">
						<img src="images/TestFGO.jpg" alt="">
						<div class="name_job">
							<div class="name">學妹</div>
							<div class="job">御靈</div>
						</div>
					</div>
					<i class='bx bx-log-out-circle' id="log_out"></i>
				</div>
			</div>
		</div>


		
	</div> <!--上面為左半邊結尾-->
		
		
		
		<!--下面為 右半邊 -->
		
		<div class="rightside" style="height:auto;width:100%;">
		
		<div class="topbar1" style="justify-content: flex-end;">
			<div>
				<div class="topnav">
					<a class="active" href="<%=request.getContextPath()%>/front-end/frontEndPage.jsp">島旅渡假村</a> <a href="#news">輕鬆</a> <a
						href="#contact">寫意</a> <a href="#about">舒服</a>
				</div>
			</div>
		</div>
		<div class="bigcards" style="width:100%;display:flex;flex-wrap:wrap;margin-left:40px;">
		<c:forEach var="shopVO" items="${list}">
			<div class="card" style="margin:30px 30px;">
				<form name="shoppingForm" action="<%=request.getContextPath()%>/CFA101G2/front-end/shop/shop.do" method="POST">
					<div class="imgBx">
					<a href="/CFA101G2/front-end/shop/shop.do?action=getOne_For_Display&itemId=${shopVO.itemId}">
					<img style="border-radius: 20px;" src="data:image/jpeg;base64,${sps.getOneShopPic(shopVO.itemId)} ">
					</a> 
					<input type="hidden" name="action" value="getItemName_For_Display">
					</div>
				</form>
				
				<form name="shoppingForm" action="/CFA101G2/ShoppingCart.do" method="POST">
					<div class="contentBx">
						<h3>${shopVO.itemName}</h3>
						<h2 class="price">${shopVO.itemFee}元</h2>
						<input type="submit" class="button" value="放入購物車"> 	
						<input type="hidden" name="action" value="ADD" >
						<input type="hidden" name="itemId" value="${shopVO.itemId}">
						<input type="hidden" name="itemName" value="${shopVO.itemName}">
						<input type="hidden" name="itemFee" value="${shopVO.itemFee}">
						<input type="hidden" name="itemDescribtion" value="${shopVO.itemDescribtion}">
						<input type="hidden" name="orderQuantity" value=1>
						<input type="hidden" name="itemQuantity" value="${shopVO.itemQuantity}">
						<input type="hidden" name="commentNumber" value="${shopVO.commentNumber}">
						<input type="hidden" name="commentTotalScore" value="${shopVO.commentTotalScore}">
					</div>
				</form>
				
			</div>
		</c:forEach>	
</div>
	</div>
</div>
</body>
<script>
let sidebar = document.querySelector(".sidebar");
let searchBtn = document.querySelector(".bx-search");

btn.onclick = function() {
	sidebar.classList.toggle("active");
}
searchBtn.onclick = function() {
	sidebar.classList.toggle("active");
}

(function App() {
	let isDark = false;
	if (localStorage.getItem("dark-mode")) {
		isDark = true;
		setDarkTheme();
	}
	const themeToggle = document.querySelector(".theme-toggle");
	themeToggle.addEventListener("click", ()=> {
		if (isDark) {
			setLightTheme();
			isDark = false;
			ManageLocalStorage("DELETE");
			return;
		}
		setDarkTheme();
		ManageLocalStorage("ADD");
		isDark = true;
	});

	function setLightTheme() {
		document.body.classList.remove("dark");
	}
	function setDarkTheme() {
		document.body.classList.add("dark");
	}

	function ManageLocalStorage(command) {
		if (command === "DELETE") {
			localStorage.removeItem("dark-mode");
			return;
		}
		localStorage.setItem("dark-mode", true);
	}
})();
</script>
</html>