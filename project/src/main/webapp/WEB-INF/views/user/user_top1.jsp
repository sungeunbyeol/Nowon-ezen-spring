<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginTop</title>
<style>
		li{
			list-style: none;
			display:inline-block
		}
		.colorBtn{
			width: 150px;
			height: 36px;
			background-color: yellowgreen;
			color: white;
			text-align: center;
			line-height: 36px;
			cursor: pointer;
			border-radius: 6px;
			float: left;
			margin-right: 10px;
		}
		.revBt{
			width: 80px;
			height: 30px;
			background-color: yellow;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			float: right;
			margin-right: 10px;
		}
		.revBt2{
			width: 30px;
			height: 30px;
			background-color: yellow;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			float: right;
			margin-right: 10px;
		}
		.bottomBt{
			width: 180px;
			height: 30px;
			background-color: white;
			color: black;
			text-align: center;
			line-height: 30px;
			cursor: pointer;
			border-radius: 3px;
			margin-right: 10px;
		}
		ul{
			text-align: center
		}
		.topFont{
			color : white;
		}
	</style>
	<body>
		<div align = "center">
			
			<table align = "center" width = "100%" height = "50" bgcolor="red">
				<c:if test="${empty loginOkBean}">
				<tr>
					<!-- 로그인 전 -->
					<td width = "17%"></td>
					<td align="left" width="20%" class="topFont"><a href="main">호텔대잔치</a></td>			<!-- 로고이미지 -->
					<td width = "33%"></td>
					<td width = "3%" class="topFont"><a href="company_main">숙소등록</a></td>						<!-- 기업회원 메인으로 -->
					<td width = "3%" class="topFont"><a href="user_join">회원가입</a></td><!-- 일반회원 가입페이지로 -->
					<td width = "3%" class="topFont">
						<c:if test = "${empty loginOkBean}">
							<a href="user_login">로그인</a>
						</c:if>	
					</td>							<!-- 회원로그인페이지로 -->
					<td width = "5%" class="topFont"><a href="user_needLogin">위시리스트</a></td>						<!-- 위시리스트 페이지로 -->
					<td width = "3%" class="topFont"><a href="user_needLogin">마이페이지</a></td>
					<td width = "3%" class="topFont"><a href="admin_user_top">관리자 페이지로</a></td>
					<td width = "15%"></td>
				</tr>
				</c:if>
			</table>
</body>
</html>