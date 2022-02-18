<%@ page language="java" contentType="text/html; charset=UTF-8C-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>maven홈페이지</title>
</head>
<body>
	<h1 align="center">MVC로 만들어 보는 홈페이지</h1>
	<c:if test="${not empty loginOkBean}">
		<h3 align="center">${loginOkBean.name}[${loginOkBean.id}]님 로그인 중......</h3>
	</c:if>
	<ul>	
		<li><h3><a href="main">main</a></h3></li>
		<li><h3><a href="memberindex">회원관리</a></h3></li>
		<li><h3>
			<c:if test="${empty loginOkBean}">
				<a href="login">로그인</a>
			</c:if>
			<c:if test="${not empty loginOkBean}">
				<a href="logout">로그아웃</a>
			</c:if>
		</h3></li>
		<li><h3><a href="shopAdmin">쇼핑몰</a></h3></li>
		<li><h3><a href="list_board">게시판</a></h3></li>
	</ul>
</body>
</html>








