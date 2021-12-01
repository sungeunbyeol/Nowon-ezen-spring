<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, shop.admin.dto.*, java.text.*"%>
<!-- mall_order.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp"%>
<div align="center">
	<table width="100%" align="center" class="outline">
		<tr class="m2">
			<td colspan="3" align="center">
				<font size="3">���� ������ ����</font><br>
			</td>
		</tr>
		<tr class="m1">
			<th width="50%" align="center">��ǰ��</th>
			<th width="15%" align="center">����</th>
			<th width="35%" align="center">�ݾ�</th>
		</tr>
		<fmt:formatNumber value="${df}" type="currency" pattern="###,###"/>
		<c:set var="cartTotalPrice" value="0"/>
		<c:if test="${ enu.hasMoreElements}">
		<tr>
			<td align="center">${order.pname}</td>
			<td align="center">${order.pqyt}</td>
			<td align="center">${df.order.pqyt*df.order.price}��</td>
		</tr>
		</c:if>	
		<c:set var="cartTotalPrice" value="${cartTotalPrice +=order.pqyt*order.price}"/>
		<tr class="m1">
			<td colspan="3">
				<b>�����Ͻ� �Ѿ� : </b>
				<font color="red">[${df.cartTotalPrice}]��</font>
			</td>
		</tr>	
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>


















