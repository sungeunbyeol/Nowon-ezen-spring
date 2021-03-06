<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/htm; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/topstyle.css">
<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/66da7a4b92.js" crossorigin="anonymous"> 
</script>
<title>호텔잔치</title>
</head>
<body>
	<nav class="navbar"><!-- 제일큰박스 -->
		<div class="navbar_logo">
			<i class="fas fa-suitcase"></i>
			<a style = "color:lemonchiffon;" href="main">Hotel Festival</a>
		</div>
		 
		<c:if test="${empty loginOkBean}">
		<ul class="navbar_menu">
			<li><a style="color:lemonchiffon;" href="main">Home</a></li> 
			<li><a style="color:lemonchiffon;" href="company_main" target='_blank'>기업페이지</a></li>
			<li><a style="color:lemonchiffon;" href="activity_usermain">액티비티</a></li>
			<li><a style="color:lemonchiffon;" href="user_login">로그인</a></li>
			<li><a style="color:lemonchiffon;" href="user_joinchoose">회원가입</a></li>
		</ul>
		
		</c:if>
		<c:if test="${not empty loginOkBean}">
		<ul class="navbar_menu">
			<li><a style="color:lemonchiffon;" href="main">Home</a></li>
			<li><a style="color:lemonchiffon;" href="activity_usermain">액티비티</a></li>
			<li><a style="color:lemonchiffon;" href="user_myPage">마이페이지</a></li>
			<li><a style="color:lemonchiffon;" href="user_wishlist">위시리스트</a></li>
			<li><a style="color:lemonchiffon;" href="user_logout">로그아웃</a></li>
		<c:if test="${loginOkBean.a_level == '3' }">
			<li><a style="color:lemonchiffon;" href="admin_user_list">관리자 페이지로 이동</a></li>
		</c:if>
		</ul>  
		</c:if>		 
</nav> 
