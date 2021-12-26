<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙소 상세보기</title>
</head>
<body>
	<table border="1" width="1000" height="600">
		<td>
		<div align ="center">
				<hr color="green" width="300">
						숙소상세보기
				<hr color="green" width="300">
				<table width = "100%" class="outline">
					<tr bgcolor="yellow">
						<th>숙소이미지</th>
						<th>방타입 </th>
						<th>방 번호</th>
						<th>방 이름</th>
						<th>방 갯수</th>
						<th>방 최대인원</th>
						<th>방 금액</th>
					</tr>
					<tr>
						<td>room_image1</td>
						<td>room_type</td>
						<td>room_name</td>
						<td>room_num</td>
						<td>romm_count</td>
						<td>room_capacity</td>
						<td>room_price</td>
						<td><a href="room_inputedit">수정하기</a></td>
						<!-- 가져갈때 room_num 통해서 상세보기 가기 -->
					</tr>
					</table>
				</div>		
			</td>
		</table>	
<%@ include file="../bottom.jsp" %>