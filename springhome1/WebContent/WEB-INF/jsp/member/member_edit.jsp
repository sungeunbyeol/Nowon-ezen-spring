<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="member.dto.*"%>
<!-- member_edit.jsp -->
<%@ include file="top.jsp"%>
	<script type="text/javascript">
		function check(){
			if (f.passwd.value==""){
				alert("��й�ȣ�� �Է��� �ּ���!!")
				f.passwd.focus()
				return
			}
			document.f.submit()
		}
	</script>
		<form name="f" method="POST" action="editMember.do">
			<input type="hidden" name="no" value="${getMember.no}"/>
			<table width="600" align="center" class="outline">
  				<tr>
					<td colspan="2" align=center class="m2">ȸ������</td>
 				</tr>
				<tr>
					<td width="150" class="m3">�̸�</td>
					<td class="m3">
						<input type="text" name="name" class="box" value="${getMember.name}" readOnly>
					</td>
				</tr>
				<tr>
					<td width="150" class="m3">���̵�</td>
					<td class="m3">
						<input type="text" name="id" class="box" value="${getMember.id}" readOnly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">��й�ȣ</td>
					<td class="m3">
						<input type="password" name="passwd" class="box" value="${getMember.passwd}">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">�ֹι�ȣ</td>
					<td class="m3">
						<input type="text" name="ssn1" class="box" value="${getMember.ssn1}" readOnly> -
				<input type="password" name="ssn2" class="box" value="${getMember.ssn2}" readOnly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">�̸���</td>
					<td class="m3">
						<input type="text" name="email" class="box" value="${getMember.email}">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">����ó</td>
					<td class="m3">
						<input type="text" name="hp1" class="box" size="3" maxlength="3" value="${getMember.hp1}"> -
						<input type="text" name="hp2" class="box" size="4" maxlength="4" value="${getMember.hp2}"> -
						<input type="text" name="hp3" class="box" size="4" maxlength="4" value="${getMember.hp3}">
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<a href="javascript:check()">[����]</a>
						<a href="#">[���]</a>
					</td>
  				</tr>
  			</table>
		</form>
<%@ include file="bottom.jsp"%>