<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_cartList.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp" %>
<div align="center">
	<table border="1" width="100%">
		<tr class="m2">
			<td colspan="6" align="center">
				<h3>��ٱ��� ����</h3>
			</td>
		</tr>
		<tr class="m1">
			<th width="10%">��ȣ</th>
			<th width="30%">��ǰ��</th>
			<th width="15%">����</th>
			<th width="15%">�ܰ�</th>
			<th width="20%">�ݾ�</th>
			<th width="10%">����</th>
		</tr>

		<c:if test="${ht == null || ht.size() == 0}">
		<tr>
			<td colspan="6">��ٱ��ϰ� ������ϴ�.</td>
		</tr>
		</c:if>
		<c:if test="${ht != null || ht.size() != 0}">
		<fmt:formatNumber value="${df}" type="currency" pattern="###,###"/>
		<c:set var="cartTotalPrice" value="0"/>
		<c:set var="cartTotalPoint" value="0"/>
<%
			Enumeration<Integer> enu = ht.keys();
			while(enu.hasMoreElements()){
				ProductDTO dto = ht.get(enu.nextElement());%>
		<tr>
			<td align="center">${cart.pnum}</td>
			<td align="center">
				<img src="${upPath}${upPath}/${cate.pimage}" width="40" height="40"><br>
				<b>${cate.pname}</b>
			</td>
			<td align="center">
				<form name="f2" action="cartEdit.mall" method="post">
					<input type="text" size="3" name="pqty" value="${cate.pqty}">��<br>
					<input type="hidden" name="pnum" value="${cate.pnum}"/>
					<input type="submit" value="����">
				</form>
			</td>
			<td algin="right">
				<b>${df.price}��</b><br>
				<b>[${cate.point}]point</b>
			</td>
			<td align="right">
				<b>${df.cate.price*df.cate.pqyt}��</b><br>
				<b>[${cate.point*cate.pqyt}]point</b>
			</td>
			<c:set var="cateTotalPrice" value="${cateTotalPrice+=cate.price*cate.pqty}"/>
			<c:set var="cateTotalPoint" value="${cateTotalPoint+=cate.point*cate.pqty}"/>
			<td align="center">
				<a href="cartDel.mall?pnum=${cate.pnum}">����</a>
			</td>			
		</tr>		
<%		}//end while%>
		<tr class="m1">
			<td colspan="4">
				<b>��ٱ��� �Ѿ� : </b>
				<font color="red">${df.cartTotalPrice}��</font><br>
				<b>�� ���� ����Ʈ : </b>
				<font color="green">[${cateTotalPoint}]point</font>
			</td>
			<td colspan="2">
				<a href="order.mall">[�ֹ��ϱ�]</a> | 
				<a href="main.mall">[��Ӽ���]</a>
			</td>
		</tr>	
	</c:if>			
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>				



















