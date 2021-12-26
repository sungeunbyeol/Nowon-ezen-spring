package com.ezen.project;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.UserJoinMapper;

@Controller
public class UserJoinController {

	@Autowired
	private UserJoinMapper userjoinMapper;
	@Autowired
	private LoginCheck loginCheck;
	@Autowired
	private LoginOkBean loginOkBean;
	@Autowired
	WishListController wishList;
	
	//회원가입 화면으로 이동
	@RequestMapping(value="/user_join" , method=RequestMethod.GET)
	public String userJoin() {
		return "user/user_join";
	}
	
	//회원가입 하기
	@RequestMapping(value="/user_join", method=RequestMethod.POST)
	public String InsertOkUser(HttpServletRequest req, UserDTO dto) {
		int res = userjoinMapper.insertUser(dto);
		if (res>0) {
			req.setAttribute("msg", "회원가입에 성공하였습니다.");
			req.setAttribute("url", "main.do");
		}else {
			req.setAttribute("msg", "회원가입을 다시 시도해주세요.");
			req.setAttribute("url", "user_join.do");
		}
		return "message";
	}

	// 로그인 화면으로 이동
	@RequestMapping("/user_login")
	public String userLogin() {
		return "user/user_login";
	}
	
	

	
	
	@RequestMapping("/searchLogin.do")
	public String SearchLogin(HttpServletRequest req, @RequestParam String mode) {
		String title = mode.equals("id")? "아이디" : "비밀번호";
		req.setAttribute("title", title);
		return "login/search";
	}
	
	
	

	//이메일 비번 찾기
//	@RequestMapping("/user_search")
//	public ModelAndView SearchCheck(HttpServletRequest req, @RequestParam Map<String, String> params) {
//		String msg = userjoinMapper.searchMember(params.get("name"), params.get("ssn1"), params.get("ssn2"), params.get("id"));
//		req.setAttribute("msg", msg);
//		if (msg != null) {
//			return new ModelAndView("forward:closeWindow.jsp");
//		}
//		if (params.get("u_email") == null) {
//			req.setAttribute("msg", "이메일을 찾을 수 없습니다. 다시 확인해 주세요!!");
//			req.setAttribute("url", "searchLogin.do?mode=id");
//		}else {
//			req.setAttribute("msg", "해당하는 정보가 일치하지 않습니다. 다시 확인해 주세요!!");
//			req.setAttribute("url", "searchLogin.do?mode=pw");
//		}
//		return new ModelAndView("message");
//	}
	
	//로그인 시도했을 때
	@RequestMapping("/user_login_ok")
	public String userLoginOk(HttpServletRequest req, HttpServletResponse resp, @RequestParam(required = false) String saveEmail) {
		loginCheck.setU_email(req.getParameter("u_email"));
		loginCheck.setU_passwd(req.getParameter("u_password"));
		
		Cookie ck = new Cookie("saveEmail", loginCheck.getU_email());
		
		int res = userjoinMapper.checkLogin(loginCheck.getU_email(), loginCheck.getU_password());
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
			loginOkBean.isMemberSetting();
			
			HttpSession session = req.getSession();
			session.setAttribute("loginOkBean", loginOkBean);
			msg = "로그인 되었습니다.";
			url = "main";
			break;
		case LoginCheck.NOT_EMAIL :
			msg = "없는 아이디 입니다. 다시 확인하시고 입력해 주세요";
			url = "user_login";
			break;
		case LoginCheck.NOT_PWD :
			msg = "비밀번호가 틀렸습니다. 다시 확인하시고 입력해 주세요";
			url = "user_login";
			break;
		case LoginCheck.ERROR :
			msg = "DB서버 오류 발생!! 관리자에게 문의해 주세요!!";
			url = "user_login";
			break;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message";
	}

	
	@RequestMapping("/user_logout")
	public ModelAndView Logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "/");
		return new ModelAndView("message");
	}
	
	
	

	
	//이메일 찾기 결과
	@RequestMapping("/user_search_email_ok")
	public String userSearchEmailOk() {
		return "user/user_search_email_ok";
	}
	
	@RequestMapping("/user_update_password")
	public String userUpdatePassword() {
		return "user/user_update_password";
	}
	

	
}
