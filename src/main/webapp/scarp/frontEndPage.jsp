<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh">
<head>

<title>島旅 Island Travel</title>
<!-- 網頁標題左側顯示 -->
<link rel="icon"
	href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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">
<!-- 收藏夾顯示圖示 -->
<link rel="icon"
	href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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"
	type="images/logo_2.ico">
<meta charset="utf-8">
<!-- width=device-width部分設置頁面的寬度以跟隨設備的屏幕寬度（這因設備而異) -->
<!-- initial-scale=1部分設置瀏覽器首次加載頁面時的初始縮放級別 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 匯入bootstrap css-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- 匯入jQuery-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 匯入bootstrap javascript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
.navbar-nav.navbar-center {
	position: absolute;
	left: 45%;
}
/* 頁尾的背景顏色 */
#Footer {
	background-color: #FFFFFF;
}

.one-third.column {
	width: 31.333%;
}

.column, .columns {
	float: left;
	margin: 0 1% 40px;
}

.footer-copy-center #Footer .footer_copy {
	text-align: center;
}

html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, address, cite, code, del, dfn, em, img, ins,
	kbd, q, s, samp, strong, var, b, u, i, ol, ul, li, fieldset, form,
	label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article,
	aside, canvas, details, embed, figure, figcaption, footer, header, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
</style>

<link rel='stylesheet' id='mfn-base-css'
	href='https://www.ajhotel.com.tw/wp-content/themes/betheme/css/base.css?ver=21.5.9.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='mfn-layout-css'
	href='https://www.ajhotel.com.tw/wp-content/themes/betheme/css/layout.css?ver=21.5.9.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='mfn-responsive-css'
	href='https://www.ajhotel.com.tw/wp-content/themes/betheme/css/responsive.css?ver=21.5.9.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='style-css'
	href='https://www.ajhotel.com.tw/wp-content/themes/betheme-child/style.css?ver=21.5.9.3'
	type='text/css' media='all' />

<style id='mfn-custom-inline-css' type='text/css'>
.blog_slider.flat .blog_slider_ul li .item_wrapper .date_label {
	display: none;
}

#Subheader .title {
	color: #fff;
}

.accordion {
	margin-top: -16px;
}

.pager {
	margin-bottom: 60px;
}

.masonry.tiles .post-item .post-desc-wrapper .post-desc .post-title:after,
	.masonry.tiles .post-item.no-img, .masonry.tiles .post-item.format-quote,
	.blog-teaser li .desc-wrapper .desc .post-title:after, .blog-teaser li.no-img,
	.blog-teaser li.format-quote {
	background: #aa1832;
}

.content_slider h3 {
	font-size: 23px;
}

.portfolio-photo .portfolio-item a.portfolio-item-bg {
	margin-bottom: 30px;
}

.portfolio_group .portfolio-item .list_style_header .links_wrapper a:last-child
	{
	margin-top: 20px;
}

.portfolio_group .portfolio-item .list_style_header h3 {
	margin-top: 30px;
}

.portfolio_group .portfolio-item .list_style_header {
	margin-bottom: 10px;
	min-height: 68px;
}

.hr_color, .hr_color hr, .hr_dots span {
	background: #aa1832;
	height: 2px;
}

.ui-tabs .ui-tabs-panel, .ui-tabs .ui-tabs-nav li.ui-state-active a {
	background-color: #aa1832;
}

.ui-tabs .ui-tabs-nav li.ui-state-active a:after {
	background: #aa1832;
}

.desc_wrapper h4, h3, h4 {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
}

.ui-tabs .ui-tabs-nav li a {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	font-size: 17px;
	font-weight: 400;
}

.ui-tabs .ui-tabs-panel {
	padding: 35px 20px 20px;
}

table.roomprice {
	border: 1px solid #ddd;
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	font-size: 15px;
}

.style-simple table td {
	border-width: 1px;
}

.booking-title.en {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	margin-bottom: 20px;
}

.subheader-both-center #Subheader .title {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	color: #fff;
}

.subheader-both-center #Subheader h4 {
	width: 100%;
	text-align: center;
	color: #fff;
}

.entry-title, .desc-wrapper {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
}

.entry-title a {
	color: #aa1832;
	font-size: 20px;
}

#Subheader .title {
	font-size: 46px;
}

.style-simple #Filters .filters_wrapper ul li a {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	font-size: 20px;
}

a.button.sp_link {
	margin-bottom: -18px;
	margin-left: 8px;
}

.faq .question .title {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	color: #aa1832;
	font-size: 20px;
}

.faq .question.active .title {
	color: #aa1832;
	font-size: 20px;
}

ol li, ul li {
	font-size: 15px;
}

