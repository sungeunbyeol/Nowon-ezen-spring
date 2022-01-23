<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- user_login.jsp -->
<%@ include file="../top.jsp"%>
<div align="center">
<script type="text/javascript">
//mode값으로 Email찾기인지 비밀번호 찾기인지 알려줌 
function searchUser(mode){
	location.href = "user_search?mode=" + mode
}
//입력 검사 
function checkLogin() {
	if(f_login.email.value=="") {
		alert("이메일을 입력해주세요.")
		f_login.u_email.focus()
		return
	}
	if(f_login.password.value=="") {
		alert("비밀번호를 입력해주세요.")
		f_login.u_password.focus()
		return
	}
	document.f_login.submit()
}
</script>
<form name="f_login" method="POST" action="userloginok">
<table border="0" width="280" align="center">
<tr>
	<td colspan="2" align="center"><h3>회원 로그인</h3></td>
</tr>
<tr>
	<!-- cookie로 아이디 기억하기 -->
	<td colspan="2">
		<c:if test = "${empty cookie.saveEmail.value}">
		<input type="text" name="email" placeholder="이메일을 입력해 주세요." tabindex="1" 
		style="width:350px;height:50px">
		</c:if>
		
		<c:if test = "${not empty cookie.saveEmail.value}">
		<input type="text" name="email" value= "${cookie.saveEmail.value}" placeholder="이메일을 입력해 주세요." tabindex="1" 
		style="width:350px;height:50px">
		</c:if>
	</td> 
</tr>
<tr>
	<td colspan="2">
		<input type="password" name="password" placeholder="비밀번호를 입력해 주세요." tabindex="2" 
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
		<!-- script에서 사용할 (mode)값 넘겨주기  -->
		<a href="javascript:searchUser('email')">이메일 찾기</a> | 
		<a href="javascript:searchUser('password')">비밀번호 찾기</a>
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
		<button type="button" name="userJoin" onclick="location.href='userjoin'" 
		style="width:80px;height:35px;background-color:grey;color:white;border-color:grey">회원 가입</button>
	</td>
</tr>
</table>
</form>
</div>
<%@ include file="../footer.jsp" %>