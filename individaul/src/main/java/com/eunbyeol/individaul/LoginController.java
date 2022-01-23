package com.eunbyeol.individaul;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunbyeol.individaul.service.LoginMapper;
import com.eunbyeol.individaul.service.UserMapper;

@Controller
public class LoginController {
	@Autowired
	private LoginCheck loginCheck;
	@Autowired
	private LoginOkBean loginOkBean;
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/userloginok")
	public String loginOk(HttpServletRequest req, HttpServletResponse resp) {
		
		loginCheck.setEmail(req.getParameter("email"));
		loginCheck.setPassword(req.getParameter("password"));
		
		String saveEmail = req.getParameter("saveEmail");
		
		Cookie ck = new Cookie("saveEmail", loginCheck.getEmail());
		
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
			
			loginOkBean.setEmail(loginCheck.getEmail());
			boolean islogin = loginOkBean.isUserSetting();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(30*60);
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "로그인 되었습니다.";
			url = "/index";
			break;
			
			case LoginCheck.NOT_email : 
				msg = "없는 계정입니다. 다시확인해주세요.";
				url = "/user/loginform";
				break;
				
			case LoginCheck.NOT_password :
				msg = "비밀번호가 틀렸습니다. 다시 확인해주세요.";
				url = "/user/loginfrom";
				break;
				
			case LoginCheck.ERROR : 
				msg = "서버오류 발생, 관리자에게 문의 바랍니다.";
				url = "/user/loginform";
				break;
		}
		
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}
	
	@RequestMapping("/logout")
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("ms", "로그아웃 되었습니다.");
		req.setAttribute("url", "/index");
		return "message";
	}
	
}
