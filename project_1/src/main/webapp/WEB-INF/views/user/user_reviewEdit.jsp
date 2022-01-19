<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="user_myPage.jsp" %>
	<h3 align="center">리뷰수정하기</h3>
	<form name="f" method="post" action="user_reviewEditOk" enctype="multipart/form-data">
	<input input type="hidden" name="review_num" value="${rdto.review_num}">
	<table border="1" align="center">
		<tr>
			<th>닉네임</th>
			<td>${rdto.review_nickname}
			</td>
			
		</tr>
		<tr>
			<th>방타입</th>
			<td>${rdto.review_roomtype}
			</td>
		</tr>
		<tr>
			<th>사진추가</th>
			<td>
				<input type="file" name="newImage">
				<input type="hidden" name="pastImage" value="${rdto.review_image}">
			</td>
			
		</tr>
		<tr>
			<th>별점</th>
			<td>
				<select name="star">
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>리뷰작성</th>
			<td><textarea name="review_contents" rows="13" cols="55">${rdto.review_contents}</textarea>
			</td>
		</tr>
		<tr>
			<th align="center">
				<button type="button" onclick="javascript:history.back()">돌아가기</button>
				<input type="submit" value="수정하기">
			</th>
		</tr>
		</table>
		</form>
<%@ include file="../bottom.jsp" %>
		