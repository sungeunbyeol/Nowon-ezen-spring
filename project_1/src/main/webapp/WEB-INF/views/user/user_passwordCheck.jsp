<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="user_myPage.jsp" %>

<form name = "f_userpassword_edit" method = "Post" action = "user_password_edit">
<input type = "hidden" name = "u_email" value = "${loginOkBean.u_email}">
	<table align = "center" align = "top" border="0">
		<caption style="line-height: 60px; font-size:25px;"><b>비밀번호 확인</b></caption>
		<tr>
			<td colspan="2">
			현재 비밀번호를 입력해 주세요
			</td>
		</tr>
		<tr>
			<td style="width: 120px;">비밀번호</td>
			<td>
				<input type="password" name="u_password" placeholder="비밀번호를 입력해주세요." required oninvalid="this.setCustomValidity('비밀번호를 입력해주세요.')" oninput = "setCustomValidity('')">
				<input type="hidden" name="pre_password" value="${loginOkBean.u_password}">
			</td>
		</tr>
		<tr> 
			<td colspan="2" align="center">
			<input type="button" value="확인" 
					style="height: 40px;
				    width: 200px;
				    border: 0;
				    border-radius: 3px;
				    background: #edf2ff;"
				    onclick = "check()" 
			    /> 
			</td> 
		</tr>
	</table>
</form> 
 
<script>
function check(){
	
	if (f_userpassword_edit.u_password.value!=f_userpassword_edit.pre_password.value){
			alert("비밀번호가 일치하지 않습니다.")
			f_userJoin_check.pre_password.focus()
			return
		}
	  
	document.f_userpassword_edit.submit()  
} 
</script>