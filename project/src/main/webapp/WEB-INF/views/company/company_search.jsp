<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- company_search_email.jsp -->
<%@ include file ="company_top.jsp" %>
<div align="center">
<form name="f_search" method="POST">
<table border="0" width="280" align="center">
<tr>
<c:if test="${param.mode.equals('email')}">
	<td align="center"><h3>이메일 찾기</h3></td>
</c:if>
<c:if test="${param.mode.equals('password')}">
	<td align="center"><h3>비밀번호 찾기</h3></td>
</c:if>
</tr>
<c:if test="${param.mode.equals('password')}">
	<td>
		<input type="text" name="c_email" placeholder="이메일을 입력해 주세요." tabindex="1" style="width:350px;height:50px">
	</td>
</c:if>
<tr>
	<td>
		<input type="text" name="c_name" placeholder="사명을 입력해 주세요." tabindex="2" style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td>
		<input type="text" name="c_bnum" placeholder="사업자번호를 입력해 주세요." tabindex="3" style="width:350px;height:50px">
	</td>
</tr>
<tr>
	<td>
	<c:if test="${param.mode.equals('email')}">
		<button type="submit" formaction="company_search_email_ok" 
		style="width:350px;height:50px;background-color:black;color:white;border-color:black">이메일 찾기</button>
	</c:if>
	<c:if test="${param.mode.equals('password')}">
		<button type="submit" formaction="company_update_password" 
		style="width:350px;height:50px;background-color:black;color:white;border-color:black">비밀번호 찾기</button>
	</c:if>
	</td>
</tr>
</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>