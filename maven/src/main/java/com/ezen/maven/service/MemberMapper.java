package com.ezen.maven.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.maven.model.MemberDTO;

@Service
public class MemberMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean checkMember(Map<String, String> map) {
			MemberDTO dto = sqlSession.selectOne("checkMember", map);
			if (dto != null) {
				return true;
			}else {
				return false;
			}
	}
	
	public int insertMember(MemberDTO dto) {
			return sqlSession.insert("insertMember", dto);
	}
	
	public List<MemberDTO> listMember(){
		return sqlSession.selectList("listMember");
	}
	
	public  int deleteMember(int no) {
		return sqlSession.delete("deleteMember", no);
	}
	
	public MemberDTO getMember(int no) {
			return sqlSession.selectOne("getMember", no);
	}
	
	public int updateMember(MemberDTO dto) {
		return sqlSession.update("updateMember", dto);
	}
	
	public List<MemberDTO> findMember(String search, String searchString){
		Map<String, String> map = new Hashtable<String, String>();
		try {
			map.put("search", search);
			map.put("searchString", searchString);
			return sqlSession.selectList("findMember",map);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchMember(String name, String ssn1, String ssn2, String id) {
		return null;
	}
	
}













