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
	
	//user_join_check.jsp���� ���� 
	@RequestMapping("/user_join_ok")
	public ModelAndView UserJoinOk(HttpServletRequest req, 
			@ModelAttribute UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		int res = userMapper.insertUser(dto);
		if (res>0) {
			mav.addObject("msg", "ȸ�����Լ���!! ������������ �̵��մϴ�.");
			mav.addObject("url", "/project");
		}else {
			mav.addObject("msg", "ȸ�����Խ���!! �ٽ� �Է��� �ּ���!!");
			mav.addObject("url", "/user_join");
		}
		
		mav.setViewName("message");
		return mav;

	}
	
	//ȸ�� Ż�������� user_mypage.jsp���� ���� 
	@RequestMapping("/user_delete")
	public String deletePage() {
		return "/user/user_delete";
	}
	
	//user_delete.jsp���� ���� ȸ��Ż�� ����
	@RequestMapping("/user_delete_user")  
	public String deleteOkPage(HttpServletRequest req) {
		String u_num = req.getParameter("u_num");
		String u_password = req.getParameter("u_password");	
		int res = userMapper.UserdeleteUser(u_num, u_password);
   
		if(res>0){
			req.setAttribute("msg", "ȸ�� Ż��Ǿ����ϴ�.");
			req.setAttribute("url", "main");
			HttpSession sessio = req.getSession();
			sessio.invalidate();
		}else { 
			req.setAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			req.setAttribute("url", "user_delete");
		}
		
		return "message"; 
	}
	
	//user_join���� ���� email �ߺ��˻� ���� 
	@RequestMapping("/checkUseremail")
	public String checkUser(@RequestParam String u_email, 
			String u_password, String u_password2, String u_name, String u_nickname, 
			String u_tel, String u_birth, HttpServletRequest req) {

		if(u_email.equals("")) {
			req.setAttribute("msg", "�̸����� �Է��ؾ� �մϴ�.");
			req.setAttribute("url", "user_join");
			return "message";
		}
		boolean isUser = userMapper.isCheckUser(u_email);
		if (isUser) {
			req.setAttribute("msg", "�̹� ���Ե� �����Դϴ�.");
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

			req.setAttribute("msg", "��� ������ �̸��� �Դϴ�.");
			req.setAttribute("url", "user_join_check");
		}
		return "message";
	} 
	
	//�ߺ��˻� �� ȸ������ �������� �̵�
	@RequestMapping("/user_join_check")
	public String userJoinCheck(String u_email) {

		return "user/user_join_check";
	}
	
	//user_info.jsp���� ���� ȸ������ ���� 
	@RequestMapping("user_edit_ok")
	public String editUserOkForm(HttpServletRequest req,
			@ModelAttribute UserDTO dto) {

		int res = userMapper.updateUser(dto);  
		if (res>0) {
			req.setAttribute("msg", "ȸ����������!! ������������ �̵��մϴ�.");
			req.setAttribute("url", "/project/user_myPage");
		}else{
			req.setAttribute("msg", "ȸ����������!! �ٽ� �Է��� �ּ���.");
			req.setAttribute("url", "/project/user_info");
		} 
		return "message";

	}
	
	//user_search���� ����  mode�� ���� �� �� email or passwordã��
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
			msg = "���̵� ã���� �����ϴ�. �ٽ� Ȯ���� �ּ���.";
			url = "/project/user_search?mode=u_email";
		}else {
			msg = "�ش��ϴ� ������ ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���";
			url = "/project/user_search?mode=u_password";
		} 
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";

	}
	
	//user_mypage.jsp���� �ѱ� 
	@RequestMapping("/user_password_edit")
	public String updateUserPassword() {
		return "/user/user_password_edit";
	} 
	
	//user_password_edit���� �ѱ� 
	@RequestMapping("/user_update_password_ok")
	public String updatepassword(HttpServletRequest req, 
			@RequestParam String u_email ) {
		
		UserDTO dto = userMapper.getUser(u_email);
		dto.setU_password(req.getParameter("u_password")); 
		int res = userMapper.updateUserPassword(dto);
		HttpSession session = req.getSession();
		session.invalidate();
		if (res>0) { 
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���");
			req.setAttribute("url", "/project");
		}else {
			req.setAttribute("msg", "��й�ȣ ���� ����!!");
			req.setAttribute("url", "/project");
		} 
		return "message";
	}

	//user_login���� mode���̶� ���� �Ѱ��ش�. 
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
