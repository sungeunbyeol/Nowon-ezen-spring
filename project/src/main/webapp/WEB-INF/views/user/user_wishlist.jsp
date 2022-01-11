<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
		function wishCheck3(w_num){
			var child = window.open("wishRelease3?w_num="+w_num, "search","width=10, height=10");
			window.parent.location.reload();
			child.close();
		}
		
	</script>
<%@ include file="../user_top.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>

	<div class="align-center" style="width: 80%;">
		위시리스트
		<div class="row justify-center align-center" style="border:1px solid grey;">
			<button style= "background:#79B8D6">후기많은순</button>
			<button style= "background:#79B8D6">낮은가격순</button>
			<button style= "background:#79B8D6">높은가격순</button>
		</div>
			<c:forEach var="wdto" items="${wishList}">
				<div class="column review border-bottom">	
					<div class="row">
						<a href="display_hotelContent?h_num=${wdto.h_num}">
							<img class="picture" src="resources/images/hotel/${wdto.h_image1}"/>
						</a>
						<div class="flex column">
							<span>
								<label><i class="fas fa-star"></i><fmt:formatNumber value="${wdto.reviewAvg}" pattern="0.0"/>  | 리뷰: ${wdto.reviewCnt}</label>
							</span>
							<span>
							<label>호텔명 : ${wdto.h_name}  </label>
							<label>등급: ${wdto.h_grade}</label>
							</span>
							<span>
							주소: ${wdto.h_address}
							</span>
						</div>
						<div>
							<span>
							<i class="fas fa-share-alt-square"></i>
							<!-- 누를때마다 이벤트 발생하게 해야함 -->
							<c:if test="${not empty sessionScope.loginOkBean}">
								<c:if test="${not empty wdto.h_name}">
									<a href="javascript:wishCheck3('${wdto.w_num}')">
									<i class="fas fa-heart"></i></a>
								</c:if>
							</c:if>
							</span>	
					</div> 
				</div>
			</div>	
		</c:forEach>
	</div>
<%@ include file="../bottom.jsp" %>