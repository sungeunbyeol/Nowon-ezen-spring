<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>mvc홈페이지</title>
</head>
<body>
	<h1 align="center">MVC로 만들어 보는 홈페이지</h1>
	<c:if test="${not empty loginOkBean}">
		<h3 align="center">${loginOkBean.name}[${loginOkBean.id}]님 로그인 중......</h3>
	</c:if>
	<ul>	
		<li><h3><a href="main.do">main</a></h3></li>
		<li><h3><a href="member.do">회원관리</a></h3></li>
		<li><h3>
			<c:if test="${empty loginOkBean}">
				<a href="login.do">로그인</a>
			</c:if>
			<c:if test="${not empty loginOkBean}">
				<a href="logout.do">로그아웃</a>
			</c:if>
		</h3></li>
		<li><h3><a href="shopAdmin.do">쇼핑몰</a></h3></li>
		<li><h3><a href="list_board.do">게시판</a></h3></li>
	</ul>
</body>
</html>








