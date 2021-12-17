package com.ezen.maven.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.maven.model.BoardDBBean;


@Service
public class BoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDBBean> listBoard(int start, int end){
			Map<String, Integer> map = new Hashtable<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			return sqlSession.selectList("listBoard", map);
	}
	
	public int getCount() {
		return sqlSession.selectOne("getCount");
	}
	
	public int readcount(String sql) {
			Map<String, String> map = new Hashtable<String, String>();
			map.put("sql", sql);
			return sqlSession.update("readcount", map);
	}
	
	public int insertBoard(BoardDBBean dto) {
			String sql = "";
			if (dto.getNum() == 0) {
				sql = "update board set re_step = re_step + 1";
			}else {
				sql = "update board set re_step = re_step + 1 where re_step>" + dto.getRe_step();
				dto.setRe_step(dto.getRe_step()+1);
				dto.setRe_level(dto.getRe_level()+1);
			}
			readcount(sql);
			return sqlSession.insert("insertBoard", dto);

	}
	
	public BoardDBBean getBoard(int num, String mode) {
			if (mode.equals("content")) {
				sqlSession.update("plusReadcount", num);
			}
			return sqlSession.selectOne("getBoard", num);
	}
	
	public int deleteBoard(int num, String passwd) {
			BoardDBBean dto = sqlSession.selectOne("getBoard", num);
			if (!dto.getPasswd().equals(passwd)) {
				return -1;
			}
			return sqlSession.delete("deleteBoard", num);
	}
	
	public int updateBoard(BoardDBBean dto) {
			BoardDBBean dto2 = sqlSession.selectOne("getBoard", dto.getNum());
			if (!dto2.getPasswd().equals(dto.getPasswd())) {
				return -1;
			}
			return sqlSession.delete("updateBoard", dto);
	}
}















