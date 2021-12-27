package com.ezen.project.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMyPageMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	
}
