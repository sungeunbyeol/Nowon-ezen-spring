<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="shop.admin.dto.*"%>
<!-- prod_view.jsp -->
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="prod_list.do" method="post">
		<table border="0" width="750" class="outline">
			<caption>��ǰ�󼼺���</caption>
			<tr>
				<th width="15%" class="m2">ī�װ�</th>
				<td width="35%" align="center">${getProduct.pcategory_fk}</td>
				<th width="15%" class="m2">��ǰ��ȣ</th>
				<td width="35%" align="center">${getProduct.pnum}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ��</th>
				<td width="35%" align="center">${getProduct.pname}</td>
				<th width="15%" class="m2">����ȸ��</th>
				<td width="35%" align="center">${getProduct.pcompany}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ�̹���</th>
				<td colspan="3" align="center">
					<img src="${upPath}/${getProduct.pimage}" width="60" height="60">
				</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${getProduct.pqty}</td>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${getProduct.price}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ����</th>
				<td width="35%" align="center">${getProduct.pspec}</td>
				<th width="15%" class="m2">��ǰ����Ʈ</th>
				<td width="35%" align="center">${getProduct.point}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">��ǰ�Ұ�</th>
				<td colspan="3">
					<textarea name="pcontents" rows="5" cols="50" readOnly>${getProduct.pcontents}</textarea>
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












