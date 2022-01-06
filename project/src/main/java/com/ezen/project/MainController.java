package com.ezen.project;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.project.model.HotelDTO;
import com.ezen.project.service.DisplayHotelMapper;

@Controller
public class MainController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	@RequestMapping("/")
	public String main(HttpServletRequest req) {
		Map<String, Integer> map = displayHotelMapper.hotelList();
		req.setAttribute("map", map);
		return "user_main";
	}
	
	@RequestMapping("/main")
	public String main2(HttpServletRequest req) {
		Map<String, Integer> map = displayHotelMapper.hotelList();
		req.setAttribute("map", map);
		return "user_main";
	}
	
	@RequestMapping("/company_main")
	public String companyMain() {
		return "company/company_main";
	}
	
}
