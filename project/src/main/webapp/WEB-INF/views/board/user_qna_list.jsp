<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../user/user_myPage.jsp" %>
<h3>Q&A 게시판</h3>
<div align="center"><mark>일반회원 자신이 쓴 글만 보이기</mark></div>
<form name="f_user_qna" action="user_qna_writeform" method="post">
<table align="center" width="400">
	<tr>
		<th align="left" colspan="2">닉네임(u_nickname)</th>
		<th align="right"><input type="submit" value="문의하기" ></th>
	</tr>
	<tr>
		<td>제목(uqa_subject)</td>
		<td>	내용(uqa_contents)</td>
		<td>작성일(uqa_joindate)</td>		
	</tr>
	<tr>
		<td colspan="3">
			<!-- 문의 내용이 없다면 -->
			<textarea style="width:500px;">문의하신 내용이 없습니다. (uqa_contents)</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right"><input type="reset" value="삭제" ></td>
	</tr>
</table>
</form>
<%@ include file="../bottom.jsp" %>