<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ϲ�ȸ���� �����ڿ��� QnA</title>
</head>
<body>
<h3>Q&A �Խ���</h3>
<div align="center"><mark>�Ϲ�ȸ�� �ڽ��� �� �۸� ���̱�</mark></div>
<table  align="center" width="400">
	<tr>
		<th align="left" colspan="2">�г���(u_nickname)</th>
		<th align="right"><input type="submit" value="�����ϱ�" ></th>
	</tr>
	<tr>
		<td>����(uqa_subject)</td>
		<td>	����(uqa_contents)</td>
		<td>�ۼ���(uqa_joindate)</td>		
	</tr>
	<tr>
		<td colspan="3">
			<!-- ���� ������ ���ٸ� -->
			<textarea style="width:500px;">�����Ͻ� ������ �����ϴ�. (uqa_contents)</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right"><input type="reset" value="����" ></td>
	</tr>
</table>

</body>
</html>