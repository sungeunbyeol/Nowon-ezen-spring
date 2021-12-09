package com.ezen.mavenTest.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ezen.mavenTest.model.BoardDBBean;

public class BoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	

	public List<BoardDBBean> listBoard(int start, int end){
		return sqlSession.selectList("listBoard");
	}
	
	public int getCount() {
		return sqlSession.selectOne("getCount");
	}
	
	public void readcount(String sql) {		
			Map<String, Integer> map = new Hashtable<>();
			map.put("start", start);
			map.put("end", end);
			return sqlSession.selectList("listBoard", map);
	}
	
	public int insertBoard(BoardDBBean dto) {
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
			
		return sqlSession.insert("insertBoard", dto);
	}
	
	public BoardDBBean getBoard(int num, String mode) {
		return sqlSession.selectOne("getBoard", num);
	}
	
	public int deleteBoard(int num, String passwd) {
		return sqlSession.delete("deleteBoard", num);
	}
	
	public int updateBoard(BoardDBBean dto) {
		return sqlSession.delete("updateBoard", dto);
	}
}















