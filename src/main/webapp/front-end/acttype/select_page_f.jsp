<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Getting Started</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Footer-Clean.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/css/lightbox.min.css">
<link rel="stylesheet" href="assets/css/Lightbox-Gallery.css">
<link rel="stylesheet" href="assets/css/Navigation-Clean.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet"
	href="assets/css/Off-Canvas-Sidebar-Drawer-Navbar.css">
<link rel="stylesheet" href="assets/css/Sidebar-1.css">
<link rel="stylesheet" href="assets/css/Sidebar.css">
<link rel="stylesheet" href="assets/css/Team-Grid.css">
<style>
	.box{
		border-radius: 10px;
	} 
</style>
</head>
<body>
  <jsp:useBean id="actTypeSvc" scope="page" class="com.acttype.model.ActTypeService" />
	
	 <FORM METHOD="post" ACTION="/acttype/acttype.do" >
       <b>選擇活動類別:</b>
       <select size="1" name="acttype">
         <c:forEach var="actTypeVO" items="${actTypeSvc.all}" > 
          <option value="${actTypeVO.actCategoryId}">${actTypeVO.actCategoryName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
     
	<div class="photo-gallery">
		<div class="container" style="background-image: initial;">
			<div class="intro">
				<h2 class="text-center">CFA101G2 島旅</h2>
				<p class="text-center">Nunc luctus in metus eget fringilla.
					Aliquam sed justo ligula. Vestibulum nibh erat, pellentesque ut
					laoreet vitae.</p>
			</div>
		</div>
	</div>
	<div class="photo-gallery">
		<div class="container">
			<div class="intro"></div>
		</div>
	</div>
	<div class="team-grid">
		<nav
			class="navbar navbar-light navbar-expand-md navigation-clean-button">
			<div class="container">
				<a class="navbar-brand" href="#">Company Name</a>
				<button data-toggle="collapse" class="navbar-toggler"
					data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span><span
						class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-1">
					<ul class="nav navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link active" href="#">First
								Item</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Second
								Item</a></li>
						<li class="nav-item dropdown"><a
							class="dropdown-toggle nav-link" data-toggle="dropdown"
							aria-expanded="false" href="#">Dropdown </a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div></li>
					</ul>
					<span class="navbar-text actions"> <a class="login" href="#">Log
							In</a><a class="btn btn-light action-button" role="button" href="#">Sign
							Up</a></span>
				</div>
			</div>
		</nav>
		<div class="container">
			<div class="row people">
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/水肺浮潛.jpg)">
						<div class="cover">
							<h3 class="name">水肺浮潛</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/快艇衝浪.jpg)">
						<div class="cover">
							<h3 class="name">快艇衝浪</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/沙灘烤肉.jpg)">
						<div class="cover">
							<h3 class="name">沙灘烤肉</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/拖曳傘.jpg)">
						<div class="cover">
							<h3 class="name">拖曳傘</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/爬山.jpg)">
						<div class="cover">
							<h3 class="name">爬山</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/香蕉船.jpg)">
						<div class="cover">
							<h3 class="name">香蕉船</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/浮潛.jpg)">
						<div class="cover">
							<h3 class="name">浮潛</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/溯溪.jpg)">
						<div class="cover">
							<h3 class="name">溯溪</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/獨木舟.jpg)">
						<div class="cover">
							<h3 class="name">獨木舟</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-3 item">
					<div class="box" style="background-image: url(../images/SUP立槳.jpg)">
						<div class="cover">
							<h3 class="name">SUP立槳</h3>
							<p class="title"></p>
							<div class="social">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-clean">
		<footer>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-sm-4 col-md-3 item">
						<h3>Services</h3>
						<ul>
							<li><a href="#">Web design</a></li>
							<li><a href="#">Development</a></li>
							<li><a href="#">Hosting</a></li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>About</h3>
						<ul>
							<li><a href="#">Company</a></li>
							<li><a href="#">Team</a></li>
							<li><a href="#">Legacy</a></li>
						</ul>
					</div>
					<div class="col-sm-4 col-md-3 item">
						<h3>Careers</h3>
						<ul>
							<li><a href="#">Job openings</a></li>
							<li><a href="#">Employee success</a></li>
							<li><a href="#">Benefits</a></li>
						</ul>
					</div>
					<div class="col-lg-3 item social">
						<a href="#"><i class="icon ion-social-facebook"></i></a><a
							href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i
							class="icon ion-social-snapchat"></i></a><a href="#"><i
							class="icon ion-social-instagram"></i></a>
						<p class="copyright">Company Name © 2017</p>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/Off-Canvas-Sidebar-Drawer-Navbar.js"></script>
	<script src="assets/js/Off-Canvas-Sidebar-Drawer-Navbar-1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/js/lightbox.min.js"></script>
</body>

</html>