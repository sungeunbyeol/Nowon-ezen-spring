<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<head>
 	<link rel="stylesheet" type="text/css" href="css.css">
	<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>

<a id="kakao-link-btn" href="javascript:sendLink()">
	<button id="kakaoShare">카카오 공유하기</button>
</a>

<script>
Kakao.init('248b81637c1b88ea9f5a633f55340228');
function sendLink() {
    Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: '제목',
          description: '설명',
          imageUrl: '이미지 URL',
          link: {
            mobileWebUrl: '공유할 URL',
            webUrl: '공유할 URL'
          }
        },
        buttons: [
          {
            title: '웹으로 보기',
            link: {
              mobileWebUrl: '공유할 URL',
              webUrl: '공유할 URL'
            }
          },
          {
            title: '앱으로 보기',
            link: {
              mobileWebUrl: '공유할 URL',
              webUrl: '공유할 URL'
            }
          }
        ]
    });
}

</script>
<!-- <script type="text/javascript">
function shareKakao() {
	  // 사용할 앱의 JavaScript 키 설정
	  Kakao.init('248b81637c1b88ea9f5a633f55340228'); 
	  // 카카오링크 버튼 생성
	  Kakao.Link.createDefaultButton({
	    container: '#btnKakao', // 카카오공유버튼ID
	    objectType: 'feed',
	    content: {
	      title: "오잉", // 보여질 제목
	      description: "오잉잉", // 보여질 설명
	      imageUrl: "http://localhost:8080/my/", // 콘텐츠 URL
	      link: {
	         mobileWebUrl: "http://localhost:8080/my/",
	         webUrl: "http://localhost:8080/my/"
	      }
	    }
	  });
	}
</script> -->