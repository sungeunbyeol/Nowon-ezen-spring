package com.eunbyeol.individaul.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eunbyeol.individaul.model.UserListDTO;

@Service
public class UserMapper {

	@Autowired
	private SqlSession sqlSession;
	
	//�������
	public int insertUser(UserListDTO dto) {
		return sqlSession.insert("insertUser", dto);
	}
	
	//�̸��� �ߺ� �� �ִ��� Ȯ���ϱ�
	public boolean isUserCheck(String email) {
		UserListDTO dto = sqlSession.selectOne("checkuser", email);
		if(dto!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
