<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="shop.admin.dto.*"%>
<!-- prod_update.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		ProductDTO dto = (ProductDTO)request.getAttribute("pdto");
		String upPath = (String)request.getAttribute("upPath");
%>
<%@ include file="top.jsp"%>
<div align="center">
<form name="f" action="prod_update_ok.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600" class="outline">
		<caption>상품수정</caption>
		<tr>
			<th class="m2">카테고리</th>
			<td align="left">  
				<input type="text" name="pcategory_fk" value="<%=dto.getPcategory_fk()%>" disabled>
			</td>
		</tr>
		<tr>
			<th class="m2">상품번호</th>
			<td>
				${pdto.pnum}
				<input type="hidden" name="pnum" value="${pdto.pnum}"/>	
			</td>
		</tr>
		<tr>
			<th class="m2">상품명</th>
			<td><input type="text" name="pname" value="${pdto.pname}"></td>
		</tr>
		<tr>
			<th class="m2">제조회사</th>
			<td><input type="text" name="pcompany" value="${pdto.pcompany}"></td>
		</tr>
		<tr>
			<th class="m2">상품이미지</th>
			<td>
				<img src="${upPath}/${pdto.pimage}" width="80" height="80">
				<input type="hidden" name="pimage2" value="${pdto.pimage}"/>
				<input type="file" name="pimage">
			</td>
		</tr>
		<tr>
			<th class="m2">상품수량</th>
			<td><input type="text" name="pqty" value="${pdto.pqty}"></td>
		</tr>
		<tr>
			<th class="m2">상품가격</th>
			<td><input type="text" name="price" value="${pdto.price}"></td>
		</tr>
		<tr>
			<th class="m2">상품스팩</th>
			<td>
			String[] spec = new String[]{"normal", "hit", "best", "new"};%>
				<select name="pspec">		
				<c:forEach var="i" begin="0" end="spec.length">
				<c:if test=""	
			for(int i=0; i<spec.length; ++i){ 
					if (dto.getPspec().equals(spec[i])){%>
						<option value="<%=spec[i]%>" selected><%=spec[i].toUpperCase()%></option>
<%				}else { %>
						<option value="<%=spec[i]%>"><%=spec[i].toUpperCase()%></option>
<%				}
				}	%>					
				</select>
			</td>
		</tr>
		<tr>
			<th class="m2">상품소개</th>
			<td><textarea name="pcontents" rows="5" cols="60"><%=dto.getPcontents()%></textarea></td>
		</tr>
		<tr>
			<th class="m2">상품포인트</th>
			<td><input type="text" name="point" value="<%=dto.getPoint()%>"></td>
		</tr>
		<tr>
			<td colspan="2" class="m1">
				<input type="submit" value="상품수정">
				<input type="reset" value="다시입력">
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="bottom.jsp"%>