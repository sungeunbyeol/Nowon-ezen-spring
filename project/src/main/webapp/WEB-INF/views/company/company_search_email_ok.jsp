<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- company_search_email_ok.jsp -->
<%@ include file ="company_top.jsp" %>
<div align="center">
<form name="f_search_email_ok" method="POST">
<table border="0" width="280" align="center">
<tr>
	<td colspan="2" align="center"><h3>이메일 찾기 결과</h3></td>
</tr>
<tr>
	<td colspan="2">회원님의 로그인 이메일 주소는 아래와 같습니다.</td>
</tr>
<tr>
	<td colspan="2">
		[CompanyDTO.email]
		<input type="hidden" name="c_email" value="">
		<!-- 하단 버튼 누르면 DTO의 email을 해당 페이지로 넘김 -->
	</td>
</tr>
<tr>
	<td>
		<button type="submit" formaction="company_update_password" 
		style="width:175px;height:50px;background-color:black;color:white;border-color:black">비밀번호 찾기</button>
	</td>
	<td>
		<button type="submit" formaction="company_login" 
		style="width:175px;height:50px;background-color:black;color:white;border-color:black">로그인</button>
	</td>
</tr>
</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>