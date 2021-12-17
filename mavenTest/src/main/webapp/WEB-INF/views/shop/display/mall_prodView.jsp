<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_prodView.jsp -->
<%@ include file="mall_top.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:if test="${empty getProduct}">
		<script type="text/javascript">
			alert("상품이 없습니다. 다시 선택해 주세요!!")
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
				<b>[${getProduct.pname}] 상품 정보</b>
			</td>
		</tr>
		<tr>
			<td align="center" class="m3">
				<img src="${upPath}/${getProduct.pimage}" width="200" height="200">
			</td>
			<td class="m3">
				<form name="f2" method="post">
					상품번호 : ${getProduct.pnum}<br>
					상품이름 : ${getProduct.pname}<br>
					상품가격 : <font color="red"><fmt:formatNumber value="${getProdcut.price}" pattern="000,000" /></font>원<br>
					상품포인트 : <font color="red">${getProduct.point}</font>point<br>
					상품갯수 : <input type="text" name="qty" size="3" value="1">개<br><br>
					<input type="hidden" name="pnum" value="${param.pnum}"/>
					<input type="hidden" name="select" value="${param.select}"/>
					<a href="javascript:goCart()">장바구니</a> |
					<a href="javascript:goOrder()">즉시구매</a> 
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<b>상품 상세 설명</b><br>
				${getProduct.pcontents}
			</td>
		</tr>
	</table>
</div>		
<%@ include file="mall_bottom.jsp"%>			
		
		
		
		
		
		
		
		
		
		
		
		