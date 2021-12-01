<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- mall.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="mall_top.jsp" %>  
<div align="center">
	<h2>Welcome to My Mall</h2>
		<fmt:formatNumber value="${df}" type="currency" pattern="###,###"/>
		<c:if test="${plist==null || plist.size()==0}">
		<b>HIT 상품이 없습니다.</b>
		</c:if>
		<c:if test="${plist!=null || plist.size()!=0}">
			<c:set var="count" value="0"/>
		<hr color="green" width="80%">
		<font size="3" color="red">HIT</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
				<c:forEach var="pdto" items="${hit}">
				<td align="center">
					<a href="mall_prodView.mall?pnum=${hit.pnum}&select=hit">
						<img src="${upPath}/${hit.pimage}" width="80" height="60">
					</a>	<br>
					${hit.pname}<br>
					<font color="red">${df.format(hit.price)}</font>원<br>
					<font color="blue">${hit.point}</font>point
				</td>
				<c:set var="count" value="${count+1 }"/>
				<c:if test="${count==3 }">
				<c:set value="0"/>
				</tr><tr>	
				</c:if>	
				</c:forEach>
			</tr>
		</table>
<%
		List<ProductDTO> plist2 = (List)request.getAttribute("new");
		%>
		<c:if test="${plist2==null || plist2.size()==0}">
		<b>NEW 상품이 없습니다.</b>
		</c:if>
		<c:if test="${plist2!=null || plist2.size()!=0}">
		<c:set var="count" value="0"/>
		<%	 else {
			int count = 0;%>
		<hr color="green" width="80%">
		<font size="3" color="red">NEW</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
		<%			for(ProductDTO pdto : plist2) {%>
						<c:forEach var="pdto" items="${new}">
				<td align="center">
					<a href="mall_prodView.mall?pnum=${new.pnum}&select=new">
						<img src="${upPath}/${new.pimage}" width="80" height="60">
					</a>	<br>
					${new.pname}<br>
					<font color="red"><%=df.format(pdto.getPrice())%></font>원<br>
					<font color="blue">${new.point}</font>point
				</td>	
				<c:set var="count" value="${count+1 }"/>
				<c:if test="${count==3 }">
				<c:set value="0"/>
				</tr><tr>	
		</c:if>
		</c:if>
		
			</tr>
		</table>
		</c:if>
<%
		List<ProductDTO> plist3 = (List)request.getAttribute("best");
		if (plist3==null || plist3.size()==0){%>
		<b>BEST 상품이 없습니다.</b>
		<%	} else {
			int count = 0;%>
		<hr color="green" width="80%">
		<font size="3" color="red">BEST</font>
		<hr color="green" width="80%">
		<table border="0" width="100%">
			<tr>
		<%			for(ProductDTO pdto : plist3) {%>
				<td align="center">
					<a href="mall_prodView.mall?pnum=<%=pdto.getPnum()%>&select=best">
						<img src="<%=upPath%>/<%=pdto.getPimage()%>" width="80" height="60">
					</a>	<br>
					<%=pdto.getPname()%><br>
					<font color="red"><%=df.format(pdto.getPrice())%></font>원<br>
					<font color="blue"><%=pdto.getPoint()%></font>point
				</td>	
		<%			count++;
				if (count == 3){
					count = 0;%>
				</tr><tr>	
		<%			}
			} %>			
			</tr>
		</table>
		</c:if>
</div>	
<%@ include file="mall_bottom.jsp"%>				
				









