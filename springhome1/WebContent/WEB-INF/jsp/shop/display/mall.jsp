<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall.jsp -->
<%@ include file="mall_top.jsp" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="center">
	<h2>Welcome to My Mall</h2>
	<c:if test="${empty hit}">
		<b>HIT 상품이 없습니다.</b>
	</c:if>	
	<c:if test="${not empty hit}">
		<c:set var="count" value="0" />		
		<hr color="green" width="80%">
		<font size="3" color="red">HIT</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
				<c:forEach var="pdto" items="${hit}">
				<td align="center">
					<a href="mall_prodView.do?pnum=${pdto.pnum}&select=hit">
						<img src="${upPath}/${pdto.pimage}" width="80" height="60">
					</a>	<br>
					${pdto.pname}<br>
					<font color="red">
						<fmt:formatNumber value="${pdto.price}" pattern="000,000" />
					</font>원<br>
					<font color="blue">${pdto.point}</font>point
				</td>	
				<c:set var="count" value="${count+1}"/>
				<c:if test="${count==3}">
					<c:set var="count" value="0"/>
					</tr><tr>	
				</c:if>
				</c:forEach>			
			</tr>
		</table>
	</c:if>	
	<c:if test="${empty pnew}">
		<b>NEW 상품이 없습니다.</b>
	</c:if>	
	<c:if test="${not empty pnew}">
		<c:set var="count" value="0" />		
		<hr color="green" width="80%">
		<font size="3" color="red">NEW</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
				<c:forEach var="pdto" items="${pnew}">
				<td align="center">
					<a href="mall_prodView.do?pnum=${pdto.pnum}&select=new">
						<img src="${upPath}/${pdto.pimage}" width="80" height="60">
					</a>	<br>
					${pdto.pname}<br>
					<font color="red">
						<fmt:formatNumber value="${pdto.price}" pattern="000,000" />
					</font>원<br>
					<font color="blue">${pdto.point}</font>point
				</td>	
				<c:set var="count" value="${count+1}"/>
				<c:if test="${count==3}">
					<c:set var="count" value="0"/>
					</tr><tr>	
				</c:if>
				</c:forEach>			
			</tr>
		</table>
	</c:if>	
	<c:if test="${empty best}">
		<b>BEST 상품이 없습니다.</b>
	</c:if>	
	<c:if test="${not empty best}">
		<c:set var="count" value="0" />		
		<hr color="green" width="80%">
		<font size="3" color="red">BEST</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
				<c:forEach var="pdto" items="${best}">
				<td align="center">
					<a href="mall_prodView.do?pnum=${pdto.pnum}&select=best">
						<img src="${upPath}/${pdto.pimage}" width="80" height="60">
					</a>	<br>
					${pdto.pname}<br>
					<font color="red">
						<fmt:formatNumber value="${pdto.price}" pattern="000,000" />
					</font>원<br>
					<font color="blue">${pdto.point}</font>point
				</td>	
				<c:set var="count" value="${count+1}"/>
				<c:if test="${count==3}">
					<c:set var="count" value="0"/>
					</tr><tr>	
				</c:if>
				</c:forEach>			
			</tr>
		</table>
	</c:if>	
</div>	
<%@ include file="mall_bottom.jsp"%>				
				









