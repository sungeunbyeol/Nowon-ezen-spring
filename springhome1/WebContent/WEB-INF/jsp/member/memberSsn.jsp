<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- memberSsn.jsp -->
<%@ include file="top.jsp" %>
<div align="center">
	<hr color="green" width="300">
	<h2>ȸ �� �� �� �� ��</h2>
	<hr color="green" width="300">
	<form name="f" action="checkMember.do" method="post">
		<table width="500" class="outline">
			<tr>
				<th>ȸ����</th>
				<td><input type="text" name="name" class="box">
			</tr>
			<tr>
				<th>�ֹι�ȣ</th>
				<td><input type="text" name="ssn1" class="box" maxlength="6"> -
				<input type="password" name="ssn2" class="box" maxlength="7"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="��ȸ">
					<input type="reset" value="���">
				</td>
			</tr>
		</table>
		
	</form>
</div>
<%@ include file="bottom.jsp"%>