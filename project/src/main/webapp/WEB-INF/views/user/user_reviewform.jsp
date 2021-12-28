<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="user_myPage.jsp" %>
	<h3>리뷰게시판</h3>
	<table border="0">
		<tr>
			<th>닉네임</th>
			<td>review_nickname</td>
		</tr>
		<tr>
			<th>방타입</th>
			<td>review_roomtype</td>
		</tr>
		<tr>
			<th>사진추가</th>
			<td><input type="file" name="picture"></td>
		</tr>
		<tr>
			<th>별점</th>
			<td>
				<select>
					<option>☆</option>
					<option>☆☆</option>
					<option>☆☆☆</option>
					<option>☆☆☆☆</option>
					<option>☆☆☆☆☆</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>리뷰작성</th>
			<td><textarea></textarea></td>
		</tr>
		<tr >
			<th colspan="2"><input type="submit" value="작성완료"></th>
		</tr>
		</table>
<%@ include file="../bottom.jsp" %>
		
