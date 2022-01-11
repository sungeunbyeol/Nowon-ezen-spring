<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="user_top.jsp"%>
<%@ include file="user_searchbar.jsp"%>

			<table align = "center" width = "65%" height = "800" bgcolor="skyblue">
				
				<tr align = "left">
					<td>
					<a href="display_hotelSearchOk?condition=seoul&inwon=2">서울<br>
					<img src="resources/main/seoul.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['서울']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=pusan&inwon=2">부산<br>
					<img src="resources/main/pusan.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['부산']}"/>개의 숙소</a>
					</td>
					
				</tr>
				<tr align = "left">
					<td>
					<a href="display_hotelSearchOk?condition=jeju&inwon=2">제주<br>
					<img src="resources/main/jeju.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['제주']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=incheon&inwon=2">인천<br>
					<img src="resources/main/inchon.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['인천']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=sokcho&inwon=2">속초<br>
					<img src="resources/main/sokcho.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['속초']}"/>개의 숙소</a>
					</td>
					
				</tr>
				<tr align = "left">
					<td>
					<a href="display_hotelSearchOk?condition=jeonju&inwon=2">전주<br>
					<img src="resources/main/jeonju.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['전주']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=yeosu&inwon=2">여수<br>
					<img src="resources/main/yeosu.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['여수']}"/>개의 숙소</a>
					</td>
					
				</tr>
				<tr align = "left">
					<td>
					<a href="display_hotelSearchOk?condition=dokdo&inwon=2">독도<br>
					<img src="resources/main/dokdo.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['독도']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=gyeongju&inwon=2">경주<br>
					<img src="resources/main/gyeongju.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['경주']}"/>개의 숙소</a>
					</td>
					<td>
					<a href="display_hotelSearchOk?condition=pyeongyang&inwon=2">평양<br>
					<img src="resources/main/pyeongyang.jfif" style="max_width:auto; height:auto;"><br>
					<c:out value="${map['평양']}"/>개의 숙소</a>
					</td>
					
				</tr>
			</table>
			
		</div>
	</body>
<%@ include file="bottom.jsp"%>
</html>