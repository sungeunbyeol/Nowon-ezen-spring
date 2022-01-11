<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_myPage.jsp" %>

<!-- <strong>내 정보 관리</strong>
	<div align="center"> -->
<script type="text/javascript">
	function edit(){
		alert("추후 업데이트 예정입니다!");
	}
</script>
		<table align="center" valign="top" border="0">
			<caption><b>내정보관리</b></caption>
			<tr>
				<td>
					<form name="info" method="post" action="user_infoChange">
						이메일<input type="text" name="email" value="${loginOkBean.u_email}" readonly><br>
						닉네임 <input type="text" name="nickname" value="${loginOkBean.u_nickname}">
						<input type="submit" value="수정">
					</form>
						휴대폰 번호 <input type="text" name="tel" value="${loginOkBean.u_tel}">
						<button onclick="javascript:edit()">수정</button>
				</td>
			</tr>
			<tr>
				<td>
					<br>
				</td>
			</tr>
			<tr>
				<td>
					<br>
				</td>
			</tr><tr>
				<td>
					<br>
				</td>
			</tr>
				<td>
					<a href="user_password_edit">비밀번호 변경</a><!-- 비밀번호 재설정 페이지로 이동 -->
					<a href="user_logout">로그아웃</a><!-- 세션만료시키고 회원메인 페이지로 -->
					<a href="user_delete">회원탈퇴</a><!-- true값 반환되면 user_personal에서 삭제 -->
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