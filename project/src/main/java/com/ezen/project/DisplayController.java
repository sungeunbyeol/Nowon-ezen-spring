package com.ezen.project;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.service.DisplayHotelMapper;

@Controller
public class DisplayController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	
//	호텔 검색후 HotelDTO리스트 반환 필터
	@RequestMapping("/display_hotelSearchOk")
	public String hotelSearchOk(HttpServletRequest req, @RequestParam Map<String,String> params) {
//		params로 검색어, 인원수, 체크인, 체크아웃을 받아 sql에서 조건으로 써야할듯(미완성)
		
//		main.jsp검색창에서 설정한 체크인/아웃 날짜들을 session에 넣어둠(user_bookWrite.jsp에서 사용) 
		HttpSession session = req.getSession();
		session.setAttribute("indate", params.get("indate"));
		session.setAttribute("outdate", params.get("outdate"));
		session.setAttribute("inwon", params.get("inwon"));
		
//		아래 메소드 실행
		searchResult(req, params);
		
		return "display/display_hotelSearchOk";
	}
	
	protected void searchResult(HttpServletRequest req, Map<String, String> params) {
		
//			검색창에서 검색했을시 true
		if(params.get("condition") != null) {
			
//			DB에서 condition에 맞는 모든 HotelDTO를 꺼내옴
			List<HotelDTO> hotelList = displayHotelMapper.hotelLocation(params.get("condition"));
			
//			꺼내온 HotelDTO 리스트들의 후기갯수, 별점평균 계산후 map에 담아줌
//			Map<호텔고유값, 후기갯수>
			Map<Integer, Integer> countReview = displayHotelMapper.countReview(hotelList);
//			Map<호텔고유값, 별점평균>
			Map<Integer, Double> averageReview = displayHotelMapper.averageReview(hotelList);
			
//			새로고침하거나 다른페이지를 다녀와도, 마지막 검색값이 그대로 남을수 있도록 session에 hotelList등록
			HttpSession session = req.getSession();
			session.setAttribute("hotelList", hotelList);
			
			LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		try{
			int u_num = loginInfo.getU_num();
			
//		회원고유값으로 호텔이 wishList에 등록되어있는지 아닌지 확인(등록 1, 미등록 0)
			for(HotelDTO hdto : hotelList) {
				hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
			}
		}catch(Exception e) {};
			
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", averageReview);
		}
	}
	
	
//	h_num으로 호텔 상세정보 찾기
	@RequestMapping("/display_hotelContent")
	public String hotelContent(HttpServletRequest req, @RequestParam int h_num) {
//		호텔 리뷰 갯수
		int reviewCount = displayHotelMapper.reviewCount(h_num);
		
//		호텔 별점 평균
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//소수 1번째 자리까지 표기
		
//		방 타입별 정보
		List<RoomDTO> twinRoom = displayHotelMapper.typeRoomList(h_num, "twin");
		List<RoomDTO> doubleRoom = displayHotelMapper.typeRoomList(h_num, "double");
		List<RoomDTO> deluxeRoom = displayHotelMapper.typeRoomList(h_num, "deluxe");
		
//		위시리스트 체크
		HttpSession session = req.getSession();
		LoginOkBean loginOkBean = (LoginOkBean)session.getAttribute("loginOkBean");
		List<HotelDTO> hotelList = (List<HotelDTO>)session.getAttribute("hotelList");
		try{
			//회원로그인시 u_num으로 확인
			int u_num = loginOkBean.getU_num();
			for(HotelDTO hdto : hotelList) {
				if(hdto.getH_num() == h_num) {
					hdto.setWishList(displayHotelMapper.isWishCheck(h_num, u_num));
					req.setAttribute("hdto", hdto);
				}
			}
		
		}catch(Exception e) {
//			비회원은 u_num에서 error발생 위시리스트 체크 필요없음
			HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
			req.setAttribute("hdto", hdto);
		};
		
		
//		호텔에 대한 리뷰 리스트
		List<ReviewDTO> reviewList = displayHotelMapper.reviewList(h_num);
		
		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("starAverage", starAverage);
		req.setAttribute("twinRoom", twinRoom);
		req.setAttribute("doubleRoom", doubleRoom);
		req.setAttribute("deluxeRoom", deluxeRoom);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("loginOkBean", loginOkBean);
		
		return "display/display_hotelContent";
	}
	
	
//	h_num과 room_num에 일치하는 결과 찾기
	@RequestMapping("/display_roomContent")
	public String roomContent(HttpServletRequest req, @RequestParam int room_num, int h_num) {
//		호텔정보
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoomDetail(room_num, h_num); //룸번호 추가
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		
//		호텔기본정보
//		@구분자로 사항들을 나눠서 배열에 담아줌 -> jsp에서 배열 하나씩 출력+줄갱
		String[] hotelInfo = hdto.getH_info().split("@");
		String[] hotelNotice = hdto.getH_notice().split("@");
		req.setAttribute("hotelInfo", hotelInfo);
		req.setAttribute("hotelNotice", hotelNotice);
		
//		객실수량 파악하여 roomCount가 0이면 [매진] 표시
		Map<String, String> params = new Hashtable<String, String>();
		String rn = String.valueOf(room_num);
		String hn = String.valueOf(h_num);
		params.put("room_num",rn);
		params.put("h_num",hn);
		int roomCount = displayHotelMapper.roomCountCheck(params);
		req.setAttribute("roomCount", roomCount);
		
		return "display/display_roomContent";
	}
}
