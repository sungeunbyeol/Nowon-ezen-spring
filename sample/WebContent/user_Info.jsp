<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!--  %@ include file="test_top2.jsp" %-->
<!--%@ include file="test_myPage.jsp" %-->

<strong>�� ���� ����</strong>
	<div align="center">
	
	<!--  <td>
		<table align="center" valign="top" border="0">
			<caption><b>����������</b></caption>-->
			<tr>
				<td>
					�̸���(u_email) <input type="text" name="email"><br>
					�г���(u_nickname) <input type="text" name="nickname"><br>
					�޴��� ��ȣ(u_tell) <input type="text" name="tel">
					<input type="submit" value="����">
					<p>
				</td>
			</tr>				

			<tr>
				<td>
					<a href="#">��й�ȣ ����</a><!-- ��й�ȣ �缳�� �������� �̵� -->
					<a href="#">�α׾ƿ�</a><!-- ���Ǹ����Ű�� ȸ������ �������� -->
					<a href="#">ȸ��Ż��</a><!-- true�� ��ȯ�Ǹ� user_personal���� ���� -->
					<!-- script-- >
						var result = confirm("������ Ż���Ͻðھ��?");
						if(result)
							{
								alert("Ż�� �Ϸ�Ǿ����ϴ�")
							}
					<!--/script-->
				</td>
			</tr>	
		</table>
	</td>
	</tr>
	</div>
</body>
</html>
<!-- bottom�߰� -->	
	