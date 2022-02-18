<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_cartList.jsp -->
<%@ include file="mall_top.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center">
	<table border="1" width="100%">
		<tr class="m2">
			<td colspan="6" align="center">
				<h3>장바구니 보기</h3>
			</td>
		</tr>
		<tr class="m1">
			<th width="10%">번호</th>
			<th width="30%">상품명</th>
			<th width="15%">수량</th>
			<th width="15%">단가</th>
			<th width="20%">금액</th>
			<th width="10%">삭제</th>
		</tr>
		<c:if test="${empty cartList}">
		<tr>
			<td colspan="6">장바구니가 비었습니다.</td>
		</tr>
		</c:if>
		<c:set var="cartTotalPrice" value="0"/>
		<c:set var="cartTotalPoint" value="0"/>
		
		<c:forEach var="dto" items="${cartList}">
		<tr>
			<td align="center">${dto.value.pnum}</td>
			<td align="center">
				<img src="${upPath}/${dto.value.pimage}" width="40" height="40"><br>
				<b>${dto.value.pname}</b>
			</td>
			<td align="center">
				<form name="f2" action="cartEdit.do" method="post">
					<input type="text" size="3" name="pqty" value="${dto.value.pqty}">개<br>
					<input type="hidden" name="pnum" value="${dto.value.pnum}"/>
					<input type="submit" value="수정">
				</form>
			</td>
			<td algin="right">
				<b><fmt:formatNumber value="${dto.value.price}" pattern="000,000" />원</b><br>
				<b>[${dto.value.point}]point</b>
			</td>
			<td align="right">
				<b><fmt:formatNumber value="${dto.value.price*dto.value.pqty}" pattern="000,000" />원</b><br>
				<b>[${dto.value.point*dto.value.pqty}]point</b>
			</td>
			<c:set var="cartTotalPrice" value="${cartTotalPrice + dto.value.price * dto.value.pqty}"/>
			<c:set var="cartTotalPoint" value="${cartTotalPoint + dto.value.point * dto.value.pqty}"/>
			<td align="center">
				<a href="cartDel.do?pnum=${dto.value.pnum}">삭제</a>
			</td>			
		</tr>		
	</c:forEach>
		<tr class="m1">
			<td colspan="4">
				<b>장바구니 총액 : </b>
				<font color="red"><fmt:formatNumber value="${cartTotalPrice}" pattern="000,000" />원</font><br>
				<b>총 적립 포인트 : </b>
				<font color="green">[<fmt:formatNumber value="${cartTotalPoint}" pattern="000,000" />]point</font>
			</td>
			<td colspan="2">
				<a href="order.do">[주문하기]</a> | 
				<a href="shop.do">[계속쇼핑]</a>
			</td>
		</tr>	
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>				



















