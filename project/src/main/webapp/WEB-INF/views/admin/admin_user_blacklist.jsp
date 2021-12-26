<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블랙리스트 페이지</title>
</head>
<body>
<%@ include file="admin_user_top.jsp" %>
<section>

<article>
	<h3 align="center">블랙리스트</h3>
	<form align="center">
		<button>전체보기</button>
		<select name="list" size="1">
			<option>이메일(u_email)</option>
			<option>이름(u_name)</option>
			<option>전화번호(u_tel)</option>
		</select>
		<input type="text">
		<button>검색</button>
	</form>
	<p>
</article>

<article align="center">
	<mark>dto에서 레벨0인 사람들 명단 불러오기</mark>
	<table align="center" border="1" width="500px">
		<tr>
			<th><input type="checkbox"></th>
			<th>이메일(u_email)</th>
			<th>이름(u_name)</th>
			<th>블랙리스트 사유(u_black)</th>
		</tr>
		<tr>
			<th><input type="checkbox"></th>
			<td>(u_email)</td>
			<td>(u_name)</td>
			<td><input type="text" name="black" value="u_black"></td>
		</tr>
	</table>
</article>

<article align="right">
	<button style="margin-right:50px;">삭제·블랙리스트 해지</button>
</article>

</section>
<%@ include file="../bottom.jsp" %>
</body>
</html>