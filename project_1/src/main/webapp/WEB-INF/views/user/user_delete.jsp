<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="user_myPage.jsp" %>
<!-- mypage에서 회원탈퇴 페이지 -->
<form name="f_userDelete" method="Post" action="user_delete_user">
<!-- 회원탈퇴를 위해서 u_num을 넘겨줌 -->
<input type="hidden" name="u_num" value="${loginOkBean.u_num}">
	<table align="center" align="top">
		<caption style="line-height:60px;font-size:25px;"><b>회원탈퇴</b></caption>
		<tr>
			<td colspan="2">
			회원 탈퇴를 위해 비밀번호를 입력해 주세요.
			</td>
		</tr> 
		<tr>
			<td style="width: 120px;">비밀번호</td>
			<td><input type="text" name="u_password" placeholder="비밀번호를 입력해주세요." required></td>
		</tr>
		<tr>
			<td style="width: 120px;">비밀번호 확인</td>
			<td><input type="text" name="u_password2" placeholder="비밀번호를 입력해주세요." required></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button type="button" name="userdelete" onclick="check()"  
			style="width:150px;height:50px;background-color:black;color:white;border-color:black">회원 탈퇴</button>
			</td> 
		</tr>
	</table>
</form> 

<script>
//비밀번호 일치 확인 script
function check(){
	console.log('회원탈퇴') 
	if (f_userDelete.u_password.value!=f_userDelete.u_password2.value){
			alert("비밀번호가 일치하지 않습니다.")
			f_userJoin_check.u_password2.focus()
			return
		}
	  
	document.f_userDelete.submit()  
} 
</script>