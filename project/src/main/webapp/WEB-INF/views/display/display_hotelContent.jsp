<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
  
	<script type="text/javascript">
		function wishCheck1(h_num, u_num, location){
			var child = window.open("wishRelease2?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search","width=10, height=10");
			window.parent.location.reload();
			child.close();
		}
		function wishCheck2(h_num, u_num, location){
			var child = window.open("wishCheck2?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
			window.parent.location.reload();
			child.close();
		}
	</script>
	
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>	<div style="width: 80%; margin: 0 auto;">
		<div class="booking">
			<div class="selectedImage">
				<img src="resources/no1.gif" id="selectedImage">
			</div>
			<div class="row justify-between images" id="bookingImages">
				<img src="resources/no1.gif">
				<img src="resources/no2.gif">
				<img src="resources/no3.gif">
				<img src="resources/no4.gif">
				<img src="resources/no5.gif">
			</div>
		</div>
			<!-- 추가내용 -->
		<div class="row align-center" style="margin-top: 10px; justify-content:flex-end;">
			<span>
			<i class="fas fa-share-alt-square"></i>
				<!-- 비회원 표시 -->
				<c:if test="${empty loginOkBean}">
					<!-- 로그인 화면으로 보내거나, 이전페이지로 보내면 될듯 -->
					<a href="user_needLogin"><i class="far fa-heart"></i></a>
				</c:if>
				<!-- 로그인 회원 표시 -->
				<c:if test="${not empty loginOkBean}">
					<c:if test="${hdto.wishList eq '1'}">
						<a href="javascript:wishCheck1('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="fas fa-heart"></i></a>
					</c:if>
					<c:if test="${hdto.wishList eq '0'}">
						<a href="javascript:wishCheck2('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="far fa-heart"></i></a>
					</c:if>
				</c:if>
			</span>		
		</div>
		<div class="border-bottom">
			<span class="spanLeft">
				${hdto.h_name}
			</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="spanLeft">
				${hdto.h_grade}성급
			</span>
			<br>
			<span>
				<i class="fas fa-star"></i>${starAverage} / 5
			</span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<span>
				${reviewCount}
			</span>
		</div> 
		<div>
			${hdto.h_address}
		</div>
		
		<%-- <c:if test="${empty twinRoom}">
			<div class="flex">
			등록된 방이 없습니다<br><br>
			</div>
		</c:if>
		<c:if test="${not empty twinRoom}"> --%>
		<c:if test="${empty twinRoom}">
			<h2 align="center">등록된 방이 없습니다</h2>
		</c:if>
		<c:if test="${not empty twinRoom}">
			<c:forEach var="tRoom" items="${twinRoom}">
				<c:if test="${tRoom.room_capacity+2 >= sessionScope.inwon}">
					<table align="center" width = "80%">
						<tr>
							<td rowspan="7"><a href = "display_roomContent?room_num=${tRoom.room_num}&h_num=${hdto.h_num}"><img class="picture" src="resources/${tRoom.room_image1}"/></a></td>
						</tr>
						<tr>
							<td>${tRoom.room_type}&nbsp;[${tRoom.room_name}]</td>
						</tr>
						<tr>
							<td>Late&nbsp;Check-in&nbsp;18시</td>
						</tr>
						<tr>
							<td>기준&nbsp;${tRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${tRoom.room_capacity + 2}명</td>
						</tr>
						<tr>
							<td>숙박&nbsp;18:00&nbsp;부터</td>
						</tr>
						<tr>
							<td align="right">판매가</td>
						</tr>
						<tr>
							<td align="right">${tRoom.room_price}원</td>
						</tr>
					</table>
				</c:if>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty doubleRoom}">
			<h2 align="center">등록된 방이 없습니다</h2>
		</c:if>
		<c:if test="${not empty doubleRoom}">
			<c:forEach var="dbRoom" items="${doubleRoom}">
			<c:if test="${dbRoom.room_capacity+2 >= sessionScope.inwon}">
				<table align="center" width = "80%">
					<tr>
						<td rowspan="7"><a href = "display_roomContent?room_num=${dbRoom.room_num}&h_num=${hdto.h_num}"><img class="picture" src="resources/${dbRoom.room_image1}"/></a></td>
					</tr>
					<tr>
						<td>${dbRoom.room_type}&nbsp;[${dbRoom.room_name}]</td>
					</tr>
					<tr>
						<td>Late&nbsp;Check-in&nbsp;18시</td>
					</tr>
					<tr>
						<td>기준&nbsp;${dbRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${dbRoom.room_capacity + 2}명</td>
					</tr>
					<tr>
						<td>숙박&nbsp;18:00&nbsp;부터</td>
					</tr>
					<tr>
						<td align="right">판매가</td>
					</tr>
					<tr>
						<td align="right">${dbRoom.room_price}원</td>
					</tr>
				</table>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${empty deluxeRoom}">
			<h2 align="center">등록된 방이 없습니다</h2>
		</c:if>
		<c:if test="${not empty deluxeRoom}">
			<c:forEach var="dxRoom" items="${deluxeRoom}">
				<table align="center" width = "80%">
					<a href = "display_roomContent?room_num=${dxRoom.room_num}&h_num=${hdto.h_num}">
					<tr>
						<td rowspan="7"><img class="picture" src="resources/${dxRoom.room_image1}"/></td>
					</tr>
					<tr>
						<td>${dxRoom.room_type}&nbsp;[${dxRoom.room_name}]</td>
					</tr>
					<tr>
						<td>Late&nbsp;Check-in&nbsp;18시</td>
					</tr>
					<tr>
						<td>기준&nbsp;${dxRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${dxRoom.room_capacity + 2}명</td>
					</tr>
					<tr>
						<td>숙박&nbsp;18:00&nbsp;부터</td>
					</tr>
					<tr>
						<td align="right">판매가</td>
					</tr>
					<tr>
						<td align="right">${dxRoom.room_price}원</td>
					</tr>
					</a>
				</table>
			</c:forEach>
		</c:if>
		<%-- <a href = "display_roomContent"><img class="picture" src="resources/${twinRoom.room_image1}"/></a>	
			<div class="flex">
			<a href = "display_roomContent">
				${twinRoom.room_type}&nbsp;[${twinRoom.room_name}]<br>
				Late&nbsp;Check-in&nbsp;18시<br>
				기준&nbsp;${twinRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${twinRoom.room_capacity}명<br><br>
				숙박&nbsp;18:00&nbsp;부터<br>
				판매가<br>
				${twinRoom.room_price}원<br><br>
			</a>
			</div>
		</c:if>
		<c:if test="${empty doubleRoom}">
			<div class="flex">
			등록된 방이 없습니다<br><br>
			</div>
		</c:if>
		<c:if test="${not empty doubleRoom}">
			<a href = "display_roomContent"><img class="picture" src="resources/${doubleRoom.room_image1}"/></a>	
			<div class="flex">
			<a href = "display_roomContent">
				${doubleRoom.room_type}&nbsp;[${doubleRoom.room_name}]<br>
				Late&nbsp;Check-in&nbsp;18시<br>
				기준&nbsp;${doubleRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${doubleRoom.room_capacity}명<br><br>
				숙박&nbsp;18:00&nbsp;부터<br>
				판매가<br>
				${doubleRoom.room_price}원<br><br>
			</a>
			</div>
		</c:if>
		<c:if test="${empty deluxeRoom}">
			<div class="flex">
			등록된 방이 없습니다<br><br>
			</div>
		</c:if>
		<c:if test="${not empty deluxeRoom}">
			<a href = "display_roomContent"><img class="picture" src="resources/${deluxeRoom.room_image1}"/></a>	
			<div class="flex">
			<a href = "display_roomContent">
				${deluxeRoom.room_type}&nbsp;[${deluxeRoom.room_name}]<br>
				Late&nbsp;Check-in&nbsp;18시<br>
				기준&nbsp;${deluxeRoom.room_capacity}명&nbsp;/&nbsp;최대&nbsp;${deluxeRoom.room_capacity}명<br><br>
				숙박&nbsp;18:00&nbsp;부터<br>
				판매가<br>
				${deluxeRoom.room_price}원<br><br>
			</a>
			</div>
		</c:if> --%>
	
		
	<input type="hidden" id="addr" value="${map_addr}">
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=248b81637c1b88ea9f5a633f55340228&libraries=services"></script>
   <div id="map" style="width:1000px;height:500px;"></div>
   <div id="clickLatlng"></div>
   <script>
	   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	   // 지도를 생성합니다    
	   var map = new kakao.maps.Map(mapContainer, mapOption); 
	   
	   // 주소-좌표 변환 객체를 생성합니다
	   var geocoder = new kakao.maps.services.Geocoder();
	   
	   // 주소로 좌표를 검색합니다
	   var addr = $("#addr").val();
	   alert(addr);
	   geocoder.addressSearch(addr, function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	      var message = 'latlng: new kakao.maps.LatLng(' + result[0].y + ', ';
	      message += result[0].x + ')';
	      
	      var resultDiv = document.getElementById('clickLatlng'); 
	      resultDiv.innerHTML = message;
	      
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">숙소 위치</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	       } 
	   });    
	      function wishCheck1(h_num, u_num, location){
	         var child = window.open("wishRelease2?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search","width=10, height=10");
	         window.parent.location.reload();
	         child.close();
	      }
	      function wishCheck2(h_num, u_num, location){
	         var child = window.open("wishCheck2?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
	         window.parent.location.reload();
	         child.close();
	      }
   </script>
		
		<div class="booking border row" align="center">
			편의시설 및 테마
		</div>
		<div class="booking border row">
			<span>
				<c:if test="${not empty hdto.h_park}">
					<img src="resources/hotelDetail/park.png" width="100" height="100"><br>
					주차가능
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_meal}">
				<img src="resources/hotelDetail/breakfast.png" width="100" height="100"><br>
					조식운영(뷔페)
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_wifi}">
				<img src="resources/hotelDetail/wifi.png" width="100" height="100"><br>
					와이파이
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_disabled}">
				<img src="resources/hotelDetail/wheelchair.jpg" width="100" height="100"><br>
					장애인 시설
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_ott}">
				<img src="resources/hotelDetail/ott.jpg" width="100" height="100"><br>
					OTT서비스
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_pool}">
				<img src="resources/hotelDetail/pool.jpg" width="100" height="100"><br>
					수영장
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_kids}">
				<img src="resources/hotelDetail/kidszone.jpg" width="100" height="100"><br>
					키즈존
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_bus}">
				<img src="resources/hotelDetail/suttlebus.jpg" width="100" height="100"><br>
					셔틀버스
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_smoke}">
				<img src="resources/hotelDetail/smoke.jpg" width="100" height="100"><br>
					흡연구역
				</c:if>
			</span>
			<span>
				<c:if test="${not empty hdto.h_front24}">
				<img src="resources/hotelDetail/24front.png" width="100" height="100"><br>
					24시간 프론트
				</c:if>
			</span>
		</div>
		
		<%-- <div class = "column review border-bottom">
			<span>
				후기(${reviewCount})개
			</span>
			<span>
				<a href="review?h_num=2">전체보기</a>
			</span>
			<br>
			<span>
				<i class="far fa-star"></i>${starAverage} / 5
			</span>
		</div> --%>
		
		<table align="center" width="80%" border="0">
			<tr>
				<td align="left">후기(${reviewCount}개)</td>
				<td align="right"><a href="review?h_num=${hdto.h_num}">전체보기</a></td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					<i class="far fa-star"></i>${starAverage} / 5<br>
					최근 12개월 누적 평점
				</td>
			</tr>
			
			<c:forEach var="review" items="${reviewList}" begin="0" end="1">
			<tr><td><br></td></tr>
				<tr>	
					<td align="left"><i class="far fa-star"></i>${review.review_star}</td>
					<td align="right">작성일&nbsp;${review.review_joindate}</td>
				</tr>
				<tr>
					<td align="left" colspan="2">u_nickname / room_name / room_type</td>
				</tr>
				<tr>
					<td align="left" colspan="2">${review.review_contents}</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="center" colspan="2"><a href="review?h_num=${hdto.h_num}">전체 후기 보기</a><br>(후기 ${reviewCount}개)</td>
			</tr>
		</table>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		let bookingImages = $('#bookingImages').children('img')
		$(bookingImages).each(function (index, el) {
			$(this).click(function() {
				let selectedSrc = $(el).attr('src')
				$('#selectedImage').attr('src', selectedSrc)
			})
		});
	});


    
</script>
<%@ include file="../bottom.jsp" %>