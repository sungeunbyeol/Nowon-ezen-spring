package com.ezen.project.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.LoginCheck;
import com.ezen.project.LoginOkBean;
import com.ezen.project.model.UserDTO;

@Service
public class UserJoinMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	LoginCheck loginCheck;
	
	@Autowired
	LoginOkBean loginOkBean;
	
	public boolean checkUser(Map<String, String> map) {
		UserDTO dto = sqlSession.selectOne("checkUser", map);
		if (dto != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertUser(UserDTO dto) {
			return sqlSession.insert("insertUser", dto);
	}
	
	public int checkLogin(String u_email, String u_password) {
			return loginCheck.checkLogin();
	}
	
	public UserDTO isMemberSetting(String email) {
		return sqlSession.selectOne("checkLogin",email);
	}
	
}