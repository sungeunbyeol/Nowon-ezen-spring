package member.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.dto.MemberDTO;

public class MemberMapper {

	private static SqlSessionFactory sqlMapper;
	
	static {  
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("Configuration.xml 파일을 빌드하는데 실패하였습니다.");
		}
	}
	
	public List<MemberDTO> listMember(){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			return
		}finally {
			sqlSession.close();
		}
	}
	
	public MemberDTO getMember(int no) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			
			return
		}finally {
			sqlSession.close();
		}
	}
	
	public int insertMember(MemberDTO dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	
	public int deleteMember(int no) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	public int updateMember(MemberDTO dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	public List<MemberDTO> findMember(String search, String searchString){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			Map<String, Integer> map = new Hashtable<>();
			 
			 List<memberDTO> find = 
		}finally {
			sqlSession.close();
		}
	}
	
	
	
	
	
}
