package login.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.dto.MemberDTO;

public class LoginMapper {

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
	
	public static MemberDTO checkLogin(String id) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("checkLogin", id);
			return dto;
		}finally {
			session.close();
		}
	}
}













