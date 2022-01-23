package com.eunbyeol.individaul.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eunbyeol.individaul.model.UserListDTO;

@Service
public class LoginMapper {

	@Autowired
	private SqlSession sqlSession;
	public UserListDTO checkLogin(String email) {
		return sqlSession.selectOne("checkLogin",email);
	}
}
