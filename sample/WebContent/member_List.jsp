<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������Ʈ</title>
</head>
<body>
<%@ include file="member_AdminTop.jsp" %>

<section>

<article>
	<h3 align="center">ȸ������Ʈ</h3>
	<form name="f" align="center" action="" method="">
		<button>��ü����</button>
		<select name="list" size="1">
			<option>�̸���(u_email)</option>
			<option>�̸�(u_name)</option>
			<option>��ȭ��ȣ(u_tel)</option>
		</select>
		<input type="text">
		<input type="submit" value="�˻�">
	</form>
	<p>
</article>

<article style="margin-top:50px;">
	<table align="center" border="1">
		<tr>
			<th>No(uqa_num)</th>
			<th>�̸�(u_name)</th>
			<th>�̸���(u_email)</th>
			<th>�г���(u_nickname)</th>
			<th>��ȣ(u_tel)</th>
			<th>�������(u_birth)</th>
			<th>Level(level)</th>
			<th>������������Ʈ �߰�</th>
		</tr>
		<tr>
			<td>(uqa_num)</td>
			<td>(u_name)</td>
			<td>(u_email)</td>
			<td>(u_nickname)</td>
			<td>(u_tel)</td>
			<td>(u_birth)</td>
			<td>(level)</td>
			<td>���� | ������Ʈ</td>
		</tr>
	</table>
</article>

</section>

<%@ include file="bottom.jsp" %>
</body>
</html>