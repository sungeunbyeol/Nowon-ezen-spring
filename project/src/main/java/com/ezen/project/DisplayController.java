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
	
	
//	ȣ�� �˻��� HotelDTO����Ʈ ��ȯ ����
	@RequestMapping("/display_hotelSearchOk")
	public String hotelSearchOk(HttpServletRequest req, @RequestParam Map<String,String> params) {
//		params�� �˻���, �ο���, üũ��, üũ�ƿ��� �޾� sql���� �������� ����ҵ�(�̿ϼ�)
		
//		main.jsp�˻�â���� ������ üũ��/�ƿ� ��¥���� session�� �־��(user_bookWrite.jsp���� ���) 
		HttpSession session = req.getSession();
		session.setAttribute("indate", params.get("indate"));
		session.setAttribute("outdate", params.get("outdate"));
		session.setAttribute("inwon", params.get("inwon"));
		
//		�Ʒ� �޼ҵ� ����
		searchResult(req, params);
		
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
			
//		ȸ������������ ȣ���� wishList�� ��ϵǾ��ִ��� �ƴ��� Ȯ��(��� 1, �̵�� 0)
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
		List<RoomDTO> twinRoom = displayHotelMapper.typeRoomList(h_num, "twin");
		List<RoomDTO> doubleRoom = displayHotelMapper.typeRoomList(h_num, "double");
		List<RoomDTO> deluxeRoom = displayHotelMapper.typeRoomList(h_num, "deluxe");
		
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
		
//		ȣ�ڱ⺻����
//		@�����ڷ� ���׵��� ������ �迭�� ����� -> jsp���� �迭 �ϳ��� ���+�ٰ�
		String[] hotelInfo = hdto.getH_info().split("@");
		String[] hotelNotice = hdto.getH_notice().split("@");
		req.setAttribute("hotelInfo", hotelInfo);
		req.setAttribute("hotelNotice", hotelNotice);
		
//		���Ǽ��� �ľ��Ͽ� roomCount�� 0�̸� [����] ǥ��
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