.cn, .sub-menu, h3.title, .portfolio-photo .portfolio-item .portfolio-details .details .categories
	{
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
}

.portfolio-photo .portfolio-item .portfolio-details .details .categories
	{
	font-size: 17px;
}

.sub-menu li {
	font-size: 16px;
}

.button {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	font-size: 16px;
}

a.button_theme {
	background-color: #aa1832;
}

a.button:hover {
	background-color: #333;
	-webkit-transition: all .5s;
	transition: all .5s;
}

.boutique-section {
	margin-bottom: 20px;
}

.layout-full-width #Wrapper {
	box-sizing: border-box;
	padding: 0 20px 20px;
}

.widget .Recent_posts {
	width: 85%;
}

#Footer .widget_categories ul {
	background-color: transparent;
}

h2.entry-title {
	font-size: 180%;
	line-height: 120%;
}

.margin-top-20 {
	margin-top: 20px;
}

#custom_post_widget-674 table {
	border: 1px solid #ddd;
}

.rest.pallax {
	padding: 60px 8% 45px;
}

#Side_slide {
	background-color: rgba(0, 0, 0, 0.5);
}

#Side_slide
 
a
:not
 
(
.button
 
)
{
color
:
 
#ddd
;


}
.style-simple .trailer_box:hover .desc {
	background-color: #aa1832;
}

#Footer .footer_copy .social li a {
	font-size: 18px;
}

#Footer .footer_copy .social li:last-child {
	margin-left: 6px;
}

#Footer h4 {
	color: #999;
	font-weight: 400;
	font-size: 28px;
}

.stand_txt a {
	font-family: "Roboto", 'LiHei Pro', Microsoft JhengHei, Arial, Tahoma,
		sans-serif;
	font-size: 16px !important;
	text-decoration: none;
}

@media only screen and (max-width: 767px) {
	#text-5 .stand_txt .column.one-second {
		width: 50% !important;
		clear: none;
		text-align: center;
	}
	#media_image-2 {
		text-align: center;
	}
	table.home {
		margin-top: 0px;
	}
	table.home-wea {
		margin-top: 85px;
	}
	.wea.column {
		margin: 0 0px;
	}
	.no-line-p {
		height: 65px !important;
	}
	.no-bg-p {
		background-color: #fff !important;
	}
	.bg-51 {
		background-color: rgba(51, 51, 51, 0.6);
	}
	.section_wrapper {
		padding-left: 0px !important;
		padding-right: 0px !important;
	}
	.portfolio_group .portfolio-item .list_style_header .links_wrapper {
		display: block;
	}
	a.portfolio_prev_js, a.portfolio_next_js {
		display: none;
	}
	.portfolio_group .portfolio-item .list_style_header .links_wrapper a:last-child
		{
		margin-top: 0px;
	}
	.portfolio_group .portfolio-item .list_style_header h3 {
		letter-spacing: 0px;
	}
	.portfolio_group .portfolio-item .list_style_header h3 {
		margin-top: 15px;
	}
	.portfolio_group .portfolio-item .list_style_header {
		min-height: unset;
	}
	.dis-none {
		display: block;
	}
	.p-dis-none {
		display: none;
	}
}
</style>

