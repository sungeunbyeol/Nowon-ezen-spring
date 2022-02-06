package com.byeol.student.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byeol.student.model.StudentDTO;

@Service
public class StudentMapper {

	@Autowired
	SqlSession sqlSession;
	
	public int InsertStudent(StudentDTO dto) {
		int �μ�Ʈ = sqlSession.insert("insert", dto);
		return �μ�Ʈ;
	}
	
	public List<StudentDTO> ListStudent() {
		List<StudentDTO> ����Ʈ = sqlSession.selectList("list");
		return ����Ʈ;
	}
	
	public int deletestudent(String id) {
		int ����Ʈ = sqlSession.delete("delete",id);
		return ����Ʈ;
	}
	
	public StudentDTO findstudent(String name) {
		StudentDTO ã�� = sqlSession.selectOne("find",name);
		return ã��;
	}
}
