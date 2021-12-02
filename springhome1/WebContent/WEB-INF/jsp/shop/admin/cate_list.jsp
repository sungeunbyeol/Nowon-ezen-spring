<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- cate_list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>

<div align="center">
	<table border="1" width="700">
		<caption><h3>카테고리 목록</h3></caption>
		<tr>
			<th>번호</th>
			<th width="30%">카테고리코드</th>
			<th width="50%">카테고리명</th>
			<th>삭제</th>
		</tr>
	<c:if test="${empty cateList}">
		<tr>
			<td colspan="4">등록된 카테고리가 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="dto" items="${cateList}">
		<tr>
			<td align="right">${dto.cnum}</td>
			<td align="center">${dto.code}</td>
			<td align="center">${dto.cname}</td>
			<td align="center"><a href="cate_delete.do?cnum=${dto.cnum}">삭제</a></td>
		</tr>
	</c:forEach>	
	</table>
</div>
<%@ include file="bottom.jsp"%>
















