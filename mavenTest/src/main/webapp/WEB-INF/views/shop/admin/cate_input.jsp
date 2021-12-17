<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- cate_input.jsp -->
<%@ include file="top.jsp"%>
<div align="center">
	<form name="f" action="cate_input_ok.do" method="post">
		<table border="1" width="500" height="150">
			<caption><h3>카테고리등록</h3></caption>
			<tr>
				<th>카테고리 코드</th>
				<td><input type="text" name="code"></td>
			</tr>
			<tr>
				<th>카테고리 이름</th>
				<td><input type="text" name="cname"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="bottom.jsp"%>