package com.ezen.mavenTest.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.mavenTest.model.MemberDTO;

@Service
public class LoginMapper {

	@Autowired
	private SqlSession sqlSession;
	
	public MemberDTO checkLogin(String id) {
			return sqlSession.selectOne("checkLogin", id);
	}
}













