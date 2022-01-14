<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

<!-- flex형태로 div담기위해서 사용 
https://studiomeal.com/archives/197 참조
-->
<style>
    .flex-container {
      display: flex;
      justify-content: right;
      align-items: right;
      
      flex-direction: column;    
    }
</style>
<html>
<head> 
<meta charset="UTF-8">
<title>회원관리자페이지 탑</title>
</head>
<body>
<header>
	
</header>  
<nav>   
<div class="flex-container"> 
	<div style="height:0px"><a href="admin_user_list"><i class="fas fa-hotel"></i></a>관리자 로그인중 </div> 
	<div>
	<!-- 아이콘달아서 버튼기능 표현  -->
	<!-- div로 각 버튼이 들어갈 공간만들기 -->
	<!-- i안에 들어가는 내용들은 아이콘에 관련된 내용 class 각 아이콘 설정, fa-3x(크기 설정: 2x,3x,4x....) 하고보니 구려서 바꿀예정--> 
		<div align = "right" style="box-sizing: border-box; margin-right:234px; float:right; width:40%; height:60px;">
			<i onclick="location.href='admin_user_list'" class="far fa-meh-blank fa-3x"></i> 
			<i onclick="location.href='admin_user_blacklist'" class="fas fa-meh-blank fa-3x"></i>
			<i onclick="location.href='list_userQnA?a_level=3'" class="far fa-comment-dots fa-3x"></i>
			<i onclick="location.href='main'" class="fas fa-home fa-3x"></i>
		</div>
	</div>
</div>
</nav>
</body>
</html>