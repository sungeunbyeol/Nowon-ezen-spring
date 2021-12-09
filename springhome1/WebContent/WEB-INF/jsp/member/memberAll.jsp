<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, member.dto.*"%>
<!-- memberAll.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<div align="center">
	<hr color="green" width="300">
	<c:if test="${empty param.mode}">
		<h2>회 원 목 록 보 기</h2>
	</c:if>
	<c:if test="${not empty param.mode}">
		<h2>회 원 찾 기</h2>
		<form name="f" method="post" action="memberAll.do">
			<input type="hidden" name="mode" value="find"/>
			<select name="search">
				<option value="id">아이디</option>
				<option value="name">회원명</option>
			</select>
			<input type="text" name="searchString">
			<input type="submit" value="찾기">
		</form>
	</c:if>
	<hr color="green" width="300">
	<table width="100%" class="outline">
		<tr bgcolor="yellow">
			<th class="m3">번호</th>
			<th class="m3">회원명</th>
			<th class="m3">아이디</th>
			<th class="m3">이메일</th>
			<th class="m3">전화번호</th>
			<th class="m3">가입일</th>
			<th class="m3">수정|삭제</th>
		</tr>
	<c:if test="${empty listMember}">
		<tr>
			<td colspan="7">등록된 회원이 없습니다.</td>
		</tr>
	</c:if>	
	<c:forEach var="dto" items="${listMember}">
		<tr>
			<td>${dto.no}</td>
			<td>${dto.name}</td>
			<td>${dto.id}</td>
			<td>${dto.email}</td>
			<td>${dto.allHp}</td>
			<td>${dto.joindate}</td>
			<td><a href="editMember.do?no=${dto.no}">수정</a> |
			<a href="deleteMember.do?no=${dto.no}">삭제</a></td>
		</tr>	
	</c:forEach>	
	</table>
</div>
<%@ include file="bottom.jsp"%>








