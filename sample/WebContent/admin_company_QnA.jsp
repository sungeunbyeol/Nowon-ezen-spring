<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� QnA������</title>
</head>
<body>
<%@ include file="company_AdminTop.jsp" %>
<section>

<article>
	<h3 align="center">��� Q&A�Խ���</h3>
	<form align="center">
		<select name="list" size="1">
			<option>ȸ���(c_name)</option>
			<option>�̸���(c_email)</option>
			<option>������(cqa_subject)</option>
		</select>
		<input type="text">
		<button>�˻�</button>
	</form>
	<p>
</article>

<article>
	<div align="center" border="1"><h4 style="margin-top:40px;">���ȸ�� Q&A ���</h4></div>
	<div align="center"><mark>������ʹ� ���ȸ���� ���� �� Q&A ��� �ҷ�����</mark></div>
	<table align="center" border="1" style="margin-bottom:50px;">
		<tr>
			<th>QnA������ȣ(cqa_num)</th>
			<th>���������ȣ(c_num)</th>
			<th>ȸ���(c_name)</th>
			<th>�̸���(c_email)</th>
			<th>������(cqa_subject)</th>
			<th>�ۼ���¥(cqa_joindate)</th>
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