</head>
<body>

	<!-- 首頁開頭 -->
	<nav class="nav navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">

				<!-- class="navbar-toggle" 這是關鍵字 有它才能讓我們順利的看到「三」 而且出現在視窗的右方 -->
				<!-- data-toggle="collapse" 這是用來製作動畫效果的 沒有的話 我們點「三」的時候不會有任何反應-->
				<!-- data-target="#myNavbar" 這裡面的myNavbar是一個CSS的id 我們可以取任意的名字 只是一定要跟下面縮放的id對應到 -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<!-- <span class="icon-bar"></span>這段這是用來產生「一」 當有三個「一」時 合在一起就會變「三」的按鍵了 -->
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- 點擊商標跳轉至首頁 #要放首頁網址 -->
				<a class="navbar-header" href="#"> <img
					src="https://i.imgur.com/2j6Zzxv.png" height="40"
					title="島旅 Island Travel" alt="島旅 Island Travel">
					</a>
			</div>
			<!-- class="collapse navbar-collapse" 這兩個是關鍵字 幫助我們在正常視窗的情況下 可以看到原本的導覽列 -->
			<div class="collapse navbar-collapse" id="myNavbar">
				<!-- 底下放要縮放的內容 -->
				<ul class="nav navbar-nav navbar-left">
					<!-- a href="#" 將要導入的網址放在" "裡面 記得把#拿掉 -->
					<li><a href="#">線上訂房</a></li>
					<li><a href="#">預定活動</a></li>
					<li><a href="#">商城</a></li>
					<li><a href="#">社群</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!-- a href="#" 將要導入的網址放在" "裡面 記得把#拿掉 -->
					<!-- class="glyphicon glyphicon-log-in" 這裡是使用bootstrap所提供的icon 直接用class就可以插入這些icon -->
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							登入</a></li>
					<!-- <li><a href="#">登出</a></li> -->
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							會員中心</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 輪播 -->
	<!-- 輪播需要使用 id在程式碼中(id="myCarousel"）才能使輪播控件正常運行 -->
	<div class="container">
		<!-- slide 添加CSS過渡和動畫效果 這使得項目滑動呈現出新的項目 -->
		<!-- data-ride="carousel" 屬性告訴 Bootstrap 在頁面加載時立即開始為輪播設置動畫 -->
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<!-- data-target屬性指向輪播的 id，data-slide-to屬性指定單擊特定點時要轉到的幻燈片-->
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>
			<!-- 幻燈片包裝 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="https://i.imgur.com/G8EL2MK.jpg" alt="sea"
						style="width:100%;height:100%;">
				</div>
				<div class="item">
					<img src="https://i.imgur.com/0PHnaog.jpg" alt="sea"
						style="width: 100%;height:100%;">
				</div>
				<div class="item">
					<img src="https://i.imgur.com/jvIiblK.jpg" alt="sea"
						style="width: 100%;height:100%;">
				</div>
				<div class="item">
					<img src="https://i.imgur.com/syaT1rJ.jpg" alt="sea"
						style="width: 100%;height:100%;">
				</div>
			</div>
			<!-- 左右控制按鈕 
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
				<span class="sr-only">Next</span>
			</a> -->
		</div>
	</div>

	<!-- 首頁結尾 -->
	<footer id="Footer" class="clearfix">
		<div class="widgets_wrapper">
			<div class="container">
				<div class="column one-third">
					<aside id="media_image-2" class="widget widget_media_image">
						<img width="240" height="172" src="https://i.imgur.com/fsslT0i.png"
							class="image wp-image-284  attachment-full size-full"
							alt="島旅logo" style="max-width: 100%; height: auto;" srcset=""
							sizes="(max-width: 240px) 100vw, 240px" />
					</aside>
				</div>
				<div class="column one-third">
					<aside id="text-5" class="widget widget_text">
						<div class="textwidget">
							<h4>資訊欄</h4>
							<div class="one mcb-wrap stand_txt">
								<div class="column one-second">
									<ul style="line-height: 38px; list-style-type: none">
										<li><a href="#" target="_blank" rel="noopener noreferrer">線上訂房</a></li>
										<li><a href="#" target="_blank" rel="noopener noreferrer">討論區</a></li>
										<li><a href="#">最新消息</a></li>
										<li><a href="#">瀏覽資訊</a></li>
									</ul>
								</div>
								<div class="column one-second">
									<ul style="line-height: 38px; list-style-type: none">
										<li><a href="#">交通資訊</a></li>
										<li><a href="#">天氣資訊</a></li>
										<li><a href="#">常見問題</a></li>
										<li><a href="#">關於島旅</a></li>
									</ul>
								</div>
							</div>
						</div>
					</aside>
				</div>
				<div class="column one-third">
					<aside id="text-4" class="widget widget_text">
						<div class="textwidget">
							<h4 style="color: #aa1832;">地址訊息</h4>
							<div class="stand_txt" style="line-height: 30px; color: #ccc;">
								<img src="https://i.imgur.com/hIpnqqy.png" width="15"> 島旅縣島旅市島旅路100號
							</div>
							<div class="stand_txt" style="line-height: 30px; color: #ccc;">
								<img src="https://i.imgur.com/ajKbJzD.png" width="15"> 電話 +886-9876-5432<br />
								<img src="https://i.imgur.com/EVCq5UM.png" width="15"> 傳真 +886-9876-3325<br />
								<img src="https://i.imgur.com/rFmwESc.png" width="15">
								islandTravel@gmail.com
							</div>
						</div>
					</aside>
				</div>
			</div>
		</div>

		<div class="footer_copy">
			<div class="container">
				<div class="column one">
					<div class="copyright">島旅 Ⓒ Copyright 2021</div>
					<ul class="social" style="list-style-type: none">
						<li class="facebook">
							<a href="https://www.facebook.com/" title="Facebook">
								<img src="https://i.imgur.com/HUIWvsS.png" height="20">
							</a></li>
						<li class="instagram">
							<a href="https://www.instagram.com/" title="Instagram">
								<img src="https://i.imgur.com/yaPh1BJ.png" height="20">
							</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<!-- 匯入bootstrap javascript -->
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>