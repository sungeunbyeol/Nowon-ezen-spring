<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- cate_list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>

<div align="center">
	<table border="1" width="700">
		<caption><h3>ī�װ� ���</h3></caption>
		<tr>
			<th>��ȣ</th>
			<th width="30%">ī�װ��ڵ�</th>
			<th width="50%">ī�װ���</th>
			<th>����</th>
		</tr>
	<c:if test="${empty cateList}">
		<tr>
			<td colspan="4">��ϵ� ī�װ��� �����ϴ�.</td>
		</tr>
	</c:if>	
	<c:forEach var="dto" items="${cateList}">
		<tr>
			<td align="right">${dto.cnum}</td>
			<td align="center">${dto.code}</td>
			<td align="center">${dto.cname}</td>
			<td align="center"><a href="cate_delete.do?cnum=${dto.cnum}">����</a></td>
		</tr>
	</c:forEach>	
	</table>
</div>
<%@ include file="bottom.jsp"%>
















