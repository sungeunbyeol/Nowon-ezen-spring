<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- prod_update.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="top.jsp"%>
<div align="center">
<form name="f" action="prod_update_ok.do" method="post" enctype="multipart/form-data">
	<table border="0" width="600" class="outline">
		<caption>상품수정</caption>
		<tr>
			<th class="m2">카테고리</th>
			<td align="left">  
				<input type="text" name="pcategory_fk" value="${getProduct.pcategory_fk}" disabled>
			</td>
		</tr>
		<tr>
			<th class="m2">상품번호</th>
			<td>
				${getProduct.pnum}
				<input type="hidden" name="pnum" value="${getProduct.pnum}"/>	
			</td>
		</tr>
		<tr>
			<th class="m2">상품명</th>
			<td><input type="text" name="pname" value="${getProduct.pname}"></td>
		</tr>
		<tr>
			<th class="m2">제조회사</th>
			<td><input type="text" name="pcompany" value="${getProduct.pcompany}"></td>
		</tr>
		<tr>
			<th class="m2">상품이미지</th>
			<td>
				<img src="${upPath}/${getProduct.pimage}" width="80" height="80">
				<input type="hidden" name="pimage2" value="${getProduct.pimage}"/>
				<input type="file" name="pimage">
			</td>
		</tr>
		<tr>
			<th class="m2">상품수량</th>
			<td><input type="text" name="pqty" value="${getProduct.pqty}"></td>
		</tr>
		<tr>
			<th class="m2">상품가격</th>
			<td><input type="text" name="price" value="${getProduct.price}"></td>
		</tr>
		<tr>
			<th class="m2">상품스팩</th>
			<td>
				<select name="pspec">				
				<c:forTokens var="spec" items="hit,new,best,normal" delims=",">
					<c:if test="${getProduct.pspec == spec}">
						<option value="${spec}" selected>${fn:toUpperCase(spec)}</option>
					</c:if>	
					<c:if test="${getProduct.pspec != spec}">
						<option value="${spec}">${fn:toUpperCase(spec)}</option>
					</c:if>	
				</c:forTokens>					
				</select>
				
			</td>
		</tr>
		<tr>
			<th class="m2">상품소개</th>
			<td><textarea name="pcontents" rows="5" cols="60">${getProduct.pcontents}</textarea></td>
		</tr>
		<tr>
			<th class="m2">상품포인트</th>
			<td><input type="text" name="point" value="${getProduct.point}"></td>
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