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
		int 인설트 = sqlSession.insert("insert", dto);
		return 인설트;
	}
	
	public List<StudentDTO> ListStudent() {
		List<StudentDTO> 리스트 = sqlSession.selectList("list");
		return 리스트;
	}
	
	public int deletestudent(String id) {
		int 딜리트 = sqlSession.delete("delete",id);
		return 딜리트;
	}
	
	public StudentDTO findstudent(String name) {
		StudentDTO 찾기 = sqlSession.selectOne("find",name);
		return 찾기;
	}
}
