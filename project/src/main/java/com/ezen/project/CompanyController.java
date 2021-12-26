package com.ezen.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {
	
	@RequestMapping("/company_login")
	public String companyLogin() {
		return "company/company_login";
	}
	
	@RequestMapping("/company_search")
	public String companySearch() {
		return "company/company_search";
	}
	
	@RequestMapping("/company_search_email_ok")
	public String companySearchEmailOk() {
		return "company/company_search_email_ok";
	}
	
	@RequestMapping("/company_update_password")
	public String companyUpdatePassword() {
		return "company/company_update_password";
	}
	
	@RequestMapping("/company_register")
	public String companyRegister() {
		return "company/company_register";
	}
	
	@RequestMapping("/company_qna_list")
	public String companyQnaList() {
		return "board/company_qna_list";
	}
	
	@RequestMapping("/company_qna_writeform")
	public String companyQnaWriteform() {
		return "board/company_qna_writeform";
	}
	
	@RequestMapping("/company_myPage")
	public String companymyPage() {
		return "company/company_myPage";
	}
	
	@RequestMapping("/company_edit")
	public String companyEdit() {
		return "company/company_edit";
	}
	
	@RequestMapping("/company_bookList")
	public String companyBookList() {
		return "company/company_bookList";
	}
}
