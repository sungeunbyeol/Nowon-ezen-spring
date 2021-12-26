package com.ezen.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {
	
	@RequestMapping("/display_hotelSearchOk")
	public String hotelSearchOk() {
		return "display/display_hotelSearchOk";
	}
	
	@RequestMapping("/display_hotelContent")
	public String hotelContent() {
		return "display/display_hotelContent";
	}
	
	@RequestMapping("/display_roomContent")
	public String roomContent() {
		return "display/display_roomContent";
	}
}
