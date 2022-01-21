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
	
	//ȸ������ ��������
	@RequestMapping("/joinform")
	public ModelAndView JoinForm() {
		return new ModelAndView("user/joinform");
	}
	
	//�α��� ��������
	@RequestMapping("/loginform")
	public String LoginForm() {
		return "user/loginform";
	}
	
	//Q&A ��������
	@RequestMapping("/qnaboard")
	public String QnAboard() {
		return "user/qnaboard";
	}
}
