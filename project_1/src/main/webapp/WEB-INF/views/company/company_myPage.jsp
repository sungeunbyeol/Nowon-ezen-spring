<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 마이페이지 리스트 include -->
<link rel="stylesheet" type="text/css" href="style.css"> 
<%@ include file="company_top.jsp"%>
<!-- 로그인 안하고 넘어가질때 있었는데 이를 방지 (추후삭제예정) -->
<c:if test = "${companyLoginOkBean==null}">
<c:redirect url = "company_main"/>
</c:if>
	<div align="center">
		<table border="1" width="800" height="650">
			<tr height="10%">
				<td align="center" colspan="2"><h1>기업 관리</h1></td>
			</tr>
			<tr>
				<td width="20%">
					<h3><a href="company_edit?c_num=${companyLoginOkBean.c_num}">기업 정보 수정</a></h3><br>
					<h3><a href="hotel_main" target="_blank">호텔 관리</a></h3><br>
					<h3><a href="list_companyQnA">Q&A</a></h3><br>
				</td>
				<td>