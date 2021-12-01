<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall_cgProdList.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp" %>
<div align="center">
	<h2>Welcome to My Mall</h2>
	<fmt:formatNumber value="${df} type="currency" pattern="###,###"/>
		<c:if test="${plist==null || plist.size()==0 }">
		<b>${cname}상품이 없습니다.</b>
		</c:if>
		<c:if test="${plist!=null || plist.size()!=0 }">
		<c:set var="count" value="0"/>
		<hr color="green" width="80%">
		<font size="3" color="red">${cname}</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
			<c:forEach var="pdto" items="${code}">
				<td align="center">
					<a href="mall_prodView.mall?pnum=${code.pnum}&select=${code}">
						<img src="${upPath} }/${code.pimage}" width="80" height="60">
					</a>	<br>
	
					${code.pname}<br>
					<font color="red">${df.code.price}</font>원<br>
					<font color="blue">${code.point}</font>point
				</td>
				<c:set var="count" value="${count+1 }"/>	
				<c:if test="${count==3 }">
				<c:set value="${count=0 }"/>
				</tr><tr>	
				</c:if>
			</c:forEach>	
			</tr>
		</table>
	</c:if>
</div>	
<%@ include file="mall_bottom.jsp"%>	