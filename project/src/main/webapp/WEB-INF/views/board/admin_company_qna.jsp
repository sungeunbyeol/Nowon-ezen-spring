<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin/admin_company_top.jsp" %>
<section>

<article>
	<h3 align="center">기업 Q&A게시판</h3>
	<form align="center">
		<select name="list" size="1">
			<option>회사명(c_name)</option>
			<option>이메일(c_email)</option>
			<option>글제목(cqa_subject)</option>
		</select>
		<input type="text">
		<button>검색</button>
	</form>
	<p>
</article>

<article>
	<div align="center" border="1"><h4 style="margin-top:40px;">기업회원 Q&A 목록</h4></div>
	<div align="center"><mark>여기부터는 기업회원이 직접 쓴 Q&A 목록 불러오기</mark></div>
	<table align="center" border="1" style="margin-bottom:50px;">
		<tr>
			<th>QnA고유번호(cqa_num)</th>
			<th>기업고유번호(c_num)</th>
			<th>회사명(c_name)</th>
			<th>이메일(c_email)</th>
			<th>글제목(cqa_subject)</th>
			<th>작성날짜(cqa_joindate)</th>
		</tr>
	</table>
</article>

<article>
<h4 align="center">아래는 답글 단 후</h4>
	<div align="center" style="padding-right:170px;"><h4>관리자</h4></div>
	<p align="center">내용 : <textarea style="width:260px; height:95px;"></textarea></p>
	<div align="center">
		<button>수정</button>
		<button>삭제</button>
		<button>목록</button>
	</div>
</article>

</section>
<%@ include file="../bottom.jsp" %>