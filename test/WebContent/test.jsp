<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>���� ���� API</title>
</head>
<body>
   <div id="map" style="width:1000px;height:500px;"></div>

   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=14a8af04d22786cb1c5094296ec7e481"></script>
   <script>
      var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
          mapOption = {
              center: new kakao.maps.LatLng(37.55997, 126.98078), // ������ �߽���ǥ
              level: 4, // ������ Ȯ�� ����
              mapTypeId : kakao.maps.MapTypeId.ROADMAP // ��������
          }; 

      // ������ �����Ѵ� 
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      // ���� Ÿ�� ���� ��Ʈ���� �����Ѵ�
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // ������ ��� ������ ���� Ÿ�� ���� ��Ʈ���� �߰��Ѵ�
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);   

      // ������ ��Ŀ�� �����ϰ� ǥ���Ѵ�
      var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(37.55997, 126.98078), // ��Ŀ�� ��ǥ
          draggable : true, // ��Ŀ�� �巡�� �����ϵ��� �����Ѵ�
          map: map // ��Ŀ�� ǥ���� ���� ��ü
      });

   </script>
</body>
</html>