<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>example</title>
	</head>
	<body>
	<c:if test="${empty loginOkBean}">
	<%@ include file="user/user_top1.jsp" %>
	</c:if>
	<c:if test="${not empty loginOkBean}">
	<%@ include file="user/user_top2.jsp" %>
	</c:if>
	
	
	</body>
</html>