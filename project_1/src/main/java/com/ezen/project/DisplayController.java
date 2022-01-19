package com.ezen.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.ezen.project.service.HotelMapper;

@Controller
public class DisplayController {
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	@Autowired
	HotelMapper hotelMapper;
	
//	ȣ�� �˻��� HotelDTO����Ʈ ��ȯ ����
	@RequestMapping("/display_hotelSearchOk")
	public String hotelSearchOk(HttpServletRequest req, @RequestParam Map<String,String> params) {
//		params�� �˻���, �ο���, üũ��, üũ�ƿ��� �޾� sql���� �������� ����ҵ�(�̿ϼ�)
		List<HotelDTO> hotelList = null;
		
		if(params.get("filter") == null) {
//			�Ʒ� �޼ҵ� ����
			searchResult(req, params);
			req.setAttribute("condition", params.get("condition"));
		}else {
			Map<Integer, Integer> countReview = null;
			List<HotelDTO> newHotelList = null;
			List<Map.Entry<Integer, Integer>> orderedByReviewCount = null;
			switch(params.get("filter")) {
				case "maxPrice":
					hotelList = displayHotelMapper.getHotelListByMaxPrice(params.get("condition"));
					break;
				case "minPrice": 
					hotelList = displayHotelMapper.getHotelListByMinPrice(params.get("condition"));
					break;
				case "maxReview": 
					hotelList = displayHotelMapper.hotelLocation(params.get("condition"));
//					������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//					Map<ȣ�ڰ�����, �ıⰹ��>
					countReview = displayHotelMapper.countReview(hotelList);
//					�ı� ��������� newHotelList��� ���ο� ����Ʈ�� �����سֱ�
					orderedByReviewCount = new LinkedList<>(countReview.entrySet());
					orderedByReviewCount.sort(Map.Entry.comparingByValue());
	         
					newHotelList = new ArrayList<HotelDTO>();
					
					for(Map.Entry<Integer, Integer> entry : orderedByReviewCount){
						int order = entry.getKey();
						for(HotelDTO hdto : hotelList) {
							if(order == hdto.getH_num()) {
								newHotelList.add(hdto);
								break;
							}
						}
					}
					
					Collections.reverse(newHotelList);
					
					hotelList = newHotelList;
					
					break;
				case "minReview": 
					hotelList = displayHotelMapper.hotelLocation(params.get("condition"));
//					������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//					Map<ȣ�ڰ�����, �ıⰹ��>
					countReview = displayHotelMapper.countReview(hotelList);
//					�ı� ��������� newHotelList��� ���ο� ����Ʈ�� �����سֱ�
					orderedByReviewCount = new LinkedList<>(countReview.entrySet());
					orderedByReviewCount.sort(Map.Entry.comparingByValue());
	         
					newHotelList = new ArrayList<HotelDTO>();
					
					for(Map.Entry<Integer, Integer> entry : orderedByReviewCount){
						int order = entry.getKey();
						for(HotelDTO hdto : hotelList) {
							if(order == hdto.getH_num()) {
								newHotelList.add(hdto);
								break;
							}
						}
					}
					
					hotelList = newHotelList;
					
					break;
			}
				         
//			������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//			Map<ȣ�ڰ�����, �ıⰹ��>
			countReview = displayHotelMapper.countReview(hotelList);
//			Map<ȣ�ڰ�����, �������>
			Map<Integer, Double> averageReview = displayHotelMapper.averageReview(hotelList);
         
//			���ΰ�ħ�ϰų� �ٸ��������� �ٳ�͵�, ������ �˻����� �״�� ������ �ֵ��� session�� hotelList���
			HttpSession session = req.getSession();
			session.setAttribute("hotelList", hotelList);
         
			LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
      
			try{
				int u_num = loginInfo.getU_num();
         
//				ȸ������������ ȣ���� wishList�� ��ϵǾ��ִ��� �ƴ��� Ȯ��(��� 1, �̵�� 0)
				for(HotelDTO hdto : hotelList) {
					hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
				}
			}catch(Exception e) {};
         
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", averageReview);
		}
		 
		//		main.jsp�˻�â���� ������ üũ��/�ƿ� ��¥���� session�� �־��(user_bookWrite.jsp���� ���) 
		HttpSession session = req.getSession();
		
		if(params.get("indate") != null && params.get("outdate") != null ) {
			session.setAttribute("indate", params.get("indate"));
			session.setAttribute("outdate", params.get("outdate"));
		}else {
			if((String)session.getAttribute("indate") == null && (String)session.getAttribute("outdate") == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				String today = sdf.format(time);

		        Calendar cal = new GregorianCalendar();
		        cal.add(Calendar.DATE, 1);
		        Date date = cal.getTime();
		        String tmr = sdf.format(date);
						
				session.setAttribute("indate", today);
				session.setAttribute("outdate", tmr);
			}
		}
		session.setAttribute("inwon", params.get("inwon"));
		
		return "display/display_hotelSearchOk";
	}
	
