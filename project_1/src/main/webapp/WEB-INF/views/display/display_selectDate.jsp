<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<title>selectDate</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#checkForm").submit(function(){
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
			   		
			   		var room_code = $("#room_code").val();
					var h_num = $("#h_num").val();
					
			   		opener.location.href='display_roomContent?room_code='+room_code+'&h_num='+h_num+'&indate='+indate2+'&outdate='+outdate2;
			   		window.close();
				});
			});
		</script>
	</head>
	
	<form name="f" id="checkForm">
		<input type="hidden" id="room_code" value="${room_code}">
		<input type="hidden" id="h_num" value="${h_num}">
		
		<table align="center" width="100%" height="200" bgcolor="skyblue">
			<tr>
				<td width="20">
					체크인 
					<input type="date" id="indate1" value="${indate}">
				</td>
				<td width="20">
					체크아웃
					<input type="date" id="outdate1" value="${outdate}">
				</td>
				<td rowspan="2">
					<br>
					<input type="submit" value="검색">
			</tr>
		</table>
	</form>
</html>