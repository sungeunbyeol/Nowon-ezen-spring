package com.ezen.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.project.model.CompanyDTO;
import com.ezen.project.service.CompanyLoginMapper;

@Controller 
public class CompanyLoginOkBean {
	private int c_num;
	private String c_ceo;
	private String c_bnum;
	private String c_password;
	private String c_name;
	private String c_tel;
	private String c_email;
	private String c_address;
	private String a_level;
	private String c_image;
	private String c_joindate;

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public String getC_ceo() {
		return c_ceo;
	}

	public void setC_ceo(String c_ceo) {
		this.c_ceo = c_ceo;
	}

	public String getC_bnum() {
		return c_bnum;
	}

	public void setC_bnum(String c_bnum) {
		this.c_bnum = c_bnum;
	}

	public String getC_password() {
		return c_password;
	}

	public void setC_password(String c_password) {
		this.c_password = c_password;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_tel() {
		return c_tel;
	}

	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public String getA_level() {
		return a_level;
	}

	public void setA_level(String a_level) {
		this.a_level = a_level;
	}

	public String getC_image() {
		return c_image;
	}

	public void setC_image(String c_image) {
		this.c_image = c_image;
	}

	public String getC_joindate() {
		return c_joindate;
	}

	public void setC_joindate(String c_joindate) {
		this.c_joindate = c_joindate;
	}

	public CompanyLoginMapper getCompanyLoginMapper() {
		return companyLoginMapper;
	}

	public void setCompanyLoginMapper(CompanyLoginMapper companyLoginMapper) {
		this.companyLoginMapper = companyLoginMapper;
	}

	@Autowired
	CompanyLoginMapper companyLoginMapper;
	
	public boolean isCompanySetting() {
		CompanyDTO dto = companyLoginMapper.companyCheckLogin(c_email);
		if (dto != null) {
			c_num = dto.getC_num();
			c_ceo = dto.getC_ceo();
			c_bnum = dto.getC_bnum();
			c_password = dto.getC_password();
			c_name = dto.getC_name();
			c_tel = dto.getC_tel();
			c_email = dto.getC_email();
			c_address = dto.getC_address();
			a_level = dto.getA_level();
			c_image = dto.getC_image();
			c_joindate = dto.getC_joindate();
			 
			return true;
		
		}
		return false;
		
	}
	}

