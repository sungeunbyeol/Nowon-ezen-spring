<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- ICon만들때 적어줘야함! -->
<link rel="stylesheet" href="resources/LJWstyle.css"/>

<script> 
	$(document).ready(function() { 
	$("input:checkbox").on('click', function() { 
		if ( $(this).prop('checked') ) { 
			$(this).parent().addClass("selected"); 
		} else { 
			$(this).parent().removeClass("selected"); 
		} 
		}); 
	}); </script>

<!-- css 적용  -->
<font align = "center" fontsize = "3">TOP</font><br>


<!-- margin : 내가 정한만큼(px) 안쪽에 빈 공간이 생김. 
padding : 내가 띄워주고 싶을때 사용 (px)
margin이든 padding이든 4가지의 픽셀(위 오른쪽 아래 왼쪾) 을 정해줘야하는데
auto를 해주면 4가지 방향이 모두 일정해진다.  
	 -->		

<div class="row align-center justify-between border-bottom" style="height: 80px;">
	<div class="row align-center">
		<i class="fas fa-star fa-2x" style="margin-right: 5px;"></i>${starAverage}/5
	</div>
	<span>총리뷰건수(${reviewCount}개)</span>
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
		<form name="check" action="/review">
			<input type="checkbox" name="photoOnly"> 포토 후기만
		</form>
			 </span>
</div>
<div>
	<c:forEach var="review" items="${reviewList}">
	<!-- 리뷰 하나 시작 -->
	<div class="column review border-bottom">
		<span>
			<i class="fas fa-star"></i>${review.review_star}
			<!-- review.review_nickname수정 -->
			<label>${review.review_nickname}</label>
			<!-- review,review_roomtype수정 -->
			<label>${review.review_roomtype}</label>
			<label>${review.review_joindate} 작성일</label>
		</span>
		<div class="row">
		<!-- 사진 있을 때 -->
			<div class="flex">
				${review.review_contents}<br>
				<c:if test="${not empty review.review_image}">
										<!-- 경로 수정 -->
					<img class="picture" src="/${review.review_image}"/>
				</c:if>
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
						
<%@ include file="../bottom.jsp" %>