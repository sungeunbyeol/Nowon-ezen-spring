package com.ezen.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HotelController {
	
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
	public String review() {
		return "board/review";
	}
}
