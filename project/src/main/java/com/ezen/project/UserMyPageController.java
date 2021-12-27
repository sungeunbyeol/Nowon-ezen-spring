package com.ezen.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.project.model.BookingDTO;
import com.ezen.project.model.HotelDTO;
import com.ezen.project.model.RoomDTO;
import com.ezen.project.model.UserDTO;
import com.ezen.project.service.DisplayHotelMapper;
import com.ezen.project.service.UserJoinMapper;

@Controller
public class UserMyPageController {
	
	@Autowired
	UserJoinMapper userMapper;
	
	@Autowired
	DisplayHotelMapper displayHotelMapper;

	@RequestMapping("/user_myPage")
	public String userMyPage() {
		return "user/user_myPage";
	}
	
	@RequestMapping("/user_info")
	public String userInfo() {
		return "user/user_info";
	}
	
	@RequestMapping("/user_bookList")
	public String userBookList() {
		return "user/user_bookList";
	}
	
	@RequestMapping("/user_pointList")
	public String userpointList() {
		return "user/user_pointList";
	}
	
	@RequestMapping("/user_qna_list")
	public String userQnaList() {
		return "board/user_qna_list";
	}
	
	@RequestMapping("/user_qna_writeform")
	public String userQnaWriteform() {
		return "board/user_qna_writeform";
	}
	
	@RequestMapping("/user_wishlist")
	public String userWishlist() {
		return "user/user_wishlist";
	}
	
	@RequestMapping("/user_bookWriteform")
	public String userBookWriteform(HttpServletRequest req, @RequestParam int h_num, String type, int u_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		RoomDTO Room = displayHotelMapper.typeRoom(h_num, type);
		UserDTO udto = displayHotelMapper.userInfo(u_num);
		req.setAttribute("hdto", hdto);
		req.setAttribute("Room", Room);
		req.setAttribute("udto", udto);
		return "user/user_bookWriteform";
	}
	
	@RequestMapping("/user_bookDetail")
	public String userBookDetail(HttpServletRequest req, @RequestParam int h_num, int u_num) {
		HotelDTO hdto = displayHotelMapper.hotelDetail(h_num);
		BookingDTO bdto = displayHotelMapper.bookInfo(u_num, h_num, 0);
		req.setAttribute("hdto", hdto);
		req.setAttribute("bdto", bdto);
		return "user/user_bookDetail";
	}
	
	@RequestMapping("/user_bookCancel")
	public String userBookCancel(HttpServletRequest req, @RequestParam int room_price, int book_num) {
		BookingDTO bdto = displayHotelMapper.bookInfo(0,0,book_num);
//		session값으로 유저dto 가져와서 req로 내려보내줘야함
		req.setAttribute("bdto", bdto);
		req.setAttribute("room_price", room_price);
		return "user/user_bookCancel";
	}
}
