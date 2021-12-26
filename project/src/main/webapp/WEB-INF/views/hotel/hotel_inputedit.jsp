<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔등록 수정</title>
</head>
	<div align = "center">
		<a href = "hotel_inputedit">호텔등록 수정 </a>| 
		<a href = "room_inputedit">룸등록 수정 </a> | 
		<a href = "hotel_detailedit">추가설명 등록 수정</a>
	</div>
	<!-- 얘가 top2로 들어가면 됨 -->
<body>
	<table border = "1" align = "center" width = "600" height = "500">
			<tr>
				<td>호텔이미지</td>
				<td><input type="file" name = "h_image1"><br>
					<input type="file" name = "h_image2"><br>
					<input type="file" name = "h_image3"><br>
					<input type="file" name = "h_image4"><br>
					<input type="file" name = "h_image5"><br>
			</tr>
			<tr>
				<td width = "120">
				호텔 이름
				</td>
				<td>
				<input type="text" name = "h_name">
				</td>
			</tr>
			<tr>
				<td width = "120">
				호텔등급
				</td>
				<td>
				<select id="grade" name="grade" size="1">
					<option value="">선택하세요.</option>
					<option value="3">트윈</option>
					<option value="4">더블</option>
					<option value="5">디럭스</option>
				</select> 
				</td>
			</tr>
			<tr>
				<td width = "120">
				호텔 담당자명
				</td>
				<td>
				<input type="text" name = "h_manager">
				</td>
			</tr>
			<tr>
				<td width = "120">
				호텔 전화번호
				</td>
				<td>
				<input type="text" name = "h_tel">
				</td>
			</tr>
				<td>
					호텔 주소
				</td>
				<td>
					<input type="text" id="sample3_postcode" placeholder="우편번호">
					<input type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"><br>
						<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
					<input type="text" id="sample3_address" placeholder="주소"><br>
					<input type="text" id="sample3_detailAddress" placeholder="상세주소">
					<input type="text" id="sample3_extraAddress" placeholder="참고항목">
				</td>
			<tr>	
				<td>
					호텔 지원서비스
				</td>
				<td>
					<input type="checkbox" name="service" value="h_park">주차공간
					<input type="checkbox" name="service" value="h_meal">조식
					<input type="checkbox" name="service" value="h_wifi">무료-WIFI
					<input type="checkbox" name="service" value="h_disabled">장애인편의시설
					<input type="checkbox" name="service" value="h_ott">OTT 서비스<br>
					<input type="checkbox" name="service" value="h_pool">수영장
					<input type="checkbox" name="service" value="h_kids">키즈존
					<input type="checkbox" name="service" value="h_bus">셔틀버스운행
					<input type="checkbox" name="service" value="h_smoke">흡연구역
					<input type="checkbox" name="service" value="h_front24">24시간프론트운영
				</td>
			</tr>
			<tr>
			<td colspan="2" align="center" >
				<form action="hotel_input.do" method = "post" name = "f" onsubmit="return check()"> 
					<input type = "button" value = "돌아가기">
					<input type = "submit" value = "계속">
				</form>
			</td>
			</tr>
		</table>
<%@ include file="../bottom.jsp" %>