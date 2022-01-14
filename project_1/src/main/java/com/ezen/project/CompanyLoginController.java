package com.ezen.project;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.service.CompanyLoginMapper;
import com.ezen.project.service.CompanyMapper;

@Controller
public class CompanyLoginController {
	@Autowired
	private CompanyLoginCheck companyLoginCheck;
	@Autowired
	private CompanyLoginOkBean companyLoginOkBean;
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
	CompanyLoginMapper companyLoginMapper;
	
	@RequestMapping("/company_login")
	public String companyLogin() {
		return "company/company_login";
	}
	
	@RequestMapping("/company_login_ok")
	public ModelAndView loginOk(HttpServletRequest req,HttpServletResponse resp) {
		companyLoginCheck.setC_email(req.getParameter("c_email"));
		companyLoginCheck.setC_password(req.getParameter("c_password"));
		String saveEmail = req.getParameter("saveEmail");
		
		Cookie ck = new Cookie("saveEmail", companyLoginCheck.getC_email());
		int res = companyLoginCheck.checkLogin();
		String msg = null, url = null;
		switch(res) {
		case CompanyLoginCheck.OK :
			if(saveEmail == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			
			companyLoginOkBean.setC_email(companyLoginCheck.getC_email());
			boolean islogin = companyLoginOkBean.isCompanySetting();
			HttpSession session = req.getSession();
			session.setAttribute("companyLoginOkBean", companyLoginOkBean);
			msg = "로그인 되었습니다.";
			url = "company_main";
			break; 
			  
		case CompanyLoginCheck.NOT_C_email : 
			msg = "없는 계정 입니다. 다시 확인하시고 입력해 주시길 바랍니다.";
			url = "company_login";
			break;
		
		case CompanyLoginCheck.NOT_C_password : 
			msg = "비밀번호가 틀렸습니다. 다시 확인하시고 입력바랍니다.";
			url = "company_login";
			break;
			 
		case CompanyLoginCheck.ERROR : 
			msg = "서버오류 발생 관리자에게 문의 바랍니다.";
			url = "company_login";
			break;
			
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("message");
	}
	@RequestMapping("/company_logout") 
	public String companyLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "/project/company_main");
		return "message";
		
		}


}


