<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- ICon만들때 적어줘야함! -->
<link rel="stylesheet" href="resources/style.css"/>
<form name="f_bookDetail" method="post" action="user_bookCancel_ok">
	<div style="width: 400px; margin: 0 auto;">
		<div class="row justify-center align-center border-bottom" style="height: 80px;">
			결제내역
		</div>
		
		<div class="payment-info">
			<div>
				<label>결제일시(예약날짜)</label>
				<label>(book_joindate)</label>
			</div>
			
			
			<div>
				<label>상품가격</label>
				<label>(room_price)</label>
			</div>
			
			<div>
				<label>사용포인트</label>
				<label>(u_point)</label>
			</div>
			
			<div>
				<label>총 결제금액</label>
				<label>(book_totalprice)</label>
			</div>
			
			<div class="border-bottom">
				<label>결제수단</label>
				<label>(book_payment)</label>
			</div>
			
			<div>
				<label>포인트 환급</label>
				<label>(u_point)</label>
			</div>
			
			<div>
				<label>환불방법</label>
				<label>(book_payment)</label>
			</div>
			
			<div class="border-bottom">
				<label>최종환불금액</label>
				<label>(book_totalprice)-(u_point)</label>
			</div>
		</div>
		<div class="row justify-center button-actions">
			<button type="button" style="background:#8797D4;">돌아가기</button>
			<button type="submit" style="background:#F5D19C;">예약취소</button>
		</div>
	</div>
</form>
<%@ include file="../bottom.jsp" %>