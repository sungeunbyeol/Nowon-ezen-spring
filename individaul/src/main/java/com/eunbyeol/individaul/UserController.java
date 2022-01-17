package com.eunbyeol.individaul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eunbyeol.individaul.service.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value="/")
	public ModelAndView Index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/index")
	public String Main() {
		return "index";
	}

	//회원가입 페이지로
	@RequestMapping("/joinform")
	public String Joinform() {
		return "user/joinform";
	}
	
	//이메일 중복체크 
	@RequestMapping("/checkUseremail")
	public String CheckUserEmail() {
		
		return "message";
	}
	
	//이메일 중복체크 하고 넘어가는 회원가입 페이지
	@RequestMapping("/joincheck")
	public String JoinCheck() {
		return "user/joincheck";
	}
	
	//회원가입이 성공하면
	@RequestMapping("/userjoinok")
	public String Joincheck() {
		return "user/loginform";
	}

	//로그인 페이지
	@RequestMapping("/loginform")
	public String Loginform() {
		return "user/loginform";
	}
	
	//QnA페이지
	@RequestMapping("/qnaboard")
	public String Qnaboard() {
		return "user/qnaboard";
	}
	
}
