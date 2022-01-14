<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!-- 마이페이지 -->
<link rel="stylesheet" type="text/css" href="style.css"> 
<%@ include file="../user_top.jsp"%>
<c:if test = "${loginOkBean==null}">
	<c:redirect url = "main"/>
</c:if>
<div align="center">
	<table border="1" width="1000" height="850">
		<tr height="10%">
			<td align="center" colspan="2">
				<h1>마이페이지</h1>
			</td>
		</tr>
		<tr>
			<td width="20%">
				<h3><a href="user_info">내 정보관리</a></h3><br>
				<h3><a href="user_bookList">예약내역</a></h3><br>
				<h3><a href="user_reviewList">내가쓴리뷰</a></h3><br>
				<h3><a href="user_pointList">포인트</a></h3><br>
				<h3><a href="list_userQnA">Q&A</a></h3><br>
			</td>
			<td>