	protected void searchResult(HttpServletRequest req, Map<String, String> params) {
		
//			�˻�â���� �˻������� true
		if(params.get("condition") != null) {
			
//			DB���� condition�� �´� ��� HotelDTO�� ������
			List<HotelDTO> hotelList = displayHotelMapper.hotelLocation(params.get("condition"));
			
//			������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//			Map<ȣ�ڰ�����, �ıⰹ��>
			Map<Integer, Integer> countReview = displayHotelMapper.countReview(hotelList);
//			Map<ȣ�ڰ�����, �������>
			Map<Integer, Double> averageReview = displayHotelMapper.averageReview(hotelList);
			
//			���ΰ�ħ�ϰų� �ٸ��������� �ٳ�͵�, ������ �˻����� �״�� ������ �ֵ��� session�� hotelList���
			HttpSession session = req.getSession();
			session.setAttribute("hotelList", hotelList);
			
			LoginOkBean loginInfo = (LoginOkBean)session.getAttribute("loginOkBean");
			try{
				int u_num = loginInfo.getU_num();
			
//			ȸ������������ ȣ���� wishList�� ��ϵǾ��ִ��� �ƴ��� Ȯ��(��� 1, �̵�� 0)
				for(HotelDTO hdto : hotelList) {
					hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
				}
			}catch(Exception e) {};
			
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", averageReview);
		}
	}
	
	
//	h_num���� ȣ�� ������ ã��
	@RequestMapping("/display_hotelContent")
	public String hotelContent(HttpServletRequest req, @RequestParam int h_num) {
		
//		ȣ�� ���� ����
		int reviewCount = displayHotelMapper.reviewCount(h_num);
		
//		ȣ�� ���� ���
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
//		�� Ÿ�Ժ� ����
		List<RoomDTO> twinRoom = displayHotelMapper.typeRoomList(h_num, "TWIN");
		List<RoomDTO> doubleRoom = displayHotelMapper.typeRoomList(h_num, "DOUBLE");
		List<RoomDTO> deluxeRoom = displayHotelMapper.typeRoomList(h_num, "DELUXE");
		
//		���ø���Ʈ üũ
		HttpSession session = req.getSession();
		LoginOkBean loginOkBean = (LoginOkBean)session.getAttribute("loginOkBean");
		List<HotelDTO> hotelList = (List<HotelDTO>)session.getAttribute("hotelList");
		
		try{
			//ȸ���α��ν� u_num���� Ȯ��
			int u_num = loginOkBean.getU_num();
			for(HotelDTO hdto : hotelList) {
				if(hdto.getH_num() == h_num) {
					hdto.setWishList(displayHotelMapper.isWishCheck(h_num, u_num));
					req.setAttribute("hdto", hdto);
				}
			}
		
		}catch(Exception e) {
//			��ȸ���� u_num���� error�߻� ���ø���Ʈ üũ �ʿ����
			HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
			req.setAttribute("hdto", hdto);
		}
		
		
//		ȣ�ڿ� ���� ���� ����Ʈ
		List<ReviewDTO> reviewList = displayHotelMapper.reviewList(h_num);
		
//		���� ���ó��ϳ�¥ �����ֱ�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String today = sdf.format(time);
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		String tmr = sdf.format(date);
		
//      �ʿ� �� �ּҰ�
		HotelDTO hdtoForAddress = null;
		
		try{
			//ȸ���α��ν� u_num���� Ȯ��
			int u_num = loginOkBean.getU_num();
			for(HotelDTO hdto : hotelList) {
				if(hdto.getH_num() == h_num) {
					hdto.setWishList(displayHotelMapper.isWishCheck(h_num, u_num));
					hdtoForAddress = hdto;
					req.setAttribute("hdto", hdto);
				}
			}
		}catch(Exception e) {
//	         ��ȸ���� u_num���� error�߻� ���ø���Ʈ üũ �ʿ����
			HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
			hdtoForAddress = hdto;
			req.setAttribute("hdto", hdto);
		}
	      
		String addr = "";
		String h_address = hdtoForAddress.getH_address();

		for(int i=0; i<h_address.length(); i++) {
			String letter = String.valueOf(h_address.charAt(i));
			
			if(!letter.equals("@")) {
				addr += letter;
			} else {
				break;
			}
		}
		
		req.setAttribute("map_addr", addr);
		
		req.setAttribute("today", today);
		req.setAttribute("tmr", tmr);

		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("starAverage", starAverage);
		req.setAttribute("twinRoom", twinRoom);
		req.setAttribute("doubleRoom", doubleRoom);
		req.setAttribute("deluxeRoom", deluxeRoom);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("loginOkBean", loginOkBean);
		
		return "display/display_hotelContent";
	}
	
	
//	h_num�� room_num�� ��ġ�ϴ� ��� ã��
	@RequestMapping("/display_roomContent")
	public String roomContent(HttpServletRequest req, @RequestParam(required=false) String room_code, 
			int h_num, String indate, String outdate) {
//		ȣ������
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		System.out.println("room_code : "+room_code);
		List<RoomDTO> roomList = hotelMapper.listRoomInGroupByRoomCode(room_code);
		RoomDTO room = roomList.get(0);
		
		HttpSession session = req.getSession();
		
		String Sindate = null, Soutdate = null;
		if(indate == null && outdate == null) {
			Sindate = (String)session.getAttribute("indate");
			Soutdate = (String)session.getAttribute("outdate");
		}
		else {
			Sindate = indate;
			Soutdate = outdate;
			session.setAttribute("indate", Sindate);
			session.setAttribute("outdate", Soutdate);
		}
		
		Map<String, String> map = new Hashtable<>();
		map.put("book_indate", Sindate);
		map.put("book_outdate", Soutdate);
		
		for(RoomDTO rdto : roomList) {
			map.put("room_num", String.valueOf(rdto.getRoom_num()));
			rdto.setRoom_booked(displayHotelMapper.isBookedRoom(map));
		}
		
		List<RoomDTO> roomList2 = new ArrayList<RoomDTO>();
		for(RoomDTO rdto : roomList) {
			if(rdto.getRoom_booked().equals("n")) {
				roomList2.add(rdto);
			}
		}
		
		roomList = roomList2;
		
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", room);
		req.setAttribute("roomList", roomList);
		
		
		Map<String, String> map2 = new Hashtable<String, String>();
		
		map2.put("book_indate", (String)session.getAttribute("indate"));
		map2.put("book_outdate", (String)session.getAttribute("outdate"));
		map2.put("room_code", room.getRoom_code());
		
		int max_roomCount = hotelMapper.countRoomOnGroup(room.getRoom_code());
		List<Integer> bookedRoom = displayHotelMapper.getBookedRoomCount(map2);
		
		int booked_roomCount = bookedRoom.size();
		
		int bookable_roomCount = max_roomCount - booked_roomCount;
		
		System.out.println("�� �� �� : " + max_roomCount);
		System.out.println("����� �� �� : " + booked_roomCount);
		System.out.println("���డ�� �� �� : " + bookable_roomCount);
		
//		ȣ�ڱ⺻����
//		@�����ڷ� ���׵��� ������ �迭�� ����� -> jsp���� �迭 �ϳ��� ���+�ٰ�
		String[] hotelInfo = hdto.getH_info().split("@");
		String[] hotelNotice = hdto.getH_notice().split("@");
		req.setAttribute("hotelInfo", hotelInfo);
		req.setAttribute("hotelNotice", hotelNotice);
		req.setAttribute("bookable_roomCount", bookable_roomCount);
		
		return "display/display_roomContent";
	}

	@RequestMapping(value="display_selectDate")
	public String selectDate(HttpServletRequest req, @RequestParam(required = false) Map<String, String> params) {
		
		req.setAttribute("room_code", params.get("room_code"));
		req.setAttribute("h_num", params.get("h_num"));
		
		return "display/display_selectDate";
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
