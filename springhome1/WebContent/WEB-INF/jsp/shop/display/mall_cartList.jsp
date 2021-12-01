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

		<c:if test="${ht == null || ht.size() == 0}">
		<tr>
			<td colspan="6">장바구니가 비었습니다.</td>
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
					<input type="text" size="3" name="pqty" value="${cate.pqty}">개<br>
					<input type="hidden" name="pnum" value="${cate.pnum}"/>
					<input type="submit" value="수정">
				</form>
			</td>
			<td algin="right">
				<b>${df.price}원</b><br>
				<b>[${cate.point}]point</b>
			</td>
			<td align="right">
				<b>${df.cate.price*df.cate.pqyt}원</b><br>
				<b>[${cate.point*cate.pqyt}]point</b>
			</td>
			<c:set var="cateTotalPrice" value="${cateTotalPrice+=cate.price*cate.pqty}"/>
			<c:set var="cateTotalPoint" value="${cateTotalPoint+=cate.point*cate.pqty}"/>
			<td align="center">
				<a href="cartDel.mall?pnum=${cate.pnum}">삭제</a>
			</td>			
		</tr>		
<%		}//end while%>
		<tr class="m1">
			<td colspan="4">
				<b>장바구니 총액 : </b>
				<font color="red">${df.cartTotalPrice}원</font><br>
				<b>총 적립 포인트 : </b>
				<font color="green">[${cateTotalPoint}]point</font>
			</td>
			<td colspan="2">
				<a href="order.mall">[주문하기]</a> | 
				<a href="main.mall">[계속쇼핑]</a>
			</td>
		</tr>	
	</c:if>			
	</table>
</div>
<%@ include file="mall_bottom.jsp"%>				



















