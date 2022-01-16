package com.eunbyeol.individaul;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping(value="/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/index")
	public String main() {
		return "index";
	}

	@RequestMapping("/joinform")
	public String joinform() {
		return "user/joinform";
	}
	
	@RequestMapping("/joinok")
	public String joinok() {
		return "user/loginform";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "user/loginform";
	}
	
	@RequestMapping("/qnaboard")
	public String qnaboard() {
		return "user/qnaboard";
	}
	
}
