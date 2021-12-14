package com.ezen.mavenTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.ezen.mavenTest.model.MemberDTO;

@Controller
public class LoginCheck {
	
	@Autowired
	private SqlSession sqlSession;
	
	public static final int OK = 0;
	public static final int NOT_ID = 1;
	public static final int NOT_PWD = 2;
	public static final int ERROR = -1;
	
	private String id;
	private String passwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public int checkLogin() {
		MemberDTO dto = sqlSession.selectOne("checkLogin", id);
		try {
			if(dto.getPasswd().equals(passwd)) {
				return OK;
			}
			else {
				return NOT_PWD;
			}
		}catch(Exception e) {
			return NOT_ID;
		}
	}
}
















