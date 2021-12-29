<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_myPage.jsp" %>
		<table align="center" valign="top" width="90%" border="1">
		<caption><b>예약내역</b></caption>
		<tr>
		<th rowspan="3">호텔사진</th>
			<th>체크인</th>
			<th>체크아웃</th>
		</tr>
		<tr>
			<th>예약번호</th>
			<th>호텔이름</th>
		</tr>
		<tr>
			<th>?박</th>
			<th>예약날짜</th>
		</tr>
		<c:forEach var="bdto" items="${bookList}">
			<tr>
				<td rowspan="4" width = "100">호텔사진<br>(누르면 상세보기로 이동)<br>${h_image1}</td>
			</tr>
			<tr>
				<td>체크인${bdto.book_indate}</td>
				<td>체크아웃${bdto.book_outdate}</td>
			</tr>
			<tr>
				<td>예약번호${bdto.book_num}</td>
				<td>호텔이름${hdto.h_name}</td>
				<td align = "right">
				<form name="reviewbutton" method="POST" action="user_reviewform">
					<input type="submit" value="리뷰쓰기">
				</form>
				</td>
			</tr>
			<tr>
				<td>?박</td>
				<td>예약날짜${bdto.book_joindate}</td>
				<td align = "right"><input type="button" value="예약취소" onclick="window.location=''"></td>	
			</tr>
			</c:forEach>
		</table>
		<c:if test="${empty bookList}">
		<h3 align="center" style="color:red">예약하신 호텔이 없습니다.</h3>
		</c:if>	
<%@ include file="../bottom.jsp" %>