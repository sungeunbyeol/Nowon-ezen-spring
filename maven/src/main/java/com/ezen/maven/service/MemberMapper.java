package com.ezen.maven.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.maven.model.MemberDTO;

@Service
public class MemberMapper {

	@Autowired
	SqlSession sqlSession;

	public boolean checkMember(Map<String, String>params) {
		MemberDTO dto = sqlSession.selectOne("membercheck", params);
		if(dto != null) {
			return true;
		}else {
			return false;
		}
	}
	
}
