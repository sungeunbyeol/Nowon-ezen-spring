<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- list.jsp -->
<html>
<head>
	<title>spring게시판</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
	<b>글 목 록</b>
	<table border="0" width="800">
		<tr bgcolor="yellow">
			<td align="right"><a href="write_board.do">글쓰기</a></td>
		</tr>
	</table>
	<table border="1" width="800">
		<tr bgcolor="green">
			<th>번호</th>
			<th width="30%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<th>IP</th>
		</tr>
		<c:if test="${empty listBoard}">
		<tr>
			<td colspan="6">등록된 게시글이 없습니다.</td>
		</tr>		
		</c:if>
		<c:set var="num" value="${number}"/>
		<c:forEach var="dto" items="${listBoard}">
		<tr>
			<td>${num}</td>
			<c:set var="num" value="${num-1}"/>
			<td>
				<c:if test="${dto.re_level>0}">
					<img src="images/level.gif" width="${dto.re_level*10}">
					<img src="images/re.gif">
	
				</c:if>
				<a href="content_board.do?num=${dto.num}">
					${dto.subject}
				</a>
				<c:if test="${dto.readcount>10}">
					<img src="images/hot.gif">
				</c:if>
			</td>
			<td>${dto.writer}</td>
			<td>${dto.reg_date}</td>
			<td>${dto.readcount}</td>
			<td>${dto.ip}</td>
		</tr>	
		</c:forEach>
	</table>
	<c:if test="${rowCount>0}">
		<c:if test="${startPage>pageBlock}">
			<a href="list_board.do?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">			
			<a href="list_board.do?pageNum=${i}">[${i}]</a>			
		</c:forEach>			
		<c:if test="${endPage < pageCount}">
			<a href="list_board.do?pageNum=${startPage+pageBlock}">[다음]</a>			
		</c:if>	
	</c:if>
</div>













