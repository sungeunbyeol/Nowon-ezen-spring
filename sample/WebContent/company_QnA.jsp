<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���ȸ���� �����ڿ��� QnA</title>
</head>
<body>
<h3>Q&A �Խ���</h3>
<div align="center"><mark>���ȸ�� �ڽ��� �� �۸� ���̱�</mark></div>
<table  align="center" width="500px">
	<tr>
		<th colspan="2" align="left">ȸ���(c_name)</th>
		<th align="right"><input type="submit" value="�����ϱ�" ></th>
	</tr>
	<tr>
		<td>����(cqa_subject)</td>
		<td>	����(cqa_contents)</td>
		<td>�ۼ���(cqa_joindate)</td>		
	</tr>
	<tr>
		<td colspan="3">
			<!-- ���� ������ ���ٸ� -->
			<textarea style="width:500px;">�����Ͻ� ������ �����ϴ�. (cqa_contents)</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right"><input type="reset" value="����" ></td>
	</tr>
</table>

</body>
</html>