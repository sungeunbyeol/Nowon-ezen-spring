<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
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

<div align="center"><font size="3">리뷰 게시판</font></div><br>	
<div class="row align-center justify-between border-bottom" style="width:1000px; height: 80px; margin-right:auto; margin-left:auto;">
	<div class="row align-center">
		<i class="fas fa-star fa-2x" style="margin-right: 5px;"></i>${starAverage}/5
	</div>
	<span>총리뷰건수(${reviewCount}개)</span>
</div>
<div class="row align-center justify-between" style="width:1000px; height: 40px;margin-top: 10px; margin-right:auto; margin-left:auto;">
	<SELECT NAME="review" SIZE="1">
		<OPTION VALUE="recently">최근 작성순
		<OPTION VALUE="HighStar">별점 높은순
		<OPTION VALUE="LowStar">별점 낮은순
	</SELECT>
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
			<div class="flex">
				${review.review_contents}<br>
				<c:if test="${not empty review.review_image}">
					<img class="picture" src="resources/images/review/${review.review_image}"/>
				</c:if>
			</div>
		</div>
	</div>	
	</c:forEach>
</div>
</div>				
<%@ include file="../bottom.jsp" %>