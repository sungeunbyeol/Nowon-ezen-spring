package com.eunbyeol.individaul;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eunbyeol.individaul.model.UserListDTO;
import com.eunbyeol.individaul.service.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	//이메일 중복체크 
	@RequestMapping("/checkUseremail")
	public String CheckUserEmail(HttpServletRequest req, @RequestParam String email, String password,
			String password2, String name, String nickname, String tel, String birthday) {
		
		if(email.equals("")) {
			req.setAttribute("msg", "이메일을 입력해주세요");
			req.setAttribute("url", "joinform");
			return "message";
		}
		boolean checkuser = userMapper.isUserCheck(email);
		if(checkuser) {
			req.setAttribute("msg", "중복된 이메일 입니다.");
			req.setAttribute("url", "joinform");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("eamil", email);
			session.setAttribute("password", password);
			session.setAttribute("password2", password2);
			session.setAttribute("name", name);
			session.setAttribute("nicname", nickname);
			session.setAttribute("tel", tel);
			session.setAttribute("birthday", birthday);
			
			req.setAttribute("msg", "사용가능한 이메일입니다.");
			req.setAttribute("url", "joincheck");
		}
		return "message";		
	}
	
	//이메일 중복체크 했으면 joincheck(중복확인 된 페이지)
	@RequestMapping("/joincheck")
	public String JoinCheck(String email) {
		return "user/joincheck";
	}
	
	//회원가입이 성공하면
	@RequestMapping("/userjoinok")
	public String Joincheck(HttpServletRequest req, UserListDTO dto) {
		int res = userMapper.insertUser(dto);
		if(res>0) {
			req.setAttribute("msg", "회원가입을 축하드립니다.");
			req.setAttribute("url", "index");
		}else {
			req.setAttribute("msg", "회원가입에 실패했습니다. 다시 시도해주세요.");
			req.setAttribute("url", "joinform");
		}
		return "message";
	}
	
	
	//아이디 찾기
	
	
	//비번찾기
	
	
	//회원수정
	


	//회원탈퇴
	
	

	
	

}
