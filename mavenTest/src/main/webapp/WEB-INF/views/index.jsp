<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>mvcȨ������</title>
</head>
<body>
	<h1 align="center">MVC�� ����� ���� Ȩ������</h1>
	<c:if test="${not empty loginOkBean}">
		<h3 align="center">${loginOkBean.name}[${loginOkBean.id}]�� �α��� ��......</h3>
	</c:if>
	<ul>	
		<li><h3><a href="main.do">main</a></h3></li>
		<li><h3><a href="member.do">ȸ������</a></h3></li>
		<li><h3>
			<c:if test="${empty loginOkBean}">
				<a href="login.do">�α���</a>
			</c:if>
			<c:if test="${not empty loginOkBean}">
				<a href="logout.do">�α׾ƿ�</a>
			</c:if>
		</h3></li>
		<li><h3><a href="shopAdmin.do">���θ�</a></h3></li>
		<li><h3><a href="list_board.do">�Խ���</a></h3></li>
	</ul>
</body>
</html>








