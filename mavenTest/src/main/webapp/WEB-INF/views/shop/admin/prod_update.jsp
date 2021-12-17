<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_update.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="top.jsp"%>
<div align="center">
<form name="f" action="prod_update_ok.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600" class="outline">
		<caption>��ǰ����</caption>
		<tr>
			<th class="m2">ī�װ�</th>
			<td align="left">  
				<input type="text" name="pcategory_fk" value="${getProduct.pcategory_fk}" disabled>
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ��ȣ</th>
			<td>
				${getProduct.pnum}
				<input type="hidden" name="pnum" value="${getProduct.pnum}"/>	
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ��</th>
			<td><input type="text" name="pname" value="${getProduct.pname}"></td>
		</tr>
		<tr>
			<th class="m2">����ȸ��</th>
			<td><input type="text" name="pcompany" value="${getProduct.pcompany}"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ�̹���</th>
			<td>
				<img src="${upPath}/${getProduct.pimage}" width="80" height="80">
				<input type="hidden" name="pimage2" value="${getProduct.pimage}"/>
				<input type="file" name="pimage">
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td><input type="text" name="pqty" value="${getProduct.pqty}"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td><input type="text" name="price" value="${getProduct.price}"></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����</th>
			<td>
				<select name="pspec">				
				<c:forTokens var="spec" items="hit,new,best,normal" delims=",">
					<c:if test="${getProduct.pspec == spec}">
						<option value="${spec}" selected>${fn:toUpperCase(spec)}</option>
					</c:if>	
					<c:if test="${getProduct.pspec != spec}">
						<option value="${spec}">${fn:toUpperCase(spec)}</option>
					</c:if>	
				</c:forTokens>					
				</select>
				
			</td>
		</tr>
		<tr>
			<th class="m2">��ǰ�Ұ�</th>
			<td><textarea name="pcontents" rows="5" cols="60">${getProduct.pcontents}</textarea></td>
		</tr>
		<tr>
			<th class="m2">��ǰ����Ʈ</th>
			<td><input type="text" name="point" value="${getProduct.point}"></td>
		</tr>
		<tr>
			<td colspan="2" class="m1">
				<input type="submit" value="��ǰ����">
				<input type="reset" value="�ٽ��Է�">
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="bottom.jsp"%>