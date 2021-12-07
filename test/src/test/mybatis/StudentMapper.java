package test.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import test.dto.StudentDTO;

public class StudentMapper {
	
	private static SqlSessionFactory sqlMapper;
	
	static {
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("Configuration.xml ������ �����ϴµ� �����Ͽ����ϴ�.");
		}
	}
	
	public static int insertStudent(StudentDTO dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res = sqlSession.insert("insertStudent", dto);
			sqlSession.commit(); //insert, update, delete �۾� �� �ݵ�� ����� �Ѵ�.
			return res;
		} finally {
			sqlSession.close();
		}
	}
	
	public static List<StudentDTO> listStudent(){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			List<StudentDTO> list = sqlSession.selectList("listStudent");
			return list;
		} finally {
			sqlSession.close();
		}
	}
	
	public static int deleteStudent(String id) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res=sqlSession.delete("deleteStudent",id);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	
	public static List<StudentDTO> findStudent(String name){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			List<StudentDTO> find = sqlSession.selectList("findStudent",name);
			return find;
		} finally {
			sqlSession.close();
		}
	}
}
