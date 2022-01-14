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
		session.setAttribute("indate", params.get("indate"));
		session.setAttribute("outdate", params.get("outdate"));
		
//		main.jsp�˻�â���� ������ üũ��/�ƿ� ��¥���� session�� �־��(user_bookWrite.jsp���� ���) 
		if(params.get("indate") != null && params.get("outdate") != null ) {
			session.setAttribute("indate", params.get("indate"));
			session.setAttribute("outdate", params.get("outdate"));
		}else {
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
		};
		
		
//		ȣ�ڿ� ���� ���� ����Ʈ
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
	
	
//	h_num�� room_num�� ��ġ�ϴ� ��� ã��
	@RequestMapping("/display_roomContent")
	public String roomContent(HttpServletRequest req, @RequestParam int room_num, int h_num) {
//		ȣ������
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoomDetail(room_num, h_num); //���ȣ �߰�
		
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		
		
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("book_indate", "2022-02-04");
		map.put("book_outdate", "2022-02-07");
		
		int max_roomCount = hotelMapper.countRoomOnGroup(Room.getRoom_code());
		List<Integer> bookedRoom = displayHotelMapper.getBookedRoomCount(map);
		
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
}
