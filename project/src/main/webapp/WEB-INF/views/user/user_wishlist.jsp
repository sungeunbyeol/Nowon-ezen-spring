<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>

	<div class="align-center" style="width: 80%;">
		위시리스트
		<div class="row justify-center align-center" style="border:1px solid grey;">
			<button style= "background:#79B8D6">후기많은순</button>
			<button style= "background:#79B8D6">낮은가격순</button>
			<button style= "background:#79B8D6">높은가격순</button>
		</div>
			<c:forEach var="wdto" items="${wishList}">
				<div class="column review border-bottom">	
					<div class="row">
						<img class="picture" src="resources/images/hotel/${wdto.h_image1}"/>
						<div class="flex column">
							<span>
								<label>${wdto.reviewStar} (${wdto.reviewAvg})  | 리뷰: ${wdto.reviewCnt}</label>
							</span>
							<span>
							<label>호텔명 : ${wdto.h_name} | </label>
							<label>등급: ${wdto.h_grade}</label>
							</span>
							<span>
							주소: ${wdto.h_address}
							</span>
						</div>
						<div>
							<span>
							<i class="fas fa-share-alt-square"></i>
							<i class="fas fa-heart"></i>
							<i class="far fa-heart"></i>
							</span>	
					</div> 
				</div>
			</div>	
		</c:forEach>
	</div>
<%@ include file="../bottom.jsp" %>