package com.eunbyeol.individaul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.eunbyeol.individaul.model.UserListDTO;
import com.eunbyeol.individaul.service.LoginMapper;

@Controller
public class LoginCheck {
	public static final int OK = 0;
	public static final int NOT_email = 1;
	public static final int NOT_password = 2;
	public static final int ERROR = -1;
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Autowired
	LoginMapper loginMapper;
	
	public int checkLogin() {
		try {
										//jdbcTemplate.queryForObject(sql, mapper, id);
			UserListDTO dto = loginMapper.checkLogin(email);
			if(dto == null) {
				return NOT_email;
			}else {
				if(dto.getPassword().equals(password)) {
					return OK;
				}else {
					return NOT_password;
				}
			}
		}catch(EmptyResultDataAccessException e) {
			return NOT_email;
		}
	}
	
}
