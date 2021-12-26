package com.ezen.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin_user_top")
	public String adminUserTop() {
		return "admin/admin_user_top";
	}
	
	@RequestMapping("/admin_user_list")
	public String adminUserList() {
		return "admin/admin_user_list";
	}
	
	@RequestMapping("/admin_user_blacklist")
	public String adminUserBlacklist() {
		return "admin/admin_user_blacklist";
	}
	
	@RequestMapping("/admin_company_list")
	public String adminCompanyList() {
		return "admin/admin_company_list";
	}
	
	@RequestMapping("/admin_user_qna")
	public String adminUserQna() {
		return "board/admin_user_qna";
	}
	
	@RequestMapping("/admin_company_qna")
	public String adminCompanyQna() {
		return "board/admin_company_qna";
	}
}
