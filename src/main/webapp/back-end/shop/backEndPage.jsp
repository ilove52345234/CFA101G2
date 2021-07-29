<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh">
<head>

<title>�q�� Island Brigade</title>
<!-- �������D������� -->
<link rel="icon"
	href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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">
<!-- ���ç���ܹϥ� -->
<link rel="icon"
	href="data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKfowJSKjMQTSUuELCgpAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABULjYMNwoVXDMFEJwyBA7SMQIN9jMFD/8zBRD/MQIN+DIED9Q0BhCgNwoVYFEpMg4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEGiQYNAcRkDIED/RkQkr/oIqP+tDGyP/x7u/4+Pf3//j39//w7O3/0sjK/6OPlP9oRk7/MwUQ9jQHEZhBFiAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSeH8ANwoVZjMFEO5+YWj/4Nrb//7+/v////////////////////////////////////////////7+/v/j3d//hGhv/DQGEfA2CRNuZkVLAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYz9ICDcKFKYyBA//hGlv/6qWm/+5qq3/4tze//7+/v/////////////////////////////////////////////////+/v7/2NDS/1QtNv8zBRCuTiYwCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEYdJggyAw64LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/d1hg//v7+///////////////////////////////////////////////////////9vT0/3BPV/8wAg3CSSApCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACUfoMAMwUQoC8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8wAQz/5eDh//////////////////////////////////////////////////7+/v/l3+D/hGlv/y8AC/8zBRCuZEBJAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADcJFGAvAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/OAsV/6KOk//+/v7//////////////////////////////////v3+/93V1/+lkZX/ZEFJ/zEDDf8vAAv/LwAL/y8AC/82CBNwAAAAAAAAAAAAAAAAAAAAAAAAAABFGyUSMAEM8C8AC/8vAAv/LwAL/y8AC/85DBf/c1Rb/7Ceov/v7O3/////////////////9vT0/9jP0f+0pKf/knl//2xKUv9AFR//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zABDPI/FB4eAAAAAAAAAAAAAAAAAAAAADQGEYgvAAv/LwAL/y8AC/8vAAv/iG1z//Px8f/////////////////x7e7/j3Z8/1QtNv8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/zQHEZQAAAAAAAAAAAAAAABJICkMMAIN7i8AC/8vAAv/LwAL/y8AC/+Wf4T/9fPz//////////////////Xy8/+diI3/Z0RM/0UbJf8xAg3/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAK/y8ACv8vAAv/MAIN9E0lLg4AAAAAAAAAADkNF04+Ehz/NwoV/y8AC/8vAAv/LwAL/y8AC/84Cxb/Z0VN/410ev+rmJz/w7a5/9XMzv/l3+D/9/X2//Xz9P/j3d7/1MrM/6yanv9NJS//NQgS/0UbJf8yBA//LwAL/y8AC/8vAAv/LwAL/y8AC/8vAAv/NwoUYgAAAAAAAAAAMwUQnJeAhf/y7/D/VzE6/y8AC/8vAAv/LwAL/y8AC/8vAAv/LwAL/y8ACv8vAAv/LwAL/y8AC/80BhH/fmFo//7+/v////////////Ds7f/49vf//v7+//bz9P/Z0dP/uamt/1s3P/8vAAv/LwAL/y8AC/80BhCiAAAAAAAAAAAyBA/GxLa5//7+/v9QKDH/LwAL/y8AC/8vAAv/LwAL/1ApMv88EBv/LwAK/y8AC/9GHSb/SiEq/7emqv/5+Pj/////////////////ppKX/72usv//////////////////////7Ojp/1EpMv90VVz/oo2S/zIEDtYAAAAAAAAAADIED+Tg2dv//////410ev8vAAv/LwAL/1w3QP+6q6//8/Dx/97X2f+VfYP/08nL//39/f/Dtrn/lX2C/8u/wv/+/v7////////////Bs7f/jnV7//v6+v///////////////////////f39///////e19n/MAEM+KeUmAQAAAAAMQIN+LWlqf/j3d//lHyC/y8AC/+chov//Pv7//////////////////39/f///////v7+//7+/v+HbHL/moOJ/4txd//k3t//rJqe/105QfZHHij/1szP/+Lc3f///////////////////////v7+/3VWXf8vAAv/SSAqEgAAAAAxAw72Rx4n/zsPGf8vAAv/YT1G//r6+v//////////////////////uamt///////f2Nr/nIaL/4VqcP80Bg//i2RQ/2tCOP+Xcln/w6J6/zYIEP+Zgof/mYKI//z8/P/29PX/2M/R///////29PT/MQMO/y8AC/9WMDkQAAAAADEDDuBLHiD/VCgm/3NLPv+EXUv/XDQ2/5yGjP/18/T///////////9qSFD/+ff3/9/Y2v9PJzD/a0lP/6qGZ//bvIz/27yM/9u8jP/bvIz/bEM4/104Qf/z8PD/r5yh//7+/v92V1//7Ojp/62bn/8vAAv/MAIN9rqqrwIAAAAAMgQPwqN/Yv/bvIz/27yM/9u8jP/YuYr/nHdd/2RARv/s6On//////3FRWf/m4OL/ZUNL/zMEDv+lgWP/27yM/9u8jP/bvIz/3sGW/9/DmP/JqH7/Xjc7//Xz9P9/Ymn/8Ozt/z8UHv87Dhn/LwAL/1sxLP8yAw7OAAAAAAAAAAA0BhGQglpJ/9u8jP/bvIz/27yM/9u8jP/bvIz/uJZx/102Ov/g2dv/gGNq/4Rob/81CBL/oX1g/9u8jP/bvIz/27yM/9u9jv/t3sf/7+HM/9y+j/+lgWT/gmZs/4pvdv+0o6f/LwAL/y8AC/9mPDT/hV5L/zMFEJoAAAAAAAAAADkMF0JIGx7/2ruL/9u8jP/bvIz/27yM/9u8jP/bvIz/xaR7/1w0NP9FGyX/QRMY/6mFZv/bvIz/27yM/93Ak//bvIz/27yM/9y/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"
	type="images/logo_2.ico">
