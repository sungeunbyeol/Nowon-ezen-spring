<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="user_top2.jsp" %>
<%@ include file="user_myPage.jsp" %>
	<td>
		<table align="center" valign="top" width="90%" border="0">
			<caption><b>예약내역</b></caption>
			<tr>
				<td rowspan="4" width = "100">호텔사진<br>(누르면 상세보기로 이동)<br>(h_image1)</td>
			</tr>
			<tr>
				<td>체크인(book_indate)</td>
				<td>체크아웃(book_outdate)</td>
			</tr>
			<tr>
				<td>예약번호(book_num)</td>
				<td>호텔이름(h_name)</td>
			</tr>
			<tr>
				<td>?박</td>
				<td>예약날짜(book_joindate결제일)</td>
				<td align = "right"><input type="button" value="예약취소" onclick="window.location=''"></td>	
			</tr>
		</table>
	</td>
	</tr>
	</div>		
</body>
</html>
<!-- bottom추가 -->