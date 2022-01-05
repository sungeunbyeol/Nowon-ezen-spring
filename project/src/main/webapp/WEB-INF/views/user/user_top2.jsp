<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>logoutTop</title>
</head>
<style>
		li{
			list-style: none;
			display:inline-block
		}
		.colorBtn{
			width: 150px;
			height: 36px;
			background-color: yellowgreen;
			color: white;
			text-align: center;
			line-height: 36px;
			cursor: pointer;
			border-radius: 6px;
			float: left;
			margin-right: 10px;
		}
		.revBt{
			width: 80px;
			height: 30px;
			background-color: yellow;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			float: right;
			margin-right: 10px;
		}
		.revBt2{
			width: 30px;
			height: 30px;
			background-color: yellow;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			float: right;
			margin-right: 10px;
		}
		.bottomBt{
			width: 180px;
			height: 30px;
			background-color: white;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			margin-right: 10px;
		}
		ul{
			text-align: center
		}
		.topFont{
			color : white;
		}
	</style>
<body>
	<div align = "center">
			<!-- 로그인 한 후 -->
			<table align = "center" width = "100%" height = "50" bgcolor="red">
				<tr>
				<td width = "17%"></td>
					<td align="left" width="20%" class="topFont"><a href="main">호텔대잔치</a></td>			<!-- 로고이미지 -->
					<td width = "33%"></td>
					<c:if test="${not empty loginOkBean}">
					<td width = "3%" class="topFont">${loginOkBean.u_name}님 접속중<br></td></c:if>
					<td width = "3%" class="topFont"><a href="user_info">마이페이지</td>						<!-- front쪽에서 리스트 적용하여(내 정보관리, 예약내역, 포인트, Q&A) -->
					<td width = "3%" class="topFont">
						<c:if test = "${not empty loginOkBean}">
							<a href="user_logout">로그아웃</a>
						</c:if>
					</td> 					<!-- 로그아웃 후 회원메인 페이지로 -->				
					<td width = "5%" class="topFont"><a href="user_wishlist?u_num=${sessionScope.loginOkBean.u_num}">위시리스트</a></td>						<!-- 위시리스트 페이지로 -->
					<td width = "10" class="topFont">관리자 페이지로<br><!-- (user_personal의 level이 3일경우) --></td>	<!-- 일반관리자접속시 -->
				</tr>
			</table>
	</div>	

