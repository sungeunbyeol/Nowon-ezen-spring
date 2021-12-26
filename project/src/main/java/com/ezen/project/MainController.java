package com.ezen.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "user_main";
	}
	
	@RequestMapping("/main")
	public String main2() {
		return "user_main";
	}
	
	@RequestMapping("/company_main")
	public String companyMain() {
		return "company/company_main";
	}
	
}
