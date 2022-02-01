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
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.ReviewDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.model.WishListDTO;
import com.ezen.project.service.DisplayHotelMapper;
import com.ezen.project.service.HotelMapper;
import com.ezen.project.service.UserMyPageMapper;

@Controller
public class DisplayHotelController {
	
	@Autowired
	private DisplayHotelMapper displayHotelMapper;
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@Autowired
	private UserMyPageMapper userMyPageMapper;
	
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
			Map<Integer, Double> starReview = null;
			List<HotelDTO> newHotelList = null;
			List<Map.Entry<Integer, Integer>> orderedByReviewCount = null;
			List<Map.Entry<Integer, Double>> orderedByStarCount = null;
			
			switch(params.get("filter")) {
				case "maxPrice":
					hotelList = displayHotelMapper.listHotelByMaxPrice(params.get("condition"));
					break;
				case "minPrice": 
					hotelList = displayHotelMapper.listHotelByMinPrice(params.get("condition"));
					break;
				case "maxReview": 
					hotelList = displayHotelMapper.listHotelByLocation(params.get("condition"));
//					������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//					Map<ȣ�ڰ�����, �ıⰹ��>
					countReview = displayHotelMapper.countReview(hotelList);
//					�ı� ��������� newHotelList��� ���ο� ����Ʈ�� �����سֱ�
					orderedByReviewCount = new LinkedList<>(countReview.entrySet());
					orderedByReviewCount.sort(Map.Entry.comparingByValue());
	         
					newHotelList = new ArrayList<HotelDTO>();
					
					for(Map.Entry<Integer, Integer> entry : orderedByReviewCount) {
						int order = entry.getKey();
						for(HotelDTO hdto : hotelList) {
							if(order == hdto.getH_num()) {
								newHotelList.add(hdto);
								break;
							}
						}
					}
					
					// �ı� �������� ����� �ı� ���������� ����
					Collections.reverse(newHotelList);
					
					hotelList = newHotelList;
					
					break;
				case "minReview": 
					hotelList = displayHotelMapper.listHotelByLocation(params.get("condition"));
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
				case "maxStar": 
					hotelList = displayHotelMapper.listHotelByLocation(params.get("condition"));
//					������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//					Map<ȣ�ڰ�����, �ıⰹ��>
					starReview = displayHotelMapper.averageReview(hotelList);
//					�ı� ��������� newHotelList��� ���ο� ����Ʈ�� �����سֱ�
					orderedByStarCount = new LinkedList<>(starReview.entrySet());
					orderedByStarCount.sort(Map.Entry.comparingByValue());
					         
					newHotelList = new ArrayList<HotelDTO>();
									
					for(Map.Entry<Integer, Double> entry : orderedByStarCount){
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
				case "minStar": 
					hotelList = displayHotelMapper.listHotelByLocation(params.get("condition"));
//					������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//					Map<ȣ�ڰ�����, �ıⰹ��>
					starReview = displayHotelMapper.averageReview(hotelList);
//					�ı� ��������� newHotelList��� ���ο� ����Ʈ�� �����سֱ�
					orderedByStarCount = new LinkedList<>(starReview.entrySet());
					orderedByStarCount.sort(Map.Entry.comparingByValue());
					         
					newHotelList = new ArrayList<HotelDTO>();
									
					for(Map.Entry<Integer, Double> entry : orderedByStarCount){
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
			starReview = displayHotelMapper.averageReview(hotelList);
         
//			���ΰ�ħ�ϰų� �ٸ��������� �ٳ�͵�, ������ �˻����� �״�� ������ �ֵ��� session�� hotelList���
			HttpSession session = req.getSession();
			req.setAttribute("condition", params.get("condition"));
			session.setAttribute("hotelList", hotelList);
         
			LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
      
			try{
				int u_num = loginInfo.getU_num();
         
//				ȸ������������ ȣ���� wishList�� ��ϵǾ��ִ��� �ƴ��� Ȯ��(��� 1, �̵�� 0)
				for(HotelDTO hdto : hotelList) {
					hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
				}
			}catch(Exception e) {};
			
			String[] addrs = new String[hotelList.size()];
			for(int i=0; i<hotelList.size(); i++) {
				HotelDTO hdto = hotelList.get(i);
				String addr = hdto.getH_address();
				String[] fullAddress = addr.split("\\(");
				addrs[i] = fullAddress[0];
			}
			
			String[] names = new String[hotelList.size()];
			for(int i=0; i<hotelList.size(); i++) {
				HotelDTO hdto = hotelList.get(i);
				names[i] = hdto.getH_name();
			}
			
			req.setAttribute("names", names); 
			req.setAttribute("addrs", addrs); 
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", starReview);
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
			List<HotelDTO> hotelList = displayHotelMapper.listHotelByLocation(params.get("condition"));
			
//			������ HotelDTO ����Ʈ���� �ıⰹ��, ������� ����� map�� �����
//			Map<ȣ�ڰ�����, �ıⰹ��>
			Map<Integer, Integer> countReview = displayHotelMapper.countReview(hotelList);
//			Map<ȣ�ڰ�����, �������>
			Map<Integer, Double> averageReview = displayHotelMapper.averageReview(hotelList);
			
//			���ΰ�ħ�ϰų� �ٸ��������� �ٳ�͵�, ������ �˻����� �״�� ������ �ֵ��� session�� hotelList���
			HttpSession session = req.getSession();
			session.setAttribute("hotelList", hotelList);
			
			LoginOkBeanUser loginInfo = (LoginOkBeanUser)session.getAttribute("loginOkBean");
			try{
				int u_num = loginInfo.getU_num();
			
//			ȸ������������ ȣ���� wishList�� ��ϵǾ��ִ��� �ƴ��� Ȯ��(��� 1, �̵�� 0)
				for(HotelDTO hdto : hotelList) {
					hdto.setWishList(displayHotelMapper.isWishCheck(hdto.getH_num(), u_num));
				}
			}catch(Exception e) {};
			
			String[] addrs = new String[hotelList.size()];
			
			for(int i=0; i<hotelList.size(); i++) {
				HotelDTO hdto = hotelList.get(i);
				String addr = hdto.getH_address();
				String[] fullAddress = addr.split("\\(");
				addrs[i] = fullAddress[0];
			}
			
			String[] names = new String[hotelList.size()];
			
			for(int i=0; i<hotelList.size(); i++) {
				HotelDTO hdto = hotelList.get(i);
				names[i] = hdto.getH_name();
			}
			req.setAttribute("names", names); 
			req.setAttribute("addrs", addrs); 
			req.setAttribute("hotelList", hotelList);
			req.setAttribute("countReview", countReview);
			req.setAttribute("averageReview", averageReview);
		}
	}
	
//	h_num���� ȣ�� ������ ã��
	@RequestMapping("/display_hotelContent")
	public String hotelContent(HttpServletRequest req, @RequestParam int h_num, String indate, String outdate, int inwon) {
		HttpSession session = req.getSession();
		session.setAttribute("indate", indate);
		session.setAttribute("outdate", outdate);
		session.setAttribute("inwon", inwon);

//		ȣ�� ���� ����
		int reviewCount = displayHotelMapper.getReviewCountByHotel(h_num);
		
//		ȣ�� ���� ���
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
//		�� Ÿ�Ժ� ����
		List<RoomDTO> twinRoom = displayHotelMapper.listRoomByType(h_num, "TWIN");
		List<RoomDTO> doubleRoom = displayHotelMapper.listRoomByType(h_num, "DOUBLE");
		List<RoomDTO> deluxeRoom = displayHotelMapper.listRoomByType(h_num, "DELUXE");
		
//		���ø���Ʈ üũ
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		List<HotelDTO> hotelList = (List<HotelDTO>)session.getAttribute("hotelList");
		
		try {
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
			HotelDTO hdto = hotelMapper.getHotel(h_num);
			req.setAttribute("hdto", hdto);
		}
		
		
//		ȣ�ڿ� ���� ���� ����Ʈ
		List<ReviewDTO> reviewList = displayHotelMapper.listReviewByHotel(h_num);
		
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
			HotelDTO hdto = hotelMapper.getHotel(h_num);
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
			int h_num) {
//		ȣ������
		HotelDTO hdto = hotelMapper.getHotel(h_num);
		List<RoomDTO> roomList = hotelMapper.listRoomInGroupByRoomCode(room_code);
		RoomDTO room = roomList.get(0);
		
		HttpSession session = req.getSession();
		
		Map<String, String> map = new Hashtable<>();
		map.put("book_indate", (String)session.getAttribute("indate"));
		map.put("book_outdate", (String)session.getAttribute("outdate"));
		
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
		
		int booked_roomCount = displayHotelMapper.countBookedRoom(map2);
		
		int bookable_roomCount = max_roomCount - booked_roomCount;
		
		System.out.println("�� �� �� : " + max_roomCount);
		System.out.println("����� �� �� : " + booked_roomCount);
		System.out.println("���డ�� �� �� : " + bookable_roomCount);
		
//		ȣ�ڱ⺻����
//		@�����ڷ� ���׵��� ������ �迭�� ����� -> jsp���� �迭 �ϳ��� ���+�ٰ���
		String[] hotelInfo = hdto.getH_info().split("@");
		String[] hotelNotice = hdto.getH_notice().split("@");
		req.setAttribute("hotelInfo", hotelInfo);
		req.setAttribute("hotelNotice", hotelNotice);
		req.setAttribute("bookable_roomCount", bookable_roomCount);
		
		return "display/display_roomContent";
	}
	
	@RequestMapping("/review")
	public String review(HttpServletRequest req, @RequestParam int h_num) {
		
//		ȣ�ڿ� ���� ���� ����Ʈ
		List<ReviewDTO> reviewList = displayHotelMapper.listReviewByHotel(h_num);
		
//		ȣ�� ���� ����
		int reviewCount = displayHotelMapper.getReviewCountByHotel(h_num);
		
//		ȣ�� ���� ���
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
		req.setAttribute("reviewCount", reviewCount);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("starAverage", starAverage);
		return "board/review";
	}
	
	//����Ʈ�������� �̵��� ��
	@RequestMapping("/user_pointList")
	public ModelAndView userpointList(HttpServletRequest req, @RequestParam(required = false) String pageNum) {
		
		HttpSession session = req.getSession();
		LoginOkBeanUser loginokbean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		
		ModelAndView mav = new ModelAndView();
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		int number = 0;
		int rowCount = 0;
		
		rowCount = userMyPageMapper.getPointCount(u_num);
		
		if (endRow>rowCount) endRow = rowCount;
		number = rowCount - startRow + 1;
		
		List<ReviewDTO> pointList = userMyPageMapper.listPoint(startRow, endRow, u_num);
		
		mav.addObject("pointList", pointList);
		mav.addObject("number", number);
		mav.addObject("rowCount", rowCount);
		
		if (rowCount>0) {
//				[1] [2] [3]
			int pageBlock = 2;
//				31(�Խñۼ�) / 5  =  �� : 6, ������ = 1
			int pageCount = rowCount / pageSize;
//				�������� 0�� �ƴϸ�, ������ �Խñ� �����ֱ� ���� ��++ ����
			if (rowCount%pageSize != 0){
				pageCount++;
			}
//										(1-1)	/	3		*	3		+ 1   = 1
//										(2-1)	/	3		*	3		+ 1   = 1
//										(4-1)	/	3		*	3		+ 1	  = 4
			int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
//									1	+	3	-	1	= 3..
//									4	+	3	-	1	= 6..
//									7	+	3	-	1	= 9
			int endPage = startPage + pageBlock - 1;
//						3	>	7	����
//						9	>	7	��		endPage = 7(������������ �ѹ��� 7�� ��)
			if (endPage > pageCount) endPage = pageCount;
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageBlock", pageBlock);
			mav.addObject("pageCount", pageCount);
		}
		
		mav.setViewName("user/user_pointList");
		
		return mav;
	}

	//���ø���Ʈ�� �̵�
	@RequestMapping("/user_wishlist")
	public ModelAndView userWishlist(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginOkBeanUser loginokbean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		int u_num = loginokbean.getU_num();
		List<WishListDTO> wdto = userMyPageMapper.listWishList(u_num);
		List<WishListDTO> wdtoAct = userMyPageMapper.wishListActView(u_num);
		req.setAttribute("wishListAct", wdtoAct);
		req.setAttribute("wishList", wdto);
		return new ModelAndView("user/user_wishlist", "wishList", wdto);
	}
	
//	hotelSeachOk ���������� ���ø���Ʈ üũ/����
	@RequestMapping(value="/wishRelease")
	public String wishRelease(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayHotelMapper.wishRelease(params);
		searchResult(req, params);
		return "display/display_hotelSearchOk";
	}
	@RequestMapping(value="/wishCheck")
	public String wishCheck(HttpServletRequest req,@RequestParam Map<String, String> params) {
		displayHotelMapper.wishCheck(params);
		searchResult(req, params);
		return "display/display_hotelSearchOk";
	}
	
//	hotelContent ���������� ���ø���Ʈ üũ/����
	@RequestMapping(value="/wishReleaseHC")
	public String wishReleaseHC(HttpServletRequest req, @RequestParam Map<String, String> params) {
		displayHotelMapper.wishRelease(params);
		hotelContentWish(req, Integer.parseInt(params.get("h_num")), params.get("indate"), params.get("outdate"), Integer.parseInt(params.get("inwon")));
		return "display/display_hotelContent";
	}
	@RequestMapping(value="/wishCheckHC")
	public String wishCheckHC(HttpServletRequest req,@RequestParam Map<String, String> params) {
		displayHotelMapper.wishCheck(params);
		hotelContentWish(req, Integer.parseInt(params.get("h_num")), params.get("indate"), params.get("outdate"), Integer.parseInt(params.get("inwon")));
		return "display/display_hotelContent";
	}
	
	protected void hotelContentWish(HttpServletRequest req, @RequestParam int h_num, String indate, String outdate, int inwon) {
		HttpSession session = req.getSession();
		session.setAttribute("indate", indate);
		session.setAttribute("outdate", outdate);
		session.setAttribute("inwon", inwon);

//		ȣ�� ���� ����
		int reviewCount = displayHotelMapper.getReviewCountByHotel(h_num);
		
//		ȣ�� ���� ���
		double starAverage = displayHotelMapper.reviewStar(h_num);
		starAverage = Math.round(starAverage*10)/10.0;//�Ҽ� 1��° �ڸ����� ǥ��
		
//		�� Ÿ�Ժ� ����
		List<RoomDTO> twinRoom = displayHotelMapper.listRoomByType(h_num, "TWIN");
		List<RoomDTO> doubleRoom = displayHotelMapper.listRoomByType(h_num, "DOUBLE");
		List<RoomDTO> deluxeRoom = displayHotelMapper.listRoomByType(h_num, "DELUXE");
		
//		���ø���Ʈ üũ
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		List<HotelDTO> hotelList = (List<HotelDTO>)session.getAttribute("hotelList");
		
		try {
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
			HotelDTO hdto = hotelMapper.getHotel(h_num);
			req.setAttribute("hdto", hdto);
		}
		
		
//		ȣ�ڿ� ���� ���� ����Ʈ
		List<ReviewDTO> reviewList = displayHotelMapper.listReviewByHotel(h_num);
		
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
			HotelDTO hdto = hotelMapper.getHotel(h_num);
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
	}
	
//	wishList������ ���� ���ø���Ʈ ����
	@RequestMapping(value="/wishRelease3")
	public String wishRelease3(HttpServletRequest req, @RequestParam int w_num) {
		displayHotelMapper.wishRelease2(w_num);
		return "user/user_wishlist";
	}
	
//	wishList������ ���� ���ø���Ʈ ����
	@RequestMapping(value="/wishRelease5")
	public String wishRelease5(HttpServletRequest req, @RequestParam int w_num) {
		displayHotelMapper.wishRelease3(w_num);
		return "user/user_wishlist";
	}
}
