<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����� QnA �ۼ��ϱ�</title>
</head>
<body>
<p align="center" style="margin-bottom:50px">
ȣ�ڴ���ġ �̿� �� �����Ͻ� ���� �����ֽø� �ִ��� ���� ���ϳ��� �亯 �帮�ڽ��ϴ�.</p>
<form name="f" action="" method="" align="center">
	ī�װ� ����<br>
	<input type="text" name="category" list="category" style="width:400px;">
	<datalist id="category">
		<option value="ȣ��"></option>
		<option value="����Ʈ"></option>
	</datalist> <br><br>
	���� ����<br>
	<input type="text" name="question" list="question" style="width:400px;">
	<datalist id="question">
		<option value="�������"></option>
		<option value="���ȯ��"></option>
		<option value="�̿빮��"></option>
		<option value="ȸ������"></option>
		<option value="����"></option>
		<option value="ȯ�ҽ�û"></option>
		<option value="��Ÿ"></option>
	</datalist> <br> <br>
	�̸���<br>
	<input type="text" name="email" style="width:400px;"><br><br>
	���ǳ���<br>
	<textarea style="width:400px; height:150px;">
	�����Ͻ� ������ 10�� �̻� �Է����ּ���.
	�����Ͻô� ������ �̸��� ���������� �����ֽø� ���� ���� ����� �����մϴ�.
	���� ���� �ۼ� �� ���������� �Էµ��� �ʵ��� ���� ��Ź�帳�ϴ�.
	</textarea> <br><br>
	<!-- �����϶���, -->
	<input type="submit" value="�����Ϸ�">
	<!-- �ű��ۼ��϶���, -->
	<input type="submit" value="�ۼ��Ϸ�">
</form>
</body>
</html>