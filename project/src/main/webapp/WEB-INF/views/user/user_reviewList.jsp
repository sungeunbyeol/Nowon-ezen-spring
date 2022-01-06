<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_myPage.jsp" %>    

	<table border="1">
		<tr>
			<th>	닉네임</th>
			<th>	별점</th>
			<th>	방타입</th>
			<th>	내용</th>
			<th>	사진</th>
			<th>	작성일</th>
			<th>삭제</th>
		</tr>
	<c:if test="${not empty reviewList}">
		<c:forEach var="rdto" items="${reviewList}">
			<tr>
				<td>${rdto.review_nickname}</td>
				<td>${rdto.review_star}</td>
				<td>${rdto.review_roomtype}</td>
				<td>${rdto.review_contents }</td>
				<td><img src="resources/images/review/${rdto.review_image}" width="160px" height="90px" ></td>
				<td>${rdto.review_joindate}</td>
				<td><a href="deleteReview?review_num=${rdto.review_num}">삭제</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty reviewList}">
		작성하신 리뷰가 없습니다.
	</c:if>
	</table>
	<c:if test="${rowCount>0}">
		<c:if test="${startPage>pageBlock}">
			<a href="user_reviewList?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">			
			<a href="user_reviewList?pageNum=${i}">[${i}]</a>			
		</c:forEach>			
		<c:if test="${endPage < pageCount}">
			<a href="user_reviewList?pageNum=${startPage+pageBlock}">[다음]</a>			
		</c:if>	
	</c:if>
    
<%@ include file="../bottom.jsp" %>