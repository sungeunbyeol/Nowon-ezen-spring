<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>도서찾기</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>도 서 찾 기</h2>
		<hr color="green" width="300">
		<table border="1" width="650">
			<tr bgcolor="skyblue">
				<th>도서명</th>
				<th>출판사</th>
				<th>지은이</th>
				<th>판매가</th>
				<th>입고일</th>
			</tr>	
			<c:set var="dto" value="${findlist}" />
			<tr bgcolor="pink">
				<td>${dto.bookname}</td>
				<td>${dto.publisher}</td>
				<td>${dto.author}</td>
				<td>${dto.price}</td>
				<td>${dto.deliverydate}</td>
			</tr>
		</table>	
	</div>
</body>
</html>