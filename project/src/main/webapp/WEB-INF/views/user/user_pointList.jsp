<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_myPage.jsp" %>
		<table align="center" valign="top" width="90%" border="0">
			<caption><b>포인트 사용 내역</b></caption>
			<tr bgcolor="yellow">
				<td>상태</td>
				<td>적립포인트</td>
				<td>내용</td>
				<td>누적포인트</td>
				<td>날짜</td>
			</tr>
			<!-- 포인트 dto를 list형태로 아래의 양식에 맞춰 출력(예약일 기준 최근거를 위로)-->
			<c:forEach var="pdto" items="${pointList}">
			<tr>
				<td>${pdto.p_status}</td>
				<td>${pdto.book_savepoint}</td><!-- 상태와 적립포인트는 상황에 따라 빨강,파랑으로 표시 -->
				<td>${pdto.p_contents}</td>
				<td>${pdto.u_point}</td>
				<td>${pdto.book_joindate}</td>
			</tr>
			</c:forEach>
		</table>
	</td>
	</tr>
	</div>		
<%@ include file="../bottom.jsp" %>