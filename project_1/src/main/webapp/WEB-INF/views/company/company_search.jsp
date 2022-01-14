<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- company_search_email.jsp -->
<%@ include file ="company_top.jsp" %>
<div align="center">
<script type="text/javascript">
	function check(email){
		if (email.value == "pass" && f_search.c_email.value==""){
			alert("이메일을 입력하셔야 합니다.")
			f_search.c_email.focus()
			return  
		}
		if (f_search.c_name.value==""){
			alert("회사명을 입력하셔야 합니다.")
			f_search.c_name.focus()
			return 
		}
		if (f_search.c_bnum.value==""){
			alert("사업자 번호를 입력하셔야 합니다.")
			f_search.c_bnum.focus()
			return 
		}
		
		document.f_search.submit()
	   }
</script>
<c:if test="${param.mode.equals('c_email')}">
<form name="f_search" method="POST" action="company_search_email_ok">
</form>
</c:if>	
<c:if test="${param.mode.equals('c_password')}">
<form name="f_search" method="POST" action="company_update_password">
</form> 
</c:if>
<input type="hidden" name="mode" value="${param.mode}">
<table border="0" width="280" align="center">
	<tr>
	<c:if test="${param.mode.equals('c_email')}">
		<td align="center"><h3>이메일 찾기</h3></td>
	</c:if>
	<c:if test="${param.mode.equals('c_password')}">
		<td align="center"><h3>비밀번호 찾기</h3></td>
	</c:if>
	</tr>
	<c:if test="${param.mode.equals('c_password')}">
		<td>
			<input type="text" name="c_email" placeholder="이메일을 입력해 주세요." tabindex="1" style="width:350px;height:50px">
		</td>
	</c:if>
	<tr>
		<td>
			<input type="text" name="c_name" placeholder="회사명을 입력해 주세요." tabindex="2" style="width:350px;height:50px">
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" name="c_bnum" placeholder="사업자번호를 입력해 주세요." tabindex="3" style="width:350px;height:50px">
		</td>
	</tr> 
	<tr>
		<td>
		<c:if test="${param.mode.equals('c_email')}">
			<button type="button" onclick="check('email')"
			style="width:350px;height:50px;background-color:black;color:white;border-color:black" value="이메일 찾기">이메일찾기</button>
		</c:if>
		<c:if test="${param.mode.equals('c_password')}">
			<button type="button" onclick="check('pass')" value="pass"
			style="width:350px;height:50px;background-color:black;color:white;border-color:black">비밀번호 찾기</button>
		</c:if>
		</td>
	</tr>
</table>
</div>
<%@ include file="../bottom.jsp" %>