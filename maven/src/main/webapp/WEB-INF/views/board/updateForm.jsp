<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- updateForm.jsp -->
<html>
<head>
	<title>�ۼ���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function check(){
			if (f.writer.value==""){
				alert("�̸��� �Է��� �ּ���!!")
				f.wirter.focus()
				return false
			}
			if (f.subject.value==""){
				alert("������ �Է��� �ּ���!!")
				f.subject.focus()
				return false
			}
			if (f.content.value==""){
				alert("�۳����� �Է��� �ּ���!!")
				f.content.focus()
				return false
			}
			if (f.passwd.value==""){
				alert("��й�ȣ�� �Է��� �ּ���!!")
				f.passwd.focus()
				return false
			}
			return true
		}
	</script>
</head>
<body>
<div align="center">
	<form name="f" action="update_board.do" method="post" onsubmit="return check()">
		<input type="hidden" name="num" value="${param.num}"/>
		<h3>�ۼ���</h3>
		<table border="1" width="500">
			<tr>
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><input type="text" name="writer" class="box" value="${getBoard.writer}"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><input type="text" name="subject" class="box" size="50" value="${getBoard.subject}"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">Email</td>
				<td><input type="text" name="email" class="box" size="50" value="${getBoard.email}"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><textarea rows="11" cols="50" name="content" class="box">${getBoard.content}</textarea></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">��й�ȣ</td>
				<td><input type="password" name="passwd" class="box"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" bgcolor="yellow">
					<input type="submit" value="�ۼ���">
					<input type="reset" value="�ٽ��ۼ�">
					<input type="button" value="��Ϻ���" onclick="window.location='list_board.do">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>