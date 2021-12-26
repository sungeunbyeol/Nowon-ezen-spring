<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<div style="width: 80%; margin: 0 auto;">
		<div class="Roomcontent">
			<div class="selectedImage">
				<img src="resources/no1.gif" id="selectedImage">
			</div>
			<div class="row justify-between images" id="roomcontentImages">
				<img src="resources/no1.gif">
				<img src="resources/no2.gif">
				<img src="resources/no3.gif">
			</div>
		</div>
		<div class="row border align-center" style="margin-top: 10px;">
			방&숙박정보
		</div>	
			<div class="row align-center" style="justify-content:flex-end;">
			<input type='button' name='booking' value='예약하기' 
			onclick="location.href='user_bookWriteform'" style="width: 80px;">
			</div>
		<div style="margin-top: 10px;">
			기본정보(h_info)
			<li>기본정보1</li>
			<li>기본정보2</li>
			<li>기본정보3</li>
			<li>기본정보4</li>
			<li>기본정보5</li>			
		</div>
		<div style="margin-top: 20px;">
			예약공지(h_notice)
			<li>예약공지1</li>
			<li>예약공지2</li>
			<li>예약공지3</li>			
		</div>
		<div style="margin-top: 20px;">
			취소규정(직접입력)
			<li>취소규정 1</li>
		</div>
		<div class="row align-center justify-center">
			<div style=" position: fixed; bottom: 0; z-index: 9999;">
        		<i class="fas fa-shopping-cart fa-2x" style="margin-right: 20px;"></i>
        		<i class="fas fa-clipboard-list fa-2x"></i>
    		</div>
		</div>
	</div>
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