<meta charset="utf-8">
<!-- width=device-width�����]�m�������e�ץH���H�]�ƪ��̹��e�ס]�o�]�]�ƦӲ�) -->
<!-- initial-scale=1�����]�m�s���������[�������ɪ���l�Y��ŧO -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
/* �� float:left �O���F�n�����O�@�ӱ��ۤ@�Ӿ�V�ݮi�Aposition: relative ���ηN�O�]�����U�ӭn���U�Կ��A���w���I���Ѧ� */
ul.navbar-nav li {
	position: relative;
	float: left;
	list-style: none;
}
/* ul.navbar-nav li a�A�N�O�Τ@�]�w��椺�s�����˦��A�ڭ̧� a �]�� block �~�������F�r�H�~�b�d�򤺳��i�H���s�����ĪG */
ul.navbar-nav li a {
	display: block;
	padding: 12px 20px;
	text-decoration: none; /* ��b�o */
}

ul.navbar-nav>li>a:hover {
	color: #666;
	background: #DDD;
}

ul.navbar-nav li a:hover {
	color: #666;
	background: #DDD;
}
/* �h�h��檺�˦�...�ĤG�h����U�Գ������˦� */
ul.navbar-nav li ul {
	display: none;
	float: left;
	position: absolute;
	margin: 0;
	padding: 0;
}
/* ��Ĥ@�h���QĲ�o�ɡA���w�ĤG�h��� */
ul.navbar-nav li:hover>ul {
	display: block;
}
/* �ĤG�h�Τ��� ��� li ���˦� */
ul.navbar-nav ul a {
	width: 142px;
	padding: 10px 12px;
	color: #666;
	background: #EEE;
}

ul.navbar-nav ul a:hover {
	background: #CCC;
}

.dropdown-submenu {
	position: relative;
}

.dropdown-submenu .dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: 0px;
	padding: 10px 10px;
	color: #666;
	background: #EEE;
}
</style>

</head>
<body>

	<!-- �����}�Y -->
	<nav class="nav navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<!-- class="navbar-toggle" �o�O����r �����~�����ڭ̶��Q���ݨ�u�T�v �ӥB�X�{�b�������k�� -->
				<!-- data-toggle="collapse" �o�O�Ψӻs�@�ʵe�ĪG�� �S������ �ڭ��I�u�T�v���ɭԤ��|���������-->
				<!-- data-target="#myNavbar" �o�̭���myNavbar�O�@��CSS��id �ڭ̥i�H�����N���W�r �u�O�@�w�n��U���Y��id������ -->
