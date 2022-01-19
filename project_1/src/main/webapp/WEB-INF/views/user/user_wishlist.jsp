<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function sleep(ms) {
	  const wakeUpTime = Date.now() + ms;
	  while (Date.now() < wakeUpTime) {}
	}
	function wishCheck3(w_num){
		var child = window.open("wishRelease3?w_num="+w_num, "search","width=10, height=10");
		window.parent.location.reload();
		sleep(300);
		child.close();
	}
</script>
<%@ include file="../user_top.jsp"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>

	<div class="align-center" style="width: 100%;">
		위시리스트
		<c:if test="${empty wishList}">
			<div align="center">위시리스트가 비어있습니다.</div>
		</c:if>
		<c:if test="${not empty wishList}">
			<c:forEach var="wdto" items="${wishList}">
				<div class="column review border-bottom">	
					<div class="row">
						<img class="picture" src="resources/images/hotel/${wdto.h_image1}"/>
						<div class="flex column">
							<span>
								<label><i class="fas fa-star"></i><fmt:formatNumber value="${wdto.reviewAvg}" pattern="0.0"/>  | 리뷰: ${wdto.reviewCnt}</label>
							</span>
							<span>
								<label>호텔명 : ${wdto.h_name}  </label>
								<label>등급: ${wdto.h_grade}</label>
							</span>
							<span>
								주소: ${fn:replace(wdto.h_address, '@', ' ')}
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
	</c:if>
	</div>
<%@ include file="../bottom.jsp" %>