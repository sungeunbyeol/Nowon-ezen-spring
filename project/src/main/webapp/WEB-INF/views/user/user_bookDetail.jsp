<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<form name="f_bookDetail" method="post" action="user_bookCancel">
	<div style="width: 400px; margin: 0 auto;">
		<div class="row justify-center align-center border-bottom" style="height: 80px;">
			예약 상세 내역 
		</div>
		<div class="column review border-bottom">	
					<div class="row">
						<img class="picture" src="resources/spa.jpg"/>
						<div class="flex column">
							<span>
							<label>호텔이름(h_name)</label>
							<label>성급(h_grade)</label>
							</span>
							<span>
							주소(h_address)
							</span>
						</div>
					</div>
				</div>
		<div>
			<div>
				<label>체크인</label>
				<label>(book_indate)</label>
			</div>			
			<div class="border-bottom">
				<label>체크아웃</label>
				<label>(book_outdate)</label>
			</div>
			<div>
				<label>예약번호</label>
				<label>(book_num)</label>
			</div>
			<div class="border-bottom">
				<label>예약자이름</label>
				<label>(u_name)</label>
			</div>
			<div class="border-bottom">
				<label>추가 인원</label>
				<label>(select value)</label>
			</div>
			<div class="border-bottom">
				<label>총결제금액</label>
				<label>(book_totalprice)</label>
			</div>
			<div>
				<label>전화문의</label>
				<label>(h_tel)</label>
			</div>
		<div class="row justify-center button-actions">
			<button type="button" style="background:#8797D4;">돌아가기</button>
			<button type="submit" style="background:#F5D19C;">예약취소</button>
		</div>
	</div>
	</div>
</form>
<%@ include file="../bottom.jsp" %>