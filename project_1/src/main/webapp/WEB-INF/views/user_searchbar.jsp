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
			var in_date = new Date(indate2);
			var date2 = outdate2.split('-');
			var out_date = new Date(outdate2);
			
   			var date = new Date();
   			
	   		if(indate2 != ''){
	       		if(outdate2 != ''){
		    		if(date.getDate() <= in_date.getDate()){
		    			if(in_date.getDate() > out_date.getDate()){
		     				alert('체크인아웃 날짜보다 체크인 날짜가 먼저여야 합니다');
		     				return false;
		     			}
		    		}else {
		    			alert('지난 날짜는 선택 할 수 없습니다.');
		    			return false;
		    		}
	       		}else{
	       			alert('체크아웃 날짜를 지정해주세요');
	       			return false;
	       		}
	       	}else{
	       		alert('체크인 날짜를 지정해주세요');
	       		return false;
	       	}
		});
	});
</script>
<form name="f" action="display_hotelSearchOk" method="post" id="mForm">
<table align="center" width="100%" height="200" bgcolor="pink">
	<tr>
		<td>
			<table align="center" width="45%" height="40">
				<tr>
					<td colspan="7"><h3>호스트 여러분들이 있기에 가능합니다</h3></td>
				</tr>
				<tr>
					<td colspan="7"><h3>마음에 드는 숙소나 맛집을 찾아볼까요?</h3></td>
				</tr>
				<tr align="center">
					<td rowspan="3">
						지역 혹은 숙소 입력<br>
						<input type="text" name="condition" placeholder="대잔치 검색" size = "25">
					</td>
					<td width="20">
						체크인 
						<input type="date" id="indate1" name="indate" value="${indate}">
					</td>
					<td width="20">
						체크아웃
						<input type="date" id="outdate1" name="outdate" value="${outdate}">
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
			</table>
		</td>
	</tr>
</table>
</form>