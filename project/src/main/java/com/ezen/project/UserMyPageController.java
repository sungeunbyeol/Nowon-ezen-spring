package com.ezen.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.UserJoinMapper;

@Controller
public class UserMyPageController {
	
	@Autowired
	UserJoinMapper userMapper;

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
	public String userBookWriteform() {
		return "user/user_bookWriteform";
	}
	
	@RequestMapping("/user_bookDetail")
	public String userBookDetail() {
		return "user/user_bookDetail";
	}
	
	@RequestMapping("/user_bookCancel")
	public String userBookCancel() {
		return "user/user_bookCancel";
	}
}
