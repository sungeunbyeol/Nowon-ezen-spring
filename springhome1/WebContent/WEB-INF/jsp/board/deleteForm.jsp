<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- deleteForm.jsp -->
<html>
<head>
	<title>�ۻ���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
	<b>�ۻ���</b>
	<br><br>
	<form name="f" action="deletePro_board.do" method="post">
		<input type="hidden" name="num" value="${param.num}"/>
		<table border="1" width="400">
			<tr bgcolor="yellow">
				<th>��й�ȣ�� �Է��� �ּ���</th>			
			</tr>
			<tr>
				<td align="center">��й�ȣ : <input type="password" name="passwd"></td>
			</tr>
			<tr bgcolor="yellow">
				<td>
					<input type="submit" value="�ۻ���">
					<input type="button" value="�۸��" onclick="window.location='list_board.do'">
				</td>	
			</tr>
		</table>
	</form>
</div>
</body>
</html>