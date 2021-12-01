<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<!-- prod_input.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<div align = "center">

<form name="f" action="prod_input_ok.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600" class="outline">
		<caption>��ǰ���ī�װ�</caption>
		<tr>
			<th class="m2">ī�װ�</th>
			<td align="left">
				<select name="pcategory_fk">
					<c:forEach var="dto" items="${cateList}">
					<option value="${dto.code}">
						${dto.cname}[${dto.code}]
					</option>
					</c:forEach>					
				</select>
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ��</th>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ�ڵ�</th>
			<td><input type="text" name="pcode"></td>
		</tr>
		<tr>
			<th class="m2">����ȸ��</th>
			<td><input type="text" name="pcompany"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ�̹���</th>
			<td><input type="file" name="pimage"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td><input type="text" name="pqty"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td>
				<select name="pspec">
					<option value="normal" selected>::NORMAL::</option>
					<option value="hit">HIT</option>
					<option value="best">BEST</option>
					<option value="new">NEW</option>
				</select>
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ�Ұ�</th>
			<td><textarea name="pcontents" rows="5" cols="60"></textarea></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����Ʈ</th>
			<td><input type="text" name="point"></td>
		</tr>
		<tr>
			<td colspan="2" class="m1">
				<input type="submit" value="��ǰ���">
				<input type="reset" value="�ٽ��Է�">
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="bottom.jsp"%>
















