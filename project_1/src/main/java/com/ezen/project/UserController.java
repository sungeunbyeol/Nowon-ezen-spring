package com.ezen.project;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.UserMapper;
import com.ezen.project.service.UserMyPageMapper;

@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMyPageMapper userMyPageMapper;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	//������������ �̵�
	@RequestMapping("/user_myPage")
	public String userMyPage() {
		return "user/user_myPage";
	}
	
	//user_join_check.jsp���� ���� 
	@RequestMapping("/user_join_ok")
	public ModelAndView UserJoinOk(HttpServletRequest req, 
			@ModelAttribute UserDTO udto) {
		ModelAndView mav = new ModelAndView();
		
		// ��й�ȣ ��ȣȭ
		String u_password = pwdEncoder.encode(udto.getU_password());
		
		// DTO�� ��ȣȭ�� ��й�ȣ�� ����
		udto.setU_password(u_password);
		int res = userMapper.insertUser(udto);
		if (res>0) {
			mav.addObject("msg", "ȸ�����Լ���!! ������������ �̵��մϴ�.");
			mav.addObject("url", "main");
		}else {
			mav.addObject("msg", "ȸ�����Խ���!! �ٽ� �Է��� �ּ���!!");
			mav.addObject("url", "user_join");
		}
		
		mav.setViewName("message");
		return mav;

	}
	
	//ȸ�� Ż�������� user_mypage.jsp���� ���� 
	@RequestMapping("/user_delete")
	public String userDelete() {
		return "/user/user_delete";
	}
	
	//user_delete.jsp���� ���� ȸ��Ż�� ����
	@RequestMapping("/user_delete_user")
	public String userDeleteUser(HttpServletRequest req, String raw_password) {
		HttpSession session = req.getSession();
		
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		int u_num = loginOkBean.getU_num();
		
		// ��ȣȭ�� ���� ��й�ȣ�� ������
		String u_password = userMapper.getUserPassword(u_num);
		
		// ���� ��й�ȣ�� �Է��� ��й�ȣ�� ��ġ�ϸ� ����
		if(pwdEncoder.matches(raw_password, u_password)) {
			int res = userMapper.deleteUser(u_num, u_password);
			
			if(res>0){
				session.invalidate();
				
				req.setAttribute("msg", "ȸ�� Ż��Ǿ����ϴ�.");
				req.setAttribute("url", "main");
			}else { 
				req.setAttribute("msg", "ȸ�� Ż�� �����Ͽ����ϴ�. �ٽ� �õ����ּ���.");
				req.setAttribute("url", "user_delete");
			}
		}else {
			req.setAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
			req.setAttribute("url", "user_delete");
		}
		
		return "message"; 
	}
	
	//user_join���� ���� email �ߺ��˻� ���� 
	@RequestMapping("/checkUseremail")
	public String checkUserEmail(@RequestParam String u_email, 
			String u_password, String u_password2, String u_name, String u_nickname, 
			String u_tel, String u_birth, HttpServletRequest req) {

		if(u_email.equals("")) {
			req.setAttribute("msg", "�̸����� �Է��ؾ� �մϴ�.");
			req.setAttribute("url", "user_join");
			return "message";
		}
		UserDTO udto = userMapper.getUserByEmail(u_email);
		if (udto != null) {
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
	
	//user_search���� ����  mode�� ���� �� �� email or passwordã��
	@RequestMapping("/user_search_ok")
	public String userSearchOk(HttpServletRequest req) {
		String mode = req.getParameter("mode");
		String u_name = req.getParameter("u_name");
		String u_tel = req.getParameter("u_tel");
		String u_email = req.getParameter("u_email");
		String msg = null, url = null; 
		  
		try {
			msg = userMapper.searchUserInfo(u_name, u_tel, u_email);
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
		
		if(u_email == null) {
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
	
	//���� �г��� ����
		@RequestMapping("/user_infoChange")
		public String userInfoChange(HttpServletRequest req, @RequestParam String nickname) {
			HttpSession session = req.getSession();
			LoginOkBeanUser loginokbean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
			int u_num = loginokbean.getU_num();
			
			Map<String, String> params = new Hashtable<String, String>();
			params.put("u_num", String.valueOf(u_num));
			params.put("nickname", nickname);
			
			int res = userMyPageMapper.changeNickName(params);
			String msg = null , url = null;
			if(res > 0) {
				loginokbean.setU_nickname(nickname);
				session.setAttribute("loginOkBean", loginokbean);
				msg="�г����� �����Ǿ����ϴ�";
				url="user_info";
			} else {
				msg="�г��� ������ �����߽��ϴ�";
				url="user_info";
			}

			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			
			return "message";
		}

		//���� ��ȭ��ȣ ����
		@RequestMapping("/user_telChange")
		public String userTelChange(HttpServletRequest req, @RequestParam String u_tel) {
			HttpSession session = req.getSession();
			LoginOkBeanUser loginokbean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
			int u_num = loginokbean.getU_num();
			
			Map<String, String> params = new Hashtable<String, String>();
			params.put("u_num", String.valueOf(u_num));
			params.put("u_tel", u_tel);
			
			int res = userMyPageMapper.changeUserTel(params);
			String msg = null , url = null;
			if(res > 0) {
				loginokbean.setU_tel(u_tel);
				session.setAttribute("loginOkBean", loginokbean);
				msg="��ȭ��ȣ�� �����Ǿ����ϴ�";
				url="user_info";
			} else {
				msg="��ȭ��ȣ ������ �����߽��ϴ�";
				url="user_info";
			}

			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			
			return "message";
		}
	
//	��ȸ���� ȸ������ ������ ������ �Ҷ� ����
	@RequestMapping("/user_needLogin")
	public ModelAndView userNeedLogin(HttpServletRequest req) {
		req.setAttribute("msg", "�α����� �ʿ��� ���� �Դϴ�");
		req.setAttribute("url", "user_login");
		return new ModelAndView("message");
	}
	
//	��й�ȣ ���� �� Ȯ��
	@RequestMapping("/user_passwordCheck")
	public String userPasswordCheck(HttpServletRequest req) {
		return "user/user_passwordCheck";
	}
	
	//�������������� ��й�ȣ ������ ������ �� 
	@RequestMapping("/user_password_edit_ok")
	public String userPasswordEditOk(HttpServletRequest req, String u_email, 
			String raw_pre_password, String raw_new_password) {
		
		UserDTO dto = userMapper.getUserByEmail(u_email);
		
		HttpSession session = req.getSession();
		LoginOkBeanUser loginOkBean = (LoginOkBeanUser)session.getAttribute("loginOkBean");
		
		// ���� ��й�ȣ
		String pre_password = loginOkBean.getU_password();
		
		// ����ڰ� �Է��� ���� ��й�ȣ�� ���� ��й�ȣ�� ��
		if(pwdEncoder.matches(raw_pre_password, pre_password)) {
			// ���� ��й�ȣ�� �°� �Է����� ���
			
			// DTO�� �� ��й�ȣ ����
			String new_password = pwdEncoder.encode(raw_new_password);
			dto.setU_password(new_password);
			
			// ��й�ȣ ������Ʈ
			int res = userMapper.updateUserPassword(dto);
			
			if (res>0) {
				// ������ ���� ����
				session.invalidate();
				
				req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���");
				req.setAttribute("url", "main");
			}else {
				req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �õ����ּ���.");
				req.setAttribute("url", "user_passwordCheck");
			}
		}else {
			// ���� ��й�ȣ�� �߸� �Է����� ���
			req.setAttribute("msg", "���� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�. �ٽ� Ȯ�����ּ���.");
			req.setAttribute("url", "user_passwordCheck");
		}
		
		return "message";
	} 
	
	// �α��� ���� ��й�ȣ ã�⿡�� ��й�ȣ�� ������ ��
	@RequestMapping("/user_update_password")
	public String userUpdatePassword() {
		return "user/user_update_password";
	}
	
	@RequestMapping("/user_update_password_ok")
	public String userUpdatePasswordOk(HttpServletRequest req, @RequestParam String u_email) {
		UserDTO udto = userMapper.getUserByEmail(u_email);
		
		// �� ��й�ȣ ��ȣȭ
		String new_password = pwdEncoder.encode(req.getParameter("raw_password"));
		
		// DTO�� �� ��й�ȣ ����
		udto.setU_password(new_password);
		
		// ��й�ȣ ������Ʈ
		int res = userMapper.updateUserPassword(udto);
		HttpSession session = req.getSession();
		
		if (res>0) {
			session.invalidate();
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �α������ּ���.");
			req.setAttribute("url", "user_login");
		}else {
			req.setAttribute("msg", "��й�ȣ ���� ����!! �ٽ� �õ����ּ���.");
			req.setAttribute("url", "user_login");
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

	@RequestMapping("/user_joinchoose")
	public String userJoinchoose() {
		return "user/user_joinchoose"; 
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
