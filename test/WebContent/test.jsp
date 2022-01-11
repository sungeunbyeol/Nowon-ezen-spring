<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>다음 지도 API</title>
</head>
<body>
   <div id="map" style="width:1000px;height:500px;"></div>

   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=14a8af04d22786cb1c5094296ec7e481"></script>
   <script>
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
          mapOption = {
              center: new kakao.maps.LatLng(37.55997, 126.98078), // 지도의 중심좌표
              level: 4, // 지도의 확대 레벨
              mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
          }; 

      // 지도를 생성한다 
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      // 지도 타입 변경 컨트롤을 생성한다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);   

      // 지도에 마커를 생성하고 표시한다
      var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(37.55997, 126.98078), // 마커의 좌표
          draggable : true, // 마커를 드래그 가능하도록 설정한다
          map: map // 마커를 표시할 지도 객체
      });

   </script>
</body>
</html>