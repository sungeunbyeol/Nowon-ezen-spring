package com.eunbyeol.individaul;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/index")
	public String Index() {
		return "index";
	}
	
	//회원가입 페이지로
	@RequestMapping("/joinform")
	public ModelAndView JoinForm() {
		return new ModelAndView("user/joinform");
	}
	
	//로그인 페이지로
	@RequestMapping("/loginform")
	public String LoginForm() {
		return "user/loginform";
	}
	
	//Q&A 페이지로
	@RequestMapping("/qnaboard")
	public String QnAboard() {
		return "user/qnaboard";
	}
}
