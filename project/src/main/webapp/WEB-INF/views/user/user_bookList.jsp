<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_myPage.jsp" %>
<script>
$.ajax({
	type : "post",
	url : "/user_bookList",
	success : function(result){
	    $("#result").html(
	            "상품명:"+result.name+",가격:"+result.price);
	    }
});
</script>	
		<table align="center" valign="top" width="90%" border="1">
		
	

		<caption><b>예약내역</b></caption>
		<c:forEach var="bdto" items="${bookList}">
			<tr>
				<td rowspan="4" width = "100">
					<img src="resources/images/hotel/${bdto.h_image1}" width="160px" height="90px" > 
				</td>
			</tr>
			<tr>
				<td>${bdto.book_indate}</td>
				<td>${bdto.book_outdate}</td>
			</tr>
			<tr>
				<td>${bdto.book_num}</td>
				<td>${bdto.h_name}</td>
				<td align = "right">
				<form name="reviewbutton" method="POST" action="user_reviewform">
				<input type="hidden" name="h_num" value="${bdto.h_num}">
				<input type="hidden" name="room_num" value="${bdto.room_num}">
					<input type="submit" value="리뷰쓰기">
				</form>
				</td>
			</tr>
			<tr>
				<td>${bdto.day}</td>
				<td>${bdto.book_joindate}</td>
				<td align = "right"><input type="button" value="예약취소" onclick="window.location=''"></td>	
			</tr>
			</c:forEach>
		</table>
		<c:if test="${empty bookList}">
		<h3 align="center" style="color:red">예약하신 호텔이 없습니다.</h3>
		</c:if>	
		<c:if test="${rowCount>0}">
		<c:if test="${startPage>pageBlock}">
			<a href="user_bookList?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">			
			<a href="user_bookList?pageNum=${i}">[${i}]</a>			
		</c:forEach>			
		<c:if test="${endPage < pageCount}">
			<a href="user_bookList?pageNum=${startPage+pageBlock}">[다음]</a>			
		</c:if>	
		</c:if>
<%@ include file="../bottom.jsp" %>