<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/htm; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="css/companytopstyle.css"> -->
<!-- 글꼴 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/66da7a4b92.js" crossorigin="anonymous"> 
</script>
<title>기업 페이지</title> 
</head>
<body> 
	<nav class="navbar"><!-- 제일큰박스 -->
		<div class="navbar_logo">
			<i class="fas fa-suitcase"></i>
			<a href="company_main">Hotel Festival</a>
		</div>
		 
		<c:if test="${empty companyLoginOkBean}">
		<div class="partner">이미 파트너로 등록 하셨나요?</div>
		<ul class="navbar_menu">
			<li><a href="company_main">Home</a></li> 
			<li><a href="company_login">로그인</a></li>
			<li><a href="company_ssn">회원가입</a></li>
		</ul>
		
		</c:if>
		<c:if test="${not empty companyLoginOkBean}">
		<ul class="navbar_menu">
			<li><a href="main">Home</a></li>
			<li><a href="company_myPage">기업관리</a></li>
			<li><a href="company_logout">로그아웃</a></li>
		<c:if test="${loginOkBean.a_level == '3' }">
			<li><a href="admin_company_list">관리자 페이지로 이동</a></li>
		</c:if>
		</ul>  
		</c:if>		 
</nav> 
	
	
	<!--  
		<div align = "center">
			<table align = "center" width = "100%" height = "50" bgcolor="blue">
				<tr>
					
					<c:if test="${empty companyLoginOkBean}">
						<td width = "17%"></td>
						<td align="left" width="20%" class="topFont"><a href="company_main">호텔대잔치</a></td>			
								
						<td width = "25%" class="topFont"></td>
						<td width = "9%" class="topFont">이미 파트너로 등록하셨나요?</td>						
						<td width = "5%" class="topFont"><a href="company_register">기업회원가입</a></td>						
						<td width = "3%" class="topFont"><a href="company_login">로그인</a></td>							
						<td width = "15%"></td>
					</c:if>
					<c:if test="${not empty companyLoginOkBean}">
					
					 	<td width = "17%"></td>
					 	<td align="left" width="20%" class="topFont"><a href="company_main">호텔대잔치</a></td>	
						<td width = "25%"></td>
						<td width = "9%" class="topFont">${companyLoginOkBean.c_email}님 접속중<br></td>						
						<td width = "4%" class="topFont"><a href="hotel_list">호텔관리</a></td>
						<td width = "4%" class="topFont"><a href="company_myPage">기업관리</a>
						
						</td>					
						<td width = "4%" class="topFont"><a href="company_logout">로그아웃</a></td>
					 
					 <c:if test="${companyLoginOkBean.a_level == '3' }">
					 	<td width = "10" class="topFont"><a href ="admin_company_list">관리자 페이지로</a></td><br>
					 	
					</c:if>
				</c:if>
				</tr>
			</table>
		</div>
		-->