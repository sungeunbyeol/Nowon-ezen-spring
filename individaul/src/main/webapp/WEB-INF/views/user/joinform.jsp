<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
<%@ include file="../top.jsp" %>
<form name="userjoin" method="POST" action="userjoin" onsubmit="return send(this)">
	<table border="0" align="center">
	<tr>
		<td colspan="3" align="center"><h3>회원 가입</h3></td>
	</tr>
	<tr>
		<td colspan="3" align="center">약관 동의</td>
	</tr>
	<tr>
		<td colspan="3">
			<textarea cols="57" readonly>대충 약관내용</textarea><br>
			<input type="checkbox" name="agree">약관에 동의합니다.
		</td>
	</tr>
	<tr>
		<td width="130" align="right">이메일</td>
		<td width="200">
			<input type="email" name="u_email" placeholder="이메일을 입력해 주세요." tabindex="1" autofocus
			style="width:200px;height:40px">
		</td>
		<td width="100">
			<button type="button" name="emailDuplcheck" id="echeck"
			style="width:100px;height:40px;background-color:black;color:white;border-color:black">중복체크</button>
		</td>
	</tr>
	<tr>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<td width="130" align="right">비밀번호</td>
		<td width="200">
			<input type="password" name="u_password" id="password1" class="pw" 
			placeholder="비밀번호를 입력해 주세요." tabindex="1" style="width:200px;height:40px">
		</td>
	</tr>
	<tr>
		<td width="130" align="right">비밀번호 확인</td>
		<td width="200">
			<input type="password" name="u_password2" id="password2" class="pw" 
			placeholder="비밀번호를 다시 입력해 주세요." tabindex="1" style="width:200px;height:40px">
			<span id="alert-success" style="display: none; color:blue">비밀번호가 일치합니다.</span>
    		<span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">
    			비밀번호가 일치하지 않습니다.
    		</span>
		</td>
	</tr>
	<tr>
		<td width="130" align="right">이름</td>
		<td width="300" colspan="2">
			<input type="text" name="u_name" placeholder="이름을 입력해 주세요." tabindex="1" 
			style="width:200px;height:40px">
		</td>
	</tr>
	<tr>
		<td width="130" align="right">닉네임</td>
		<td width="200">
			<input type="text" name="u_nickname" placeholder="별명을 입력해 주세요." tabindex="1" 
			style="width:200px;height:40px">
		</td>
		<td width="100">
			<button type="button" name="nicknameDuplcheck" id="ncheck"
			style="width:100px;height:40px;background-color:black;color:white;border-color:black">중복체크</button>
		</td>
	</tr>
	<tr>
		<td width="130" align="right">생년월일</td>
		<td width="200">
			<input type="text" name="u_birth" placeholder="생년월일을 입력해 주세요." tabindex="1" 
			style="width:200px;height:40px">
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			<button type="submit" name="joinbutton" onclick="checkLogin()"
			style="width:350px;height:50px;background-color:black;color:white;border-color:black">회원가입</button>
			<!-- <input type="submit" value="회원가입" onclick="checkLogin()"> -->
		</td>
	</tr>
	</table>
</form>
</div>

<script>
    $('.pw').focusout(function () {
        var pwd1 = $("#password1").val();
        var pwd2 = $("#password2").val();
  
        if ( pwd1 != '' && pwd2 == '' ) {
            null;
        } else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                $("#alert-success").css('display', 'inline-block');
                $("#alert-danger").css('display', 'none');
            } else {
                alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요.");
                $("#alert-success").css('display', 'none');
                $("#alert-danger").css('display', 'inline-block');
            }
        }
    });

	function send(f){ 
		if(f.agree.checked==true){ 
				return true;
			} else { 
				alert("약관에 동의해주시기 바랍니다."); 
				return false; 
		}
	}
	
</script>

<%@ include file="../footer.jsp" %>