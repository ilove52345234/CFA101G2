<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>島旅 Island Brigade</title>
<!-- 網頁標題左側顯示 -->
<link rel="icon" href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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">
<!-- 收藏夾顯示圖示 -->
<link rel="icon" href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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" type="images/logo_2.ico">
<!-- 匯入bootstrap5 css -->
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<!-- 匯入bootstrap5 java script -->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>--%>
<!-- 匯入bootstrap3 jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/include.js"></script>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* 有 float:left 是為了要讓選單是一個接著一個橫向拓展，position: relative 的用意是因為接下來要有下拉選單，讓定位點有參考 */
ul.navbar-nav li {
	position: relative;
	float: left;
	list-style: none;
}
/* ul.navbar-nav li a，就是統一設定選單內連結的樣式，我們把 a 設為 block 才能讓除了字以外在範圍內都可以有連結的效果 */
ul.navbar-nav li a {
	display: block;
	text-decoration: none; /* 放在這 */
}

ul.navbar-nav>li>a:hover {
	color: #666;
	background: #DDD;
}
/* 多層選單的樣式...第二層之後下拉部分之樣式 */
ul.navbar-nav li ul {
	display: none;
	float: left;
	position: absolute;
	margin: 0;
	padding: 0;
}
/* 當第一層選單被觸發時，指定第二層顯示 */
/* ul.navbar-nav li:hover > ul{
		display: block;
		} */
/* 第二層或之後 選單 li 之樣式 */
ul.navbar-nav ul a {
	width: 145px;
	padding: 10px 12px;
	color: #666;
	background: #EEE;
}

ul.navbar-nav ul a:hover {
	background: #CCC;
}

ul.navbar-nav ul li:hover>ul {
	display: block;
	position: absolute;
	top: 0;
	left: 100%;
}

.dropdown-menu {
	position: relative;
	min-width: 0rem;
}

.dropdown-submenu .dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: 0px;
	padding: 10px 10px;
	color: #666;
	background: #EEE;
}

.navbar {
	padding-top: 0rem;
	padding-bottom: 0rem;
}
</style>
</head>

<body>
	<!-- navbar fixed-top 固定上方 -->
	<nav class="navbar navbar-expand-lg navbar fixed-top navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> 
			<img src="https://i.imgur.com/2j6Zzxv.png" height="40"
				title="島旅 Island Brigade" alt="島旅 Island Brigade"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink2" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">訂房管理</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">住房管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">查看住房訂單</a></li>
									<li><a class="dropdown-item" href="#">修改訂單狀態</a></li>
									<li><a class="dropdown-item" href="#">查看住房訂單</a></li>
									<li><a class="dropdown-item" href="#">查詢住客歷史</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">房型管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">新增/刪除房型</a></li>
									<li><a class="dropdown-item" href="#">上/下架房型</a></li>
									<li><a class="dropdown-item" href="#">房型相片管理</a></li>
									<li><a class="dropdown-item" href="#">房型價格管理</a></li>
									<li><a class="dropdown-item" href="#">房型敘述管理</a></li>
									<li><a class="dropdown-item" href="#">前台推播</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">房間管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">房間數量查詢</a></li>
									<li><a class="dropdown-item" href="#">設定房間狀況</a></li>
									<li><a class="dropdown-item" href="#">房內品項管理</a></li>
									<li><a class="dropdown-item" href="#">遺失物管理</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">Check in</a>
								<ul>
									<li><a class="dropdown-item" href="#">房間狀況查詢</a></li>
									<li><a class="dropdown-item" href="#">查詢住客資料</a></li>
									<li><a class="dropdown-item" href="#">建立額外消費明細</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">Check out</a>
								<ul>
									<li><a class="dropdown-item" href="#">房間狀況查詢</a></li>
									<li><a class="dropdown-item" href="#">查看額外消費明細</a></li>
								</ul></li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">訂房促銷管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">建立訂房優惠</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink2" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 活動管理</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">活動管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">活動開始舉辦</a></li>
									<li><a class="dropdown-item" href="#">活動取消舉辦</a></li>
									<li><a class="dropdown-item" href="#">參加人員管理</a></li>
									<li><a class="dropdown-item" href="#">發送評價邀請</a></li>
									<li><a class="dropdown-item" href="#">活動行事曆</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">活動類別管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">新增/修改活動</a></li>
									<li><a class="dropdown-item" href="#">上/下架活動</a></li>
									<li><a class="dropdown-item" href="#">前台推播</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">活動促銷管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">建立活動優惠方案</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink2" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">商城管理</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">商品管理</a>
								<ul>
									<li><a class="dropdown-item" href="searchCommodity.jsp">商品資料管理</a></li>
									<li><a class="dropdown-item" href="addCommodityContent.jsp">商品建檔</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">訂單管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">日期查詢訂單</a></li>
									<li><a class="dropdown-item" href="#">訂單明細管理</a></li>
									<li><a class="dropdown-item" href="#">更改訂單狀態</a></li>
									<li><a class="dropdown-item" href="#">出貨排程</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink2" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 會員管理</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">會員帳號管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">查閱會員註冊資料</a></li>
									<li><a class="dropdown-item" href="#">會員停權管理</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">文章管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">處理檢舉文章</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">聊天室管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">建立聊天室</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a href="#" id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">線上客服</a></li>
						</ul>
					</li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink2" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 員工管理</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">後台帳號管理</a>
								<ul>
									<li><a class="dropdown-item" href="#">登入</a></li>
									<li><a class="dropdown-item" href="#">新增管理員</a></li>
									<li><a class="dropdown-item" href="#">刪除管理員</a></li>
									<li><a class="dropdown-item" href="#">權限管理</a></li>
								</ul>
							</li>
							<li class="nav-item dropdown">
							<a href="#" id="navbarDropdownMenuLink3" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">聊天室管理</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav justify-content-end">
					<li class="nav-item">
						<!-- a href=" " 點擊登入會員的圖示時跳轉道的登入會員頁面(JSP) --> 
						<a class="nav-link active" aria-current="page" href="#"> <!-- img src="https://i.imgur.com/55Ef5HZ.png" 登入會員圖示網址 -->
							<img src="https://i.imgur.com/55Ef5HZ.png" height="32" width="32"></a>
						<!-- img src="https://i.imgur.com/HuMcIJ1.png" 會員登出圖示網址 --> <!-- img src="https://i.imgur.com/7TH2HgF.png" 會員中心圖示網址 -->
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>