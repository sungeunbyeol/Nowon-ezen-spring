<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- search.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>${title} 찾기</title>
	<link rel="stylesheet" type="text/css" href="style.css"> 
	<script type="text/javascript">
		function check(mode){
			if (f.name.value==""){
				alert("회원명을 입력하셔야 합니다.")
				f.name.focus()
				return false
			}
			if (f.ssn1.value==""){
				alert("주민번호 앞자리를 입력하셔야 합니다.")
				f.ssn1.focus()
				return false
			}
			if (f.ssn2.value==""){
				alert("주민번호 뒷자리를 입력하셔야 합니다.")
				f.ssn2.focus()
				return false
			}
			if (mode.value=="pw" && f.id.value==""){
				alert("아이디를 입력하셔야 합니다.")
				f.id.focus()
				return false
			}
			return true
		}
	</script>
</head>
<body>
	<div align="center">
	<hr color="green" width="300">
	<h2>${title} 찾기</h2>
	<hr color="green" width="300">
	<form name="f" action="searchCheckLogin.do" method="post" onsubmit="return check('${param.mode}')">
		<table width="500" class="outline">
			<tr>
				<th>회원명</th>
				<td><input type="text" name="name" class="box">
			</tr>
			<tr>
				<th>주민번호</th>
				<td><input type="text" name="ssn1" class="box" maxlength="6"> -
				<input type="password" name="ssn2" class="box" maxlength="7"></td>
			</tr>
		<c:if test="${param.mode == 'pw'}">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" class="box">
			</tr>
		</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="찾기">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
		
	</form>
	</div>

</body>
</html>