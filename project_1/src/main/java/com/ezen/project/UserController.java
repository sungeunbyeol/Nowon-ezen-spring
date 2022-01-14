package com.ezen.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.DisplayHotelMapper;
import com.ezen.project.service.UserMapper;


@Controller
public class UserController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	DisplayHotelMapper displayHotelMapper;
	
	//user_join_check.jsp에서 보냄 
	@RequestMapping("/user_join_ok")
	public ModelAndView UserJoinOk(HttpServletRequest req, 
			@ModelAttribute UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		int res = userMapper.insertUser(dto);
		if (res>0) {
			mav.addObject("msg", "회원가입성공!! 메인페이지로 이동합니다.");
			mav.addObject("url", "/project");
		}else {
			mav.addObject("msg", "회원가입실패!! 다시 입력해 주세요!!");
			mav.addObject("url", "/user_join");
		}
		
		mav.setViewName("message");
		return mav;

	}
	
	//회원 탈퇴페이지 user_mypage.jsp에서 보냄 
	@RequestMapping("/user_delete")
	public String deletePage() {
		return "/user/user_delete";
	}
	
	//user_delete.jsp에서 보냄 회원탈퇴 실행
	@RequestMapping("/user_delete_user")  
	public String deleteOkPage(HttpServletRequest req) {
		String u_num = req.getParameter("u_num");
		String u_password = req.getParameter("u_password");	
		int res = userMapper.UserdeleteUser(u_num, u_password);
   
		if(res>0){
			req.setAttribute("msg", "회원 탈퇴되었습니다.");
			req.setAttribute("url", "main");
			HttpSession sessio = req.getSession();
			sessio.invalidate();
		}else { 
			req.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("url", "user_delete");
		}
		
		return "message"; 
	}
	
	//user_join에서 보냄 email 중복검사 진행 
	@RequestMapping("/checkUseremail")
	public String checkUser(@RequestParam String u_email, 
			String u_password, String u_password2, String u_name, String u_nickname, 
			String u_tel, String u_birth, HttpServletRequest req) {

		if(u_email.equals("")) {
			req.setAttribute("msg", "이메일을 입력해야 합니다.");
			req.setAttribute("url", "user_join");
			return "message";
		}
		boolean isUser = userMapper.isCheckUser(u_email);
		if (isUser) {
			req.setAttribute("msg", "이미 가입된 계정입니다.");
			req.setAttribute("url", "user_join");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("u_email", u_email);
			session.setAttribute("u_password", u_password);
			session.setAttribute("u_password2", u_password2);
			session.setAttribute("u_name", u_name);
			session.setAttribute("u_nickname", u_nickname);
			session.setAttribute("u_tel", u_tel);
			session.setAttribute("u_birth", u_birth);

			req.setAttribute("msg", "사용 가능한 이메일 입니다.");
			req.setAttribute("url", "user_join_check");
		}
		return "message";
	} 
	
	//중복검사 후 회원가입 페이지로 이동
	@RequestMapping("/user_join_check")
	public String userJoinCheck(String u_email) {

		return "user/user_join_check";
	}
	
	//user_info.jsp에서 보냄 회원정보 수정 
	@RequestMapping("user_edit_ok")
	public String editUserOkForm(HttpServletRequest req,
			@ModelAttribute UserDTO dto) {

		int res = userMapper.updateUser(dto);  
		if (res>0) {
			req.setAttribute("msg", "회원수정성공!! 마이페이지로 이동합니다.");
			req.setAttribute("url", "/project/user_myPage");
		}else{
			req.setAttribute("msg", "회원수정실패!! 다시 입력해 주세요.");
			req.setAttribute("url", "/project/user_info");
		} 
		return "message";

	}
	
	//user_search에서 보냄  mode로 구별 한 뒤 email or password찾기
	@RequestMapping("/user_search_ok")
	public String userSearchOk(HttpServletRequest req) {
		String mode = req.getParameter("mode");
		String u_name = req.getParameter("u_name");
		String u_tel = req.getParameter("u_tel");
		String u_email = req.getParameter("u_email");
		String msg = null, url = null; 
		  
		try {
			msg = userMapper.searchUser(u_name, u_tel, u_email);
			url = null;
			req.setAttribute("msg", msg);
		}catch(Exception e){

		} 
		if (msg != null) {
			if(mode.equals("u_email")) {
				return "/user/user_search_email_ok";
			}else if(mode.equals("u_password")) {
				
				req.setAttribute("u_email", u_email);
				return "/user/user_update_password";
			}
 
		}
		if(u_email ==null) {
			msg = "아이디를 찾을수 없습니다. 다시 확인해 주세요.";
			url = "/project/user_search?mode=u_email";
		}else {
			msg = "해당하는 정보가 일치하지 않습니다. 다시 확인해주세요";
			url = "/project/user_search?mode=u_password";
		} 
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";

	}
	
	//user_mypage.jsp에서 넘김 
	@RequestMapping("/user_password_edit")
	public String updateUserPassword() {
		return "/user/user_password_edit";
	} 
	
	//user_password_edit에서 넘김 
	@RequestMapping("/user_update_password_ok")
	public String updatepassword(HttpServletRequest req, 
			@RequestParam String u_email ) {
		
		UserDTO dto = userMapper.getUser(u_email);
		dto.setU_password(req.getParameter("u_password")); 
		int res = userMapper.updateUserPassword(dto);
		HttpSession session = req.getSession();
		session.invalidate();
		if (res>0) { 
			req.setAttribute("msg", "비밀번호 변경 성공!! 다시 로그인해주세요");
			req.setAttribute("url", "/project");
		}else {
			req.setAttribute("msg", "비밀번호 변경 실패!!");
			req.setAttribute("url", "/project");
		} 
		return "message";
	}

	//user_login에서 mode값이랑 같이 넘겨준다. 
	@RequestMapping("/user_search")
	public String userSearch() {
		return "user/user_search";
	}
 
	@RequestMapping("/user_search_email_ok")
	public String userSearchEmailOk() {
		return "user/user_search_email_ok";
	}

	@RequestMapping("/user_update_password")
	public String userUpdatePassword() {
		return "user/user_update_password";
	}

	@RequestMapping("/user_join")
	public String userJoin() {
		return "user/user_join";
	}

	@RequestMapping("/user_info")
	public String userInfo() {
		return "user/user_info";
	}
	
}
