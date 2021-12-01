<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- memberSsn.jsp -->
<%@ include file="top.jsp" %>
<div align="center">
	<hr color="green" width="300">
	<h2>회 원 가 입 유 무</h2>
	<hr color="green" width="300">
	<form name="f" action="checkMember.do" method="post">
		<table width="500" class="outline">
			<tr>
				<th>회원명</th>
				<td><input type="text" name="name" class="box">
			</tr>
			<tr>
				<th>주민번호</th>
				<td><input type="text" name="ssn1" class="box" maxlength="6"> -
				<input type="password" name="ssn2" class="box" maxlength="7"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="조회">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
		
	</form>
</div>
<%@ include file="bottom.jsp"%>