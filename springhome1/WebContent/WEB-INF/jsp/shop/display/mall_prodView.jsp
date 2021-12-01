<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_prodView.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp" %>
<fmt:formatNumber value="${df}" type="currency" pattern="###,###"/>
		<c:if test="${pdto == null }">
		<script type="text/javascript">
			alert("��ǰ�� �����ϴ�. �ٽ� ������ �ּ���!!")
			location.href="mall.jsp"
		</script>
		</c:if>
<script type="text/javascript">
	function goCart(){
		document.f2.action = "cartAdd.mall"
		document.f2.submit()
	}
	function goOrder(){
		document.f2.action = "order.mall"
		document.f2.submit()
	}
</script>		
<div align="center">
	<table border="0" class="outline" width="100%">
		<tr class="m1">
			<td colspan="2" align="center">
				<b>[${pdto.pname}] ��ǰ ����</b>
			</td>
		</tr>
		<tr>
			<td align="center" class="m3">
				<img src="${upPath}/${pdto.pimage}" width="200" height="200">
			</td>
			<td class="m3">
				<form name="f2" method="post">
					��ǰ��ȣ : ${pdto.pnum}<br>
					��ǰ�̸� : ${pdto.pname}<br>
					��ǰ���� : <font color="red">${df.pdto.price}</font>��<br>
					��ǰ����Ʈ : <font color="red">${pdto.point}</font>point<br>
					��ǰ���� : <input type="text" name="qty" size="3" value="1">��<br><br>
					<input type="hidden" name="pnum" value="${pnum}"/>
					<input type="hidden" name="select" value="${select}"/>
					<a href="javascript:goCart()">��ٱ���</a> |
					<a href="javascript:goOrder()">��ñ���</a> 
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<b>��ǰ �� ����</b><br>
				${pdto.pcontents}
			</td>
		</tr>
	</table>
</div>		
<%@ include file="mall_bottom.jsp"%>			
		
		
		
		
		
		
		
		
		
		
		
		