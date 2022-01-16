<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<header>
	<h1>쫘잔로고</h1>
</header>

<c:if test="${empty loginOkBean}">
	<nav>
		<ul>
			<li><a href="joinform">회원가입</a></li>
			<li><a href="loginform">로그인</a></li>
			<li><a href="qnaboard">Q&A</a></li>
			<li>
				<select>
					<option>한국어</option>
					<option>English</option>
					<option>日本語</option>
					<option>中國人</option>
				</select>
			</li>
		</ul>
	</nav>
</c:if>

<c:if test="${not empty loginOkBean}">
	<nav>
		<ul>
			<li><a href="logout">로그아웃</a></li>
			<li><a href="qnaboard">Q&A</a></li>
			<li><a href="mypage">마이페이지</a></li>
			<li>
				<select>
					<option>한국어</option>
					<option>English</option>
					<option>日本語</option>
					<option>中國人</option>
				</select>
			</li>
		</ul>
	</nav>
</c:if>