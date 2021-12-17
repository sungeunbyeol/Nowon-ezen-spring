package com.ezen.maven.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.maven.model.MemberDTO;

@Service
public class LoginMapper {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	MemberMapper memberMapper;
	
	public MemberDTO checkLogin(String id) {
			return sqlSession.selectOne("checkLogin", id);
	}
}













