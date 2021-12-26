<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원리스트</title>
</head>
<body>
<%@ include file="member_AdminTop.jsp" %>

<section>

<article>
	<h3 align="center">회원리스트</h3>
	<form name="f" align="center" action="" method="">
		<button>전체보기</button>
		<select name="list" size="1">
			<option>이메일(u_email)</option>
			<option>이름(u_name)</option>
			<option>전화번호(u_tel)</option>
		</select>
		<input type="text">
		<input type="submit" value="검색">
	</form>
	<p>
</article>

<article style="margin-top:50px;">
	<table align="center" border="1">
		<tr>
			<th>No(uqa_num)</th>
			<th>이름(u_name)</th>
			<th>이메일(u_email)</th>
			<th>닉네임(u_nickname)</th>
			<th>번호(u_tel)</th>
			<th>생년월일(u_birth)</th>
			<th>Level(level)</th>
			<th>삭제·블랙리스트 추가</th>
		</tr>
		<tr>
			<td>(uqa_num)</td>
			<td>(u_name)</td>
			<td>(u_email)</td>
			<td>(u_nickname)</td>
			<td>(u_tel)</td>
			<td>(u_birth)</td>
			<td>(level)</td>
			<td>삭제 | 블랙리스트</td>
		</tr>
	</table>
</article>

</section>

<%@ include file="bottom.jsp" %>
</body>
</html>