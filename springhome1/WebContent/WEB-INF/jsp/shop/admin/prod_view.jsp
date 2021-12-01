<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_view.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="prod_list.do" method="post">
		<table border="0" width="750" class="outline">
			<caption>��ǰ�󼼺���</caption>
			<tr>
				<th width="15%" class="m2">ī�װ�</th>
				<td width="35%" align="center">${pdto.pcategory_fk}</td>
				<th width="15%" class="m2">��ǰ��ȣ</th>
				<td width="35%" align="center">${pdto.pnum}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ��</th>
				<td width="35%" align="center">${pdto.pname}</td>
				<th width="15%" class="m2">����ȸ��</th>
				<td width="35%" align="center">${pdto.pcompany}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ�̹���</th>
				<td colspan="3" align="center">
					<img src="${upPath}/${pdto.pimage}" width="60" height="60">
				</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${pdto.pqty}</td>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${pdto.price}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${pdto.pspec}</td>
				<th width="15%" class="m2">��ǰ����Ʈ</th>
				<td width="35%" align="center">${pdto.point}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ�Ұ�</th>
				<td colspan="3">
					<textarea name="pcontents" rows="5" cols="50" readOnly>${pdto.pcontents}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="m1" align="center">
					<input type="submit" value="���ư���">
				</td>	
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>












