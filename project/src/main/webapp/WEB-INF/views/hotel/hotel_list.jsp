<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙소 리스트</title>
</head>
<body>
	<div align = "center">
		<a href = "hotel_input">숙소등록 </a>| 
		<a href = "hotel_list">숙소 리스트</a>
	</div>
	<!-- 이부분이 TOP파일 -->
		<table border="1" width="1000" height="600">
		<td>
		<div align ="center">
				<hr color="green" width="300">
							숙소리스트
				<hr color="green" width="300">
				<table width = "100%" class="outline">
					<tr bgcolor="yellow">
						<th>호텔이미지</th>
						<th>번호</th>
						<th>호텔명</th>
						<th>호텔번호</th>
						<th>호텔주소</th>
						<th>호텔등급</th>
					</tr>
					<tr>
						<td>h_image1</td>
						<td>h_num</td>
						<td>h_name</td>
						<td>h_tel</td>
						<td>h_address</td>
						<td>h_grade</td>
						<td><a href="room_list">상세보기</a><br>
						<a href="hotel_inputedit">수정</a></td>
						<!-- 가져갈때 h_num 통해서 상세보기 가기 -->
					</tr>
					</table>
				</div>		
			</td>
		</table>	
<%@ include file="../bottom.jsp" %>