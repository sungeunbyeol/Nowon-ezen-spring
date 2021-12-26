<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
	<div class="align-center" style="width: 80%;">
		<div class="row justify-center align-center" style="border:1px solid grey;">
			<button style= "background:#79B8D6">후기많은순</button>
			<button style= "background:#79B8D6">낮은가격순</button>
			<button style= "background:#79B8D6">높은가격순</button>
			<button style= "background:#79B8D6">위시리스트순</button>
			<button style= "background:#79B8D6">지도</button>
		</div>
			<c:forEach var="star" begin="0" end="15">
				<div class="column review border-bottom">	
					<div class="row">
						<a href="display_hotelContent"><img class="picture" src="resources/spa.jpg"/></a>
						<div class="flex column">
							<span>
							<c:forEach var="star" begin="0" end="4">
							<c:if test="${ star < 3}">
							<i class="fas fa-star"></i>
							</c:if>
							<c:if test="${ star >= 3}">
							<i class="far fa-star"></i>
							</c:if>
							</c:forEach>
							<label> 후기갯수</label>
							</span>
							<span>
							<label>호텔이름(h_name)</label>
							<label>성급(h_grade)</label>
							</span>
							<span>
							주소(h_address)
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
<%@ include file="../bottom.jsp"%>