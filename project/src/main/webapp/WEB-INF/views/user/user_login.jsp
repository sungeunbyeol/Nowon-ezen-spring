<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- user_login.jsp -->
<%@ include file="../user_top.jsp"%>
<div align="center">
<script type="text/javascript">
function checkLogin() {
	if(f_login.u_email.value=="") {
		alert("이메일을 입력해주세요.")
		f_login.u_email.focus()
		return
	}
	if(f_login.u_password.value=="") {
		alert("비밀번호를 입력해주세요.")
		f_login.u_password.focus()
		return
	}
	document.f_login.submit()
}
</script>
<form name="f_login" method="POST" action="user_login_ok">
<table border="0" width="280" align="center">
<tr>
	<td colspan="2" align="center"><h3>회원 로그인</h3></td>
</tr>
<tr>
	<td colspan="2">
		<input type="text" name="u_email" placeholder="이메일을 입력해 주세요." tabindex="1" 
		style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="password" name="u_password" placeholder="비밀번호를 입력해 주세요." tabindex="2" 
		style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td colspan="2">
		<button type="button" name="userLogin" onclick="checkLogin()" 
		style="width:350px;height:50px;background-color:black;color:white;border-color:black">로그인</button>
	</td>
</tr>
<tr>
	<td>
		<input type="checkbox" name="saveEmail">이메일 기억
	</td>
	<td align="right">
		<a href="user_search?mode=email">이메일 찾기</a> | 
		<a href="user_search?mode=password">비밀번호 찾기</a>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td colspan="2">
		<img src="resources/naverLogin.png" width="350" height="60">
		<br>
	</td>
</tr>
<tr>
	<td colspan="2">
		<img src="resources/kakaoLogin.png" width="350" height="60">
		<br>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<b>아직 회원이 아니신가요?</b>&nbsp;&nbsp;&nbsp;
		<button type="button" name="userJoin" onclick="location.href='user_join'" 
		style="width:80px;height:35px;background-color:grey;color:white;border-color:grey">회원 가입</button>
	</td>
</tr>
</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>