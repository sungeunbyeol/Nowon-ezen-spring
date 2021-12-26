<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- company_login.jsp -->
<%@ include file ="company_top.jsp" %>
<div align="center">
<form name="f_login" method="POST" action="company_login_ok">
<table border="0" width="280" align="center">
<tr>
	<td colspan="2" align="center"><h3>기업 로그인</h3></td>
</tr>
<tr>
	<td colspan="2">
		<input type="text" name="c_email" placeholder="이메일을 입력해 주세요." tabindex="1" 
		style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="password" name="c_password" placeholder="비밀번호를 입력해 주세요." tabindex="2" 
		style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td colspan="2">
		<button type="button" name="companyLogin" onclick="checkLogin()"
		style="width:350px;height:50px;background-color:black;color:white;border-color:black">로그인</button>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox" name="saveEmail">이메일 기억
	</td>
	<td align="right">
		<a href="company_search?mode=email">이메일 찾기</a> | 
		<a href="company_search?mode=password">비밀번호 찾기</a>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<b>아직 회원이 아니신가요?</b>&nbsp;&nbsp;&nbsp;
		<button type="button" name="companyJoin" onclick="location.href='company_register'" 
		style="width:80px;height:35px;background-color:grey;color:white;border-color:grey">회원 가입</button>
	</td>
</tr>
</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>