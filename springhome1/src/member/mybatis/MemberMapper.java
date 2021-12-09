package member.mybatis;

import java.io.IOException;
import java.io.Reader;
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
	
	public static boolean checkMember(Map<String, String> map) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("checkMember", map);
			if (dto != null) {
				return true;
			}else {
				return false;
			}
		}finally {
			session.close();
		}
	}
	
	public static int insertMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.insert("insertMember", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	
	public static List<MemberDTO> listMember(Map<String, String> map){
		SqlSession session = sqlMapper.openSession();
		List<MemberDTO> list = null;
		try {
			if (map.get("mode") == null) {
				list = session.selectList("listMember");
			}else {
				if (map.get("search") == null) {
					map.put("search", "id");
					map.put("searchString", "");
				}
				list = session.selectList("findMember", map);
			}
			return list;
		}finally {
			session.close();
		}
	}
	
	public static int deleteMember(int no) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.delete("deleteMember", no);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
	
	public static MemberDTO getMember(int no) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("getMember", no);
			return dto;
		}finally {
			session.close();
		}
	}
	
	public static int updateMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.update("updateMember", dto);
			session.commit();
			return res;
		}finally {
			session.close();
		}
	}
}













