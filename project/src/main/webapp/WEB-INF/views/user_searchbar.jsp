<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		$("#plus").click(function(){
 			var inwon = $("#inwon").val();
 			var inwon2 = parseInt(inwon) + 1;
 			if(inwon2>6){
 				alert("6명 이상은 입력할 수 없습니다.");
 				e.stopPropagation();
 				e.preventDefault();
 			}else{
 				$("#inwon").attr('value',inwon2);
 			}
 		});
 		$("#minus").click(function(e){
 			var inwon = $("#inwon").val();
 			var inwon2 = parseInt(inwon) - 1;
 			if(inwon2<2){
 				alert("2명 이하는 입력할 수 없습니다.");
 				e.stopPropagation();
 				e.preventDefault();
 			}else{
 				$("#inwon").attr('value',inwon2);
 			}
 		});
 		$("#mForm").submit(function(){
 			var indate2 = $("#indate1").val();
 			var outdate2 = $("#outdate1").val();
 			
 			var date1 = indate2.split('-');
 			var date2 = outdate2.split('-');
 			var in_date = new Date(date1[0],date1[1],date1[2]);
 			var out_date = new Date(date2[0],date2[1],date2[2]);
 			
 			if(in_date.getTime() > out_date.getTime()){
 				alert("체크인아웃 날짜보다 체크인 날짜가 먼저여야 합니다");
 				return false;
 			}
 		});
 	});
 </script>
			<table align = "center" width = "100%" height = "200" bgcolor="pink">
				<tr>
					<td>
						<table align ="center" width = "45%" height = "40">
							<tr>
								<td colspan="7"><h3>호스트 여러분들이 있기에 가능합니다</h3></td>
							</tr>
							<tr>
								<td colspan="7"><h3>마음에 드는 숙소나 맛집을 찾아볼까요?</h3></td>
							</tr>
							<br>
							<tr>
							<form name="f" action="display_hotelSearchOk" method="post" id="mForm">
								<tr align="center">
								 <br>
								<td rowspan="3">
									<input type="text" name="condition" placeholder="대잔치 검색" size = 25>
								</td>
								<td width="20">
									체크인 
									<input type="date" id="indate1" name="indate" value="${sessionScope.indate}">
								</td>
								<td width="20">
									체크아웃
									<input type="date" id="outdate1" name="outdate" value="${sessionScope.outdate}">
								</td>
								<td>
								인원<br>
									<input type="button" name="minus" id="minus" value="-">
									<c:if test="${empty sessionScope.inwon}">
										<input type="text" name="inwon" id="inwon" value="2" size="1" readonly>
									</c:if>
									<c:if test="${not empty sessionScope.inwon}">
										<input type="text" name="inwon" id="inwon" size="1" value="${sessionScope.inwon}" readonly>
									</c:if>
								
									<input type="button" name="plus" id="plus" value="+">
								</td>
								<td rowspan="3">
								 <br>
									<input type="submit" value="검색">
								</td>	
								</tr>
									<!-- <ul>
										<li class="revBt">검색</li>		호텔 검색결과 화면으로
										<li class="revBt2">+</li>		페이지 동일, 숫자만 올려줌
										<li class="revBt2">2</li>		초기값 2
										<li class="revBt2">-</li>		페이지 동일, 숫자만 내려줌
										<li class="revBt">인원</li>		
										<li class="revBt">체크아웃</li>	캘린더에서 선택시 string값 반환
										<li class="revBt">체크인</li>		캘린더에서 선택시 string값 반환
									</ul> -->
								
								</form>
							</tr>
						</table>
					</td>
				</tr>	
			</table>