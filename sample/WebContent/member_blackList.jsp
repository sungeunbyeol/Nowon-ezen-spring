<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������Ʈ ������</title>
</head>
<body>
<%@ include file="member_AdminTop.jsp" %>
<section>

<article>
	<h3 align="center">������Ʈ</h3>
	<form align="center">
		<button>��ü����</button>
		<select name="list" size="1">
			<option>�̸���(u_email)</option>
			<option>�̸�(u_name)</option>
			<option>��ȭ��ȣ(u_tel)</option>
		</select>
		<input type="text">
		<button>�˻�</button>
	</form>
	<p>
</article>

<article align="center">
	<mark>dto���� ����0�� ����� ��� �ҷ�����</mark>
	<table align="center" border="1" width="500px">
		<tr>
			<th><input type="checkbox"></th>
			<th>�̸���(u_email)</th>
			<th>�̸�(u_name)</th>
			<th>������Ʈ ����(u_black)</th>
		</tr>
		<tr>
			<th><input type="checkbox"></th>
			<td>(u_email)</td>
			<td>(u_name)</td>
			<td><input type="text" name="black" value="u_black"></td>
		</tr>
	</table>
</article>

<article align="right">
	<button style="margin-right:50px;">������������Ʈ ����</button>
</article>

</section>
</body>
</html>