<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>example</title>
		<style>
			.topFont{
				color : white;
			}
		</style>
	</head>
	<body>
		<div align = "center">
			<table align = "center" width = "100%" height = "50" bgcolor="blue">
				<tr>
					<!-- 로그인 전 -->
					<td width = "17%"></td>
					<td align="left" width="20%" class="topFont">호텔대잔치</td>			<!-- 로고이미지 -->
					<td width = "25%" class="topFont"></td>
					<td width = "9%" class="topFont">이미 파트너로 등록하셨나요?</td>						
					<td width = "5%" class="topFont"><a href="company_register">기업회원가입</a></td>						<!-- 기업회원 가입페이지로 -->
					<td width = "3%" class="topFont"><a href="company_login">로그인</a></td>							<!-- 기업로그인페이지로 -->
					<td width = "4%" class="topFont"><a href="company_edit">기업관리</a></td>
					<td width = "10" class="topFont"><a href="admin_company_list">관리자 페이지로</a><br>
					<td width = "15%"></td>
					<!-- 로그인 후 -->
					<!-- <td width = "17%"></td>
					<td align="left" width="20%" class="topFont">호텔대잔치</td>			로고이미지
					<td width = "25%"></td>
					<td width = "9%" class="topFont">~회사 접속중<br>(user_company의 c_name)</td>						
					<td width = "4%" class="topFont">호텔관리</td>						front쪽에서 리스트 적용하여(기업정보수정, 숙소관리, 예약현황, Q&A)
					<td width = "4%" class="topFont">로그아웃</td>						로그아웃 후 기업메인 페이지로
					<td width = "10" class="topFont">관리자 페이지로<br>(user_personal의 level이 3일경우)</td>					기업관리자접속시
					 -->
				</tr>
			</table>
		</div>