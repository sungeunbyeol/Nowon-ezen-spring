<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�������Ʈ</title>
</head>
<body>
<%@ include file="company_AdminTop.jsp" %>

<section>

<article>
	<h3 align="center">�������Ʈ</h3>
	<form name="f" action="" method="" align="center">
		<button>��ü����</button>
		<select name="list" size="1">
			<option>ȸ���(c_name)</option>
			<option>�̸���(c_email)</option>
			<option>��ǥ���̸�(c_ceo)</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="�˻�" >
	</form>
</article>

<article>
<table border="1" align="center" style="margin-top:30px;">
	<tr>
		<th>No(c_num)</th>
		<th>ȸ���(c_name)</th>
		<th>�̸���(c_email)</th>
		<th>��ǥ���̸�(c_ceo)</th>
		<th>ȸ����ȭ��ȣ(c_tel)</th>
		<th>������(c_joindate)</th>
		<th>Level(level)</th>
		<th>������������Ʈ �߰�</th>
	</tr>
		<!-- �˻��� ȣ�ڿ� �󼼺��⸦ ������ DB������ ����ȣ�� ���� ����-->
		<!-- �켱 button���� ����. ������ ���鶧�� list�� �ٸ� �Ӽ����� �ٲٱ� -->
	<tr>
		<td>(c_num)</td>
		<td>(c_name)</td>
		<td>(c_email)</td>
		<td>(c_ceo)</td>
		<td>(c_tel)</td>
		<td>(c_joindate)</td>
		<td>(level)</td>
		<td>���� | ������Ʈ</td>
	</tr>
</table>
</article>
</section>


<%@ include file="bottom.jsp" %>
</body>
</html>