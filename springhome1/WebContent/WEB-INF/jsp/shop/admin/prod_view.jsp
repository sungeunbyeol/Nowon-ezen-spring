<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="shop.admin.dto.*"%>
<!-- prod_view.jsp -->
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="prod_list.do" method="post">
		<table border="0" width="750" class="outline">
			<caption>상품상세보기</caption>
			<tr>
				<th width="15%" class="m2">카테고리</th>
				<td width="35%" align="center">${getProduct.pcategory_fk}</td>
				<th width="15%" class="m2">상품번호</th>
				<td width="35%" align="center">${getProduct.pnum}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">상품명</th>
				<td width="35%" align="center">${getProduct.pname}</td>
				<th width="15%" class="m2">제조회사</th>
				<td width="35%" align="center">${getProduct.pcompany}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">상품이미지</th>
				<td colspan="3" align="center">
					<img src="${upPath}/${getProduct.pimage}" width="60" height="60">
				</td>
			</tr>
			<tr>
				<th width="15%" class="m2">상품수량</th>
				<td width="35%" align="center">${getProduct.pqty}</td>
				<th width="15%" class="m2">상품가격</th>
				<td width="35%" align="center">${getProduct.price}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">상품스팩</th>
				<td width="35%" align="center">${getProduct.pspec}</td>
				<th width="15%" class="m2">상품포인트</th>
				<td width="35%" align="center">${getProduct.point}</td>
			</tr>
			<tr>
				<th width="15%" class="m2">상품소개</th>
				<td colspan="3">
					<textarea name="pcontents" rows="5" cols="50" readOnly>${getProduct.pcontents}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="m1" align="center">
					<input type="submit" value="돌아가기">
				</td>	
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>












