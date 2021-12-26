<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- ICon만들때 적어줘야함! -->
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<!-- css 적용  -->
	<font align = "center" fontsize = "3">TOP</font><br>
	

			<!-- margin : 내가 정한만큼(px) 안쪽에 빈 공간이 생김. 
			padding : 내가 띄워주고 싶을때 사용 (px)
			margin이든 padding이든 4가지의 픽셀(위 오른쪽 아래 왼쪾) 을 정해줘야하는데
			auto를 해주면 4가지 방향이 모두 일정해진다.  
		  -->		
		
			<div class="row align-center justify-between border-bottom" style="height: 80px;">
				<div class="row align-center">
					<i class="fas fa-star fa-2x" style="margin-right: 5px;"></i>평균/5
				</div>
				<span>총리뷰건수</span>
			</div>
			<div class="row align-center justify-between border-bottom" style="height: 40px;">
				<!-- row, align-center, justify-between을 3가지로 불러옴
				불러올 때 flex를 적용해주기 위해서 css에서 불러오게 한다. justify-between으로 사이 떨어드려준다.  -->
				<SELECT NAME="review" SIZE="1">
    				<OPTION VALUE="recently">최근 작성순
   					<OPTION VALUE="HighStar">별점 높은순
    				<OPTION VALUE="LowStar">별점 낮은순
  				 </SELECT>
  				 <span>
  				 	<input type="radio" value="onlyPhoto"> 포토 후기만
  				 </span>
			</div>
			<div>
				<c:forEach var="star" begin="0" end="40">
				<!-- 리뷰 하나 시작 -->
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
							방이 너무 좋아요(review_content)
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
				<!-- 리뷰 하나 끝! -->		
			</div>