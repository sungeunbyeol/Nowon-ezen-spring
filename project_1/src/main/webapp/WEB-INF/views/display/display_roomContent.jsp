<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<form name="f9" id="checkForm" action="user_bookWriteform" method="post">
	<input type="hidden" id="h_num" name="h_num" value="${hdto.h_num}">
	<div style="width: 80%; margin: 0 auto;">
		<div class="Roomcontent">
		<div class="row border align-center" style="margin-top: 10px;">
			[${Room.room_name}]${Room.room_type}
		</div>
		<div class="row_border_align-center" style="margin-top: 10px;">
			<select id="room_num" name="room_num">
			<c:forEach var="rdto" items="${roomList}">
				<option value="${rdto.room_num}">
					${rdto.room_num}
					<c:if test="${rdto.room_booked eq 'n'}">
						[예약가능]
					</c:if>
				</option>
			</c:forEach>
			</select>
		</div>
		<div class="row border align-center" style="margin-top: 10px;">
			객실 최대 인원 ${Room.room_capacity}명
		</div>
		<div class="row border align-center" style="margin-top: 10px;">
			${fn:replace(hdto.h_address, '@', ' ')}
		</div>
		
			<div class="selectedImage">
				<img src="resources/images/room/${Room.room_image1}" id="selectedImage">
			</div>
			<div class="row justify-between images" id="roomcontentImages">
				<img src="resources/images/room/${Room.room_image1}">
				<img src="resources/images/room/${Room.room_image2}">
				<img src="resources/images/room/${Room.room_image3}">
			</div>
		</div>
		<div class="row border align-center" style="margin-top: 10px;">
			숙박<br>
			체크인 15:00 부터<br>
			체크아웃 23:00 부터
		</div>	
		<div class="row border align-center" style="margin-top: 10px;">
			${Room.room_price} 원 
		</div>
			<div class="row align-center" style="justify-content:flex-end;">
				<c:if test="${bookable_roomCount ne 0}">
					<input type='submit' value='예약하기'>
				</c:if>
				<c:if test="${bookable_roomCount eq 0}">
					<input type='button' value='매진' onclick="#" style="width: 80px;">
				</c:if>
			</div>
		<div style="margin-top: 10px;">
			기본정보
			<ul>
				<c:forEach var="info" items="${hotelInfo}">
					<li>${info}</li>
				</c:forEach>
			</ul>		
		</div>
		<div style="margin-top: 20px;">
			예약공지
			<ul>
				<c:forEach var="notice" items="${hotelNotice}">
					<li>${notice}</li>
				</c:forEach>
			</ul>			
		</div>
		<div style="margin-top: 20px;">
			취소규정
			<li>당일 체크인 상품으로 취소 및 환불 불가합니다.</li>
			<li>단, 호텔의 경우 예약 완료 시점부터 10분 이내 전액 취소가 가능합니다. (MY야놀자 → 예약 내역)</li>
			<li>예약완료 후 10분 이내라도 입실 시간이 경과된 경우, 취소 및 환불 불가합니다.</li>
			<li>상세한 취소 규정은 '자세히 보기'에서 확인하실 수 있습니다.</li>
		</div>
		<div class="row align-center justify-center">
			<div style=" position: fixed; bottom: 0; z-index: 9999;">
        		<i class="fas fa-shopping-cart fa-2x" style="margin-right: 20px;"></i>
        		<i class="fas fa-clipboard-list fa-2x"></i>
    		</div>
		</div>
	</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		let roomcontentImages = $('#roomcontentImages').children('img')
		$(roomcontentImages).each(function (index, el) {
			$(this).click(function() {
				let selectedSrc = $(el).attr('src')
				$('#selectedImage').attr('src', selectedSrc)
			})
		});
	});
</script>
<%@ include file="../bottom.jsp" %>