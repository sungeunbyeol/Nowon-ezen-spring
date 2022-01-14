package com.ezen.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.ezen.project.model.CompanyDTO;
import com.ezen.project.service.CompanyLoginMapper;

@Controller
public class CompanyLoginCheck {
	public static final int OK = 0;
	public static final int NOT_C_email = 1;
	public static final int NOT_C_password = 2;
	public static final int ERROR = -1;
	
	private String c_email;
	private String c_password;
	
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public String getC_password() {
		return c_password;
	}

	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	@Autowired
	CompanyLoginMapper companyLoginMapper;
	 
	 public int checkLogin() {
	      try {
	         CompanyDTO dto = companyLoginMapper.companyCheckLogin(c_email);
	         if(dto == null) {
	            return NOT_C_email;
	         }else {
	         if (dto.getC_password().equals(c_password)) {
	            return OK;
	         }else {
	            return NOT_C_password;
	         }
	         } 
	      }catch(EmptyResultDataAccessException e) {
	         return NOT_C_email;
	      } 
	}
}



