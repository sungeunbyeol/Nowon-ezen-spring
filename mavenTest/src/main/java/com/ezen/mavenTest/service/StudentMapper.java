package com.ezen.mavenTest.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.mavenTest.model.StudentDTO;

@Service
public class StudentMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertStudent(StudentDTO dto) {
		return sqlSession.insert("insertStudent", dto);
	}
	
	public List<StudentDTO> listStudent(){
		return sqlSession.selectList("listStudent");
	}
	
	public int deleteStudent(String id) {
		return sqlSession.delete("deleteStudent",id);
	}
	
	public List<StudentDTO> findStuent(String name){
		return sqlSession.selectList("findStudent",  name);
	}
}
