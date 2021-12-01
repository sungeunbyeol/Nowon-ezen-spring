<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_prodView.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp" %>
<fmt:formatNumber value="${df}" type="currency" pattern="###,###"/>
		<c:if test="${pdto == null }">
		<script type="text/javascript">
			alert("상품이 없습니다. 다시 선택해 주세요!!")
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
				<b>[${pdto.pname}] 상품 정보</b>
			</td>
		</tr>
		<tr>
			<td align="center" class="m3">
				<img src="${upPath}/${pdto.pimage}" width="200" height="200">
			</td>
			<td class="m3">
				<form name="f2" method="post">
					상품번호 : ${pdto.pnum}<br>
					상품이름 : ${pdto.pname}<br>
					상품가격 : <font color="red">${df.pdto.price}</font>원<br>
					상품포인트 : <font color="red">${pdto.point}</font>point<br>
					상품갯수 : <input type="text" name="qty" size="3" value="1">개<br><br>
					<input type="hidden" name="pnum" value="${pnum}"/>
					<input type="hidden" name="select" value="${select}"/>
					<a href="javascript:goCart()">장바구니</a> |
					<a href="javascript:goOrder()">즉시구매</a> 
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<b>상품 상세 설명</b><br>
				${pdto.pcontents}
			</td>
		</tr>
	</table>
</div>		
<%@ include file="mall_bottom.jsp"%>			
		
		
		
		
		
		
		
		
		
		
		
		