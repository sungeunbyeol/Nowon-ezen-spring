<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<script type="text/javascript">
	function sleep(ms) {
	  const wakeUpTime = Date.now() + ms;
	  while (Date.now() < wakeUpTime) {}
	}
	function wishCheck1(h_num, u_num, location){
		var child = window.open("wishRelease?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
		window.parent.location.reload();
		sleep(300);
		child.close();
	}
	function wishCheck2(h_num, u_num, location){
		var child = window.open("wishCheck?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
		window.parent.location.reload();
		sleep(300);
		child.close();
	}
	function goHotelContent(h_num) {
		document.f_searchOk.action = "display_hotelContent?h_num="+h_num;
		document.f_searchOk.submit();
	}
	
	function goNextFilter(filter, condition) {
		document.f_searchOk.action = "display_hotelSearchOk?filter="+filter+"&condition="+condition;
		document.f_searchOk.submit();
	}
</script>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
	<div class="align-center" style="width: 1000; margin: 0 auto;" >
		<div class="row justify-center align-center" style="border:1px solid grey;">
			<a href="javascript:goNextFilter('maxReview','${condition}')">
				<button style= "background:#79B8D6">후기 많은 순</button>
			</a>
			<a href="javascript:goNextFilter('minReview','${condition}')">
				<button style= "background:#79B8D6">후기 적은 순</button>
			</a>
			<a href="javascript:goNextFilter('maxPrice','${condition}')">
				<button style= "background:#79B8D6">높은 가격 순</button>
			</a>
			<a href="javascript:goNextFilter('minPrice','${condition}')">
				<button style= "background:#79B8D6">낮은 가격 순</button>
			</a>
			<a href="javascript:goNextFilter('maxStar','${condition}')">
				<button style= "background:#79B8D6">별점 높은 순</button>
			</a>
			<a href="javascript:goNextFilter('minStar','${condition}')">
				<button style= "background:#79B8D6">별점 낮은 순</button>
			</a>
		</div>
		
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2f24176f9573eaaf0fc762f8cebc19f7&libraries=services"></script>
<div id="map" style="width:100%;height:350px;"></div>	
<script>
	const addrs = new Array();
		<c:forEach var="addr" items="${addrs}" varStatus="listdx">
			addrs.push("${addr}");
		</c:forEach>
	const names = new Array();
		<c:forEach var="name" items="${names}" varStatus="listdx">
			names.push("${name}");
		</c:forEach>
	
	const h_num = new Array();
		<c:forEach var="hdto" items="${hotelList}" varStatus="listdx">
			h_num.push("${hdto.h_num}");
		</c:forEach>
		
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	   mapOption = {
	       center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	       level: 10 // 지도의 확대 레벨
	   };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	function test(addr, name, h_num){
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(addr, function(result, status) {
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>'
		        });
		        infowindow.open(map, marker);
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
			}
			
		    kakao.maps.event.addListener(marker, 'click', function() {
			// 마커 위에 인포윈도우를 표시합니다
				document.f_searchOk.action = "display_hotelContent?h_num="+h_num;
				document.f_searchOk.submit();
			});
		})
	};    
	
	for(let i=0; i<names.length; i++){
		test(addrs[i], names[i], h_num[i]);
		
	}
</script>
		
		<c:forEach var="hdto" items="${hotelList}">
		<div class="column review border-bottom">	
			<div class="row">
				<a href="javascript:goHotelContent('${hdto.h_num}')"><img class="picture" src="resources/images/hotel/${hdto.h_image1}"/></a>
				<div class="flex column">
					<span>
					<label>
						<i class="fas fa-star"></i>
						<c:forEach var="average" items="${averageReview}">
							<c:if test="${hdto.h_num eq average.key}">
								${average.value}/5점
							</c:if>
						</c:forEach>
						<c:forEach var="review" items="${countReview}">
							<c:if test="${hdto.h_num eq review.key}">
								후기 수(${review.value})
							</c:if>
						</c:forEach>
					</label>
					</span>
					<span>
						<label>${hdto.h_name}</label>
						<label>${hdto.h_grade}성급</label>
					</span>
					<span>
						${fn:replace(hdto.h_address, '@', ' ')}
					</span>
				</div>
				<div>
					<span>
					<i class="fas fa-share-alt-square"></i>
					<!-- wishList가 체크된 방은 검은하트, 체크안된방은 빈 하트로 표시 -->
					<!-- 비회원 표시 -->
					<c:if test="${empty loginOkBean}">
						<!-- 로그인 화면으로 보내거나, 이전페이지로 보내면 될듯 -->
						<a href="user_needLogin"><i class="far fa-heart"></i></a>
					</c:if>
					<!-- 로그인 회원 표시 -->
					<!-- 누를때마다 이벤트 발생하게 해야함 -->
					<c:if test="${not empty loginOkBean}">
						<c:if test="${hdto.wishList >= '1'}">
							<a href="javascript:wishCheck1('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="fas fa-heart"></i></a>
						</c:if>
						<c:if test="${hdto.wishList eq '0'}">
							<a href="javascript:wishCheck2('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="far fa-heart"></i></a>
						</c:if>
					</c:if>
					
					</span>	
				</div> 
			</div>
		</div>	
		</c:forEach>
	</div>
<%@ include file="../bottom.jsp"%>