<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<form style="background-color:#fbfbfb;margin-top:-16px; margin-bottom:-22px;"name="f9" id="checkForm" action="user_bookWriteform" method="post">
	<input type="hidden" id="h_num" name="h_num" value="${hdto.h_num}">
	<div style="width: 1000; margin: 0 auto; ">
		<div class="Roomcontent"> 
		<span class="row align-center" style="font-weight:bold; margin-top: 10px;">
			[${Room.room_name}]${Room.room_type}
		</span> 
		<span class="row align-center" style="font-weight:bold; margin-top: 10px;">
			객실 최대 인원 ${Room.room_capacity}명
		</span> 
		<span class="row align-center" style="font-weight:bold; margin-top: 10px;">
			${fn:replace(hdto.h_address, '@', ' ')}
		</span> 
		 
			<div class="selectedImage" style="margin-top:10px;">
				<img src="resources/images/room/${Room.room_image1}" id="selectedImage">
			</div>
			<div style="margin-top:3px;" class="row justify-end flex-grow images" id="roomcontentImages">
				<img style="width:18%; margin-left:2px;" src="resources/images/room/${Room.room_image1}">
				<img style="width:18%; margin-left:2px;" src="resources/images/room/${Room.room_image2}">
				<img style="width:18%; margin-left:2px;" src="resources/images/room/${Room.room_image3}">
			</div>
		</div>
	<div class="row" style="justify-content: space-between;">
		<span class="row align-center" style="font-size:larger; font-weight:bold; margin-top: 10px; width:40%; justify-content: flex-start;">
			<ul>
			<li style="list-style-type:none;">숙박</li>
			<li style="list-style-type:circle;">체크인 15:00 부터</li>
			<li style="list-style-type:circle;">체크아웃 23:00 부터 </li>
			</ul>
			</span>
		<div class="row" style="justify-content: flex-end;" >	
			<div class="column" style="display: flex;">		 
					<div class="column" style="font-size:larger; font-weight:bold; text-align:right; width: 180px; margin-top: 10px; align-items: revert;"> 
						${Room.room_price} 원 
					</div>
					<div class="column align-center" style="flex-direction:row-reverse;margin-right:6px; margin-top:10px;">
						<select id="room_num" name="room_num" style="width:120px; height: 30px;">
						<c:if test="${fn:length(roomList) eq 0}">
							매진
						</c:if>
						<c:if test="${fn:length(roomList) ne 0}">
						<c:forEach var="rdto" items="${roomList}">
							<option value="${rdto.room_num}">
								객실 [${rdto.room_num}]
							</option>  
						</c:forEach>
						</c:if>  
						</select>
					</div>   
					<div class="column" style="width: 100px; margin-left:auto; margin-right:24px; margin-top:10px; ">	
						<c:if test="${bookable_roomCount ne 0}"> 
							<input type='submit' value='예약하기' style="font-weight:bold;font-size:large;width:120px; height:40px; background-color:#609AE9;">
						</c:if> 
						<c:if test="${bookable_roomCount eq 0}"> 
							<input type='button' value='매진' onclick="#" style="width: 120px; background-color:#f5635c;">  
						</c:if>
					</div>
				</div>
			</div>	 
		</div>
			
		<div style="margin-top: 60px;">
			<h4>기본정보</h4>
			<ul>
				<c:forEach var="info" items="${hotelInfo}">
					<li>${info}</li>
				</c:forEach>
			</ul>		
		</div>
		<div style="margin-top: 20px;">
			<h4>예약공지</h4>
			<ul>
				<c:forEach var="notice" items="${hotelNotice}">
					<li>${notice}</li>
				</c:forEach>
			</ul>			
		</div>
		<div style="margin-top: 20px;">
			<h4>취소공지</h4> 
			<ul> 
			<li>당일 체크인 상품으로 취소 및 환불 불가합니다.</li>
			<li>단, 호텔의 경우 예약 완료 시점부터 10분 이내 전액 취소가 가능합니다. (MY야놀자 → 예약 내역)</li>
			<li>예약완료 후 10분 이내라도 입실 시간이 경과된 경우, 취소 및 환불 불가합니다.</li>
			<li>상세한 취소 규정은 '자세히 보기'에서 확인하실 수 있습니다.</li>
			</ul>
		</div>
		<div class="row align-center justify-center"> 
			<div style="text-align:center; position: fixed; bottom: 0; z-index: 9999; width:100%; background-color:#1f244d;">
        		<i class="fas fa-address-book fa-2x" style="margin-left:100px; margin-right: 20px; color: white; margin-bottom:10px; margin-top: 10px;" onclick="location.href='user_myPage'"></i>
        	<c:if test="${bookable_roomCount ne 0}">
        		<i class="fas fa-credit-card fa-2x" style="margin-right:20px;color: white; margin-bottom:10px" onclick="reservation()"></i>
			</c:if>
    			<i class="fas fa-heart fa-2x" style="margin-right:20px;color: white; margin-bottom:10px; margin-top: 10px;" onclick="location.href='user_wishlist'"></i> 
    			<i class="fas fa-home fa-2x" style="color: white; margin-bottom:10px; margin-top: 10px;" onclick="location.href='main'"></i> 	
    		</div>
		</div>
	</div>  
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	function reservation(){

		document.f9.submit()
	}
</script>
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