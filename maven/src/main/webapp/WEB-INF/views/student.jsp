<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- student.jsp -->
<html>
<head>
	<title>�л��������α׷�</title>
</head>
<body>
	<div align="center">
		<hr color="green" width="300">
		<h2>�� �� �� �� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="insertStudent.do" method="post">
			<table border="1">
				<tr>
					<td>
						���̵� : <input type="text" name="id"><br>
						�л��� : <input type="text" name="name"><br>
						�б޸� : <input type="text" name="cname"><br>
						<input type="submit" value="�Է�">
						<input type="reset" value="�ٽ��Է�">
					</td>
				</tr>
			</table>
		</form>
		<hr color="green" width="300">
		<h2>�� �� �� �� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="deleteStudent.do" method="post">
			<table border="1">
				<tr>
					<td>
						���̵� : <input type="text" name="id">
						<input type="submit" value="����">
					</td>
				</tr>
			</table>
		</form>
		<hr color="green" width="300">
		<h2>�� �� ã �� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="findStudent.do" method="post">
			<table border="1">
				<tr>
					<td>
						�л��� : <input type="text" name="name">
						<input type="submit" value="ã��">
					</td>
				</tr>
			</table>
		</form>
		<hr color="green" width="300">
		<h2>�� �� �� �� �� �� ��</h2>
		<hr color="green" width="300">
		<form name="f" action="listStudent.do" method="post">
			<table border="1">
				<tr>
					<td>
						<input type="submit" value="�л������������ �̵�">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>