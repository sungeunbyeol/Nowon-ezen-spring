<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>룸 등록</title>
</head>
	<div align = "center">
		<a href = "hotel_input"> 호텔등록 </a>| 
		<a href = "room_input">룸등록 </a> | 
		<a href = "hotel_detail">추가설명 등록</a>
	</div>
	<!-- 얘가 top2로 들어가면 됨 -->
<body>
	<table  align = "center" width = "1000" height = "300">
		<th>TWIN룸</th>
			<tr>
				<td>숙소 이름</td>
				<td>
				<input type="text" name = "room_name">
				</td>
				<td>금액</td>
				<td>
				<input type="text" name = "room_price=">원
				</td>
				<td>숙소 최대사용인원</td>
				<td>
				<input type="text" name = "room_capacity">명
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td >숙소 객실수</td>
				<td>
				<input type="text" name = "room_count">개
				</td>
				<td>방 상제정보</td>
				<td>
				<input tpye="text" name="room_contents">
				</td>
				<td>숙소 이미지</td>
				<td><input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
				</td>
			</tr>
				<th>DOUBLE룸</th>
			<tr>
				<td>숙소 이름</td>
				<td>
				<input type="text" name = "room_name">
				</td>
				<td>금액</td>
				<td>
				<input type="text" name = "room_price=">원
				</td>
				<td>숙소 최대사용인원</td>
				<td>
				<input type="text" name = "room_capacity">명
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td >숙소 객실수</td>
				<td>
				<input type="text" name = "room_count">개
				</td>
				<td>방 상제정보</td>
				<td>
				<input tpye="text" name="room_contents">
				</td>
				<td>숙소 이미지</td>
				<td><input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
				</td>
			</tr>
				<th>DELUX룸</th>
			<tr>
				<td>숙소 이름</td>
				<td>
				<input type="text" name = "room_name">
				</td>
				<td>금액</td>
				<td>
				<input type="text" name = "room_price=">원
				</td>
				<td>숙소 최대사용인원</td>
				<td>
				<input type="text" name = "room_capacity">명
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td >숙소 객실수</td>
				<td>
				<input type="text" name = "room_count">개
				</td>
				<td>방 상제정보</td>
				<td>
				<input tpye="text" name="room_contents">
				</td>
				<td>숙소 이미지</td>
				<td><input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
					<input type="file" name = "room_image1"><br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<form action="roominput.do" method = "post" name = "f" onsubmit="return check()">
						<input type = "button" value = "돌아가기">
						<input type = "submit" value = "계속">
					</form>
				</td>
			</tr>
		</table>
	<%@ include file="../bottom.jsp" %>