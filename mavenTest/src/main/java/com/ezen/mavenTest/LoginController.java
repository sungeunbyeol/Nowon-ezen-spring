package com.ezen.mavenTest;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.mavenTest.service.LoginMapper;
import com.ezen.mavenTest.service.MemberMapper;

@Controller
public class LoginController {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private LoginCheck loginCheck;
	
	@Autowired
	private LoginOkBean loginOkBean;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping("/login.do")
	public ModelAndView Login() {
		return new ModelAndView("login/login");
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView Logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "�α׾ƿ� �Ǿ����ϴ�.");
		req.setAttribute("url", "main.do");
		return new ModelAndView("message");
	}
	
	@RequestMapping("/searchLogin.do")
	public String SearchLogin(HttpServletRequest req, @RequestParam String mode) {
		String title = mode.equals("id")? "���̵�" : "��й�ȣ";
		req.setAttribute("title", title);
		return "login/search";
	}
	
	@RequestMapping("/searchCheckLogin.do")
	public ModelAndView SearchCheck(HttpServletRequest req, @RequestParam Map<String, String> params) {
		String msg = memberMapper.searchMember(params.get("name"), params.get("ssn1"), params.get("ssn2"), params.get("id"));
		req.setAttribute("msg", msg);
		if (msg != null) {
			return new ModelAndView("closeWindow");
		}
		if (params.get("id") == null) {
			req.setAttribute("msg", "���̵� ã�� �� �����ϴ�. �ٽ� Ȯ���� �ּ���!!");
			req.setAttribute("url", "searchLogin.do?mode=id");
		}else {
			req.setAttribute("msg", "�ش��ϴ� ������ ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���� �ּ���!!");
			req.setAttribute("url", "searchLogin.do?mode=pw");
		}
		return new ModelAndView("message");
	}
	
	@RequestMapping("/login_ok.do")
	public ModelAndView LoginOk(HttpServletRequest req, HttpServletResponse resp, @RequestParam String saveId) {
		
		loginCheck.setId(req.getParameter("id"));
		loginCheck.setPasswd(req.getParameter("passwd"));
		
		Cookie ck = new Cookie("saveId", loginCheck.getId());
		int res = loginMapper.checkLogin();
		String msg = null, url = null;
		switch(res){
		case LoginCheck.OK :
			if(saveId == null) {
				ck.setMaxAge(0);
			}else {
				ck.setMaxAge(12*60*60);
			}
			resp.addCookie(ck);
			
			loginOkBean.setId(loginCheck.getId());
			loginOkBean.isMemberSetting();
			
			HttpSession session = req.getSession();
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "�α��� �Ǿ����ϴ�.";
			url = "main.do";
			break;
		case LoginCheck.NOT_ID :
			msg = "���� ���̵� �Դϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���";
			url = "login.do";
			break;
		case LoginCheck.NOT_PWD :
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���Ͻð� �Է��� �ּ���";
			url = "login.do";
			break;
		case LoginCheck.ERROR :
			msg = "DB���� ���� �߻�!! �����ڿ��� ������ �ּ���!!";
			url = "login.do";
			break;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return new ModelAndView("message");
	}

}
