<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="user_myPage.jsp" %>

<form name = "f_userDelete" method = "Post" action = "user_delete_user">
<input type = "hidden" name = "u_num" value = "${loginOkBean.u_num }">
	<table align = "center" align = "top" border="0">
		<tr>
			<caption style="line-height: 60px; font-size:25px;"><b>ȸ��Ż��</b></caption>
		</tr>
		<tr>
			<td colspan="2">
			ȸ�� Ż�� ���� ��й�ȣ�� �Է��� �ּ���.
			</td>
		</tr> 
		<tr>
			<td style="width: 120px;">��й�ȣ</td>
			<td><input type="text" name="u_password" placeholder="��й�ȣ�� �Է����ּ���." required></td>
		</tr>
		<tr>
			<td style="width: 120px;">��й�ȣ Ȯ��</td>
			<td><input type="text" name="u_password2" placeholder="��й�ȣ�� �Է����ּ���." required></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button type="button" name="userdelete" onclick="check()"  
			style="width:150px;height:50px;background-color:black;color:white;border-color:black">ȸ�� Ż��</button>
			</td> 
		</tr>
	</table>
</form> 

<script>
function check(){
	console.log('ȸ��Ż��') 
	if (f_userDelete.u_password.value!=f_userDelete.u_password2.value){
			alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.")
			f_userJoin_check.u_password2.focus()
			return
		}
	  
	document.f_userDelete.submit()  
} 
</script>