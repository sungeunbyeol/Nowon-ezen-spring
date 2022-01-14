package com.ezen.project.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.project.model.CompanyDTO;

@Service
public class CompanyLoginMapper {
	@Autowired
	private SqlSession sqlSession;
	public CompanyDTO companyCheckLogin(String c_email) {
		return sqlSession.selectOne("companyCheckLogin", c_email);
	}

}
