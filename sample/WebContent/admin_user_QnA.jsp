<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� QnA������</title>
</head>
<body>
<%@ include file="member_AdminTop.jsp" %>
<section>

<article>
<h3 align="center">Q&A�Խ���</h3>
<form align="center">
	<select name="list" size="1">
		<option>�ۼ���(u_name)</option>
		<option>�̸���(u_email)</option>
		<option>������(uqa_subject)</option>
	</select>
	<input type="text">
	<button>�˻�</button>
</form>
<p>
</article>

<article>
<div align="center" border="1"><h4 style="margin-top:40px;">�Ϲ�ȸ�� Q&A ���</h4></div>
<div align="center"><mark>������ʹ� �������� ���� �� Q&A ��� �ҷ�����</mark></div>
<table align="center" border="1" style="margin-bottom:50px;">
	<tr>
		<th>QnA������ȣ(uqa_num)</th>
		<th>ȸ��������ȣ(u_num)</th>
		<th>�ۼ���(u_name)</th>
		<th>�̸���(u_email)</th>
		<th>������(uqa_subject)</th>
		<th>�ۼ���¥(uqa_joindate)</th>
	</tr>
</table>
</article>

<article>
<h4 align="center">�Ʒ��� ��� �� ��</h4>
<div align="center" style="padding-right:170px;"><h4>������</h4></div>
<p align="center">���� : <textarea style="width:260px; height:95px;"></textarea></p>
<div align="center">
	<button>����</button>
	<button>����</button>
	<button>���</button>
</div>
</article>

</section>
</body>
</html>