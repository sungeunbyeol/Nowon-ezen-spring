package com.ezen.mavenTest.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.mavenTest.LoginCheck;
import com.ezen.mavenTest.model.MemberDTO;

@Service
public class LoginMapper {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private LoginCheck loginCheck;
	
	public int checkLogin() {
			return loginCheck.checkLogin();
	}
	
	public MemberDTO isMemberSetting(String id) {
		return sqlSession.selectOne("checkLogin",id);
	}
}













