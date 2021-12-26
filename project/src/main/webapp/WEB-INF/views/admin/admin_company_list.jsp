<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업리스트</title>
</head>
<body>
<%@ include file="admin_company_top.jsp" %>

<section>

<article>
	<h3 align="center">기업리스트</h3>
	<form name="f" action="" method="" align="center">
		<button>전체보기</button>
		<select name="list" size="1">
			<option>회사명(c_name)</option>
			<option>이메일(c_email)</option>
			<option>대표자이름(c_ceo)</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색" >
	</form>
</article>

<article>
<table border="1" align="center" style="margin-top:30px;">
	<tr>
		<th>No(c_num)</th>
		<th>회사명(c_name)</th>
		<th>이메일(c_email)</th>
		<th>대표자이름(c_ceo)</th>
		<th>회사전화번호(c_tel)</th>
		<th>가입일(c_joindate)</th>
		<th>Level(level)</th>
		<th>삭제·블랙리스트 추가</th>
	</tr>
		<!-- 검색된 호텔에 상세보기를 누르면 DB연동된 숙소호텔 정보 나옴-->
		<!-- 우선 button으로 만듦. 실제로 만들때는 list나 다른 속성으로 바꾸기 -->
	<tr>
		<td>(c_num)</td>
		<td>(c_name)</td>
		<td>(c_email)</td>
		<td>(c_ceo)</td>
		<td>(c_tel)</td>
		<td>(c_joindate)</td>
		<td>(level)</td>
		<td>삭제 | 블랙리스트</td>
	</tr>
</table>
</article>
</section>

<%@ include file="../bottom.jsp" %>
</body>
</html>