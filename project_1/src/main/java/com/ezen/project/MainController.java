package com.ezen.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.project.service.DisplayHotelMapper;

@Controller
public class MainController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	@RequestMapping("/")
	public String main(HttpServletRequest req) {
//		접속시 초기 indate,outdate 오늘,내일날짜로 초기화
		HttpSession session = req.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String today = sdf.format(time);

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		String tmr = sdf.format(date);
        
		if(session.getAttribute("indate") == null && session.getAttribute("outdate") == null) {
			session.setAttribute("indate", today);
			session.setAttribute("outdate", tmr);
		}
      
//		메인 지역별 숙소 갯수
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
