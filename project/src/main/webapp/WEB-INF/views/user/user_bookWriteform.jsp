<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- ICon만들때 적어줘야함! -->
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<meta charset="UTF-8">
<form name="f_bookWriteform" method="post" action="user_bookDetail">
	<div style="width: 650px; margin: 0 auto;">
		<div class="row book-detail">
			<div class="first">
				<div class="section">
					<div class="row" style="font-weight: bold; font-size:20px; padding-left: 35px;">예약자 정보</div>
					<div class="row">
						<label>예약자이름(u_name)</label>
						<label><input type="text" /></label>
					</div>
					<div class="row">
						<label>휴대폰번호(u_tel)</label>
						<label><input type="text" /></label>
					</div>
					<div class="row">
						<label>객실 인원</label>
						<label>(room_capacity)</label>
					</div>
					<div class="row">
						<label>추가 인원</label>
						<label><select>
							<option value=""></option>
							<option value="1">1</option>
							<option value="2">2</option>
						</select></label>
					</div>
					<div class="row">
					추가인원이 선택지보다 많을경우 호텔에 문의바랍니다.
					</div>
				</div>
				<div class="section">
					<div class="row">
						<label>구매총액</label>
						<label>(book_totalprice)=(room_price) + (room_extraPrice)*(select value)</label>
					</div>
					<div class="row">
						<label>포인트사용</label>
						<label>(u_point)</label>
					</div>
				</div>
				<div class="section">
					<div class="row" style="font-weight: bold; font-size:20px; padding-left: 35px;">결제수단 선택</div>
					<div class="column align-center gutter-y-10" style="height: auto;">
						<button style="width: 200px; background:#F5C736;">카카오 페이(book_payment)</button>
						
						<button style="width: 200px; background:#4AEB54;">네이버 페이(book_payment)</button>
					</div>
				</div>
			</div>
			
			<div class="second">
				<div class="section">
					<div class="column">
						<label>숙소이름</label>
						<label>(room_name)</label>
					</div>
					<div class="column">
						<label>방타입</label>
						<label>(room_type)</label>
					</div>
					<div class="column">
						<label>체크인</label>
						<label>(book_indate)</label>
					</div>
					<div class="column">
						<label>체크아웃</label>
						<label>(book_outdate)</label>
					</div>
				</div>
				
				<div class="section payment" style="border-top: 0;">
					<div class="column">
						<label>총 결제금액</label>
						<label>(book_totalprice)=(room_price) + (room_extraPrice)*select value</label>
					</div>
					<div class="column">
						<label>포인트적립</label>
						<label>[(book_savepoint)]적립</label>
					</div>
					<div class="row justify-center" style="padding: 15px;">
						<button style="background:#F58B7B; width: 100%; ">결제하기(book_payment)</button>
					</div>					
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="../bottom.jsp" %>