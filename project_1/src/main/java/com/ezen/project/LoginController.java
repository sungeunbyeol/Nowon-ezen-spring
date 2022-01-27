package com.ezen.project;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	private LoginCheck loginCheck;
	
	@Autowired
	private LoginOkBeanUser loginOkBean;

	@Autowired
	private LoginOkBeanCompany companyLoginOkBean;

	@RequestMapping("/user_login")
	public String userLogin() {
		return "user/user_login";
	}
	
	@RequestMapping("/company_login")
	public String companyLogin() {
		return "company/company_login";
	}
	
	@RequestMapping("/activity_login")
	public String activityLogin() {
		return "activitymain/activity_login";
	}

	@RequestMapping("/user_login_ok")
	public ModelAndView userLoginOk(HttpServletRequest req,HttpServletResponse resp) {
		loginCheck.setU_email(req.getParameter("u_email"));
		loginCheck.setU_password(req.getParameter("u_password"));
		
		//��Ű�� �̸��� ���� ���� 
		String saveEmail = req.getParameter("saveEmail");
		//��Ű�� �̸��� ���� 
		Cookie ck = new Cookie("saveEmail", loginCheck.getU_email());
 
		String kakaoEmail = req.getParameter("kakaoEmail");
		String kakaonickname = req.getParameter("kakaonickname");
		String kakaobirthday = req.getParameter("kakaobirth");
		String kakaobirthyear = req.getParameter("kakaobrithyear");
		String kakaoname = null;
		String kakaobirth = null;
		
		if(kakaobirthday!=null && kakaobirthyear!=null) {
			kakaobirth = kakaobirthyear + kakaobirthday;
		}  
		
		if(req.getParameter("kakaoname").equals("undefined")) {		 
			kakaoname = null;			 
		}else {
			kakaoname=req.getParameter("kakaoname");				
		}
		
		if(!kakaoEmail.equals("")) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("kakaoEmail", kakaoEmail);
			mav.addObject("kakaonickname", kakaonickname);
			mav.addObject("kakaoname", kakaoname);
			mav.addObject("kakaobirth", kakaobirth);
			mav.addObject("kakaobirthyear", kakaobirthyear); 
			mav.setViewName("/user/user_join"); 
			return mav; 
		}

		int res = loginCheck.checkULogin();
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
			url = "main";
			break; 

		case LoginCheck.NOT_email : 
			msg = "���� ���� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ֽñ� �ٶ��ϴ�.";
			url = "user_login";
			break; 

		case LoginCheck.NOT_password : 
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է¹ٶ��ϴ�.";
			url = "user_login";
			break;

		case LoginCheck.ERROR : 
			msg = "�������� �߻� �����ڿ��� ���� �ٶ��ϴ�.";
			url = "user_login";
			break;

		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("message");
	}
	
	@RequestMapping("/company_login_ok")
	public ModelAndView companyLoginOk(HttpServletRequest req,HttpServletResponse resp) {
		loginCheck.setC_email(req.getParameter("c_email"));
		loginCheck.setC_password(req.getParameter("c_password"));
		String saveEmail = req.getParameter("saveEmail");
		
		Cookie ck = new Cookie("saveEmail", loginCheck.getC_email());
		int res = loginCheck.checkCLogin();
		String msg = null, url = null;
		switch(res) {
		case LoginCheck.OK :
			if(saveEmail == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			
			companyLoginOkBean.setC_email(loginCheck.getC_email());
			boolean islogin = companyLoginOkBean.isCompanySetting();
			HttpSession session = req.getSession();
			session.setAttribute("companyLoginOkBean", companyLoginOkBean);
			msg = "�α��� �Ǿ����ϴ�.";
			url = "company_main";
			break; 
			  
		case LoginCheck.NOT_email : 
			msg = "���� ���� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ֽñ� �ٶ��ϴ�.";
			url = "company_login";
			break;
		
		case LoginCheck.NOT_password : 
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է¹ٶ��ϴ�.";
			url = "company_login";
			break;
			 
		case LoginCheck.ERROR : 
			msg = "�������� �߻� �����ڿ��� ���� �ٶ��ϴ�.";
			url = "company_login";
			break;
			
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("message");
	}
	
	@RequestMapping("/activity_login_ok")
	public ModelAndView activityLoginOk(HttpServletRequest req,HttpServletResponse resp) {

		loginCheck.setU_email(req.getParameter("u_email"));
		loginCheck.setU_password(req.getParameter("u_password"));
		//��Ű�� �̸��� ���� ���� 
		String saveEmail = req.getParameter("saveEmail");
		//��Ű�� �̸��� ���� 
		Cookie ck = new Cookie("saveEmail", loginCheck.getU_email());
 
		String kakaoEmail = req.getParameter("kakaoEmail");
		String kakaonickname = req.getParameter("kakaonickname");
		String kakaobirthday = req.getParameter("kakaobirth");
		String kakaobirthyear = req.getParameter("kakaobrithyear");
		String kakaoname = null;
		String kakaobirth = null;
		
		if(kakaobirthday!=null && kakaobirthyear!=null) {
			kakaobirth = kakaobirthyear + kakaobirthday;
		}  
		
		if(req.getParameter("kakaoname").equals("undefined")) {		 
			kakaoname = null;			 
		}else {
			kakaoname=req.getParameter("kakaoname");				
		}
		
		if(!kakaoEmail.equals("")) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("kakaoEmail", kakaoEmail);
			mav.addObject("kakaonickname", kakaonickname);
			mav.addObject("kakaoname", kakaoname);
			mav.addObject("kakaobirth", kakaobirth);
			mav.addObject("kakaobirthyear", kakaobirthyear); 
			mav.setViewName("/user/user_join"); 
			return mav; 
		}

		int res = loginCheck.checkULogin();
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
				url = "activity_usermain"; 
				break; 

			case LoginCheck.NOT_email : 
				msg = "���� ���� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ֽñ� �ٶ��ϴ�.";
				url = "activity_login";
				break; 

			case LoginCheck.NOT_password : 
				msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է¹ٶ��ϴ�.";
				url = "activity_login";
				break;

			case LoginCheck.ERROR : 
				msg = "�������� �߻� �����ڿ��� ���� �ٶ��ϴ�.";
				url = "activity_login";
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
		req.setAttribute("url", "main");
		return "message";
	}

	@RequestMapping("/company_logout") 
	public String companyLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "company_main");
		return "message";
		
	}
	
	@RequestMapping("/activity_logout") 
	public String activityLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "activity_usermain");
		return "message";
	}
} 
