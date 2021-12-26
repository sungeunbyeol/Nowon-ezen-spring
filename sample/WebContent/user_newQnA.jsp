<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>사용자 QnA 작성하기</title>
</head>
<body>
<p align="center" style="margin-bottom:50px">
호텔대잔치 이용 중 불편하신 점을 문의주시면 최대한 빠른 시일내에 답변 드리겠습니다.</p>
<form name="f" action="" method="" align="center">
	카테고리 유형<br>
	<input type="text" name="category" list="category" style="width:400px;">
	<datalist id="category">
		<option value="호텔"></option>
		<option value="포인트"></option>
	</datalist> <br><br>
	문의 유형<br>
	<input type="text" name="question" list="question" style="width:400px;">
	<datalist id="question">
		<option value="예약결제"></option>
		<option value="취소환불"></option>
		<option value="이용문의"></option>
		<option value="회원정보"></option>
		<option value="리뷰"></option>
		<option value="환불신청"></option>
		<option value="기타"></option>
	</datalist> <br> <br>
	이메일<br>
	<input type="text" name="email" style="width:400px;"><br><br>
	문의내용<br>
	<textarea style="width:400px; height:150px;">
	문의하실 내용을 10자 이상 입력해주세요.
	문의하시는 제휴점 이름과 예약정보를 남겨주시면 보다 빠른 상담이 가능합니다.
	문의 내용 작성 시 개인정보가 입력되지 않도록 주의 부탁드립니다.
	</textarea> <br><br>
	<!-- 수정일때는, -->
	<input type="submit" value="수정완료">
	<!-- 신규작성일때는, -->
	<input type="submit" value="작성완료">
</form>
</body>
</html>