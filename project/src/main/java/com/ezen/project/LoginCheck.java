package com.ezen.project;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.project.model.UserDTO;

@Controller
public class LoginCheck {
	
	@Autowired
	private SqlSession sqlSession;
	
	public static final int OK = 0;
	public static final int NOT_EMAIL = 1;
	public static final int NOT_PWD = 2;
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
	public void setU_passwd(String u_password) {
		this.u_password = u_password;
	}
	
	public int checkLogin() {
		UserDTO dto = sqlSession.selectOne("checkLogin", u_email);
		try {
			if(dto.getU_password().equals(u_password)) {
				return OK;
			}
			else {
				return NOT_PWD;
			}
		}catch(Exception e) {
			return NOT_EMAIL;
		}
	}
}
















