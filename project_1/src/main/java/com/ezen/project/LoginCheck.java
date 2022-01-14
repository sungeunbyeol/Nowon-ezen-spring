package com.ezen.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.ezen.project.model.UserDTO;
import com.ezen.project.service.LoginMapper;

@Controller
public class LoginCheck {
	public static final int OK = 0;
	public static final int NOT_U_email = 1;
	public static final int NOT_U_password = 2;
	public static final int ERROR = -1;

	private String u_email;
	private String u_password;

	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	@Autowired
	LoginMapper loginMapper;

	//로그인 성공여부 
	public int checkLogin() {
		try {
			UserDTO dto = loginMapper.checkLogin(u_email);	//jdbcTemplate.queryForObject(sql, mapper, id);
			if(dto == null) {
				return NOT_U_email;
			}else {
				if (dto.getU_password().equals(u_password)) {
					return OK;
				}else {
					return NOT_U_password;
				}
			} 
		}catch(EmptyResultDataAccessException e) {
			return NOT_U_email;
		} 

	}
}
