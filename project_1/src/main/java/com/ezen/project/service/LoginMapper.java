package com.ezen.project.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.UserDTO;

@Service
public class LoginMapper {
	@Autowired
	private SqlSession sqlSession;
	public UserDTO checkLogin(String u_email) {
		return sqlSession.selectOne("checkLogin", u_email);
		
	}

}
