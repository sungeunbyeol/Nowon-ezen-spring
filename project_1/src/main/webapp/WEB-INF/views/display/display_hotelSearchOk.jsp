<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../user_top.jsp" %>
<%@ include file="../user_searchbar.jsp"%>
<script type="text/javascript">
	function sleep(ms) {
	  const wakeUpTime = Date.now() + ms;
	  while (Date.now() < wakeUpTime) {}
	}
	function wishCheck1(h_num, u_num, location){
		var child = window.open("wishRelease?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
		window.parent.location.reload();
		sleep(300);
		child.close();
	}
	function wishCheck2(h_num, u_num, location){
		var child = window.open("wishCheck?h_num="+h_num+"&u_num="+u_num+"&location="+location, "search", "width=10, height=10");
		window.parent.location.reload();
		sleep(300);
		child.close();
	}
</script>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
<link rel="stylesheet" href="resources/LJWstyle.css"/>
	<div class="align-center" style="width: 80%;">
		<div class="row justify-center align-center" style="border:1px solid grey;">
			<a href="display_hotelSearchOk?filter=maxReview&condition=${condition}">
				<button style= "background:#79B8D6">후기 많은 순</button>
			</a>
			<a href="display_hotelSearchOk?filter=minReview&condition=${condition}">
				<button style= "background:#79B8D6">후기 적은 순</button>
			</a>
			<a href="display_hotelSearchOk?filter=maxPrice&condition=${condition}">
				<button style= "background:#79B8D6">높은 가격 순</button>
			</a>
			<a href="display_hotelSearchOk?filter=minPrice&condition=${condition}">
				<button style= "background:#79B8D6">낮은 가격 순</button>
			</a>
			<button style= "background:#79B8D6">지도</button>
		</div>
			<c:forEach var="hdto" items="${hotelList}">
				<div class="column review border-bottom">	
					<div class="row">
						<a href="goNext()"><img class="picture" src="resources/images/hotel/${hdto.h_image1}"/></a>
						<div class="flex column">
							<span>
							<label>
								<i class="fas fa-star"></i>
								<c:forEach var="average" items="${averageReview}">
									<c:if test="${hdto.h_num eq average.key}">
										${average.value}/5점
									</c:if>
								</c:forEach>
								<c:forEach var="review" items="${countReview}">
									<c:if test="${hdto.h_num eq review.key}">
										후기 수(${review.value})
									</c:if>
								</c:forEach>
							</label>
							</span>
							<span>
								<label>${hdto.h_name}</label>
								<label>${hdto.h_grade}성급</label>
							</span>
							<span>
								${fn:replace(hdto.h_address, '@', ' ')}
							</span>
						</div>
						<div>
							<span>
							<i class="fas fa-share-alt-square"></i>
							<!-- wishList가 체크된 방은 검은하트, 체크안된방은 빈 하트로 표시 -->
							<!-- 비회원 표시 -->
							<c:if test="${empty loginOkBean}">
								<!-- 로그인 화면으로 보내거나, 이전페이지로 보내면 될듯 -->
								<a href="user_needLogin"><i class="far fa-heart"></i></a>
							</c:if>
							<!-- 로그인 회원 표시 -->
							<!-- 누를때마다 이벤트 발생하게 해야함 -->
							<c:if test="${not empty loginOkBean}">
								<c:if test="${hdto.wishList >= '1'}">
									<a href="javascript:wishCheck1('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="fas fa-heart"></i></a>
								</c:if>
								<c:if test="${hdto.wishList eq '0'}">
									<a href="javascript:wishCheck2('${hdto.h_num}','${loginOkBean.u_num}','${param.location}')"><i class="far fa-heart"></i></a>
								</c:if>
							</c:if>
							
							</span>	
						</div> 
					</div>
				</div>	
				</c:forEach>
	</div>
<%@ include file="../bottom.jsp"%>