<!-- 				<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 					data-target="#myNavbar"> -->
					<!-- <span class="icon-bar"></span>�o�q�o�O�ΨӲ��͡u�@�v ���T�ӡu�@�v�� �X�b�@�_�N�|�ܡu�T�v������F -->
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- �I���Ӽи���ܭ��� #�n�񭺭����} -->
				<a class="navbar-header" href="#"> <img src="https://i.imgur.com/2j6Zzxv.png" height="40" title="�q�� Island Brigade" alt="�q�� Island Brigade"></a>
			</div>
			<!-- class="collapse navbar-collapse" �o��ӬO����r ���U�ڭ̦b���`���������p�U �i�H�ݨ�쥻�������C -->
			<div class="collapse navbar-collapse" id="myNavbar">
				<!-- ���U��n�Y�񪺤��e -->
				<ul class="nav navbar-nav navbar-left">
					<!-- a href="#" �N�n�ɤJ�����}��b" "�̭� �O�o��#���� -->
					<li><a href="#">�q�к޲z<span class="caret"></span></a>
						<ul>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">��к޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�ק�q�檬�A</a></li>
									<li><a tabindex="-1" href="#">�f�֭ק�q��</a></li>
									<li><a tabindex="-1" href="#">�d�ݦ�Эq��</a></li>
									<li><a tabindex="-1" href="#">�d�ߦ�Ⱦ��v</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�Ы��޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�s�W/�R���Ы�</a></li>
									<li><a tabindex="-1" href="#">�W/�U�[�Ы�</a></li>
									<li><a tabindex="-1" href="#">�Ы��ۤ��޲z</a></li>
									<li><a tabindex="-1" href="#">�Ы�����޲z</a></li>
									<li><a tabindex="-1" href="#">�Ы��ԭz�޲z</a></li>
									<li><a tabindex="-1" href="#">�e�x����</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�ж��޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�ж��ƶq�d��</a></li>
									<li><a tabindex="-1" href="#">�]�w�ж����p</a></li>
									<li><a tabindex="-1" href="#">�Ф��~���޲z</a></li>
									<li><a tabindex="-1" href="#">�򥢪��޲z</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">Check in<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�ж����p�d��</a></li>
									<li><a tabindex="-1" href="#">�d�ߦ�ȸ��</a></li>
									<li><a tabindex="-1" href="#">�إ��B�~���O����</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">Check out<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�ж����p�d��</a></li>
									<li><a tabindex="-1" href="#">�d���B�~���O����</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�q�ЫP�P�޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�إ߭q���u�f</a></li>
								</ul></li>
						</ul></li>
					<li><a href="#">���ʺ޲z<span class="caret"></span></a>
						<ul>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">���ʺ޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">���ʶ}�l�|��</a></li>
									<li><a tabindex="-1" href="#">���ʨ����|��</a></li>
									<li><a tabindex="-1" href="#">�ѥ[�H���޲z</a></li>
									<li><a tabindex="-1" href="#">�o�e�����ܽ�</a></li>
									<li><a tabindex="-1" href="#">���ʦ�ƾ�</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�������O�޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�s�W/�קﬡ��</a></li>
									<li><a tabindex="-1" href="#">�W/�U�[����</a></li>
									<li><a tabindex="-1" href="#">�e�x����</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">���ʫP�P�޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�إ߬����u�f���</a></li>
								</ul></li>
						</ul></li>
					<li><a href="#">�ӫ��޲z<span class="caret"></span></a>
						<ul>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�ӫ~�޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="searchCommodity.jsp">�ӫ~��ƺ޲z</a></li>
									<li><a tabindex="-1" href="addCommodityContent.jsp">�ӫ~����</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�q��޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�q����Ӻ޲z</a></li>
									<li><a tabindex="-1" href="#">���q�檬�A</a></li>
									<li><a tabindex="-1" href="#">����r�d�߭q��</a></li>
									<li><a tabindex="-1" href="#">�X�f�Ƶ{</a></li>
									<li><a tabindex="-1" href="#">�h�f�f��</a></li>
								</ul>
							</li>
						</ul>
					</li>
					<li><a href="#">�|���޲z<span class="caret"></span></a>
						<ul>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�|���b���޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�d�\�|�����U���</a></li>
									<li><a tabindex="-1" href="#">�|�����v�޲z</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">�峹�޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�B�z���|�峹</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">��ѫǺ޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�إ߲�ѫ�</a></li>
								</ul></li>
							<li><a href="#">�u�W�ȪA</a></li>
						</ul></li>
					<li><a href="#">���u�޲z<span class="caret"></span></a>
						<ul>
							<li class="dropdown-submenu"><a class="test" tabindex="-1"
								href="#">��x�b���޲z<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a tabindex="-1" href="#">�n�J</a></li>
									<li><a tabindex="-1" href="#">�s�W�޲z��</a></li>
									<li><a tabindex="-1" href="#">�R���޲z��</a></li>
									<li><a tabindex="-1" href="#">�v���޲z</a></li>
								</ul></li>
							<li><a href="#">��ѫǺ޲z</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!-- a href="#" �N�n�ɤJ�����}��b" "�̭� �O�o��#���� -->
					<!-- class="glyphicon glyphicon-log-in" �o�̬O�ϥ�bootstrap�Ҵ��Ѫ�icon ������class�N�i�H���J�o��icon -->
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							�޲z���n�J</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<script>
		$(document).ready(function() {
			$('.dropdown-submenu a.test').on("click", function(e) {
				$(this).next('ul').toggle();
				e.stopPropagation();
				e.preventDefault();
			});
		});
	</script>
</body>
</html>