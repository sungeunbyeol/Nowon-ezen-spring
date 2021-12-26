<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<div style="width: 80%; margin: 0 auto;">
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
			<i class="fas fa-heart"></i>
			<i class="far fa-heart"></i>
			</span>		
		</div>
		<div>
		성급(h_grade)
		</div>
		<div class="border-bottom">
		호텔명(h_name)
		</div>
		<div>
			<span>
				<c:forEach var="star" begin="0" end="4">
				<c:if test="${ star < 3}">
				<i class="fas fa-star"></i>
				</c:if>
				<c:if test="${ star >= 3}">
				<i class="far fa-star"></i>
				</c:if>
				</c:forEach>
			</span>
		</div> 
		<div>
		후기(몇개)(review....?)
		</div>
		<div class="booking border row">
			<img class="picture" src="resources/spa.jpg"/>
				<div class="flex">
					<a href="display_roomContent">방정보(room...?)</a>
				</div>
		</div>
		<div class="booking border row">
		 호텔위치지도
		</div>
		<div class="booking border row">
		편의시설 정보 (h_info)
		</div>
			<c:forEach var="star" begin="0" end="1">
			<div class="column review border-bottom">
				<span>
					<c:forEach var="star" begin="0" end="4">
					<c:if test="${ star < 3}">
					<i class="fas fa-star"></i>
					</c:if>
					<c:if test="${ star >= 3}">
					<i class="far fa-star"></i>
					</c:if>
					</c:forEach>
					<label>닉네임(u_nickname)</label>
					<label>방타입(room_type)</label>
				</span>
				<div class="row">
				<!-- 사진 있을 때 -->
					<img class="picture" src="resources/spa.jpg"/>
				
					<div class="flex">
						방이 너무 좋아요(review_contents)
					</div>
					<!-- 사진 있을 때  -->
					
				<!-- 사진 없을 때 
					<div style="width: 100%;">
						방이 너무 좋아요(내용)
					</div>
					사진 없을 때 
				-->
				</div>
				
				<!-- 별. fontawsome사이트에 들어가서 찾는거 검색후 복붙 --> 
			</div>	
			</c:forEach>
		<div class="booking border" style = "margin-top: 5px;">
			<a href="review">리뷰더보기(페이지 이동주소)</a>
		</div>
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