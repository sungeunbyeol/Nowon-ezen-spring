<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../company/company_myPage.jsp" %>
<h3>Q&A 게시판</h3>
<div align="center"><mark>기업회원 자신이 쓴 글만 보이기</mark></div>
<form name="f_company_qna" action="company_qna_writeform" method="post">
<table align="center" width="500px">
	<tr>
		<th colspan="2" align="left">회사명(c_name)</th>
		<th align="right"><input type="submit" value="문의하기" ></th>
	</tr>
	<tr>
		<td>제목(cqa_subject)</td>
		<td>	내용(cqa_contents)</td>
		<td>작성일(cqa_joindate)</td>		
	</tr>
	<tr>
		<td colspan="3">
			<!-- 문의 내용이 없다면 -->
			<textarea style="width:500px;">문의하신 내용이 없습니다. (cqa_contents)</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right"><input type="reset" value="삭제" ></td>
	</tr>
</table>
<%@ include file="../bottom.jsp" %>