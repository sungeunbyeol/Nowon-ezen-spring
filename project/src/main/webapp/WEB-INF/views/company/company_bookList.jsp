<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="company_myPage.jsp" %>
	<div align ="center">
		<hr color="green" width="300">
			<h2>예약현황</h2>
		<hr color="green" width="300">
		<table width ="100%" class="outline">
			<tr bgcolor="skyblue">
				<td rowspan="2">h_pimage1</td>
				<td align = "center">
					h_name + room_name | book_name | book_totalprice | book_status<br>
					book_num | book_indate ~ book_outdate | u_num | 인원수<br>
				</td>
				<td rowspan="2">
					<form >
					<input type ='button' value='취소'>
					</form> <br>
					<form >
					<input type ='button' value='확정'>
					</form>
				</td>
		</table>
	</div>
<%@ include file="../bottom.jsp" %>