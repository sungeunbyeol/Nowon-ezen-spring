<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
 	});
 </script>
 <body>
			<table align = "center" width = "100%" height = "200" bgcolor="pink">
				<tr>
					<td>
						<table align ="center" width = "30%" height = "40" >
							<tr>
								<td colspan="7"><h3>호스트 여러분들이 있기에 가능합니다</h3></td>
							</tr>
							<tr>
								<td colspan="7"><h3>마음에 드는 숙소나 맛집을 찾아볼까요?</h3></td>
							</tr>
							<br>
							<tr>
							<form name="f" action="" method="POST">
								<td rowspan="3">
									<select name="location_select">
										<option>호텔명</option>
										<option>주소</option>
									</select>
								</td>
								<td rowspan="3">
									<input type="text" name="location" placeholder="대잔치 검색" size = 40>
								</td>
								<td rowspan="3">
									<input type="submit" value="검색">
								</td>	
								<td>
									<input type="button" name="minus" id="minus" value="-">
								</td>
								<td>
									<input type="text" name="inwon" id="inwon" value="2" readonly>
								</td>	
								<td>
									<input type="button" name="plus" id="plus" value="+">
								</td>
								<tr>
								<td colspan="3">
									<ul>
										<!-- <li class="revBt">인원</li> -->	
									</ul>
								</td>
								</tr>
								<tr>
								<td>
									체크인 
									<input type="date" id="date">
								</td>
								<td>
									체크아웃
									<input type="date" id="date">
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
</body>