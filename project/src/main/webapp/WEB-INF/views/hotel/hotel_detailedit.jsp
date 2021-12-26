<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가설명 등록수정</title>
</head>
	<div align = "center">
		<a href = "hotel_inputedit"> 호텔등록 수정 </a>| 
		<a href = "room_inputedit">룸등록 수정</a> | 
		<a href = "hotel_detailedit">추가설명 등록 수정</a>
	</div>
	<!-- 얘가 top2로 들어가면 됨 -->
<body>
<table align = "center" width = "600" height = "500">
			<tr>
				<td>기본정보</td>
				<td><textarea rows="5" cols="50" name="h_contents"></textarea></td>
			</tr>
			<tr>
				<td>
				예약 규정
				</td>
				<td><textarea rows="5" cols="50" name="h_notice"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center" >
					<form action="roominput.do" method = "post" name = "f" onsubmit="return check()">
						<input type = "button" value = "돌아가기">
						<input type = "submit" value = "등록완료">
					</form>
				</td>
			</tr>
<%@ include file="../bottom.jsp" %>