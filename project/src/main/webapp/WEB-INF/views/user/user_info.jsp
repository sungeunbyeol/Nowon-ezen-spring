<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="user_myPage.jsp" %>

<!-- <strong>내 정보 관리</strong>
	<div align="center"> -->
		<table align="center" valign="top" border="0">
			<caption><b>내정보관리</b></caption>
			<tr>
				<td>
					이메일(u_email) <input type="text" name="email"><br>
					닉네임(u_nickname) <input type="text" name="nickname"><br>
					휴대폰 번호(u_tell) <input type="text" name="tel">
					<input type="submit" value="수정">
					<p>
				</td>
			</tr>
			
			<tr>
				<td>
					<a href="#">비밀번호 변경</a><!-- 비밀번호 재설정 페이지로 이동 -->
					<a href="#">로그아웃</a><!-- 세션만료시키고 회원메인 페이지로 -->
					<a href="#">회원탈퇴</a><!-- true값 반환되면 user_personal에서 삭제 -->
					<!-- script-- >
						var result = confirm("정말로 탈퇴하시겠어요?");
						if(result)
							{
								alert("탈퇴가 완료되었습니다")
							}
					<!--/script-->
				</td>
			</tr>	
		</table>
	</td>
	</tr>
	</div>
	<%@ include file="../bottom.jsp" %>