package com.ezen.project;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.service.LoginMapper;
import com.ezen.project.service.UserMapper;

@Controller
public class LoginController {
	@Autowired
	private LoginCheck loginCheck;
	@Autowired
	private LoginOkBean loginOkBean;
	@Autowired
	UserMapper userMapper;
	@Autowired
	LoginMapper loginMapper;

	@RequestMapping("/user_login")
	public String userLogin() {
		return "user/user_login";
	}

	@RequestMapping("/user_login_ok")
	public ModelAndView loginOk(HttpServletRequest req,HttpServletResponse resp) {

		loginCheck.setU_email(req.getParameter("u_email"));
		loginCheck.setU_password(req.getParameter("u_password"));
		//��Ű�� �̸��� ���� ���� 
		String saveEmail = req.getParameter("saveEmail");
		//��Ű�� �̸��� ���� 
		Cookie ck = new Cookie("saveEmail", loginCheck.getU_email());
 
		int res = loginCheck.checkLogin();
		String msg = null, url = null;
		switch(res) {
		case LoginCheck.OK :
			if(saveEmail == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);

			loginOkBean.setU_email(loginCheck.getU_email());
			boolean islogin = loginOkBean.isUserSetting();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(30*60);
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "�α��� �Ǿ����ϴ�.";
			url = "/project/main";
			break; 

		case LoginCheck.NOT_U_email : 
			msg = "���� ���� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ֽñ� �ٶ��ϴ�.";
			url = "/project/user_login";
			break; 

		case LoginCheck.NOT_U_password : 
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է¹ٶ��ϴ�.";
			url = "/project/user_login";
			break;

		case LoginCheck.ERROR : 
			msg = "�������� �߻� �����ڿ��� ���� �ٶ��ϴ�.";
			url = "/user_login";
			break;

		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("message");
	}
	@RequestMapping("/user_logout") 
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "/project");
		return "message";

	}




} 



