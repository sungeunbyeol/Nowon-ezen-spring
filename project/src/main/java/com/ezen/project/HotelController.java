package com.ezen.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.project.model.ReviewDTO;
import com.ezen.project.service.DisplayHotelMapper;

@Controller
public class HotelController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	@RequestMapping("/hotel_main")
	public String hotelMain() {
		return "hotel/hotel_main";
	}
	
	@RequestMapping("/hotel_list")
	public String hotelList() {
		return "hotel/hotel_list";
	}
	
	@RequestMapping("/hotel_input")
	public String hotelInput() {
		return "hotel/hotel_input";
	}
	
	@RequestMapping("/hotel_inputedit")
	public String hotelInputedit() {
		return "hotel/hotel_inputedit";
	}
	
	@RequestMapping("/hotel_detail")
	public String hotelDetail() {
		return "hotel/hotel_detail";
	}
	
	@RequestMapping("/hotel_detailedit")
	public String hotelDetailedit() {
		return "hotel/hotel_detailedit";
	}
	
	@RequestMapping("/room_list")
	public String roomList() {
		return "hotel/room_list";
	}
	
	@RequestMapping("/room_input")
	public String roomInput() {
		return "hotel/room_input";
	}
	
	@RequestMapping("/room_inputedit")
	public String roomInputedit() {
		return "hotel/room_inputedit";
	}
	
	@RequestMapping("/review")
	public String review(HttpServletRequest req, @RequestParam int h_num) {
		
//		ȣ�ڿ� ���� ���� ����Ʈ
		List<ReviewDTO> reviewList = displayHotelMapper.reviewList(h_num);
		
//		ȣ�� ���� ����
		int reviewCount = displayHotelMapper.reviewCount(h_num);
		
//		ȣ�� ���� ���
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("starAverage", starAverage);
		return "board/review";
	}
}
