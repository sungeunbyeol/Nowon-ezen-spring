<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../user_top.jsp" %>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- ICon만들때 적어줘야함! -->
<link rel="stylesheet" href="resources/LJWstyle.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
        $("#extra").change(function(){
           var extra = $(this).val();
           var extra2 = parseInt(extra);
           var room_price = $("#room_price").val();
           var room_price2 = parseInt(room_price);
           var room_extraprice = $("#room_extraprice").val();
           var room_extraprice2 = parseInt(room_extraprice);
           var total = room_price2 + room_extraprice2 * extra2;
           $("#room_price2").attr( 'value', total);
           //$("#finalPrice").attr('value', result2);
           
           var usePoint = $("#usePoint").val();
           var result2 = total - usePoint;
           var savepoint = result2 * 0.1;
           $("#totalPrice").attr('value', result2);
           $("#finalPrice").attr('value', result2);
           $("#savePoint").attr('value', savepoint);
           
           
        });
        $("#usePoint").change(function(){
            var usePoint = $(this).val();
            var room_price = $("#room_price2").val();
            var result2 = parseInt(room_price) - parseInt(usePoint);
            $("#totalPrice").attr('value', result2);
            $("#finalPrice").attr('value', result2);
            var savepoint = result2 * 0.1;
            $("#savePoint").attr('value', savepoint);
            
         });
        $("#bwform").submit(function(){
        	event.preventDefault();
        	var indate = $("#indate").val();
        	var outdate = $("#outdate").val();
        	var isSubmit = false;
        	var check;
        	var usePoint = $("#usePoint").val();
        	var userPoint = $("#userPoint").val();
        	if(indate != ''){
        		if(outdate != ''){
		        	if( parseInt(userPoint) >= parseInt(usePoint) ){
		        		check = confirm("결제 하시겠습니까?");
		        		
		        		var date1 = indate.split('-');
		     			var date2 = outdate.split('-');
		     			var in_date = new Date(date1[0],date1[1],date1[2]);
		     			var out_date = new Date(date2[0],date2[1],date2[2]);
		     			
		        		if(in_date.getTime() > out_date.getTime()){
		     				alert("체크인아웃 날짜보다 체크인 날짜가 먼저여야 합니다");
		     				return false;
		     			}
		        		if(check) {
		            		this.submit();
		            	}
		        	}else{
		        		isSubmit = false;
		        		alert("포인트가 부족합니다");
		        	}
        		}else{
        			alert('체크아웃 날짜를 지정해주세요');
        		}
        	}else{
        		alert('체크인 날짜를 지정해주세요');
        	}
        });
     });
	
</script>

<meta charset="UTF-8">
<form name="f_bookWriteform" id="bwform" method="post" action="user_bookConfirm">
<input type="hidden" name="h_num" value="${hdto.h_num}">
<input type="hidden" name="u_num" value="${loginOkBean.u_num}">
<input type="hidden" name="room_num" value="${Room.room_num}">
<input type="hidden" id="room_price" value="${Room.room_price}">
<input type="hidden" id="room_extraprice" value="${Room.room_extraprice}">
<input type="hidden" id="userPoint" value="${udto.u_point}">

<input type="hidden" name="room_price" value="${Room.room_price}">
	<div style="width: 650px; margin: 0 auto;">
		<div class="row book-detail">
			<div class="first">
				<div class="section">
					<div class="row" style="font-weight: bold; font-size:20px; padding-left: 35px;">예약자 정보</div>
					<div class="row">
						<label>예약자이름</label>
						<label><input type="text" name="book_name" value="${udto.u_name}"/></label>
					</div>
					<div class="row">
						<label>휴대폰번호</label>
						<label><input type="text" name="book_tel" value="${udto.u_tel}"/></label>
					</div>
					<div class="row">
						<label>객실 인원</label>
						<label>${Room.room_capacity}</label>
					</div>
		<!-- 추가인원, 사용포인트, 총결제금액, 포인트적립칸 모두 jQuery로 수정할것 -->
					<div class="row">
						<label>추가 인원</label>
						<label>
							
							<select name="book_extra" id="extra">
								<option value="0">추가인원</option>
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</label>
					</div>
					<div class="row">
					추가인원이 선택지보다 많을경우 호텔에 문의바랍니다.
					</div>
				</div>
				<div class="section">
					<div class="row">
						<label>객실 가격</label>
						<!-- book_totalprice -->
						<label>
							<input type="text" name="room_price" id="room_price2"
							 value="${Room.room_price}" readonly>원
						</label>
					</div>
					<div class="row">
						<label>가용 포인트</label>
						<label>${udto.u_point}포인트</label>
					</div>
					<div class="row">
						<!-- 사용포인트 controller에서 확인해서 가지고있는 포인트보다 높으면 결제못하게 돌려보내기 -->
						<label>사용 포인트</label>
						<label>
						<input name="inputPoint" value="0" id="usePoint">
						</label>
					</div>
					<div class="row">
						<label>총 결제금액</label>
						<label>
						<input type="text" name="book_totalprice" id="totalPrice" value="${Room.room_price}" readOnly>
						</label>
						<%-- <input type="hidden" name="book_totalprice" value="${(Room.room_price + (Room.room_extraprice * 1)) - udto.u_point}"> --%>
					</div>
					
				</div>
				<div class="section">
					<div class="row" style="font-weight: bold; font-size:20px; padding-left: 35px;">결제수단 선택</div>
					<div class="column align-center gutter-y-10" style="height: auto;">
						<button style="width: 200px; background:#F5C736;">카카오 페이(book_payment)</button>
						<input type="hidden" name="book_payment" value="카카오페이">
						<button style="width: 200px; background:#4AEB54;">네이버 페이(book_payment)</button>
					</div>
				</div>
			</div>
			
			<div class="second">
				<div class="section">
					<div class="column">
						<label>호텔이름</label>
						<label>${hdto.h_name}</label>
					</div>
					<div class="column">
						<label>방타입</label>
						<label>${Room.room_type}</label>
						<input type="hidden" name="book_roomtype" value="${Room.room_type}">
					</div>
					<!-- indate, outdate session에서 받아오는걸로 처리해야할것같음 -->
					<div class="column">
						<label>체크인</label>
						<c:if test="${not empty indate}">
							<label>${indate}</label>
							<input type="hidden" name="indate" value="${indate}">
						</c:if>
						<c:if test="${empty indate}">
							<label><input type="date" name="indate" id="indate"></label>
						</c:if>
						
					</div>
					<div class="column">
						<label>체크아웃</label>
						<c:if test="${not empty outdate}">
							<label>${outdate}</label>
							<input type="hidden" name="outdate" value="${outdate}">
						</c:if>
						<c:if test="${empty indate}">
							<label><input type="date" name="outdate" id="outdate"></label>
						</c:if>
					</div>
				</div>
				
				<div class="section payment" style="border-top: 0;">
					<div class="column">
						<!-- 총 결제금액 수정 끝나면 use_bookCancel로 값 넘기기 -->
						<label>총 결제금액</label>
						<label><input type="text" id="finalPrice" value="${Room.room_price}" readonly></label>
					</div>
					<div class="column">
						<label>포인트적립</label>
						<label>
							<input type="text" id="savePoint" name="book_savepoint" value="${Room.room_price * 0.1}" size="5" readonly>적립
						</label>
					</div>
					<div class="row justify-center" style="padding: 15px;">
						<button style="background:#F58B7B; width: 100%;">결제하기(book_payment)</button>
					</div>					
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="../bottom.jsp" %>