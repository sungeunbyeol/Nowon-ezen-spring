<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, shop.admin.dto.*" %>      
<!-- mall_top.jsp -->
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:if test="${listCate == null || listCate.size() == 0 }">
		<script type="text/javascript">
			alert("���θ� �غ����Դϴ�.!!")
			location.href="main.do"
		</script>		
	</c:if>
<html>
<head>
	<title>���θ�Ȩ</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function cateList(cname, code){
			document.f.action = "mall_cgProdList.mall"
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
					<a href="main.shop">������Ȩ</a> | 
					<a href="main.mall">���θ�Ȩ</a> | 
					<a href="cartList.mall">��ٱ���</a>
				</td>
			</tr>
			<tr>
				<td width="20%" valign="top" align="center">
					tree/view
					<table border="1" width="100%">
					<c:forEach var="dto" items="${cateList}">
						<tr>
							<td><a href="javascript:cateList('${cateList.cname}',${cateList.code}'">
								${cateList.cname}[${cateList.code}]
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
				
				
				
				
				
				
				
				
				
				
				
				
				
				