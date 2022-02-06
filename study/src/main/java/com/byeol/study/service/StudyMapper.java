package com.byeol.study.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byeol.study.model.StudyDTO;

@Service
public class StudyMapper {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insertbooklist(StudyDTO dto) {
		int insert = sqlSession.insert("insertbook",dto);
		return insert;
	}
	
	public List<StudyDTO> listbookpage(){
		List<StudyDTO> dto2 = sqlSession.selectList("list");
		return dto2;
	}
	
	public int deletename(String bookname) {
		int µô¸®Æ® = sqlSession.delete("deletebook", bookname);
		return µô¸®Æ®;
	}
	
	public StudyDTO findbook(String search, String searchString) {
		Map<String, String> map = new Hashtable<String, String>();
		String sql = "select * from library where " + search + " = '" + searchString + "'";
		// select * from library where bookname = #{bookname}
		// select * from library where publisher = #{publisher}
		// select * from library where author = #{author}
		map.put("sql", sql);
		StudyDTO dto = sqlSession.selectOne("find",map);
		return dto;
	}
	
	
	/*public StudyDTO findbook(String search, String searchString) {
		
		Map<String, String> map = new Hashtable<String, String>();
//		map.put("String", search);
//		map.put("String", searchString);
		String sql = "select * from library where " + search + " = '" + searchString+"'";
		map.put("sql", sql);
		
		StudyDTO dto = sqlSession.selectOne("find",map);
		return dto;
	}
	*/
}
