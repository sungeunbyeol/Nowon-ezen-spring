<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!-- mall_top.jsp -->
<c:if test="${empty cateList}">
	<script type="text/javascript">
		alert("ºÓ«Œ∏Ù ¡ÿ∫Ò¡ﬂ ¿‘¥œ¥Ÿ.")
		location.href="main.do"
	</script>
</c:if>
<html>
<head>
	<title>ºÓ«Œ∏Ù»®</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function cateList(cname, code){
			document.f.action = "mall_cgProdList.do"
			document.f.cname.value = cname
			document.f.code.value = code
			document.f.submit()
		}
	</script>
</head>
<body>
	<div align="center">
		<table border="1" width="800" height="650">
			<tr height="50">
				<td colspan="2" align="center">
					<a href="main.do">HOME</a> | 
					<a href="shopAdmin.do">∞¸∏Æ¿⁄»®</a> | 
					<a href="shop.do">ºÓ«Œ∏Ù»®</a> | 
					<a href="cartList.do">¿ÂπŸ±∏¥œ</a>
				</td>
			</tr>
			<tr>
				<td width="20%" valign="top" align="center">
					tree/view
					<table border="1" width="100%">
				<c:forEach var="dto" items="${cateList}">	
						<tr>
							<td><a href="javascript:cateList('${dto.cname}','${dto.code}')">
								${dto.cname}[${dto.code}]
							</a></td>
						</tr>
				</c:forEach>
					</table>
					<form name="f" method="post">
						<input type="hidden" name="cname">
						<input type="hidden" name="code">
					</form>
				</td>
				<td width="80%">
				
				
				
				
				
				
				
				
				
				
				
				
				
				