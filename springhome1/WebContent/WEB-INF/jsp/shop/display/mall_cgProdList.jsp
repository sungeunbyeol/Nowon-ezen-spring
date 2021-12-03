<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_cgProdList.jsp -->
<%@ include file="mall_top.jsp" %>
<div align="center">
	<h2>Welcome to My Mall</h2>
	<c:if test="${empty code}">
		<b>${param.cname} 상품이 없습니다.</b>
	</c:if>	
	<c:if test="${not empty code}">
		<c:set var="count" value="0" />		
		<hr color="green" width="80%">
		<font size="3" color="red">${param.cname}</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
				<c:forEach var="pdto" items="${code}">
				<td align="center">
					<a href="mall_prodView.do?pnum=${pdto.pnum}&select=${param.code}">
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