<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_prodView.jsp -->
<%@ include file="mall_top.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:if test="${empty getProduct}">
		<script type="text/javascript">
			alert("��ǰ�� �����ϴ�. �ٽ� ������ �ּ���!!")
			location.href="shop.do"
		</script>
	</c:if>
<script type="text/javascript">
	function goCart(){
		document.f2.action = "cartAdd.do"
		document.f2.submit()
	}
	function goOrder(){
		document.f2.action = "order.do"
		document.f2.submit()
	}
</script>		
<div align="center">
	<table border="0" class="outline" width="100%">
		<tr class="m1">
			<td colspan="2" align="center">
				<b>[${getProduct.pname}] ��ǰ ����</b>
			</td>
		</tr>
		<tr>
			<td align="center" class="m3">
				<img src="${upPath}/${getProduct.pimage}" width="200" height="200">
			</td>
			<td class="m3">
				<form name="f2" method="post">
					��ǰ��ȣ : ${getProduct.pnum}<br>
					��ǰ�̸� : ${getProduct.pname}<br>
					��ǰ���� : <font color="red"><fmt:formatNumber value="${getProdcut.price}" pattern="000,000" /></font>��<br>
					��ǰ����Ʈ : <font color="red">${getProduct.point}</font>point<br>
					��ǰ���� : <input type="text" name="qty" size="3" value="1">��<br><br>
					<input type="hidden" name="pnum" value="${param.pnum}"/>
					<input type="hidden" name="select" value="${param.select}"/>
					<a href="javascript:goCart()">��ٱ���</a> |
					<a href="javascript:goOrder()">��ñ���</a> 
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<b>��ǰ �� ����</b><br>
				${getProduct.pcontents}
			</td>
		</tr>
	</table>
</div>		
<%@ include file="mall_bottom.jsp"%>			
		
		
		
		
		
		
		
		
		
		
		
		