package board.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class BoardMapper {
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
	
	public static List<BoardDBBean> listBoard(int start, int end){
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			Map<String, Integer> map = new Hashtable<>();
			map.put("start", start);
			map.put("end", end);
			List<BoardDBBean> list = sqlSession.selectList("listBoard", map);
			return list;
		}finally {
			sqlSession.close();
		}
	}
	
	public static int getCount() {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			int res = sqlSession.selectOne("getCount");
			return res;
		}finally {
			sqlSession.close();
		}
	}
	
	public static void readcount(String sql) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			java.util.Map<String, String> map = new java.util.Hashtable<>();
			map.put("sql", sql);
			int res = sqlSession.update("readcount", map);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	public static int insertBoard(BoardDBBean dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			String sql = "";
			if (dto.getNum() == 0) {
				sql = "update board set re_step = re_step + 1";
			}else {
				sql = "update board set re_step = re_step + 1 where re_step>" + dto.getRe_step();
				dto.setRe_step(dto.getRe_step()+1);
				dto.setRe_level(dto.getRe_level()+1);
			}
			readcount(sql);
			int res = sqlSession.insert("insertBoard", dto);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	
	public static BoardDBBean getBoard(int num, String mode) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			if (mode.equals("content")) {
				sqlSession.update("plusReadcount", num);
				sqlSession.commit();
			}
			BoardDBBean dto = sqlSession.selectOne("getBoard", num);
			return dto;
		}finally {
			sqlSession.close();
		}
	}
	
	public static int deleteBoard(int num, String passwd) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			BoardDBBean dto = sqlSession.selectOne("getBoard", num);
			if (!dto.getPasswd().equals(passwd)) {
				return -1;
			}
			int res = sqlSession.delete("deleteBoard", num);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	
	public static int updateBoard(BoardDBBean dto) {
		SqlSession sqlSession = sqlMapper.openSession();
		try {
			BoardDBBean dto2 = sqlSession.selectOne("getBoard", dto.getNum());
			if (!dto2.getPasswd().equals(dto.getPasswd())) {
				return -1;
			}
			int res = sqlSession.delete("updateBoard", dto);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
}















