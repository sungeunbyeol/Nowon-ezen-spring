<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="user_myPage.jsp" %>

<form name = "f_userpassword_edit" method = "Post" action = "user_update_password_ok">
<input type = "hidden" name = "u_email" value = "${loginOkBean.u_email}">
	<table align = "center" align = "top" border="0">
		<tr>
			<caption style="line-height: 60px; font-size:25px;"><b>��й�ȣ ����</b></caption>
		</tr>
		<tr>
			<td colspan="2">
			���ο� ��й�ȣ�� �Է��� �ּ���.
			</td>
		</tr>
		<tr>
			<td style="width: 120px;">��й�ȣ</td>
			<td><input type="text" name="u_password" placeholder="��й�ȣ�� �Է����ּ���." required oninvalid="this.setCustomValidity('��й�ȣ�� �Է����ּ���.')" oninput = "setCustomValidity('')"></td>
		</tr>
		<tr>
			<td style="width: 120px;">��й�ȣ Ȯ��</td>
			<td><input type="text" name="u_password2" placeholder="��й�ȣ�� �Է����ּ���." required oninvalid="this.setCustomValidity('��й�ȣ�� �Է����ּ���.')" oninput = "setCustomValidity('')"></td>
		</tr>
		<tr> 
			<td colspan="2" align="center">
			<input type="button" value="��й�ȣ ����" 
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
	
	if (f_userpassword_edit.u_password.value!=f_userpassword_edit.u_password2.value){
			alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.")
			f_userJoin_check.u_password2.focus()
			return
		}
	  
	document.f_userpassword_edit.submit()  
} 
</script>