<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- fileUpload.jsp -->
<html>
<head>	
	<title>���Ͼ��ε�</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� �� �� �� �� Ʈ</h2>
		<hr color="green" width="300">
		<form name="f" action="fileUpload_ok2.do" method="post"  enctype="multipart/form-data">
		<!-- default : enctype="application/x-www-form-urlencoded" -->
			<table border="1" width="500">
				<tr>
					<th>�ø���</th>
					<td><input type="text" name="name"></td>
				</tr>		
				<tr>
					<th>���ϼ���</th>
					<td><input type="text" name="content"></td>
				</tr>
				<tr>
					<th>���ϸ�</th>
					<td><input type="file" name="filename"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="���ε�">
						<input type="reset" value="�ٽ��Է�">
					</td>
				</tr>
			</table>
		</form>	
	</div>
</body>
</html>