<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, member.dto.*"%>
<!-- memberAll.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<div align="center">
	<hr color="green" width="300">
	<c:if test="${empty param.mode}">
		<h2>ȸ �� �� �� �� ��</h2>
	</c:if>
	<c:if test="${not empty param.mode}">
		<h2>ȸ �� ã ��</h2>
		<form name="f" method="post" action="memberAll.do">
			<input type="hidden" name="mode" value="find"/>
			<select name="search">
				<option value="id">���̵�</option>
				<option value="name">ȸ����</option>
			</select>
			<input type="text" name="searchString">
			<input type="submit" value="ã��">
		</form>
	</c:if>
	<hr color="green" width="300">
	<table width="100%" class="outline">
		<tr bgcolor="yellow">
			<th class="m3">��ȣ</th>
			<th class="m3">ȸ����</th>
			<th class="m3">���̵�</th>
			<th class="m3">�̸���</th>
			<th class="m3">��ȭ��ȣ</th>
			<th class="m3">������</th>
			<th class="m3">����|����</th>
		</tr>
	<c:if test="${empty listMember}">
		<tr>
			<td colspan="7">��ϵ� ȸ���� �����ϴ�.</td>
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
			<td><a href="editMember.do?no=${dto.no}">����</a> |
			<a href="deleteMember.do?no=${dto.no}">����</a></td>
		</tr>	
	</c:forEach>	
	</table>
</div>
<%@ include file="bottom.jsp"%>








