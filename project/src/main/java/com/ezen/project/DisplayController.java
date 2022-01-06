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
	
	@RequestMapping("/display_hotelSearchOk")
	public String hotelSearchOk(HttpServletRequest req, @RequestParam Map<String,String> params) {
		//params으로 받을건 search 와 filter 두개
		
		HttpSession session = req.getSession();
		session.setAttribute("indate", params.get("indate"));
		session.setAttribute("outdate", params.get("outdate"));
		
		List<HotelDTO> list = null;
		searchResult(req, params);
		return "display/display_hotelSearchOk";
	}
	
	protected void searchResult(HttpServletRequest req, Map<String, String> params) {
		if(params.get("condition") != null) {
			List<HotelDTO> hotelList = displayHotelMapper.hotelLocation(params.get("condition"));
			Map<Integer, Integer> countReview = displayHotelMapper.countReview(hotelList);
			Map<Integer, Double> averageReview = displayHotelMapper.averageReview(hotelList);
			
			HttpSession session = req.getSession();
			session.setAttribute("hotelList", hotelList);
			LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
		try{
			int u_num = loginInfo.getU_num();
			for(HotelDTO hdto : hotelList) {
				hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
			}
			displayHotelMapper.wishList2(hotelList, u_num);
		}catch(Exception e) {};
			
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", averageReview);
		}
	}
	
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
			int u_num = loginOkBean.getU_num();
			for(HotelDTO hdto : hotelList) {
				if(hdto.getH_num() == h_num) {
					hdto.setWishList(displayHotelMapper.isWishCheck(h_num, u_num));
					req.setAttribute("hdto", hdto);
				}
			}
			displayHotelMapper.wishList2(hotelList, u_num);
		
		}catch(Exception e) {
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
	
	@RequestMapping("/display_roomContent")
	public String roomContent(HttpServletRequest req, @RequestParam int room_num, int h_num) {
//		호텔정보
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoomDetail(room_num, h_num); //룸번호 추가
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		
//		호텔기본정보
		String[] hotelInfo = hdto.getH_info().split("@");
		String[] hotelNotice = hdto.getH_notice().split("@");
		req.setAttribute("hotelInfo", hotelInfo);
		req.setAttribute("hotelNotice", hotelNotice);
		
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
