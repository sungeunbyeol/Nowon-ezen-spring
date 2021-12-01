<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- login.jsp-->
<html>
<head>
	<title>로그인</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function checkMember(){
			location.href="memberSsn.do"
		}
		function searchMember(mode){
			window.open("searchLogin.do?mode=" + mode, "search", "width=650, height=400")
		}
		function loginCheck(){
			if (f.id.value==""){
				alert("아이디를 입력해 주세요!!")
				f.id.focus()
				return
			}
			if (f.passwd.value==""){
				alert("비밀번호를 입력해 주세요!!")
				f.passwd.focus()
				return
			}
			document.f.submit()
		}
	</script>
</head>
<body>	
<div align="center">
<br>
<img src="images/bottom.gif" width="570" height="40" border="0" alt="">
<br>
<p>
<img src="images/tm_login.gif" width="100" height="13" border="0" 
	align=center ALT="회원 로그인">
<form name="f" action="login_ok.log" method="post">
	<table width="570" align="center" height="120">
		<tr>
			<td align="right" width="30%">
				<img src="images/id01.gif" 
				width="28" height="11" border="0" alt="아이디">&nbsp;&nbsp;
			</td>
			<td width="40%">
				<input type="text" name="id" tabindex="1">
			</td>
			<td rowspan="2" width="30%" valign="middle">
				<a href="javascript:loginCheck()">
					<img src="images/bt_login.gif" border="0" alt="로그인"  tabindex="3">&nbsp;&nbsp;<br>
				</a>
				<nobr>
					<input type="checkbox" name="saveId">
					<input type="checkbox" name="saveId" checked>
					<font face="굴림" size="2">아이디 기억하기</font>
				</nobr>
			</td>
		</tr>
		<tr>
			<td align="right">
				<img src="images/pwd.gif" 
							width="37" height="11" alt="비밀번호">
			</td>
			<td>
				<input type="password" name="passwd"  tabindex="2">
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="javascript:checkMember()">
					<img src="images/bt_join.gif" width="60" height="22" alt="회원가입">
				</a>	
				<a href="javascript:searchMember('id')">
 					<img src="images/bt_search_id.gif" width="60" height="22" alt="아이디 찾기">
 				</a>	
 				 <a href="javascript:searchMember('pw')">
					<img src="images/bt_search_pw.gif" width="60" height="22" alt="비밀번호 찾기">
				</a>				
			</td>
		</tr>
	</table>
</form> 
</div>
</body>
</html>