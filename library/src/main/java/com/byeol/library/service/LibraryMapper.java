package com.byeol.library.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byeol.library.model.LibraryDTO;

@Service
public class LibraryMapper {

	@Autowired
	SqlSession sqlSession;
	
	public int insertbook(LibraryDTO dto) {
		//return sqlSession.insert("insertbook", dto);
		int 아무거나 = sqlSession.insert("insertbook",dto);
		return 아무거나;
	}
	
	public List<LibraryDTO> listpage() {
		List<LibraryDTO> bookList = sqlSession.selectList("listpage");
		return bookList;
	}
	
	public int deletebook(String bookname) {
		int delete = sqlSession.delete("1", bookname);
		return delete;
	}
	
	public LibraryDTO findbook(String search, String searchString) {
		Map<String, String> map = new Hashtable<String, String>();
//		String ex = "예시";
//		map.put("ex", "ex");
//		map.put("ex", ex);
//		map.put("search", search);
//		map.put("searchString", searchString);
		
		String sql = "select * from library where " + search + " = '" + searchString+"'";
		map.put("sql", sql);
		LibraryDTO dto = sqlSession.selectOne("find", map);
		
		return dto;
		
	}
	
}
