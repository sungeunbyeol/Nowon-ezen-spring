<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- user_search_email.jsp -->
<%@ include file="../user_top.jsp"%>
<div align="center">
<form name="f_search" method="POST" action="user_search_ok">
	<input type="hidden" name="mode" value="${param.mode}">
	<table border="0" width="280" align="center">
		<tr>
			<c:if test="${param.mode.equals('u_email')}">
				<td align="center"><h3>이메일 찾기</h3></td>
			</c:if>
			<c:if test="${param.mode.equals('u_password')}">
				<td align="center"><h3>비밀번호 찾기</h3></td>
			</c:if>
		</tr>
		<c:if test="${param.mode.equals('u_password')}">
			<td>
				<input type="text" name="u_email" placeholder="이메일을 입력해 주세요." tabindex="1" style="width:350px;height:50px" required oninvalid="this.setCustomValidity('이메일을 입력해주세요.')" oninput = "setCustomValidity('')">
			</td> 
		</c:if> 
		<tr>
			<td>
				<input type="text" name="u_name" placeholder="이름을 입력해 주세요." tabindex="2" style="width:350px;height:50px" required oninvalid="this.setCustomValidity('이름을 입력해주세요.')" oninput = "setCustomValidity('')">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="u_tel" placeholder="전화번호를 입력해 주세요." tabindex="3" style="width:350px;height:50px" required oninvalid="this.setCustomValidity('전화번호를 입력해주세요.')" oninput = "setCustomValidity('')">
			</td>
		</tr>
		<tr>
			<td>
			<c:if test="${param.mode.equals('u_email')}">
				<button type="submit" formaction="user_search_ok" 
				style="width:350px;height:50px;background-color:black;color:white;border-color:black">이메일 찾기</button>
			</c:if>
			<c:if test="${param.mode.equals('u_password')}">
				<button type="submit" formaction="user_search_ok" 
				style="width:350px;height:50px;background-color:black;color:white;border-color:black">비밀번호 찾기</button>
			</c:if>
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>