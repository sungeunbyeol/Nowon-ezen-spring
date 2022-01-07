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

	//로그아웃 할때 
	@RequestMapping("/user_logout")
	public ModelAndView Logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.setAttribute("msg", "로그아웃 되었습니다.");
		req.setAttribute("url", "main.do");
		return new ModelAndView("message");
	}
	
	// 회원탈퇴 페이지로
	@RequestMapping("/user_delete")
	public String deletePage() {
		return "/user/user_delete";
	}
	
	//회원탈퇴 시도했을 때
	@RequestMapping("/user_delete_user") 
	public String deleteOkPage(@RequestParam Map<String, String> params, HttpServletRequest req) {
		String u_num = req.getParameter("u_num");
		String u_password = req.getParameter("u_password");	
		int res = userjoinMapper.UserdeleteUser(u_num, u_password);
   
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
	
	//비밀번호 변경 페이지로
	@RequestMapping("/user_password_edit")
	public String updateUserPassword() {
		return "/user/user_password_edit";
	}

	// 비빌번호 변경 시도했을 때
	@RequestMapping("/user_update_password_ok")
	public String updatepassword(HttpServletRequest req, 
			@RequestParam String u_email ) {
		
		UserDTO dto = userjoinMapper.getUser(u_email);
		dto.setU_password(req.getParameter("u_password")); 
		int res = userjoinMapper.updateUserPassword(dto);
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
	
}
