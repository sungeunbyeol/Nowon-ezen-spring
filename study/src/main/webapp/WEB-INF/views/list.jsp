<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>�������</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2> �� �� �� �� �� ��</h2>
		<hr color="green" width="300">
		<table border="1" width="650">
			<tr bgcolor="skyblue">
				<th>������</th>
				<th>���ǻ�</th>
				<th>������</th>
				<th>�ǸŰ�</th>
				<th>�԰���</th>
			</tr>
			<c:forEach var="dto" items="${list_list}">
			<tr bgcolor="pink">
				<td>${dto.bookname}</td>
				<td>${dto.publisher}</td>
				<td>${dto.author}</td>
				<td>${dto.price}</td>
				<td>${dto.deliverydate}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>